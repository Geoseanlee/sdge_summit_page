package com.sdgs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("forum")
public class Forum {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private String speakers;
    private Integer participants;
    private Integer discussions;
    private String status; // UPCOMING, PAST
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String tag; // 会议标签（如品牌&青年领袖板块等）
} 