package com.sdgs.mapper;

import com.sdgs.entity.ExpoInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExpoInfoMapper {
    
    @Select("SELECT id, title, content, create_time, update_time FROM expo_info ORDER BY update_time DESC LIMIT 1")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "content", column = "content"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    ExpoInfo selectLatest();
} 