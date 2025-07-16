<template>
  <div class="news">
    <!-- 页面头部 -->
    <div class="page-header">
      <div class="container">
        <h1>新闻资讯</h1>
        <p>关注可持续发展的最新动态和进展</p>
      </div>
    </div>

    <!-- 主要内容 -->
    <div class="news-content">
      <div class="container">
        <!-- 筛选和搜索 -->
        <div class="filter-section">
          <div class="search-box">
            <el-input
              v-model="searchQuery"
              placeholder="搜索新闻..."
              prefix-icon="Search"
              size="large"
            />
          </div>
          <div class="category-tabs">
            <el-button
              v-for="category in categories"
              :key="category.id"
              :type="selectedCategory === category.id ? 'primary' : ''"
              @click="selectedCategory = category.id"
            >
              {{ category.name }}
            </el-button>
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
              <div class="news-category">{{ article.category }}</div>
            </div>
            <div class="news-content-area">
              <h3>{{ article.title }}</h3>
              <p class="news-summary">{{ article.summary }}</p>
              <div class="news-meta">
                <span class="news-date">{{ article.date }}</span>
                <span class="news-source">{{ article.source }}</span>
                <span class="news-read-time">{{ article.readTime }} 分钟阅读</span>
              </div>
              <div class="news-tags">
                <span
                  v-for="tag in article.tags"
                  :key="tag"
                  class="news-tag"
                >
                  #{{ tag }}
                </span>
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
const selectedCategory = ref('all')
const loading = ref(false)
const newsData = ref([])

// 分类数据
const categories = ref([
  { id: 'all', name: '全部' },
  { id: '政策发布', name: '政策发布' },
  { id: '气候行动', name: '气候行动' },
  { id: '教育发展', name: '教育发展' },
  { id: '健康福祉', name: '健康福祉' },
  { id: '经济发展', name: '经济发展' },
  { id: '环境保护', name: '环境保护' }
])

// 获取新闻数据
const fetchNewsData = async () => {
  loading.value = true
  try {
    const response = await request.get('/news/published')
    newsData.value = response.map(item => ({
      ...item,
      date: item.publishTime ? new Date(item.publishTime).toLocaleDateString('zh-CN') : '',
      image: item.coverImageUrl || 'https://images.unsplash.com/photo-1573164713714-d95e436ab8d6?w=800&h=400&fit=crop',
      readTime: Math.ceil((item.content || '').length / 200), // 估算阅读时间
      tags: item.tags ? item.tags.split(',').map(tag => tag.trim()) : []
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

  // 按分类筛选
  if (selectedCategory.value !== 'all') {
    filtered = filtered.filter(news => news.category === selectedCategory.value)
  }

  // 按搜索关键词筛选
  if (searchQuery.value) {
    const query = searchQuery.value.toLowerCase()
    filtered = filtered.filter(news =>
      news.title.toLowerCase().includes(query) ||
      news.summary.toLowerCase().includes(query) ||
      (news.tags && news.tags.some(tag => tag.toLowerCase().includes(query)))
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
  background-color: #f8f9fa;
}

.page-header {
  background: linear-gradient(135deg, #059669 0%, #047857 100%);
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

.news-content {
  padding: 60px 0;
}

.filter-section {
  margin-bottom: 40px;
}

.search-box {
  text-align: center;
  margin-bottom: 20px;
}

.search-box .el-input {
  max-width: 400px;
}

.category-tabs {
  display: flex;
  justify-content: center;
  gap: 10px;
  flex-wrap: wrap;
}

.news-list {
  display: grid;
  gap: 30px;
}

.news-card {
  background: white;
  border-radius: 16px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  cursor: pointer;
  display: flex;
  gap: 24px;
}

.news-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
}

.news-image {
  position: relative;
  width: 300px;
  height: 200px;
  flex-shrink: 0;
}

.news-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.news-category {
  position: absolute;
  top: 12px;
  left: 12px;
  background: rgba(0, 0, 0, 0.8);
  color: white;
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 0.8rem;
}

.news-content-area {
  flex: 1;
  padding: 24px;
  display: flex;
  flex-direction: column;
}

.news-content-area h3 {
  font-size: 1.4rem;
  font-weight: 600;
  margin-bottom: 12px;
  color: #333;
  line-height: 1.4;
}

.news-summary {
  color: #666;
  line-height: 1.6;
  margin-bottom: 16px;
  flex: 1;
}

.news-meta {
  display: flex;
  gap: 16px;
  margin-bottom: 12px;
  font-size: 0.9rem;
  color: #999;
  flex-wrap: wrap;
}

.news-tags {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
}

.news-tag {
  background: #f0f9ff;
  color: #0369a1;
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8rem;
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
  .page-header h1 {
    font-size: 2rem;
  }
  
  .news-card {
    flex-direction: column;
  }
  
  .news-image {
    width: 100%;
    height: 200px;
  }
  
  .category-tabs {
    justify-content: flex-start;
    overflow-x: auto;
    padding-bottom: 10px;
  }
}
</style> 