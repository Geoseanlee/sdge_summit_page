package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.entity.ExpoInfo;
import com.sdgs.service.ExpoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/expo")
public class ExpoController {
    
    @Autowired
    private ExpoService expoService;
    
    /**
     * 获取世博会页面信息
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
} 