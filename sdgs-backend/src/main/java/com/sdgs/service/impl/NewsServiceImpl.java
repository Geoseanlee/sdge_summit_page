package com.sdgs.service.impl;

import com.sdgs.entity.News;
import com.sdgs.mapper.NewsMapper;
import com.sdgs.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NewsServiceImpl implements NewsService {
    
    @Autowired
    private NewsMapper newsMapper;
    
    @Override
    public List<News> getPublishedNews() {
        try {
            return newsMapper.selectPublished();
        } catch (Exception e) {
            log.error("查询已发布新闻失败", e);
            return null;
        }
    }
    
    @Override
    public List<News> getAllNews() {
        try {
            return newsMapper.selectAll();
        } catch (Exception e) {
            log.error("查询所有新闻失败", e);
            return null;
        }
    }
    
    @Override
    public News getById(Long id) {
        if (id == null) {
            log.warn("新闻ID不能为空");
            return null;
        }
        
        try {
            return newsMapper.selectById(id);
        } catch (Exception e) {
            log.error("根据ID查询新闻失败, id: {}", id, e);
            return null;
        }
    }
    
    @Override
    public boolean save(News news) {
        if (news == null) {
            log.warn("新闻对象不能为空");
            return false;
        }
        
        // 基本字段验证
        if (news.getTitle() == null || news.getTitle().trim().isEmpty()) {
            log.warn("新闻标题不能为空");
            return false;
        }
        
        try {
            // 设置默认值
            if (news.getStatus() == null) {
                news.setStatus(1); // 默认已发布
            }
            if (news.getViewCount() == null) {
                news.setViewCount(0);
            }
            
            return newsMapper.insert(news) > 0;
        } catch (Exception e) {
            log.error("保存新闻失败", e);
            return false;
        }
    }
    
    @Override
    public boolean updateById(News news) {
        if (news == null || news.getId() == null) {
            log.warn("新闻对象或ID不能为空");
            return false;
        }
        
        try {
            return newsMapper.updateById(news) > 0;
        } catch (Exception e) {
            log.error("更新新闻失败, id: {}", news.getId(), e);
            return false;
        }
    }
    
    @Override
    public boolean removeById(Long id) {
        if (id == null) {
            log.warn("新闻ID不能为空");
            return false;
        }
        
        try {
            return newsMapper.deleteById(id) > 0;
        } catch (Exception e) {
            log.error("删除新闻失败, id: {}", id, e);
            return false;
        }
    }
    
    @Override
    public boolean incrementViewCount(Long id) {
        if (id == null) {
            log.warn("新闻ID不能为空");
            return false;
        }
        
        try {
            return newsMapper.incrementViewCount(id) > 0;
        } catch (Exception e) {
            log.error("增加阅读量失败, id: {}", id, e);
            return false;
        }
    }
    
    @Override
    public List<News> getByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            log.warn("分类不能为空");
            return null;
        }
        
        try {
            return newsMapper.selectByCategory(category);
        } catch (Exception e) {
            log.error("根据分类查询新闻失败, category: {}", category, e);
            return null;
        }
    }
    
    @Override
    public Long getNextAvailableId() {
        try {
            Long nextId = newsMapper.getNextAvailableId();
            return nextId != null ? nextId : 1L;
        } catch (Exception e) {
            log.error("获取下一个可用ID失败", e);
            return 1L;
        }
    }
    
    @Override
    public boolean saveWithId(News news) {
        if (news == null) {
            log.warn("新闻对象不能为空");
            return false;
        }
        
        // 基本字段验证
        if (news.getTitle() == null || news.getTitle().trim().isEmpty()) {
            log.warn("新闻标题不能为空");
            return false;
        }
        
        try {
            // 设置默认值
            if (news.getStatus() == null) {
                news.setStatus(1); // 默认已发布
            }
            if (news.getViewCount() == null) {
                news.setViewCount(0);
            }
            
            // 如果没有指定ID，则获取下一个可用ID
            if (news.getId() == null) {
                news.setId(getNextAvailableId());
            }
            
            return newsMapper.insertWithId(news) > 0;
        } catch (Exception e) {
            log.error("使用指定ID保存新闻失败", e);
            return false;
        }
    }
    
} 