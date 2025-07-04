<template>
  <div class="about">
    <!-- 页面头部 -->
    <div class="page-header">
      <div class="container">
        <h1>关于我们</h1>
        <p>致力于推进联合国可持续发展目标的实现</p>
      </div>
    </div>

    <!-- 主要内容 -->
    <div class="about-content">
      <div class="container">
        <!-- 我们的使命 -->
        <section class="mission-section">
          <h2>我们的使命</h2>
          <p class="mission-text">
            我们致力于推进联合国可持续发展目标（SDGs）的实现，通过教育、倡导、合作和行动，
            为构建一个更加公平、包容和可持续的世界而努力。我们相信，每个人都有责任和能力
            为可持续发展贡献自己的力量。
          </p>
        </section>

        <!-- SDGs 介绍 -->
        <section class="sdgs-intro">
          <h2>什么是可持续发展目标（SDGs）</h2>
          <div class="sdgs-content">
            <div class="sdgs-text">
              <p>
                可持续发展目标（Sustainable Development Goals，简称SDGs）是联合国在2015年通过的
                17个全球发展目标，旨在到2030年解决世界面临的最紧迫挑战。
              </p>
              <p>
                这些目标涵盖了消除贫困、促进教育、保护环境、推动性别平等、应对气候变化等各个方面，
                为全球的可持续发展提供了清晰的路线图。
              </p>
            </div>
            <div class="sdgs-stats">
              <div class="stat-item">
                <div class="stat-number">17</div>
                <div class="stat-label">个发展目标</div>
              </div>
              <div class="stat-item">
                <div class="stat-number">169</div>
                <div class="stat-label">个具体目标</div>
              </div>
              <div class="stat-item">
                <div class="stat-number">2030</div>
                <div class="stat-label">年实现期限</div>
              </div>
            </div>
          </div>
        </section>

        <!-- 我们的工作 -->
        <section class="our-work">
          <h2>我们的工作</h2>
          <div class="work-grid">
            <div class="work-item">
              <div class="work-icon">📚</div>
              <h3>教育推广</h3>
              <p>通过各种形式的教育活动，提高公众对SDGs的认知和理解</p>
            </div>
            <div class="work-item">
              <div class="work-icon">🤝</div>
              <h3>合作伙伴</h3>
              <p>与政府、企业、NGO等建立合作关系，共同推进SDGs实施</p>
            </div>
            <div class="work-item">
              <div class="work-icon">💡</div>
              <h3>创新解决方案</h3>
              <p>开发和推广创新的解决方案，应对可持续发展挑战</p>
            </div>
            <div class="work-item">
              <div class="work-icon">📊</div>
              <h3>监测评估</h3>
              <p>跟踪和评估SDGs的实施进展，提供数据支持</p>
            </div>
          </div>
        </section>

        <!-- 团队介绍 -->
        <section class="team-section">
          <h2>我们的团队</h2>
          <div class="team-grid">
            <div class="team-member">
              <div class="member-avatar">
                👨‍🏫
              </div>
              <h3>张教授</h3>
              <p class="member-title">首席顾问</p>
              <p class="member-desc">可持续发展领域专家，拥有20年研究经验</p>
            </div>
            <div class="team-member">
              <div class="member-avatar">
                👨‍🔬
              </div>
              <h3>李博士</h3>
              <p class="member-title">项目总监</p>
              <p class="member-desc">专注于环境政策和气候变化研究</p>
            </div>
            <div class="team-member">
              <div class="member-avatar">
                👩‍💼
              </div>
              <h3>王主任</h3>
              <p class="member-title">教育主管</p>
              <p class="member-desc">致力于推广可持续发展教育</p>
            </div>
          </div>
        </section>

        <!-- 联系方式 -->
        <section class="contact-section">
          <h2>联系我们</h2>
          <div class="contact-info">
            <div class="contact-item">
              <div class="contact-icon">📧</div>
              <div>
                <h4>邮箱</h4>
                <p>info@sdgs-platform.org</p>
              </div>
            </div>
            <div class="contact-item">
              <div class="contact-icon">📞</div>
              <div>
                <h4>电话</h4>
                <p>+86 10 1234 5678</p>
              </div>
            </div>
            <div class="contact-item">
              <div class="contact-icon">📍</div>
              <div>
                <h4>地址</h4>
                <p>北京市朝阳区国贸大厦8层</p>
              </div>
            </div>
          </div>
        </section>

        <!-- 了解更多 -->
        <section class="more-info-section">
          <div style="text-align: center;">
            <h2>想了解更多？</h2>
            <p style="margin-bottom: 2rem; color: #666;">深入了解我们的组织历程、价值观念和治理结构</p>
            <router-link to="/about/more" class="more-btn">
              📖 查看更多信息
            </router-link>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<style scoped>
.about {
  min-height: 100vh;
  background-color: #f8f9fa;
}

.page-header {
  background: linear-gradient(135deg, #6366f1 0%, #4f46e5 100%);
  color: white;
  padding: 60px 0;
  text-align: center;
}

.container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 20px;
}

.page-header h1 {
  font-size: 3rem;
  margin-bottom: 1rem;
  font-weight: 700;
}

.page-header p {
  font-size: 1.2rem;
  opacity: 0.9;
}

.about-content {
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

.mission-text {
  font-size: 1.2rem;
  line-height: 1.8;
  color: #666;
  text-align: center;
  max-width: 800px;
  margin: 0 auto;
}

.sdgs-content {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 40px;
  align-items: center;
}

.sdgs-text p {
  font-size: 1.1rem;
  line-height: 1.7;
  color: #666;
  margin-bottom: 1rem;
}

.sdgs-stats {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.stat-item {
  background: white;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.stat-number {
  font-size: 2.5rem;
  font-weight: 700;
  color: #6366f1;
  margin-bottom: 8px;
}

.stat-label {
  color: #666;
  font-weight: 500;
}

.work-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 24px;
}

.work-item {
  background: white;
  padding: 32px;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s ease;
}

.work-item:hover {
  transform: translateY(-4px);
}

.work-icon {
  font-size: 3rem;
  margin-bottom: 16px;
}

.work-item h3 {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 12px;
  color: #333;
}

.work-item p {
  color: #666;
  line-height: 1.6;
}

.team-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 24px;
}

.team-member {
  background: white;
  padding: 32px;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.member-avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin: 0 auto 20px;
  background: linear-gradient(135deg, #f3f4f6, #e5e7eb);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 3rem;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.team-member h3 {
  font-size: 1.3rem;
  font-weight: 600;
  margin-bottom: 8px;
  color: #333;
}

.member-title {
  color: #6366f1;
  font-weight: 500;
  margin-bottom: 12px;
}

.member-desc {
  color: #666;
  line-height: 1.6;
}

.contact-info {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 24px;
}

.contact-item {
  background: white;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  align-items: center;
  gap: 16px;
}

.contact-icon {
  font-size: 2rem;
  flex-shrink: 0;
}

.contact-item h4 {
  font-size: 1.1rem;
  font-weight: 600;
  margin-bottom: 4px;
  color: #333;
}

.contact-item p {
  color: #666;
}

.more-btn {
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  padding: 16px 32px;
  background: linear-gradient(135deg, #6366f1, #4f46e5);
  color: white;
  text-decoration: none;
  border-radius: 50px;
  font-weight: 600;
  font-size: 1.1rem;
  transition: all 0.3s ease;
  box-shadow: 0 4px 12px rgba(99, 102, 241, 0.3);
}

.more-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(99, 102, 241, 0.4);
  background: linear-gradient(135deg, #4f46e5, #3730a3);
}

/* 响应式设计 */
@media (max-width: 768px) {
  .page-header h1 {
    font-size: 2rem;
  }
  
  .sdgs-content {
    grid-template-columns: 1fr;
  }
  
  .sdgs-stats {
    flex-direction: row;
    justify-content: space-around;
  }
  
  .work-grid,
  .team-grid {
    grid-template-columns: 1fr;
  }
}
</style>
