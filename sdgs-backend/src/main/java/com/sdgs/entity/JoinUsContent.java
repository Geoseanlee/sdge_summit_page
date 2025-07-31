package com.sdgs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应 join_us_content 表
 */
public class JoinUsContent implements Serializable {

    /** 主键 */
    private Long id;

    /** 类别：project/member/food_award */
    private String category;

    /** 标题 */
    private String title;

    /** 描述 */
    private String description;

    /** 图片地址 */
    private String imageUrl;

    /** 跳转链接 */
    private String linkUrl;

    /** 排序值，越小越靠前 */
    private Integer sortOrder;

    /** 创建时间 */
    private Date createTime;

    // Getter & Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
