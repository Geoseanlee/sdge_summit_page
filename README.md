# SDGS 可持续发展目标平台

## 🌍 项目简介

SDGS 是一个专注于可持续发展目标（Sustainable Development Goals）的现代化 Web 平台，采用前后端分离架构设计。项目集成了图片云存储、多媒体展示、新闻资讯等功能，为推广和展示可持续发展目标提供全面的数字化解决方案。

## 🏗️ 项目架构

```
sdgs/
├── sdgs-backend/          # 后端服务 (Spring Boot 3)
│   ├── 图片云存储服务      # 阿里云OSS集成
│   ├── RESTful API        # 统一接口服务
│   └── 数据管理系统        # MyBatis Plus + MySQL
├── sdgs-user/            # 前端应用 (Vue 3)
│   ├── SDGs主题页面       # 可持续发展目标展示
│   ├── 新闻资讯系统       # 新闻列表和详情
│   ├── 博览会模块         # 活动和峰会展示
│   ├── 圆桌论坛          # 论坛讨论功能
│   ├── 关于我们          # 组织介绍
│   ├── 加入我们          # 招募信息
│   └── OSS测试工具       # 图片管理测试界面
└── README.md             # 项目说明文档
```

## ✨ 核心功能特性

### 🖼️ 图片云存储系统
- **阿里云OSS集成**: 高可用的云端图片存储
- **智能上传**: 支持单张/批量图片上传（最多10张）
- **格式验证**: 支持 jpg、png、gif、bmp、webp 格式
- **大小限制**: 单文件最大10MB，确保性能优化
- **自动命名**: UUID + 日期目录结构，避免文件冲突
- **批量管理**: 支持批量删除（最多50张）
- **实时预览**: 图片上传后即时显示

### 🌟 SDGs主题展示
- **首页展示**: 可持续发展目标概览
- **详细介绍**: 每个SDGs目标的深度解读
- **视觉设计**: 现代化UI设计，突出SDGs主题色彩
- **响应式布局**: 完美适配桌面、平板、手机等设备

### 📰 新闻资讯系统
- **新闻列表**: 分类展示各类SDGs相关新闻
- **详情页面**: 完整的新闻内容展示
- **搜索功能**: 快速定位相关新闻内容

### 🏢 博览会与活动
- **活动展示**: SDGs相关博览会和会议信息
- **峰会报道**: 重要峰会的背景和成果展示
- **互动参与**: 在线参与和报名功能

### 💬 圆桌论坛
- **讨论平台**: SDGs话题的深度讨论
- **观点交流**: 多方观点的展示和互动
- **专家解读**: 专业人士的见解分享

### 📱 响应式设计
- **多设备适配**: 智能响应不同屏幕尺寸
- **触摸优化**: 移动端手势和交互优化
- **性能优化**: 图片懒加载、资源压缩等

## 🛠️ 技术栈

### 后端技术栈
| 技术 | 版本 | 用途 |
|------|------|------|
| **Java** | 17+ | 主要编程语言 |
| **Spring Boot** | 3.5.0 | 核心应用框架 |
| **MyBatis Plus** | 3.5.7 | 数据库ORM框架 |
| **MySQL** | 8.0+ | 关系型数据库 |
| **阿里云OSS** | 3.18.2 | 对象存储服务 |
| **Lombok** | Latest | 代码简化工具 |
| **Maven** | 3.6+ | 项目构建工具 |

### 前端技术栈
| 技术 | 版本 | 用途 |
|------|------|------|
| **Vue.js** | 3.5.13 | 渐进式前端框架 |
| **Vue Router** | 4.5.0 | 前端路由管理 |
| **Pinia** | 3.0.1 | 状态管理库 |
| **Element Plus** | 2.10.2 | UI组件库 |
| **Axios** | 1.10.0 | HTTP请求库 |
| **Vite** | 6.2.4 | 现代化构建工具 |
| **Vitest** | 3.1.1 | 单元测试框架 |

## 🚀 快速开始

### 环境要求

#### 开发环境
- **Java**: JDK 17 或更高版本
- **Node.js**: 16.0 或更高版本
- **MySQL**: 8.0 或更高版本
- **Maven**: 3.6 或更高版本

#### 阿里云服务（可选）
- **OSS对象存储**: 用于图片云存储
- **CDN加速**: 提升图片访问速度

### 1. 项目克隆

```bash
git clone https://gitee.com/bill-lychen/sdgs.git
cd sdgs
```

### 2. 数据库配置

#### 创建数据库
```sql
CREATE DATABASE sdgs_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

#### 配置数据库连接
修改 `sdgs-backend/src/main/resources/application.yaml`：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/sdgs_db
    username: root          # 你的数据库用户名
    password: 123456        # 你的数据库密码
```

### 3. OSS配置（可选）

如需使用图片上传功能，请配置阿里云OSS：
```yaml
aliyun:
  oss:
    endpoint: oss-cn-shanghai.aliyuncs.com
    access-key-id: 你的AccessKeyId
    access-key-secret: 你的AccessKeySecret
    bucket-name: 你的Bucket名称
```

### 4. 启动后端服务

```bash
cd sdgs-backend

# 使用Maven Wrapper启动（推荐）
./mvnw spring-boot:run

# 或使用系统Maven启动
mvn spring-boot:run
```

后端服务将在 http://localhost:8080 启动

### 5. 启动前端服务

```bash
cd sdgs-user

# 安装依赖
npm install

# 启动开发服务器
npm run dev
```

前端服务将在 http://localhost:5173 启动

### 6. 验证安装

1. **访问首页**: http://localhost:5173
2. **测试后端连接**: 点击首页的"测试后端连接"按钮
3. **测试OSS功能**: 访问 http://localhost:5173/test-oss 测试图片上传

## 📡 API 接口概览

### 基础信息
- **基础URL**: `http://localhost:8080/api`
- **数据格式**: JSON
- **字符编码**: UTF-8

### 主要接口

#### 系统测试
```
GET  /api/test/ping                    # 服务连通性测试
```

#### 图片管理
```
POST   /api/file/upload/image          # 单张图片上传
POST   /api/file/upload/images         # 批量图片上传
DELETE /api/file/delete                # 删除图片
DELETE /api/file/delete/batch          # 批量删除图片
GET    /api/file/list                  # 获取图片列表
GET    /api/file/list/today            # 获取今日图片
```

> 📖 **详细API文档**: 查看 `sdgs-backend/HELP.md` 获取完整的接口文档

## 🎨 页面功能

### 主要页面
- **🏠 首页** (`/`): SDGs概览和介绍
- **📋 关于我们** (`/about`): 组织介绍和详细信息
- **📰 新闻资讯** (`/news`): 新闻列表和详情页面
- **🏢 博览会** (`/expo`): 活动展示和峰会背景
- **💬 圆桌论坛** (`/roundtable`): 讨论和观点交流
- **👥 加入我们** (`/join-us`): 招募信息和联系方式
- **🔧 OSS测试** (`/test-oss`): 图片管理测试工具

### 特色功能
- **响应式设计**: 自适应各种屏幕尺寸
- **现代化UI**: Element Plus组件库，美观易用
- **流畅动画**: Vue3 Transition和CSS动画效果
- **无障碍访问**: 遵循Web无障碍设计标准

## 🔧 开发指南

### 后端开发
```bash
# 编译项目
./mvnw compile

# 运行测试
./mvnw test

# 打包项目
./mvnw clean package

# 启动项目（开发模式）
./mvnw spring-boot:run
```

### 前端开发
```bash
# 开发模式启动
npm run dev

# 生产环境构建
npm run build

# 预览构建结果
npm run preview

# 运行单元测试
npm run test:unit

# 代码格式化
npm run format

# ESLint检查和修复
npm run lint
```

### 项目结构规范

#### 后端结构
```
sdgs-backend/src/main/java/com/sdgs/
├── common/          # 通用组件（异常处理、统一返回等）
├── config/          # 配置类（MyBatis、OSS、Web等）
├── controller/      # 控制器层（API接口）
├── service/         # 服务接口层
├── service/impl/    # 服务实现层
├── entity/          # 实体类
└── mapper/          # 数据访问层
```

#### 前端结构
```
sdgs-user/src/
├── assets/          # 静态资源（CSS、图片等）
├── components/      # 可复用组件
├── composables/     # 组合式函数
├── router/          # 路由配置
├── utils/           # 工具函数
├── views/           # 页面组件
│   ├── about/       # 关于我们子页面
│   ├── expo/        # 博览会子页面
│   └── news/        # 新闻子页面
└── stores/          # 状态管理（暂未使用）
```

## 🌟 项目亮点

### 技术亮点
- ✅ **现代化技术栈**: Spring Boot 3 + Vue 3 + Java 17
- ✅ **云原生设计**: 阿里云OSS集成，支持云端部署
- ✅ **响应式架构**: 前后端分离，API设计RESTful
- ✅ **代码质量**: 统一代码规范，完善的异常处理
- ✅ **开发体验**: 热重载、自动化构建、丰富的开发工具

### 业务亮点
- 🎯 **SDGs主题专业性**: 专门为可持续发展目标设计
- 🖼️ **图片管理完整性**: 从上传到展示的完整解决方案
- 📱 **移动端友好**: 完美的移动端用户体验
- 🎨 **视觉设计现代化**: 符合现代Web设计趋势
- 🔧 **功能模块化**: 清晰的功能划分，易于扩展

## 🐛 常见问题

### 1. 数据库连接失败
**问题**: 启动后端时提示数据库连接失败
**解决方案**:
- 检查MySQL服务是否启动
- 验证 `application.yaml` 中的数据库配置
- 确认数据库用户权限
- 检查防火墙设置

### 2. OSS上传失败
**问题**: 图片上传时提示OSS错误
**解决方案**:
- 验证阿里云OSS配置信息是否正确
- 检查Bucket权限设置
- 确认网络连接正常
- 查看后端日志了解具体错误

### 3. 前端跨域问题
**问题**: 前端请求后端接口时出现跨域错误
**解决方案**:
- 开发环境：检查 `vite.config.js` 中的代理配置
- 生产环境：配置Nginx反向代理或后端CORS

### 4. 端口冲突
**问题**: 启动时提示端口被占用
**解决方案**:
- 后端端口（8080）：修改 `application.yaml` 中的 `server.port`
- 前端端口（5173）：在启动命令中指定 `npm run dev -- --port 3000`

## 🤝 贡献指南

我们欢迎所有形式的贡献！请遵循以下步骤：

1. **Fork 本仓库**
2. **创建功能分支**: `git checkout -b feature/amazing-feature`
3. **提交更改**: `git commit -m 'Add amazing feature'`
4. **推送到分支**: `git push origin feature/amazing-feature`
5. **提交 Pull Request**

### 开发规范
- 遵循项目现有的代码风格
- 添加必要的单元测试
- 更新相关文档
- 确保所有测试通过

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 📞 技术支持

### 获取帮助
- **📖 文档**: 查看 `sdgs-backend/HELP.md` 获取详细的后端开发文档
- **🐛 问题报告**: [GitHub Issues](../../issues)
- **💬 讨论**: [GitHub Discussions](../../discussions)
- **📧 邮箱**: your-email@example.com

### 相关链接
- [联合国可持续发展目标官网](https://sdgs.un.org/)
- [Spring Boot官方文档](https://spring.io/projects/spring-boot)
- [Vue.js官方文档](https://vuejs.org/)
- [Element Plus组件库](https://element-plus.org/)
- [阿里云OSS文档](https://help.aliyun.com/product/31815.html)

---

<div align="center">

### 🌍 为可持续发展目标贡献力量

**如果这个项目对你有帮助，请给个 ⭐ Star！**

![SDGs Logo](https://via.placeholder.com/200x50/FF6B6B/FFFFFF?text=SDGs+Platform)

</div>
