-- 清空并重新创建世博会背景信息表
DROP TABLE IF EXISTS expo_background;

-- 创建世博会背景信息表
CREATE TABLE expo_background (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 section_type VARCHAR(50) NOT NULL COMMENT '章节类型',
                                 title TEXT COMMENT '标题',
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
    ('part1_section1_content', '联合国于2015年通过了2030年可持续发展议程，提出了涵盖减贫、教育、气候、环境、性别、伙伴关系等领域的17个可持续发展目标（SDGs），构成了联合国到2030年为实现可持续发展目标而采取的行动纲领。这些目标涵盖了消除贫困、优质教育、性别平等、清洁能源、气候行动等各个领域，并提出了169个具体指标和232个全球指标体系，为"未来契约"（Pact for the Future）制定了蓝图，通过2024年联合国的《未来峰会》向全球公布。', NULL, NULL, 3),
    ('part1_section1_image', NULL, NULL, '/images/sdgs-goals.jpg', 4),

    ('part1_section2_title', '2. 世界博览会：World Expo 全球思想与创新的交汇舞台', NULL, NULL, 5),
    ('part1_section2_content', '世界博览会（World Expo）自1851年伦敦以来，已成为思想与创新的全球性交汇平台。文化保护与人道主义为密导原则，每一届博览会都是一次盛典。我们从亚洲、非洲、拉丁美洲的多元文明中汲取智慧，"共创未来"。2025年大阪关西世博会将于2025年4月13日至10月13日在日本大阪梦洲岛举办，人类主题为"设计未来社会的生活方式"（Designing Future Society for Our Lives）。届时，190多个国家和国际组织、企业将齐聚大阪，文化交流与日本奥地利合作的新形式。', NULL, NULL, 6),
    ('part1_section2_image', NULL, NULL, '/images/expo-logo.jpg', 7),

    ('part1_section3_title', '3. 青年领袖：Youth Leaders 变革的核心力量，引领可持续未来', NULL, NULL, 8),
    ('part1_section3_content', '联合国将青年定义为15岁至24岁的群体，而这个年龄段的人群占全球总人口的"青年领袖"。青年在可持续发展中扮演关键角色，他们的声音、影响力和行动对实现可持续发展目标至关重要。通过教育、科技、创业和社会参与，培养青年与全球领导力工作坊、平台，提供和本次项目的核心内容，未来全球青年与可持续发展使命对话。', NULL, NULL, 9),
    ('part1_section3_image', NULL, NULL, '/images/youth-leaders.jpg', 10),

    -- 第二部分
    ('part2_main_title', '国家政策倡议与国际合作', NULL, NULL, 11),
    ('part2_subtitle', '【知行合一，大国担当】
SDGs中国力量', NULL, NULL, 12),
    ('part2_content', '中国领导人始终将可持续发展作为国家战略的重要组成部分，习近平主席在不同国际场合多次强调中国推动全球可持续发展的决心与行动。

• 全球发展倡议：习近平主席提出"全球发展倡议"，聚焦减贫、粮食安全、清洁能源等八大领域，已获100多个国家支持，实施1100多个合作项目。例如，中国通过南南合作基金为发展中国家提供气候援助，累计培训超1万人次环保人才。

• 支持联合国倡议：中国承诺全力支持实现联合国2030年可持续发展目标，将南南合作援助基金升级为"全球发展和南南合作基金"，并加大对联合国和平与发展基金的投入，助力发展中国家能力建设。

• 多边平台推动：在G20、APEC等国际会议上，中国倡导"共商共建共享"原则，推动建立公正合理的全球治理体系。2024年G20峰会期间，习近平提出构建"绿色金融"，促进清洁能源技术共享。

• 中国政府重视生态实践与绿色转型，国内生态治理：森林保护、能源转型、生物多样性等取得显著成效。

• 同时推动国际绿色合作："一带一路"绿色项目：哈萨克斯坦风电项目年减排65万吨CO₂，巴西美丽山特高压输电工程保护亚马孙雨林生态。

• 技术输出：为全球提供60%风电设备、70%光伏组件，降低可再生能源成本超60%。

• 中国承诺2030年前碳达峰、2060年前碳中和，通过碳市场建设、绿色技术创新（如碳捕集技术）及全民低碳行动持续推进目标。国际社会评价中国"以行动诠释绿色增长与生态保护并重共赢"，为全球南方国家提供可借鉴经验。', NULL, NULL, 13);