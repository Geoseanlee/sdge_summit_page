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
('SDGs线上论坛第四期：', '育见可持续未来：AI时代的学习力与全球素养', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/bff09c4b0e8a498c8f15d9279b2bea8d.jpg', '未来教育,ESG板块'),
('SDGs线上论坛第三期：', '可持续发展与全球治理', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/0e2fbcc474ed4cbcb04c5aa4b5a33715.jpg', '全球治理'),
('SDGs线上论坛第二期：', '数字经济与创新发展', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/136a38177ae74152b900639127a916fa.jpg', '数字经济,创新'),
('SDGs线上论坛第一期：', '绿色金融与可持续投资', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/7ff44278063245608a9a20c9de817bbf.jpg', '绿色金融,投资'),
('SDGs青年论坛：', '青年力量与社会责任', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/bff09c4b0e8a498c8f15d9279b2bea8d.jpg', '青年,社会责任'),
('SDGs女性论坛：', '女性创新与领导力', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/0e2fbcc474ed4cbcb04c5aa4b5a33715.jpg', '女性,创新'),
('SDGs教育论坛：', '未来教育与全球素养', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/136a38177ae74152b900639127a916fa.jpg', '教育,全球素养'),
('SDGs艺术论坛：', '艺术与社会变革', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/7ff44278063245608a9a20c9de817bbf.jpg', '艺术,社会变革'),
('SDGs可持续论坛：', '可持续城市与社区', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/bff09c4b0e8a498c8f15d9279b2bea8d.jpg', '可持续,城市'),
('SDGs治理论坛：', '全球治理与合作', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/0e2fbcc474ed4cbcb04c5aa4b5a33715.jpg', '治理,合作'),
('SDGs创新论坛：', '创新科技与未来趋势', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/136a38177ae74152b900639127a916fa.jpg', '创新,科技'),
('SDGs绿色论坛：', '绿色能源与环境保护', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/7ff44278063245608a9a20c9de817bbf.jpg', '绿色能源,环境'),
('SDGs医疗论坛：', '健康中国与医疗创新', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/bff09c4b0e8a498c8f15d9279b2bea8d.jpg', '健康,医疗'),
('SDGs乡村论坛：', '乡村振兴与可持续发展', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/0e2fbcc474ed4cbcb04c5aa4b5a33715.jpg', '乡村,振兴'),
('SDGs企业论坛：', '企业社会责任与可持续战略', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/136a38177ae74152b900639127a916fa.jpg', '企业,社会责任'),
('SDGs国际论坛：', '国际合作与发展', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/7ff44278063245608a9a20c9de817bbf.jpg', '国际,合作'),
('SDGs未来论坛：', '未来趋势与青年机遇', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/bff09c4b0e8a498c8f15d9279b2bea8d.jpg', '未来,青年'),
('SDGs气候论坛：', '气候变化与绿色转型', 'https://sdgs-images-shanghai.oss-cn-shanghai.aliyuncs.com/images/2025/07/01/bff09c4b0e8a498c8f15d9279b2bea8d.jpg', '气候,绿色转型');