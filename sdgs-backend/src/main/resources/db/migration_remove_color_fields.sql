-- 迁移脚本：移除颜色字段
-- 如果您之前创建的表包含颜色字段，请执行此脚本来移除它们

-- 检查并移除颜色字段
SET @sql = '';

-- 检查 date_color 字段是否存在并删除
SELECT COUNT(*) INTO @col_count 
FROM information_schema.COLUMNS 
WHERE TABLE_SCHEMA = DATABASE() 
  AND TABLE_NAME = 'summit_schedule' 
  AND COLUMN_NAME = 'date_color';

IF @col_count > 0 THEN
    SET @sql = 'ALTER TABLE summit_schedule DROP COLUMN date_color';
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END IF;

-- 检查 time_color 字段是否存在并删除
SELECT COUNT(*) INTO @col_count 
FROM information_schema.COLUMNS 
WHERE TABLE_SCHEMA = DATABASE() 
  AND TABLE_NAME = 'summit_schedule' 
  AND COLUMN_NAME = 'time_color';

IF @col_count > 0 THEN
    SET @sql = 'ALTER TABLE summit_schedule DROP COLUMN time_color';
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END IF;

-- 检查 event_color 字段是否存在并删除
SELECT COUNT(*) INTO @col_count 
FROM information_schema.COLUMNS 
WHERE TABLE_SCHEMA = DATABASE() 
  AND TABLE_NAME = 'summit_schedule' 
  AND COLUMN_NAME = 'event_color';

IF @col_count > 0 THEN
    SET @sql = 'ALTER TABLE summit_schedule DROP COLUMN event_color';
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END IF;

SELECT '颜色字段迁移完成！表格结构已更新。' AS message; 