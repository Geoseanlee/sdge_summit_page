package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * 文件上传控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/file")
@CrossOrigin
public class FileUploadController {

    @Autowired
    private OssService ossService;

    /**
     * 上传图片
     *
     * @param file 图片文件
     * @return 上传结果
     */
    @PostMapping("/upload/image")
    public Result<Map<String, Object>> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 验证文件是否为空
            if (file == null || file.isEmpty()) {
                return Result.error(400, "请选择要上传的文件");
            }

            // 验证文件类型
            if (!ossService.isImageFile(file)) {
                return Result.error(400, "只能上传图片文件（jpg、png、gif、bmp等）");
            }

            // 验证文件大小（限制为10MB）
            if (!ossService.isValidFileSize(file, 10)) {
                return Result.error(400, "文件大小不能超过10MB");
            }

            // 上传文件
            String fileUrl = ossService.uploadFile(file);

            // 构建返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("fileName", file.getOriginalFilename());
            data.put("fileSize", file.getSize());
            data.put("fileUrl", fileUrl);

            log.info("图片上传成功: {}", fileUrl);
            return Result.success(data);

        } catch (Exception e) {
            log.error("图片上传失败", e);
            return Result.error(500, "图片上传失败: " + e.getMessage());
        }
    }

    /**
     * 批量上传图片
     *
     * @param files 图片文件数组
     * @return 上传结果
     */
    @PostMapping("/upload/images")
    public Result<Map<String, Object>> uploadImages(@RequestParam("files") MultipartFile[] files) {
        try {
            if (files == null || files.length == 0) {
                return Result.error(400, "请选择要上传的文件");
            }

            // 限制批量上传数量
            if (files.length > 10) {
                return Result.error(400, "一次最多只能上传10张图片");
            }

            Map<String, Object> data = new HashMap<>();
            data.put("successUrls", new java.util.ArrayList<String>());
            data.put("failedFiles", new java.util.ArrayList<String>());
            
            int successCount = 0;
            int failedCount = 0;

            for (MultipartFile file : files) {
                try {
                    // 验证单个文件
                    if (file.isEmpty()) {
                        ((java.util.List<String>) data.get("failedFiles")).add(file.getOriginalFilename() + " - 文件为空");
                        failedCount++;
                        continue;
                    }

                    if (!ossService.isImageFile(file)) {
                        ((java.util.List<String>) data.get("failedFiles")).add(file.getOriginalFilename() + " - 不是图片文件");
                        failedCount++;
                        continue;
                    }

                    if (!ossService.isValidFileSize(file, 10)) {
                        ((java.util.List<String>) data.get("failedFiles")).add(file.getOriginalFilename() + " - 文件过大");
                        failedCount++;
                        continue;
                    }

                    // 上传文件
                    String fileUrl = ossService.uploadFile(file);
                    ((java.util.List<String>) data.get("successUrls")).add(fileUrl);
                    successCount++;

                } catch (Exception e) {
                    ((java.util.List<String>) data.get("failedFiles")).add(file.getOriginalFilename() + " - " + e.getMessage());
                    failedCount++;
                }
            }

            data.put("successCount", successCount);
            data.put("failedCount", failedCount);

            String message = String.format("批量上传完成，成功：%d个，失败：%d个", successCount, failedCount);
            log.info(message);

            return Result.success(data);

        } catch (Exception e) {
            log.error("批量上传失败", e);
            return Result.error(500, "批量上传失败: " + e.getMessage());
        }
    }

    /**
     * 删除图片
     *
     * @param fileUrl 图片文件URL
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public Result<Map<String, Object>> deleteImage(@RequestParam("fileUrl") String fileUrl) {
        try {
            if (fileUrl == null || fileUrl.trim().isEmpty()) {
                return Result.error(400, "文件URL不能为空");
            }

            boolean deleted = ossService.deleteFile(fileUrl);
            
            Map<String, Object> data = new HashMap<>();
            data.put("fileUrl", fileUrl);
            data.put("deleted", deleted);

            if (deleted) {
                log.info("图片删除成功: {}", fileUrl);
                return Result.success(data);
            } else {
                log.warn("图片删除失败，文件不存在: {}", fileUrl);
                return Result.error(404, "要删除的文件不存在");
            }

        } catch (Exception e) {
            log.error("图片删除失败", e);
            return Result.error(500, "图片删除失败: " + e.getMessage());
        }
    }

    /**
     * 批量删除图片
     *
     * @param fileUrls 图片文件URL列表
     * @return 删除结果
     */
    @DeleteMapping("/delete/batch")
    public Result<Map<String, Object>> deleteImages(@RequestBody List<String> fileUrls) {
        try {
            if (fileUrls == null || fileUrls.isEmpty()) {
                return Result.error(400, "文件URL列表不能为空");
            }

            // 限制批量删除数量
            if (fileUrls.size() > 50) {
                return Result.error(400, "一次最多只能删除50个文件");
            }

            Map<String, Object> result = ossService.deleteFiles(fileUrls);

            String message = String.format("批量删除完成，成功：%d个，失败：%d个", 
                    (Integer) result.get("successCount"), 
                    (Integer) result.get("failedCount"));
            
            log.info(message);
            return Result.success(result);

        } catch (Exception e) {
            log.error("批量删除失败", e);
            return Result.error(500, "批量删除失败: " + e.getMessage());
        }
    }

    /**
     * 获取图片列表
     *
     * @param prefix 目录前缀（可选）
     * @param maxKeys 最大返回数量（可选，默认100）
     * @return 图片列表
     */
    @GetMapping("/list")
    public Result<Map<String, Object>> listImages(
            @RequestParam(value = "prefix", required = false) String prefix,
            @RequestParam(value = "maxKeys", required = false, defaultValue = "100") Integer maxKeys) {
        try {
            if (maxKeys <= 0 || maxKeys > 1000) {
                maxKeys = 100; // 限制最大值
            }

            List<Map<String, Object>> fileList = ossService.listFiles(prefix, maxKeys);
            
            Map<String, Object> data = new HashMap<>();
            data.put("files", fileList);
            data.put("total", fileList.size());
            data.put("prefix", prefix);

            log.info("获取图片列表成功，共{}个文件", fileList.size());
            return Result.success(data);

        } catch (Exception e) {
            log.error("获取图片列表失败", e);
            return Result.error(500, "获取图片列表失败: " + e.getMessage());
        }
    }

    /**
     * 获取今日上传的图片
     *
     * @return 今日图片列表
     */
    @GetMapping("/list/today")
    public Result<Map<String, Object>> listTodayImages() {
        try {
            // 生成今日日期前缀
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd");
            String todayPrefix = "images/" + sdf.format(new Date()) + "/";

            List<Map<String, Object>> fileList = ossService.listFiles(todayPrefix, 200);
            
            Map<String, Object> data = new HashMap<>();
            data.put("files", fileList);
            data.put("total", fileList.size());
            data.put("date", sdf.format(new Date()));

            log.info("获取今日图片列表成功，共{}个文件", fileList.size());
            return Result.success(data);

        } catch (Exception e) {
            log.error("获取今日图片列表失败", e);
            return Result.error(500, "获取今日图片列表失败: " + e.getMessage());
        }
    }
} 