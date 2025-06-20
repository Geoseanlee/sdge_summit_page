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
              <span class="logo-text">SDGs Platform</span>
            </router-link>
          </div>

          <!-- 导航菜单 -->
          <div class="nav-menu" :class="{ active: isMenuOpen }">
            <router-link to="/" class="nav-link" @click="closeMenu">首页</router-link>
            <router-link to="/expo" class="nav-link" @click="closeMenu">SDGs展览</router-link>
            <router-link to="/roundtable" class="nav-link" @click="closeMenu">圆桌会议</router-link>
            <router-link to="/news" class="nav-link" @click="closeMenu">新闻资讯</router-link>
            <router-link to="/about" class="nav-link" @click="closeMenu">关于我们</router-link>
            <router-link to="/join-us" class="nav-link join-us-btn" @click="closeMenu">
              加入我们
            </router-link>
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

/* 导航栏样式 */
.app-header {
  background: white;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 1000;
}

.navbar {
  padding: 0;
}

.nav-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 70px;
}

.nav-logo .logo-link {
  display: flex;
  align-items: center;
  text-decoration: none;
  color: #333;
  font-weight: 700;
  font-size: 1.3rem;
}

.logo-icon {
  font-size: 1.8rem;
  margin-right: 10px;
}

.logo-text {
  color: #2563eb;
}

.nav-menu {
  display: flex;
  align-items: center;
  gap: 2rem;
}

.nav-link {
  text-decoration: none;
  color: #666;
  font-weight: 500;
  transition: color 0.3s ease;
  padding: 8px 16px;
  border-radius: 6px;
}

.nav-link:hover {
  color: #2563eb;
  background-color: #f8fafc;
}

.nav-link.router-link-active {
  color: #2563eb;
  background-color: #dbeafe;
}

.join-us-btn {
  background: linear-gradient(135deg, #2563eb, #1d4ed8);
  color: white !important;
  padding: 10px 20px;
  border-radius: 25px;
  font-weight: 600;
}

.join-us-btn:hover {
  background: linear-gradient(135deg, #1d4ed8, #1e40af) !important;
  transform: translateY(-1px);
}

.nav-toggle {
  display: none;
  flex-direction: column;
  cursor: pointer;
  padding: 4px;
}

.nav-toggle span {
  width: 25px;
  height: 3px;
  background: #333;
  margin: 3px 0;
  transition: 0.3s;
  border-radius: 2px;
}

/* 主要内容区域 */
.main-content {
  flex: 1;
}

/* 页脚样式 */
.app-footer {
  background: #1f2937;
  color: white;
  margin-top: auto;
}

.footer-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 40px 20px 20px;
}

.footer-content {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 30px;
  margin-bottom: 30px;
}

.footer-section h4 {
  color: #f9fafb;
  margin-bottom: 16px;
  font-size: 1.1rem;
  font-weight: 600;
}

.footer-section p {
  color: #d1d5db;
  line-height: 1.6;
  margin-bottom: 8px;
}

.footer-section ul {
  list-style: none;
  padding: 0;
}

.footer-section ul li {
  margin-bottom: 8px;
}

.footer-section ul li a {
  color: #d1d5db;
  text-decoration: none;
  transition: color 0.3s ease;
}

.footer-section ul li a:hover {
  color: #60a5fa;
}

.footer-bottom {
  border-top: 1px solid #374151;
  padding-top: 20px;
  text-align: center;
}

.footer-bottom p {
  color: #9ca3af;
  margin: 0;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .nav-menu {
    position: fixed;
    left: -100%;
    top: 70px;
    flex-direction: column;
    background-color: white;
    width: 100%;
    text-align: center;
    transition: 0.3s;
    box-shadow: 0 10px 27px rgba(0, 0, 0, 0.05);
    padding: 20px 0;
    gap: 0;
  }

  .nav-menu.active {
    left: 0;
  }

  .nav-menu .nav-link {
    padding: 15px;
    display: block;
    width: 100%;
  }

  .nav-toggle {
    display: flex;
  }

  .nav-toggle.active span:nth-child(2) {
    opacity: 0;
  }

  .nav-toggle.active span:nth-child(1) {
    transform: translateY(9px) rotate(45deg);
  }

  .nav-toggle.active span:nth-child(3) {
    transform: translateY(-9px) rotate(-45deg);
  }

  .footer-content {
    grid-template-columns: 1fr;
    gap: 20px;
  }
}
</style>