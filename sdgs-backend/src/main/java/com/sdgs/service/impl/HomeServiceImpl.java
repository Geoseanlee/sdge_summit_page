package com.sdgs.service.impl;

import com.sdgs.entity.HomeInfo;
import com.sdgs.mapper.HomeInfoMapper;
import com.sdgs.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HomeServiceImpl implements HomeService {
    
    @Autowired
    private HomeInfoMapper homeInfoMapper;
    
    @Override
    public HomeInfo getLatestHomeInfo() {
        try {
            return homeInfoMapper.getLatestHomeInfo();
        } catch (Exception e) {
            log.error("获取最新首页信息失败", e);
            return null;
        }
    }
    
    @Override
    public HomeInfo getHomeInfoById(Long id) {
        if (id == null || id <= 0) {
            log.warn("首页信息ID无效: {}", id);
            return null;
        }
        
        try {
            return homeInfoMapper.getHomeInfoById(id);
        } catch (Exception e) {
            log.error("根据ID获取首页信息失败, id: {}", id, e);
            return null;
        }
    }
    
    @Override
    public HomeInfo createHomeInfo(HomeInfo homeInfo) {
        if (homeInfo == null) {
            log.warn("首页信息对象为空，无法创建");
            return null;
        }
        
        try {
            int rows = homeInfoMapper.insertHomeInfo(homeInfo);
            if (rows > 0) {
                log.info("创建首页信息成功，ID: {}", homeInfo.getId());
                return homeInfo;
            } else {
                log.warn("创建首页信息失败，影响行数: {}", rows);
                return null;
            }
        } catch (Exception e) {
            log.error("创建首页信息异常", e);
            return null;
        }
    }
    
    @Override
    public HomeInfo updateHomeInfo(Long id, HomeInfo homeInfo) {
        if (id == null || id <= 0 || homeInfo == null) {
            log.warn("更新首页信息参数无效，id: {}, homeInfo: {}", id, homeInfo);
            return null;
        }
        
        // 检查记录是否存在
        HomeInfo existingInfo = getHomeInfoById(id);
        if (existingInfo == null) {
            log.warn("要更新的首页信息不存在, id: {}", id);
            return null;
        }
        
        try {
            homeInfo.setId(id);
            int rows = homeInfoMapper.updateHomeInfo(homeInfo);
            if (rows > 0) {
                log.info("更新首页信息成功，ID: {}", id);
                return getHomeInfoById(id); // 返回更新后的完整数据
            } else {
                log.warn("更新首页信息失败，影响行数: {}", rows);
                return null;
            }
        } catch (Exception e) {
            log.error("更新首页信息异常, id: {}", id, e);
            return null;
        }
    }
    
    @Override
    public boolean deleteHomeInfo(Long id) {
        if (id == null || id <= 0) {
            log.warn("删除首页信息ID无效: {}", id);
            return false;
        }
        
        try {
            int rows = homeInfoMapper.deleteHomeInfo(id);
            if (rows > 0) {
                log.info("删除首页信息成功，ID: {}", id);
                return true;
            } else {
                log.warn("删除首页信息失败，记录可能不存在, id: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("删除首页信息异常, id: {}", id, e);
            return false;
        }
    }
}
