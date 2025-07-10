<template>
  <div class="about">
    <!-- 加载骨架 -->
    <div v-if="loading" class="loading">加载中…</div>

    <!-- 真正内容 -->
    <template v-else>
      <!-- Hero / 头图 -->
      <section class="hero">
        <img class="hero-img" :src="about.heroImg" alt="Hero" />
        <div class="hero-overlay">

          <h1 class="hero-title">{{ about.headerTitle }}</h1>
          <div class="hero-tags">
            <div class="hero-tag" v-for="t in headerTags" :key="t">{{ t }}</div>
          </div>
          <a class="hero-btn" :href="about.heroBtnLink">{{ about.heroBtnText }}</a>
        </div>
      </section>

      <!-- 引言 -->
      <section class="intro">
        <p v-for="(para, i) in about.introHtml.split('\n').filter(p => p.trim())" :key="i">
          {{ para }}
        </p>
      </section>

      <!-- 三大优势 -->
      <section class="advantages section-container">
        <h2 class="sec-title">三大优势</h2>

        <div class="adv-grid">
          <article class="adv-card" v-for="(item, i) in advantages" :key="i">
            <img class="adv-img" :src="item.image" :alt="item.title" />
            <h3 class="adv-title">{{ item.title }}</h3>
            <p class="adv-desc" v-html="item.desc" />
          </article>
        </div>
      </section>

      <!-- 统计四宫格 -->
      <section class="stats section-container">
        <div
          class="stat-box"
          v-for="(s, i) in stats"
          :key="i"
          :style="{ background: s.color }"
        >
          <h3 v-html="s.title"></h3>
          <p v-html="s.desc"></p>
        </div>
      </section>

      <!-- 伙伴 LOGO 示例（选做，可继续拆成组件） -->
      <section class="logo-wall section-container">
        <h2 class="logo-wall-title">部分平台及媒体资源</h2>
        <img class="logo-wall-img" :src="about.logoWallImg" alt="logo墙" v-if="about.logoWallImg" />
      </section>
      <section class="logo-wall section-container">
        <h2 class="logo-wall-title">特别合作伙伴</h2>
        <img class="logo-wall-img" :src="about.specialLogoWallImg" alt="logo墙" v-if="about.specialLogoWallImg" />
      </section>
      <section class="logo-wall section-container">
        <h2 class="logo-wall-title">合作伙伴</h2>
        <img class="logo-wall-img" :src="about.partnerLogoWallImg" alt="logo墙" v-if="about.partnerLogoWallImg" />
      </section>
      <!--
      <LogoWall title="特别合作伙伴" :list="specialList" />
      <LogoWall title="合作伙伴" :list="partnerList" />
      -->
    </template>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'
import bgImage from '@/assets/images/about-bg.jpg'

/** ========= 类型定义（可根据后端字段微调） ========= */
interface Advantage {
  title: string
  image: string
  desc: string
}
interface Stat {
  title: string
  color: string
  desc: string
}

/** ========= 状态 ========= */
const loading = ref(true)
const about   = ref<any | null>(null)

/** ========= 派生数据 ========= */
const headerTags = computed<string[]>(() =>
  about.value ? about.value.headerTags.split(',') : [])

const advantages = computed<Advantage[]>(() =>
  about.value ? JSON.parse(about.value.advantagesJson) : [])

const stats = computed<Stat[]>(() =>
  about.value ? JSON.parse(about.value.statsJson) : [])

// logo 列表示例（若要渲染 Logo 墙）
const mediaList   = computed<string[]>(() =>
  about.value ? JSON.parse(about.value.mediaJson)   : [])

const specialList = computed<string[]>(() =>
  about.value ? JSON.parse(about.value.specialJson) : [])

const partnerList = computed<string[]>(() =>
  about.value ? JSON.parse(about.value.partnerJson) : [])

/** ========= 拉取数据 ========= */
onMounted(async () => {
  try {
    const { data } = await axios.get('/api/public/about-overview')
    about.value = data.data
    console.log('API 返回数据:', about.value)
    console.log('headerTags:', headerTags.value)
    console.log('advantages:', advantages.value)
  } catch (error) {
    console.error('API 请求失败:', error)
  } finally {
    loading.value = false
  }
})
</script>

<style scoped>
/* ===== 布局基础 ===== */
.about {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Helvetica Neue',
    Helvetica, Arial, 'Segoe UI', sans-serif;
}
.loading {
  min-height: 60vh;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
}

/* ===== Hero ===== */
.hero {
  position: relative;
  height: 420px;
  overflow: hidden;
  background: linear-gradient(180deg, #e3f0fc 0%, #fff 100%);
}
.hero-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  filter: brightness(0.7);
  position: absolute;
  left: 0; top: 0; z-index: 1;
}
.hero-overlay {
  position: relative;
  z-index: 2;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
  color: #222;
  text-align: center;
  background: linear-gradient(180deg,rgba(255,255,255,0.7) 0%,rgba(255,255,255,0.2) 100%);
}

.hero-title {
  font-size: 2.4rem;
  font-weight: 700;
  margin-bottom: 1rem;
  color: #2176c7;
}
.hero-tags {
  display: flex;
  gap: 2.5rem; /* tag 之间的间距可根据设计稿调整 */
  justify-content: center;
  margin-bottom: 1.5rem;
  flex-wrap: wrap;
}
.hero-tag {
  font-size: 1.18rem;
  color: #2176c7; /* 取设计稿主色或 #2563eb、#2176c7 等 */
  font-weight: 500;
  background: none;
  border-radius: 0;
  padding: 0;
  /* 无需 border、background、box-shadow */
}
.hero-btn {
  padding: 0.6rem 2.2rem;
  background: #689bdd;
  border-radius: 10px;
  font-weight: 600;
  color: #ffffff;
  font-size: 1.1rem;
  box-shadow: 0 2px 8px rgba(255,177,0,0.15);
  transition: background 0.2s;
}
.hero-btn:hover {
  background: #ffd966;
}

/* ===== 引言 ===== */
.intro {
  max-width: 950px;
  margin: 3rem auto;
  line-height: 1.8;
  font-size: 1.05rem;
  color: #444;
  padding: 0 1rem;
}

/* ===== 三大优势 ===== */
.advantages {
  /* max-width: 960px;   /* 或 1000px，按设计稿调整 */
  /* margin: 0 auto 4rem auto; */
  /* padding: 0 32px;     /* 两侧留点内边距，防止超大屏幕贴边 */
}
.sec-title {
  text-align: left;
  font-size: 2rem;
  font-weight: 700;
  margin-bottom: 2rem;
  color: #1b73a2;
  /* 不需要再加 margin-left */
}
.adv-grid {
  display: flex;
  gap: 2.5rem; /* 或你喜欢的间距 */
  justify-content: flex-start;
}
.adv-card {
  background: #fff;
  border-radius: 24px;
  /* 去掉阴影和边框 */
  box-shadow: none;
  border: none;
  overflow: hidden;
  width: 340px; /* 可根据设计稿调整 */
  margin: 0; /* 去掉左右 margin */
  display: flex;
  flex-direction: column;
  align-items: stretch;

}
.adv-card:hover {
  transform: translateY(-6px) scale(1.03);
}
.adv-img {
  width: 100%;
  height: 180px;         /* 可根据设计稿调整 */
  object-fit: cover;
  border-radius: 24px;   /* 四角圆角 */
  display: block;
}
.adv-title {
  font-size: 1.2rem;
  font-weight: 700;
  margin: 1.2rem 0 0.5rem 0; /* 只保留上下间距 */
  text-align: left;
  color: #222;
}
.adv-desc {
  margin: 0 0 1.5rem 0;      /* 只保留下间距 */
  color: #222;
  font-size: 0.8rem;
  line-height: 1.7;
  text-align: left;
}

/* ===== 统计四宫格 ===== */
.stats {
  display: flex;
  gap: 1.5rem;
  justify-content: center;
  margin: 0 auto 4rem;
  /* max-width/padding 由 .section-container 控制 */
}
.stat-box {
  flex: 1;
  padding: 2rem 1.5rem;
  border-radius: 14px;
  color: #fff;
  text-align: left;         /* 让内容左对齐 */
  line-height: 1.6;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  font-size: 1.05rem;
  min-height: 300px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;  /* 让内容靠左 */
  justify-content: flex-start; /* 让内容靠上 */
}
.stat-box h3 {
  font-size: 1.5rem;          /* 更大标题 */
  font-weight: 700;
  margin-bottom: 1rem;
  margin-top: 0;
  line-height: 1.2;
}
.stat-box p {
  font-size: 0.8rem;          /* 内容小一点 */
  margin: 0;
  line-height: 1.7;
}

/* ===== Logo Wall ===== */
.logo-wall {
  margin: 3rem 0;
  text-align: center;
  /* padding: 0 32px;    /* 两侧留白，防止超大屏幕贴边 */
  /* box-sizing: border-box; */
}
.logo-wall-title {
  font-size: 1.8rem;
  margin-bottom: 1.5rem;
  color: #18346f;
  font-weight: 600;
}
.logo-wall-img {
  display: block;
  max-width: 900px;   /* 或 100%, 也可以用 80vw 等响应式单位 */
  width: 100%;
  height: auto;
  margin: 0 auto;
  border-radius: 24px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.06);
}

/* 响应式微调 */
@media (max-width: 640px) {
  .hero-title { font-size: 1.8rem }
  .adv-img    { height: 140px }
}

.section-container {
  max-width: 960px;
  margin: 0 auto 3.5rem auto; /* 下外边距加大，原来可能是0~2rem */
  padding: 0 32px;
  box-sizing: border-box;
}

.advantages.section-container {
  margin-bottom: 4rem;
}
.stats.section-container {
  margin-bottom: 4rem;
}
.logo-wall.section-container {
  margin-bottom: 4rem;
}
.logo-wall.section-container:last-child {
  margin-bottom: 5rem;
}
</style>
