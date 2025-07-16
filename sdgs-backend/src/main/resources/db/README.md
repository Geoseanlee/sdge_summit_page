# SDGS 数据库初始化说明

## 数据库结构

本项目使用MySQL数据库，数据库名称为 `sdgs_db`。

## 文件说明

### `news.sql`
新闻资讯表脚本，包含：
- news表结构定义
- 示例数据（5条新闻记录）

## 使用方法

### 方法一：直接执行
```bash
mysql -u root -p sdgs_db < news.sql
```

### 方法二：手动执行
1. 登录MySQL并选择数据库
```bash
mysql -u root -p
USE sdgs_db;
```

2. 执行脚本
```sql
source /path/to/news.sql
```

## 数据库配置

确保 `application.yaml` 中的数据库配置正确：

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/sdgs_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: your_password
```

## 表结构

### news表
- `id`: 主键ID
- `title`: 新闻标题
- `subtitle`: 副标题
- `summary`: 新闻简介
- `content`: 新闻正文内容（富文本）
- `cover_image_url`: 封面图片URL
- `category`: 新闻分类
- `tags`: 标签（逗号分隔）
- `author`: 作者
- `source`: 来源
- `publish_time`: 发布时间
- `status`: 状态（0:草稿 1:已发布）
- `view_count`: 阅读量
- `create_time`: 创建时间
- `update_time`: 更新时间

## 示例数据

脚本中包含了5条示例新闻数据，涵盖了不同的分类：
- 政策发布
- 气候行动
- 教育发展
- 健康福祉
- 经济发展

## 注意事项

1. 执行前请确保数据库 `sdgs_db` 已存在
2. 确保MySQL服务正在运行
3. 数据库用户需要有相应的权限
4. 定期备份数据库 