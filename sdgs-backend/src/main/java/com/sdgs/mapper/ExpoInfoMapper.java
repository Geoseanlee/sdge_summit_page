package com.sdgs.mapper;

import com.sdgs.entity.ExpoInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ExpoInfoMapper {
    
    /**
     * 获取最新的世博会信息
     */
    @Select("SELECT id, title, subtitle, description, image_url, card_title, card_subtitle, card_description, card_image_url, section_title_1, section_image_url_1, section_title_2, section_image_url_2, section_title_3, section_image_url_3, create_time, update_time FROM expo_info ORDER BY update_time DESC LIMIT 1")
    @Results(id = "expoInfoResultMap", value = {
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "subtitle", column = "subtitle"),
        @Result(property = "description", column = "description"),
        @Result(property = "imageUrl", column = "image_url"),
        @Result(property = "cardTitle", column = "card_title"),
        @Result(property = "cardSubtitle", column = "card_subtitle"),
        @Result(property = "cardDescription", column = "card_description"),
        @Result(property = "cardImageUrl", column = "card_image_url"),
        @Result(property = "sectionTitle1", column = "section_title_1"),
        @Result(property = "sectionImageUrl1", column = "section_image_url_1"),
        @Result(property = "sectionTitle2", column = "section_title_2"),
        @Result(property = "sectionImageUrl2", column = "section_image_url_2"),
        @Result(property = "sectionTitle3", column = "section_title_3"),
        @Result(property = "sectionImageUrl3", column = "section_image_url_3"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    ExpoInfo selectLatest();
} 