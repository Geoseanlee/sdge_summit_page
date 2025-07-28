package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.entity.AboutOverviewPage;
import com.sdgs.service.AboutOverviewPageService;
//import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AboutOverviewPageController {

    private final AboutOverviewPageService service;

    /* ---------- 前台公开 ---------- */

    /**
     * 访问 /api/public/about-overview?locale=zh-CN
     */
    @GetMapping("/public/about-overview")
    public Result<AboutOverviewPage> getPublished(
            @RequestParam(defaultValue = "zh-CN") String locale) {

        AboutOverviewPage page = service.getPublished(locale);
        return Result.success(page);
    }

    /* ---------- 后台管理 ---------- */

    @GetMapping("/admin/about-overview/{id}")
    public Result<AboutOverviewPage> getById(@PathVariable Long id) {
        return Result.success(service.getById(id));
    }

    @PostMapping("/admin/about-overview")
    public Result<Long> create(@RequestBody  AboutOverviewPage page) {
        Long id = service.create(page);           // 草稿
        return Result.success(id);
    }

    @PutMapping("/admin/about-overview/{id}")
    public Result<Boolean> update(@PathVariable Long id,
                                  @RequestBody  AboutOverviewPage page) {

        page.setId(id);
        return Result.success(service.update(page));
    }

    @DeleteMapping("/admin/about-overview/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(service.delete(id));
    }
}