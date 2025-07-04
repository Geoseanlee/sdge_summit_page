package com.sdgs.controller;

import com.sdgs.common.Result;
import com.sdgs.entity.SummitSchedule;
import com.sdgs.entity.SummitGuestCategory;
import com.sdgs.service.SummitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/summit")
public class SummitController {

    @Autowired
    private SummitService summitService;

    /**
     * 获取峰会议程信息
     * 
     * @return 峰会议程列表
     */
    @GetMapping("/schedule")
    public Result<List<SummitSchedule>> getSchedule() {
        try {
            List<SummitSchedule> scheduleList = summitService.getScheduleList();
            // 即使scheduleList为null，也返回成功状态，让前端处理null值显示
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
    public Result<List<SummitGuestCategory>> getGuests() {
        try {
            List<SummitGuestCategory> guestCategories = summitService.getGuestCategories();
            // 即使guestCategories为null，也返回成功状态，让前端处理null值显示
            return Result.success(guestCategories);
        } catch (Exception e) {
            log.error("获取峰会嘉宾信息失败", e);
            return Result.error(500, "获取峰会嘉宾信息失败");
        }
    }
}