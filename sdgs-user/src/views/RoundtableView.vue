<template>
  <div class="roundtable">
    <!-- 主要内容 -->
    <div class="roundtable-content">
      <div class="container">
        <!-- 会议详细介绍（如图2） -->
        <section class="forum-intro-section">
          <h2 class="forum-intro-title">“世博全球青年共创科学与艺术可持续未来”</h2>
          <div class="forum-intro-content">
            <p>世界博览会（世博会）是由国家政府主办、多国和国际组织参与的全球最高级别展览平台，旨在通过展示各国的科技创新、文化成就和经济成果，促进全球合作与交流。其核心理念为“理解、沟通、欢聚、合作”，致力于增进各国人民之间的相互理解，推动全球可持续发展。</p>
            <p>世博会不仅是人类文明成就的展示舞台，更是探讨全球性挑战、寻求解决方案的重要平台，每一届都围绕特定主题展开，聚焦城市化、可持续发展、科技创新等重大问题。被誉为“经济、科技与文化的奥林匹克”，是人类文明成果的集中展示平台。</p>
            <ul class="forum-intro-list">
              <li>品牌&青年领袖板块：将展示创新与可持续实践，以及青年领袖板块汇聚全球影响力人物；</li>
              <li>未来教育板块：探讨未来人才培养，促进未来和可持续发展的专业与学术成果；</li>
              <li>艺术疗愈板块：将传统非物质文化遗产与现代科技相结合，通过创新的艺术表现形式，探索艺术在心理健康和情感疗愈中的独特作用；</li>
              <li>女性板块：将聚焦女性在可持续发展中的关键角色，促进性别平等，赋能更多女性在多领域发挥领导力与影响力，成为变革推动者。</li>
              <li>ESG板块：聚焦环境、社会和治理三大核心领域共同探索如何通过绿色技术、社会责任和透明治理，构建更具韧性和可持续的未来生态。</li>
            </ul>
          </div>
        </section>

        <!-- 会议列表 -->
        <section class="forum-list-section">
          <div class="forum-list">
            <div class="forum-item" v-for="item in pagedForumList" :key="item.id">
              <div class="forum-img">
                <img :src="item.imageUrl" :alt="item.title" />
              </div>
              <div class="forum-info">
                <div class="forum-tag">{{ item.tag }}</div>
                <div class="forum-title">{{ item.title }}</div>
                <div class="forum-desc">{{ item.description }}</div>
                <el-button type="primary" @click="viewMore(item)">更多</el-button>
              </div>
            </div>
          </div>
          <div class="forum-pagination">
            <el-pagination
              background
              layout="prev, pager, next"
              :total="forumList.length"
              :page-size="4"
              :current-page.sync="currentPage"
              :pager-count="7"
              hide-on-single-page
              @current-change="handlePageChange"
            />
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { ElMessage } from 'element-plus'
import { Clock, Location } from '@element-plus/icons-vue'
import request from '@/utils/request'

// 响应式数据
const upcomingMeetings = ref([])
const pastMeetings = ref([])
const forumList = ref([
  {
    id: 1,
    imageUrl: 'https://via.placeholder.com/120x120?text=Poster1',
    title: '品牌&青年领袖板块会议',
    description: '聚焦青年创新与可持续实践，汇聚全球影响力人物。',
    tag: '品牌&青年领袖板块',
  },
  {
    id: 2,
    imageUrl: 'https://via.placeholder.com/120x120?text=Poster2',
    title: '未来教育板块会议',
    description: '探讨未来人才培养，促进可持续发展的专业与学术成果。',
    tag: '未来教育板块',
  },
  {
    id: 3,
    imageUrl: 'https://via.placeholder.com/120x120?text=Poster3',
    title: '艺术疗愈板块会议',
    description: '创新艺术与科技结合，探索艺术在心理健康中的独特作用。',
    tag: '艺术疗愈板块',
  },
  {
    id: 4,
    imageUrl: 'https://via.placeholder.com/120x120?text=Poster4',
    title: '女性板块会议',
    description: '聚焦女性在可持续发展中的关键角色，赋能女性领导力。',
    tag: '女性板块',
  },
  {
    id: 5,
    imageUrl: 'https://via.placeholder.com/120x120?text=Poster5',
    title: 'ESG板块会议',
    description: '聚焦环境、社会和治理三大领域的绿色技术与责任。',
    tag: 'ESG板块',
  },
])

const currentPage = ref(1)
const pageSize = 4
const pagedForumList = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return forumList.value.slice(start, start + pageSize)
})

// 获取会议数据
const fetchMeetings = async () => {
  try {
    // 获取即将举行的会议
    const upcomingRes = await request.get('/forums', {
      params: { status: 'UPCOMING' }
    })
    upcomingMeetings.value = (upcomingRes || []).map(meeting => ({
      ...meeting,
      day: new Date(meeting.startTime).getDate(),
      month: new Date(meeting.startTime).toLocaleString('en-US', { month: 'short' }).toUpperCase(),
      time: `${new Date(meeting.startTime).toLocaleTimeString()} - ${new Date(meeting.endTime).toLocaleTimeString()}`,
      speakers: meeting.speakers ? meeting.speakers.split(',') : []
    }))

    // 获取历史会议
    const pastRes = await request.get('/forums', {
      params: { status: 'PAST' }
    })
    pastMeetings.value = (pastRes || []).map(meeting => ({
      ...meeting,
      date: new Date(meeting.startTime).toLocaleDateString('zh-CN')
    }))
  } catch (error) {
    ElMessage.error('获取会议数据失败')
    console.error(error)
  }
}

// 方法
const registerMeeting = async (meeting) => {
  try {
    await request.post(`/forums/${meeting.id}/register`)
    ElMessage.success(`已成功报名：${meeting.title}`)
  } catch (error) {
    ElMessage.error('报名失败，请稍后重试')
  }
}

const viewDetails = (meeting) => {
  ElMessage.info(`查看会议详情：${meeting.title}`)
}

const watchRecording = (meeting) => {
  ElMessage.info(`观看会议回放：${meeting.title}`)
}

function viewMore(item) {
  // 这里可以跳转详情页或弹窗
  alert('更多: ' + item.title)
}

function handlePageChange(page) {
  currentPage.value = page
}

// 页面加载时获取数据
onMounted(() => {
  fetchMeetings()
})
</script>

<style scoped>
.roundtable {
  min-height: 100vh;
  background-color: #f8f9fa;
}

.page-header {
  background: linear-gradient(135deg, #1e40af 0%, #1e3a8a 100%);
  color: white;
  padding: 60px 0 40px 0;
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
  padding: 40px 0 0 0;
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

.forum-card {
  display: flex;
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.07);
  overflow: hidden;
  margin-bottom: 32px;
  min-height: 160px;
}

.forum-card-left {
  background: linear-gradient(135deg, #b2d8f7 0%, #e0c3fc 100%);
  padding: 24px 32px;
  min-width: 320px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.forum-card-title {
  font-size: 1.2rem;
  font-weight: bold;
  color: #1e3a8a;
  margin-bottom: 8px;
}

.forum-card-en-title {
  font-size: 0.95rem;
  color: #333;
  margin-bottom: 16px;
}

.forum-card-info {
  font-size: 0.95rem;
  color: #444;
  line-height: 1.7;
}

.forum-card-right {
  flex: 1;
  padding: 24px 32px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
}

.forum-card-topic {
  font-size: 1.1rem;
  font-weight: 600;
  color: #1e3a8a;
  margin-bottom: 12px;
}

.forum-card-tags {
  margin-bottom: 18px;
}

.forum-card-tag {
  display: inline-block;
  background: #e0f2fe;
  color: #0284c7;
  border-radius: 12px;
  padding: 2px 12px;
  font-size: 0.9rem;
  margin-right: 8px;
}

.forum-card-btn {
  align-self: flex-end;
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
  grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
  gap: 32px;
  justify-items: center;
}

.topic-card {
  background: white;
  padding: 32px 20px 24px 20px;
  border-radius: 16px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.07);
  text-align: center;
  transition: all 0.3s ease;
  min-width: 240px;
  min-height: 180px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.topic-card-large-icon .topic-icon-large {
  font-size: 2.8rem;
  margin-bottom: 18px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.topic-card h3 {
  font-size: 1.1rem;
  font-weight: 600;
  margin-bottom: 10px;
  color: #222;
}

.topic-card p {
  color: #666;
  line-height: 1.5;
  margin-bottom: 16px;
  font-size: 0.98rem;
}

.topic-stats {
  display: flex;
  justify-content: center;
  gap: 18px;
  font-size: 0.95rem;
  color: #999;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .page-header h1 {
    font-size: 2rem;
  }
  .forum-intro-section {
    padding: 0 8px;
  }
  .discussion-topics {
    padding: 0 8px;
  }
  .topics-grid {
    gap: 16px;
  }
}

.topics-vertical {
  display: flex;
  flex-direction: column;
  gap: 32px;
  align-items: center;
}

.forum-list-section {
  max-width: 900px;
  margin: 0 auto;
  padding: 40px 0 60px 0;
}
.forum-list {
  display: flex;
  flex-direction: column;
  gap: 32px;
}
.forum-item {
  display: flex;
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.07);
  overflow: hidden;
  min-height: 180px;
}
.forum-img {
  width: 180px;
  height: 180px;
  background: #f3f3f3;
  display: flex;
  align-items: center;
  justify-content: center;
}
.forum-img img {
  max-width: 100%;
  max-height: 100%;
  object-fit: cover;
}
.forum-info {
  flex: 1;
  padding: 24px 32px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.forum-tag {
  font-size: 0.95rem;
  color: #1765d6;
  margin-bottom: 8px;
}
.forum-title {
  font-size: 1.2rem;
  font-weight: bold;
  margin-bottom: 10px;
}
.forum-desc {
  font-size: 1rem;
  color: #444;
  margin-bottom: 18px;
}
.forum-pagination {
  display: flex;
  justify-content: center;
  margin-top: 32px;
}
/deep/ .el-pagination {
  font-size: 1.1rem;
}
/deep/ .el-pagination.is-background .el-pager li {
  border-radius: 4px;
  min-width: 32px;
  height: 32px;
  line-height: 32px;
}
/deep/ .el-pagination.is-background .el-pager li.active {
  background: #42a5f5;
  color: #fff;
}
</style> 