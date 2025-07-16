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
        <div class="content-wrapper">
          <!-- 文章内容 -->
          <article class="article-content">
            <header class="article-header">
              <h1>{{ article.title }}</h1>
              <div class="article-meta">
                <span class="author">📝 {{ article.author }}</span>
                <span class="date">📅 {{ article.date }}</span>
                <span class="category">🏷️ {{ article.category }}</span>
                <span class="views">👁️ {{ article.views }} 阅读</span>
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
              
              <!-- 相关链接 -->
              <div class="related-links" v-if="article.relatedLinks">
                <h3>相关链接</h3>
                <ul>
                  <li v-for="link in article.relatedLinks" :key="link.title">
                    <a :href="link.url" target="_blank">{{ link.title }}</a>
                  </li>
                </ul>
              </div>
            </div>

            <!-- 文章底部 -->
            <footer class="article-footer">
              <div class="share-section">
                <h4>分享文章</h4>
                <div class="share-buttons">
                  <button class="share-btn weibo">微博</button>
                  <button class="share-btn wechat">微信</button>
                  <button class="share-btn qq">QQ</button>
                  <button class="share-btn copy">复制链接</button>
                </div>
              </div>
            </footer>
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

        <!-- 评论区 -->
        <section class="comments-section">
          <h2>评论 ({{ comments.length }})</h2>
          
          <!-- 发表评论 -->
          <div class="comment-form">
            <el-form @submit.prevent="submitComment">
              <el-form-item>
                <el-input
                  v-model="newComment.author"
                  placeholder="您的姓名"
                  style="width: 200px; margin-right: 16px;"
                />
                <el-input
                  v-model="newComment.email"
                  placeholder="邮箱地址"
                  style="width: 200px;"
                />
              </el-form-item>
              <el-form-item>
                <el-input
                  v-model="newComment.content"
                  type="textarea"
                  :rows="4"
                  placeholder="写下您的评论..."
                  style="width: 100%;"
                />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitComment">发表评论</el-button>
              </el-form-item>
            </el-form>
          </div>

          <!-- 评论列表 -->
          <div class="comments-list">
            <div 
              v-for="comment in comments" 
              :key="comment.id"
              class="comment-item"
            >
              <div class="comment-avatar">
                <span>{{ comment.author.charAt(0) }}</span>
              </div>
              <div class="comment-content">
                <div class="comment-header">
                  <span class="comment-author">{{ comment.author }}</span>
                  <span class="comment-date">{{ comment.date }}</span>
                </div>
                <p class="comment-text">{{ comment.content }}</p>
                <div class="comment-actions">
                  <button class="action-btn">👍 {{ comment.likes }}</button>
                  <button class="action-btn">💬 回复</button>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'

const route = useRoute()
const articleId = route.params.id

// 文章数据
const article = ref({
  id: 1,
  title: '联合国发布2024年可持续发展目标进展报告：全球合作迎来新机遇',
  author: '联合国新闻中心',
  date: '2024年3月15日',
  category: '政策发布',
  views: 2345,
  tags: ['SDGs', '政策', '全球合作', '可持续发展'],
  image: 'https://images.unsplash.com/photo-1573164713714-d95e436ab8d6?w=800&h=400&fit=crop',
  content: `
    <h2 id="section1">背景介绍</h2>
    <p>联合国在纽约总部正式发布了《2024年可持续发展目标进展报告》，这是自2015年通过《2030年可持续发展议程》以来的第九次年度评估报告。报告全面分析了全球在17个可持续发展目标方面取得的进展和面临的挑战。</p>
    
    <p>报告指出，尽管面临新冠疫情、地缘政治冲突和气候变化等多重挑战，国际社会在推进可持续发展目标方面仍取得了显著进展。特别是在清洁能源、数字技术应用和国际合作机制建设方面，呈现出前所未有的发展势头。</p>

    <h2 id="section2">主要内容</h2>
    <p>报告重点强调了以下几个方面的重要进展：</p>
    
    <h3>1. 贫困减少成效显著</h3>
    <p>全球极端贫困人口比例从2015年的10.1%下降至2023年的8.4%，超过1.3亿人摆脱了极端贫困。其中，亚洲和非洲地区的减贫成效最为突出。</p>
    
    <h3>2. 教育普及率持续提升</h3>
    <p>全球小学教育净入学率达到95.2%，性别教育差距进一步缩小。数字教育技术的广泛应用为偏远地区的教育发展带来了新机遇。</p>
    
    <h3>3. 清洁能源发展迅速</h3>
    <p>可再生能源占全球发电量的比例达到30.1%，太阳能和风能成本大幅下降，为能源转型提供了有力支撑。</p>

    <h2 id="section3">重要意义</h2>
    <p>这份报告的发布具有重要的现实意义和深远的历史意义：</p>
    
    <blockquote>
      <p>"我们正处在实现可持续发展目标的关键时期。虽然面临挑战，但我们有理由对未来保持乐观。"</p>
      <cite>— 联合国秘书长安东尼奥·古特雷斯</cite>
    </blockquote>
    
    <p>报告为各国政府、国际组织和民间社会提供了重要的政策参考，有助于调整发展战略，加强国际合作，确保在2030年实现所有可持续发展目标。</p>

    <h2 id="section4">未来展望</h2>
    <p>展望未来，报告提出了三大重点行动领域：</p>
    
    <ul>
      <li><strong>加强多边合作</strong>：建立更加有效的国际合作机制，促进资源共享和技术转移。</li>
      <li><strong>促进创新发展</strong>：充分利用数字技术、人工智能等新兴技术，推动可持续发展模式创新。</li>
      <li><strong>确保包容性增长</strong>：重点关注最脆弱群体的需求，确保发展成果惠及所有人。</li>
    </ul>
    
    <p>联合国呼吁国际社会继续加强合作，共同努力，确保在2030年实现所有可持续发展目标，为人类和地球创造更加美好的未来。</p>
  `,
  relatedLinks: [
    { title: '联合国可持续发展目标官网', url: 'https://sdgs.un.org/' },
    { title: '2030年可持续发展议程', url: 'https://sdgs.un.org/2030agenda' }
  ]
})

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

// 评论数据
const comments = ref([
  {
    id: 1,
    author: '张三',
    date: '2024年3月16日 10:30',
    content: '这份报告很有价值，为我们了解全球可持续发展现状提供了重要参考。希望各国能够加强合作，共同推进SDGs目标的实现。',
    likes: 15
  },
  {
    id: 2,
    author: '李四',
    date: '2024年3月16日 14:20',
    content: '清洁能源发展确实令人鼓舞，但在发展中国家的推广还需要更多的技术支持和资金援助。',
    likes: 8
  },
  {
    id: 3,
    author: '王五',
    date: '2024年3月16日 16:45',
    content: '教育普及率的提升很不错，但我们还需要关注教育质量的问题，特别是偏远地区的教育质量。',
    likes: 12
  }
])

// 新评论表单
const newComment = reactive({
  author: '',
  email: '',
  content: ''
})

// 提交评论
const submitComment = () => {
  if (!newComment.author || !newComment.content) {
    ElMessage.warning('请填写姓名和评论内容')
    return
  }
  
  const comment = {
    id: comments.value.length + 1,
    author: newComment.author,
    date: new Date().toLocaleString('zh-CN'),
    content: newComment.content,
    likes: 0
  }
  
  comments.value.unshift(comment)
  
  // 重置表单
  newComment.author = ''
  newComment.email = ''
  newComment.content = ''
  
  ElMessage.success('评论发表成功')
}

onMounted(() => {
  // 根据路由参数加载对应文章
  console.log('加载文章ID:', articleId)
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

.related-links {
  margin-top: 32px;
  padding: 24px;
  background: #f8f9fa;
  border-radius: 12px;
}

.related-links h3 {
  margin-bottom: 16px;
  color: #333;
}

.related-links ul {
  list-style: none;
  padding: 0;
}

.related-links li {
  margin-bottom: 8px;
}

.related-links a {
  color: #e11d48;
  text-decoration: none;
}

.related-links a:hover {
  text-decoration: underline;
}

.article-footer {
  padding: 40px;
  border-top: 1px solid #e5e7eb;
}

.share-section h4 {
  margin-bottom: 16px;
  color: #333;
}

.share-buttons {
  display: flex;
  gap: 12px;
}

.share-btn {
  padding: 8px 16px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 500;
  transition: transform 0.2s;
}

.share-btn:hover {
  transform: translateY(-2px);
}

.share-btn.weibo {
  background: #e6162d;
  color: white;
}

.share-btn.wechat {
  background: #07c160;
  color: white;
}

.share-btn.qq {
  background: #12b7f5;
  color: white;
}

.share-btn.copy {
  background: #6b7280;
  color: white;
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

/* 评论区样式 */
.comments-section {
  background: white;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  padding: 40px;
  margin-top: 40px;
}

.comments-section h2 {
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 24px;
  color: #333;
}

.comment-form {
  margin-bottom: 40px;
  padding: 24px;
  background: #f8f9fa;
  border-radius: 12px;
}

.comment-item {
  display: flex;
  gap: 16px;
  margin-bottom: 24px;
  padding-bottom: 24px;
  border-bottom: 1px solid #e5e7eb;
}

.comment-item:last-child {
  margin-bottom: 0;
  padding-bottom: 0;
  border-bottom: none;
}

.comment-avatar {
  flex: 0 0 40px;
}

.comment-avatar span {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 40px;
  height: 40px;
  background: #e11d48;
  color: white;
  border-radius: 50%;
  font-weight: 600;
}

.comment-content {
  flex: 1;
}

.comment-header {
  display: flex;
  gap: 16px;
  margin-bottom: 8px;
}

.comment-author {
  font-weight: 600;
  color: #333;
}

.comment-date {
  color: #999;
  font-size: 0.9rem;
}

.comment-text {
  margin-bottom: 12px;
  line-height: 1.6;
  color: #444;
}

.comment-actions {
  display: flex;
  gap: 16px;
}

.action-btn {
  background: none;
  border: none;
  color: #666;
  cursor: pointer;
  font-size: 0.9rem;
  padding: 4px 8px;
  border-radius: 4px;
  transition: all 0.2s;
}

.action-btn:hover {
  background: #f3f4f6;
  color: #333;
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
  
  .share-buttons {
    flex-wrap: wrap;
  }
  
  .comments-section {
    padding: 24px;
  }
}
</style> 