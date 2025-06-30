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
('SDGs线上论坛第七期：', '世博青年共创艺术与科技可持续未来', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/0e2fbcc474ed4cbcb04c5aa4b5a33715.jpg', '艺术与科技,ESG板块'),
('SDGs线上论坛第六期：', '青年领袖与未来创新', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/136a38177ae74152b900639127a916fa.jpg', '青年领袖,ESG板块'),
('SDGs线上论坛第五期：', '女性力量与可持续未来', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/7ff44278063245608a9a20c9de817bbf.jpg', '女性力量,ESG板块'),
('SDGs线上论坛第四期：', '育见可持续未来：AI时代的学习力与全球素养', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/bff09c4b0e8a498c8f15d9279b2bea8d.jpg', '未来教育,ESG板块');