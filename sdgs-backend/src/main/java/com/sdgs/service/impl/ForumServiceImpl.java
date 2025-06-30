package com.sdgs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdgs.entity.Forum;
import com.sdgs.mapper.ForumMapper;
import com.sdgs.service.ForumService;
import org.springframework.stereotype.Service;

@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum> implements ForumService {
} 