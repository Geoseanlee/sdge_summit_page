package com.sdgs.service;

import com.sdgs.entity.ExpoBackground;
import java.util.Map;

public interface ExpoBackgroundService {
    /**
     * 获取世博会背景页面的所有数据
     */
    Map<String, Object> getExpoBackgroundData();

    /**
     * 根据类型获取单个背景信息
     */
    ExpoBackground getByType(String sectionType);

    /**
     * 保存世博会背景页面数据
     */
    void saveExpoBackgroundData(Map<String, Object> data);
}