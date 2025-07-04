package com.sdgs.service;

import com.sdgs.entity.Forum;
import java.util.List;
 
public interface ForumService {
    List<Forum> list();
    Forum getById(Long id);
    boolean save(Forum forum);
    boolean updateById(Forum forum);
    boolean removeById(Long id);
} 