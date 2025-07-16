package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.service.ExpoBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}