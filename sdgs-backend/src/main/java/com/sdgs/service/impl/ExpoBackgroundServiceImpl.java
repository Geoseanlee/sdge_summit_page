package com.sdgs.service.impl;

import com.sdgs.entity.ExpoBackground;
import com.sdgs.mapper.ExpoBackgroundMapper;
import com.sdgs.service.ExpoBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExpoBackgroundServiceImpl implements ExpoBackgroundService {

    @Autowired
    private ExpoBackgroundMapper expoBackgroundMapper;

    @Override
    public Map<String, Object> getExpoBackgroundData() {
        Map<String, Object> result = new HashMap<>();

        // 第一部分主标题
        result.put("part1MainTitle", getDataByType("part1_main_title"));

        // 第一部分的三个小section
        result.put("part1Section1Title", getDataByType("part1_section1_title"));
        result.put("part1Section1Content", getDataByType("part1_section1_content"));
        result.put("part1Section1Image", getDataByType("part1_section1_image"));

        result.put("part1Section2Title", getDataByType("part1_section2_title"));
        result.put("part1Section2Content", getDataByType("part1_section2_content"));
        result.put("part1Section2Image", getDataByType("part1_section2_image"));

        result.put("part1Section3Title", getDataByType("part1_section3_title"));
        result.put("part1Section3Content", getDataByType("part1_section3_content"));
        result.put("part1Section3Image", getDataByType("part1_section3_image"));

        // 第二部分
        result.put("part2MainTitle", getDataByType("part2_main_title"));
        result.put("part2SubTitle", getDataByType("part2_subtitle"));
        result.put("part2Content", getDataByType("part2_content"));

        return result;
    }

    @Override
    public ExpoBackground getByType(String sectionType) {
        return expoBackgroundMapper.findBySectionType(sectionType);
    }

    private String getDataByType(String sectionType) {
        ExpoBackground background = getByType(sectionType);
        if (background != null) {
            if (background.getTitle() != null) {
                return background.getTitle();
            } else if (background.getContent() != null) {
                return background.getContent();
            } else if (background.getImageUrl() != null) {
                return background.getImageUrl();
            }
        }
        return null;
    }
}