SET NAMES utf8mb4;

DROP TABLE IF EXISTS forum;

CREATE TABLE `forum` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '会议标题',
  `description` text COMMENT '会议描述',
  `image_url` varchar(255) DEFAULT NULL COMMENT '会议图片URL',
  `tag` varchar(255) DEFAULT NULL COMMENT '会议标签（可多个，逗号分隔）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO forum (title, description, image_url, tag)
VALUES
('品牌&青年领袖板块会议,未来教育板块会议', '聚焦青年创新与可持续实践，汇聚全球影响力人物。', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/0e2fbcc474ed4cbcb04c5aa4b5a33715.jpg', '品牌&青年领袖板块'),
('未来教育板块会议', '探讨未来人才培养，促进可持续发展的专业与学术成果。', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/136a38177ae74152b900639127a916fa.jpg', '未来教育板块'),
('艺术疗愈板块会议', '创新艺术与科技结合，探索艺术在心理健康中的独特作用。', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/7ff44278063245608a9a20c9de817bbf.jpg', '艺术疗愈板块'),
('女性板块会议', '聚焦女性在可持续发展中的关键角色，赋能女性领导力。', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/bff09c4b0e8a498c8f15d9279b2bea8d.jpg', '女性板块'),
('ESG板块会议', '聚焦环境、社会和治理三大领域的绿色技术与责任。', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/0e2fbcc474ed4cbcb04c5aa4b5a33715.jpg', 'ESG板块');