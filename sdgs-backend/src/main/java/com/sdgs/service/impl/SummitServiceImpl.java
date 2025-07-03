package com.sdgs.service.impl;

import com.sdgs.entity.SummitSchedule;
import com.sdgs.entity.SummitGuest;
import com.sdgs.entity.SummitGuestCategory;
import com.sdgs.mapper.SummitScheduleMapper;
import com.sdgs.mapper.SummitGuestMapper;
import com.sdgs.service.SummitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SummitServiceImpl implements SummitService {

    @Autowired
    private SummitScheduleMapper summitScheduleMapper;

    @Autowired
    private SummitGuestMapper summitGuestMapper;

    @Override
    public List<SummitSchedule> getScheduleList() {
        try {
            return summitScheduleMapper.selectAll();
        } catch (Exception e) {
            log.error("获取峰会议程失败", e);
            return null;
        }
    }

    @Override
    public List<SummitGuestCategory> getGuestCategories() {
        try {
            // 获取所有分类
            List<SummitGuestCategory> categories = summitGuestMapper.selectAllCategories();

            // 为每个分类设置嘉宾列表
            categories.forEach(category -> {
                List<SummitGuest> guests = summitGuestMapper.selectGuestsByCategoryId(category.getId());
                category.setGuests(guests);
            });

            return categories;
        } catch (Exception e) {
            log.error("获取峰会嘉宾信息失败", e);
            return null;
        }
    }
}