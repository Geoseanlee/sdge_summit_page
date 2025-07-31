package com.sdgs.mapper;

import com.sdgs.entity.JoinUsContent;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface JoinUsContentMapper {

    // 查询全部
    @Select("SELECT * FROM join_us_content ORDER BY sort_order ASC")
    List<JoinUsContent> findAll();

    // 按类别查询
    @Select("SELECT * FROM join_us_content WHERE category = #{category} ORDER BY sort_order ASC")
    List<JoinUsContent> listByCategory(String category);

    @Insert("INSERT INTO join_us_content (category, title, description, image_url, link_url, sort_order, create_time) " +
            "VALUES (#{category}, #{title}, #{description}, #{imageUrl}, #{linkUrl}, #{sortOrder}, NOW())")
    int add(JoinUsContent content);

    @Delete("DELETE FROM join_us_content WHERE id = #{id}")
    int delete(Long id);

    @Update("UPDATE join_us_content SET " +
        "title = #{title}, " +
        "description = #{description}, " +
        "image_url = #{imageUrl}, " +
        "link_url = #{linkUrl}, " +
        "update_time = NOW() " +
        "WHERE id = #{id}")
    int update(JoinUsContent content);

}

