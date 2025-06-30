package com.sdgs.mapper;

import com.sdgs.entity.ExpoInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ExpoInfoMapper {
    
    /**
     * 获取最新的世博会信息
     */
    @Select("SELECT id, title, subtitle, description, image_url, create_time, update_time FROM expo_info ORDER BY update_time DESC LIMIT 1")
    @Results(id = "expoInfoResultMap", value = {
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "subtitle", column = "subtitle"),
        @Result(property = "description", column = "description"),
        @Result(property = "imageUrl", column = "image_url"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    ExpoInfo selectLatest();
    
    /**
     * 根据ID查询世博会信息
     */
    @Select("SELECT id, title, subtitle, description, image_url, create_time, update_time FROM expo_info WHERE id = #{id}")
    @ResultMap("expoInfoResultMap")
    ExpoInfo selectById(Long id);
    
    /**
     * 插入新的世博会信息
     */
    @Insert("INSERT INTO expo_info (title, subtitle, description, image_url, create_time, update_time) " +
            "VALUES (#{title}, #{subtitle}, #{description}, #{imageUrl}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ExpoInfo expoInfo);
    
    /**
     * 更新世博会信息
     */
    @Update("UPDATE expo_info SET title = #{title}, subtitle = #{subtitle}, description = #{description}, " +
            "image_url = #{imageUrl}, update_time = NOW() WHERE id = #{id}")
    int update(ExpoInfo expoInfo);
    
    /**
     * 删除世博会信息
     */
    @Delete("DELETE FROM expo_info WHERE id = #{id}")
    int deleteById(Long id);
} 