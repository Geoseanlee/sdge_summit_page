package com.sdgs.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SummitGuest {

    private Long id;

    private Long categoryId; // 分类ID

    private String guestName; // 嘉宾名称或描述

    private String guestImage; // 嘉宾头像OSS URL

    private Integer sortOrder; // 排序顺序

    private LocalDateTime createTime; // 创建时间

    private LocalDateTime updateTime; // 更新时间
}