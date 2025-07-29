package com.sdgs.mapper;

import com.sdgs.entity.AboutMoreSection;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AboutMoreSectionMapper {

    @Select("SELECT * FROM about_more_section WHERE is_published = true ORDER BY sort_order ASC, id ASC")
    List<AboutMoreSection> selectAllPublished();

    @Select("SELECT * FROM about_more_section ORDER BY sort_order ASC, id ASC")
    List<AboutMoreSection> selectAllForAdmin();

    @Insert("INSERT INTO about_more_section (title, content, sort_order, is_published) VALUES (#{title}, #{content}, #{sortOrder}, #{isPublished})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(AboutMoreSection section);

    @Update("UPDATE about_more_section SET title=#{title}, content=#{content}, sort_order=#{sortOrder}, is_published=#{isPublished} WHERE id=#{id}")
    int update(AboutMoreSection section);

    @Delete("DELETE FROM about_more_section WHERE id=#{id}")
    int deleteById(Long id);
}