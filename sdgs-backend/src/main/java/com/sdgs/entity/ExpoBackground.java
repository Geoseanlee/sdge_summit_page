package com.sdgs.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpoBackground {
    private Long id;
    private String sectionType;
    private String title;
    private String content;
    private String imageUrl;
    private Integer sortOrder;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}