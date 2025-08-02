<template>
  <div class="news">
    <!-- 主要内容 -->
    <div class="news-content">
      <div class="container">
        <!-- 筛选和搜索 - 居中框框样式 -->
        <div class="filter-section">
          <div class="filter-container">
            <div class="search-box">
              <el-input
                v-model="searchQuery"
                placeholder="搜索新闻..."
                prefix-icon="Search"
                size="large"
              />
            </div>

            <div class="search-button-container">
              <el-button type="primary" size="large" @click="fetchNewsData">
                搜索
              </el-button>
            </div>
          </div>
        </div>

        <!-- 新闻列表 -->
        <div class="news-list">
          <!-- 加载状态 -->
          <div v-if="loading" class="loading-state">
            <p>正在加载新闻...</p>
          </div>
          
          <!-- 空状态 -->
          <div v-else-if="filteredNews.length === 0" class="empty-state">
            <p>暂无新闻数据</p>
          </div>
          
          <!-- 新闻卡片 -->
          <div
            v-else
            v-for="article in filteredNews"
            :key="article.id"
            class="news-card"
            @click="openNews(article)"
          >
            <div class="news-image">
              <img :src="article.image" :alt="article.title" />
            </div>
            <div class="news-content-area">
              <h3>{{ article.title }}</h3>
              <p class="news-summary">{{ article.summary }}</p>
              <div class="news-meta">
                <span class="news-date">{{ article.date }}</span>
                <span class="news-read-time">{{ article.readTime }} 分钟阅读</span>
              </div>

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import request from '@/utils/request'

const $router = useRouter()

// 响应式数据
const searchQuery = ref('')
const loading = ref(false)
const newsData = ref([])

// 获取新闻数据
const fetchNewsData = async () => {
  loading.value = true
  try {
    const response = await request.get('/news/published')
    newsData.value = response.map(item => ({
      ...item,
      date: item.publishTime ? new Date(item.publishTime).toLocaleDateString('zh-CN') : '',
      image: item.coverImageUrl || '/placeholder-news.jpg',
      readTime: Math.ceil((item.content || '').length / 200) // 估算阅读时间
    }))
  } catch (error) {
    ElMessage.error('加载新闻数据失败')
    console.error('Error fetching news:', error)
  } finally {
    loading.value = false
  }
}

// 组件挂载时获取数据
onMounted(() => {
  fetchNewsData()
})

// 计算属性：过滤后的新闻
const filteredNews = computed(() => {
  let filtered = newsData.value

  // 按搜索关键词筛选
  if (searchQuery.value) {
    const query = searchQuery.value.toLowerCase()
    filtered = filtered.filter(news =>
      news.title.toLowerCase().includes(query) ||
      news.summary.toLowerCase().includes(query)
    )
  }

  return filtered
})

// 方法
const openNews = (article) => {
  // 跳转到新闻详情页
  $router.push(`/news/${article.id}`)
}
</script>

<style scoped>
.news {
  min-height: 100vh;
  background: white;
  position: relative;
}

.news::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 18%;
  background-image: url('https://image.kkday.com/image/get/s1.kkday.com/product_261745/20241108085501_4472o/jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  z-index: 1;
}

.news::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 18%; 
  background: rgba(0, 0, 0, 0.3);
  z-index: 2;
}

.news-content {
  padding: 300px 0 60px 0;
  position: relative;
  z-index: 3;
}

.container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 20px;
}

.filter-section {
  margin-bottom: 40px;
  display: flex;
  justify-content: center;
}

.filter-container {
  background: white;
  border-radius: 16px;
  padding: 30px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 1200px;
  max-width: 100%;
  min-height: 250px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.search-box {
  margin-bottom: 20px;
}

.search-box .el-input {
  max-width: 400px;
}



.search-button-container {
  text-align: center;
}

.news-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 32px;
}

.news-card {
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
  cursor: pointer;
  transition: all 0.3s ease;
}

.news-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 -2px 1px rgba(255, 255, 255, 0), 0 12px 20px rgba(0,0,0,0.25);
}

.news-image {
  width: 220px;
  min-height: 250px;
  height: auto;
  display: flex;
  align-items: stretch;
  justify-content: center;
  background: #f7f7f7;
  position: relative;
}

.news-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}



.news-content-area {
  position: relative;
  padding: 1px 24px 80px 32px;
  flex: 1;
  max-width: 900px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.news-content-area h3 {
  font-size: 1.2rem;
  font-weight: bold;
  margin-bottom: 10px;
  color: #0166A5FF;
  line-height: 1;
}

.news-summary {
  font-size: 1rem;
  color: #0166A5FF;
  font-weight: bold;
  line-height: 1;
  margin-bottom: 18px;
  flex: 1;
}

.news-meta {
  display: flex;
  gap: 16px;
  margin-bottom: 12px;
  font-size: 0.95rem;
  color: #0166A5FF;
  font-weight: bold;
  flex-wrap: wrap;
}



.loading-state,
.empty-state {
  text-align: center;
  padding: 60px 20px;
  color: #666;
}

.loading-state p,
.empty-state p {
  font-size: 1.1rem;
  margin: 0;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .news::before {
    height: 200px;  /* 移动设备上的固定高度 */
  }
  
  .news::after {
    height: 200px;  /* 移动设备上的固定高度 */
  }
  
  .news-content {
    padding: 200px 0 60px 0;
  }
  
  .news-card {
    flex-direction: column;
    width: 100%;
  }
  
  .news-image {
    width: 100%;
    height: 200px;
  }
  

  
  .filter-container {
    max-width: 95%;
    padding: 20px;
  }
  
  .news-list {
    padding: 20px;
  }
  
  .news-content-area {
    padding: 20px;
  }
}
</style> 