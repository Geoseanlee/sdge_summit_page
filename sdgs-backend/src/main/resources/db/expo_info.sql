-- 世博会信息表
CREATE TABLE `expo_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(100) DEFAULT NULL COMMENT '主标题',
  `subtitle` varchar(200) DEFAULT NULL COMMENT '副标题',
  `description` text COMMENT '描述文字（包含多个段落，用换行符分隔）',
  `image_url` varchar(500) DEFAULT NULL COMMENT '图片OSS URL',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='世博会信息表';

-- 插入示例数据
INSERT INTO `expo_info` (`title`, `subtitle`, `description`, `image_url`) VALUES 
(
  '世博会', 
  '全球最高级的展览平台', 
  '世界博览会（世博会）是由国家政府主办、多国和国际组织参与的全球最高级别展览平台，旨在通过展示各国的科技创新、文化成就和经济成果，促进全球合作与交流。其核心理念为"理解、沟通、欢聚、合作"，致力于增进各国人民之间的相互理解，推动全球可持续发展。

世博会不仅是人类文明成就的展示舞台，更是探讨全球性挑战、寻求解决方案的重要平台，每一届都围绕特定主题展开，聚焦城市化、可持续发展、科技创新等重大问题。被誉为"经济、科技与文化的奥林匹克"，是人类文明成果的集中展示平台。', 
  NULL
); 