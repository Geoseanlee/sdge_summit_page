package com.sdgs.mapper;

import com.sdgs.entity.AboutOverviewPage;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AboutOverviewPageMapper {

    /* ———————— 查询 ———————— */

    /** 按语言获取已发布页面（前端用） */
    @Select("""
        SELECT * FROM about_overview_page
        WHERE locale = #{locale} AND status = 1
        LIMIT 1
        """)
    AboutOverviewPage selectPublishedByLocale(@Param("locale") String locale);

    /** 后台进入编辑页：不管草稿/发布都取 */
    @Select("SELECT * FROM about_overview_page WHERE id = #{id}")
    AboutOverviewPage selectById(@Param("id") Long id);

    /* ———————— 写入 ———————— */

    /** 新增草稿（管理后台"新建语言版本"） */
    @Insert("""
        INSERT INTO about_overview_page
        (locale, header_title, header_tags,
         hero_btn_text, hero_btn_link, hero_img,
         intro_html, advantages_title, advantages_json, stats_title, stats_json,
         media_title, media_json, special_title, special_json, partner_title, partner_json,
         status, created_at, updated_at)
        VALUES
        (#{locale}, #{headerTitle}, #{headerTags},
         #{heroBtnText}, #{heroBtnLink}, #{heroImg},
         #{introHtml}, #{advantagesTitle}, #{advantagesJson}, #{statsTitle}, #{statsJson},
         #{mediaTitle}, #{mediaJson}, #{specialTitle}, #{specialJson}, #{partnerTitle}, #{partnerJson},
         #{status}, NOW(), NOW())
        """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(AboutOverviewPage page);

    /** 更新整页（后台"保存/发布"按钮） */
    @Update("""
        UPDATE about_overview_page SET
          header_title     = #{headerTitle},
          header_tags      = #{headerTags},
          hero_btn_text    = #{heroBtnText},
          hero_btn_link    = #{heroBtnLink},
          hero_img         = #{heroImg},
          intro_html       = #{introHtml},
          advantages_title = #{advantagesTitle},
          advantages_json  = #{advantagesJson},
          stats_title      = #{statsTitle},
          stats_json       = #{statsJson},
          media_title      = #{mediaTitle},
          media_json       = #{mediaJson},
          special_title    = #{specialTitle},
          special_json     = #{specialJson},
          partner_title    = #{partnerTitle},
          partner_json     = #{partnerJson},
          status           = #{status},
          updated_at       = NOW()
        WHERE id = #{id}
        """)
    int updateById(AboutOverviewPage page);

    /** 彻底删除（如果确实需要） */
    @Delete("DELETE FROM about_overview_page WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
}