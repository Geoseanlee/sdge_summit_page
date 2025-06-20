<script setup>
// 这里可以添加页面逻辑
</script>

<template>
  <div class="home">
    <!-- 顶部横幅区域 -->
    <section class="hero-section">
      <div class="hero-content">
        <h1 class="hero-title">可持续发展目标 (SDGs)</h1>
        <p class="hero-subtitle">共同构建可持续发展的美好未来</p>
        <div class="hero-buttons">
          <el-button type="primary" size="large" @click="$router.push('/expo')">
            探索展览
          </el-button>
          <el-button size="large" @click="$router.push('/about')">
            了解更多
          </el-button>
        </div>
      </div>
    </section>

    <!-- 功能导航区域 -->
    <section class="features-section">
      <div class="container">
        <h2 class="section-title">探索 SDGs</h2>
        <div class="features-grid">
          <div class="feature-card" @click="$router.push('/expo')">
            <div class="feature-icon">🎯</div>
            <h3>SDGs 展览</h3>
            <p>深入了解17个可持续发展目标的内容和意义</p>
          </div>
          <div class="feature-card" @click="$router.push('/roundtable')">
            <div class="feature-icon">💬</div>
            <h3>圆桌会议</h3>
            <p>参与专家学者的深度讨论和交流</p>
          </div>
          <div class="feature-card" @click="$router.push('/news')">
            <div class="feature-icon">📰</div>
            <h3>新闻资讯</h3>
            <p>获取最新的可持续发展动态和资讯</p>
          </div>
          <div class="feature-card" @click="$router.push('/join-us')">
            <div class="feature-icon">🤝</div>
            <h3>加入我们</h3>
            <p>成为可持续发展的倡导者和实践者</p>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
.home {
  min-height: 100vh;
}

.hero-section {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 120px 20px 80px;
  text-align: center;
}

.hero-content {
  max-width: 800px;
  margin: 0 auto;
}

.hero-title {
  font-size: 3.5rem;
  font-weight: 700;
  margin-bottom: 1rem;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
}

.hero-subtitle {
  font-size: 1.5rem;
  margin-bottom: 2rem;
  opacity: 0.9;
}

.hero-buttons {
  display: flex;
  gap: 1rem;
  justify-content: center;
  flex-wrap: wrap;
}

.features-section {
  padding: 80px 20px;
  background-color: #f8f9fa;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
}

.section-title {
  text-align: center;
  font-size: 2.5rem;
  font-weight: 600;
  margin-bottom: 3rem;
  color: #333;
}

.features-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 2rem;
}

.feature-card {
  background: white;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: all 0.3s ease;
  cursor: pointer;
}

.feature-card:hover {
  transform: translateY(-8px);
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.15);
}

.feature-icon {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.feature-card h3 {
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 1rem;
  color: #333;
}

.feature-card p {
  color: #666;
  line-height: 1.6;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .hero-title {
    font-size: 2.5rem;
  }
  
  .hero-subtitle {
    font-size: 1.2rem;
  }
  
  .hero-buttons {
    flex-direction: column;
    align-items: center;
  }
  
  .section-title {
    font-size: 2rem;
  }
  
  .features-grid {
    grid-template-columns: 1fr;
  }
}
</style>
