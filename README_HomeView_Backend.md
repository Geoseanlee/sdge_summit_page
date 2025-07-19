# HomeView 后端数据获取功能

## 概述

已将 HomeView.vue 从静态内容改为从后端动态获取数据，保持了原有的按键功能和跳转逻辑。

## 后端修改

### 1. 数据库表结构

创建了新的 `home_info` 表来存储首页数据：

- 英雄区域：标题 1、副标题 1、标题 2、副标题 2、图片 URL
- SDGs 介绍：两段描述文字
- 世博会介绍：标题、副标题、背景图片 URL
- 联盟介绍：标题、两段描述文字
- 数据区域：标题、图片 URL
- 加入我们：标题、三个项目描述、图片 URL

### 2. 新增文件

- `HomeInfo.java` - 实体类
- `HomeInfoMapper.java` - 数据访问层
- `HomeService.java` - 服务接口
- `HomeServiceImpl.java` - 服务实现
- `HomeController.java` - 控制器
- `home_info.sql` - 数据库建表脚本
- `setup_home_info.sql` - 完整的建表和数据初始化脚本

### 3. API 接口

- `GET /api/home/info` - 获取最新的首页信息
- `GET /api/home/detail/{id}` - 根据 ID 获取首页信息
- `POST /api/home/manage` - 创建新的首页信息（管理功能）
- `PUT /api/home/manage/{id}` - 更新首页信息（管理功能）
- `DELETE /api/home/manage/{id}` - 删除首页信息（管理功能）

## 前端修改

### 1. 数据获取

- 使用 `request.get('/home/info')` 从后端获取数据
- 添加了加载状态显示
- 保留了原有的默认文本作为后备

### 2. 动态内容

所有文本内容和图片 URL 现在都从后端数据动态获取：

- 文本内容：`{{ homeInfo?.heroTitle1 || '默认文本' }}` 格式，如果后端数据不存在，显示原有的静态文本
- 图片显示：使用 `v-if="homeInfo?.imageUrl"` 条件渲染，只有当后端提供图片 URL 时才显示图片，没有图片时不显示任何图片元素
- 世博会背景区域：当没有背景图片时，使用渐变背景色作为替代

### 3. 保持的功能

- 所有按钮的点击事件和路由跳转保持不变
- 响应式设计和 CSS 样式保持不变
- 组件的整体布局和交互保持不变

## 使用方法

### 1. 数据库初始化

```sql
-- 执行建表脚本
source d:\project\sdgs\sdgs-backend\src\main\resources\db\setup_home_info.sql
```

### 2. 启动后端

```bash
cd d:\project\sdgs\sdgs-backend
mvn spring-boot:run
```

### 3. 启动前端

```bash
cd d:\project\sdgs\sdgs-user
npm run dev
```

### 4. 访问页面

前端会自动调用 `/api/home/info` 接口获取数据

## 数据管理

可以通过管理 API 或直接修改数据库来更新首页内容，支持动态修改：

- 标题和副标题
- 描述文字
- 图片 URL
- 所有展示内容

## 错误处理

- 如果后端接口失败，页面会显示默认的静态文本
- 如果后端没有提供图片 URL，对应的图片区域将不显示（而非显示占位图片）
- 世博会介绍区域在没有背景图片时会使用蓝色渐变背景
- 添加了加载状态指示器
- 使用了合适的错误处理和日志记录

## 备注

- 按钮功能和路由跳转完全保持原样
- 图片 URL 支持相对路径和绝对 URL
- 所有样式和响应式设计保持不变
