<template>
  <div class="roundtable">
    <!-- 页面头部 -->
    <div class="page-header">
      <div class="container">
        <h1>圆桌会议</h1>
        <p>SDGs 专家学者深度讨论与交流平台</p>
      </div>
    </div>

    <!-- 主要内容 -->
    <div class="roundtable-content">
      <div class="container">
        <!-- 会议概述 -->
        <section class="meeting-overview">
          <h2>关于圆桌会议</h2>
          <p class="overview-text">
            圆桌会议是我们为促进可持续发展目标讨论而设立的专业交流平台。在这里，来自不同领域的专家学者、
            政策制定者、企业领导者和社会活动家汇聚一堂，分享见解、交流经验、探讨解决方案。
          </p>
        </section>

        <!-- 即将举行的会议 -->
        <section class="upcoming-meetings">
          <h2>即将举行的会议</h2>
          <div class="meetings-grid">
            <div
              v-for="meeting in upcomingMeetings"
              :key="meeting.id"
              class="meeting-card"
            >
              <div class="meeting-date">
                <div class="date-day">{{ meeting.day }}</div>
                <div class="date-month">{{ meeting.month }}</div>
              </div>
              <div class="meeting-info">
                <h3>{{ meeting.title }}</h3>
                <p class="meeting-description">{{ meeting.description }}</p>
                <div class="meeting-details">
                  <span class="meeting-time">
                    <el-icon><Clock /></el-icon>
                    {{ meeting.time }}
                  </span>
                  <span class="meeting-location">
                    <el-icon><Location /></el-icon>
                    {{ meeting.location }}
                  </span>
                </div>
                <div class="meeting-speakers">
                  <h4>主要嘉宾：</h4>
                  <div class="speakers-list">
                    <span
                      v-for="speaker in meeting.speakers"
                      :key="speaker"
                      class="speaker-tag"
                    >
                      {{ speaker }}
                    </span>
                  </div>
                </div>
                <div class="meeting-actions">
                  <el-button type="primary" @click="registerMeeting(meeting)">
                    立即报名
                  </el-button>
                  <el-button @click="viewDetails(meeting)">
                    查看详情
                  </el-button>
                </div>
              </div>
            </div>
          </div>
        </section>

        <!-- 历史会议 -->
        <section class="past-meetings">
          <h2>往期精彩回顾</h2>
          <div class="past-meetings-grid">
            <div
              v-for="meeting in pastMeetings"
              :key="meeting.id"
              class="past-meeting-card"
            >
              <div class="past-meeting-image">
                <img :src="meeting.image" :alt="meeting.title" />
              </div>
              <div class="past-meeting-content">
                <h3>{{ meeting.title }}</h3>
                <p class="past-meeting-date">{{ meeting.date }}</p>
                <p class="past-meeting-summary">{{ meeting.summary }}</p>
                <el-button text type="primary" @click="watchRecording(meeting)">
                  观看回放 →
                </el-button>
              </div>
            </div>
          </div>
        </section>

        <!-- 讨论主题 -->
        <section class="discussion-topics">
          <h2>热门讨论主题</h2>
          <div class="topics-grid">
            <div
              v-for="topic in discussionTopics"
              :key="topic.id"
              class="topic-card"
            >
              <div class="topic-icon">{{ topic.icon }}</div>
              <h3>{{ topic.title }}</h3>
              <p>{{ topic.description }}</p>
              <div class="topic-stats">
                <span>{{ topic.participants }} 人参与</span>
                <span>{{ topic.discussions }} 条讨论</span>
              </div>
            </div>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { Clock, Location } from '@element-plus/icons-vue'

// 响应式数据
const upcomingMeetings = ref([
  {
    id: 1,
    title: '气候变化与可持续发展',
    description: '探讨气候变化对各个SDG目标的影响，以及如何通过协同行动实现碳中和目标。',
    day: '15',
    month: 'MAR',
    time: '14:00 - 17:00',
    location: '在线会议',
    speakers: ['张教授', '李博士', 'Smith教授'],
  },
  {
    id: 2,
    title: '数字化转型与包容性发展',
    description: '讨论数字技术如何促进教育公平、减少不平等，以及数字鸿沟的挑战。',
    day: '22',
    month: 'MAR',
    time: '09:00 - 12:00',
    location: '北京国际会议中心',
    speakers: ['王院士', 'Johnson博士', '陈教授'],
  },
])

const pastMeetings = ref([
  {
    id: 1,
    title: '可持续城市与社区建设',
    date: '2024年2月20日',
    summary: '本次会议深入探讨了智慧城市建设、绿色建筑、公共交通等议题，来自全球30多位专家分享了最佳实践案例。',
    image: '/images/meeting1.jpg',
  },
  {
    id: 2,
    title: '清洁能源与绿色经济',
    date: '2024年1月18日',
    summary: '聚焦可再生能源发展、绿色金融、碳市场机制等话题，为实现碳达峰碳中和目标提供了重要思路。',
    image: '/images/meeting2.jpg',
  },
])

const discussionTopics = ref([
  {
    id: 1,
    icon: '🌍',
    title: '全球合作与伙伴关系',
    description: '如何加强国际合作，建立有效的全球伙伴关系',
    participants: 156,
    discussions: 89,
  },
  {
    id: 2,
    icon: '💡',
    title: '创新技术与可持续发展',
    description: '探讨人工智能、区块链等新技术在SDG中的应用',
    participants: 203,
    discussions: 127,
  },
  {
    id: 3,
    icon: '🎓',
    title: '教育与能力建设',
    description: '提升全民可持续发展意识和能力的策略',
    participants: 178,
    discussions: 95,
  },
  {
    id: 4,
    icon: '🏢',
    title: '企业社会责任',
    description: '企业在实现SDG中的作用和责任',
    participants: 145,
    discussions: 73,
  },
])

// 方法
const registerMeeting = (meeting) => {
  ElMessage.success(`已成功报名：${meeting.title}`)
}

const viewDetails = (meeting) => {
  ElMessage.info(`查看会议详情：${meeting.title}`)
}

const watchRecording = (meeting) => {
  ElMessage.info(`观看会议回放：${meeting.title}`)
}
</script>

<style scoped>
.roundtable {
  min-height: 100vh;
  background-color: #f8f9fa;
}

.page-header {
  background: linear-gradient(135deg, #1e40af 0%, #1e3a8a 100%);
  color: white;
  padding: 60px 0;
  text-align: center;
}

.container {
  max-width: 1200px;
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

.roundtable-content {
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

.overview-text {
  font-size: 1.1rem;
  line-height: 1.8;
  color: #666;
  text-align: center;
  max-width: 800px;
  margin: 0 auto;
}

.meetings-grid {
  display: grid;
  gap: 30px;
}

.meeting-card {
  background: white;
  border-radius: 16px;
  padding: 30px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  display: flex;
  gap: 30px;
  transition: transform 0.3s ease;
}

.meeting-card:hover {
  transform: translateY(-4px);
}

.meeting-date {
  text-align: center;
  flex-shrink: 0;
}

.date-day {
  font-size: 2.5rem;
  font-weight: 700;
  color: #1e40af;
}

.date-month {
  font-size: 1rem;
  color: #666;
  font-weight: 500;
}

.meeting-info {
  flex: 1;
}

.meeting-info h3 {
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 1rem;
  color: #333;
}

.meeting-description {
  color: #666;
  line-height: 1.6;
  margin-bottom: 1rem;
}

.meeting-details {
  display: flex;
  gap: 20px;
  margin-bottom: 1rem;
  flex-wrap: wrap;
}

.meeting-time,
.meeting-location {
  display: flex;
  align-items: center;
  gap: 5px;
  color: #666;
  font-size: 0.9rem;
}

.meeting-speakers h4 {
  font-size: 1rem;
  margin-bottom: 0.5rem;
  color: #333;
}

.speakers-list {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
  margin-bottom: 1.5rem;
}

.speaker-tag {
  background: #e0f2fe;
  color: #01579b;
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 0.85rem;
}

.meeting-actions {
  display: flex;
  gap: 12px;
}

.past-meetings-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));
  gap: 20px;
}

.past-meeting-card {
  background: white;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
}

.past-meeting-card:hover {
  transform: translateY(-2px);
}

.past-meeting-image {
  height: 200px;
  background: #ddd;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
}

.past-meeting-content {
  padding: 20px;
}

.past-meeting-content h3 {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 0.5rem;
}

.past-meeting-date {
  color: #999;
  font-size: 0.9rem;
  margin-bottom: 1rem;
}

.past-meeting-summary {
  color: #666;
  line-height: 1.6;
  margin-bottom: 1rem;
}

.topics-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 20px;
}

.topic-card {
  background: white;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: all 0.3s ease;
}

.topic-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
}

.topic-icon {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.topic-card h3 {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 1rem;
  color: #333;
}

.topic-card p {
  color: #666;
  line-height: 1.5;
  margin-bottom: 1rem;
}

.topic-stats {
  display: flex;
  justify-content: space-between;
  font-size: 0.9rem;
  color: #999;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .page-header h1 {
    font-size: 2rem;
  }
  
  .meeting-card {
    flex-direction: column;
    text-align: center;
  }
  
  .meeting-details {
    justify-content: center;
  }
  
  .meeting-actions {
    justify-content: center;
  }
}
</style> 