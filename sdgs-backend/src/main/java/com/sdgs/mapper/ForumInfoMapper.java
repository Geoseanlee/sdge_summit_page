package com.sdgs.mapper;

import com.sdgs.entity.ForumInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ForumInfoMapper {

    /**
     * 查询最新一条论坛介绍信息
     */
    @Select("SELECT id, title, content, create_time, update_time FROM forum_info ORDER BY update_time DESC LIMIT 1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    ForumInfo selectLatest();

    @Update("UPDATE forum_info SET title=#{title}, content=#{content}, update_time=NOW() WHERE id=#{id}")
    int updateById(ForumInfo info);
} 