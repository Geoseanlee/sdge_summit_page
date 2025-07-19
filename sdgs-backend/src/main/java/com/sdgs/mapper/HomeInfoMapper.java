package com.sdgs.mapper;

import com.sdgs.entity.HomeInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface HomeInfoMapper {
    
    /**
     * 获取最新的首页信息
     */
    @Select("SELECT id, hero_title_1, hero_subtitle_1, hero_title_2, hero_subtitle_2, hero_image_url, " +
            "sdgs_description_1, sdgs_description_2, expo_title, expo_subtitle, expo_image_url, " +
            "alliance_title, alliance_description_1, alliance_description_2, data_title, data_image_url, " +
            "join_title, join_item_1, join_item_2, join_item_3, join_image_url, create_time, update_time " +
            "FROM home_info ORDER BY update_time DESC LIMIT 1")
    @Results(id = "homeInfoResultMap", value = {
        @Result(property = "id", column = "id"),
        @Result(property = "heroTitle1", column = "hero_title_1"),
        @Result(property = "heroSubtitle1", column = "hero_subtitle_1"),
        @Result(property = "heroTitle2", column = "hero_title_2"),
        @Result(property = "heroSubtitle2", column = "hero_subtitle_2"),
        @Result(property = "heroImageUrl", column = "hero_image_url"),
        @Result(property = "sdgsDescription1", column = "sdgs_description_1"),
        @Result(property = "sdgsDescription2", column = "sdgs_description_2"),
        @Result(property = "expoTitle", column = "expo_title"),
        @Result(property = "expoSubtitle", column = "expo_subtitle"),
        @Result(property = "expoImageUrl", column = "expo_image_url"),
        @Result(property = "allianceTitle", column = "alliance_title"),
        @Result(property = "allianceDescription1", column = "alliance_description_1"),
        @Result(property = "allianceDescription2", column = "alliance_description_2"),
        @Result(property = "dataTitle", column = "data_title"),
        @Result(property = "dataImageUrl", column = "data_image_url"),
        @Result(property = "joinTitle", column = "join_title"),
        @Result(property = "joinItem1", column = "join_item_1"),
        @Result(property = "joinItem2", column = "join_item_2"),
        @Result(property = "joinItem3", column = "join_item_3"),
        @Result(property = "joinImageUrl", column = "join_image_url"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    HomeInfo getLatestHomeInfo();
    
    /**
     * 根据ID获取首页信息
     */
    @Select("SELECT id, hero_title_1, hero_subtitle_1, hero_title_2, hero_subtitle_2, hero_image_url, " +
            "sdgs_description_1, sdgs_description_2, expo_title, expo_subtitle, expo_image_url, " +
            "alliance_title, alliance_description_1, alliance_description_2, data_title, data_image_url, " +
            "join_title, join_item_1, join_item_2, join_item_3, join_image_url, create_time, update_time " +
            "FROM home_info WHERE id = #{id}")
    @ResultMap("homeInfoResultMap")
    HomeInfo getHomeInfoById(@Param("id") Long id);
    
    /**
     * 创建新的首页信息
     */
    @Insert("INSERT INTO home_info (hero_title_1, hero_subtitle_1, hero_title_2, hero_subtitle_2, hero_image_url, " +
            "sdgs_description_1, sdgs_description_2, expo_title, expo_subtitle, expo_image_url, " +
            "alliance_title, alliance_description_1, alliance_description_2, data_title, data_image_url, " +
            "join_title, join_item_1, join_item_2, join_item_3, join_image_url) " +
            "VALUES (#{heroTitle1}, #{heroSubtitle1}, #{heroTitle2}, #{heroSubtitle2}, #{heroImageUrl}, " +
            "#{sdgsDescription1}, #{sdgsDescription2}, #{expoTitle}, #{expoSubtitle}, #{expoImageUrl}, " +
            "#{allianceTitle}, #{allianceDescription1}, #{allianceDescription2}, #{dataTitle}, #{dataImageUrl}, " +
            "#{joinTitle}, #{joinItem1}, #{joinItem2}, #{joinItem3}, #{joinImageUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertHomeInfo(HomeInfo homeInfo);
    
    /**
     * 更新首页信息
     */
    @Update("UPDATE home_info SET hero_title_1 = #{heroTitle1}, hero_subtitle_1 = #{heroSubtitle1}, " +
            "hero_title_2 = #{heroTitle2}, hero_subtitle_2 = #{heroSubtitle2}, hero_image_url = #{heroImageUrl}, " +
            "sdgs_description_1 = #{sdgsDescription1}, sdgs_description_2 = #{sdgsDescription2}, " +
            "expo_title = #{expoTitle}, expo_subtitle = #{expoSubtitle}, expo_image_url = #{expoImageUrl}, " +
            "alliance_title = #{allianceTitle}, alliance_description_1 = #{allianceDescription1}, " +
            "alliance_description_2 = #{allianceDescription2}, data_title = #{dataTitle}, data_image_url = #{dataImageUrl}, " +
            "join_title = #{joinTitle}, join_item_1 = #{joinItem1}, join_item_2 = #{joinItem2}, " +
            "join_item_3 = #{joinItem3}, join_image_url = #{joinImageUrl} WHERE id = #{id}")
    int updateHomeInfo(HomeInfo homeInfo);
    
    /**
     * 删除首页信息
     */
    @Delete("DELETE FROM home_info WHERE id = #{id}")
    int deleteHomeInfo(@Param("id") Long id);
}
