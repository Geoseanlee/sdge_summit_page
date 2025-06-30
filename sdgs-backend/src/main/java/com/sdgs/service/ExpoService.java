package com.sdgs.service;

import com.sdgs.entity.ExpoInfo;

public interface ExpoService {
    
    /**
     * 获取最新的世博会信息
     * @return 世博会信息，如果没有数据则返回null
     */
    ExpoInfo getLatestExpoInfo();
} 