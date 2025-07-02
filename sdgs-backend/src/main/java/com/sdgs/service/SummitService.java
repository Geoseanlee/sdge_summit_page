package com.sdgs.service;

import com.sdgs.entity.SummitSchedule;
import com.sdgs.entity.SummitGuestCategory;

import java.util.List;

public interface SummitService {

    /**
     * 获取峰会议程列表
     * 
     * @return 峰会议程列表
     */
    List<SummitSchedule> getScheduleList();

    /**
     * 获取峰会嘉宾分类及嘉宾信息
     * 
     * @return 嘉宾分类列表（包含嘉宾信息）
     */
    List<SummitGuestCategory> getGuestCategories();
}