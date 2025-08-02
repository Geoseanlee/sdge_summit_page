<template>
  <div class="test-oss-container">
    <h1>OSS上传测试页面</h1>
    
    <!-- OSS连接测试 -->
    <div class="test-section">
      <h2>OSS连接测试</h2>
      <el-button @click="testOssConnection" :loading="testingConnection">
        {{ testingConnection ? '测试中...' : '测试OSS连接' }}
      </el-button>
      <div v-if="connectionResult" class="result">
        <pre>{{ JSON.stringify(connectionResult, null, 2) }}</pre>
      </div>
    </div>

    <!-- 文件上传测试 -->
    <div class="test-section">
      <h2>文件上传测试</h2>
      <div class="upload-area">
        <input ref="fileInput" type="file" accept="image/*" @change="handleFileSelect" style="display: none" />
        <el-button @click="triggerFileInput">选择图片文件</el-button>
        <span v-if="selectedFile" class="file-info">
          已选择: {{ selectedFile.name }} ({{ formatFileSize(selectedFile.size) }})
        </span>
      </div>
      <el-button 
        type="primary" 
        @click="testUpload" 
        :disabled="!selectedFile" 
        :loading="uploading"
      >
        {{ uploading ? '上传中...' : '测试上传' }}
      </el-button>
      <div v-if="uploadResult" class="result">
        <pre>{{ JSON.stringify(uploadResult, null, 2) }}</pre>
      </div>
    </div>

    <!-- 错误信息显示 -->
    <div v-if="errorMessage" class="error-section">
      <h3>错误信息</h3>
      <div class="error-message">
        {{ errorMessage }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { get, post } from '@/api'

const fileInput = ref(null)
const selectedFile = ref(null)
const testingConnection = ref(false)
const uploading = ref(false)
const connectionResult = ref(null)
const uploadResult = ref(null)
const errorMessage = ref('')

const triggerFileInput = () => {
  fileInput.value.click()
}

const handleFileSelect = (event) => {
  const file = event.target.files[0]
  if (!file) return

  if (!file.type.startsWith('image/')) {
    ElMessage.error('请选择图片文件')
    return
  }
  
  if (file.size > 10 * 1024 * 1024) {
    ElMessage.error('文件大小不能超过10MB')
    return
  }

  selectedFile.value = file
  errorMessage.value = ''
  uploadResult.value = null
}

const testOssConnection = async () => {
  testingConnection.value = true
  connectionResult.value = null
  errorMessage.value = ''
  
  try {
    const res = await get('/api/test/oss')
    connectionResult.value = res
    if (res.code === 200) {
      ElMessage.success('OSS连接测试成功')
    } else {
      ElMessage.error(`OSS连接测试失败: ${res.message}`)
    }
  } catch (error) {
    errorMessage.value = `OSS连接测试失败: ${error.message || '网络错误'}`
    ElMessage.error(errorMessage.value)
  } finally {
    testingConnection.value = false
  }
}

const testUpload = async () => {
  if (!selectedFile.value) return
  
  uploading.value = true
  uploadResult.value = null
  errorMessage.value = ''
  
  try {
    const formData = new FormData()
    formData.append('file', selectedFile.value)
    
    const res = await post('/api/file/upload/image', formData)
    uploadResult.value = res
    
    if (res.code === 200) {
      ElMessage.success('文件上传测试成功')
    } else {
      ElMessage.error(`文件上传测试失败: ${res.message}`)
    }
  } catch (error) {
    errorMessage.value = `文件上传测试失败: ${error.message || '网络错误'}`
    ElMessage.error(errorMessage.value)
  } finally {
    uploading.value = false
  }
}

const formatFileSize = (bytes) => {
  if (bytes === 0) return '0 Bytes'
  const k = 1024
  const sizes = ['Bytes', 'KB', 'MB', 'GB']
  const i = Math.floor(Math.log(bytes) / Math.log(k))
  return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i]
}
</script>

<style scoped>
.test-oss-container {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}

.test-section {
  margin-bottom: 30px;
  padding: 20px;
  border: 1px solid #e4e7ed;
  border-radius: 4px;
}

.test-section h2 {
  margin-top: 0;
  margin-bottom: 15px;
  color: #303133;
}

.upload-area {
  margin-bottom: 15px;
}

.file-info {
  margin-left: 10px;
  color: #606266;
}

.result {
  margin-top: 15px;
  padding: 10px;
  background-color: #f5f7fa;
  border-radius: 4px;
  font-family: monospace;
  font-size: 12px;
  max-height: 300px;
  overflow-y: auto;
}

.error-section {
  margin-top: 20px;
  padding: 15px;
  background-color: #fef0f0;
  border: 1px solid #fbc4c4;
  border-radius: 4px;
}

.error-section h3 {
  margin-top: 0;
  color: #f56c6c;
}

.error-message {
  color: #f56c6c;
  font-family: monospace;
  white-space: pre-wrap;
}
</style> 