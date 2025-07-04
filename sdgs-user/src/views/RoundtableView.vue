<template>
  <div class="roundtable">
    <!-- 主要内容 -->
    <div class="roundtable-content">
      <div class="container">
        <!-- 会议详细介绍（如图2） -->
        <section class="forum-intro-section">
          <h2 class="forum-intro-title">“世博全球青年共创科学与艺术可持续未来”</h2>
          <div class="forum-intro-content">
            <p class="intro-paragraph">世界博览会（世博会）是由国家政府主办、多国和国际组织参与的全球最高级别展览平台，旨在通过展示各国的科技创新、文化成就和经济成果，促进全球合作与交流。其核心理念为“理解、沟通、欢聚、合作”，致力于增进各国人民之间的相互理解，推动全球可持续发展。</p>
            <p class="intro-paragraph">世博会不仅是人类文明成就的展示舞台，更是探讨全球性挑战、寻求解决方案的重要平台，每一届都围绕特定主题展开，聚焦城市化、可持续发展、科技创新等重大问题。被誉为“经济、科技与文化的奥林匹克”，是人类文明成果的集中展示平台。</p>
            <p class="intro-paragraph">▪ 品牌&青年领袖板块：将展示创新与可持续实践，以及青年领袖板块汇聚全球影响力人物；<br>
▪ 未来教育板块：探讨未来人才培养，促进未来和可持续发展的专业与学术成果；<br>
▪ 艺术疗愈板块：将传统非物质文化遗产与现代科技相结合，通过创新的艺术表现形式，探索艺术在心理健康和情感疗愈中的独特作用；<br>
▪ 女性板块：将聚焦女性在可持续发展中的关键角色，促进性别平等，赋能更多女性在多领域发挥领导力与影响力，成为变革推动者。<br>
▪ ESG板块：聚焦环境、社会和治理三大核心领域共同探索如何通过绿色技术、社会责任和透明治理，构建更具韧性和可持续的未来生态。</p>
          </div>
        </section>

        <!-- 会议列表 -->
        <section class="forum-list-section">
          <div class="forum-list">
            <div class="forum-item" v-for="item in pagedForumList" :key="item.id">
              <div class="forum-img">
                <img :src="item.imageUrl || item.image_url" :alt="item.title" />
              </div>
              <div class="forum-info">
                <div class="forum-title">{{ item.title }}</div>
                <div class="forum-desc">{{ item.description }}</div>
                <div class="forum-tags">
                  <span v-for="(t, idx) in item.tag.split(',')" :key="t">
                    #{{ t.trim() }}<span v-if="idx !== item.tag.split(',').length - 1">&nbsp;&nbsp;&nbsp;</span>
                  </span>
                </div>
                <el-button class="forum-more-btn" type="primary" @click="viewMore(item)">更多&nbsp; &gt;</el-button>
              </div>
            </div>
          </div>
          <div class="forum-pagination">
            <div class="custom-pagination">
              <span class="pagination-info">第{{ currentPage }}页，共{{ totalPages }}页</span>
              <button class="pager-btn" :disabled="currentPage === 1" @click="changePage(currentPage - 1)">&lt;</button>
              <template v-for="page in pageList" :key="page">
                <button
                  v-if="page !== '...'"
                  class="pager-btn"
                  :class="{ active: currentPage === page }"
                  @click="changePage(page)"
                >{{ page }}</button>
                <span v-else class="pager-btn ellipsis">...</span>
              </template>
              <button class="pager-btn" :disabled="currentPage === totalPages" @click="changePage(currentPage + 1)">&gt;</button>
            </div>
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

const forumList = ref([])
const currentPage = ref(1)
const pageSize = 4
const maxPagerCount = 6
const totalPages = computed(() => Math.ceil(forumList.value.length / pageSize))

const pagedForumList = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return forumList.value.slice(start, start + pageSize)
})

const pageList = computed(() => {
  const list = []
  if (totalPages.value <= 5) {
    for (let i = 1; i <= totalPages.value; i++) list.push(i)
    return list
  }
  if (currentPage.value <= 3) {
    list.push(1, 2, 3, 4, '...', totalPages.value)
    return list
  }
  if (currentPage.value >= totalPages.value - 2) {
    list.push(1, '...', totalPages.value - 3, totalPages.value - 2, totalPages.value - 1, totalPages.value)
    return list
  }
  list.push(1, '...', currentPage.value - 1, currentPage.value, currentPage.value + 1, '...', totalPages.value)
  return list
})

function changePage(page) {
  if (typeof page === 'number' && page >= 1 && page <= totalPages.value) {
    currentPage.value = page
  }
}

const fetchForums = async () => {
  try {
    const res = await request.get('/forums')
    forumList.value = res || []
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

// 页面加载时获取数据
onMounted(() => {
  fetchForums()
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
  align-items: center;
  gap: 32px;
}
.forum-item {
  background: #eaf6fb;
  color: #0166A5FF;
  display: flex;
  border-radius: 16px;
  box-shadow: 0 -2px 1px rgba(255, 255, 255, 0), 0 8px 14px rgba(0,0,0,0.18);
  overflow: hidden;
  margin-bottom: 32px;
  min-height: 250px;
  width: 1200px;
  max-width: 100%;
}
.forum-img {
  width: 220px;
  min-height: 250px;
  height: auto;
  display: flex;
  align-items: stretch;
  justify-content: center;
  background: #f7f7f7;
}
.forum-img img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}
.forum-info {
  position: relative;
  padding: 1px 24px 80px 32px;
  flex: 1;
  max-width: 900px;
  display: flex;
  flex-direction: column;
    justify-content: center;
  }
.forum-tags {
  font-weight: bold;
  margin-top: 2em;
}
.forum-tag {
  display: inline-block;
  background: #e0f2fe;
  color: #1765d6;
  border-radius: 12px;
  padding: 2px 12px;
  font-size: 0.95rem;
  margin-right: 8px;
}
.forum-title,
.forum-desc,
.forum-tags,
.forum-info {
  color: #0166A5FF;
  font-weight: bold;
  font-size: 0.95rem;
}
.forum-title {
  font-size: 1.2rem;
  font-weight: bold;
  margin-bottom: 10px;
  line-height: 1;
}
.forum-desc {
  font-size: 1rem;
  margin-bottom: 18px;
  line-height: 1;
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
.forum-more-btn {
  min-width: unset;
  width: auto;
  padding: 0 18px;
  height: 32px;
  font-size: 16px;
  position: absolute;
  right: 32px;
  bottom: 24px;
  border-radius: 12px;
  box-shadow: none;
  background: #43A0E2FF;
  color: #fff;
  display: flex;
  align-items: center;
    justify-content: center;
  }
.intro-paragraph {
  margin-bottom: 1.5em;
  line-height: 1.9;
  font-size: 0.95rem;
}
.forum-intro-title {
  color: #0166A5FF !important;
}
.forum-intro-section,
.forum-intro-section .forum-intro-content,
.forum-intro-section .intro-paragraph {
  color: #000 !important;
}
.custom-pagination {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  margin-top: 24px;
  font-size: 1.1rem;
  color: #7a97b6;
}
.pagination-info {
  margin-right: 16px;
}
.pager-btn {
  background: #f5f7fa;
  border: none;
  border-radius: 6px;
  padding: 4px 14px;
  font-size: 1.1rem;
  color: #0166A5FF;
  cursor: pointer;
  transition: background 0.2s;
}
.pager-btn.active {
  background: #51aaff;
  color: #fff;
}
.pager-btn:disabled {
  color: #b0b0b0;
  cursor: not-allowed;
}
.pager-btn.ellipsis {
  background: none;
  color: #999;
  cursor: default;
}
</style> 