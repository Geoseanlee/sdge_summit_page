package com.sdgs.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HomeInfo {
    
    private Long id;
    
    // 英雄区域
    private String heroTitle1; // 走进世界
    private String heroSubtitle1; // 与世博全球青年一起
    private String heroTitle2; // 走进世博
    private String heroSubtitle2; // 共创可持续发展未来
    private String heroImageUrl; // 英雄区域图片URL
    
    // SDGs介绍区域
    private String sdgsDescription1; // SDGs第一段描述
    private String sdgsDescription2; // SDGs第二段描述
    
    // 世博会介绍区域
    private String expoTitle; // 2025大阪世博会情况及介绍
    private String expoSubtitle; // "闪耀生命的未来社会的设计"
    private String expoImageUrl; // 世博会背景图片URL
    
    // 世博全球青年联盟介绍区域
    private String allianceTitle; // 世博全球青年联盟
    private String allianceDescription1; // 联盟第一段描述
    private String allianceDescription2; // 联盟第二段描述
    
    // 相关数据区域
    private String dataTitle; // 相关行动数据
    private String dataImageUrl; // 数据图表图片URL
    
    // 加入我们区域
    private String joinTitle; // 加入我们
    private String joinItem1; // 第一个加入项目
    private String joinItem2; // 第二个加入项目
    private String joinItem3; // 第三个加入项目
    private String joinImageUrl; // 加入我们图片URL
    
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
