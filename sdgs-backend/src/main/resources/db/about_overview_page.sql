/* ————————————————————————————————
   0. 可选：创建并切换到业务库
   ———————————————————————————————— */
CREATE DATABASE IF NOT EXISTS `sdgs_db`
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE `sdgs_db`;

/* ————————————————————————————————
   1. “关于我们”页面单表（最终版）
   表名：about_overview_page
   ———————————————————————————————— */
DROP TABLE IF EXISTS `about_overview_page`;
CREATE TABLE `about_overview_page` (
                                       `id`               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                                       `locale`           VARCHAR(10)  NOT NULL DEFAULT 'zh-CN' COMMENT '语言标识 (如 zh-CN / en-US)',

    /* === Hero / Header 区 === */
                                       `header_title`     VARCHAR(128)              COMMENT '页面主标题',
                                       `header_tags`      VARCHAR(256)              COMMENT '顶部 3~5 个卡标签，逗号分隔即可',
                                       `hero_btn_text`    VARCHAR(32)               COMMENT '顶部按钮文字',
                                       `hero_btn_link`    VARCHAR(256)              COMMENT '顶部按钮链接 URL',
                                       `hero_img`         VARCHAR(256)              COMMENT '顶部大图 URL',

    /* === 引言段落 ===== */
                                       `intro_html`       TEXT                      COMMENT '引言段 HTML（图①下方两段文字）',

    /* === 三大优势 (图②上三卡片) === */
                                       `advantages_json`  JSON                      COMMENT '“三大优势” 数组 [ { image,title,desc }, ... ]',

    /* === 四色统计卡 (图②下四块) === */
                                       `stats_json`       JSON                      COMMENT '“覆盖国家 / 媒体覆盖 …” 4 色块数组',

    /* === Logo 列表 === */
                                       `media_json`       JSON                      COMMENT '“部分平台及媒体资源” logo 数组',
                                       `special_json`     JSON                      COMMENT '“特别合作伙伴” logo 数组',
                                       `partner_json`     JSON                      COMMENT '“合作伙伴” logo 数组',

    /* === 通用字段 === */
                                       `status`           TINYINT       NOT NULL DEFAULT 1 COMMENT '1=已发布 0=草稿',
                                       `created_at`       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `updated_at`       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `uk_locale` (`locale`)  -- 一种语言只保留一行
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT = '关于我们页面（单表维护所有模块）';

/* ————————————————————————————————
   2. 演示：插入一行中文草稿（可删）
   ———————————————————————————————— */
/* ————————————————————————————————
   2. 演示：插入一行中文（已补全 URL）
   ———————————————————————————————— */
INSERT INTO `about_overview_page`
(`locale`, `header_title`, `header_tags`, `hero_btn_text`, `hero_btn_link`, `hero_img`,
 `intro_html`, `advantages_json`, `stats_json`,
 `media_json`, `special_json`, `partner_json`, `status`)
VALUES
    (
        'zh-CN',
        '世博全球青年联盟',
        '中国品牌，走向世界,中国文化，世界传播,中国青年，世界风采',
        '更多',
        '#',
        'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/11/eec122b17b3f475d9a9e042829ad69b2.jpg',

        '世博全球青年联盟（World Expo Global Young Alliance）在迪拜世博会期间，由世博全球青年SDGs峰会组委会与SDGsVision 2050发起，在迪拜世博会中华文化馆与中华文化促进会支持下成立，为激发全球青年为实现可持续发展目标SDGs（也称为全球目标和2030年议程）作出贡献的行动。/n世博全球青年联盟将连接全球的创意机构和人才，汇聚全球产业界、政界、学界、商界力量着力落实《联合国2030年可持续发展议程》，促进社会进步、环境保护、经济建设可持续发展。',

        /* 三大优势 */
        JSON_ARRAY(
                JSON_OBJECT(
                        'image','https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/11/6f35d484716048c0a74b505e3f905687.jpg',
                        'title','2025年世博全球青年团队携手联合国教科文组织参与日本大阪世博会',
                        'desc','依托世博会和联合国科教文组织的世界影响力，我们为品牌提供权威认可，助力全球传播，提升品牌公信力与国际竞争力。'
                ),
                JSON_OBJECT(
                        'image','https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/11/e7e0b3fbea334854a10ceb6e70b3055a.jpg',
                        'title','广泛的海内外青年与高校社群',
                        'desc','社群成员覆盖全球192个国家、7000万人次，直接链接1000+高校及130+知名媒体资源，助力品牌传播。'
                ),
                JSON_OBJECT(
                        'image','https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/11/811b6979d83741d8a538a74be158c943.jpg',
                        'title','跨界联动，融合艺术与科技',
                        'desc','打破行业边界，将艺术与科技深度融入品牌建设中，跨界联动，构建多元传播生态，拓展全球影响力。'
                )
        ),

        /* 统计四宫格 */
        JSON_ARRAY(
                JSON_OBJECT('color','#4A9AD4','title','覆盖国家','desc','192 个国家<br>2500 万人次'),
                JSON_OBJECT('color','#D0A36A','title','全球权威媒体覆盖','desc','BBC、欧洲时报、纽约时报、中新网、中国网、新华社、人民日报等海内外130余家媒体资源'),
                JSON_OBJECT('color','#4C8B7D','title','菁英合作社群强强联手','desc','44 个全球菁英合作伙伴'),
                JSON_OBJECT('color','#B15054','title','海内外志愿者','desc','83 位海内外志愿者团队')
        ),

        /* logo 示例（同一张图演示，按需替换/扩展） */
        JSON_ARRAY('https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/11/5c42ef566a4e449a92ccb5a42f1e7952.png'),
        JSON_ARRAY('https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/11/5c42ef566a4e449a92ccb5a42f1e7952.png'),
        JSON_ARRAY('https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/11/5c42ef566a4e449a92ccb5a42f1e7952.png'),

        1   -- 已发布
    );

/* 查询示例（给前端）： */
-- SELECT * FROM about_overview_page WHERE locale='zh-CN' LIMIT 1;


