-- 创建一个通用的页面配置表
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