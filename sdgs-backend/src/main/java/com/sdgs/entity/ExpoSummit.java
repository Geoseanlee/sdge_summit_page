package com.sdgs.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 世博会峰会数据实体
 * 
 * @author system
 * @since 2025-07-30
 */
@Data
public class ExpoSummit {

    private Long id;

    /**
     * 数据类型：schedule-议程，guest-嘉宾
     */
    private String dataType;

    /**
     * 日期，如8月24日（仅议程类型使用）
     */
    private String date;

    /**
     * 时间，如17:00前（仅议程类型使用）
     */
    private String time;

    /**
     * 事件内容（仅议程类型使用）
     */
    private String event;

    /**
     * 分类标题（仅嘉宾类型使用）
     */
    private String categoryTitle;

    /**
     * 嘉宾名称（仅嘉宾类型使用）
     */
    private String guestName;

    /**
     * 嘉宾头像OSS URL（仅嘉宾类型使用）
     */
    private String guestImage;

    /**
     * 排序顺序
     */
    private Integer sortOrder;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
