<script setup>
// 这里可以添加页面逻辑
</script>

<template>
  <div class="home">
    <!-- 顶部横幅区域 -->
    <section class="hero-section">
      <div class="hero-content">
        <h1 class="hero-title page-title">可持续发展目标 (SDGs)</h1>
        <p class="hero-subtitle text-content">共同构建可持续发展的美好未来</p>
        <div class="hero-buttons">
          <el-button type="primary" size="large" class="btn-mobile" @click="$router.push('/expo')">
            探索展览
          </el-button>
          <el-button size="large" class="btn-mobile" @click="$router.push('/about')">
            了解更多
          </el-button>
        </div>
      </div>
    </section>

    <!-- 功能导航区域 -->
    <section class="features-section section">
      <div class="content-wrapper">
        <h2 class="section-title">探索 SDGs</h2>
        <div class="feature-grid">
          <div class="feature-card" @click="$router.push('/expo')">
            <div class="feature-icon">🎯</div>
            <h3 class="card-title">SDGs 展览</h3>
            <p class="text-content">深入了解17个可持续发展目标的内容和意义</p>
          </div>
          <div class="feature-card" @click="$router.push('/roundtable')">
            <div class="feature-icon">💬</div>
            <h3 class="card-title">圆桌会议</h3>
            <p class="text-content">参与专家学者的深度讨论和交流</p>
          </div>
          <div class="feature-card" @click="$router.push('/news')">
            <div class="feature-icon">📰</div>
            <h3 class="card-title">新闻资讯</h3>
            <p class="text-content">获取最新的可持续发展动态和资讯</p>
          </div>
          <div class="feature-card" @click="$router.push('/join-us')">
            <div class="feature-icon">🤝</div>
            <h3 class="card-title">加入我们</h3>
            <p class="text-content">成为可持续发展的倡导者和实践者</p>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
/* =================================
   首页专用样式
   ================================= */
.home {
  width: 100%;
}

.hero-section {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: calc(var(--spacing-xxl) * 3) var(--spacing-lg) var(--spacing-xxl);
  text-align: center;
  position: relative;
  overflow: hidden;
}

@media (max-width: 767px) {
  .hero-section {
    padding: calc(var(--spacing-xl) * 2) var(--spacing-sm) var(--spacing-xl);
  }
}

.hero-content {
  max-width: var(--container-sm);
  margin: 0 auto;
  position: relative;
  z-index: 2;
}

.hero-title {
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
  color: white;
}

.hero-subtitle {
  opacity: 0.95;
  color: rgba(255, 255, 255, 0.9);
  font-size: var(--font-size-xl);
}

@media (max-width: 767px) {
  .hero-subtitle {
    font-size: var(--font-size-lg);
  }
}

.hero-buttons {
  display: flex;
  gap: var(--spacing-md);
  justify-content: center;
  flex-wrap: wrap;
  margin-top: var(--spacing-lg);
}

@media (max-width: 767px) {
  .hero-buttons {
    flex-direction: column;
    align-items: center;
    gap: var(--spacing-sm);
  }
}

.features-section {
  background-color: #f8f9fa;
}

.feature-card {
  background: white;
  padding: var(--spacing-lg);
  border-radius: var(--radius-xl);
  box-shadow: var(--shadow-base);
  text-align: center;
  transition: var(--transition-base);
  cursor: pointer;
  border: 1px solid rgba(0, 0, 0, 0.05);
}

.feature-card:hover {
  transform: translateY(-8px);
  box-shadow: var(--shadow-xl);
}

@media (max-width: 767px) {
  .feature-card {
    padding: var(--spacing-md);
    border-radius: var(--radius-lg);
  }
  
  .feature-card:hover {
    transform: translateY(-4px); /* 减少移动端的变换 */
  }
}

.feature-icon {
  font-size: 3rem;
  margin-bottom: var(--spacing-md);
  display: block;
}

@media (max-width: 767px) {
  .feature-icon {
    font-size: 2.5rem;
    margin-bottom: var(--spacing-sm);
  }
}

/* 响应式文本大小调整 */
@media (max-width: 480px) {
  .hero-title {
    font-size: var(--font-size-2xl) !important;
  }
  
  .hero-subtitle {
    font-size: var(--font-size-base) !important;
  }
}

/* 针对触摸设备的优化 */
@media (hover: none) and (pointer: coarse) {
  .feature-card:hover {
    transform: none;
  }
  
  .feature-card:active {
    transform: scale(0.98);
    box-shadow: var(--shadow-md);
  }
}

/* 高对比度模式支持 */
@media (prefers-contrast: high) {
  .feature-card {
    border: 2px solid #333;
  }
  
  .hero-section {
    background: #000;
  }
}

/* 减少动画偏好支持 */
@media (prefers-reduced-motion: reduce) {
  .feature-card {
    transition: none;
  }
  
  .feature-card:hover {
    transform: none;
  }
}
</style>
