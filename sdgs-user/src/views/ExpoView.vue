<template>
  <div class="expo">
    <!-- 页面头部 -->
    <div class="page-header">
      <div class="container">
        <h1>SDGs 展览</h1>
        <p>探索联合国17个可持续发展目标</p>
        
        <!-- 子页面导航 -->
        <div class="sub-nav">
          <router-link to="/expo/background" class="nav-btn">
            📚 世博会背景
          </router-link>
          <router-link to="/expo/summit" class="nav-btn">
            🏛️ 世博会峰会
          </router-link>
        </div>
      </div>
    </div>

    <!-- 子页面内容或主要内容 -->
    <router-view v-if="$route.matched.length > 1" />
    
    <!-- SDGs 目标展示（主页面内容） -->
    <div v-else class="expo-content">
      <div class="container">
        <!-- 搜索和筛选 -->
        <div class="search-section">
          <el-input
            v-model="searchQuery"
            placeholder="搜索SDG目标..."
            prefix-icon="Search"
            size="large"
            style="max-width: 400px"
          />
        </div>

        <!-- SDGs 卡片网格 -->
        <div class="sdg-grid">
          <div
            v-for="sdg in filteredSDGs"
            :key="sdg.id"
            class="sdg-card"
            :style="{ backgroundColor: sdg.color }"
            @click="openSDGDetail(sdg)"
          >
            <div class="sdg-number">{{ sdg.id }}</div>
            <div class="sdg-content">
              <h3>{{ sdg.title }}</h3>
              <p>{{ sdg.description }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- SDG 详情对话框 -->
    <el-dialog
      v-model="dialogVisible"
      :title="`SDG ${selectedSDG?.id}: ${selectedSDG?.title}`"
      width="80%"
      max-width="800px"
    >
      <div v-if="selectedSDG" class="sdg-detail">
        <p class="sdg-full-description">{{ selectedSDG.fullDescription }}</p>
        <div class="sdg-targets">
          <h4>主要目标：</h4>
          <ul>
            <li v-for="target in selectedSDG.targets" :key="target">{{ target }}</li>
          </ul>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'

const $route = useRoute()

// 响应式数据
const searchQuery = ref('')
const dialogVisible = ref(false)
const selectedSDG = ref(null)

// SDGs 数据
const sdgs = ref([
  {
    id: 1,
    title: '消除贫困',
    description: '在全世界消除一切形式的贫困',
    color: '#e5243b',
    fullDescription: '消除贫困是可持续发展最重要的挑战，也是消除饥饿、促进健康和福祉、确保包容和公平的优质教育等其他目标的先决条件。',
    targets: ['到2030年，在世界各地消除极端贫困', '减少至少一半的各种贫困人口', '建立适合穷人和弱势群体的社会保护制度']
  },
  {
    id: 2,
    title: '消除饥饿',
    description: '消除饥饿，实现粮食安全，改善营养状况和促进可持续农业',
    color: '#dda63a',
    fullDescription: '消除饥饿、实现粮食安全、改善营养和促进可持续农业对于建设我们希望的世界至关重要。',
    targets: ['到2030年消除饥饿', '消除所有形式的营养不良', '促进可持续农业实践']
  },
  {
    id: 3,
    title: '良好健康与福祉',
    description: '确保健康的生活方式，促进各年龄段人群的福祉',
    color: '#4c9f38',
    fullDescription: '确保健康的生活方式，促进各年龄段所有人的福祉，这对可持续发展至关重要。',
    targets: ['降低全球孕产妇死亡率', '消除可预防的新生儿和儿童死亡', '终结艾滋病、结核病、疟疾等流行病']
  },
  // 可以继续添加其他14个SDG目标...
  {
    id: 4,
    title: '优质教育',
    description: '确保包容和公平的优质教育，让全民终身享有学习机会',
    color: '#c5192d',
    fullDescription: '获得包容、公平的优质教育和终身学习机会对每个人都至关重要。教育是实现其他可持续发展目标的关键。',
    targets: ['确保所有男女童完成免费初等和中等教育', '确保获得优质学前教育', '消除教育中的性别差距']
  },
  {
    id: 5,
    title: '性别平等',
    description: '实现性别平等，增强所有妇女和女童的权能',
    color: '#ff3a21',
    fullDescription: '性别平等不仅是基本人权，也是繁荣和可持续世界的必要基础。',
    targets: ['消除对妇女和女童的各种形式歧视', '消除针对妇女和女童的暴力', '确保妇女平等参与政治和经济生活']
  }
])

// 计算属性：过滤后的SDGs
const filteredSDGs = computed(() => {
  if (!searchQuery.value) {
    return sdgs.value
  }
  return sdgs.value.filter(sdg =>
    sdg.title.toLowerCase().includes(searchQuery.value.toLowerCase()) ||
    sdg.description.toLowerCase().includes(searchQuery.value.toLowerCase())
  )
})

// 方法
const openSDGDetail = (sdg) => {
  selectedSDG.value = sdg
  dialogVisible.value = true
}
</script>

<style scoped>
.expo {
  min-height: 100vh;
  background-color: #f8f9fa;
}

.page-header {
  background: linear-gradient(135deg, #2c5aa0 0%, #1e3a8a 100%);
  color: white;
  padding: 60px 0;
  text-align: center;
}

.container {
  max-width: 1200px;
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

.sub-nav {
  margin-top: 2rem;
  display: flex;
  justify-content: center;
  gap: 1rem;
}

.nav-btn {
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  padding: 12px 24px;
  background: rgba(255, 255, 255, 0.2);
  color: white;
  text-decoration: none;
  border-radius: 25px;
  font-weight: 500;
  transition: all 0.3s ease;
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.3);
}

.nav-btn:hover {
  background: rgba(255, 255, 255, 0.3);
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
}

.nav-btn.router-link-active {
  background: white;
  color: #2c5aa0;
}

.expo-content {
  padding: 60px 0;
}

.search-section {
  text-align: center;
  margin-bottom: 40px;
}

.sdg-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
}

.sdg-card {
  background: white;
  border-radius: 12px;
  padding: 24px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 20px;
  color: white;
}

.sdg-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

.sdg-number {
  font-size: 2.5rem;
  font-weight: 900;
  background: rgba(255, 255, 255, 0.2);
  width: 60px;
  height: 60px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.sdg-content h3 {
  font-size: 1.4rem;
  font-weight: 600;
  margin-bottom: 8px;
}

.sdg-content p {
  font-size: 0.95rem;
  line-height: 1.5;
  opacity: 0.95;
}

.sdg-detail .sdg-full-description {
  font-size: 1.1rem;
  line-height: 1.7;
  margin-bottom: 24px;
  color: #555;
}

.sdg-targets h4 {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 12px;
  color: #333;
}

.sdg-targets ul {
  list-style: none;
  padding: 0;
}

.sdg-targets li {
  padding: 8px 0;
  border-bottom: 1px solid #eee;
  color: #666;
}

.sdg-targets li:before {
  content: '✓';
  color: #4c9f38;
  font-weight: bold;
  margin-right: 10px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .page-header h1 {
    font-size: 2rem;
  }
  
  .sdg-grid {
    grid-template-columns: 1fr;
  }
  
  .sdg-card {
    flex-direction: column;
    text-align: center;
  }
}
</style> 