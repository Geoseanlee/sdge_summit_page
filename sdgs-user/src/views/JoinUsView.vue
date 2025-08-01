<template>
  <div class="join-us-page">
    <!-- 顶部 Banner -->
    <div class="banner section-container">
      <img :src="bannerImage" alt="banner" />
      <h2 class="banner-title">
        与世博全球青年一起<br />共创可持续发展未来
      </h2>
    </div>

    <!-- 项目岗位信息 -->
    <div class="section-container">
      <h2 class="section-title">{{ projectTitle }}</h2>
      <div class="carousel-wrapper" v-if="projects.length">
        <el-carousel
          ref="projectCarousel"
          :interval="5000"
          arrow="always"
          indicator-position="none"
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

        <!-- 自定义圆点 -->
        <div class="indicators project-indicators">
          <span
            v-for="(item, index) in projects"
            :key="index"
            :class="{ active: activeIndex === index }"
            @click="setActive(index, 'projectCarousel')"
          ></span>
        </div>
      </div>
    </div>

    <!-- 入选名单 -->
    <div class="section-container">
      <h2 class="section-title">{{ memberTitle }}</h2>
      <div v-for="(cat, idx) in categories" :key="idx" class="member-block">
        <div class="category-title">{{ cat.categoryTitle }}</div>

        <!-- 食品奖项模块 -->
        <div v-if="cat.type === 'slider'" class="carousel-wrapper food-award-carousel">
          <el-carousel
            ref="foodCarousel"
            arrow="always"
            indicator-position="none"
            :autoplay="false"
            :height="undefined"
            @change="handleCarouselChange('food', $event)"
          >
            <el-carousel-item v-for="(item, i) in cat.items" :key="item.id">
              <div class="project-card">
                <h3 class="food-title">{{ item.title }}</h3>
                <img :src="item.imageUrl" alt="" class="food-img" />
              </div>
            </el-carousel-item>
          </el-carousel>

          <!-- 小圆点 -->
          <div class="indicators food-indicators">
            <span
              v-for="(item, index) in cat.items"
              :key="index"
              :class="{ active: foodActiveIndex === index }"
              @click="setActive(index, 'foodCarousel')"
            ></span>
          </div>
        </div>

        <!-- 普通图片 -->
        <img v-else :src="cat.items[0].imageUrl" alt="名单图片" class="member-img small-img" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const bannerImage = ref('')
const projectTitle = ref('项目岗位信息')
const memberTitle = ref('入选名单')
const projects = ref([])
const categories = ref([])

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
  if (link) window.open(link, '_blank')
}

onMounted(async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/joinus/list')
    const data = res.data

    // Banner
    bannerImage.value = data.find(item => item.category === 'banner')?.imageUrl

    // 动态标题
    projectTitle.value = data.find(item => item.category === 'projectTitle')?.title || '项目岗位信息'
    memberTitle.value = data.find(item => item.category === 'memberTitle')?.title || '入选名单'

    // 项目岗位信息
    projects.value = data.filter(item => item.category === 'project')

    // 入选名单 + 食品奖项
    categories.value = data
      .filter(item => item.category === 'member')
      .map(item => ({
        categoryTitle: item.title,
        type: 'image',
        items: [item]
      }))

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
  background: #fff;
}

.banner.section-container {
  max-width: 960px;
  margin: 2rem auto;
  text-align: center;
  position: relative;
}
.banner img {
  width: 100%;
  border-radius: 8px;
}
.banner-title {
  position: absolute;
  bottom: 30px;
  left: 80px;
  color: white;
  font-size: 2rem;
  font-weight: 700;
}

.section-container {
  max-width: 960px;
  margin: 0 auto 3.5rem auto;
  padding: 0 32px;
  box-sizing: border-box;
}

.section-title {
  font-size: 1.9rem;
  font-weight: bold;
  margin: 2rem 0 1.5rem;
  color: #1976d2;
  text-align: left;
}

.category-title {
  display: inline-block;
  background: #409eff;
  color: #fff;
  padding: 5px 15px;
  border-radius: 20px;
  font-size: 1rem;
  font-weight: bold;
  margin-bottom: 1.2rem;
}

.project-card {
  background: #f8faff;
  border-radius: 10px;
  padding: 15px;
  width: 85%;
  margin: 0 auto;
  text-align: center;
}
.project-card img,
.food-img,
.member-img {
  width: 85%;
  height: auto;
  display: block;
  margin: 0 auto;
  object-fit: contain;
  border-radius: 8px;
}

.project-title {
  font-weight: bold;
  font-size: 1.2rem;
  margin-bottom: 10px;
}
.food-title {
  font-weight: bold;
  font-size: 1rem;
  margin-bottom: 15px;
}

.project-btn {
  margin-top: 30px;
}


.indicators {
  text-align: center;
  margin-top: 6px;
}
.project-indicators {
  margin-top: -60px;
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

.food-award-carousel .el-carousel__container {
  height: auto !important;
  overflow: visible !important;
}
.food-award-carousel .el-carousel__item {
  height: auto !important;
  overflow: visible !important;
}

.member-block {
  text-align: center;
  margin: 30px 0;
}

@media (max-width: 768px) {
  .section-container,
  .banner.section-container {
    padding: 0 16px;
  }
  .section-title {
    font-size: 1.5rem;
    text-align: center;
  }
}
</style>
