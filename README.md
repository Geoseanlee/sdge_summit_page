# SDGS 项目

## 项目简介

SDGS 是一个基于 Spring Boot 3 + Vue 3 的前后端分离项目，旨在提供现代化的 Web 应用开发体验。

## 项目架构

```
sdgs/
├── sdgs-backend/          # 后端项目 (Spring Boot)
├── sdgs-user/            # 前端项目 (Vue 3)
└── README.md             # 项目说明文档
```

## 技术栈

### 后端技术栈
- **Java 17** - 主要编程语言
- **Spring Boot 3.5.0** - 后端核心框架
- **Spring Web** - Web 服务框架
- **MyBatis Plus 3.5.7** - ORM 持久层框架
- **MySQL** - 数据库
- **Lombok** - 代码简化工具
- **Maven** - 项目构建工具

### 前端技术栈
- **Vue 3.5.13** - 前端核心框架
- **Vue Router 4.5.0** - 路由管理
- **Pinia 3.0.1** - 状态管理
- **Element Plus 2.10.2** - UI 组件库
- **Axios 1.10.0** - HTTP 请求库
- **Vite 6.2.4** - 构建工具
- **Vitest** - 单元测试框架

## 项目特性

- 🚀 现代化技术栈，开发体验优秀
- 📦 前后端分离架构，松耦合设计
- 🔧 完整的项目脚手架，开箱即用
- 🎨 Element Plus UI 组件库，界面美观
- 🔄 Axios 请求拦截器，统一错误处理
- 📝 MyBatis Plus 集成，简化数据库操作
- ✨ 统一的返回结果封装

## 环境要求

### 开发环境
- **JDK**: 17 或更高版本
- **Node.js**: 16.0 或更高版本
- **MySQL**: 8.0 或更高版本
- **Maven**: 3.6 或更高版本

## 快速开始

### 1. 克隆项目

```bash
git clone https://gitee.com/bill-lychen/sdgs.git
cd sdgs
```

### 2. 数据库配置

#### 创建数据库
```sql
CREATE DATABASE sdgs_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

#### 修改数据库配置
修改 `sdgs-backend/src/main/resources/application.yaml` 文件：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/sdgs_db
    username: root          # 修改为你的数据库用户名
    password: 123456        # 修改为你的数据库密码
```

### 3. 启动后端服务

```bash
cd sdgs-backend

# 使用 Maven Wrapper 启动 (推荐)
./mvnw spring-boot:run

# 或者使用 Maven 启动
mvn spring-boot:run
```

后端服务将在 http://localhost:8080 启动

### 4. 启动前端服务

```bash
cd sdgs-user

# 安装依赖
npm install

# 启动开发服务器
npm run dev
```

前端服务将在 http://localhost:5173 启动

### 5. 验证安装

访问 http://localhost:5173，点击"点我测试与后端的连接"按钮，如果看到成功提示，说明前后端连接正常。

## 项目结构详解

### 后端项目结构 (sdgs-backend)

```
src/main/java/com/sdgs/
├── common/                    # 通用类
│   ├── GlobalExceptionHandler.java  # 全局异常处理器
│   └── Result.java                   # 统一返回结果封装
├── config/                    # 配置类
│   ├── MybatisPlusConfig.java       # MyBatis Plus 配置
│   └── WebMvcConfig.java            # Web MVC 配置
├── controller/                # 控制器层
│   └── TestController.java          # 测试控制器
├── entity/                    # 实体类
├── mapper/                    # 数据访问层
└── SdgsBackendApplication.java      # 启动类
```

### 前端项目结构 (sdgs-user)

```
src/
├── assets/                    # 静态资源
├── components/                # 组件
│   ├── HelloWorld.vue
│   ├── TheWelcome.vue
│   └── icons/                # 图标组件
├── router/                    # 路由配置
│   └── index.js
├── stores/                    # 状态管理
│   └── counter.js
├── utils/                     # 工具类
│   └── request.js            # HTTP 请求封装
├── views/                     # 页面组件
│   ├── AboutView.vue
│   └── HomeView.vue
├── App.vue                    # 根组件
└── main.js                    # 入口文件
```

## 开发指南

### API 接口规范

所有 API 接口都遵循统一的返回格式：

```json
{
  "code": 200,           // 状态码，200 表示成功
  "message": "操作成功",  // 提示信息
  "data": {}             // 数据载荷
}
```

### 前端请求配置

前端使用 Axios 进行 HTTP 请求，已配置：
- 基础路径：`/api`
- 请求超时：10 秒
- Vite 代理：将 `/api` 请求代理到 `http://localhost:8080`
- 统一错误处理和消息提示

### 后端接口开发

1. 在 `controller` 包下创建控制器类
2. 使用 `@RestController` 和 `@RequestMapping` 注解
3. 方法返回类型使用 `Result<T>` 进行统一封装

示例：
```java
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @GetMapping("/list")
    public Result<List<User>> getUserList() {
        // 业务逻辑
        return Result.success(userList);
    }
}
```

## 可用脚本

### 后端脚本

```bash
# 编译项目
./mvnw compile

# 运行测试
./mvnw test

# 打包项目
./mvnw package

# 启动项目
./mvnw spring-boot:run
```

### 前端脚本

```bash
# 开发环境启动
npm run dev

# 生产环境构建
npm run build

# 预览构建结果
npm run preview

# 运行单元测试
npm run test:unit

# 代码格式化
npm run format

# ESLint 检查并修复
npm run lint
```

## 常见问题

### 1. 数据库连接失败
- 检查 MySQL 服务是否启动
- 验证数据库配置信息是否正确
- 确认数据库用户权限

### 2. 前端请求跨域
- 开发环境下 Vite 已配置代理解决跨域问题
- 生产环境需要后端配置 CORS 或使用 Nginx 反向代理

### 3. 端口冲突
- 后端默认端口：8080
- 前端默认端口：5173
- 如有冲突可在配置文件中修改

## 贡献指南

1. Fork 本仓库
2. 创建你的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交你的修改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开一个 Pull Request

## 许可证

本项目采用 MIT 许可证，详情请查看 [LICENSE](LICENSE) 文件。

## 联系方式

如有问题或建议，请通过以下方式联系：

- 项目 Issues: [GitHub Issues](../../issues)
- 邮箱: your-email@example.com

---

⭐ 如果这个项目对你有帮助，请给个 Star！
