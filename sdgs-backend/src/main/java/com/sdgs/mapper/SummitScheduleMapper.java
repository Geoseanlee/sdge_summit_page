package com.sdgs.mapper;

import com.sdgs.entity.SummitSchedule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SummitScheduleMapper {

    @Select("SELECT id, date, time, event, date_color, time_color, event_color, sort_order, create_time, update_time FROM summit_schedule ORDER BY sort_order ASC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "date", column = "date"),
            @Result(property = "time", column = "time"),
            @Result(property = "event", column = "event"),
            @Result(property = "dateColor", column = "date_color"),
            @Result(property = "timeColor", column = "time_color"),
            @Result(property = "eventColor", column = "event_color"),
            @Result(property = "sortOrder", column = "sort_order"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<SummitSchedule> selectAll();

    @Select("SELECT id, date, time, event, date_color, time_color, event_color, sort_order, create_time, update_time FROM summit_schedule WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "date", column = "date"),
            @Result(property = "time", column = "time"),
            @Result(property = "event", column = "event"),
            @Result(property = "dateColor", column = "date_color"),
            @Result(property = "timeColor", column = "time_color"),
            @Result(property = "eventColor", column = "event_color"),
            @Result(property = "sortOrder", column = "sort_order"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    SummitSchedule selectById(Long id);
}