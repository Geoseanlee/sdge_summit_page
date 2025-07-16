<template>
  <div class="news-detail">
    <!-- 页面头部 -->
    <div class="page-header">
      <div class="container">
        <nav class="breadcrumb">
          <router-link to="/news">动态资讯</router-link>
          <span class="separator">></span>
          <span class="current">详情</span>
        </nav>
      </div>
    </div>

    <!-- 主要内容 -->
    <div class="detail-content">
      <div class="container">
        <!-- 加载状态 -->
        <div v-if="loading" class="loading-state">
          <p>正在加载文章...</p>
        </div>
        
        <!-- 文章不存在 -->
        <div v-else-if="!article" class="error-state">
          <p>文章不存在或已被删除</p>
        </div>
        
        <!-- 文章内容 -->
        <div v-else class="content-wrapper">
          <!-- 文章内容 -->
          <article class="article-content">
            <header class="article-header">
              <h1>{{ article.title }}</h1>
              <div class="article-meta">
                <span class="author">作者: {{ article.author }}</span>
                <span class="date">日期: {{ article.date }}</span>
                <span class="category">分类: {{ article.category }}</span>
                <span class="views">阅读: {{ article.views }}</span>
              </div>
              <div class="article-tags">
                <span 
                  v-for="tag in article.tags" 
                  :key="tag" 
                  class="tag"
                >
                  {{ tag }}
                </span>
              </div>
            </header>

            <div class="article-body">
              <div class="featured-image">
                <img :src="article.image" :alt="article.title" />
              </div>
              
              <div class="article-text" v-html="article.content"></div>
              

            </div>


          </article>

          <!-- 侧边栏 -->
          <aside class="sidebar">
            <!-- 文章导航 -->
            <div class="sidebar-card">
              <h3>文章导航</h3>
              <div class="toc">
                <ul>
                  <li><a href="#section1">背景介绍</a></li>
                  <li><a href="#section2">主要内容</a></li>
                  <li><a href="#section3">重要意义</a></li>
                  <li><a href="#section4">未来展望</a></li>
                </ul>
              </div>
            </div>

            <!-- 相关文章 -->
            <div class="sidebar-card">
              <h3>相关文章</h3>
              <div class="related-articles">
                <div 
                  v-for="relatedArticle in relatedArticles" 
                  :key="relatedArticle.id"
                  class="related-item"
                >
                  <div class="related-image">
                    <img :src="relatedArticle.image" :alt="relatedArticle.title" />
                  </div>
                  <div class="related-content">
                    <h4>{{ relatedArticle.title }}</h4>
                    <span class="related-date">{{ relatedArticle.date }}</span>
                  </div>
                </div>
              </div>
            </div>

            <!-- 热门标签 -->
            <div class="sidebar-card">
              <h3>热门标签</h3>
              <div class="hot-tags">
                <span 
                  v-for="tag in hotTags" 
                  :key="tag"
                  class="hot-tag"
                >
                  {{ tag }}
                </span>
              </div>
            </div>
          </aside>
        </div>


      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import request from '@/utils/request'

const route = useRoute()
const articleId = route.params.id

// 文章数据
const article = ref(null)
const loading = ref(false)

// 获取文章数据
const fetchArticle = async () => {
  loading.value = true
  try {
    const response = await request.get(`/news/${articleId}`)
    article.value = {
      ...response,
      date: response.publishTime ? new Date(response.publishTime).toLocaleDateString('zh-CN') : '',
      views: response.viewCount || 0,
      tags: response.tags ? response.tags.split(',').map(tag => tag.trim()) : [],
      image: response.coverImageUrl || 'https://images.unsplash.com/photo-1573164713714-d95e436ab8d6?w=800&h=400&fit=crop'
    }
      } catch (error) {
      console.error('Error fetching article:', error)
    } finally {
    loading.value = false
  }
}

// 相关文章
const relatedArticles = ref([
  {
    id: 2,
    title: '气候变化应对：全球碳中和进展评估',
    date: '2024年3月12日',
    image: 'https://images.unsplash.com/photo-1569163139394-de44cb3c0db9?w=200&h=120&fit=crop'
  },
  {
    id: 3,
    title: '数字技术赋能可持续发展创新实践',
    date: '2024年3月10日',
    image: 'https://images.unsplash.com/photo-1451187580459-43490279c0fa?w=200&h=120&fit=crop'
  },
  {
    id: 4,
    title: '全球教育公平：挑战与机遇并存',
    date: '2024年3月8日',
    image: 'https://images.unsplash.com/photo-1497486751825-1233686d5d80?w=200&h=120&fit=crop'
  }
])

// 热门标签
const hotTags = ref([
  'SDGs', '气候行动', '教育发展', '清洁能源', '国际合作', 
  '创新科技', '社会公平', '环境保护', '经济发展'
])



onMounted(() => {
  // 根据路由参数加载对应文章
  fetchArticle()
})
</script>

<style scoped>
.news-detail {
  min-height: 100vh;
  background-color: #f8f9fa;
}

.page-header {
  background: linear-gradient(135deg, #e11d48 0%, #be185d 100%);
  color: white;
  padding: 20px 0;
}

.container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 20px;
}

.breadcrumb {
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

.detail-content {
  padding: 40px 0;
}

.content-wrapper {
  display: grid;
  grid-template-columns: 1fr 300px;
  gap: 40px;
}

/* 文章内容样式 */
.article-content {
  background: white;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.article-header {
  padding: 40px;
  border-bottom: 1px solid #e5e7eb;
}

.article-header h1 {
  font-size: 2.2rem;
  font-weight: 700;
  color: #333;
  margin-bottom: 20px;
  line-height: 1.3;
}

.article-meta {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  margin-bottom: 16px;
  font-size: 0.9rem;
  color: #666;
}

.article-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.tag {
  background: #e11d48;
  color: white;
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 0.8rem;
  font-weight: 500;
}

.article-body {
  padding: 40px;
}

.featured-image {
  margin-bottom: 32px;
  border-radius: 12px;
  overflow: hidden;
}

.featured-image img {
  width: 100%;
  height: 400px;
  object-fit: cover;
}

.article-text {
  line-height: 1.8;
  color: #444;
}

.article-text :deep(h2) {
  font-size: 1.8rem;
  font-weight: 600;
  margin: 32px 0 16px;
  color: #333;
  padding-bottom: 8px;
  border-bottom: 2px solid #e11d48;
}

.article-text :deep(h3) {
  font-size: 1.4rem;
  font-weight: 600;
  margin: 24px 0 12px;
  color: #333;
}

.article-text :deep(p) {
  margin-bottom: 16px;
}

.article-text :deep(ul), .article-text :deep(ol) {
  margin: 16px 0;
  padding-left: 24px;
}

.article-text :deep(li) {
  margin-bottom: 8px;
}

.article-text :deep(blockquote) {
  background: #f8f9fa;
  border-left: 4px solid #e11d48;
  padding: 20px;
  margin: 24px 0;
  font-style: italic;
}

.article-text :deep(blockquote cite) {
  display: block;
  text-align: right;
  margin-top: 12px;
  font-size: 0.9rem;
  color: #666;
}





/* 侧边栏样式 */
.sidebar-card {
  background: white;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 24px;
  margin-bottom: 24px;
}

.sidebar-card h3 {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 16px;
  color: #333;
  border-bottom: 2px solid #e11d48;
  padding-bottom: 8px;
}

.toc ul {
  list-style: none;
  padding: 0;
}

.toc li {
  margin-bottom: 8px;
}

.toc a {
  color: #666;
  text-decoration: none;
  font-size: 0.9rem;
}

.toc a:hover {
  color: #e11d48;
}

.related-item {
  display: flex;
  gap: 12px;
  margin-bottom: 16px;
  padding-bottom: 16px;
  border-bottom: 1px solid #f3f4f6;
}

.related-item:last-child {
  margin-bottom: 0;
  padding-bottom: 0;
  border-bottom: none;
}

.related-image {
  flex: 0 0 60px;
}

.related-image img {
  width: 60px;
  height: 40px;
  object-fit: cover;
  border-radius: 6px;
}

.related-content h4 {
  font-size: 0.9rem;
  font-weight: 500;
  margin-bottom: 4px;
  color: #333;
  line-height: 1.3;
}

.related-date {
  font-size: 0.8rem;
  color: #999;
}

.hot-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.hot-tag {
  background: #f3f4f6;
  color: #666;
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8rem;
  cursor: pointer;
  transition: all 0.2s;
}

.hot-tag:hover {
  background: #e11d48;
  color: white;
}



.loading-state,
.error-state {
  text-align: center;
  padding: 100px 20px;
  color: #666;
}

.loading-state p,
.error-state p {
  font-size: 1.2rem;
  margin: 0;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .content-wrapper {
    grid-template-columns: 1fr;
  }
  
  .article-header {
    padding: 24px;
  }
  
  .article-header h1 {
    font-size: 1.8rem;
  }
  
  .article-body {
    padding: 24px;
  }
  
  .article-meta {
    font-size: 0.8rem;
  }
  

  

}
</style> 