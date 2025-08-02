package com.sdgs.mapper;

import com.sdgs.entity.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsMapper {

    /**
     * 查询所有新闻（按发布时间倒序）
     */
    @Select("SELECT * FROM news WHERE status = 1 ORDER BY publish_time DESC")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "subtitle", column = "subtitle"),
        @Result(property = "summary", column = "summary"),
        @Result(property = "content", column = "content"),
        @Result(property = "coverImageUrl", column = "cover_image_url"),
        @Result(property = "publishTime", column = "publish_time"),
        @Result(property = "status", column = "status"),
        @Result(property = "viewCount", column = "view_count"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    List<News> selectPublished();

    /**
     * 管理端查询所有新闻（包括草稿）
     */
    @Select("SELECT * FROM news ORDER BY create_time DESC")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "subtitle", column = "subtitle"),
        @Result(property = "summary", column = "summary"),
        @Result(property = "content", column = "content"),
        @Result(property = "coverImageUrl", column = "cover_image_url"),
        @Result(property = "publishTime", column = "publish_time"),
        @Result(property = "status", column = "status"),
        @Result(property = "viewCount", column = "view_count"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    List<News> selectAll();

    /**
     * 根据ID查询新闻
     */
    @Select("SELECT * FROM news WHERE id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "subtitle", column = "subtitle"),
        @Result(property = "summary", column = "summary"),
        @Result(property = "content", column = "content"),
        @Result(property = "coverImageUrl", column = "cover_image_url"),
        @Result(property = "publishTime", column = "publish_time"),
        @Result(property = "status", column = "status"),
        @Result(property = "viewCount", column = "view_count"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    News selectById(Long id);

    /**
     * 插入新闻
     */
    @Insert("INSERT INTO news (title, subtitle, summary, content, cover_image_url, publish_time, status, view_count, create_time, update_time) " +
            "VALUES (#{title}, #{subtitle}, #{summary}, #{content}, #{coverImageUrl}, #{publishTime}, #{status}, #{viewCount}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(News news);

    /**
     * 更新新闻
     */
    @Update("UPDATE news SET title = #{title}, subtitle = #{subtitle}, summary = #{summary}, content = #{content}, cover_image_url = #{coverImageUrl}, publish_time = #{publishTime}, status = #{status}, update_time = NOW() WHERE id = #{id}")
    int updateById(News news);

    /**
     * 删除新闻
     */
    @Delete("DELETE FROM news WHERE id = #{id}")
    int deleteById(Long id);

    /**
     * 增加阅读量
     */
    @Update("UPDATE news SET view_count = view_count + 1 WHERE id = #{id}")
    int incrementViewCount(Long id);

} 