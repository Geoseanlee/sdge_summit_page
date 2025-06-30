package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.entity.ExpoInfo;
import com.sdgs.service.ExpoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/expo")
public class ExpoController {
    
    @Autowired
    private ExpoService expoService;
    
    /**
     * 获取世博会页面信息（最新的）
     * @return 世博会信息，如果没有数据则返回null
     */
    @GetMapping("/info")
    public Result<ExpoInfo> getExpoInfo() {
        try {
            ExpoInfo expoInfo = expoService.getLatestExpoInfo();
            // 即使expoInfo为null，也返回成功状态，让前端处理null值显示
            return Result.success(expoInfo);
        } catch (Exception e) {
            log.error("获取世博会信息失败", e);
            return Result.error(500, "获取世博会信息失败");
        }
    }
    
    /**
     * 根据ID获取世博会信息
     * @param id 世博会信息ID
     * @return 世博会信息
     */
    @GetMapping("/detail/{id}")
    public Result<ExpoInfo> getExpoInfoById(@PathVariable Long id) {
        try {
            ExpoInfo expoInfo = expoService.getExpoInfoById(id);
            if (expoInfo == null) {
                return Result.error(404, "世博会信息不存在");
            }
            return Result.success(expoInfo);
        } catch (Exception e) {
            log.error("根据ID获取世博会信息失败, id: {}", id, e);
            return Result.error(500, "获取世博会信息失败");
        }
    }
    
    /**
     * 创建新的世博会信息
     * @param expoInfo 世博会信息
     * @return 创建成功的世博会信息
     */
    @PostMapping("/manage")
    public Result<ExpoInfo> createExpoInfo(@RequestBody ExpoInfo expoInfo) {
        try {
            ExpoInfo createdExpoInfo = expoService.createExpoInfo(expoInfo);
            if (createdExpoInfo == null) {
                return Result.error(400, "创建世博会信息失败，请检查输入数据");
            }
            return Result.success(createdExpoInfo);
        } catch (Exception e) {
            log.error("创建世博会信息失败", e);
            return Result.error(500, "创建世博会信息失败");
        }
    }
    
    /**
     * 更新世博会信息
     * @param id 世博会信息ID
     * @param expoInfo 更新的世博会信息
     * @return 更新后的世博会信息
     */
    @PutMapping("/manage/{id}")
    public Result<ExpoInfo> updateExpoInfo(@PathVariable Long id, @RequestBody ExpoInfo expoInfo) {
        try {
            ExpoInfo updatedExpoInfo = expoService.updateExpoInfo(id, expoInfo);
            if (updatedExpoInfo == null) {
                return Result.error(400, "更新世博会信息失败，请检查ID和输入数据");
            }
            return Result.success(updatedExpoInfo);
        } catch (Exception e) {
            log.error("更新世博会信息失败, id: {}", id, e);
            return Result.error(500, "更新世博会信息失败");
        }
    }
    
    /**
     * 删除世博会信息
     * @param id 世博会信息ID
     * @return 删除结果
     */
    @DeleteMapping("/manage/{id}")
    public Result<String> deleteExpoInfo(@PathVariable Long id) {
        try {
            boolean deleted = expoService.deleteExpoInfo(id);
            if (deleted) {
                return Result.success("删除成功");
            } else {
                return Result.error(400, "删除失败，世博会信息不存在");
            }
        } catch (Exception e) {
            log.error("删除世博会信息失败, id: {}", id, e);
            return Result.error(500, "删除世博会信息失败");
        }
    }
} 