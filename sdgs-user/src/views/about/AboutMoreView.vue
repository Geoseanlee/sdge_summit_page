<template>
  <div class="about-more-page">
    <div class="breadcrumb-container">
      <router-link to="/">首页</router-link> >
      <router-link to="/about">关于我们</router-link> >
      <span>{{ pageConfig.title || '...' }}</span> </div>

    <div class="main-title">
      <h1>{{ pageConfig.title }}</h1>
    </div>

    <div class="content-sections-no-line">
      <section v-for="section in sections" :key="section.id" class="content-block">
        <h2 class="section-subtitle">{{ section.title }}</h2>
        <p class="section-content">{{ section.content }}</p>
      </section>
    </div>

    <div class="final-statement">
      <p>{{ pageConfig.finalParagraph }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue';
import request from '@/utils/request';
import { ElMessage } from 'element-plus';

const sections = ref([]);
const loading = ref(true);

// 新增：使用reactive来存储页面配置
const pageConfig = reactive({
  title: '',
  finalParagraph: ''
});

const fetchData = async () => {
  loading.value = true;
  try {
    // 使用Promise.all并行加载两个接口
    const [configRes, sectionsRes] = await Promise.all([
      request.get('/about-more/public/config'),
      request.get('/about-more/public/sections')
    ]);

    // 处理配置数据
    const configData = JSON.parse(configRes.configData);
    pageConfig.title = configData.title;
    pageConfig.finalParagraph = configData.finalParagraph;

    // 处理动态区块数据
    sections.value = sectionsRes;

  } catch (error) {
    ElMessage.error('内容加载失败');
    console.error('Failed to fetch page data:', error);
  } finally {
    loading.value = false;
  }
};

onMounted(fetchData);
</script>

<style scoped>
.about-more-page {
  max-width: 900px;
  margin: 40px auto;
  padding: 0 20px;
  color: #333;
}
.breadcrumb-container {
  color: #6c757d;
  margin-bottom: 30px;
}
.breadcrumb-container a {
  color: #007bff;
  text-decoration: none;
}
.main-title h1 {
  color: #004a91; /* 大标题颜色 */
  font-size: 2em;
  text-align: center;
  margin-bottom: 50px;
  font-weight: bold;
}
/* 移除了带竖线的样式 */
.content-sections-no-line {
  /* border-left: 3px solid #004a91; */ /* 移除左边框 */
  /* padding-left: 30px; */ /* 移除左内边距 */
}
.content-block {
  margin-bottom: 40px;
}
/* 小标题的新样式 */
.section-subtitle {
  color: #1e65a5; /* 比大标题 #004a91 浅一些的蓝色 */
  font-size: 1.4em;
  font-weight: bold;
  margin-bottom: 15px;
}
.section-content {
  line-height: 1.8;
  text-align: justify;
}
.final-statement {
  background-color: #e7f3ff;
  color: #004a91;
  padding: 20px;
  border-radius: 5px;
  margin-top: 40px;
  text-align: center;
  font-weight: 500;
  line-height: 1.7;
}
</style>