package com.sdgs.entity;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * 新闻资讯实体类
 */
@Data
public class News {
    
    private Long id;
    
    /** 新闻标题 */
    private String title;
    
    /** 副标题 */
    private String subtitle;
    
    /** 新闻简介 */
    private String summary;
    
    /** 新闻正文内容（富文本） */
    private String content;
    
    /** 封面图片URL */
    private String coverImageUrl;
    
    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishTime;
    
    /** 状态（0:草稿 1:已发布） */
    private Integer status;
    
    /** 阅读量 */
    private Integer viewCount;
    
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    
} 