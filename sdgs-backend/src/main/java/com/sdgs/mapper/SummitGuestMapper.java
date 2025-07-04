package com.sdgs.mapper;

import com.sdgs.entity.SummitGuest;
import com.sdgs.entity.SummitGuestCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SummitGuestMapper {

    // 查询所有嘉宾分类
    @Select("SELECT id, title, sort_order, create_time, update_time FROM summit_guest_category ORDER BY sort_order ASC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "sortOrder", column = "sort_order"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<SummitGuestCategory> selectAllCategories();

    // 根据分类ID查询嘉宾列表
    @Select("SELECT id, category_id, guest_name, guest_image, sort_order, create_time, update_time FROM summit_guest WHERE category_id = #{categoryId} ORDER BY sort_order ASC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "guestName", column = "guest_name"),
            @Result(property = "guestImage", column = "guest_image"),
            @Result(property = "sortOrder", column = "sort_order"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<SummitGuest> selectGuestsByCategoryId(Long categoryId);

    // 查询所有嘉宾
    @Select("SELECT id, category_id, guest_name, guest_image, sort_order, create_time, update_time FROM summit_guest ORDER BY category_id ASC, sort_order ASC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "guestName", column = "guest_name"),
            @Result(property = "guestImage", column = "guest_image"),
            @Result(property = "sortOrder", column = "sort_order"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<SummitGuest> selectAllGuests();
}