<template>
  <div class="not-found-container">
    <div class="not-found-content">
      <h1>404</h1>
      <h2>页面未找到</h2>
      <p>抱歉，您要查找的页面不存在。它可能已被移动或删除。</p>
      <router-link to="/" class="back-home-link">返回首页</router-link>
    </div>
  </div>
</template>

<script setup>
// No script needed for this simple static page
</script>

<style scoped>
.not-found-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 60vh; /* Make sure it takes up a good portion of the viewport */
  text-align: center;
  padding: var(--spacing-lg);
  color: var(--color-text);
}

.not-found-content {
  max-width: 600px;
}

.not-found-content h1 {
  font-size: 8rem;
  font-weight: 700;
  color: #5dade2;
  margin: 0;
  line-height: 1;
}

.not-found-content h2 {
  font-size: 2.5rem;
  margin: var(--spacing-md) 0;
  font-weight: 600;
}

.not-found-content p {
  font-size: 1.2rem;
  color: var(--color-text-soft);
  margin-bottom: var(--spacing-xl);
}

.back-home-link {
  display: inline-block;
  padding: 12px 30px;
  background-color: #5dade2;
  color: white;
  text-decoration: none;
  font-weight: 600;
  border-radius: var(--rounded-md);
  transition: var(--transition-base);
  box-shadow: var(--shadow-md);
}

.back-home-link:hover {
  background-color: #4a98c8;
  transform: translateY(-2px);
  box-shadow: var(--shadow-lg);
}

@media (max-width: 767px) {
  .not-found-content h1 {
    font-size: 6rem;
  }
  .not-found-content h2 {
    font-size: 2rem;
  }
  .not-found-content p {
    font-size: 1rem;
  }
}
</style> 