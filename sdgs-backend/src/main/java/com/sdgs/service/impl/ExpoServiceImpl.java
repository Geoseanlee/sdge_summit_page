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
} 