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
          <div
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
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const $router = useRouter()

// 响应式数据
const searchQuery = ref('')
const selectedCategory = ref('all')

// 分类数据
const categories = ref([
  { id: 'all', name: '全部' },
  { id: 'climate', name: '气候行动' },
  { id: 'education', name: '教育发展' },
  { id: 'health', name: '健康福祉' },
  { id: 'economy', name: '经济发展' },
  { id: 'environment', name: '环境保护' }
])

// 新闻数据
const newsData = ref([
  {
    id: 1,
    title: '联合国发布2024年可持续发展目标进展报告',
    summary: '报告显示全球在减贫、教育、健康等领域取得显著进展，但气候变化仍是最大挑战。',
    category: '全部',
    categoryId: 'all',
    date: '2024-03-10',
    source: '联合国官网',
    readTime: 5,
    image: '/images/news1.jpg',
    tags: ['SDG报告', '全球进展', '联合国']
  },
  {
    id: 2,
    title: '全球气候峰会达成新的减排协议',
    summary: '195个国家承诺在2030年前将碳排放量减少50%，加速向清洁能源转型。',
    category: '气候行动',
    categoryId: 'climate',
    date: '2024-03-08',
    source: 'Climate News',
    readTime: 8,
    image: '/images/news2.jpg',
    tags: ['气候变化', '减排', '清洁能源']
  },
  {
    id: 3,
    title: '数字教育平台助力全球教育公平',
    summary: '新兴的在线教育技术正在帮助发展中国家的儿童获得优质教育资源。',
    category: '教育发展',
    categoryId: 'education',
    date: '2024-03-05',
    source: 'Education Today',
    readTime: 6,
    image: '/images/news3.jpg',
    tags: ['数字教育', '教育公平', '技术创新']
  },
  {
    id: 4,
    title: '全球疫苗接种计划显著改善儿童健康',
    summary: '世界卫生组织报告显示，全球儿童疫苗接种率达到历史新高。',
    category: '健康福祉',
    categoryId: 'health',
    date: '2024-03-03',
    source: 'WHO',
    readTime: 4,
    image: '/images/news4.jpg',
    tags: ['疫苗接种', '儿童健康', 'WHO']
  },
  {
    id: 5,
    title: '绿色金融推动可持续经济发展',
    summary: '越来越多的金融机构将ESG因素纳入投资决策，推动经济向可持续方向发展。',
    category: '经济发展',
    categoryId: 'economy',
    date: '2024-03-01',
    source: 'Finance Weekly',
    readTime: 7,
    image: '/images/news5.jpg',
    tags: ['绿色金融', 'ESG', '可持续投资']
  }
])

// 计算属性：过滤后的新闻
const filteredNews = computed(() => {
  let filtered = newsData.value

  // 按分类筛选
  if (selectedCategory.value !== 'all') {
    filtered = filtered.filter(news => news.categoryId === selectedCategory.value)
  }

  // 按搜索关键词筛选
  if (searchQuery.value) {
    const query = searchQuery.value.toLowerCase()
    filtered = filtered.filter(news =>
      news.title.toLowerCase().includes(query) ||
      news.summary.toLowerCase().includes(query) ||
      news.tags.some(tag => tag.toLowerCase().includes(query))
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