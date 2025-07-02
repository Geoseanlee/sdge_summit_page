# 峰会模块前后端架构说明

## 📋 概述

峰会模块已重构为完全的前后端分离架构，遵循以下设计原则：
- **数据库只存储业务数据**：不包含UI样式信息
- **前端完全控制样式**：所有颜色、布局由CSS管理
- **后端专注数据逻辑**：提供纯净的JSON API

## 🗄️ 数据库设计

### 峰会议程表 (summit_schedule)
```sql
CREATE TABLE `summit_schedule` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `date` VARCHAR(20) DEFAULT NULL COMMENT '日期，如8月24日',
  `time` VARCHAR(50) DEFAULT NULL COMMENT '时间，如17:00前',
  `event` TEXT DEFAULT NULL COMMENT '事件内容',
  `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='峰会议程表';
```

## 🔗 API接口

### 获取峰会议程
- **接口地址**: `GET /api/summit/schedule`
- **返回格式**:
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "date": "8月24日",
      "time": "17:00前",
      "event": "入住酒店&领取参展证件",
      "sortOrder": 1
    }
  ]
}
```

### 获取峰会嘉宾信息
- **接口地址**: `GET /api/summit/guests`
- **返回格式**:
```json
{
  "code": 200,
  "message": "success", 
  "data": [
    {
      "id": 1,
      "title": "1. 大阪世博会主办方及政府代表——世博会组委会领导",
      "guests": [
        {
          "id": 1,
          "guestName": "世博会组委会领导（主席、副主席、会长、副会长、秘书长）",
          "guestImage": null
        }
      ]
    }
  ]
}
```

## 🎨 前端颜色逻辑

### 表格颜色规则

#### 1. 日期列（第一列）
- **交替颜色**: `#bae1f6` ↔ `#d7f1fc`
- **切换规则**: 每当遇到新的日期组时切换颜色

#### 2. 时间列（第二列）  
- **跟随日期列**:
  - 当日期列为 `#bae1f6` 时 → 时间列为 `#d7f1fc`
  - 当日期列为 `#d7f1fc` 时 → 时间列为 `#ffffff`

#### 3. 事件列（第三列）
- **复杂模式**: 根据具体日期组和组内位置确定
- **第1日期组（8月24日）**: 全部 `#bae1f6`
- **第2日期组（8月25日）**: 
  - 索引 0,1: `#e1f3ff`
  - 索引 2,3: `#bae1f6` 
  - 索引 4,5,6: `#e1f3ff`
  - 索引 7: `#bae1f6`
- **第3日期组（8月26日）**: `#e1f3ff` → `#bae1f6` → `#e1f3ff`
- **其他日期组**: 按日期组奇偶性交替

### 实现方式

```javascript
// 计算颜色逻辑
const getRowColors = (rowIndex) => {
    // 找到当前行所属的日期组和在组内的索引
    let currentDateGroupIndex = 0
    let indexInGroup = 0
    let currentGroupStartIndex = 0
    
    // 计算日期组索引和组内位置
    for (let i = 0; i <= rowIndex; i++) {
        const row = scheduleData.value[i]
        if (row?.date && row.date.trim() !== '') {
            if (i > 0) {
                let hasDateBefore = false
                for (let j = 0; j < i; j++) {
                    if (scheduleData.value[j]?.date && scheduleData.value[j].date.trim() !== '') {
                        hasDateBefore = true
                        break
                    }
                }
                if (hasDateBefore) {
                    currentDateGroupIndex++
                    currentGroupStartIndex = i
                }
            }
        }
        if (i === rowIndex) {
            indexInGroup = i - currentGroupStartIndex
        }
    }
    
    // 第一列和第二列颜色逻辑
    const isEvenGroup = currentDateGroupIndex % 2 === 0
    const dateColor = isEvenGroup ? '#bae1f6' : '#d7f1fc'
    const timeColor = isEvenGroup ? '#d7f1fc' : '#ffffff'
    
    // 第三列复杂颜色逻辑
    let eventColor = '#bae1f6'
    if (currentDateGroupIndex === 0) {
        eventColor = '#bae1f6'
    } else if (currentDateGroupIndex === 1) {
        if (indexInGroup <= 1) eventColor = '#e1f3ff'
        else if (indexInGroup <= 3) eventColor = '#bae1f6'
        else if (indexInGroup <= 6) eventColor = '#e1f3ff'
        else eventColor = '#bae1f6'
    } else if (currentDateGroupIndex === 2) {
        eventColor = indexInGroup === 1 ? '#bae1f6' : '#e1f3ff'
    } else {
        eventColor = isEvenGroup ? '#bae1f6' : '#e1f3ff'
    }
    
    return { dateColor, timeColor, eventColor }
}
```

## 🔄 迁移说明

### 如果您之前使用了包含颜色字段的版本

1. **执行迁移脚本**:
   ```sql
   -- 运行 migration_remove_color_fields.sql
   source sdgs-backend/src/main/resources/db/migration_remove_color_fields.sql
   ```

2. **或者重新创建表**:
   ```sql
   -- 运行完整脚本
   source sdgs-backend/src/main/resources/db/summit_complete.sql
   ```

### 部署步骤

1. **更新数据库**：执行 `summit_complete.sql`
2. **重启后端**：确保新的实体类和Mapper生效
3. **更新前端**：前端代码已自动支持新的颜色逻辑
4. **测试功能**：验证表格颜色和数据显示

## ✅ 优势

1. **架构清晰**：前后端职责明确分离
2. **维护简单**：样式修改只需更改前端CSS
3. **性能更好**：减少数据传输量
4. **扩展性强**：易于添加新的样式规则
5. **代码整洁**：遵循最佳实践 