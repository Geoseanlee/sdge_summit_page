package com.sdgs.mapper;

import com.sdgs.entity.Forum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import java.util.List;
 
@Mapper
public interface ForumMapper {
    
    @Select("SELECT * FROM forum ORDER BY create_time DESC")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "description", column = "description"),
        @Result(property = "imageUrl", column = "image_url"),
        @Result(property = "tag", column = "tag"),
        @Result(property = "url", column = "url"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    List<Forum> selectAll();
    
    @Select("SELECT * FROM forum WHERE id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "description", column = "description"),
        @Result(property = "imageUrl", column = "image_url"),
        @Result(property = "tag", column = "tag"),
        @Result(property = "url", column = "url"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    Forum selectById(Long id);
    
    @Insert("INSERT INTO forum (title, description, image_url, tag, url, create_time, update_time) VALUES (#{title}, #{description}, #{imageUrl}, #{tag}, #{url}, NOW(), NOW())")
    int insert(Forum forum);
    
    @Update("UPDATE forum SET title = #{title}, description = #{description}, image_url = #{imageUrl}, tag = #{tag}, url = #{url}, update_time = NOW() WHERE id = #{id}")
    int updateById(Forum forum);
    
    @Delete("DELETE FROM forum WHERE id = #{id}")
    int deleteById(Long id);
} 