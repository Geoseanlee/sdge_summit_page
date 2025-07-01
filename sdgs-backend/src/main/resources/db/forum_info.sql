-- 设置数据库字符集为utf8mb4，确保支持中文和特殊字符
ALTER DATABASE sdgs_db CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

SET NAMES utf8mb4;

-- 论坛介绍信息表
DROP TABLE IF EXISTS `forum_info`;
CREATE TABLE `forum_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '介绍内容（可包含HTML）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='论坛介绍信息表';

-- 插入示例数据
INSERT INTO `forum_info` (`title`, `content`) VALUES 
(
  '“世博全球青年共创科学与艺术可持续未来”',
  'p世界博览会（世博会）是由国家政府主办、多国和国际组织参与的全球最高级别展览平台，旨在通过展示各国的科技创新、文化成就和经济成果，促进全球合作与交流。其核心理念为理解、沟通、欢聚、合作，致力于增进各国人民之间的相互理解，推动全球可持续发展。p
p p
p世博会不仅是人类文明成就的展示舞台，更是探讨全球性挑战、寻求解决方案的重要平台，每一届都围绕特定主题展开，聚焦城市化、可持续发展、科技创新等重大问题。被誉为经济、科技与文化的奥林匹克，是人类文明成果的集中展示平台。p
p▪ 品牌&青年领袖板块：将展示创新与可持续实践，以及青年领袖板块汇聚全球影响力人物；br
▪ 未来教育板块：探讨未来人才培养，促进未来和可持续发展的专业与学术成果；br
▪ 艺术疗愈板块：将传统非物质文化遗产与现代科技相结合，通过创新的艺术表现形式，探索艺术在心理健康和情感疗愈中的独特作用；br
▪ 女性板块：将聚焦女性在可持续发展中的关键角色，促进性别平等，赋能更多女性在多领域发挥领导力与影响力，成为变革推动者。br
▪ ESG板块：聚焦环境、社会和治理三大核心领域共同探索如何通过绿色技术、社会责任和透明治理，构建更具韧性和可持续的未来生态。p'
); 