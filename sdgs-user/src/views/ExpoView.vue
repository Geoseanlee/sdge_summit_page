<template>
  <div class="expo-page">
    <!-- 世博会标题 -->
    <div class="page-title">
      <h1>{{ expoInfo?.title || 'null' }}</h1>
    </div>

    <!-- 主图片区域 -->
    <div class="hero-image">
      <div class="image-placeholder" v-if="!expoInfo?.imageUrl">
        <!-- 没有图片时显示null -->
        <span class="placeholder-text">null</span>
      </div>
      <img v-else :src="expoInfo.imageUrl" alt="世博会图片" class="hero-img" />
    </div>

    <!-- 副标题 -->
    <div class="subtitle">
      <h2>{{ expoInfo?.subtitle || 'null' }}</h2>
    </div>

    <!-- 描述内容 -->
    <div class="description">
      <div v-if="expoInfo?.description" class="description-content">
        <p 
          v-for="(paragraph, index) in descriptionParagraphs" 
          :key="index" 
          class="description-text"
        >
          {{ paragraph }}
        </p>
      </div>
      <p v-else class="description-text">null</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import request from '@/utils/request'

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

.image-placeholder {
  width: 100%;
  height: 400px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
}

.placeholder-text {
  color: white;
  font-size: 24px;
  font-weight: 500;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
}

.hero-img {
  width: 100%;
  height: 400px;
  object-fit: cover;
  display: block;
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
  
  .image-placeholder {
    height: 250px;
  }
  
  .hero-img {
    height: 250px;
  }
  
  .placeholder-text {
    font-size: 18px;
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
}
</style> 