import axios from 'axios'
import { ElMessage } from 'element-plus'

// 1. 创建一个新的 aixos 实例
const service = axios.create({
  baseURL: '/api',
  timeout: 10000,
})

// 2. 请求拦截器
service.interceptors.request.use(
  (config) => {
    // 用户端为公开访问，无需在此处添加 Token
    return config
  },
  (error) => {
    console.error('Request Error:', error)
    return Promise.reject(error)
  },
)

// 3. 响应拦截器
service.interceptors.response.use(
  (response) => {
    const res = response.data
    if (res.code !== 200) {
      ElMessage({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000,
      })
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      // 成功时直接返回数据部分
      return res.data
    }
  },
  (error) => {
    console.error('Response Error:', error)
    ElMessage({
      message: error.message,
      type: 'error',
      duration: 5 * 1000,
    })
    return Promise.reject(error)
  },
)

// 4. 导出 axios 实例
export default service
