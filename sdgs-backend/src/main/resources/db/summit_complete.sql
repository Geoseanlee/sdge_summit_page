-- ====================================
-- 峰会相关表完整SQL脚本
-- ====================================

-- 峰会议程表
DROP TABLE IF EXISTS `summit_schedule`;
CREATE TABLE `summit_schedule` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `date` VARCHAR(20) DEFAULT NULL COMMENT '日期，如8月24日',
  `time` VARCHAR(50) DEFAULT NULL COMMENT '时间，如17:00前',
  `event` TEXT DEFAULT NULL COMMENT '事件内容',
  `date_color` VARCHAR(10) DEFAULT '#bae1f6' COMMENT '日期列背景色',
  `time_color` VARCHAR(10) DEFAULT '#d7f1fc' COMMENT '时间列背景色',
  `event_color` VARCHAR(10) DEFAULT '#bae1f6' COMMENT '事件列背景色',
  `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='峰会议程表';

-- 峰会嘉宾分类表
DROP TABLE IF EXISTS `summit_guest_category`;
CREATE TABLE `summit_guest_category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` VARCHAR(500) NOT NULL COMMENT '分类标题',
  `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='峰会嘉宾分类表';

-- 峰会嘉宾信息表
DROP TABLE IF EXISTS `summit_guest`;
CREATE TABLE `summit_guest` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `category_id` BIGINT NOT NULL COMMENT '分类ID',
  `guest_name` TEXT NOT NULL COMMENT '嘉宾名称或描述',
  `guest_image` VARCHAR(500) DEFAULT NULL COMMENT '嘉宾头像OSS URL',
  `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='峰会嘉宾信息表';

-- ====================================
-- 插入峰会议程数据
-- ====================================
INSERT INTO `summit_schedule` (`date`, `time`, `event`, `date_color`, `time_color`, `event_color`, `sort_order`) VALUES
('8月24日', '17:00前', '入住酒店&领取参展证件', '#bae1f6', '#d7f1fc', '#bae1f6', 1),
('', '18:00-20:00', '欢迎晚宴', '#bae1f6', '#d7f1fc', '#bae1f6', 2),
('8月25日', '', '峰会开幕致辞', '#d7f1fc', '#ffffff', '#e1f3ff', 3),
('', '上午', '主题分享', '#d7f1fc', '#ffffff', '#e1f3ff', 4),
('', '', '圆桌对话', '#d7f1fc', '#ffffff', '#bae1f6', 5),
('', '12:00-13:00', '午餐', '#d7f1fc', '#ffffff', '#bae1f6', 6),
('', '', '', '#d7f1fc', '#ffffff', '#e1f3ff', 7),
('', '下午', '各板块分论坛', '#d7f1fc', '#ffffff', '#e1f3ff', 8),
('', '', '', '#d7f1fc', '#ffffff', '#e1f3ff', 9),
('', '18:00-21:00', '晚宴与颁奖酒会', '#d7f1fc', '#ffffff', '#bae1f6', 10),
('8月26日', '上午', '参访大阪吹田德川分病院、NEC日本电气株式会社、无印良品社、日本皇家医疗俱乐部、丰田集团（可选）', '#bae1f6', '#d7f1fc', '#e1f3ff', 11),
('', '12:00-14:00', '午餐', '#bae1f6', '#d7f1fc', '#bae1f6', 12),
('', '下午', '大咖分享与交流', '#bae1f6', '#d7f1fc', '#e1f3ff', 13);

-- ====================================
-- 插入峰会嘉宾分类数据
-- ====================================
INSERT INTO `summit_guest_category` (`title`, `sort_order`) VALUES
('1. 大阪世博会主办方及政府代表——世博会组委会领导', 1),
('2. 国际组织及机构代表', 2),
('3. 世界500强及商界领袖', 3),
('4. 学术及研究机构代表', 4),
('5. 体育界及奥运冠军', 5),
('6. 文化、艺术及娱乐界代表', 6),
('7. 企业家及行业合作伙伴', 7);

-- ====================================
-- 插入峰会嘉宾数据
-- ====================================
INSERT INTO `summit_guest` (`category_id`, `guest_name`, `sort_order`) VALUES
-- 分类1的嘉宾
(1, '世博会组委会领导（主席、副主席、会长、副会长、秘书长）', 1),
(1, '日本政府官员（国家级、部级及相关部门领导、如经济产业省、文化厅、文部科学省等）', 2),
(1, '中国政府相关部门官员（科技、文化、教育、商务等领域）', 3),
(1, '领事馆/大使馆', 4),

-- 分类2的嘉宾
(2, '联合国教科文组织（UNESCO）官员', 1),
(2, '经合组织（OECD）、世界经济论坛（WEF）、国际教育协会（IEA）等国际组织代表', 2),
(2, '全球科技与创新机构代表（如IEEE、世界人工智能大会等）', 3),

-- 分类3的嘉宾
(3, '全球及亚洲500强企业高管（总裁、副总裁、董事等）', 1),
(3, '重点邀请企业：本田（Honda）、无印良品（MUJI）、丰田（Toyota）、', 2),
(3, '松下（Panasonic）、索尼（Sony）、软银（SoftBank）等；', 3),
(3, '中国代表企业：华为、阿里巴巴、腾讯、比亚迪、字节跳动等', 4),
(3, '中日商会及企业协会领导', 5),
(3, '日本经济团体联合会（经团联）代表', 6),
(3, '中国贸促会、日本商工会议所等机构领导', 7),
(3, '中日商会会长', 8),

-- 分类4的嘉宾
(4, '国际顶尖高校教授及学者（哈佛、斯坦福、牛津、东京大学、北京大学、清华大学等）', 1),
(4, '科技及未来趋势专家（人工智能、绿色能源、生命科学等领域）', 2),
(4, '教育及文化研究专家', 3),

-- 分类5的嘉宾
(5, '奥运会、亚运会、世锦赛冠军（跨项目，如体操、游泳、田径等）', 1),
(5, '日本及中国的体育明星及体育组织负责人', 2),

-- 分类6的嘉宾
(6, '知名艺人及娱乐明星（电影、音乐、戏剧等）', 1),
(6, '社交媒体及数字内容创作者（各领域KOL、大V、知名网红）', 2),
(6, '文化学者及艺术家（书画、摄影、雕塑、设计、动画等领域）', 3),
(6, '知名IP及品牌代表（动漫、游戏、文学IP等）', 4),

-- 分类7的嘉宾
(7, '上午论坛：大型企业代表（跨国企业、行业龙头企业高管）', 1),
(7, '下午分论坛：中小型领域企业代表（创新型企业、创业公司、独角兽企业创始人）', 2),
(7, '投资及金融机构代表（风险投资、私募基金、银行高管）', 3);

-- ====================================
-- 执行完成提示
-- ====================================
SELECT '峰会相关表和数据创建完成！' AS message; 