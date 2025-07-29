
DROP TABLE IF EXISTS `page_config`;
CREATE TABLE `page_config` (
                               `id` INT AUTO_INCREMENT PRIMARY KEY,
                               `page_key` VARCHAR(50) NOT NULL UNIQUE COMMENT '页面唯一标识，如 about_more',
                               `config_data` JSON NOT NULL COMMENT '存储页面配置的JSON对象',
                               `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通用页面配置表';

-- 为“关于我们-更多”页面插入初始配置
INSERT INTO `page_config` (`page_key`, `config_data`) VALUES
    ('about_more', JSON_OBJECT(
            'title', '世博全球青年联盟使命与愿景',
            'finalParagraph', '世博全球青年联盟在联合国SDGs愿景、未来契约的倡议、世博会平台与青年力量的深度交汇中应运而生，致力于通过青年视角与跨界共创，为全球带来可持续发展的中国方案与文化对话。'
                   ));


--
-- Table structure for table `about_more_section`
-- Description: Stores the dynamic content sections for the "About Us - More" page.
--

-- Drop the table if it already exists to avoid errors on re-import
DROP TABLE IF EXISTS `about_more_section`;

-- Create the new table structure
CREATE TABLE `about_more_section` (
                                      `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                      `title` VARCHAR(255) NOT NULL COMMENT '区块标题',
                                      `content` TEXT COMMENT '区块的描述内容',
                                      `sort_order` INT DEFAULT 0 COMMENT '排序值，越小越靠前',
                                      `is_published` BOOLEAN DEFAULT TRUE COMMENT '是否发布，true为发布',
                                      `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='关于我们-更多页面的动态区块';

--
-- Inserting initial data for the `about_more_section` table
-- Description: These are the three initial sections based on the design mockup.
--

INSERT INTO `about_more_section` (`title`, `content`, `sort_order`) VALUES
                                                                        ('1. 中国品牌，走向世界', '在全球可持续浪潮中，中国新兴品牌以更强的社会责任与创新能力走向世界舞台。WEGYA支持具有可持续精神与文化价值的中国企业，通过世博平台开展国际展示、交流与合作。通过国际传播、展陈互动、青年对话与产品体验等多元形式，助力“中国品牌”提升全球认知度，打造兼具温度、价值与文化信赖的国际品牌形象。', 1),
                                                                        ('2. 中国文化，世界传播', '文化是国家形象的根基。WEGYA聚焦文化多样性，非遗创新与艺术科技科技相结合，推动中华文明在国际语境下的创造性转化与当代表达。通过非遗展演、文化对话、艺术展览等形式，让世界听见来自中国的文化声音，构建多元共生的全球文化交流平台。同时响应，联合国教科文组织《2005年保护和促进文化表现形式多样性公约》的精神，展现中国文化在全球可持续发展议题新中的独特价值。', 2),
                                                                        ('3. 中国青年，世界风采', 'WEGYA聚焦青年责任、可持续领导力与全球行动力的培养，遴选具有创新精神与国际视野的中国青年代表参与全球对话与议程。通过主题峰会、青年圆桌、国际交流、社群联动等机制，展现新时代中国青年在环境保护、科技创新、社会企业、文化传播等多个领域的全球影响力，塑造有担当、有温度、有想象力的中国青年形象。', 3);