<template>
  <div class="about-more">
    <!-- 页面头部 -->
    <div class="page-header">
      <div class="container">
        <nav class="breadcrumb">
          <router-link to="/about">关于我们</router-link>
          <span class="separator">></span>
          <span class="current">更多信息</span>
        </nav>
        <h1>更多信息</h1>
        <p>深入了解我们的组织历程、价值观念和未来愿景</p>
      </div>
    </div>

    <!-- 主要内容 -->
    <div class="more-content">
      <div class="container">
        <!-- 组织历程 -->
        <section class="history-section">
          <h2>组织发展历程</h2>
          <div class="timeline">
            <div class="timeline-item">
              <div class="timeline-year">2015</div>
              <div class="timeline-content">
                <h3>组织成立</h3>
                <p>在联合国通过《2030年可持续发展议程》的背景下，我们的组织正式成立，致力于推动SDGs在全球范围内的实施。</p>
              </div>
            </div>
            <div class="timeline-item">
              <div class="timeline-year">2017</div>
              <div class="timeline-content">
                <h3>首个重大项目</h3>
                <p>启动"教育无界限"项目，为发展中国家的儿童提供优质教育资源，惠及超过10万名学生。</p>
              </div>
            </div>
            <div class="timeline-item">
              <div class="timeline-year">2019</div>
              <div class="timeline-content">
                <h3>国际认可</h3>
                <p>获得联合国可持续发展解决方案网络（SDSN）正式认证，成为官方合作伙伴组织。</p>
              </div>
            </div>
            <div class="timeline-item">
              <div class="timeline-year">2021</div>
              <div class="timeline-content">
                <h3>数字化转型</h3>
                <p>建立全球第一个SDGs数字化监测平台，为各国政府和组织提供实时数据支持。</p>
              </div>
            </div>
            <div class="timeline-item">
              <div class="timeline-year">2023</div>
              <div class="timeline-content">
                <h3>全球网络</h3>
                <p>合作网络扩展至全球150个国家，拥有超过1000个合作伙伴和5万名志愿者。</p>
              </div>
            </div>
          </div>
        </section>

        <!-- 核心价值观 -->
        <section class="values-section">
          <h2>核心价值观</h2>
          <div class="values-grid">
            <div class="value-card">
              <div class="value-icon">🌱</div>
              <h3>可持续性</h3>
              <p>我们相信可持续发展是人类未来的唯一出路，致力于在经济、社会和环境三个维度实现平衡发展。</p>
              <div class="value-details">
                <ul>
                  <li>推广绿色技术和清洁能源</li>
                  <li>促进循环经济发展</li>
                  <li>保护生物多样性</li>
                  <li>倡导可持续消费模式</li>
                </ul>
              </div>
            </div>
            <div class="value-card">
              <div class="value-icon">🤝</div>
              <h3>包容性</h3>
              <p>我们坚持"不让任何人掉队"的原则，特别关注弱势群体的需求，确保发展成果惠及所有人。</p>
              <div class="value-details">
                <ul>
                  <li>消除性别不平等</li>
                  <li>减少收入差距</li>
                  <li>关爱残障人士</li>
                  <li>支持少数民族发展</li>
                </ul>
              </div>
            </div>
            <div class="value-card">
              <div class="value-icon">🔗</div>
              <h3>合作共赢</h3>
              <p>我们深信只有通过全球合作才能解决人类面临的共同挑战，致力于建立开放、透明的合作平台。</p>
              <div class="value-details">
                <ul>
                  <li>促进南南合作</li>
                  <li>加强技术转移</li>
                  <li>建设伙伴关系</li>
                  <li>分享最佳实践</li>
                </ul>
              </div>
            </div>
            <div class="value-card">
              <div class="value-icon">💡</div>
              <h3>创新驱动</h3>
              <p>我们拥抱科技创新，运用最新的数字技术和人工智能，为可持续发展提供创新解决方案。</p>
              <div class="value-details">
                <ul>
                  <li>开发智能监测系统</li>
                  <li>应用大数据分析</li>
                  <li>推广数字化教育</li>
                  <li>创新金融工具</li>
                </ul>
              </div>
            </div>
          </div>
        </section>

        <!-- 治理结构 -->
        <section class="governance-section">
          <h2>治理结构</h2>
          <div class="governance-content">
            <div class="governance-chart">
              <div class="org-level">
                <h3>理事会</h3>
                <p>最高决策机构，由来自各大洲的15名杰出人士组成</p>
              </div>
              <div class="org-level">
                <h3>执行委员会</h3>
                <p>负责日常运营管理，包括战略规划、资源配置和项目监督</p>
              </div>
              <div class="org-level">
                <h3>专业委员会</h3>
                <div class="committees">
                  <span class="committee">教育委员会</span>
                  <span class="committee">环境委员会</span>
                  <span class="committee">健康委员会</span>
                  <span class="committee">经济委员会</span>
                </div>
              </div>
              <div class="org-level">
                <h3>地区办事处</h3>
                <p>分布在全球六大洲，负责区域项目实施和伙伴关系管理</p>
              </div>
            </div>
            <div class="governance-principles">
              <h3>治理原则</h3>
              <div class="principle-list">
                <div class="principle-item">
                  <span class="principle-icon">⚖️</span>
                  <div class="principle-content">
                    <h4>透明度</h4>
                    <p>所有决策过程公开透明，财务报告定期向公众披露</p>
                  </div>
                </div>
                <div class="principle-item">
                  <span class="principle-icon">📊</span>
                  <div class="principle-content">
                    <h4>问责制</h4>
                    <p>建立完善的监督机制，确保项目执行的有效性和影响力</p>
                  </div>
                </div>
                <div class="principle-item">
                  <span class="principle-icon">👥</span>
                  <div class="principle-content">
                    <h4>多元参与</h4>
                    <p>鼓励各利益相关方参与决策，确保代表性和民主性</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>

        <!-- 财务信息 -->
        <section class="finance-section">
          <h2>财务透明度</h2>
          <div class="finance-content">
            <div class="finance-overview">
              <h3>2023年财务概览</h3>
              <div class="finance-stats">
                <div class="finance-stat">
                  <div class="stat-value">$125M</div>
                  <div class="stat-label">总收入</div>
                </div>
                <div class="finance-stat">
                  <div class="stat-value">$118M</div>
                  <div class="stat-label">项目支出</div>
                </div>
                <div class="finance-stat">
                  <div class="stat-value">94.4%</div>
                  <div class="stat-label">项目资金占比</div>
                </div>
                <div class="finance-stat">
                  <div class="stat-value">5.6%</div>
                  <div class="stat-label">管理费用占比</div>
                </div>
              </div>
            </div>
            <div class="funding-sources">
              <h3>资金来源</h3>
              <div class="funding-chart">
                <div class="funding-item">
                  <span class="funding-bar" style="width: 45%; background: #3b82f6;"></span>
                  <span class="funding-label">政府拨款 (45%)</span>
                </div>
                <div class="funding-item">
                  <span class="funding-bar" style="width: 30%; background: #10b981;"></span>
                  <span class="funding-label">企业捐赠 (30%)</span>
                </div>
                <div class="funding-item">
                  <span class="funding-bar" style="width: 15%; background: #f59e0b;"></span>
                  <span class="funding-label">基金会支持 (15%)</span>
                </div>
                <div class="funding-item">
                  <span class="funding-bar" style="width: 10%; background: #ef4444;"></span>
                  <span class="funding-label">个人捐赠 (10%)</span>
                </div>
              </div>
            </div>
          </div>
        </section>

        <!-- 影响力报告 -->
        <section class="impact-section">
          <h2>影响力成果</h2>
          <div class="impact-content">
            <div class="impact-summary">
              <h3>累计影响力（2015-2023）</h3>
              <div class="impact-stats">
                <div class="impact-stat">
                  <div class="impact-number">500万+</div>
                  <div class="impact-desc">直接受益人群</div>
                </div>
                <div class="impact-stat">
                  <div class="impact-number">2000+</div>
                  <div class="impact-desc">项目执行数量</div>
                </div>
                <div class="impact-stat">
                  <div class="impact-number">150</div>
                  <div class="impact-desc">覆盖国家数量</div>
                </div>
                <div class="impact-stat">
                  <div class="impact-number">17</div>
                  <div class="impact-desc">涉及SDG目标</div>
                </div>
              </div>
            </div>
            
            <div class="sdg-progress">
              <h3>SDG目标贡献度</h3>
              <div class="sdg-bars">
                <div class="sdg-bar-item">
                  <span class="sdg-label">SDG 1 - 消除贫困</span>
                  <div class="progress-bar">
                    <div class="progress-fill" style="width: 85%;"></div>
                  </div>
                  <span class="progress-value">85%</span>
                </div>
                <div class="sdg-bar-item">
                  <span class="sdg-label">SDG 4 - 优质教育</span>
                  <div class="progress-bar">
                    <div class="progress-fill" style="width: 92%;"></div>
                  </div>
                  <span class="progress-value">92%</span>
                </div>
                <div class="sdg-bar-item">
                  <span class="sdg-label">SDG 13 - 气候行动</span>
                  <div class="progress-bar">
                    <div class="progress-fill" style="width: 78%;"></div>
                  </div>
                  <span class="progress-value">78%</span>
                </div>
                <div class="sdg-bar-item">
                  <span class="sdg-label">SDG 17 - 伙伴关系</span>
                  <div class="progress-bar">
                    <div class="progress-fill" style="width: 96%;"></div>
                  </div>
                  <span class="progress-value">96%</span>
                </div>
              </div>
            </div>
          </div>
        </section>

        <!-- 联系方式 -->
        <section class="contact-section">
          <h2>联系我们</h2>
          <div class="contact-content">
            <div class="contact-info">
              <div class="contact-item">
                <h3>🏢 全球总部</h3>
                <p>地址：纽约联合国广场1号<br>
                邮编：10017<br>
                电话：+1-212-963-1234<br>
                邮箱：info@sdgs-global.org</p>
              </div>
              <div class="contact-item">
                <h3>🌏 亚太区域办事处</h3>
                <p>地址：北京市朝阳区建国门外大街1号<br>
                邮编：100004<br>
                电话：+86-10-8531-0000<br>
                邮箱：asia@sdgs-global.org</p>
              </div>
              <div class="contact-item">
                <h3>📱 社交媒体</h3>
                <div class="social-links">
                  <a href="#" class="social-link">微博</a>
                  <a href="#" class="social-link">微信</a>
                  <a href="#" class="social-link">LinkedIn</a>
                  <a href="#" class="social-link">Twitter</a>
                </div>
              </div>
            </div>
            
            <div class="quick-contact">
              <h3>快速联系</h3>
              <el-form @submit.prevent="submitContact">
                <el-form-item>
                  <el-input v-model="contactForm.name" placeholder="您的姓名" />
                </el-form-item>
                <el-form-item>
                  <el-input v-model="contactForm.email" placeholder="邮箱地址" />
                </el-form-item>
                <el-form-item>
                  <el-select v-model="contactForm.type" placeholder="咨询类型" style="width: 100%;">
                    <el-option label="合作咨询" value="cooperation" />
                    <el-option label="志愿服务" value="volunteer" />
                    <el-option label="媒体采访" value="media" />
                    <el-option label="其他" value="other" />
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-input 
                    v-model="contactForm.message" 
                    type="textarea" 
                    :rows="4" 
                    placeholder="请描述您的咨询内容..."
                  />
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitContact" style="width: 100%;">
                    发送消息
                  </el-button>
                </el-form-item>
              </el-form>
            </div>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { ElMessage } from 'element-plus'

// 联系表单
const contactForm = reactive({
  name: '',
  email: '',
  type: '',
  message: ''
})

// 提交联系表单
const submitContact = () => {
  if (!contactForm.name || !contactForm.email || !contactForm.message) {
    ElMessage.warning('请填写必要信息')
    return
  }
  
  // 这里可以添加发送逻辑
  ElMessage.success('消息发送成功，我们会尽快回复您！')
  
  // 重置表单
  contactForm.name = ''
  contactForm.email = ''
  contactForm.type = ''
  contactForm.message = ''
}
</script>

<style scoped>
.about-more {
  min-height: 100vh;
  background-color: #f8f9fa;
}

.page-header {
  background: linear-gradient(135deg, #7c3aed 0%, #5b21b6 100%);
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

.more-content {
  padding: 60px 0;
}

section {
  margin-bottom: 80px;
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
  background: #7c3aed;
  transform: translateX(-50%);
}

.timeline-item {
  display: flex;
  margin-bottom: 50px;
  position: relative;
}

.timeline-item:nth-child(odd) {
  flex-direction: row;
}

.timeline-item:nth-child(even) {
  flex-direction: row-reverse;
}

.timeline-year {
  flex: 0 0 80px;
  background: #7c3aed;
  color: white;
  padding: 12px;
  border-radius: 50%;
  text-align: center;
  font-weight: 600;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  z-index: 2;
}

.timeline-content {
  flex: 1;
  background: white;
  margin: 0 30px;
  padding: 30px;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.timeline-content h3 {
  font-size: 1.3rem;
  font-weight: 600;
  margin-bottom: 12px;
  color: #333;
}

.timeline-content p {
  color: #666;
  line-height: 1.6;
}

/* 价值观卡片样式 */
.values-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 32px;
}

.value-card {
  background: white;
  padding: 40px;
  border-radius: 20px;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s ease;
}

.value-card:hover {
  transform: translateY(-8px);
}

.value-icon {
  font-size: 4rem;
  margin-bottom: 20px;
}

.value-card h3 {
  font-size: 1.4rem;
  font-weight: 600;
  margin-bottom: 16px;
  color: #333;
}

.value-card > p {
  color: #666;
  line-height: 1.6;
  margin-bottom: 24px;
}

.value-details ul {
  list-style: none;
  padding: 0;
  text-align: left;
}

.value-details li {
  padding: 8px 0;
  color: #555;
  border-bottom: 1px solid #f3f4f6;
}

.value-details li:before {
  content: '✓';
  color: #7c3aed;
  font-weight: bold;
  margin-right: 10px;
}

/* 治理结构样式 */
.governance-content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 40px;
}

.governance-chart {
  background: white;
  padding: 32px;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.org-level {
  margin-bottom: 24px;
  padding: 20px;
  background: #f8f9fa;
  border-radius: 12px;
  text-align: center;
}

.org-level h3 {
  color: #7c3aed;
  margin-bottom: 8px;
  font-size: 1.2rem;
}

.committees {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  justify-content: center;
  margin-top: 12px;
}

.committee {
  background: #7c3aed;
  color: white;
  padding: 4px 12px;
  border-radius: 16px;
  font-size: 0.8rem;
}

.governance-principles {
  background: white;
  padding: 32px;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.governance-principles h3 {
  color: #333;
  margin-bottom: 24px;
  font-size: 1.3rem;
}

.principle-item {
  display: flex;
  gap: 16px;
  margin-bottom: 20px;
  align-items: flex-start;
}

.principle-icon {
  font-size: 1.5rem;
  flex-shrink: 0;
}

.principle-content h4 {
  font-size: 1.1rem;
  font-weight: 600;
  margin-bottom: 4px;
  color: #333;
}

.principle-content p {
  color: #666;
  line-height: 1.6;
  font-size: 0.9rem;
}

/* 财务信息样式 */
.finance-content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 40px;
}

.finance-overview, .funding-sources {
  background: white;
  padding: 32px;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.finance-overview h3, .funding-sources h3 {
  color: #333;
  margin-bottom: 24px;
  font-size: 1.3rem;
}

.finance-stats {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.finance-stat {
  text-align: center;
  padding: 20px;
  background: #f8f9fa;
  border-radius: 12px;
}

.stat-value {
  font-size: 2rem;
  font-weight: 700;
  color: #7c3aed;
  margin-bottom: 8px;
}

.stat-label {
  color: #666;
  font-size: 0.9rem;
}

.funding-chart {
  space-y: 16px;
}

.funding-item {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
}

.funding-bar {
  height: 24px;
  border-radius: 12px;
  margin-right: 16px;
  min-width: 60px;
}

.funding-label {
  color: #333;
  font-size: 0.9rem;
  font-weight: 500;
}

/* 影响力报告样式 */
.impact-content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 40px;
}

.impact-summary, .sdg-progress {
  background: white;
  padding: 32px;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.impact-summary h3, .sdg-progress h3 {
  color: #333;
  margin-bottom: 24px;
  font-size: 1.3rem;
}

.impact-stats {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.impact-stat {
  text-align: center;
  padding: 20px;
  background: linear-gradient(135deg, #7c3aed, #5b21b6);
  color: white;
  border-radius: 12px;
}

.impact-number {
  font-size: 2rem;
  font-weight: 700;
  margin-bottom: 8px;
}

.impact-desc {
  font-size: 0.9rem;
  opacity: 0.9;
}

.sdg-bar-item {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
  gap: 12px;
}

.sdg-label {
  flex: 0 0 180px;
  font-size: 0.8rem;
  color: #333;
}

.progress-bar {
  flex: 1;
  height: 20px;
  background: #e5e7eb;
  border-radius: 10px;
  overflow: hidden;
}

.progress-fill {
  height: 100%;
  background: linear-gradient(135deg, #7c3aed, #5b21b6);
  border-radius: 10px;
  transition: width 0.3s ease;
}

.progress-value {
  flex: 0 0 40px;
  text-align: right;
  font-size: 0.8rem;
  color: #7c3aed;
  font-weight: 600;
}

/* 联系方式样式 */
.contact-content {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 40px;
}

.contact-info {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 24px;
}

.contact-item {
  background: white;
  padding: 32px;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.contact-item h3 {
  color: #333;
  margin-bottom: 16px;
  font-size: 1.2rem;
}

.contact-item p {
  color: #666;
  line-height: 1.6;
}

.social-links {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  margin-top: 12px;
}

.social-link {
  padding: 8px 16px;
  background: #7c3aed;
  color: white;
  text-decoration: none;
  border-radius: 20px;
  font-size: 0.9rem;
  transition: transform 0.2s;
}

.social-link:hover {
  transform: translateY(-2px);
  background: #5b21b6;
}

.quick-contact {
  background: white;
  padding: 32px;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.quick-contact h3 {
  color: #333;
  margin-bottom: 24px;
  font-size: 1.3rem;
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
  
  .timeline-year {
    position: absolute;
    left: 0;
    top: 0;
  }
  
  .timeline-content {
    margin: 0;
    margin-top: 80px;
  }
  
  .values-grid {
    grid-template-columns: 1fr;
  }
  
  .governance-content,
  .finance-content,
  .impact-content,
  .contact-content {
    grid-template-columns: 1fr;
  }
  
  .contact-info {
    grid-template-columns: 1fr;
  }
  
  .finance-stats,
  .impact-stats {
    grid-template-columns: 1fr;
  }
  
  .sdg-label {
    flex: 0 0 120px;
    font-size: 0.7rem;
  }
}
</style> 