package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.entity.ExpoInfo;
import com.sdgs.service.ExpoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expo-info")
@CrossOrigin
public class ExpoInfoController {
    @Autowired
    private ExpoInfoService expoInfoService;

    @GetMapping("/latest")
    public Result<ExpoInfo> getLatest() {
        return Result.success(expoInfoService.getLatest());
    }
} 