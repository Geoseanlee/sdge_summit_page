package com.sdgs.service;

import com.sdgs.entity.News;

import java.util.List;

public interface NewsService {
    
    /**
     * 查询所有已发布的新闻
     */
    List<News> getPublishedNews();
    
    /**
     * 管理端查询所有新闻（包括草稿）
     */
    List<News> getAllNews();
    
    /**
     * 根据ID查询新闻
     */
    News getById(Long id);
    
    /**
     * 保存新闻
     */
    boolean save(News news);
    
    /**
     * 更新新闻
     */
    boolean updateById(News news);
    
    /**
     * 删除新闻
     */
    boolean removeById(Long id);
    
    /**
     * 增加阅读量
     */
    boolean incrementViewCount(Long id);

} 