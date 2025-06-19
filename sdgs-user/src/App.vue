<script setup>
import { RouterView } from 'vue-router'
// 1. 引入我们封装好的 axios 实例
import request from './utils/request.js'

// 2. 定义一个点击按钮时触发的异步函数
async function pingBackend() {
  try {
    // 3. 使用 request 对象发送请求
    //    这里的 '/test/ping' 会被 Vite 代理到 'http://localhost:8080/api/test/ping'
    const responseData = await request.get('/test/ping')

    // 4. 如果成功，用 alert 弹窗显示后端返回的数据
    alert('成功接收到后端数据：' + responseData)
  } catch (error) {
    // 5. 如果失败，用 alert 弹窗显示错误
    //    (我们的 request.js 拦截器也会用 ElMessage 提示，alert 更直接)
    alert('连接失败！请按 F12 查看控制台错误。')
    console.error(error)
  }
}
</script>

<template>
  <header>
    <div style="padding: 20px; text-align: center; border: 2px dashed #ccc; margin-bottom: 20px;">
      <h1>前后端连接测试</h1>
      <button @click="pingBackend" style="font-size: 16px; padding: 10px 20px;">
        点我测试与后端的连接
      </button>
    </div>
  </header>

  <RouterView />
</template>

<style scoped>
/* 这里可以留空或添加你自己的样式 */
</style>