package com.sdgs.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 关于我们 - 单页实体
 */
@Data
public class AboutOverviewPage {

    private Long id;

    /* 语言 / Hero 区 */
    private String locale;
    private String headerTitle;
    private String headerTags;
    private String heroBtnText;
    private String heroBtnLink;
    private String heroImg;

    /* 引言段 & 模块 JSON（直接存字符串即可，前端/后台自行 JSON.parse） */
    private String introHtml;
    private String advantagesTitle;  // 新增：三大优势标题
    private String advantagesJson;
    private String statsTitle;       // 新增：统计卡标题
    private String statsJson;
    private String mediaTitle;       // 新增：媒体资源标题
    private String mediaJson;
    private String specialTitle;     // 新增：特别合作伙伴标题
    private String specialJson;
    private String partnerTitle;     // 新增：合作伙伴标题
    private String partnerJson;

    private Integer status;          // 1 = 发布, 0 = 草稿
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}