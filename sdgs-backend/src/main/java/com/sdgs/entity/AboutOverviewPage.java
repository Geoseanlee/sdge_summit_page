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
    private String advantagesJson;
    private String statsJson;
    private String mediaJson;
    private String specialJson;
    private String partnerJson;

    private Integer status;          // 1 = 发布, 0 = 草稿
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}