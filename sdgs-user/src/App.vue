<script setup>
import { ref } from 'vue'
import { RouterView } from 'vue-router'
// 1. 引入我们封装好的 axios 实例
import request from './utils/request.js'

// 2. 定义一个点击按钮时触发的异步函数
async function pingBackend() {
  try {
    // 3. 使用 request 对象发送请求
    //    这里的 '/test/ping' 会被 Vite 代理到 'http://localhost:8080/api/test/ping'
    const responseData = await request.get('/test/ping')

    // 4. 如果成功，用 alert 弹窗显示后端返回的数据
    alert('成功接收到后端数据：' + responseData)
  } catch (error) {
    // 5. 如果失败，用 alert 弹窗显示错误
    //    (我们的 request.js 拦截器也会用 ElMessage 提示，alert 更直接)
    alert('连接失败！请按 F12 查看控制台错误。')
    console.error(error)
  }
}

// 响应式数据
const isMenuOpen = ref(false)

// 方法
const toggleMenu = () => {
  isMenuOpen.value = !isMenuOpen.value
}

const closeMenu = () => {
  isMenuOpen.value = false
}
</script>

<template>
  <div id="app">
    <!-- 导航栏 -->
    <header class="app-header">
      <nav class="navbar">
        <div class="nav-container">
          <!-- Logo -->
          <div class="nav-logo">
            <router-link to="/" class="logo-link">
              <span class="logo-icon">🌍</span>
              <div class="logo-text-container">
                <span class="logo-text">世博全球青年联盟</span>
                <span class="logo-subtitle">World Expo Global Youth Alliance</span>
              </div>
            </router-link>
          </div>

          <!-- 导航菜单 -->
          <div class="nav-menu" :class="{ active: isMenuOpen }">
            <router-link to="/expo" class="nav-link" @click="closeMenu">世博会</router-link>
            <router-link to="/roundtable" class="nav-link" @click="closeMenu">圆桌会议</router-link>
            <router-link to="/news" class="nav-link" @click="closeMenu">动态</router-link>
            <router-link to="/about" class="nav-link" @click="closeMenu">关于我们</router-link>
            <router-link to="/join-us" class="nav-link" @click="closeMenu">加入我们</router-link>
            <router-link to="/test-oss" class="nav-link test-btn" @click="closeMenu">OSS测试</router-link>
          </div>

          <!-- 移动端菜单按钮 -->
          <div class="nav-toggle" @click="toggleMenu">
            <span></span>
            <span></span>
            <span></span>
          </div>
        </div>
      </nav>
    </header>

    <!-- 主要内容区域 -->
    <main class="main-content">
      <RouterView />
    </main>

    <!-- 页脚 -->
    <footer class="app-footer">
      <div class="footer-container">
        <div class="footer-content">
          <div class="footer-section">
            <h4>SDGs Platform</h4>
            <p>致力于推进联合国可持续发展目标的实现</p>
          </div>
          <div class="footer-section">
            <h4>快速链接</h4>
            <ul>
              <li><router-link to="/expo">SDGs展览</router-link></li>
              <li><router-link to="/roundtable">圆桌会议</router-link></li>
              <li><router-link to="/news">新闻资讯</router-link></li>
              <li><router-link to="/about">关于我们</router-link></li>
            </ul>
          </div>
          <div class="footer-section">
            <h4>联系我们</h4>
            <p>📧 info@sdgs-platform.org</p>
            <p>📞 +86 10 1234 5678</p>
            <p>📍 北京市朝阳区国贸大厦8层</p>
          </div>
        </div>
        <div class="footer-bottom">
          <p>&copy; 2024 SDGs Platform. All rights reserved.</p>
        </div>
      </div>
    </footer>
  </div>
</template>

<style scoped>
#app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

/* =================================
   导航栏响应式样式
   ================================= */
.app-header {
  background: linear-gradient(135deg, #67b7f7, #5dade2);
  box-shadow: var(--shadow-md);
  position: sticky;
  top: 0;
  z-index: 1000;
  width: 100%;
}

.navbar {
  padding: 0;
}

.nav-container {
  max-width: var(--container-lg);
  margin: 0 auto;
  padding: 0 var(--spacing-lg);
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 140px;
}

@media (max-width: 1279px) {
  .nav-container {
    max-width: var(--container-md);
    padding: 0 var(--spacing-md);
  }
}

@media (max-width: 767px) {
  .nav-container {
    padding: 0 var(--spacing-sm);
    height: 100px;
  }
}

.nav-logo .logo-link {
  display: flex;
  align-items: center;
  text-decoration: none;
  color: white;
  font-weight: 700;
  font-size: 1.3rem;
  transition: var(--transition-base);
}

.logo-icon {
  font-size: 1.8rem;
  margin-right: 15px;
}

.logo-text-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

@media (max-width: 767px) {
  .nav-logo .logo-link {
    font-size: 1.1rem;
  }
  
  .logo-icon {
    font-size: 1.5rem;
    margin-right: 10px;
  }
}

.logo-text {
  color: white;
  font-weight: 700;
  font-size: 1.8rem;
  line-height: 1.2;
}

.logo-subtitle {
  color: rgba(255, 255, 255, 0.9);
  font-weight: 400;
  font-size: 1.1rem;
  margin-top: 2px;
}

@media (max-width: 1023px) {
  .logo-text {
    font-size: 1.5rem;
  }
  .logo-subtitle {
    font-size: 0.9rem;
  }
}

@media (max-width: 480px) {
  .logo-text {
    font-size: 1.2rem;
  }
  .logo-subtitle {
    font-size: 0.7rem;
  }
}

.nav-menu {
  display: flex;
  align-items: center;
  gap: 2rem;
  transition: var(--transition-base);
}

@media (max-width: 1023px) {
  .nav-menu {
    gap: 1.5rem;
  }
}

@media (max-width: 767px) {
  .nav-menu {
    position: fixed;
    top: 100px;
    left: 0;
    right: 0;
    background: linear-gradient(135deg, #67b7f7, #5dade2);
    flex-direction: column;
    gap: 0;
    padding: var(--spacing-md) 0;
    box-shadow: var(--shadow-lg);
    transform: translateY(-100%);
    opacity: 0;
    visibility: hidden;
    transition: all var(--transition-base);
    max-height: calc(100vh - 100px);
    overflow-y: auto;
  }
  
  .nav-menu.active {
    transform: translateY(0);
    opacity: 1;
    visibility: visible;
  }
}

.nav-link {
  text-decoration: none;
  color: white;
  font-weight: 500;
  font-size: 1.1rem;
  transition: var(--transition-base);
  padding: 8px 16px;
  border-radius: var(--radius-md);
  white-space: nowrap;
}

@media (max-width: 1023px) {
  .nav-link {
    padding: 6px 12px;
    font-size: 1rem;
  }
}

@media (max-width: 767px) {
  .nav-link {
    display: block;
    width: 100%;
    padding: 15px var(--spacing-lg);
    text-align: center;
    border-radius: 0;
    border-bottom: 1px solid rgba(255, 255, 255, 0.2);
    font-size: 18px;
    min-height: 44px;
    line-height: 44px;
    color: white;
  }
  
  .nav-link:last-child {
    border-bottom: none;
  }
}

.nav-link:hover {
  color: white;
  background-color: rgba(255, 255, 255, 0.2);
}

@media (max-width: 767px) {
  .nav-link:hover {
    background-color: rgba(255, 255, 255, 0.2);
  }
}

.nav-link.router-link-active {
  color: white;
  font-weight: 700;
  font-size: 1.2rem;
  background-color: transparent;
}

@media (max-width: 1023px) {
  .nav-link.router-link-active {
    font-size: 1.1rem;
  }
}

@media (max-width: 767px) {
  .nav-link.router-link-active {
    font-size: 20px;
    font-weight: 700;
  }
}

.test-btn {
  background: linear-gradient(135deg, #f59e0b, #d97706);
  color: white !important;
  padding: 8px 16px;
  border-radius: var(--radius-md);
  font-weight: 600;
  box-shadow: var(--shadow-sm);
}

.test-btn:hover {
  background: linear-gradient(135deg, #d97706, #b45309) !important;
  transform: translateY(-1px);
  box-shadow: var(--shadow-md);
}

.nav-toggle {
  display: none;
  flex-direction: column;
  cursor: pointer;
  padding: 8px;
  border-radius: var(--radius-md);
  transition: var(--transition-base);
  background: transparent;
  border: none;
  width: 40px;
  height: 40px;
  justify-content: center;
  align-items: center;
}

@media (max-width: 767px) {
  .nav-toggle {
    display: flex;
  }
}

.nav-toggle:hover {
  background-color: rgba(255, 255, 255, 0.2);
}

.nav-toggle span {
  width: 25px;
  height: 3px;
  background: white;
  margin: 3px 0;
  transition: var(--transition-base);
  border-radius: 2px;
}

/* 主要内容区域 */
.main-content {
  flex: 1;
}

/* =================================
   页脚响应式样式
   ================================= */
.app-footer {
  background: #1f2937;
  color: white;
  margin-top: auto;
  width: 100%;
}

.footer-container {
  max-width: var(--container-lg);
  margin: 0 auto;
  padding: var(--spacing-xl) var(--spacing-lg) var(--spacing-lg);
}

@media (max-width: 1279px) {
  .footer-container {
    max-width: var(--container-md);
    padding: var(--spacing-xl) var(--spacing-md) var(--spacing-lg);
  }
}

@media (max-width: 767px) {
  .footer-container {
    padding: var(--spacing-lg) var(--spacing-sm) var(--spacing-md);
  }
}

.footer-content {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: var(--spacing-xl);
  margin-bottom: var(--spacing-xl);
}

@media (max-width: 767px) {
  .footer-content {
    grid-template-columns: 1fr;
    gap: var(--spacing-lg);
    margin-bottom: var(--spacing-lg);
  }
}

.footer-section h4 {
  color: #f9fafb;
  margin-bottom: var(--spacing-sm);
  font-size: var(--font-size-lg);
  font-weight: 600;
}

.footer-section p {
  color: #d1d5db;
  line-height: 1.6;
  margin-bottom: var(--spacing-xs);
  font-size: var(--font-size-sm);
}

.footer-section ul {
  list-style: none;
  padding: 0;
}

.footer-section ul li {
  margin-bottom: var(--spacing-xs);
}

.footer-section ul li a {
  color: #d1d5db;
  text-decoration: none;
  transition: var(--transition-base);
  font-size: var(--font-size-sm);
}

.footer-section ul li a:hover {
  color: #60a5fa;
}

.footer-bottom {
  border-top: 1px solid #374151;
  padding-top: var(--spacing-lg);
  text-align: center;
}

.footer-bottom p {
  color: #9ca3af;
  margin: 0;
  font-size: var(--font-size-sm);
}

/* =================================
   导航切换动画效果
   ================================= */
.nav-toggle.active span:nth-child(2) {
  opacity: 0;
}

.nav-toggle.active span:nth-child(1) {
  transform: translateY(9px) rotate(45deg);
}

.nav-toggle.active span:nth-child(3) {
  transform: translateY(-9px) rotate(-45deg);
}
</style>