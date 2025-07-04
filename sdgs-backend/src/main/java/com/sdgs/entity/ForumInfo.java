package com.sdgs.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 论坛介绍信息实体
 */
@Data
public class ForumInfo {
    private Long id;
    /** 主标题 */
    private String title;
    /** HTML 内容（或纯文本） */
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 