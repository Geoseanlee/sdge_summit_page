<template>
  <div class="home">
    <div class="hero-section">
      <div class="hero-content">
        <div class="hero-item">
          <h1 class="main-title blue-title">走进世界</h1>
          <p class="subtitle">与世博全球青年一起</p>
        </div>
        <div class="hero-item">
          <h1 class="main-title blue-title">走进世博</h1>
          <p class="subtitle">共创可持续发展未来</p>
        </div>
      </div>
    </div>

    <!-- 添加图片区域 -->
    <div class="image-section">
      <div class="hero-image">
        <img src="/expo_logo.png" alt="展示图片" class="hero-img" />
      </div>
    </div>

    <!-- SDGs介绍区域 -->
    <div class="sdgs-section">
      <div class="sdgs-content">
        <div class="sdgs-text">
          <p class="sdgs-description">
            联合国可持续发展目标（Sustainable Development
            Goals，SDGs）是联合国为应对全球挑战而制定的一系列目标，旨在从2015年到2030年间以综合方式彻底解决社会、经济和环境三个维度的发展问题，转向可持续发展道路。
          </p>
          <p class="sdgs-description">
            这些目标旨在推动全球发展，实现更美好、更可持续的未来，涵盖了贫困、饥饿、健康、教育、性别平等、水资源、能源、经济增长、基础设施、不平等、城市发展、消费模式、气候变化、海洋与陆地生态系统、和平与包容性社会等多个领域。
          </p>
        </div>
        <div class="sdgs-logo">
          <h2 class="sdgs-title">SDGs</h2>
          <button class="more-info-btn" @click="goToBackground">
            了解更多信息 <span class="arrow">›</span>
          </button>
        </div>
      </div>
    </div>

    <!-- 世博会介绍区域 -->
    <div class="expo-intro-section">
      <div class="expo-intro-container">
        <div class="expo-text-area">
          <h2 class="expo-title">2025大阪世博会情况及介绍</h2>
          <p class="expo-subtitle">"闪耀生命的未来社会的设计"</p>
          <button class="explore-btn" @click="goToExpo">
            探索 <span class="arrow">›</span>
          </button>
        </div>
        <div class="expo-image">
          <img
            src="https://images.unsplash.com/photo-1540959733332-eab4deabeeaf?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1200&q=80"
            alt="世博会场景" class="expo-bg-img" />
        </div>
      </div>
    </div>

    <!-- 世博全球青年联盟介绍区域 -->
    <div class="alliance-section">
      <div class="alliance-content">
        <h2 class="alliance-title">世博全球青年联盟</h2>
        <div class="alliance-description">
          <p class="alliance-text">
            世博全球青年联盟（World Expo Global Young Alliance）在迪拜世博会期间，由世博全球青年SDGs峰会组委会与SDGsVision
            2050发起，在迪拜世博会中华文化馆与中华文化促进会支持下成立，为激发全球青年为实现可持续发展目标SDGs（也称为全球目标和2030年议程）做出贡献的行动。
          </p>
          <p class="alliance-text">
            世博全球青年联盟将连接全球的创意机构和人才，汇聚全球产业界、政界、学界、商界力量助力落实《联合国2030年可持续发展议程》，促进社会进步、环境保护、经济建设可持续发展。
          </p>
        </div>
      </div>
    </div>

    <!-- 相关数据区域 -->
    <div class="data-section">
      <div class="data-content">
        <h2 class="data-title">相关行动数据</h2>
        <div class="data-image">
          <img
            src="https://images.unsplash.com/photo-1551288049-bebda4e38f71?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1200&q=80"
            alt="数据图表" class="data-img" />
        </div>
      </div>
    </div>

    <!-- 加入我们区域 -->
    <div class="join-us-section">
      <div class="join-us-content">
        <div class="join-us-text">
          <h2 class="join-us-title">加入我们</h2>
          <ul class="join-us-list">
            <li class="join-us-item">要成为世博全球青年联盟的成员，关注公众号SDGs Vision 2050</li>
            <li class="join-us-item">携手全球产业界、政界、学界、商界力量</li>
            <li class="join-us-item">邀您共同探讨与行动！</li>
          </ul>
          <button class="join-info-btn" @click="goToJoinUs">
            了解更多信息 <span class="arrow">›</span>
          </button>
        </div>
        <div class="join-us-image">
          <img
            src="https://images.unsplash.com/photo-1515187029135-18ee286d815b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1200&q=80"
            alt="加入我们" class="join-us-img" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

// 跳转到背景页面
const goToBackground = () => {
  router.push('/expo/background')
}

// 跳转到世博会页面
const goToExpo = () => {
  router.push('/expo')
}

// 跳转到加入我们页面
const goToJoinUs = () => {
  router.push('/join-us')
}
</script>

<style scoped>
.home {
  padding: 0;
  min-height: 100vh;
  background: #f8f9fa;
}

.hero-section {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  min-height: auto;
  padding: 20px 20px;
}

.hero-content {
  display: flex;
  gap: 0px;
  max-width: 600px;
  width: 100%;
  justify-content: center;
  align-items: center;
}

.hero-item {
  text-align: center;
  flex: 1;
}

.main-title {
  font-size: 3.2rem;
  font-weight: bold;
  margin: 0 0 20px 0;
  line-height: 1.2;
}

.blue-title {
  color: #0e82e8;
}

.subtitle {
  font-size: 1.5rem;
  margin: 0;
  color: #0e82e8;
  font-weight: 500;
}

/* 图片区域样式 */
.image-section {
  max-width: 1400px;
  margin: 0 auto 0;
  padding: 0 80px;
}

.hero-image {
  margin-bottom: 40px;
  border-radius: 20px;
  overflow: hidden;
}

.hero-img {
  width: 100%;
  height: auto;
  object-fit: cover;
  display: block;
  border-radius: 20px;
}

/* SDGs介绍区域样式 */
.sdgs-section {
  max-width: 1200px;
  margin: 60px auto 0;
  padding: 0 120px;
}

.sdgs-content {
  display: flex;
  align-items: center;
  gap: 60px;
}

.sdgs-text {
  flex: 1;
}

.sdgs-description {
  font-size: 16px;
  line-height: 1.8;
  color: #333;
  margin-bottom: 20px;
  text-align: justify;
  letter-spacing: 0.5px;
}

.sdgs-description:last-child {
  margin-bottom: 0;
}

.sdgs-logo {
  flex: 0 0 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.sdgs-title {
  font-size: 4rem;
  font-weight: bold;
  color: #0e82e8;
  margin: 0 0 30px 0;
  letter-spacing: 2px;
}

.more-info-btn {
  background: #0e82e8;
  color: white;
  border: none;
  padding: 12px 24px;
  border-radius: 25px;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
  transition: all 0.3s ease;
  text-decoration: none;
}

.more-info-btn:hover {
  background: #0d74d1;
  transform: translateY(-2px);
}

.more-info-btn .arrow {
  font-size: 16px;
  transition: transform 0.3s ease;
}

.more-info-btn:hover .arrow {
  transform: translateX(3px);
}

/* 世博会介绍区域样式 */
.expo-intro-section {
  max-width: 1300px;
  margin: 80px auto 40px;
  padding: 0 100px;
}

.expo-intro-container {
  position: relative;
  border-radius: 20px;
  overflow: hidden;
  min-height: 400px;
  display: flex;
  align-items: center;
}

.expo-image {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 1;
}

.expo-bg-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 20px;
}

.expo-text-area {
  position: relative;
  z-index: 2;
  padding: 60px;
  color: white;
  max-width: 600px;
}

.expo-title {
  font-size: 2.5rem;
  font-weight: bold;
  margin: 0 0 20px 0;
  line-height: 1.2;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

.expo-subtitle {
  font-size: 1.2rem;
  margin: 0 0 40px 0;
  line-height: 1.5;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
}

.explore-btn {
  background: #4A90E2;
  color: white;
  border: none;
  padding: 15px 30px;
  border-radius: 30px;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 10px;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(74, 144, 226, 0.3);
}

.explore-btn:hover {
  background: #357ABD;
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(74, 144, 226, 0.4);
}

.explore-btn .arrow {
  font-size: 18px;
  transition: transform 0.3s ease;
}

.explore-btn:hover .arrow {
  transform: translateX(3px);
}

/* 世博全球青年联盟介绍区域样式 */
.alliance-section {
  max-width: 1200px;
  margin: 80px auto 60px;
  padding: 0 120px;
}

.alliance-content {
  text-align: left;
}

.alliance-title {
  font-size: 2.5rem;
  font-weight: bold;
  color: #0e82e8;
  margin: 0 0 40px 0;
  line-height: 1.2;
  letter-spacing: 1px;
}

.alliance-description {
  line-height: 1.8;
}

.alliance-text {
  font-size: 16px;
  line-height: 1.8;
  color: #333;
  margin-bottom: 25px;
  text-align: justify;
  letter-spacing: 0.5px;
}

.alliance-text:last-child {
  margin-bottom: 0;
}

/* 相关数据区域样式 */
.data-section {
  max-width: 1200px;
  margin: 80px auto 60px;
  padding: 0 120px;
}

.data-content {
  text-align: left;
}

.data-title {
  font-size: 2.5rem;
  font-weight: bold;
  color: #0e82e8;
  margin: 0 0 40px 0;
  line-height: 1.2;
  letter-spacing: 1px;
}

.data-image {
  border-radius: 20px;
  overflow: hidden;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
}

.data-img {
  width: 100%;
  height: auto;
  object-fit: cover;
  display: block;
  border-radius: 20px;
}

/* 加入我们区域样式 */
.join-us-section {
  max-width: 1200px;
  margin: 80px auto 60px;
  padding: 0 120px;
}

.join-us-content {
  display: flex;
  align-items: center;
  gap: 60px;
}

.join-us-text {
  flex: 1;
}

.join-us-title {
  font-size: 2.5rem;
  font-weight: bold;
  color: #0e82e8;
  margin: 0 0 30px 0;
  line-height: 1.2;
  letter-spacing: 1px;
}

.join-us-list {
  list-style: none;
  padding: 0;
  margin: 0 0 40px 0;
}

.join-us-item {
  font-size: 16px;
  line-height: 1.8;
  color: #333;
  margin-bottom: 15px;
  position: relative;
  padding-left: 20px;
  letter-spacing: 0.5px;
}

.join-us-item:before {
  content: '•';
  color: #0e82e8;
  font-size: 20px;
  position: absolute;
  left: 0;
  top: -2px;
}

.join-us-item:last-child {
  margin-bottom: 0;
}

.join-info-btn {
  background: #0e82e8;
  color: white;
  border: none;
  padding: 15px 30px;
  border-radius: 30px;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 10px;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(14, 130, 232, 0.3);
}

.join-info-btn:hover {
  background: #0d74d1;
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(14, 130, 232, 0.4);
}

.join-info-btn .arrow {
  font-size: 18px;
  transition: transform 0.3s ease;
}

.join-info-btn:hover .arrow {
  transform: translateX(3px);
}

.join-us-image {
  flex: 0 0 400px;
  border-radius: 20px;
  overflow: hidden;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
}

.join-us-img {
  width: 100%;
  height: 300px;
  object-fit: cover;
  display: block;
  border-radius: 20px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .hero-content {
    flex-direction: column;
    gap: 15px;
  }

  .main-title {
    font-size: 2.4rem;
  }

  .subtitle {
    font-size: 1.2rem;
  }

  .image-section {
    padding: 0 40px;
  }

  .sdgs-section {
    padding: 0 60px;
    margin: 40px auto 0;
  }

  .sdgs-content {
    flex-direction: column;
    gap: 30px;
  }

  .sdgs-logo {
    flex: none;
  }

  .sdgs-title {
    font-size: 3rem;
  }

  .sdgs-description {
    font-size: 15px;
  }

  .expo-intro-section {
    padding: 0 50px;
    margin: 60px auto 30px;
  }

  .expo-text-area {
    padding: 40px;
  }

  .expo-title {
    font-size: 2rem;
  }

  .expo-subtitle {
    font-size: 1.1rem;
    margin: 0 0 30px 0;
  }

  .explore-btn {
    font-size: 15px;
    padding: 12px 25px;
  }

  .alliance-section {
    padding: 0 60px;
    margin: 60px auto 40px;
  }

  .alliance-title {
    font-size: 2rem;
    margin: 0 0 30px 0;
  }

  .alliance-text {
    font-size: 15px;
    margin-bottom: 20px;
  }

  .data-section {
    padding: 0 60px;
    margin: 60px auto 40px;
  }

  .data-title {
    font-size: 2rem;
    margin: 0 0 30px 0;
  }

  .join-us-section {
    padding: 0 60px;
    margin: 60px auto 40px;
  }

  .join-us-content {
    flex-direction: column;
    gap: 40px;
  }

  .join-us-title {
    font-size: 2rem;
    margin: 0 0 25px 0;
  }

  .join-us-item {
    font-size: 15px;
    margin-bottom: 12px;
  }

  .join-info-btn {
    font-size: 15px;
    padding: 12px 25px;
  }

  .join-us-image {
    flex: none;
    width: 100%;
  }

  .join-us-img {
    height: 250px;
  }
}

@media (max-width: 480px) {
  .main-title {
    font-size: 2rem;
  }

  .subtitle {
    font-size: 1rem;
  }

  .hero-section {
    padding: 15px 15px;
  }

  .image-section {
    padding: 0 25px;
  }

  .sdgs-section {
    padding: 0 40px;
    margin: 30px auto 0;
  }

  .sdgs-content {
    gap: 20px;
  }

  .sdgs-title {
    font-size: 2.5rem;
    margin: 0 0 20px 0;
  }

  .sdgs-description {
    font-size: 14px;
  }

  .more-info-btn {
    font-size: 13px;
    padding: 10px 20px;
  }

  .expo-intro-section {
    padding: 0 40px;
    margin: 40px auto 20px;
  }

  .expo-intro-container {
    min-height: 300px;
  }

  .expo-text-area {
    padding: 30px;
  }

  .expo-title {
    font-size: 1.8rem;
  }

  .expo-subtitle {
    font-size: 1rem;
    margin: 0 0 25px 0;
  }

  .explore-btn {
    font-size: 14px;
    padding: 10px 20px;
  }

  .alliance-section {
    padding: 0 40px;
    margin: 40px auto 30px;
  }

  .alliance-title {
    font-size: 1.8rem;
    margin: 0 0 25px 0;
  }

  .alliance-text {
    font-size: 14px;
    margin-bottom: 18px;
  }

  .data-section {
    padding: 0 40px;
    margin: 40px auto 30px;
  }

  .data-title {
    font-size: 1.8rem;
    margin: 0 0 25px 0;
  }

  .join-us-section {
    padding: 0 20px;
    margin: 40px auto 30px;
  }

  .join-us-title {
    font-size: 1.5rem;
    margin: 0 0 20px 0;
  }

  .join-us-item {
    font-size: 14px;
    margin-bottom: 10px;
  }

  .join-info-btn {
    font-size: 14px;
    padding: 10px 20px;
  }

  .join-us-img {
    height: 200px;
  }
}
</style>
