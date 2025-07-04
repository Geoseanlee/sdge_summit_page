package com.sdgs.service;

import com.sdgs.entity.ExpoInfo;

public interface ExpoService {
    
    /**
     * 获取最新的世博会信息
     * @return 世博会信息，如果没有数据则返回null
     */
    ExpoInfo getLatestExpoInfo();
    
    /**
     * 根据ID获取世博会信息
     * @param id 世博会信息ID
     * @return 世博会信息，如果没有数据则返回null
     */
    ExpoInfo getExpoInfoById(Long id);
    
    /**
     * 创建新的世博会信息
     * @param expoInfo 世博会信息
     * @return 创建成功的世博会信息（包含生成的ID）
     */
    ExpoInfo createExpoInfo(ExpoInfo expoInfo);
    
    /**
     * 更新世博会信息
     * @param id 世博会信息ID
     * @param expoInfo 更新的世博会信息
     * @return 更新后的世博会信息，如果更新失败返回null
     */
    ExpoInfo updateExpoInfo(Long id, ExpoInfo expoInfo);
    
    /**
     * 删除世博会信息
     * @param id 世博会信息ID
     * @return 删除是否成功
     */
    boolean deleteExpoInfo(Long id);
} 