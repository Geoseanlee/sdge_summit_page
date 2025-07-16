-- 清空并重新创建世博会背景信息表
DROP TABLE IF EXISTS expo_background;

-- 创建世博会背景信息表
CREATE TABLE expo_background (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 section_type VARCHAR(50) NOT NULL COMMENT '章节类型',
                                 title VARCHAR(500) COMMENT '标题',
                                 content TEXT COMMENT '内容',
                                 image_url VARCHAR(500) COMMENT '图片URL',
                                 sort_order INT DEFAULT 0 COMMENT '排序',
                                 created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 UNIQUE KEY uk_section_type (section_type)
);

-- 插入初始数据 - 根据实际页面结构
INSERT INTO expo_background (section_type, title, content, image_url, sort_order) VALUES
    -- 第一部分主标题
    ('part1_main_title', '以青年之力，搭建中国与世界的可持续桥梁', NULL, NULL, 1),

    -- 第一部分的三个小section
    ('part1_section1_title', '1. 联合国SDGs与未来契约：共绘人类可持续未来蓝图', NULL, NULL, 2),
    ('part1_section1_content', '联合国可持续发展目标（SDGs）是全球共同的行动纲领，旨在通过17个目标和169个具体指标，推动全球在2030年前实现可持续发展。这些目标涵盖了消除贫困、优质教育、性别平等、清洁能源、气候行动等各个领域，为构建更加公平、包容和可持续的世界提供了清晰的路线图。', NULL, NULL, 3),
    ('part1_section1_image', NULL, NULL, '/images/sdgs-goals.jpg', 4),

    ('part1_section2_title', '2. 世界博览会：World Expo 全球思想与创新的交汇舞台', NULL, NULL, 5),
    ('part1_section2_content', '世界博览会作为全球最重要的国际交流平台，自1851年首届伦敦世博会以来，一直致力于展示人类文明的最新成果和未来发展方向。2025年大阪关西世博会以"设计未来社会的生活方式"为主题，将聚焦可持续发展、数字化转型和社会创新等关键议题。', NULL, NULL, 6),
    ('part1_section2_image', NULL, NULL, '/images/expo-logo.jpg', 7),

    ('part1_section3_title', '3. 青年领袖：Youth Leaders 变革的核心力量，引领可持续未来', NULL, NULL, 8),
    ('part1_section3_content', '青年是推动可持续发展的重要力量，他们具有创新思维、敢于挑战现状的勇气和对未来的深切关注。通过教育、创业、科技创新和社会参与，青年领袖正在各个领域发挥着关键作用，成为连接不同文化、推动国际合作的重要桥梁。', NULL, NULL, 9),
    ('part1_section3_image', NULL, NULL, '/images/youth-leaders.jpg', 10),

    -- 第二部分
    ('part2_main_title', '国家政策倡议与国际合作', NULL, NULL, 11),
    ('part2_subtitle', '【知行合一，大国担当】<br />SDGs中国力量', NULL, NULL, 12),
    ('part2_content', '中国积极响应联合国可持续发展目标，制定了一系列政策措施，推动国内可持续发展的同时，也为全球可持续发展贡献中国智慧和中国方案。通过"一带一路"倡议、南南合作等平台，中国与世界各国共同推进可持续发展事业。', NULL, NULL, 13);