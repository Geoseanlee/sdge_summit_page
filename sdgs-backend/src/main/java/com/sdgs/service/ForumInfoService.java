package com.sdgs.service;

import com.sdgs.entity.ForumInfo;

public interface ForumInfoService {
    /**
     * 获取最新的论坛介绍信息
     */
    ForumInfo getLatest();

    void update(ForumInfo info);
} 