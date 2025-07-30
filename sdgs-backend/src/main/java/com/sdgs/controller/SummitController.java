package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.entity.ExpoSummit;
import com.sdgs.service.ExpoSummitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/summit")
public class SummitController {

    @Autowired
    private ExpoSummitService summitService;

    /**
     * 获取峰会完整数据（统一接口）
     * 包含议程和嘉宾信息
     * 
     * @return 峰会完整数据
     */
    @GetMapping("/complete")
    public Result<Map<String, Object>> getCompleteData() {
        try {
            Map<String, Object> completeData = summitService.getCompleteData();
            return Result.success(completeData);
        } catch (Exception e) {
            log.error("获取峰会完整数据失败", e);
            return Result.error(500, "获取峰会完整数据失败");
        }
    }

    /**
     * 获取峰会议程信息
     * 
     * @return 峰会议程列表
     */
    @GetMapping("/schedule")
    public Result<Object> getSchedule() {
        try {
            // 这个接口保留用于向下兼容，但实际数据来自统一表
            Map<String, Object> completeData = summitService.getCompleteData();
            Object scheduleList = completeData != null ? completeData.get("schedule") : null;
            return Result.success(scheduleList);
        } catch (Exception e) {
            log.error("获取峰会议程失败", e);
            return Result.error(500, "获取峰会议程失败");
        }
    }

    /**
     * 获取峰会嘉宾分类及嘉宾信息
     * 
     * @return 嘉宾分类列表（包含嘉宾信息）
     */
    @GetMapping("/guests")
    public Result<Object> getGuests() {
        try {
            // 这个接口保留用于向下兼容，但实际数据来自统一表
            Map<String, Object> completeData = summitService.getCompleteData();
            Object guestCategories = completeData != null ? completeData.get("guests") : null;
            return Result.success(guestCategories);
        } catch (Exception e) {
            log.error("获取峰会嘉宾信息失败", e);
            return Result.error(500, "获取峰会嘉宾信息失败");
        }
    }

    /**
     * 添加峰会数据项（议程或嘉宾）
     * 
     * @param summitData 峰会数据
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result<String> addSummitData(@RequestBody ExpoSummit summitData) {
        try {
            boolean success = summitService.addSummitData(summitData);
            if (success) {
                return Result.success("添加成功");
            } else {
                return Result.error(500, "添加失败");
            }
        } catch (Exception e) {
            log.error("添加峰会数据失败", e);
            return Result.error(500, "添加峰会数据失败");
        }
    }

    /**
     * 更新峰会数据项
     * 
     * @param id 数据ID
     * @param summitData 峰会数据
     * @return 更新结果
     */
    @PutMapping("/update/{id}")
    public Result<String> updateSummitData(@PathVariable Long id, @RequestBody ExpoSummit summitData) {
        try {
            summitData.setId(id);
            boolean success = summitService.updateSummitData(summitData);
            if (success) {
                return Result.success("更新成功");
            } else {
                return Result.error(500, "更新失败");
            }
        } catch (Exception e) {
            log.error("更新峰会数据失败", e);
            return Result.error(500, "更新峰会数据失败");
        }
    }

    /**
     * 删除峰会数据项
     * 
     * @param id 数据ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    public Result<String> deleteSummitData(@PathVariable Long id) {
        try {
            boolean success = summitService.deleteSummitData(id);
            if (success) {
                return Result.success("删除成功");
            } else {
                return Result.error(500, "删除失败");
            }
        } catch (Exception e) {
            log.error("删除峰会数据失败", e);
            return Result.error(500, "删除峰会数据失败");
        }
    }
}