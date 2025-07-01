package com.sdgs.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpoInfo {
    
    private Long id;
    
    private String title; // 主标题
    
    private String content; // 介绍内容（HTML）
    
    private LocalDateTime createTime; // 创建时间
    
    private LocalDateTime updateTime; // 更新时间
} 