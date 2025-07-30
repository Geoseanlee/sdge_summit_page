package com.sdgs.service.impl;

import com.sdgs.entity.ExpoSummit;
import com.sdgs.mapper.ExpoSummitMapper;
import com.sdgs.service.ExpoSummitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExpoSummitServiceImpl implements ExpoSummitService {

    @Autowired
    private ExpoSummitMapper expoSummitMapper;

    @Override
    public Map<String, Object> getCompleteData() {
        try {
            Map<String, Object> completeData = new HashMap<>();

            // 获取议程数据
            List<ExpoSummit> scheduleDataList = expoSummitMapper.getScheduleList();
            List<Map<String, Object>> scheduleItems = scheduleDataList.stream()
                    .map(data -> {
                        Map<String, Object> item = new HashMap<>();
                        item.put("id", data.getId());
                        item.put("date", data.getDate());
                        item.put("time", data.getTime());
                        item.put("event", data.getEvent());
                        item.put("dataType", "schedule");
                        return item;
                    })
                    .collect(Collectors.toList());
            completeData.put("schedule", scheduleItems);

            // 获取嘉宾数据
            List<ExpoSummit> guestDataList = expoSummitMapper.getGuestList();
            List<Map<String, Object>> guestItems = guestDataList.stream()
                    .map(data -> {
                        Map<String, Object> item = new HashMap<>();
                        item.put("id", data.getId());
                        item.put("categoryTitle", data.getCategoryTitle());
                        item.put("guestName", data.getGuestName());
                        item.put("guestImage", data.getGuestImage());
                        item.put("dataType", "guest");
                        return item;
                    })
                    .collect(Collectors.toList());
            completeData.put("guests", guestItems);

            return completeData;
        } catch (Exception e) {
            log.error("获取峰会完整数据失败", e);
            return null;
        }
    }

    @Override
    public boolean addSummitData(ExpoSummit summitData) {
        try {
            return expoSummitMapper.insertSummitData(summitData) > 0;
        } catch (Exception e) {
            log.error("添加峰会数据失败", e);
            return false;
        }
    }

    @Override
    public boolean updateSummitData(ExpoSummit summitData) {
        try {
            return expoSummitMapper.updateSummitData(summitData) > 0;
        } catch (Exception e) {
            log.error("更新峰会数据失败", e);
            return false;
        }
    }

    @Override
    public boolean deleteSummitData(Long id) {
        try {
            return expoSummitMapper.deleteSummitData(id) > 0;
        } catch (Exception e) {
            log.error("删除峰会数据失败", e);
            return false;
        }
    }
}