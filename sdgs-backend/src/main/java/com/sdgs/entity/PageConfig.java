package com.sdgs.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PageConfig {
    private Integer id;
    private String pageKey;
    // 我们将JSON数据作为字符串处理，由框架自动转换
    private String configData;
    private LocalDateTime updateTime;
}