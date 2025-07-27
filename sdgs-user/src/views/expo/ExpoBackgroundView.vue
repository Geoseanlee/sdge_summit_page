<template>
  <div class="expo-background">
    <div class="background-content">
      <div class="container">
        <!-- 面包屑放入 container 内部，与内容左对齐 -->
        <div class="breadcrumb">
          <a href="#" class="icon-link">
            <img src="@/assets/home-icon.png" alt="首页" class="home-icon">
          </a>
          <img src="@/assets/arrow-icon.png" alt=">" class="separator">
          <a href="#" class="breadcrumb-link">世博会</a>
          <img src="@/assets/arrow-icon.png" alt=">" class="separator">
          <span class="current">背景</span>
        </div>
        <!-- 第一部分 -->
        <section class="content-section combined-section">
          <h2 v-if="backgroundData.part1MainTitle" class="multiline-text">{{ backgroundData.part1MainTitle }}</h2>
          <h2 v-else>暂无标题</h2>
          <div class="divider"></div>

          <div class="block-flex">
            <div class="text-area">
              <h3 v-if="backgroundData.part1Section1Title" class="multiline-text">{{ backgroundData.part1Section1Title }}</h3>
              <h3 v-else>暂无标题</h3>
              <p
                v-if="backgroundData.part1Section1Content"
                class="multiline-text"
              >{{ backgroundData.part1Section1Content }}</p>
              <p v-else>暂无内容</p>
            </div>
            <div class="image-area">
              <img
                v-if="backgroundData.part1Section1Image"
                :src="backgroundData.part1Section1Image"
                alt="图1"
              />
              <div v-else class="placeholder-image">暂无图片</div>
            </div>
          </div>
          <div class="divider"></div>

          <div class="block-flex reverse-layout">
            <div class="text-area">
              <h3 v-if="backgroundData.part1Section2Title" class="multiline-text">{{ backgroundData.part1Section2Title }}</h3>
              <h3 v-else>暂无标题</h3>
              <p
                v-if="backgroundData.part1Section2Content"
                class="multiline-text"
              >{{ backgroundData.part1Section2Content }}</p>
              <p v-else>暂无内容</p>
            </div>
            <div class="image-area">
              <img
                v-if="backgroundData.part1Section2Image"
                :src="backgroundData.part1Section2Image"
                alt="图2"
              />
              <div v-else class="placeholder-image">暂无图片</div>
            </div>
          </div>
          <div class="divider"></div>

          <div class="block-flex">
            <div class="text-area">
              <h3 v-if="backgroundData.part1Section3Title" class="multiline-text">{{ backgroundData.part1Section3Title }}</h3>
              <h3 v-else>暂无标题</h3>
              <p
                v-if="backgroundData.part1Section3Content"
                class="multiline-text"
              >{{ backgroundData.part1Section3Content }}</p>
              <p v-else>暂无内容</p>
            </div>
            <div class="image-area">
              <img
                v-if="backgroundData.part1Section3Image"
                :src="backgroundData.part1Section3Image"
                alt="图3"
              />
              <div v-else class="placeholder-image">暂无图片</div>
            </div>
          </div>
          <div class="divider"></div>
        </section>



        <!-- 第二部分 -->
        <section class="content-section combined-policy-section">
          <h2 v-if="backgroundData.part2MainTitle" class="multiline-text">{{ backgroundData.part2MainTitle }}</h2>
          <h2 v-else>暂无标题</h2>
          <div class="policy-section">
            <h3 v-if="backgroundData.part2SubTitle" class="multiline-text">{{ backgroundData.part2SubTitle }}</h3>
            <h3 v-else>暂无副标题</h3>
            <div v-if="backgroundData.part2Content" class="multiline-text">{{ backgroundData.part2Content }}</div>
            <div v-else>暂无内容</div>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

// 响应式数据 - 使用更通用的命名
const backgroundData = ref({
  part1MainTitle: null,
  part1Section1Title: null,
  part1Section1Content: null,
  part1Section1Image: null,
  part1Section2Title: null,
  part1Section2Content: null,
  part1Section2Image: null,
  part1Section3Title: null,
  part1Section3Content: null,
  part1Section3Image: null,
  part2MainTitle: null,
  part2SubTitle: null,
  part2Content: null,
})

// 获取背景数据
const fetchBackgroundData = async () => {
  try {
    const response = await axios.get('/api/expo/background/data')
    if (response.data.code === 200) {
      backgroundData.value = response.data.data
    } else {
      console.error('获取数据失败:', response.data.message)
    }
  } catch (error) {
    console.error('请求失败:', error)
  }
}

// 页面加载时获取数据
onMounted(() => {
  fetchBackgroundData()
})
</script>

<style scoped>
/* 原有样式保持不变 */
.expo-background {
  min-height: 100vh;
  background-color: #fff;
}

.page-header {
  background-color: #fff;
  color: #1c3365;
  padding: 20px 0;
}

/* 面包屑样式，左对齐并与 container 保持一致 */
.breadcrumb {
  display: flex;
  align-items: center;
  font-size: 20px;
  font-weight: 900 !important;
  color: #0167a5;
  margin-bottom: 24px;
  padding-left: var(--spacing-lg); /* 与 container 左内边距一致 */
  padding-right: var(--spacing-lg);
}

/* 下方内容区域进一步内缩，留出更多页边距 */
.content-section {
  padding-left: 40px;
  padding-right: 40px;
}

.breadcrumb a,
.text-link,
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

.background-content {
  padding: 20px 0; /* 减少顶部内边距 */
}

section {
  margin-bottom: 60px;
}

section h2 {
  font-size: 2rem;
  font-weight: 600;
  margin-bottom: 1rem;
  color: #0167a5;
  text-align: left;
}

.content-block {
  background: none;
  box-shadow: none;
  border-radius: 0;
}

.content-section h3 {
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 16px;
  color: #0167a5;
}

.block-flex {
  display: flex;
  gap: 24px;
  align-items: center;
}

.block-flex.reverse-layout {
  flex-direction: row-reverse;
}

.text-area {
  flex: 1;
}

.image-area {
  flex: 0 0 300px;
}

.image-area img {
  max-width: 100%;
  border-radius: 12px;
}

/* 占位图片样式 */
.placeholder-image {
  width: 100%;
  height: 200px;
  background-color: #f5f5f5;
  border: 2px dashed #ddd;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #999;
  font-size: 14px;
}

.policy-section {
  background: none;
  padding: 32px 0;
  border-radius: 0;
  text-align: center;
}

.policy-section h3 {
  margin-top: 0px;
  text-align: center;
  line-height: 1;
  font-weight: 400; /* 更细的字体 */
  font-size: 1.3rem; /* 可选：稍微调小字号 */
}

.policy-section div {
  text-align: left; /* 内容div左对齐 */
}

.divider {
  height: 2px;
  background-color: #b5c7ea;
  margin: 10px 0;
}

@media (max-width: 768px) {
  .block-flex {
    flex-direction: column;
  }

  .block-flex.reverse-layout {
    flex-direction: column;
  }

  .text-area {
    text-align: center;
  }

  .image-area {
    margin-bottom: 20px;
  }
}

.combined-section {
  margin-bottom: 60px;
}

/* 调整第一部分和第二部分之间的间距 */
.combined-policy-section {
  margin-top: 80px; /* 增加顶部间距 */
}

.combined-policy-section h2 {
  margin-bottom: 0px;
  font-size: 2rem;
  line-height: 1;
  text-align: center;
}

/* 替换之前的 white-space: pre-line 样式 */
.multiline-text {
  white-space: pre-line; /* 保留换行符 */
}
</style>
