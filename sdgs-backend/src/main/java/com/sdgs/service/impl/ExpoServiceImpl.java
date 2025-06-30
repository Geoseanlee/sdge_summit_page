package com.sdgs.service.impl;

import com.sdgs.entity.ExpoInfo;
import com.sdgs.mapper.ExpoInfoMapper;
import com.sdgs.service.ExpoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExpoServiceImpl implements ExpoService {
    
    @Autowired
    private ExpoInfoMapper expoInfoMapper;
    
    @Override
    public ExpoInfo getLatestExpoInfo() {
        try {
            return expoInfoMapper.selectLatest();
        } catch (Exception e) {
            log.error("获取世博会信息失败", e);
            return null;
        }
    }
    
    @Override
    public ExpoInfo getExpoInfoById(Long id) {
        if (id == null) {
            log.warn("世博会信息ID不能为空");
            return null;
        }
        
        try {
            return expoInfoMapper.selectById(id);
        } catch (Exception e) {
            log.error("根据ID获取世博会信息失败, id: {}", id, e);
            return null;
        }
    }
    
    @Override
    public ExpoInfo createExpoInfo(ExpoInfo expoInfo) {
        if (expoInfo == null) {
            log.warn("世博会信息不能为空");
            return null;
        }
        
        // 基本字段验证
        if (expoInfo.getTitle() == null || expoInfo.getTitle().trim().isEmpty()) {
            log.warn("世博会标题不能为空");
            return null;
        }
        
        try {
            int result = expoInfoMapper.insert(expoInfo);
            if (result > 0) {
                log.info("创建世博会信息成功, id: {}", expoInfo.getId());
                return expoInfo;
            } else {
                log.warn("创建世博会信息失败");
                return null;
            }
        } catch (Exception e) {
            log.error("创建世博会信息失败", e);
            return null;
        }
    }
    
    @Override
    public ExpoInfo updateExpoInfo(Long id, ExpoInfo expoInfo) {
        if (id == null) {
            log.warn("世博会信息ID不能为空");
            return null;
        }
        
        if (expoInfo == null) {
            log.warn("世博会信息不能为空");
            return null;
        }
        
        // 验证记录是否存在
        ExpoInfo existingInfo = expoInfoMapper.selectById(id);
        if (existingInfo == null) {
            log.warn("要更新的世博会信息不存在, id: {}", id);
            return null;
        }
        
        // 设置ID
        expoInfo.setId(id);
        
        try {
            int result = expoInfoMapper.update(expoInfo);
            if (result > 0) {
                log.info("更新世博会信息成功, id: {}", id);
                return expoInfoMapper.selectById(id);
            } else {
                log.warn("更新世博会信息失败, id: {}", id);
                return null;
            }
        } catch (Exception e) {
            log.error("更新世博会信息失败, id: {}", id, e);
            return null;
        }
    }
    
    @Override
    public boolean deleteExpoInfo(Long id) {
        if (id == null) {
            log.warn("世博会信息ID不能为空");
            return false;
        }
        
        // 验证记录是否存在
        ExpoInfo existingInfo = expoInfoMapper.selectById(id);
        if (existingInfo == null) {
            log.warn("要删除的世博会信息不存在, id: {}", id);
            return false;
        }
        
        try {
            int result = expoInfoMapper.deleteById(id);
            if (result > 0) {
                log.info("删除世博会信息成功, id: {}", id);
                return true;
            } else {
                log.warn("删除世博会信息失败, id: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("删除世博会信息失败, id: {}", id, e);
            return false;
        }
    }
} 