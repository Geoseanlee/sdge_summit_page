package com.sdgs.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AboutMoreSection {
    private Long id;
    private String title;
    private String content;
    private Integer sortOrder;
    private Boolean isPublished;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}