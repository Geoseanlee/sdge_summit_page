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
         more_page_title, more_page_final_statement,
         status, created_at, updated_at)
        VALUES
        (#{locale}, #{headerTitle}, #{headerTags},
         #{heroBtnText}, #{heroBtnLink}, #{heroImg},
         #{introHtml}, #{advantagesTitle}, #{advantagesJson}, #{statsTitle}, #{statsJson},
         #{mediaTitle}, #{mediaJson}, #{specialTitle}, #{specialJson}, #{partnerTitle}, #{partnerJson},
         #{morePageTitle}, #{morePageFinalStatement},
         #{status}, NOW(), NOW())
        """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(AboutOverviewPage page);

    /** 更新整页（后台"保存/发布"按钮） */
    @Update("""
        <script>
            UPDATE about_overview_page
            <set>
                <if test="locale != null">locale = #{locale},</if>
                <if test="headerTitle != null">header_title = #{headerTitle},</if>
                <if test="headerTags != null">header_tags = #{headerTags},</if>
                <if test="heroBtnText != null">hero_btn_text = #{heroBtnText},</if>
                <if test="heroBtnLink != null">hero_btn_link = #{heroBtnLink},</if>
                <if test="heroImg != null">hero_img = #{heroImg},</if>
                <if test="introHtml != null">intro_html = #{introHtml},</if>
                <if test="advantagesTitle != null">advantages_title = #{advantagesTitle},</if>
                <if test="advantagesJson != null">advantages_json = #{advantagesJson},</if>
                <if test="statsTitle != null">stats_title = #{statsTitle},</if>
                <if test="statsJson != null">stats_json = #{statsJson},</if>
                <if test="mediaTitle != null">media_title = #{mediaTitle},</if>
                <if test="mediaJson != null">media_json = #{mediaJson},</if>
                <if test="specialTitle != null">special_title = #{specialTitle},</if>
                <if test="specialJson != null">special_json = #{specialJson},</if>
                <if test="partnerTitle != null">partner_title = #{partnerTitle},</if>
                <if test="partnerJson != null">partner_json = #{partnerJson},</if>
                <if test="morePageTitle != null">more_page_title = #{morePageTitle},</if>
                <if test="morePageFinalStatement != null">more_page_final_statement = #{morePageFinalStatement},</if>
                <if test="status != null">status = #{status},</if>
                updated_at = NOW()
            </set>
            WHERE id = #{id}
        </script>
        """)
    int updateById(AboutOverviewPage page);

    /** 彻底删除（如果确实需要） */
    @Delete("DELETE FROM about_overview_page WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
}