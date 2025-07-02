package com.sdgs.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SummitSchedule {

    private Long id;

    private String date; // 日期，如"8月24日"

    private String time; // 时间，如"17:00前"

    private String event; // 事件内容

    private String dateColor; // 日期列背景色

    private String timeColor; // 时间列背景色

    private String eventColor; // 事件列背景色

    private Integer sortOrder; // 排序顺序

    private LocalDateTime createTime; // 创建时间

    private LocalDateTime updateTime; // 更新时间
}