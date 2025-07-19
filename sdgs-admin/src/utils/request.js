import axios from 'axios'
import { ElMessage } from 'element-plus'

// 创建 axios 实例
const service = axios.create({
  baseURL: '/api', // api 的 base_url
  timeout: 15000, // 增加默认超时时间到15秒
})

// request 拦截器
service.interceptors.request.use(
  (config) => {
    // 可以在这里添加token等信息
    // config.headers['Authorization'] = 'Bearer ' + getToken()

    // 如果是FormData，让浏览器自动设置Content-Type
    if (config.data instanceof FormData) {
      delete config.headers['Content-Type']
    }

    return config
  },
  (error) => {
    // Do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  },
)

// response 拦截器
service.interceptors.response.use(
  (response) => {
    const res = response.data
    if (res.code !== 20000 && res.code !== 200) {
      ElMessage({
        message: res.message,
        type: 'error',
        duration: 5 * 1000,
      })
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  (error) => {
    console.log('err' + error) // for debug
    ElMessage({
      message: error.message,
      type: 'error',
      duration: 5 * 1000,
    })
    return Promise.reject(error)
  },
)

export default service
