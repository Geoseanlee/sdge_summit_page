# SDGs Backend Project - 开发者指南

## 📋 项目简介

SDGs Backend 是一个基于 Spring Boot 3.5.0 的后端服务项目，专为可持续发展目标（SDGs）平台提供数据服务和文件管理功能。项目采用现代化的 Java 开发技术栈，支持图片云存储、RESTful API 等核心功能。

### 🎯 主要功能

- **🖼️ 图片管理**: 阿里云OSS集成，支持图片上传、删除、列表查询
- **📊 数据管理**: 基于MyBatis Plus的数据库操作
- **🔐 安全防护**: 完善的异常处理和数据验证
- **📱 跨平台支持**: RESTful API设计，支持Web、移动端调用

## 🏗️ 技术架构

### 技术栈

| 技术 | 版本 | 用途 |
|------|------|------|
| Spring Boot | 3.5.0 | 核心框架 |
| MyBatis Plus | 3.5.7 | 数据库操作 |
| MySQL | 8.0+ | 数据存储 |
| 阿里云OSS | 3.18.2 | 文件存储 |
| Lombok | - | 代码简化 |
| Maven | 3.6+ | 依赖管理 |

### 项目结构

```
src/main/java/com/sdgs/
├── common/                     # 公共组件
│   ├── GlobalExceptionHandler.java  # 全局异常处理
│   └── Result.java            # 统一返回结果
├── config/                     # 配置类
│   ├── MybatisPlusConfig.java  # MyBatis Plus配置
│   ├── OssConfig.java         # OSS配置
│   └── WebMvcConfig.java      # Web配置
├── controller/                 # 控制器层
│   ├── FileUploadController.java  # 文件上传控制器
│   └── TestController.java    # 测试控制器
├── service/                    # 服务接口层
│   ├── OssService.java        # OSS服务接口
│   └── impl/                  # 服务实现层
│       └── OssServiceImpl.java # OSS服务实现
├── entity/                     # 实体类
├── mapper/                     # 数据访问层
└── SdgsBackendApplication.java # 应用启动类
```

## 🚀 快速开始

### 1. 环境要求

- **Java**: JDK 17+
- **Maven**: 3.6+
- **MySQL**: 8.0+
- **IDE**: IntelliJ IDEA 或 Eclipse

### 2. 项目配置

#### 数据库配置 (application.yaml)
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/sdgs_db
    username: root
    password: 123456
```

#### 阿里云OSS配置 (application.yaml)
```yaml
aliyun:
  oss:
    endpoint: oss-cn-shanghai.aliyuncs.com
    access-key-id: 你的AccessKeyId
    access-key-secret: 你的AccessKeySecret
    bucket-name: 你的Bucket名称
```

### 3. 启动项目

#### 方式一：IDE启动
1. 导入项目到IDE
2. 配置数据库连接和OSS参数
3. 运行 `SdgsBackendApplication.java`

#### 方式二：命令行启动
```bash
# 开发环境启动
mvn spring-boot:run

# 打包后启动
mvn clean package
java -jar target/sdgs-backend-0.0.1-SNAPSHOT.jar
```

### 4. 验证启动

访问测试接口：`http://localhost:8080/api/test/ping`

正常返回：
```json
{
  "code": 200,
  "message": "操作成功",
  "data": "Pong! 后端服务响应正常。"
}
```

## 📡 API 接口文档

### 基础信息

- **基础URL**: `http://localhost:8080`
- **数据格式**: JSON
- **字符编码**: UTF-8

### 统一返回格式

```json
{
  "code": 200,        // 状态码，200-成功，其他-失败
  "message": "操作成功", // 响应信息
  "data": {}          // 响应数据
}
```

### 测试接口

#### 1. 连接测试
```http
GET /api/test/ping
```

**响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": "Pong! 后端服务响应正常。"
}
```

### 文件管理接口

#### 1. 单张图片上传
```http
POST /api/file/upload/image
Content-Type: multipart/form-data
```

**请求参数**:
- `file`: 图片文件 (form-data)

**限制条件**:
- 文件类型：jpg, png, gif, bmp, webp
- 文件大小：最大10MB

**响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "fileName": "image.jpg",
    "fileSize": 102400,
    "fileUrl": "https://bucket.oss-cn-shanghai.aliyuncs.com/images/2024/01/15/abc123.jpg"
  }
}
```

#### 2. 批量图片上传
```http
POST /api/file/upload/images
Content-Type: multipart/form-data
```

**请求参数**:
- `files`: 图片文件数组 (form-data)

**限制条件**:
- 单次最多上传10张图片
- 每张图片最大10MB

#### 3. 删除图片
```http
DELETE /api/file/delete?fileUrl={fileUrl}
```

**请求参数**:
- `fileUrl`: 图片URL (query参数)

#### 4. 获取图片列表
```http
GET /api/file/list?prefix={prefix}&maxKeys={maxKeys}
```

**请求参数**:
- `prefix`: 目录前缀，可选
- `maxKeys`: 最大返回数量，默认100

#### 5. 获取今日图片
```http
GET /api/file/list/today
```

## 🔧 开发指南

### 代码规范

1. **命名规范**
   - 类名：大驼峰 (PascalCase)
   - 方法名：小驼峰 (camelCase)
   - 常量：全大写下划线分隔 (UPPER_SNAKE_CASE)

2. **注释规范**
   - 类和方法必须有JavaDoc注释
   - 复杂逻辑添加行内注释

### 新增功能开发流程

1. **创建实体类** (Entity)
2. **创建数据访问接口** (Mapper)
3. **创建服务接口** (Service)
4. **创建服务实现** (ServiceImpl)
5. **创建控制器** (Controller)

## 🛠️ 部署指南

### 生产环境配置

#### 环境变量配置
```bash
# 数据库配置
export DB_HOST=localhost
export DB_PORT=3306
export DB_NAME=sdgs_db
export DB_USERNAME=root
export DB_PASSWORD=your_password

# OSS配置
export OSS_ENDPOINT=oss-cn-shanghai.aliyuncs.com
export OSS_ACCESS_KEY_ID=your_access_key_id
export OSS_ACCESS_KEY_SECRET=your_access_key_secret
export OSS_BUCKET_NAME=your_bucket_name
```

## 🐛 问题排查

### 常见问题

#### 1. 启动失败
- **数据库连接失败**: 检查数据库配置和网络连接
- **OSS配置错误**: 验证AccessKey和Bucket配置

#### 2. 文件上传失败
- **文件大小超限**: 检查文件大小限制配置
- **OSS权限不足**: 检查OSS AccessKey权限设置

#### 3. 接口调用失败
- **跨域问题**: 检查@CrossOrigin注解配置
- **路径映射错误**: 检查Controller的@RequestMapping路径

## 📞 技术支持

### 开发团队
- **项目负责人**: SDGs开发团队
- **技术栈**: Spring Boot + MyBatis Plus + 阿里云OSS
- **开发环境**: Java 17 + Maven 3.6+

### 相关文档
- [Spring Boot官方文档](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [MyBatis Plus官方文档](https://baomidou.com/)
- [阿里云OSS Java SDK](https://help.aliyun.com/document_detail/32008.html)

### 版本历史
- **v1.0.0**: 初始版本，包含基础文件管理功能
- **v1.1.0**: 增加批量操作支持
- **v1.2.0**: 优化异常处理和日志记录

---

📝 **注意**: 请确保在生产环境中妥善保管数据库密码和OSS访问密钥等敏感信息，建议使用环境变量或配置中心进行管理。 