package com.sdgs.service.impl;

import com.sdgs.entity.JoinUsContent;
import com.sdgs.mapper.JoinUsContentMapper;
import com.sdgs.service.JoinUsContentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinUsContentServiceImpl implements JoinUsContentService {

    @Resource
    private JoinUsContentMapper mapper;

    @Override
    public List<JoinUsContent> listByCategory(String category) {
        if (category == null || category.isEmpty()) {
            return mapper.findAll(); // 不传参数时查询全部
        } else {
            return mapper.listByCategory(category); // 按分类查
        }
    }


    @Override
    public boolean add(JoinUsContent content) {
        return mapper.add(content) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return mapper.delete(id) > 0;
    }

    @Override
    public boolean update(JoinUsContent content) {
        return mapper.update(content) > 0;
    }
}
