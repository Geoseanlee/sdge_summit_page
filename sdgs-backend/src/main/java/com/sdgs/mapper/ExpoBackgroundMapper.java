package com.sdgs.mapper;


import com.sdgs.entity.ExpoBackground;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ExpoBackgroundMapper {

    @Select("SELECT * FROM expo_background WHERE section_type = #{sectionType}")
    ExpoBackground findBySectionType(String sectionType);

    @Insert("INSERT INTO expo_background(section_type, title, content, image_url, sort_order, created_time, updated_time) " +
            "VALUES(#{sectionType}, #{title}, #{content}, #{imageUrl}, #{sortOrder}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ExpoBackground expoBackground);

    @Update("UPDATE expo_background SET title = #{title}, content = #{content}, image_url = #{imageUrl}, " +
            "sort_order = #{sortOrder}, updated_time = NOW() WHERE section_type = #{sectionType}")
    int update(ExpoBackground expoBackground);
}