package com.sdgs.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpoInfo {
    
    private Long id;
    
    private String title; // 主标题，如"世博会"
    
    private String subtitle; // 副标题，如"全球最高级的展览平台"
    
    private String description; // 描述文字（包含两段内容）
    
    private String imageUrl; // 图片的OSS URL
    
    private LocalDateTime createTime; // 创建时间
    
    private LocalDateTime updateTime; // 更新时间
} 