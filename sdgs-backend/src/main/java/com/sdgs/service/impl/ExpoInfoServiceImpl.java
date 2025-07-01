package com.sdgs.service.impl;

import com.sdgs.entity.ExpoInfo;
import com.sdgs.mapper.ExpoInfoMapper;
import com.sdgs.service.ExpoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpoInfoServiceImpl implements ExpoInfoService {
    @Autowired
    private ExpoInfoMapper expoInfoMapper;

    @Override
    public ExpoInfo getLatest() {
        return expoInfoMapper.selectLatest();
    }
} 