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
    
    // 大卡片相关字段
    private String cardTitle; // 大卡片主标题，如"2025大阪世博会"
    
    private String cardSubtitle; // 大卡片副标题，如"闪耀生命的未来社会的设计"
    
    private String cardDescription; // 大卡片描述内容
    
    private String cardImageUrl; // 大卡片左侧图片OSS URL
    
    // 三个section相关字段
    private String sectionTitle1; // 第一个section标题
    
    private String sectionImageUrl1; // 第一个section图片OSS URL
    
    private String sectionTitle2; // 第二个section标题
    
    private String sectionImageUrl2; // 第二个section图片OSS URL
    
    private String sectionTitle3; // 第三个section标题
    
    private String sectionImageUrl3; // 第三个section图片OSS URL
    
    private LocalDateTime createTime; // 创建时间
    
    private LocalDateTime updateTime; // 更新时间
} 