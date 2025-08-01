CREATE DATABASE IF NOT EXISTS sdgs_db CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE sdgs_db;

DROP TABLE IF EXISTS join_us_content;

CREATE TABLE join_us_content (
                                 id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
                                 category VARCHAR(50) NOT NULL COMMENT '内容分类: banner / project / member / food_award',
                                 title VARCHAR(255) NOT NULL COMMENT '标题',
                                 description TEXT COMMENT '描述信息',
                                 image_url VARCHAR(500) COMMENT '图片地址',
                                 link_url VARCHAR(500) COMMENT '跳转链接（如果有）',
                                 sort_order INT DEFAULT 0 COMMENT '排序字段（数字越小越靠前）',
                                 create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='加入我们页面的动态内容表';

-- 插入初始数据
INSERT INTO join_us_content (category, title, description, image_url, link_url, sort_order, create_time) VALUES
-- banner
('banner', 'JoinUs Banner', '加入我们页面的顶部 Banner', '', NULL, 1, NOW()),

-- 项目岗位信息
('project', '世博全球青年菁英计划正式开启', '2025大阪世博会“构建生命共生的未来”正如火如荼的进行，世博全球青年联盟联手联合国教科文8月登陆2025年大阪世博会官方馆Team expo，开启青年菁英人才计划。', '', 'https://ucnwrvp6mqsb.feishu.cn/share/base/form/shrcnCNgA0ZLzRCA9qaCn7nRD98', 1, NOW()),
('project', '世博全球青年联盟理事会正式启动', '全球舞台，中国担当！可持续发展不仅是全球共识，更是我们每个人的使命。我们邀请更多有责任、有担当的中国青年与企业深度参与世博会全球议题的策划与执行，在气候变化、科技普惠、文化共融等国际议程中提出中国方案，展现青年力量。', '', 'https://ucnwrvp6mqsb.feishu.cn/share/base/form/shrcnhDLESjU8HKueuNSmDuNdeb', 2, NOW()),
('project', '《世博杰出青年TOP100》报名进行中', '通过百亿级创新基金、区块链协作平台及SDG导师计划，系统性推动青年成为可持续发展变革的主力。峰会将汇聚全球顶尖青年领袖、创新先锋与行业巨擘，探讨科技、艺术与可持续发展的无限可能，正式开启《2025世博杰出青年Top100》榜单评选，向世界展现中国青年的创新力量！', '', 'https://ucnwrvp6mqsb.feishu.cn/share/base/form/shrcntaaXSoZ4bV9EQDUBjNvwKh', 3, NOW()),
('project', '《世博青年大学生领袖TOP100》报名进行中', '当世界聚焦2025大阪世博会“构建生命共生的未来”，世博全球青年联盟联手联合国教科文登陆2025年大阪世博会官方馆Team expo，开启2025世博青年创新与职业大赛暨《世博青年大学生领袖TOP100》计划。', '', 'https://ucnwrvp6mqsb.feishu.cn/share/base/form/shrcnLEBw3iZTBkWIv4WX4j6rEd', 4, NOW()),

-- 入选名单图片
('member', '世博杰出青年', '入选名单图片展示', '', NULL, 1, NOW()),
('member', '世博青年大学生', '入选名单图片展示', '', NULL, 2, NOW()),
('member', '世博小使者', '入选名单图片展示', '', NULL, 3, NOW()),

-- 食品奖项
('food_award', '可持续食品品牌TOP10', NULL, '', NULL, 1, NOW()),
('food_award', '青年创新食品项目TOP10', NULL, '', NULL, 2, NOW()),
('food_award', '食品科技创新榜TOP10', NULL, '', NULL, 3, NOW()),
('food_award', '可持续食材榜单TOP10', NULL, '', NULL, 4, NOW()),
('food_award', '地方可持续风味榜TOP10', NULL, '', NULL, 5, NOW());

-- 动态标题初始化
INSERT INTO joinus (category, title) VALUES
                                         ('projectTitle', '项目岗位信息'),
                                         ('memberTitle', '入选名单'),

