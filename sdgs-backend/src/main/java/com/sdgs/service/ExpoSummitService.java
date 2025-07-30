package com.sdgs.service;

import com.sdgs.entity.ExpoSummit;

import java.util.Map;

public interface ExpoSummitService {

    /**
     * 获取峰会完整数据（统一接口）
     * 包含议程和嘉宾信息
     * 
     * @return 峰会完整数据
     */
    Map<String, Object> getCompleteData();

    /**
     * 添加峰会数据项
     * 
     * @param summitData 峰会数据
     * @return 是否添加成功
     */
    boolean addSummitData(ExpoSummit summitData);

    /**
     * 更新峰会数据项
     * 
     * @param summitData 峰会数据
     * @return 是否更新成功
     */
    boolean updateSummitData(ExpoSummit summitData);

    /**
     * 删除峰会数据项
     * 
     * @param id 数据ID
     * @return 是否删除成功
     */
    boolean deleteSummitData(Long id);
}