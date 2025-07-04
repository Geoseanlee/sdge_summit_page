<template>
  <div class="expo-background">
    <!-- 页面头部 -->
    <div class="page-header">
      <div class="container">
        <nav class="breadcrumb">
          <router-link to="/expo">SDGs展览</router-link>
          <span class="separator">></span>
          <span class="current">世博会背景</span>
        </nav>
        <h1>世博会背景</h1>
        <p>了解可持续发展目标世博会的起源与发展历程</p>
      </div>
    </div>

    <!-- 主要内容 -->
    <div class="background-content">
      <div class="container">
        <!-- 历史发展 -->
        <section class="history-section">
          <h2>历史发展</h2>
          <div class="timeline">
            <div class="timeline-item">
              <div class="timeline-date">2015年</div>
              <div class="timeline-content">
                <h3>联合国2030年可持续发展议程</h3>
                <p>联合国大会通过《2030年可持续发展议程》，确立了17个可持续发展目标（SDGs），标志着全球可持续发展进入新纪元。</p>
              </div>
            </div>
            <div class="timeline-item">
              <div class="timeline-date">2018年</div>
              <div class="timeline-content">
                <h3>首届SDGs世博会概念提出</h3>
                <p>在联合国可持续发展高级别政治论坛上，首次提出举办专门展示SDGs成果的世博会概念，得到各国积极响应。</p>
              </div>
            </div>
            <div class="timeline-item">
              <div class="timeline-date">2020年</div>
              <div class="timeline-content">
                <h3>SDGs世博会筹备启动</h3>
                <p>尽管受到疫情影响，各国依然坚持推进SDGs世博会的筹备工作，并创新性地采用线上线下结合的展示模式。</p>
              </div>
            </div>
            <div class="timeline-item">
              <div class="timeline-date">2023年</div>
              <div class="timeline-content">
                <h3>全面筹备阶段</h3>
                <p>进入全面筹备阶段，各国展馆设计完成，技术平台搭建就绪，预计将成为史上最具影响力的可持续发展主题展览。</p>
              </div>
            </div>
          </div>
        </section>

        <!-- 世博会意义 -->
        <section class="significance-section">
          <h2>世博会意义</h2>
          <div class="significance-grid">
            <div class="significance-card">
              <div class="card-icon">🎯</div>
              <h3>目标展示</h3>
              <p>全面展示17个可持续发展目标的内涵、意义和实现路径，让公众深入了解SDGs的重要性。</p>
            </div>
            <div class="significance-card">
              <div class="card-icon">🌍</div>
              <h3>全球合作</h3>
              <p>促进各国在可持续发展领域的交流与合作，分享最佳实践，共同应对全球挑战。</p>
            </div>
            <div class="significance-card">
              <div class="card-icon">💡</div>
              <h3>创新展示</h3>
              <p>展示最新的可持续发展技术和解决方案，推动创新成果的转化与应用。</p>
            </div>
            <div class="significance-card">
              <div class="card-icon">📚</div>
              <h3>教育意义</h3>
              <p>提高全球公众的可持续发展意识，培养下一代的环保理念和责任感。</p>
            </div>
          </div>
        </section>

        <!-- 核心理念 -->
        <section class="concept-section">
          <h2>核心理念</h2>
          <div class="concept-content">
            <div class="concept-text">
              <div class="concept-item">
                <h3>🌱 可持续性</h3>
                <p>世博会本身就是可持续发展理念的体现，从场馆建设到运营管理，都严格遵循绿色、低碳、循环的原则。</p>
              </div>
              <div class="concept-item">
                <h3>🤝 包容性</h3>
                <p>欢迎全球各国、各地区、各行业参与，特别关注发展中国家和弱势群体，确保每个人都能从中受益。</p>
              </div>
              <div class="concept-item">
                <h3>🔄 创新性</h3>
                <p>运用最新的数字技术、虚拟现实等手段，创造沉浸式、互动式的展示体验，让参观者深度参与。</p>
              </div>
            </div>
            <div class="concept-stats">
              <div class="stat-box">
                <div class="stat-number">193</div>
                <div class="stat-label">参与国家</div>
              </div>
              <div class="stat-box">
                <div class="stat-number">1000+</div>
                <div class="stat-label">展示项目</div>
              </div>
              <div class="stat-box">
                <div class="stat-number">50万+</div>
                <div class="stat-label">预计参观人次</div>
              </div>
            </div>
          </div>
        </section>

        <!-- 参与方式 -->
        <section class="participation-section">
          <h2>如何参与</h2>
          <div class="participation-steps">
            <div class="step-item">
              <div class="step-number">1</div>
              <h3>在线注册</h3>
              <p>访问官方网站完成参观者注册，选择感兴趣的展览内容。</p>
            </div>
            <div class="step-item">
              <div class="step-number">2</div>
              <h3>选择参观方式</h3>
              <p>可选择线上虚拟参观或线下实地参观，灵活安排参观时间。</p>
            </div>
            <div class="step-item">
              <div class="step-number">3</div>
              <h3>互动体验</h3>
              <p>参与各种互动活动，与专家学者交流，获得深度学习体验。</p>
            </div>
            <div class="step-item">
              <div class="step-number">4</div>
              <h3>分享传播</h3>
              <p>将学到的知识分享给更多人，成为可持续发展的倡导者。</p>
            </div>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script setup>
// 这里可以添加页面逻辑
</script>

<style scoped>
.expo-background {
  min-height: 100vh;
  background-color: #f8f9fa;
}

.page-header {
  background: linear-gradient(135deg, #2c5aa0 0%, #1e3a8a 100%);
  color: white;
  padding: 40px 0 60px;
}

.container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 20px;
}

.breadcrumb {
  margin-bottom: 20px;
  font-size: 0.9rem;
}

.breadcrumb a {
  color: rgba(255, 255, 255, 0.8);
  text-decoration: none;
}

.breadcrumb a:hover {
  color: white;
}

.separator {
  margin: 0 10px;
  color: rgba(255, 255, 255, 0.6);
}

.current {
  color: white;
}

.page-header h1 {
  font-size: 2.5rem;
  margin-bottom: 1rem;
  font-weight: 700;
}

.page-header p {
  font-size: 1.1rem;
  opacity: 0.9;
}

.background-content {
  padding: 60px 0;
}

section {
  margin-bottom: 60px;
}

section h2 {
  font-size: 2rem;
  font-weight: 600;
  margin-bottom: 2rem;
  color: #333;
  text-align: center;
}

/* 时间线样式 */
.timeline {
  position: relative;
  max-width: 800px;
  margin: 0 auto;
}

.timeline::before {
  content: '';
  position: absolute;
  left: 50%;
  top: 0;
  bottom: 0;
  width: 2px;
  background: #e5e7eb;
  transform: translateX(-50%);
}

.timeline-item {
  display: flex;
  margin-bottom: 40px;
  position: relative;
}

.timeline-item:nth-child(odd) {
  flex-direction: row;
}

.timeline-item:nth-child(even) {
  flex-direction: row-reverse;
}

.timeline-date {
  flex: 0 0 100px;
  background: #2563eb;
  color: white;
  padding: 10px 20px;
  border-radius: 20px;
  text-align: center;
  font-weight: 600;
  height: fit-content;
  position: relative;
  z-index: 2;
}

.timeline-content {
  flex: 1;
  background: white;
  margin: 0 30px;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.timeline-content h3 {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 8px;
  color: #333;
}

.timeline-content p {
  color: #666;
  line-height: 1.6;
}

/* 意义卡片样式 */
.significance-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 24px;
}

.significance-card {
  background: white;
  padding: 32px;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s ease;
}

.significance-card:hover {
  transform: translateY(-8px);
}

.card-icon {
  font-size: 3rem;
  margin-bottom: 16px;
}

.significance-card h3 {
  font-size: 1.3rem;
  font-weight: 600;
  margin-bottom: 12px;
  color: #333;
}

.significance-card p {
  color: #666;
  line-height: 1.6;
}

/* 核心理念样式 */
.concept-content {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 40px;
  align-items: start;
}

.concept-item {
  margin-bottom: 24px;
  padding: 20px;
  background: white;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.concept-item h3 {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 8px;
  color: #333;
}

.concept-item p {
  color: #666;
  line-height: 1.6;
}

.concept-stats {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.stat-box {
  background: white;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.stat-number {
  font-size: 2.5rem;
  font-weight: 700;
  color: #2563eb;
  margin-bottom: 8px;
}

.stat-label {
  color: #666;
  font-weight: 500;
}

/* 参与步骤样式 */
.participation-steps {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 24px;
}

.step-item {
  background: white;
  padding: 32px;
  border-radius: 16px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  position: relative;
}

.step-number {
  width: 50px;
  height: 50px;
  background: linear-gradient(135deg, #2563eb, #1d4ed8);
  color: white;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.5rem;
  font-weight: 700;
  margin: 0 auto 16px;
}

.step-item h3 {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 12px;
  color: #333;
}

.step-item p {
  color: #666;
  line-height: 1.6;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .page-header h1 {
    font-size: 2rem;
  }
  
  .timeline::before {
    left: 30px;
  }
  
  .timeline-item {
    flex-direction: column !important;
    padding-left: 60px;
  }
  
  .timeline-date {
    position: absolute;
    left: 0;
    top: 0;
  }
  
  .timeline-content {
    margin: 0;
    margin-top: 60px;
  }
  
  .concept-content {
    grid-template-columns: 1fr;
  }
  
  .concept-stats {
    flex-direction: row;
    justify-content: space-around;
  }
}
</style> 