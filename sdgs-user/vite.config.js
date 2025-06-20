import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue(), vueDevTools()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },
  server: {
    proxy: {
      '/api': {
        // '/api' 是一个标识，所有以/api开头的请求都会被代理
        target: 'http://localhost:8080', // 代理的目标地址
        changeOrigin: true, // 需要虚拟主机站点
        // 不重写路径，保持 /api 前缀
      },
    },
  },
})
