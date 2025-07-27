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

    @Override
    @Transactional
    public void saveExpoBackgroundData(Map<String, Object> data) {
        // 保存第一部分主标题
        saveOrUpdateField("part1_main_title", (String) data.get("part1MainTitle"), null, null, 1);

        // 保存第一部分的三个小section
        saveOrUpdateField("part1_section1_title", (String) data.get("part1Section1Title"), null, null, 2);
        saveOrUpdateField("part1_section1_content", null, (String) data.get("part1Section1Content"), null, 3);
        saveOrUpdateField("part1_section1_image", null, null, (String) data.get("part1Section1Image"), 4);

        saveOrUpdateField("part1_section2_title", (String) data.get("part1Section2Title"), null, null, 5);
        saveOrUpdateField("part1_section2_content", null, (String) data.get("part1Section2Content"), null, 6);
        saveOrUpdateField("part1_section2_image", null, null, (String) data.get("part1Section2Image"), 7);

        saveOrUpdateField("part1_section3_title", (String) data.get("part1Section3Title"), null, null, 8);
        saveOrUpdateField("part1_section3_content", null, (String) data.get("part1Section3Content"), null, 9);
        saveOrUpdateField("part1_section3_image", null, null, (String) data.get("part1Section3Image"), 10);

        // 保存第二部分
        saveOrUpdateField("part2_main_title", (String) data.get("part2MainTitle"), null, null, 11);
        saveOrUpdateField("part2_subtitle", (String) data.get("part2SubTitle"), null, null, 12);
        saveOrUpdateField("part2_content", null, (String) data.get("part2Content"), null, 13);
    }

    private void saveOrUpdateField(String sectionType, String title, String content, String imageUrl, int sortOrder) {
        ExpoBackground background = expoBackgroundMapper.findBySectionType(sectionType);
        if (background == null) {
            // 不存在则新增
            background = new ExpoBackground();
            background.setSectionType(sectionType);
            background.setTitle(title);
            background.setContent(content);
            background.setImageUrl(imageUrl);
            background.setSortOrder(sortOrder);
            expoBackgroundMapper.insert(background);
        } else {
            // 存在则更新
            background.setTitle(title);
            background.setContent(content);
            background.setImageUrl(imageUrl);
            background.setSortOrder(sortOrder);
            expoBackgroundMapper.update(background);
        }
    }
}