<template>
  <div class="test-oss-container">
    <div class="test-header">
      <h1>🌐 OSS图片上传测试</h1>
      <p>测试阿里云OSS图片上传、显示和删除功能</p>
    </div>

    <!-- 上传区域 -->
    <div class="upload-section">
      <h2>📤 图片上传</h2>
      
      <!-- 单张图片上传 -->
      <div class="upload-box">
        <h3>单张图片上传</h3>
        <div class="upload-area" @click="triggerFileInput">
          <input
            ref="fileInput"
            type="file"
            accept="image/*"
            @change="handleFileSelect"
            style="display: none"
          />
          <div class="upload-icon">📸</div>
          <p>点击上传图片</p>
          <small>支持 JPG、PNG、GIF 格式，最大10MB</small>
        </div>
        <button 
          :disabled="!selectedFile || uploading" 
          @click="uploadSingleImage"
          class="upload-btn"
        >
          {{ uploading ? '上传中...' : '上传图片' }}
        </button>
      </div>
    </div>

    <!-- 图片展示区域 -->
    <div class="gallery-section">
      <div class="gallery-header">
        <h2>🖼️ 图片库</h2>
        <button @click="refreshImageList" class="refresh-btn">🔄 刷新</button>
      </div>

      <div v-if="loading" class="loading">
        <div class="spinner"></div>
        <p>加载中...</p>
      </div>

      <div v-else-if="imageList.length === 0" class="empty-state">
        <div class="empty-icon">📭</div>
        <p>暂无图片，快去上传一些吧！</p>
      </div>

      <div v-else class="image-grid">
        <div 
          v-for="(image, index) in imageList" 
          :key="index" 
          class="image-card"
        >
          <div class="image-wrapper">
            <img 
              :src="image.fileUrl" 
              :alt="image.fileName"
              @load="handleImageLoad"
            />
            <div class="image-overlay">
              <button 
                @click="deleteImage(image.fileUrl, index)"
                class="action-btn delete-btn"
                title="删除"
              >
                🗑️
              </button>
            </div>
          </div>
          <div class="image-info">
            <p class="file-name">{{ getShortFileName(image.fileName) }}</p>
            <p class="file-size">{{ formatFileSize(image.fileSize) }}</p>
          </div>
        </div>
      </div>
    </div>

    <!-- 操作结果提示 -->
    <div v-if="message" :class="['message', messageType]">
      {{ message }}
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

// 响应式数据
const selectedFile = ref(null)
const uploading = ref(false)
const loading = ref(false)
const imageList = ref([])
const message = ref('')
const messageType = ref('success')

// 引用
const fileInput = ref(null)

// 页面加载时获取图片列表
onMounted(() => {
  loadImageList()
})

// 触发文件选择
const triggerFileInput = () => {
  fileInput.value.click()
}

// 处理文件选择
const handleFileSelect = (event) => {
  const file = event.target.files[0]
  if (file && validateFile(file)) {
    selectedFile.value = file
  }
}

// 文件验证
const validateFile = (file) => {
  if (!file.type.startsWith('image/')) {
    showMessage('请选择图片文件', 'error')
    return false
  }
  
  if (file.size > 10 * 1024 * 1024) {
    showMessage('文件大小不能超过10MB', 'error')
    return false
  }
  
  return true
}

// 上传单张图片
const uploadSingleImage = async () => {
  if (!selectedFile.value) return
  
  uploading.value = true
  const formData = new FormData()
  formData.append('file', selectedFile.value)
  
  try {
    const response = await fetch('/api/file/upload/image', {
      method: 'POST',
      body: formData
    })
    
    const result = await response.json()
    
    if (result.code === 200) {
      showMessage('图片上传成功！', 'success')
      selectedFile.value = null
      fileInput.value.value = ''
      loadImageList() // 刷新图片列表
    } else {
      showMessage(`上传失败：${result.message}`, 'error')
    }
  } catch (error) {
    showMessage(`上传失败：${error.message}`, 'error')
  } finally {
    uploading.value = false
  }
}

// 加载图片列表
const loadImageList = async () => {
  loading.value = true
  try {
    const response = await fetch('/api/file/list?maxKeys=50')
    const result = await response.json()
    
    if (result.code === 200) {
      imageList.value = result.data.files || []
    } else {
      showMessage(`加载图片列表失败：${result.message}`, 'error')
    }
  } catch (error) {
    showMessage(`加载图片列表失败：${error.message}`, 'error')
  } finally {
    loading.value = false
  }
}

// 刷新图片列表
const refreshImageList = () => {
  loadImageList()
}

// 删除图片
const deleteImage = async (fileUrl, index) => {
  if (!confirm('确定要删除这张图片吗？')) return
  
  try {
    const response = await fetch(`/api/file/delete?fileUrl=${encodeURIComponent(fileUrl)}`, {
      method: 'DELETE'
    })
    
    const result = await response.json()
    
    if (result.code === 200) {
      showMessage('图片删除成功！', 'success')
      imageList.value.splice(index, 1) // 从列表中移除
    } else {
      showMessage(`删除失败：${result.message}`, 'error')
    }
  } catch (error) {
    showMessage(`删除失败：${error.message}`, 'error')
  }
}

// 图片加载处理
const handleImageLoad = (event) => {
  event.target.classList.add('loaded')
}

// 工具函数
const formatFileSize = (size) => {
  if (size < 1024) return size + ' B'
  if (size < 1024 * 1024) return (size / 1024).toFixed(1) + ' KB'
  return (size / (1024 * 1024)).toFixed(1) + ' MB'
}

const getShortFileName = (fileName) => {
  const parts = fileName.split('/')
  return parts[parts.length - 1]
}

// 显示消息
const showMessage = (msg, type = 'success') => {
  message.value = msg
  messageType.value = type
  setTimeout(() => {
    message.value = ''
  }, 3000)
}
</script>

<style scoped>
.test-oss-container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 20px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', sans-serif;
}

.test-header {
  text-align: center;
  margin-bottom: 40px;
}

.test-header h1 {
  color: #2563eb;
  margin-bottom: 10px;
}

.upload-section {
  background: white;
  border-radius: 12px;
  padding: 30px;
  margin-bottom: 40px;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
}

.upload-box {
  margin-bottom: 30px;
  padding: 20px;
  border: 2px dashed #e5e7eb;
  border-radius: 8px;
  background: #f9fafb;
}

.upload-area {
  text-align: center;
  padding: 40px 20px;
  cursor: pointer;
  transition: all 0.3s ease;
  border-radius: 8px;
}

.upload-area:hover {
  background: #f3f4f6;
  border-color: #2563eb;
}

.upload-icon {
  font-size: 3rem;
  margin-bottom: 10px;
}

.upload-btn {
  background: #2563eb;
  color: white;
  border: none;
  padding: 12px 24px;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 500;
  margin-top: 15px;
  transition: background-color 0.3s ease;
}

.upload-btn:hover:not(:disabled) {
  background: #1d4ed8;
}

.upload-btn:disabled {
  background: #9ca3af;
  cursor: not-allowed;
}

.gallery-section {
  background: white;
  border-radius: 12px;
  padding: 30px;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
}

.gallery-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.refresh-btn {
  background: #10b981;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 6px;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.3s ease;
}

.refresh-btn:hover {
  background: #059669;
}

.loading {
  text-align: center;
  padding: 40px;
}

.spinner {
  width: 40px;
  height: 40px;
  border: 4px solid #f3f4f6;
  border-top: 4px solid #2563eb;
  border-radius: 50%;
  animation: spin 1s linear infinite;
  margin: 0 auto 10px;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.empty-state {
  text-align: center;
  padding: 60px 20px;
  color: #6b7280;
}

.empty-icon {
  font-size: 4rem;
  margin-bottom: 15px;
}

.image-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 20px;
}

.image-card {
  border: 1px solid #e5e7eb;
  border-radius: 8px;
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.image-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 25px -5px rgba(0, 0, 0, 0.1);
}

.image-wrapper {
  position: relative;
  width: 100%;
  height: 200px;
  overflow: hidden;
}

.image-wrapper img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: opacity 0.3s ease;
  opacity: 0;
}

.image-wrapper img.loaded {
  opacity: 1;
}

.image-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  opacity: 0;
  transition: opacity 0.3s ease;
}

.image-card:hover .image-overlay {
  opacity: 1;
}

.action-btn {
  background: white;
  border: none;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  cursor: pointer;
  font-size: 16px;
  transition: transform 0.2s ease;
}

.action-btn:hover {
  transform: scale(1.1);
}

.delete-btn:hover {
  background: #fee2e2;
}

.image-info {
  padding: 12px;
  background: #f9fafb;
}

.file-name {
  font-weight: 500;
  color: #374151;
  margin: 0 0 5px 0;
  font-size: 14px;
  word-break: break-all;
}

.file-size {
  font-size: 12px;
  color: #6b7280;
  margin: 2px 0;
}

.message {
  position: fixed;
  top: 20px;
  right: 20px;
  padding: 12px 20px;
  border-radius: 6px;
  color: white;
  font-weight: 500;
  z-index: 1001;
  animation: slideIn 0.3s ease;
}

.message.success {
  background: #10b981;
}

.message.error {
  background: #ef4444;
}

@keyframes slideIn {
  from {
    transform: translateX(100%);
    opacity: 0;
  }
  to {
    transform: translateX(0);
    opacity: 1;
  }
}

@media (max-width: 768px) {
  .test-oss-container {
    padding: 10px;
  }
  
  .upload-section, .gallery-section {
    padding: 20px;
  }
  
  .gallery-header {
    flex-direction: column;
    gap: 15px;
    align-items: stretch;
  }
  
  .image-grid {
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    gap: 15px;
  }
  
  .image-wrapper {
    height: 150px;
  }
}
</style> 