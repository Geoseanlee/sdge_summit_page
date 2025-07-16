-- 新闻资讯表
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(200) NOT NULL COMMENT '新闻标题',
  `subtitle` varchar(500) DEFAULT NULL COMMENT '副标题',
  `summary` text COMMENT '新闻简介',
  `content` longtext COMMENT '新闻正文内容（富文本）',
  `cover_image_url` varchar(500) DEFAULT NULL COMMENT '封面图片URL',
  `category` varchar(50) DEFAULT NULL COMMENT '新闻分类',
  `tags` varchar(200) DEFAULT NULL COMMENT '标签（逗号分隔）',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `source` varchar(100) DEFAULT NULL COMMENT '来源',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `status` tinyint(1) DEFAULT 1 COMMENT '状态（0:草稿 1:已发布）',
  `view_count` int(11) DEFAULT 0 COMMENT '阅读量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`),
  KEY `idx_publish_time` (`publish_time`),
  KEY `idx_category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='新闻资讯表';

-- 插入示例数据
INSERT INTO `news` (`title`, `subtitle`, `summary`, `content`, `cover_image_url`, `category`, `tags`, `author`, `source`, `publish_time`, `status`) VALUES
('联合国发布2024年可持续发展目标进展报告', '全球合作迎来新机遇', '报告显示全球在减贫、教育、健康等领域取得显著进展，但气候变化仍是最大挑战。', '<h2>背景介绍</h2><p>联合国在纽约总部正式发布了《2024年可持续发展目标进展报告》，这是自2015年通过《2030年可持续发展议程》以来的第九次年度评估报告。</p><h2>主要内容</h2><p>报告重点强调了以下几个方面的重要进展：</p><h3>1. 贫困减少成效显著</h3><p>全球极端贫困人口比例从2015年的10.1%下降至2023年的8.4%，超过1.3亿人摆脱了极端贫困。</p>', 'https://images.unsplash.com/photo-1573164713714-d95e436ab8d6?w=800&h=400&fit=crop', '政策发布', 'SDG报告,全球进展,联合国', '联合国新闻中心', '联合国官网', '2024-03-15 10:30:00', 1),
('全球气候峰会达成新的减排协议', '195个国家承诺减少碳排放', '195个国家承诺在2030年前将碳排放量减少50%，加速向清洁能源转型。', '<h2>峰会成果</h2><p>本次全球气候峰会取得了重要突破，195个国家一致通过了新的减排协议。</p><h2>主要承诺</h2><p>各国承诺在2030年前将碳排放量减少50%，并加速向清洁能源转型。</p>', 'https://images.unsplash.com/photo-1569163139394-de44cb3c0db9?w=800&h=400&fit=crop', '气候行动', '气候变化,减排,清洁能源', 'Climate News', 'Climate News', '2024-03-10 14:20:00', 1),
('数字教育平台助力全球教育公平', '新兴技术推动教育创新', '新兴的在线教育技术正在帮助发展中国家的儿童获得优质教育资源。', '<h2>技术创新</h2><p>数字教育平台通过创新技术，为全球教育公平做出了重要贡献。</p><h2>影响范围</h2><p>目前已有超过100个国家的学生通过数字教育平台获得了优质教育资源。</p>', 'https://images.unsplash.com/photo-1497486751825-1233686d5d80?w=800&h=400&fit=crop', '教育发展', '数字教育,教育公平,技术创新', 'Education Today', 'Education Today', '2024-03-08 09:15:00', 1),
('全球疫苗接种计划显著改善儿童健康', '世界卫生组织最新报告', '世界卫生组织报告显示，全球儿童疫苗接种率达到历史新高。', '<h2>疫苗接种成果</h2><p>世界卫生组织最新报告显示，全球儿童疫苗接种率达到历史新高，为儿童健康提供了重要保障。</p><h2>数据统计</h2><p>目前全球儿童疫苗接种率已达到95%以上，有效预防了多种传染病的发生。</p>', 'https://images.unsplash.com/photo-1576091160399-112ba8d25d1f?w=800&h=400&fit=crop', '健康福祉', '疫苗接种,儿童健康,WHO', 'WHO', 'WHO', '2024-03-05 16:45:00', 1),
('绿色金融推动可持续经济发展', 'ESG投资成为新趋势', '越来越多的金融机构将ESG因素纳入投资决策，推动经济向可持续方向发展。', '<h2>绿色金融发展</h2><p>绿色金融作为推动可持续经济发展的重要工具，正在全球范围内快速发展。</p><h2>ESG投资</h2><p>越来越多的金融机构将ESG因素纳入投资决策，推动经济向可持续方向发展。</p>', 'https://images.unsplash.com/photo-1611974789855-9c2a0a7236a3?w=800&h=400&fit=crop', '经济发展', '绿色金融,ESG,可持续投资', 'Finance Weekly', 'Finance Weekly', '2024-03-01 11:20:00', 1); 