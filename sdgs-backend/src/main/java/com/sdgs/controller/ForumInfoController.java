package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.entity.ForumInfo;
import com.sdgs.service.ForumInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/forum-info")
public class ForumInfoController {

    @Autowired
    private ForumInfoService forumInfoService;

    /**
     * 获取最新论坛介绍信息
     */
    @GetMapping("/latest")
    public Result<ForumInfo> latest() {
        return Result.success(forumInfoService.getLatest());
    }
} 