package com.sdgs.mapper;

import com.sdgs.entity.ExpoSummit;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 世博会峰会数据 Mapper 接口
 * 
 * @author system
 * @since 2025-07-30
 */
@Mapper
public interface ExpoSummitMapper {

    /**
     * 获取峰会议程数据
     * 
     * @return 议程列表
     */
    @Select("SELECT * FROM expo_summit WHERE data_type = 'schedule' ORDER BY sort_order ASC")
    List<ExpoSummit> getScheduleList();

    /**
     * 获取峰会嘉宾数据
     * 
     * @return 嘉宾列表
     */
    @Select("SELECT * FROM expo_summit WHERE data_type = 'guest' ORDER BY sort_order ASC")
    List<ExpoSummit> getGuestList();

    /**
     * 添加峰会数据项
     * 
     * @param summitData 峰会数据
     * @return 影响行数
     */
    @Insert("INSERT INTO expo_summit (data_type, date, time, event, category_title, guest_name, guest_image, sort_order) " +
            "VALUES (#{dataType}, #{date}, #{time}, #{event}, #{categoryTitle}, #{guestName}, #{guestImage}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSummitData(ExpoSummit summitData);

    /**
     * 更新峰会数据项
     * 
     * @param summitData 峰会数据
     * @return 影响行数
     */
    @Update("UPDATE expo_summit SET data_type = #{dataType}, date = #{date}, time = #{time}, event = #{event}, " +
            "category_title = #{categoryTitle}, guest_name = #{guestName}, guest_image = #{guestImage}, " +
            "sort_order = #{sortOrder} WHERE id = #{id}")
    int updateSummitData(ExpoSummit summitData);

    /**
     * 删除峰会数据项
     * 
     * @param id 数据ID
     * @return 影响行数
     */
    @Delete("DELETE FROM expo_summit WHERE id = #{id}")
    int deleteSummitData(Long id);
}
