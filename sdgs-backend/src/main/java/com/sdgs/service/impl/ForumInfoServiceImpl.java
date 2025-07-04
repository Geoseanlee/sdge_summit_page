package com.sdgs.service.impl;

import com.sdgs.entity.ForumInfo;
import com.sdgs.mapper.ForumInfoMapper;
import com.sdgs.service.ForumInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForumInfoServiceImpl implements ForumInfoService {

    @Autowired
    private ForumInfoMapper forumInfoMapper;

    @Override
    public ForumInfo getLatest() {
        return forumInfoMapper.selectLatest();
    }
} 