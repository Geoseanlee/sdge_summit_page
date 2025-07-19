-- 数据库初始化脚本
-- 使用 sdgs_db 数据库
USE sdgs_db;

-- 删除现有表（如果存在）
DROP TABLE IF EXISTS `home_info`;

-- 首页信息表
CREATE TABLE `home_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `hero_title_1` varchar(100) DEFAULT NULL COMMENT '英雄区域标题1',
  `hero_subtitle_1` varchar(200) DEFAULT NULL COMMENT '英雄区域副标题1',
  `hero_title_2` varchar(100) DEFAULT NULL COMMENT '英雄区域标题2',
  `hero_subtitle_2` varchar(200) DEFAULT NULL COMMENT '英雄区域副标题2',
  `hero_image_url` varchar(500) DEFAULT NULL COMMENT '英雄区域图片URL',
  `sdgs_description_1` text DEFAULT NULL COMMENT 'SDGs第一段描述',
  `sdgs_description_2` text DEFAULT NULL COMMENT 'SDGs第二段描述',
  `expo_title` varchar(200) DEFAULT NULL COMMENT '世博会介绍标题',
  `expo_subtitle` varchar(200) DEFAULT NULL COMMENT '世博会介绍副标题',
  `expo_image_url` varchar(500) DEFAULT NULL COMMENT '世博会背景图片URL',
  `alliance_title` varchar(200) DEFAULT NULL COMMENT '联盟标题',
  `alliance_description_1` text DEFAULT NULL COMMENT '联盟第一段描述',
  `alliance_description_2` text DEFAULT NULL COMMENT '联盟第二段描述',
  `data_title` varchar(200) DEFAULT NULL COMMENT '数据区域标题',
  `data_image_url` varchar(500) DEFAULT NULL COMMENT '数据图表图片URL',
  `join_title` varchar(200) DEFAULT NULL COMMENT '加入我们标题',
  `join_item_1` varchar(500) DEFAULT NULL COMMENT '加入项目1',
  `join_item_2` varchar(500) DEFAULT NULL COMMENT '加入项目2',
  `join_item_3` varchar(500) DEFAULT NULL COMMENT '加入项目3',
  `join_image_url` varchar(500) DEFAULT NULL COMMENT '加入我们图片URL',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='首页信息表';

-- 插入示例数据
INSERT INTO `home_info` (
  `hero_title_1`, `hero_subtitle_1`, `hero_title_2`, `hero_subtitle_2`, `hero_image_url`,
  `sdgs_description_1`, `sdgs_description_2`,
  `expo_title`, `expo_subtitle`, `expo_image_url`,
  `alliance_title`, `alliance_description_1`, `alliance_description_2`,
  `data_title`, `data_image_url`,
  `join_title`, `join_item_1`, `join_item_2`, `join_item_3`, `join_image_url`
) VALUES (
  '走进世界', '与世博全球青年一起', '走进世博', '共创可持续发展未来', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/19/home_hero_image.png',
  '联合国可持续发展目标（Sustainable Development Goals，SDGs）是联合国为应对全球挑战而制定的一系列目标，旨在从2015年到2030年间以综合方式彻底解决社会、经济和环境三个维度的发展问题，转向可持续发展道路。',
  '这些目标旨在推动全球发展，实现更美好、更可持续的未来，涵盖了贫困、饥饿、健康、教育、性别平等、水资源、能源、经济增长、基础设施、不平等、城市发展、消费模式、气候变化、海洋与陆地生态系统、和平与包容性社会等多个领域。',
  '2025大阪世博会情况及介绍', '"闪耀生命的未来社会的设计"', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/19/home_expo_background.png',
  '世博全球青年联盟',
  '世博全球青年联盟（World Expo Global Young Alliance）在迪拜世博会期间，由世博全球青年SDGs峰会组委会与SDGsVision 2050发起，在迪拜世博会中华文化馆与中华文化促进会支持下成立，为激发全球青年为实现可持续发展目标SDGs（也称为全球目标和2030年议程）做出贡献的行动。',
  '世博全球青年联盟将连接全球的创意机构和人才，汇聚全球产业界、政界、学界、商界力量助力落实《联合国2030年可持续发展议程》，促进社会进步、环境保护、经济建设可持续发展。',
  '相关行动数据', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/19/home_data_chart.png',
  '加入我们', '要成为世博全球青年联盟的成员，关注公众号SDGs Vision 2050', '携手全球产业界、政界、学界、商界力量', '邀您共同探讨与行动！', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/19/home_join_us.png'
);

-- 确认数据插入成功
SELECT * FROM home_info;
