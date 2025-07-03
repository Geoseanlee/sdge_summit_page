package com.sdgs.service.impl;

import com.sdgs.entity.Forum;
import com.sdgs.mapper.ForumMapper;
import com.sdgs.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {
    
    @Autowired
    private ForumMapper forumMapper;
    
    @Override
    public List<Forum> list() {
        return forumMapper.selectAll();
    }
    
    @Override
    public Forum getById(Long id) {
        return forumMapper.selectById(id);
    }
    
    @Override
    public boolean save(Forum forum) {
        return forumMapper.insert(forum) > 0;
    }
    
    @Override
    public boolean updateById(Forum forum) {
        return forumMapper.updateById(forum) > 0;
    }
    
    @Override
    public boolean removeById(Long id) {
        return forumMapper.deleteById(id) > 0;
    }
} 