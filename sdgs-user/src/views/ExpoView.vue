<template>
  <div class="expo-page">
    <!-- 世博会标题 -->
    <div class="page-title">
      <h1>{{ expoInfo?.title || 'null' }}</h1>
    </div>

    <!-- 主图片区域 -->
    <div class="hero-image" v-if="expoInfo?.imageUrl">
      <img :src="expoInfo.imageUrl" alt="世博会图片" class="hero-img" />
    </div>

    <!-- 副标题 -->
    <div class="subtitle">
      <h2>{{ expoInfo?.subtitle || 'null' }}</h2>
    </div>

    <!-- 描述内容 -->
    <div class="description">
      <div v-if="expoInfo?.description" class="description-content">
        <p v-for="(paragraph, index) in descriptionParagraphs" :key="index" class="description-text">
          {{ paragraph }}
        </p>
      </div>
      <p v-else class="description-text">null</p>
    </div>

    <!-- 展示区域标题 -->
    <div class="showcase-header">
      <h2 class="showcase-main-title">{{ expoInfo?.cardTitle || 'null' }}</h2>
      <h3 class="showcase-sub-title">{{ expoInfo?.cardSubtitle || 'null' }}</h3>
    </div>

    <!-- 新增的展示区域 -->
    <div class="expo-showcase">
      <div class="showcase-container">
        <!-- 左侧图片区域 -->
        <div class="showcase-image">
          <div class="image-placeholder-showcase" v-if="!expoInfo?.cardImageUrl">
            <span class="placeholder-text">null</span>
          </div>
          <img v-else :src="expoInfo.cardImageUrl" alt="大卡片图片" class="showcase-img" />
        </div>

        <!-- 右侧内容区域 -->
        <div class="showcase-content">
          <h2 class="showcase-title">{{ expoInfo?.cardSubtitle || 'null' }}</h2>
          <div class="showcase-description">
            <p v-if="expoInfo?.cardDescription">
              {{ expoInfo.cardDescription }}
            </p>
            <p v-else>null</p>
          </div>

          <!-- 探索按钮 -->
          <div class="explore-button-container">
            <button class="explore-button" @click="goToBackground">
              探索 <span class="arrow">></span>
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 三个section展示区域 -->
    <div class="three-sections">
      <!-- 第一个section -->
      <div class="section-item">
        <h2 class="section-title">{{ expoInfo?.sectionTitle1 || 'null' }}</h2>
        <div class="section-image" v-if="expoInfo?.sectionImageUrl1">
          <img :src="expoInfo.sectionImageUrl1" alt="section图片" class="section-img" />
        </div>
      </div>

      <!-- 第二个section -->
      <div class="section-item">
        <h2 class="section-title">{{ expoInfo?.sectionTitle2 || 'null' }}</h2>
        <div class="section-image" v-if="expoInfo?.sectionImageUrl2">
          <img :src="expoInfo.sectionImageUrl2" alt="section图片" class="section-img" />
        </div>
      </div>

      <!-- 第三个section -->
      <div class="section-item">
        <h2 class="section-title">{{ expoInfo?.sectionTitle3 || 'null' }}</h2>
        <div class="section-image" v-if="expoInfo?.sectionImageUrl3">
          <img :src="expoInfo.sectionImageUrl3" alt="section图片" class="section-img" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRouter } from 'vue-router'
import request from '@/utils/request'

const router = useRouter()

// 响应式数据
const expoInfo = ref(null)
const loading = ref(false)

// 计算属性：将描述内容按段落分割
const descriptionParagraphs = computed(() => {
  if (!expoInfo.value?.description || typeof expoInfo.value.description !== 'string') {
    return []
  }

  // 按换行符分割段落，过滤空行
  return expoInfo.value.description
    .split(/\n+/) // 支持多个连续换行符
    .map(p => p.trim())
    .filter(p => p.length > 0)
})

// 获取世博会信息
const getExpoInfo = async () => {
  loading.value = true
  try {
    const data = await request.get('/expo/info')
    expoInfo.value = data
  } catch (error) {
    console.error('获取世博会信息失败:', error)
    // 出错时保持expoInfo为null，页面会显示null
    expoInfo.value = null
  } finally {
    loading.value = false
  }
}

// 跳转到峰会页面
const goToBackground = () => {
  router.push('/expo/summit')
}

// 组件挂载时获取数据
onMounted(() => {
  getExpoInfo()
})
</script>

<style scoped>
.expo-page {
  max-width: 1400px;
  margin: 0 auto;
  padding: 40px 80px;
  font-family: 'Microsoft YaHei', Arial, sans-serif;
  background-color: #ffffff;
  min-height: 100vh;
}

/* 世博会标题 */
.page-title {
  margin-bottom: 30px;
}

.page-title h1 {
  font-size: 48px;
  font-weight: bold;
  color: #1e88e5;
  margin: 0;
  letter-spacing: 2px;
}

/* 主图片区域 */
.hero-image {
  margin-bottom: 40px;
  border-radius: 20px;
  overflow: hidden;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
}

.hero-img {
  width: 100%;
  height: 400px;
  object-fit: cover;
  display: block;
  border-radius: 20px;
}

/* 副标题 */
.subtitle {
  margin-bottom: 30px;
}

.subtitle h2 {
  font-size: 36px;
  font-weight: bold;
  color: #1e88e5;
  margin: 0;
  letter-spacing: 1px;
}

/* 描述内容 */
.description {
  line-height: 1.8;
  margin-bottom: 60px;
}

.description-text {
  font-size: 18px;
  color: #333;
  margin-bottom: 20px;
  text-align: justify;
  line-height: 1.8;
  letter-spacing: 0.5px;
}

.description-text:last-child {
  margin-bottom: 0;
}

/* 展示区域标题样式 */
.showcase-header {
  margin-top: 60px;
  margin-bottom: 30px;
  text-align: left;
  display: flex;
  align-items: center;
  gap: 20px;
}

.showcase-main-title {
  font-size: 40px;
  font-weight: bold;
  color: #1e88e5;
  margin: 0;
  letter-spacing: 1px;
}

.showcase-sub-title {
  font-size: 24px;
  font-weight: normal;
  color: #1e88e5;
  margin: 0;
  letter-spacing: 1px;
}

/* 新增的展示区域样式 */
.expo-showcase {
  background: #e3f2fd;
  border-radius: 20px;
  overflow: hidden;
}

.showcase-container {
  display: flex;
  align-items: stretch;
  gap: 0;
  padding: 0;
  min-height: 400px;
}

.showcase-image {
  flex: 0 0 400px;
}

.image-placeholder-showcase {
  width: 100%;
  height: 100%;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 20px 0 0 20px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.showcase-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 20px 0 0 20px;
}

.showcase-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 60px;
}

.showcase-title {
  font-size: 20px;
  font-weight: bold;
  color: #000000;
  margin: 0 0 30px 0;
  line-height: 1.2;
}

.showcase-description {
  margin-bottom: 40px;
}

.showcase-description p {
  font-size: 18px;
  color: #333;
  line-height: 1.8;
  margin: 0;
  text-align: justify;
  letter-spacing: 0.5px;
}

.explore-button-container {
  display: flex;
  justify-content: flex-end;
}

.explore-button {
  background: linear-gradient(135deg, #1e88e5 0%, #42a5f5 100%);
  color: white;
  border: none;
  padding: 15px 30px;
  border-radius: 50px;
  font-size: 18px;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(30, 136, 229, 0.3);
}

.explore-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(30, 136, 229, 0.4);
}

.explore-button .arrow {
  font-size: 20px;
  transition: transform 0.3s ease;
}

.explore-button:hover .arrow {
  transform: translateX(3px);
}

/* 三个section样式 */
.three-sections {
  margin-top: 60px;
  display: flex;
  flex-direction: column;
  gap: 60px;
}

.section-item {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.section-title {
  font-size: 48px;
  font-weight: bold;
  color: #1e88e5;
  margin: 0 0 30px 0;
  letter-spacing: 2px;
  text-align: center;
}

.section-image {
  border-radius: 20px;
  overflow: hidden;
  /* box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1); */
}

.section-img {
  width: 100%;
  height: auto;
  display: block;
  border-radius: 20px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .expo-page {
    padding: 20px 40px;
  }

  .page-title h1 {
    font-size: 36px;
  }

  .subtitle h2 {
    font-size: 28px;
  }

  .description-text {
    font-size: 16px;
  }

  .hero-img {
    height: 250px;
  }

  .showcase-header {
    margin-top: 40px;
    margin-bottom: 20px;
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
  }

  .showcase-main-title {
    font-size: 30px;
    letter-spacing: 1px;
  }

  .showcase-sub-title {
    font-size: 18px;
  }

  .expo-showcase {
    margin-top: 0;
  }

  .showcase-container {
    flex-direction: column;
    gap: 0;
    padding: 0;
    min-height: 350px;
  }

  .showcase-image {
    flex: none;
    width: 100%;
    height: 200px;
  }

  .image-placeholder-showcase {
    height: 100%;
    border-radius: 20px 20px 0 0;
  }

  .showcase-img {
    border-radius: 20px 20px 0 0;
  }

  .showcase-content {
    flex: 1;
    padding: 30px;
  }

  .showcase-title {
    font-size: 18px;
  }

  .showcase-description p {
    font-size: 16px;
  }

  .explore-button {
    font-size: 16px;
    padding: 12px 25px;
  }

  .three-sections {
    margin-top: 40px;
    gap: 40px;
  }

  .section-title {
    font-size: 36px;
    margin: 0 0 20px 0;
    letter-spacing: 1px;
  }


}

@media (max-width: 480px) {
  .expo-page {
    padding: 20px 25px;
  }

  .page-title h1 {
    font-size: 28px;
    letter-spacing: 1px;
  }

  .subtitle h2 {
    font-size: 24px;
  }

  .description-text {
    font-size: 15px;
  }

  .showcase-header {
    margin-top: 30px;
    margin-bottom: 15px;
    flex-direction: column;
    align-items: flex-start;
    gap: 8px;
  }

  .showcase-main-title {
    font-size: 24px;
    letter-spacing: 1px;
  }

  .showcase-sub-title {
    font-size: 16px;
  }

  .expo-showcase {
    margin-top: 0;
  }

  .showcase-container {
    padding: 0;
    min-height: 300px;
  }

  .showcase-image {
    height: 150px;
  }

  .showcase-content {
    flex: 1;
    padding: 20px;
  }

  .showcase-title {
    font-size: 17px;
  }

  .showcase-description p {
    font-size: 15px;
  }

  .explore-button-container {
    justify-content: center;
  }

  .three-sections {
    margin-top: 30px;
    gap: 30px;
  }

  .section-title {
    font-size: 28px;
    margin: 0 0 15px 0;
    letter-spacing: 1px;
  }


}
</style>