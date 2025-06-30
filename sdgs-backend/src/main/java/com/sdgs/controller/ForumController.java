package com.sdgs.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdgs.common.Result;
import com.sdgs.entity.Forum;
import com.sdgs.service.ForumService;
import com.sdgs.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/forums")
public class ForumController {
    
    @Autowired
    private ForumService forumService;
    
    @Autowired
    private OssService ossService;

    @GetMapping
    public Result<List<Forum>> list(@RequestParam(required = false) String status) {
        LambdaQueryWrapper<Forum> queryWrapper = new LambdaQueryWrapper<>();
        if (status != null) {
            queryWrapper.eq(Forum::getStatus, status);
        }
        queryWrapper.orderByDesc(Forum::getCreateTime);
        return Result.success(forumService.list(queryWrapper));
    }

    @GetMapping("/{id}")
    public Result<Forum> getById(@PathVariable Long id) {
        return Result.success(forumService.getById(id));
    }

    @PostMapping
    public Result<Forum> save(@RequestBody Forum forum) {
        forumService.save(forum);
        return Result.success(forum);
    }

    @PutMapping("/{id}")
    public Result<Forum> update(@PathVariable Long id, @RequestBody Forum forum) {
        forum.setId(id);
        forumService.updateById(forum);
        return Result.success(forum);
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(forumService.removeById(id));
    }

    @PostMapping("/upload")
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String url = ossService.uploadFile(file);
        return Result.success(url);
    }
} 