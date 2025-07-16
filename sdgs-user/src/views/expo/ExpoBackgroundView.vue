<template>
  <div class="expo-background">
    <!-- 页面头部 -->
    <div class="page-header">
      <div class="container">
        <nav class="breadcrumb">
          <router-link to="/" class="icon-link">
            <img src="@/assets/home-icon.png" alt="主页" class="home-icon" /> <!-- 使用本地图片 -->
          </router-link>
          <img src="@/assets/arrow-icon.png" alt="分隔符" class="separator" />
          <router-link to="/expo" class="breadcrumb-link">世博会</router-link> <!-- 加粗“世博会” -->
          <img src="@/assets/arrow-icon.png" alt="分隔符" class="separator" />
          <span class="current">背景</span> <!-- 加粗“背景” -->
        </nav>
      </div>
    </div>

    <!-- 主要内容 -->
    <div class="background-content">
      <div class="container">

        <!-- 第一部分到第四部分合并 -->
        <section class="content-section combined-section">
          <h2>以青年之力，搭建中国与世界的可持续桥梁</h2>
          <div class="divider"></div>

          <div class="block-flex">
            <div class="text-area">
              <h3>1. 联合国SDGs与未来契约：共绘人类可持续未来蓝图</h3>
              <p v-html="sdgsIntro"></p>
            </div>
            <div class="image-area">
              <img :src="sdgsImageUrl" alt="SDGs目标图" />
            </div>
          </div>
          <div class="divider"></div>

          <div class="block-flex reverse-layout">
            <div class="text-area">
              <h3>2. 世界博览会：World Expo 全球思想与创新的交汇舞台</h3>
              <p v-html="expoIntro"></p>
            </div>
            <div class="image-area">
              <img :src="expoImageUrl" alt="EXPO 2025 logo" />
            </div>
          </div>
          <div class="divider"></div>

          <div class="block-flex">
            <div class="text-area">
              <h3>3. 青年领袖：Youth Leaders 变革的核心力量，引领可持续未来</h3>
              <p v-html="youthIntro"></p>
            </div>
            <div class="image-area">
              <img :src="youthImageUrl" alt="青年协作图" />
            </div>
          </div>
        </section>

        <!-- 第五部分到第七部分合并 -->
        <section class="content-section combined-policy-section">
          <h2>国家政策倡议与国际合作</h2>
          <div class="policy-section">
            <h3>
              【知行合一，大国担当】<br />
              SDGs中国力量
            </h3>
            <div v-html="chinaPolicyHtml"></div>
          </div>
          <div class="content-block">
            <p>这里可以添加更多内容...</p>
          </div>
        </section>

      </div>
    </div>
  </div>
</template>

<script setup>
// 这里可以添加页面逻辑
</script>

<style scoped>
/* ===========================
   页面整体布局样式
   =========================== */
.expo-background {
  min-height: 100vh; /* 确保页面高度至少为视口高度 */
  background-color: #fff; /* 设置背景为纯白色 */
}

/* ===========================
   页面头部样式
   =========================== */
.page-header {
  background-color: #fff; /* 设置白色背景，突出头部区域 */
  color: #1c3365; /* 设置深蓝色文字，提升可读性 */
  padding: 20px 0; /* 添加上下内边距，增加头部的视觉空间 */
}

.breadcrumb {
  display: flex; /* 使用弹性布局，使导航项水平排列 */
  align-items: center; /* 垂直居中对齐导航项 */
  font-size: 20px; /* 设置导航文字大小 */
  font-weight: 900 !important; /* 强制加粗导航文字，提升层级感 */
  color: #0167a5; /* 设置蓝色文字，符合整体配色 */
  margin-bottom: 24px; /* 添加导航与下方内容的间距 */
}

.breadcrumb a,
.text-link,
.current {
  color: #0167a5; /* 设置链接文字颜色，与整体配色一致 */
  text-decoration: none; /* 去掉下划线，简化视觉效果 */
  font-weight: 700 !important; /* 强制加粗，突出导航项 */
}

.separator {
  width: 16px; /* 设置分隔符宽度 */
  height: 16px; /* 设置分隔符高度 */
  margin: 0 8px; /* 添加分隔符左右间距 */
  vertical-align: middle; /* 垂直居中对齐 */
  object-fit: contain; /* 保持图片比例，避免变形 */
}

.home-icon {
  width: 30px; /* 设置主页图标宽度 */
  height: 30px; /* 设置主页图标高度 */
  object-fit: contain; /* 保持图标比例 */
  vertical-align: middle; /* 垂直居中对齐 */
}

/* ===========================
   面包屑导航悬停样式调整
   =========================== */
.breadcrumb-link:hover {
  color: #014f7d; /* 设置悬停时更深的颜色 */
  text-decoration: underline; /* 添加下划线以增强交互反馈 */
}
/* ===========================
   Home 图标悬停样式调整
   =========================== */
.icon-link:hover .home-icon {
  filter: brightness(0.8); /* 悬停时降低亮度，提供交互反馈 */
  transform: scale(1.1); /* 悬停时放大图标，增强视觉效果 */
  transition: all 0.3s ease; /* 添加平滑过渡效果 */
}

/* ===========================
   主要内容样式
   =========================== */
.background-content {
  padding: 60px 0; /* 添加上下内边距，增加内容区域的视觉空间 */
}

section {
  margin-bottom: 60px; /* 每个部分之间添加间距，分隔内容 */
}

section h2 {
  font-size: 2rem; /* 设置标题字体大小，突出显示 */
  font-weight: 600; /* 设置字体加粗，强调标题 */
  margin-bottom: 1rem; /* 添加标题与下方内容的间距 */
  color: #0167a5; /* 设置标题颜色，与整体配色一致 */
  text-align: left; /* 左对齐标题，保持一致性 */
}

/* ===========================
   内容块样式
   =========================== */
.content-block {
  background: none; /* 移除背景色 */
  box-shadow: none; /* 移除阴影效果 */
  border-radius: 0; /* 移除圆角 */
}

.content-section h3 {
  font-size: 1.5rem; /* 设置标题字体大小，突出显示 */
  font-weight: 600; /* 设置字体加粗，强调标题 */
  margin-bottom: 16px; /* 添加标题与下方内容的间距 */
  color: #0167a5; /* 设置标题颜色，与整体配色一致 */
}

/* ===========================
   弹性布局样式
   =========================== */
.block-flex {
  display: flex; /* 使用弹性布局，使子元素水平排列 */
  gap: 24px; /* 设置子元素之间的间距 */
  align-items: center; /* 垂直居中对齐子元素 */
}

.block-flex.reverse-layout {
  flex-direction: row-reverse; /* 子元素反向排列，用于特定布局 */
}

.text-area {
  flex: 1; /* 占据剩余空间，确保文字区域自适应 */
}

.image-area {
  flex: 0 0 300px; /* 固定宽度，确保图片区域大小一致 */
}

.image-area img {
  max-width: 100%; /* 图片宽度自适应，避免超出容器 */
  border-radius: 12px; /* 设置圆角，提升视觉柔和度 */
}

/* ===========================
   政策部分样式
   =========================== */
.policy-section {
  background: none; /* 移除背景色 */
  padding: 32px; /* 保留内边距 */
  border-radius: 0; /* 移除圆角 */
  text-align: center; /* 内容居中 */
}

.policy-section h3 {
  font-size: 1.5rem; /* 设置标题字体大小，突出显示 */
  font-weight: 600; /* 设置字体加粗，强调标题 */
  margin-bottom: 16px; /* 添加标题与下方内容的间距 */
  color: #0167a5; /* 设置标题颜色，与整体配色一致 */
}

/* ===========================
   分割线样式
   =========================== */
.divider {
  height: 2px; /* 设置分割线高度 */
  background-color: #b5c7ea; /* 设置分割线颜色，与整体配色一致 */
  margin: 40px 0; /* 添加上下间距，分隔内容 */
}

/* ===========================
   响应式样式
   =========================== */
@media (max-width: 768px) {
  .block-flex {
    flex-direction: column; /* 子元素垂直排列，适配小屏幕 */
  }

  .block-flex.reverse-layout {
    flex-direction: column; /* 子元素垂直排列，适配小屏幕 */
  }

  .text-area {
    text-align: center; /* 窄屏模式下文字内容居中 */
  }

  .image-area {
    margin-bottom: 20px; /* 添加图片下方间距，避免内容重叠 */
  }
}

/* ===========================
   合并后的样式调整
   =========================== */
.combined-section {
  margin-bottom: 60px; /* 设置与下方内容的间距 */
}

.combined-policy-section {
  margin-top: 40px; /* 设置与上方内容的间距 */
}

.combined-policy-section h2 {
  margin-bottom: 0px; /* 缩短标题与下方内容的间距 */
  font-size: 2rem; /* 调整字体大小，减少视觉占用 */
  line-height: 1; /* 减小行高，进一步缩短间距 */
  text-align: center; /* 标题居中 */
}

.policy-section h3 {
  margin-top: 0px; /* 减少标题与上方内容的间距 */
  font-size: 1.5rem; /* 调整字体大小，减少视觉占用 */
  line-height: 1; /* 减小行高，进一步缩短间距 */
}


</style>
