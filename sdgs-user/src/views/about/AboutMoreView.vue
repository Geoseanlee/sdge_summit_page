<template>
  <div class="about-more-page">
    <div class="breadcrumb">
      <router-link to="/" class="icon-link">
        <img src="@/assets/home-icon.png" alt="首页" class="home-icon">
      </router-link>
      <img src="@/assets/arrow-icon.png" alt=">" class="separator">
      <router-link to="/about" class="breadcrumb-link">关于我们</router-link>
      <img src="@/assets/arrow-icon.png" alt=">" class="separator">
      <span class="current">{{ pageConfig.title || '...' }}</span>
    </div>

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

/* 面包屑样式，左对齐并与 container 保持一致 */
.breadcrumb {
  display: flex;
  align-items: center;
  font-size: 20px;
  font-weight: 900 !important;
  color: #0167a5;
  margin-bottom: 12px;
  margin-top: -20px;
  margin-left: -20px;
  padding-left: 0;
  padding-right: 0;
}

.breadcrumb a,
.breadcrumb-link,
.current {
  color: #0167a5;
  text-decoration: none;
  font-weight: 700 !important;
}

.separator {
  width: 16px;
  height: 16px;
  margin: 0 8px;
  vertical-align: middle;
  object-fit: contain;
}

.home-icon {
  width: 30px;
  height: 30px;
  object-fit: contain;
  vertical-align: middle;
}

.breadcrumb-link:hover {
  color: #014f7d;
  text-decoration: underline;
}

.icon-link:hover .home-icon {
  filter: brightness(0.8);
  transform: scale(1.1);
  transition: all 0.3s ease;
}

.main-title h1 {
  color: #004a91;
  /* 大标题颜色 */
  font-size: 2em;
  text-align: center;
  margin-bottom: 50px;
  font-weight: bold;
}

/* 移除了带竖线的样式 */
.content-sections-no-line {
  /* 原本的左边框和内边距已被移除 */
  margin-bottom: 20px;
}

.content-block {
  margin-bottom: 40px;
}

/* 小标题的新样式 */
.section-subtitle {
  color: #1e65a5;
  /* 比大标题 #004a91 浅一些的蓝色 */
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