<template>
  <div class="home">
    <!-- 加载状态 -->
    <div v-if="loading" class="loading-container">
      <div class="loading-text">加载中...</div>
    </div>

    <!-- 主内容 -->
    <template v-else>
      <!-- 英雄区域 -->
      <div class="hero-section">
        <div class="hero-content">
          <div class="hero-titles-row">
            <div class="hero-item" v-if="homeInfo?.heroTitle1 || homeInfo?.heroSubtitle1">
              <h1 class="main-title blue-title" v-if="homeInfo?.heroTitle1">{{ homeInfo.heroTitle1 }}</h1>
              <p class="subtitle blue-subtitle" v-if="homeInfo?.heroSubtitle1">{{ homeInfo.heroSubtitle1 }}</p>
            </div>
            <div class="hero-item" v-if="homeInfo?.heroTitle2 || homeInfo?.heroSubtitle2">
              <h1 class="main-title blue-title" v-if="homeInfo?.heroTitle2">{{ homeInfo.heroTitle2 }}</h1>
              <p class="subtitle blue-subtitle" v-if="homeInfo?.heroSubtitle2">{{ homeInfo.heroSubtitle2 }}</p>
            </div>
          </div>
        </div>
        <div class="hero-image" v-if="homeInfo?.heroImageUrl">
          <img :src="homeInfo.heroImageUrl" alt="首页图片" class="hero-img" />
        </div>
      </div>

      <!-- SDGs介绍区域 -->
      <div class="sdgs-section">
        <div class="sdgs-content">
          <div class="sdgs-text">
            <p class="sdgs-description" v-if="homeInfo?.sdgsDescription1">
              {{ homeInfo.sdgsDescription1 }}
            </p>
            <p class="sdgs-description" v-if="homeInfo?.sdgsDescription2">
              {{ homeInfo.sdgsDescription2 }}
            </p>
          </div>
          <div class="sdgs-logo">
            <h2 class="sdgs-title">SDGs</h2>
            <button class="more-info-btn" @click="goToExpoBackground">
              了解更多信息 <span class="arrow">›</span>
            </button>
          </div>
        </div>
      </div>

      <!-- 探索区域 - 新增卡片式布局 -->
      <div class="explore-section">
        <div class="explore-card"
          :style="{ backgroundImage: homeInfo?.expoImageUrl ? `linear-gradient(rgba(255, 255, 255, 0.2), rgba(255, 255, 255, 0.2)), url(${homeInfo.expoImageUrl})` : 'none' }">
          <div class="explore-content">
            <h2 class="explore-title" v-if="homeInfo?.expoTitle">{{ homeInfo.expoTitle }}</h2>
            <p class="explore-subtitle" v-if="homeInfo?.expoSubtitle">{{ homeInfo.expoSubtitle }}</p>
            <button class="explore-btn" @click="goToExpo">
              探索 <span class="arrow">›</span>
            </button>
          </div>
        </div>
      </div>

      <!-- 博览会区域 -->
      <div class="expo-section" style="display: none;">
        <div class="section-content">
          <div class="expo-content">
            <h2 class="section-title" v-if="homeInfo?.expoTitle">{{ homeInfo.expoTitle }}</h2>
            <button class="expo-info-btn" @click="goToExpo">
              了解更多信息 <span class="arrow">›</span>
            </button>
          </div>
          <div class="expo-image" v-if="homeInfo?.expoImageUrl">
            <img :src="homeInfo.expoImageUrl" alt="博览会" class="expo-img" />
          </div>
        </div>
      </div>

      <!-- 联盟介绍 -->
      <div class="alliance-section">
        <div class="section-content">
          <div class="alliance-content">
            <h2 class="section-title" v-if="homeInfo?.allianceTitle">{{ homeInfo.allianceTitle }}</h2>
            <p class="alliance-description" v-if="homeInfo?.allianceDescription1">
              {{ homeInfo.allianceDescription1 }}
            </p>
            <p class="alliance-description" v-if="homeInfo?.allianceDescription2">
              {{ homeInfo.allianceDescription2 }}
            </p>
          </div>
        </div>
      </div>

      <!-- 相关数据区域 -->
      <div class="data-section">
        <div class="data-content">
          <h2 class="data-title" v-if="homeInfo?.dataTitle">{{ homeInfo.dataTitle }}</h2>
          <div class="data-image" v-if="homeInfo?.dataImageUrl">
            <img :src="homeInfo.dataImageUrl" alt="数据图表" class="data-img" />
          </div>
        </div>
      </div>

      <!-- 加入我们区域 -->
      <div class="join-us-section">
        <div class="join-us-content">
          <div class="join-us-main">
            <div class="join-us-text">
              <h2 class="join-us-title" v-if="homeInfo?.joinTitle">{{ homeInfo.joinTitle }}</h2>
              <ul class="join-us-list" v-if="homeInfo?.joinItem1 || homeInfo?.joinItem2 || homeInfo?.joinItem3">
                <li class="join-us-item" v-if="homeInfo?.joinItem1">{{ homeInfo.joinItem1 }}</li>
                <li class="join-us-item" v-if="homeInfo?.joinItem2">{{ homeInfo.joinItem2 }}</li>
                <li class="join-us-item" v-if="homeInfo?.joinItem3">{{ homeInfo.joinItem3 }}</li>
              </ul>
              <button class="join-info-btn" @click="goToJoinUs">
                了解更多信息 <span class="arrow">›</span>
              </button>
            </div>
            <div class="join-us-image" v-if="homeInfo?.joinImageUrl">
              <img :src="homeInfo.joinImageUrl" alt="加入我们" class="join-us-img" />
            </div>
          </div>
        </div>
      </div>
    </template>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import request from '@/utils/request'

const router = useRouter()

// 响应式数据
const homeInfo = ref(null)
const loading = ref(false)

// 获取首页信息
const getHomeInfo = async () => {
  loading.value = true
  try {
    const data = await request.get('/home/info')
    homeInfo.value = data
  } catch (error) {
    console.error('获取首页信息失败:', error)
  } finally {
    loading.value = false
  }
}

// 页面跳转方法
const goToBackground = () => {
  router.push('/about')
}

const goToExpoBackground = () => {
  router.push('/expo/background')
}

const goToExpo = () => {
  router.push('/expo')
}

const goToJoinUs = () => {
  router.push('/join-us')
}

// 页面挂载时获取数据
onMounted(() => {
  getHomeInfo()
})
</script>

<style scoped>
.home {
  max-width: 1400px;
  margin: 0 auto;
  padding: 40px 80px;
  background-color: white;
}

.loading-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50vh;
}

.loading-text {
  font-size: 18px;
  color: #666;
}

/* 英雄区域样式 */
.hero-section {
  padding: 2rem 0;
  text-align: center;
  background-color: white;
}

.hero-content {
  margin-bottom: 2rem;
}

.hero-titles-row {
  display: flex;
  justify-content: center;
  gap: 4rem;
  flex-wrap: wrap;
}

.hero-item {
  text-align: center;
}

.main-title {
  font-size: 3rem;
  font-weight: bold;
  margin-bottom: 1rem;
  color: #333;
}

.blue-title {
  color: #1976d2;
}

.subtitle {
  font-size: 1.4rem;
  color: #666;
  margin-bottom: 1rem;
}

.blue-subtitle {
  color: #1976d2;
}

.hero-image {
  text-align: center;
  margin: 2rem 0;
}

.hero-img {
  width: 1200px;
  max-width: 100%;
  height: auto;
  border-radius: 8px;
}

/* SDGs区域样式 */
.sdgs-section {
  padding: 2rem 0;
  background-color: white;
}

.sdgs-content {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  gap: 4rem;
}

.sdgs-text {
  flex: 1;
}

.sdgs-description {
  font-size: 1rem;
  line-height: 1.6;
  margin-bottom: 1rem;
  color: #333;
}

.sdgs-logo {
  flex-shrink: 0;
  text-align: center;
}

.sdgs-title {
  font-size: 2rem;
  font-weight: bold;
  color: #1976d2;
  margin-bottom: 1rem;
}

.more-info-btn {
  background-color: #1976d2;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
}

.more-info-btn:hover {
  background-color: #1565c0;
}

.arrow {
  margin-left: 0.5rem;
}

/* 探索区域样式 */
.explore-section {
  padding: 2rem 0;
  background-color: white;
}

.explore-card {
  max-width: 1200px;
  margin: 0 auto;
  min-height: 300px;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  border-radius: 16px;
  display: flex;
  align-items: flex-start;
  justify-content: flex-start;
  padding: 3rem;
  position: relative;
  overflow: hidden;
  background-color: #f5f5f5;
  /* 没有图片时的背景色 */
}

.explore-content {
  max-width: 500px;
  z-index: 2;
  padding-top: 1rem;
}

.explore-title {
  font-size: 2rem;
  font-weight: bold;
  color: white;
  margin-bottom: 1rem;
  line-height: 1.3;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
}

.explore-subtitle {
  font-size: 1.1rem;
  color: white;
  margin-bottom: 2rem;
  line-height: 1.5;
  text-shadow: 0 1px 2px rgba(0, 0, 0, 0.3);
}

.explore-btn {
  background-color: #4a90e2;
  color: white;
  border: none;
  padding: 12px 24px;
  border-radius: 25px;
  cursor: pointer;
  font-size: 1rem;
  font-weight: 500;
  transition: background-color 0.3s ease;
  display: inline-flex;
  align-items: center;
  gap: 8px;
}

.explore-btn:hover {
  background-color: #357abd;
}

/* 博览会区域样式 */
.expo-section {
  padding: 2rem 0;
  background-color: white;
}

.section-content {
  max-width: 1200px;
  margin: 0 auto;
}

.expo-content {
  margin-bottom: 2rem;
}

.section-title {
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 1rem;
  color: #333;
}

.expo-description {
  font-size: 1rem;
  line-height: 1.6;
  margin-bottom: 1rem;
  color: #666;
}

.expo-info-btn {
  background-color: #1976d2;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
}

.expo-info-btn:hover {
  background-color: #1565c0;
}

.expo-image {
  text-align: center;
  margin: 2rem 0;
}

.expo-img {
  max-width: 100%;
  height: auto;
  border-radius: 8px;
}

/* 联盟区域样式 */
.alliance-section {
  padding: 2rem 0;
  background-color: white;
}

.alliance-content {
  max-width: 1200px;
  margin: 0 auto;
  text-align: left;
}

.alliance-content .section-title {
  color: #1976d2;
  text-align: left;
  font-size: 1.9rem;
}

.alliance-description {
  font-size: 1rem;
  line-height: 1.6;
  margin-bottom: 1rem;
  color: #333;
  text-align: left;
}

/* 数据区域样式 */
.data-section {
  padding: 2rem 0;
  background-color: white;
}

.data-content {
  max-width: 1200px;
  margin: 0 auto;
  text-align: left;
}

.data-title {
  font-size: 1.9rem;
  font-weight: bold;
  margin-bottom: 2rem;
  color: #1976d2;
  text-align: left;
}

.data-image {
  text-align: center;
}

.data-img {
  width: 800px;
  max-width: 100%;
  height: auto;
  border-radius: 8px;
}

/* 加入我们区域样式 */
.join-us-section {
  padding: 2rem 0;
  background-color: white;
}

.join-us-content {
  max-width: 1200px;
  margin: 0 auto;
}

.join-us-main {
  display: flex;
  align-items: center;
  gap: 4rem;
}

.join-us-text {
  flex: 1;
}

.join-us-title {
  font-size: 1.9rem;
  font-weight: bold;
  margin-bottom: 1rem;
  color: #1976d2;
  text-align: left;
}

.join-us-list {
  list-style: none;
  padding: 0;
  margin-bottom: 2rem;
}

.join-us-item {
  font-size: 1rem;
  line-height: 1.6;
  margin-bottom: 1rem;
  padding-left: 1.5rem;
  position: relative;
  color: #333;
}

.join-us-item::before {
  content: '•';
  position: absolute;
  left: 0;
  color: #1976d2;
}

.join-info-btn {
  background-color: #1976d2;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
}

.join-info-btn:hover {
  background-color: #1565c0;
}

.join-us-image {
  flex-shrink: 0;
}

.join-us-img {
  width: 300px;
  height: 200px;
  object-fit: cover;
  border-radius: 8px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .home {
    padding: 20px 40px;
  }

  .hero-section,
  .expo-section {
    padding: 1rem 0;
  }

  .sdgs-section,
  .explore-section,
  .alliance-section,
  .data-section,
  .join-us-section {
    padding: 1rem 0;
  }

  .hero-titles-row {
    flex-direction: column;
    gap: 2rem;
  }

  .hero-img {
    width: 100%;
  }

  .sdgs-content {
    flex-direction: column;
    gap: 2rem;
  }

  .explore-card {
    min-height: 250px;
    padding: 2rem;
  }

  .explore-title {
    font-size: 1.5rem;
  }

  .explore-subtitle {
    font-size: 1rem;
  }

  .main-title {
    font-size: 2rem;
  }

  .section-title {
    font-size: 1.3rem;
  }

  .data-img {
    width: 100%;
  }

  .join-us-main {
    flex-direction: column;
    gap: 2rem;
  }

  .join-us-img {
    width: 100%;
    max-width: 300px;
    height: auto;
  }
}
</style>