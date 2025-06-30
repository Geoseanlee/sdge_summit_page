package com.sdgs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("forum")
public class Forum {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String description;
    @TableField("image_url")
    private String imageUrl;
    private String tag; // 会议标签（如品牌&青年领袖板块等）
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 