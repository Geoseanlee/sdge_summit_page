package com.sdgs.mapper;


import com.sdgs.entity.ExpoBackground;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ExpoBackgroundMapper {

    @Select("SELECT * FROM expo_background WHERE section_type = #{sectionType}")
    ExpoBackground findBySectionType(String sectionType);
}