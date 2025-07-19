package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.entity.HomeInfo;
import com.sdgs.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/home")
public class HomeController {
    
    @Autowired
    private HomeService homeService;
    
    /**
     * 获取首页信息（最新的）
     * @return 首页信息，如果没有数据则返回null
     */
    @GetMapping("/info")
    public Result<HomeInfo> getHomeInfo() {
        try {
            HomeInfo homeInfo = homeService.getLatestHomeInfo();
            // 即使homeInfo为null，也返回成功状态，让前端处理null值显示
            return Result.success(homeInfo);
        } catch (Exception e) {
            log.error("获取首页信息失败", e);
            return Result.error(500, "获取首页信息失败");
        }
    }
    
    /**
     * 根据ID获取首页信息
     * @param id 首页信息ID
     * @return 首页信息
     */
    @GetMapping("/detail/{id}")
    public Result<HomeInfo> getHomeInfoById(@PathVariable Long id) {
        try {
            HomeInfo homeInfo = homeService.getHomeInfoById(id);
            if (homeInfo == null) {
                return Result.error(404, "首页信息不存在");
            }
            return Result.success(homeInfo);
        } catch (Exception e) {
            log.error("根据ID获取首页信息失败, id: {}", id, e);
            return Result.error(500, "获取首页信息失败");
        }
    }
    
    /**
     * 创建新的首页信息
     * @param homeInfo 首页信息
     * @return 创建成功的首页信息
     */
    @PostMapping("/manage")
    public Result<HomeInfo> createHomeInfo(@RequestBody HomeInfo homeInfo) {
        try {
            HomeInfo createdHomeInfo = homeService.createHomeInfo(homeInfo);
            if (createdHomeInfo == null) {
                return Result.error(400, "创建首页信息失败，请检查输入数据");
            }
            return Result.success(createdHomeInfo);
        } catch (Exception e) {
            log.error("创建首页信息失败", e);
            return Result.error(500, "创建首页信息失败");
        }
    }
    
    /**
     * 更新首页信息
     * @param id 首页信息ID
     * @param homeInfo 更新的首页信息
     * @return 更新后的首页信息
     */
    @PutMapping("/manage/{id}")
    public Result<HomeInfo> updateHomeInfo(@PathVariable Long id, @RequestBody HomeInfo homeInfo) {
        try {
            HomeInfo updatedHomeInfo = homeService.updateHomeInfo(id, homeInfo);
            if (updatedHomeInfo == null) {
                return Result.error(400, "更新首页信息失败，请检查ID和输入数据");
            }
            return Result.success(updatedHomeInfo);
        } catch (Exception e) {
            log.error("更新首页信息失败, id: {}", id, e);
            return Result.error(500, "更新首页信息失败");
        }
    }
    
    /**
     * 删除首页信息
     * @param id 首页信息ID
     * @return 删除结果
     */
    @DeleteMapping("/manage/{id}")
    public Result<String> deleteHomeInfo(@PathVariable Long id) {
        try {
            boolean deleted = homeService.deleteHomeInfo(id);
            if (deleted) {
                return Result.success("删除首页信息成功");
            } else {
                return Result.error(400, "删除首页信息失败，记录可能不存在");
            }
        } catch (Exception e) {
            log.error("删除首页信息失败, id: {}", id, e);
            return Result.error(500, "删除首页信息失败");
        }
    }
}
