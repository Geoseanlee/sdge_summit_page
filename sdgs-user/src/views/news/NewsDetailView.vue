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
                <span class="date">日期: {{ article.date }}</span>
                <span class="views">阅读: {{ article.views }}</span>
              </div>

            </header>

            <div class="article-body">
              <div class="featured-image">
                <img :src="article.image" :alt="article.title" />
              </div>
              
              <div class="article-text" v-html="article.content"></div>
              

            </div>


          </article>


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
      image: response.coverImageUrl || '/placeholder-news.jpg'
    }
      } catch (error) {
      console.error('Error fetching article:', error)
    } finally {
    loading.value = false
  }
}





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
  display: block;
  max-width: 1000px;
  margin: 0 auto;
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
    margin: 0 20px;
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