package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.entity.AboutMoreSection;
import com.sdgs.entity.PageConfig; // 导入PageConfig
import com.sdgs.mapper.PageConfigMapper; // 导入PageConfigMapper
import com.sdgs.service.AboutMoreSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map; // 导入Map

@RestController
@RequestMapping("/api/about-more")
@RequiredArgsConstructor
public class AboutMoreSectionController {

    private final AboutMoreSectionService sectionService;
    private final PageConfigMapper pageConfigMapper; // 注入PageConfigMapper

    // === 新增的页面配置接口 ===

    // 公开接口：获取配置
    @GetMapping("/public/config")
    public Result<PageConfig> getPublicConfig() {
        return Result.success(pageConfigMapper.findByPageKey("about_more"));
    }

    // 管理接口：更新配置
    @PutMapping("/admin/config")
    public Result<Void> updateConfig(@RequestBody Map<String, String> configData) {
        // 在实际应用中，这里应该将Map转换为JSON字符串
        // 但由于框架的配合，直接传递Map通常也能被正确处理为JSON
        String jsonString = "{\"title\":\"" + configData.get("title") + "\", \"finalParagraph\":\"" + configData.get("finalParagraph") + "\"}";
        pageConfigMapper.updateByPageKey("about_more", jsonString);
        return Result.success();
    }


    // == 已有的动态区块接口（保持不变） ==
    @GetMapping("/public/sections")
    public Result<List<AboutMoreSection>> getPublicSections() {
        return Result.success(sectionService.getAllPublishedSections());
    }

    @GetMapping("/admin/sections")
    public Result<List<AboutMoreSection>> getAdminSections() {
        return Result.success(sectionService.getAllSectionsForAdmin());
    }

    @PostMapping("/admin/sections")
    public Result<AboutMoreSection> createSection(@RequestBody AboutMoreSection section) {
        return Result.success(sectionService.createSection(section));
    }

    @PutMapping("/admin/sections/{id}")
    public Result<AboutMoreSection> updateSection(@PathVariable Long id, @RequestBody AboutMoreSection section) {
        section.setId(id);
        return Result.success(sectionService.updateSection(section));
    }

    @DeleteMapping("/admin/sections/{id}")
    public Result<Void> deleteSection(@PathVariable Long id) {
        sectionService.deleteSection(id);
        return Result.success();
    }
}