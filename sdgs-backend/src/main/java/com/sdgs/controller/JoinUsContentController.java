package com.sdgs.controller;

import com.sdgs.entity.JoinUsContent;
import com.sdgs.service.JoinUsContentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/api/joinus")
public class JoinUsContentController {

    @Resource
    private JoinUsContentService service;

    // 查询
    @GetMapping("/list")
    public List<JoinUsContent> listByCategory(@RequestParam(required = false) String category) {
        return service.listByCategory(category);
    }

    // 新增
    @PostMapping("/add")
    public String add(@RequestBody JoinUsContent content) {
        boolean success = service.add(content);
        return success ? "新增成功" : "新增失败";
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody JoinUsContent content) {
        System.out.println("🧐 update 接收到 id=" + id + ", content=" + content);
        content.setId(id);
        boolean success = service.update(content);
        return success ? "更新成功" : "更新失败";
}

    // 删除
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boolean success = service.delete(id);
        return success ? "删除成功" : "删除失败";
    }
}
