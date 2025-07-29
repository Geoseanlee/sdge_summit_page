package com.sdgs.mapper;

import com.sdgs.entity.PageConfig;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PageConfigMapper {

    @Select("SELECT * FROM page_config WHERE page_key = #{pageKey}")
    PageConfig findByPageKey(String pageKey);

    @Update("UPDATE page_config SET config_data = #{configData} WHERE page_key = #{pageKey}")
    int updateByPageKey(@Param("pageKey") String pageKey, @Param("configData") String configData);
    
    // 如果需要，也可以添加insert方法
}