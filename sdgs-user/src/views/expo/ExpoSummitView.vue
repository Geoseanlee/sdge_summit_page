<template>
  <div class="expo-summit">
    <!-- 页面头部 -->
    <div class="page-header">
      <div class="container">
        <nav class="breadcrumb">
          <router-link to="/expo">SDGs展览</router-link>
          <span class="separator">></span>
          <span class="current">世博会峰会</span>
        </nav>
        <h1>世博会峰会</h1>
        <p>汇聚全球智慧，共商可持续发展大计</p>
      </div>
    </div>

    <!-- 主要内容 -->
    <div class="summit-content">
      <div class="container">
        <!-- 峰会概览 -->
        <section class="overview-section">
          <h2>峰会概览</h2>
          <div class="overview-grid">
            <div class="overview-card">
              <div class="overview-icon">🗓️</div>
              <h3>举办时间</h3>
              <p>2024年9月15日-20日</p>
            </div>
            <div class="overview-card">
              <div class="overview-icon">📍</div>
              <h3>举办地点</h3>
              <p>北京国际展览中心</p>
            </div>
            <div class="overview-card">
              <div class="overview-icon">👥</div>
              <h3>参与规模</h3>
              <p>200+国家代表团</p>
            </div>
            <div class="overview-card">
              <div class="overview-icon">🎯</div>
              <h3>主要议题</h3>
              <p>17个SDG主题论坛</p>
            </div>
          </div>
        </section>

        <!-- 主要议程 -->
        <section class="agenda-section">
          <h2>主要议程</h2>
          <div class="agenda-timeline">
            <div class="agenda-day">
              <div class="day-header">
                <h3>第一天 - 开幕式</h3>
                <span class="date">9月15日</span>
              </div>
              <div class="agenda-items">
                <div class="agenda-item">
                  <div class="time">09:00-10:30</div>
                  <div class="content">
                    <h4>开幕式暨高级别圆桌会议</h4>
                    <p>联合国秘书长特别致辞，各国元首及政府首脑出席</p>
                  </div>
                </div>
                <div class="agenda-item">
                  <div class="time">11:00-12:30</div>
                  <div class="content">
                    <h4>SDGs进展评估报告发布</h4>
                    <p>2024年全球可持续发展目标实施进展综合评估</p>
                  </div>
                </div>
                <div class="agenda-item">
                  <div class="time">14:00-17:30</div>
                  <div class="content">
                    <h4>主题论坛：消除贫困与零饥饿</h4>
                    <p>探讨SDG1和SDG2的实施策略与最佳实践</p>
                  </div>
                </div>
              </div>
            </div>

            <div class="agenda-day">
              <div class="day-header">
                <h3>第二天 - 健康与教育</h3>
                <span class="date">9月16日</span>
              </div>
              <div class="agenda-items">
                <div class="agenda-item">
                  <div class="time">09:00-12:00</div>
                  <div class="content">
                    <h4>全球健康与福祉论坛</h4>
                    <p>后疫情时代的健康系统重建与创新</p>
                  </div>
                </div>
                <div class="agenda-item">
                  <div class="time">14:00-17:00</div>
                  <div class="content">
                    <h4>优质教育与性别平等专题</h4>
                    <p>数字化教育转型与教育公平实现路径</p>
                  </div>
                </div>
              </div>
            </div>

            <div class="agenda-day">
              <div class="day-header">
                <h3>第三天 - 环境与气候</h3>
                <span class="date">9月17日</span>
              </div>
              <div class="agenda-items">
                <div class="agenda-item">
                  <div class="time">09:00-12:00</div>
                  <div class="content">
                    <h4>气候行动高峰论坛</h4>
                    <p>碳中和目标实现路径与国际合作机制</p>
                  </div>
                </div>
                <div class="agenda-item">
                  <div class="time">14:00-17:00</div>
                  <div class="content">
                    <h4>清洁能源与可持续城市</h4>
                    <p>绿色技术创新与智慧城市建设</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>

        <!-- 重要嘉宾 -->
        <section class="speakers-section">
          <h2>重要嘉宾</h2>
          <div class="speakers-grid">
            <div class="speaker-card">
              <div class="speaker-avatar">🧑‍💼</div>
              <h3>安东尼奥·古特雷斯</h3>
              <p class="speaker-title">联合国秘书长</p>
              <p class="speaker-topic">主题演讲：全球可持续发展的紧迫性</p>
            </div>
            <div class="speaker-card">
              <div class="speaker-avatar">👩‍🔬</div>
              <h3>玛丽亚·科学家</h3>
              <p class="speaker-title">气候变化专家</p>
              <p class="speaker-topic">气候行动与绿色转型策略</p>
            </div>
            <div class="speaker-card">
              <div class="speaker-avatar">👨‍🏫</div>
              <h3>约翰·教育家</h3>
              <p class="speaker-title">教育创新专家</p>
              <p class="speaker-topic">数字教育的未来发展趋势</p>
            </div>
            <div class="speaker-card">
              <div class="speaker-avatar">👩‍💼</div>
              <h3>艾玛·经济学家</h3>
              <p class="speaker-title">发展经济学专家</p>
              <p class="speaker-topic">可持续经济增长模式</p>
            </div>
          </div>
        </section>

        <!-- 预期成果 -->
        <section class="outcomes-section">
          <h2>预期成果</h2>
          <div class="outcomes-content">
            <div class="outcomes-text">
              <div class="outcome-item">
                <h3>📄 政策共识</h3>
                <p>形成《2024年可持续发展北京宣言》，为后续五年的SDGs实施提供指导原则。</p>
              </div>
              <div class="outcome-item">
                <h3>🤝 合作协议</h3>
                <p>签署多项国际合作协议，建立跨国界、跨领域的合作伙伴关系。</p>
              </div>
              <div class="outcome-item">
                <h3>💡 创新方案</h3>
                <p>发布可持续发展创新解决方案集，推广最佳实践案例。</p>
              </div>
            </div>
            <div class="outcomes-stats">
              <div class="outcome-stat">
                <div class="stat-number">500+</div>
                <div class="stat-label">参会代表</div>
              </div>
              <div class="outcome-stat">
                <div class="stat-number">50+</div>
                <div class="stat-label">专题论坛</div>
              </div>
              <div class="outcome-stat">
                <div class="stat-number">100+</div>
                <div class="stat-label">合作项目</div>
              </div>
            </div>
          </div>
        </section>

        <!-- 参会信息 -->
        <section class="participation-section">
          <h2>参会信息</h2>
          <div class="participation-cards">
            <div class="participation-card">
              <h3>🎫 注册参会</h3>
              <ul>
                <li>政府代表团：官方渠道申请</li>
                <li>企业代表：在线注册系统</li>
                <li>学术机构：邀请函申请</li>
                <li>NGO组织：合作伙伴推荐</li>
              </ul>
              <el-button type="primary" size="large">立即注册</el-button>
            </div>
            <div class="participation-card">
              <h3>📺 在线观看</h3>
              <ul>
                <li>官方网站全程直播</li>
                <li>多语言同声传译</li>
                <li>互动问答环节</li>
                <li>会后视频回放</li>
              </ul>
              <el-button size="large">预约观看</el-button>
            </div>
            <div class="participation-card">
              <h3>📱 媒体报道</h3>
              <ul>
                <li>实时新闻更新</li>
                <li>专家访谈直播</li>
                <li>社交媒体互动</li>
                <li>官方发布平台</li>
              </ul>
              <el-button size="large">关注动态</el-button>
            </div>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ElMessage } from 'element-plus'

// 这里可以添加页面逻辑
</script>

<style scoped>
.expo-summit {
  min-height: 100vh;
  background-color: #f8f9fa;
}

.page-header {
  background: linear-gradient(135deg, #059669 0%, #047857 100%);
  color: white;
  padding: 40px 0 60px;
}

.container {
  max-width: 1200px;
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

.summit-content {
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

/* 概览卡片样式 */
.overview-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 24px;
}

.overview-card {
  background: white;
  padding: 32px;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s ease;
}

.overview-card:hover {
  transform: translateY(-8px);
}

.overview-icon {
  font-size: 3rem;
  margin-bottom: 16px;
}

.overview-card h3 {
  font-size: 1.3rem;
  font-weight: 600;
  margin-bottom: 12px;
  color: #333;
}

.overview-card p {
  color: #666;
  font-size: 1.1rem;
  font-weight: 500;
}

/* 议程时间线样式 */
.agenda-timeline {
  max-width: 900px;
  margin: 0 auto;
}

.agenda-day {
  margin-bottom: 40px;
  background: white;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.day-header {
  background: linear-gradient(135deg, #059669, #047857);
  color: white;
  padding: 20px 30px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.day-header h3 {
  font-size: 1.3rem;
  font-weight: 600;
  margin: 0;
}

.date {
  font-size: 1rem;
  font-weight: 500;
}

.agenda-items {
  padding: 30px;
}

.agenda-item {
  display: flex;
  gap: 24px;
  margin-bottom: 24px;
  padding-bottom: 24px;
  border-bottom: 1px solid #e5e7eb;
}

.agenda-item:last-child {
  margin-bottom: 0;
  padding-bottom: 0;
  border-bottom: none;
}

.time {
  flex: 0 0 120px;
  font-weight: 600;
  color: #059669;
  font-size: 1.1rem;
}

.content h4 {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 8px;
  color: #333;
}

.content p {
  color: #666;
  line-height: 1.6;
}

/* 嘉宾卡片样式 */
.speakers-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 24px;
}

.speaker-card {
  background: white;
  padding: 32px;
  border-radius: 16px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s ease;
}

.speaker-card:hover {
  transform: translateY(-4px);
}

.speaker-avatar {
  font-size: 4rem;
  margin-bottom: 16px;
}

.speaker-card h3 {
  font-size: 1.3rem;
  font-weight: 600;
  margin-bottom: 8px;
  color: #333;
}

.speaker-title {
  color: #059669;
  font-weight: 500;
  margin-bottom: 12px;
}

.speaker-topic {
  color: #666;
  line-height: 1.6;
  font-style: italic;
}

/* 预期成果样式 */
.outcomes-content {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 40px;
  align-items: start;
}

.outcome-item {
  margin-bottom: 24px;
  padding: 24px;
  background: white;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.outcome-item h3 {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 12px;
  color: #333;
}

.outcome-item p {
  color: #666;
  line-height: 1.6;
}

.outcomes-stats {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.outcome-stat {
  background: white;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.outcome-stat .stat-number {
  font-size: 2.5rem;
  font-weight: 700;
  color: #059669;
  margin-bottom: 8px;
}

.outcome-stat .stat-label {
  color: #666;
  font-weight: 500;
}

/* 参会信息样式 */
.participation-cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
  gap: 24px;
}

.participation-card {
  background: white;
  padding: 32px;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.participation-card h3 {
  font-size: 1.3rem;
  font-weight: 600;
  margin-bottom: 20px;
  color: #333;
}

.participation-card ul {
  list-style: none;
  padding: 0;
  margin-bottom: 24px;
  text-align: left;
}

.participation-card ul li {
  padding: 8px 0;
  color: #666;
  border-bottom: 1px solid #f3f4f6;
}

.participation-card ul li:before {
  content: '✓';
  color: #059669;
  font-weight: bold;
  margin-right: 10px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .page-header h1 {
    font-size: 2rem;
  }
  
  .day-header {
    flex-direction: column;
    text-align: center;
    gap: 8px;
  }
  
  .agenda-item {
    flex-direction: column;
    gap: 12px;
  }
  
  .time {
    flex: none;
  }
  
  .outcomes-content {
    grid-template-columns: 1fr;
  }
  
  .outcomes-stats {
    flex-direction: row;
    justify-content: space-around;
  }
}
</style> 