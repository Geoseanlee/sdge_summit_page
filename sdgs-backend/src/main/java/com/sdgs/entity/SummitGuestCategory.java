package com.sdgs.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SummitGuestCategory {

    private Long id;

    private String title; // 分类标题

    private Integer sortOrder; // 排序顺序

    private LocalDateTime createTime; // 创建时间

    private LocalDateTime updateTime; // 更新时间

    private List<SummitGuest> guests; // 该分类下的嘉宾列表
}