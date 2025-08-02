package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/test")
@CrossOrigin
public class TestController {

    @Autowired
    private OssService ossService;

    /**
     * 测试OSS连接
     */
    @GetMapping("/oss")
    public Result<Map<String, Object>> testOssConnection() {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("message", "OSS服务正常");
            data.put("timestamp", System.currentTimeMillis());
            
            log.info("OSS连接测试成功");
            return Result.success(data);
            
        } catch (Exception e) {
            log.error("OSS连接测试失败", e);
            Map<String, Object> data = new HashMap<>();
            data.put("error", e.getMessage());
            data.put("timestamp", System.currentTimeMillis());
            return Result.error(500, "OSS连接测试失败: " + e.getMessage());
        }
    }

    /**
     * 测试文件上传端点
     */
    @GetMapping("/upload-endpoint")
    public Result<Map<String, Object>> testUploadEndpoint() {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("endpoint", "/api/file/upload/image");
            data.put("method", "POST");
            data.put("contentType", "multipart/form-data");
            data.put("maxFileSize", "50MB");
            data.put("supportedFormats", "jpg, png, gif, bmp");
            
            log.info("文件上传端点测试成功");
            return Result.success(data);
            
        } catch (Exception e) {
            log.error("文件上传端点测试失败", e);
            return Result.error(500, "文件上传端点测试失败: " + e.getMessage());
        }
    }
}
