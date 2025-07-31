<template>
  <div class="join-us-page">
    <!-- 顶部 Banner -->
    <div class="banner">
      <img :src="bannerImage" alt="banner" />
      <h2 class="banner-title">与世博全球青年一起<br />共创可持续发展未来</h2>
    </div>

    <!-- 项目岗位信息（滑块） -->
    <h2 class="section-title">项目岗位信息</h2>
    <div class="carousel-wrapper" v-if="projects.length">
      <el-carousel
        ref="projectCarousel"
        :interval="5000"
        arrow="always"
        indicator-position=""
        @change="handleCarouselChange('project', $event)"
      >
        <el-carousel-item v-for="(item, index) in projects" :key="item.id">
          <div class="project-card">
            <h3 class="project-title">{{ item.title }}</h3>
            <p>{{ item.description }}</p>
            <el-button class="project-btn" type="primary" @click="goToLink(item.linkUrl)">
              报名通道
            </el-button>
          </div>
        </el-carousel-item>
      </el-carousel>

      <!-- 圆点指示器 -->
      <div class="indicators">
        <span
          v-for="(item, index) in projects"
          :key="index"
          :class="{ active: activeIndex === index }"
          @click="setActive(index, 'projectCarousel')"
        ></span>
      </div>
    </div>

    <!-- 入选名单 -->
    <h2 class="section-title">入选名单</h2>
    <div v-for="(cat, idx) in categories" :key="idx" class="member-block">
      <div class="category-title">{{ cat.categoryTitle }}</div>

      <!-- 食品奖项模块（滑块） -->
      <div v-if="cat.type === 'slider'" class="carousel-wrapper">
        <el-carousel
          ref="foodCarousel"
          :interval="5000"
          arrow="always"
          indicator-position=""
          @change="handleCarouselChange('food', $event)"
        >
          <el-carousel-item v-for="(item, i) in cat.items" :key="item.id">
            <div class="project-card">
              <h3 class="food-title">{{ item.title }}</h3>
              <img :src="item.imageUrl" alt="" class="food-img" />
            </div>
          </el-carousel-item>
        </el-carousel>

        <!-- 圆点指示器 -->
        <div class="indicators food-indicators">
          <span
            v-for="(item, index) in cat.items"
            :key="index"
            :class="{ active: foodActiveIndex === index }"
            @click="setActive(index, 'foodCarousel')"
          ></span>
        </div>
      </div>

      <!-- 非滑块的图片 -->
      <img v-else :src="cat.items[0].imageUrl" alt="名单图片" class="member-img small-img" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const bannerImage = ref('') 

const projects = ref([])
const categories = ref([])

/* 圆点索引 */
const activeIndex = ref(0)
const foodActiveIndex = ref(0)
const projectCarousel = ref(null)
const foodCarousel = ref(null)

const setActive = (index, type) => {
  if (type === 'foodCarousel') {
    foodActiveIndex.value = index
    foodCarousel.value.setActiveItem(index)
  } else {
    activeIndex.value = index
    projectCarousel.value.setActiveItem(index)
  }
}

const handleCarouselChange = (type, index) => {
  if (type === 'food') {
    foodActiveIndex.value = index
  } else {
    activeIndex.value = index
  }
}

const goToLink = (link) => {
  if (link) {
    window.open(link, '_blank')
  }
}

onMounted(async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/joinus/list')
    const data = res.data

    bannerImage.value = data.find(item => item.category === 'banner')?.imageUrl 

    // 1. 项目岗位信息（category = project）
    projects.value = data.filter(item => item.category === 'project')

    // 2. 入选名单（category = member） => 每条直接显示为一个小模块
    categories.value = data
      .filter(item => item.category === 'member')
      .map(item => ({
        categoryTitle: item.title,
        type: 'image',
        items: [item] 
      }))

    // 3. 食品奖项（category = food_award）
    const foodAwardItems = data.filter(item => item.category === 'food_award')
    if (foodAwardItems.length) {
      categories.value.push({
        categoryTitle: '食品奖项',
        type: 'slider',
        items: foodAwardItems
      })
    }
  } catch (error) {
    console.error('加载数据失败', error)
  }
})

</script>

<style scoped>
.join-us-page {
  padding: 20px;
}
.banner {
  position: relative;
}
.banner img {
  width: 100%;
}
.banner-title {
  position: absolute;
  bottom: 80px;
  left: 20px;
  color: white;
  font-size: 32px;
  font-weight: bold;
}
.section-title {
  text-align: left;
  margin: 30px 0 10px;
  font-size: 22px;
  color: #003366;
  font-weight: bold;
}


.carousel-wrapper {
  position: relative;
  width: 80%;
  margin: 0 auto;
}

.project-card {
  background: #f8faff;
  border-radius: 10px;
  padding: 15px;
  width: 85%;
  margin: 0 auto;
  text-align: center;
  position: relative;    
  height: auto;
}

.food-title {
  font-weight: bold;
  font-size: 16px;
  margin-bottom: 15px;
}

.food-img {
  width: 100%;
  height: auto;
  max-height: none;    
  object-fit: contain; 
  border-radius: 8px;
}

.project-btn {
  position: static;
  margin-top: 30px;  
}

.project-title {
  font-weight: bold;
  font-size: 18px;
  margin-bottom: 10px;
}

.member-img {
  width: 100%;
  height: auto;
  border-radius: 8px;
}

.small-img {
  width: 70%;
  height: auto;
  display: block;
  margin: 0 auto;
  border-radius: 8px;
}

.indicators {
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
}

.food-indicators {
  bottom: -10px;
}

.indicators span {
  display: inline-block;
  width: 8px;
  height: 8px;
  background: #ccc;
  border-radius: 50%;
  margin: 0 5px;
  cursor: pointer;
}
.indicators span.active {
  background: #409eff;
}

.member-block {
  text-align: center;
  margin: 30px 0;
}
.category-title {
  display: inline-block;
  background: #409eff;
  color: #fff;
  padding: 5px 15px;
  border-radius: 20px;
  font-size: 16px;
  margin-bottom: 10px;
  text-align: center;
}

:deep(.el-carousel__indicators) {
  display: none !important;
}



</style>
