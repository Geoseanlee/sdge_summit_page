-- 删除现有表（如果存在）
DROP TABLE IF EXISTS `expo_info`;

-- 世博会信息表
CREATE TABLE `expo_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(100) DEFAULT NULL COMMENT '主标题',
  `subtitle` varchar(200) DEFAULT NULL COMMENT '副标题',
  `description` text COMMENT '描述文字（包含多个段落，用换行符分隔）',
  `image_url` varchar(500) DEFAULT NULL COMMENT '图片OSS URL',
  `card_title` varchar(100) DEFAULT NULL COMMENT '大卡片主标题',
  `card_subtitle` varchar(200) DEFAULT NULL COMMENT '大卡片副标题', 
  `card_description` text DEFAULT NULL COMMENT '大卡片描述内容',
  `card_image_url` varchar(500) DEFAULT NULL COMMENT '大卡片左侧图片OSS URL',
  `section_title_1` varchar(100) DEFAULT NULL COMMENT '第一个section标题',
  `section_image_url_1` varchar(500) DEFAULT NULL COMMENT '第一个section图片OSS URL',
  `section_title_2` varchar(100) DEFAULT NULL COMMENT '第二个section标题',
  `section_image_url_2` varchar(500) DEFAULT NULL COMMENT '第二个section图片OSS URL',
  `section_title_3` varchar(100) DEFAULT NULL COMMENT '第三个section标题',
  `section_image_url_3` varchar(500) DEFAULT NULL COMMENT '第三个section图片OSS URL',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='世博会信息表';

-- 插入示例数据
INSERT INTO `expo_info` (`title`, `subtitle`, `description`, `image_url`, `card_title`, `card_subtitle`, `card_description`, `card_image_url`, `section_title_1`, `section_image_url_1`, `section_title_2`, `section_image_url_2`, `section_title_3`, `section_image_url_3`) VALUES 
(
  '世博会', 
  '全球最高级的展览平台', 
  '世界博览会（世博会）是由国家政府主办、多国和国际组织参与的全球最高级别展览平台，旨在通过展示各国的科技创新、文化成就和经济成果，促进全球合作与交流。其核心理念为"理解、沟通、欢聚、合作"，致力于增进各国人民之间的相互理解，推动全球可持续发展。

世博会不仅是人类文明成就的展示舞台，更是探讨全球性挑战、寻求解决方案的重要平台，每一届都围绕特定主题展开，聚焦城市化、可持续发展、科技创新等重大问题。被誉为"经济、科技与文化的奥林匹克"，是人类文明成果的集中展示平台。',
  'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/06/9203a9663c514a7fb4a5ad4f9556e7c9.png',
  '2025大阪世博会',
  '"闪耀生命的未来社会的设计"',
  '2025年大阪世博会将于2025年4月13日至10月13日在日本大阪市梦洲举行，主题为"闪耀生命的未来社会的设计"（Designing Future Society for Our Lives），强调科技创新、医疗健康和可持续发展，致力于为人类未来社会提供解决方案。',
  NULL,
  '世博会影响力概览',
  NULL,
  '一切始于世博',
  'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/06/e3cdc32ce7904f5c9156fa96f76d4ca9.png',
  '往届世博会嘉宾代表',
  'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/06/f8781c9c1d2942d589feca7cc46436c0.png'
);