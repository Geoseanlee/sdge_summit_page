package com.sdgs.service;

import com.sdgs.entity.HomeInfo;

public interface HomeService {
    
    /**
     * 获取最新的首页信息
     * @return 首页信息，如果没有数据则返回null
     */
    HomeInfo getLatestHomeInfo();
    
    /**
     * 根据ID获取首页信息
     * @param id 首页信息ID
     * @return 首页信息
     */
    HomeInfo getHomeInfoById(Long id);
    
    /**
     * 创建新的首页信息
     * @param homeInfo 首页信息
     * @return 创建成功的首页信息
     */
    HomeInfo createHomeInfo(HomeInfo homeInfo);
    
    /**
     * 更新首页信息
     * @param id 首页信息ID
     * @param homeInfo 更新的首页信息
     * @return 更新后的首页信息
     */
    HomeInfo updateHomeInfo(Long id, HomeInfo homeInfo);
    
    /**
     * 删除首页信息
     * @param id 首页信息ID
     * @return 是否删除成功
     */
    boolean deleteHomeInfo(Long id);
}
