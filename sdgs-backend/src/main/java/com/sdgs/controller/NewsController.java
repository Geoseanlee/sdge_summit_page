package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.entity.News;
import com.sdgs.service.NewsService;
import com.sdgs.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/news")
public class NewsController {
    
    @Autowired
    private NewsService newsService;
    
    @Autowired
    private OssService ossService;
    
    /**
     * 获取所有已发布的新闻（用户端）
     */
    @GetMapping("/published")
    public Result<List<News>> getPublishedNews() {
        try {
            List<News> news = newsService.getPublishedNews();
            return Result.success(news);
        } catch (Exception e) {
            log.error("获取已发布新闻失败", e);
            return Result.error(500, "获取新闻失败");
        }
    }
    
    /**
     * 获取所有新闻（管理端）
     */
    @GetMapping("/admin/list")
    public Result<List<News>> getAllNews() {
        try {
            List<News> news = newsService.getAllNews();
            return Result.success(news);
        } catch (Exception e) {
            log.error("获取所有新闻失败", e);
            return Result.error(500, "获取新闻失败");
        }
    }
    
    /**
     * 根据ID获取新闻详情
     */
    @GetMapping("/{id}")
    public Result<News> getNewsById(@PathVariable Long id) {
        try {
            News news = newsService.getById(id);
            if (news == null) {
                return Result.error(404, "新闻不存在");
            }
            
            // 增加阅读量
            newsService.incrementViewCount(id);
            
            return Result.success(news);
        } catch (Exception e) {
            log.error("根据ID获取新闻失败, id: {}", id, e);
            return Result.error(500, "获取新闻详情失败");
        }
    }
    
    /**
     * 创建新闻
     */
    @PostMapping("/admin")
    public Result<News> createNews(@RequestBody News news) {
        try {
            boolean success = newsService.save(news);
            if (success) {
                return Result.success(news);
            } else {
                return Result.error(500, "创建新闻失败");
            }
        } catch (Exception e) {
            log.error("创建新闻失败", e);
            return Result.error(500, "创建新闻失败");
        }
    }
    
    /**
     * 更新新闻
     */
    @PutMapping("/admin/{id}")
    public Result<News> updateNews(@PathVariable Long id, @RequestBody News news) {
        try {
            news.setId(id);
            boolean success = newsService.updateById(news);
            if (success) {
                return Result.success(news);
            } else {
                return Result.error(500, "更新新闻失败");
            }
        } catch (Exception e) {
            log.error("更新新闻失败, id: {}", id, e);
            return Result.error(500, "更新新闻失败");
        }
    }
    
    /**
     * 删除新闻
     */
    @DeleteMapping("/admin/{id}")
    public Result<Boolean> deleteNews(@PathVariable Long id) {
        try {
            boolean success = newsService.removeById(id);
            if (success) {
                return Result.success(true);
            } else {
                return Result.error(500, "删除新闻失败");
            }
        } catch (Exception e) {
            log.error("删除新闻失败, id: {}", id, e);
            return Result.error(500, "删除新闻失败");
        }
    }
    
    /**
     * 根据分类获取新闻
     */
    @GetMapping("/category/{category}")
    public Result<List<News>> getNewsByCategory(@PathVariable String category) {
        try {
            List<News> news = newsService.getByCategory(category);
            return Result.success(news);
        } catch (Exception e) {
            log.error("根据分类获取新闻失败, category: {}", category, e);
            return Result.error(500, "获取新闻失败");
        }
    }
    
    /**
     * 获取下一个可用的ID
     */
    @GetMapping("/admin/nextId")
    public Result<Long> getNextAvailableId() {
        try {
            Long nextId = newsService.getNextAvailableId();
            return Result.success(nextId);
        } catch (Exception e) {
            log.error("获取下一个可用ID失败", e);
            return Result.error(500, "获取ID失败");
        }
    }
    
    /**
     * 使用指定ID创建新闻
     */
    @PostMapping("/admin/withId")
    public Result<News> createNewsWithId(@RequestBody News news) {
        try {
            boolean success = newsService.saveWithId(news);
            if (success) {
                return Result.success(news);
            } else {
                return Result.error(500, "创建新闻失败");
            }
        } catch (Exception e) {
            log.error("使用指定ID创建新闻失败", e);
            return Result.error(500, "创建新闻失败");
        }
    }
    
    /**
     * 上传图片
     */
    @PostMapping("/admin/upload")
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String url = ossService.uploadFile(file);
            return Result.success(url);
        } catch (Exception e) {
            log.error("上传图片失败", e);
            return Result.error(500, "上传图片失败");
        }
    }
    
} 