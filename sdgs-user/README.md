# SDGs Platform - 前端项目

🌍 **可持续发展目标 (SDGs) 平台** - 一个致力于推进联合国可持续发展目标实现的现代化Web应用平台。

## 📖 项目简介

本项目是SDGs Platform的前端应用，采用现代化的Vue 3生态系统构建，为用户提供关于可持续发展目标的全面信息展示、圆桌会议参与、新闻资讯浏览等功能。项目特别注重响应式设计和用户体验，确保在所有设备上都能提供优秀的访问体验。

## 🚀 核心特性

### 📱 **完全响应式设计**
- 移动优先的设计理念
- 支持从320px到4K屏幕的完整适配
- 自定义响应式断点系统
- 触摸友好的交互设计

### 🎨 **现代化UI设计**
- 基于Element Plus的组件库
- 统一的视觉设计语言
- 流畅的动画和过渡效果
- 支持高对比度和无障碍访问

### 🌟 **丰富的功能模块**
- **首页**：展示SDGs概览和快速导航
- **SDGs展览**：17个可持续发展目标的详细介绍
- **圆桌会议**：专家讨论和会议信息
- **新闻资讯**：最新的可持续发展动态
- **关于我们**：组织介绍和团队信息
- **加入我们**：志愿者申请和参与方式

### ⚡ **优秀的性能**
- Vue 3 Composition API
- Vite构建工具，快速开发和构建
- 代码分割和懒加载
- 现代浏览器优化

## 🛠️ 技术栈

### 前端框架
- **Vue 3.5.13** - 渐进式JavaScript框架
- **Vue Router 4.5.0** - 官方路由管理器
- **Pinia 3.0.1** - 状态管理库

### UI组件库
- **Element Plus 2.10.2** - Vue 3组件库
- **自定义响应式CSS框架** - 完整的响应式设计系统

### 开发工具
- **Vite 6.2.4** - 下一代前端构建工具
- **ESLint 9.22.0** - 代码质量检查
- **Prettier 3.5.3** - 代码格式化
- **Vitest 3.1.1** - 单元测试框架

### 网络请求
- **Axios 1.10.0** - HTTP客户端库

## 📁 项目结构

```
sdgs-user/
├── public/                 # 静态资源
│   └── favicon.ico
├── src/
│   ├── assets/            # 资源文件
│   │   ├── base.css       # 基础样式
│   │   ├── main.css       # 主样式文件
│   │   └── responsive.css # 响应式设计系统
│   ├── components/        # 公共组件
│   ├── composables/       # 组合式API
│   │   └── useResponsive.js # 响应式工具
│   ├── router/            # 路由配置
│   │   └── index.js
│   ├── utils/             # 工具函数
│   │   └── request.js     # API请求封装
│   ├── views/             # 页面组件
│   │   ├── HomeView.vue           # 首页
│   │   ├── ExpoView.vue           # SDGs展览主页
│   │   ├── RoundtableView.vue     # 圆桌会议
│   │   ├── NewsView.vue           # 新闻资讯
│   │   ├── AboutView.vue          # 关于我们
│   │   ├── JoinUsView.vue         # 加入我们
│   │   ├── expo/                  # 展览子页面
│   │   │   ├── ExpoBackgroundView.vue
│   │   │   └── ExpoSummitView.vue
│   │   ├── news/                  # 新闻子页面
│   │   │   └── NewsDetailView.vue
│   │   └── about/                 # 关于子页面
│   │       └── AboutMoreView.vue
│   ├── App.vue            # 根组件
│   └── main.js           # 应用入口
├── index.html            # HTML模板
├── vite.config.js        # Vite配置
├── package.json          # 项目依赖
├── RESPONSIVE_GUIDE.md   # 响应式设计指南
└── README.md            # 项目文档
```

## 🎯 响应式设计系统

### 断点定义
```css
--breakpoint-xs: 480px;   /* 超小屏幕 (手机竖屏) */
--breakpoint-sm: 768px;   /* 小屏幕 (手机横屏/小平板) */
--breakpoint-md: 1024px;  /* 中等屏幕 (平板) */
--breakpoint-lg: 1280px;  /* 大屏幕 (桌面) */
--breakpoint-xl: 1440px;  /* 超大屏幕 (大桌面) */
```

### 主要特性
- 📱 移动优先设计原则
- 🎨 现代CSS Grid和Flexbox布局
- 🔧 丰富的响应式工具类
- 📏 智能容器系统
- 🎛️ JavaScript响应式API
- ♿ 无障碍访问支持

## 💻 开发环境要求

- **Node.js**: 16.0+ 
- **npm**: 8.0+ 或 **yarn**: 1.22+
- **现代浏览器**: Chrome 90+, Firefox 88+, Safari 14+

## 🚀 快速开始

### 1. 克隆项目
```bash
git clone <repository-url>
cd sdgs-user
```

### 2. 安装依赖
```bash
npm install
# 或
yarn install
```

### 3. 启动开发服务器
```bash
npm run dev
# 或
yarn dev
```

项目将在 `http://localhost:5173` 启动

### 4. 构建生产版本
```bash
npm run build
# 或
yarn build
```

### 5. 预览生产构建
```bash
npm run preview
# 或
yarn preview
```

## 🛠️ 开发指令

```bash
# 开发服务器
npm run dev

# 生产构建
npm run build

# 构建预览
npm run preview

# 运行单元测试
npm run test:unit

# 代码检查
npm run lint

# 代码格式化
npm run format
```

## 📱 响应式开发指南

### 使用CSS工具类
```html
<!-- 响应式容器 -->
<div class="container">
  <!-- 响应式网格 -->
  <div class="grid grid-auto-fit">
    <div class="feature-card">卡片内容</div>
  </div>
</div>

<!-- 响应式显示控制 -->
<div class="hidden-xs block-sm">小屏幕以上显示</div>
```

### 使用JavaScript API
```vue
<script setup>
import { useResponsive } from '@/composables/useResponsive'

const { isMobile, isTablet, isDesktop, responsive } = useResponsive()

// 响应式值
const buttonSize = responsive({
  xs: 'small',
  sm: 'default',
  lg: 'large'
})
</script>

<template>
  <el-button :size="buttonSize">响应式按钮</el-button>
  <div v-if="isMobile">移动端内容</div>
</template>
```

## 🎨 UI组件使用

### Element Plus组件
项目中的Element Plus组件已经过响应式优化：

```vue
<template>
  <!-- 自动适配移动端的按钮 -->
  <el-button class="btn-mobile" type="primary">按钮</el-button>
  
  <!-- 响应式表单 -->
  <el-form>
    <el-form-item>
      <el-input placeholder="输入内容" />
    </el-form-item>
  </el-form>
  
  <!-- 移动端优化的对话框 -->
  <el-dialog v-model="visible" title="标题">
    内容会在移动端自动适配
  </el-dialog>
</template>
```

## 🔗 后端集成

### API配置
项目已配置Axios实例用于与后端通信：

```javascript
// src/utils/request.js
const request = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 10000
})
```

### 开发代理
Vite开发服务器已配置代理，自动转发API请求到后端：

```javascript
// vite.config.js
server: {
  proxy: {
    '/api': {
      target: 'http://localhost:8080',
      changeOrigin: true
    }
  }
}
```

## 🧪 测试

### 运行单元测试
```bash
npm run test:unit
```

### 响应式测试建议
1. **浏览器开发工具**：使用设备模拟器测试
2. **真实设备测试**：在不同设备上实际测试
3. **网络条件测试**：模拟不同网络速度
4. **可访问性测试**：使用屏幕阅读器测试

## 📊 性能优化

### 已实现的优化
- ⚡ Vite构建工具，快速热重载
- 📦 组件懒加载和代码分割
- 🎨 CSS变量系统，减少重复样式
- 📱 移动端触摸优化
- 🖼️ 图片响应式处理

### 性能监控
```bash
# 构建分析
npm run build -- --analyze

# 使用Lighthouse进行性能测试
lighthouse http://localhost:5173
```

## 🌐 浏览器支持

| 浏览器 | 支持版本 |
|--------|----------|
| Chrome | 90+ |
| Firefox | 88+ |
| Safari | 14+ |
| Edge | 90+ |

## 📚 相关文档

- [Vue 3 官方文档](https://vuejs.org/)
- [Element Plus 文档](https://element-plus.org/)
- [Vite 文档](https://vitejs.dev/)
- [响应式设计指南](./RESPONSIVE_GUIDE.md)

## 🤝 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交 Pull Request

### 开发规范
- 使用ESLint和Prettier保持代码风格统一
- 遵循Vue 3 Composition API最佳实践
- 确保所有新功能都具备响应式支持
- 编写必要的单元测试

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 📞 联系我们

- 📧 邮箱：info@sdgs-platform.org
- 🌐 网站：https://sdgs-platform.org
- 📱 微信：SDGsPlatform

---

⭐ 如果这个项目对您有帮助，请给我们一个星标！

**一起为可持续发展目标的实现贡献力量！** 🌍✨
