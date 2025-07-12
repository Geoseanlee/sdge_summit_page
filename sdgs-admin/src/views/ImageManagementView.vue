<template>
  <div class="image-management-container">
    <div class="header">
      <h1>图片资源管理</h1>
      <p>在这里，您可以上传、浏览和删除存储在云端的图片资源。</p>
    </div>

    <!-- 上传区域 -->
    <div class="upload-section card">
      <h2>上传新图片</h2>
      <div class="upload-box" @click="triggerFileInput">
        <input ref="fileInput" type="file" accept="image/*" @change="handleFileSelect" style="display: none" />
        <el-icon :size="50" color="#c0c4cc"><UploadFilled /></el-icon>
        <p>点击此处选择图片</p>
        <small>支持 JPG、PNG、GIF 格式，最大10MB</small>
      </div>
      <el-button type="primary" :disabled="!selectedFile || uploading" @click="uploadSingleImage" :loading="uploading">
        {{ uploading ? '上传中...' : '开始上传' }}
      </el-button>
    </div>

    <!-- 图片展示区域 -->
    <div class="gallery-section card">
      <div class="gallery-header">
        <h2>图片库</h2>
        <el-button @click="refreshImageList" :icon="Refresh" circle></el-button>
      </div>

      <div v-if="loading" class="loading-state">
        <el-icon class="is-loading" :size="30"><Loading /></el-icon>
        <p>正在加载图片...</p>
      </div>

      <div v-else-if="imageList.length === 0" class="empty-state">
        <el-empty description="暂无图片，快去上传一些吧！" />
      </div>

      <div v-else class="image-grid">
        <div v-for="(image, index) in imageList" :key="image.fileUrl" class="image-card">
          <el-image :src="image.fileUrl" fit="cover" class="image-item" lazy>
             <template #placeholder>
              <div class="image-slot">加载中<span class="dot">...</span></div>
            </template>
          </el-image>
          <div class="image-overlay">
            <el-button type="danger" :icon="Delete" @click="handleDeleteImage(image.fileUrl, index)" circle></el-button>
          </div>
          <div class="image-info">
            <p class="file-name">{{ getShortFileName(image.fileName) }}</p>
            <p class="file-size">{{ formatFileSize(image.fileSize) }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { UploadFilled, Refresh, Delete, Loading } from '@element-plus/icons-vue'
import { uploadImage, listImages, deleteImage } from '@/api'

const selectedFile = ref(null)
const uploading = ref(false)
const loading = ref(false)
const imageList = ref([])
const fileInput = ref(null)

onMounted(() => {
  loadImageList()
})

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
}

const uploadSingleImage = async () => {
  if (!selectedFile.value) return
  uploading.value = true
  try {
    const res = await uploadImage(selectedFile.value)
    if (res.code === 200) {
      ElMessage.success('图片上传成功！')
      selectedFile.value = null
      fileInput.value.value = ''
      loadImageList()
    } else {
      ElMessage.error(`上传失败：${res.message}`)
    }
  } catch (error) {
    ElMessage.error(`上传失败：${error.message || '网络错误'}`)
  } finally {
    uploading.value = false
  }
}

const loadImageList = async () => {
  loading.value = true
  try {
    const res = await listImages({ maxKeys: 100 }) // 获取最多100张图片
    if (res.code === 200) {
      imageList.value = res.data.files || []
    } else {
      ElMessage.error(`加载图片列表失败：${res.message}`)
    }
  } catch (error) {
    ElMessage.error(`加载图片列表失败：${error.message || '网络错误'}`)
  } finally {
    loading.value = false
  }
}

const refreshImageList = () => {
  ElMessage.info('正在刷新图片列表...')
  loadImageList()
}

const handleDeleteImage = async (fileUrl, index) => {
  try {
    await ElMessageBox.confirm('此操作将永久删除该图片, 是否继续?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    })
    
    const res = await deleteImage(fileUrl)
    if (res.code === 200) {
      ElMessage.success('图片删除成功！')
      imageList.value.splice(index, 1)
    } else {
      ElMessage.error(`删除失败：${res.message}`)
    }
  } catch (error) {
    // 如果 error 是 'cancel'，则是用户点击了取消，不做任何事
    if (error !== 'cancel') {
        ElMessage.error(`删除失败：${error.message || '网络错误'}`)
    }
  }
}

const formatFileSize = (size) => {
  if (!size) return 'N/A'
  if (size < 1024) return size + ' B'
  if (size < 1024 * 1024) return (size / 1024).toFixed(1) + ' KB'
  return (size / (1024 * 1024)).toFixed(1) + ' MB'
}

const getShortFileName = (fileName) => {
  if (!fileName) return '未命名'
  const parts = fileName.split('/')
  return parts[parts.length - 1]
}
</script>

<style scoped>
.image-management-container {
  padding: 20px;
}
.header {
  text-align: center;
  margin-bottom: 30px;
}
.header h1 {
  color: #333;
  margin-bottom: 10px;
}
.card {
  background: white;
  border-radius: 8px;
  padding: 25px;
  margin-bottom: 30px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.upload-box {
  text-align: center;
  padding: 30px;
  border: 2px dashed #dcdfe6;
  border-radius: 6px;
  cursor: pointer;
  transition: border-color 0.3s;
  margin-bottom: 20px;
}
.upload-box:hover {
  border-color: #409eff;
}
.gallery-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.loading-state, .empty-state {
  text-align: center;
  padding: 50px 0;
  color: #909399;
}
.image-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 20px;
}
.image-card {
  border: 1px solid #ebeef5;
  border-radius: 8px;
  overflow: hidden;
  position: relative;
  transition: transform 0.3s, box-shadow 0.3s;
}
.image-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 20px rgba(0,0,0,0.08);
}
.image-item {
  width: 100%;
  height: 180px;
}
.image-slot {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: #f5f7fa;
  color: #c0c4cc;
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
  opacity: 0;
  transition: opacity 0.3s;
}
.image-card:hover .image-overlay {
  opacity: 1;
}
.image-info {
  padding: 12px;
}
.file-name {
  font-weight: 500;
  color: #303133;
  margin: 0 0 5px 0;
  font-size: 14px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.file-size {
  font-size: 12px;
  color: #909399;
  margin: 0;
}
</style> 