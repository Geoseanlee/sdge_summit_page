package com.sdgs.service.impl;

import com.sdgs.entity.AboutOverviewPage;
import com.sdgs.mapper.AboutOverviewPageMapper;
import com.sdgs.service.AboutOverviewPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AboutOverviewPageServiceImpl implements AboutOverviewPageService {

    private final AboutOverviewPageMapper mapper;

    @Override
    public AboutOverviewPage getPublished(String locale) {
        return mapper.selectPublishedByLocale(locale);
    }

    @Override
    public AboutOverviewPage getById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    @Transactional
    public Long create(AboutOverviewPage page) {
        // 初次创建默认草稿 0
        if (page.getStatus() == null) {
            page.setStatus(0);
        }
        mapper.insert(page);
        return page.getId();
    }

    @Override
    @Transactional
    public boolean update(AboutOverviewPage page) {
        return mapper.updateById(page) > 0;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return mapper.deleteById(id) > 0;
    }
}