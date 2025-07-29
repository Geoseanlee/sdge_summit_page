package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.service.ExpoBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/expo/background")
public class ExpoBackgroundController {

    @Autowired
    private ExpoBackgroundService expoBackgroundService;

    /**
     * 获取世博会背景页面所有数据
     */
    @GetMapping("/data")
    public Result<Map<String, Object>> getExpoBackgroundData() {
        try {
            Map<String, Object> data = expoBackgroundService.getExpoBackgroundData();
            return Result.success(data);
        } catch (Exception e) {
            return Result.error(500, "获取失败: " + e.getMessage());
        }
    }

    /**
     * 保存世博会背景页面数据
     */
    @PostMapping("/data")
    public Result<?> saveExpoBackgroundData(@RequestBody Map<String, Object> data) {
        try {
            expoBackgroundService.saveExpoBackgroundData(data);
            return Result.success();
        } catch (Exception e) {
            return Result.error(500, "保存失败: " + e.getMessage());
        }
    }

    /**
     * 更新世博会背景页面数据
     */
    @PutMapping("/data")
    public Result<?> updateExpoBackgroundData(@RequestBody Map<String, Object> data) {
        try {
            expoBackgroundService.saveExpoBackgroundData(data);
            return Result.success();
        } catch (Exception e) {
            return Result.error(500, "更新失败: " + e.getMessage());
        }
    }
}