package com.sdgs.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Forum {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String tag; // 会议标签（如品牌&青年领袖板块等）
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 