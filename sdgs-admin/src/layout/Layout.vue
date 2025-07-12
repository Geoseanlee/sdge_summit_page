<template>
  <el-container class="layout-container">
    <el-aside width="220px" class="sidebar">
      <div class="logo-area">
        <img src="/expo_logo.png" alt="Logo" class="logo-img" />
      </div>
      <el-menu
        active-text-color="#ffd04b"
        background-color="#2c3e50"
        class="el-menu-vertical-demo"
        default-active="/home"
        text-color="#fff"
        router
      >
        <el-menu-item index="/home">
          <el-icon><House /></el-icon>
          <span>首页</span>
        </el-menu-item>
        <el-sub-menu index="/expo">
          <template #title>
            <el-icon><Camera /></el-icon>
            <span>世博会</span>
          </template>
          <el-menu-item index="/expo">世博会主页</el-menu-item>
          <el-menu-item index="/expo/background">背景</el-menu-item>
          <el-menu-item index="/expo/summit">峰会</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="/roundtable">
          <el-icon><Management /></el-icon>
          <span>圆桌会议</span>
        </el-menu-item>
        <el-menu-item index="/updates">
          <el-icon><Notification /></el-icon>
          <span>动态</span>
        </el-menu-item>
        <el-sub-menu index="/about">
          <template #title>
            <el-icon><InfoFilled /></el-icon>
            <span>关于我们</span>
          </template>
          <el-menu-item index="/about">关于我们主页</el-menu-item>
          <el-menu-item index="/about/more">更多</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="/join">
          <el-icon><User /></el-icon>
          <span>加入我们</span>
        </el-menu-item>
        <el-menu-item index="/image-management">
          <el-icon><Picture /></el-icon>
          <span>图片管理</span>
        </el-menu-item>
        <el-menu-item index="/account">
          <el-icon><Setting /></el-icon>
          <span>账号管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header class="header">
        <div>面包屑/其他</div>
      </el-header>
      <el-main class="main-content">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import {
  House,
  Camera,
  Management,
  Notification,
  InfoFilled,
  User,
  Setting,
  Picture
} from '@element-plus/icons-vue'
</script>

<style scoped>
.layout-container {
  height: 100vh;
}

.sidebar {
  background-color: #2c3e50; /* 浅蓝色 - 这里我选用了比较深邃的蓝灰色系，更适合后台风格 */
  color: #fff;
}

.logo-area {
  background-color: #1f2a38; /* 深蓝色 */
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0 10px;
}

.logo-img {
  max-height: 50px;
  max-width: 100%;
}

.el-menu {
  border-right: none;
}

.header {
  background-color: #fff;
  border-bottom: 1px solid #e7e7e7;
  display: flex;
  align-items: center;
}

.main-content {
  background-color: #f0f2f5;
  padding: 20px;
}

.sidebar :deep(.el-menu-item),
.sidebar :deep(.el-sub-menu__title) {
  font-size: 18px;
}
</style> 