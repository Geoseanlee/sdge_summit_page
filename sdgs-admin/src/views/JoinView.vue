<template>
  <div class="joinus-admin">
    <h2>加入我们页面管理</h2>

    <!-- Banner 独立区域 -->
    <div class="category-section">
      <h3>页面 Banner 图片</h3>
      <div class="form-section">
        <div class="image-uploader">
          <img v-if="bannerItem?.imageUrl" :src="bannerItem.imageUrl" class="image-preview" />
          <div v-else class="image-placeholder">暂无 Banner 图片</div>

          <!-- 隐藏文件选择 -->
          <input
            type="file"
            accept="image/*"
            style="display:none"
            ref="bannerFile"
            @change="handleBannerUpload"
          />
          <!-- 点击触发 -->
          <button class="upload-button" @click.prevent="triggerBannerInput">
            更换 Banner 图片
          </button>
        </div>

        <div class="button-group">
          <button class="save-button" @click="updateBanner">保存修改</button>
        </div>
      </div>
    </div>

    <!-- 其他分类 -->
    <div v-for="category in otherCategories" :key="category.key" class="category-section">
      <h3>{{ category.title }}</h3>
      <div
        v-for="item in tableData.filter(row => row.category === category.key)"
        :key="item.id"
        class="form-section"
      >
        <!-- 标题 -->
        <div class="form-group">
          <label>标题</label>
          <input type="text" v-model="item.title" />
        </div>

        <!-- 描述 + 链接，仅 project -->
        <div class="form-group" v-if="item.category === 'project'">
          <label>描述</label>
          <textarea v-model="item.description" rows="2"></textarea>
        </div>
        <div class="form-group" v-if="item.category === 'project'">
          <label>跳转链接</label>
          <input type="text" v-model="item.linkUrl" />
        </div>

        <!-- 图片上传 -->
        <div class="form-group" v-if="item.category !== 'project'">
          <label>图片</label>
          <div class="image-uploader">
            <img v-if="item.imageUrl" :src="item.imageUrl" class="image-preview" />
            <div v-else class="image-placeholder">暂无图片</div>
            <input
              type="file"
              accept="image/*"
              style="display:none"
              :ref="el => fileInputs[item.id] = el"
              @change="e => handleImageUpload(item, e)"
            />
            <button class="upload-button" @click.prevent="triggerFileInput(item.id)">
              更换图片
            </button>
          </div>
        </div>

        <!-- 按钮 -->
        <div class="button-group">
          <button class="save-button" @click="updateItem(item)">保存修改</button>
          <button class="delete-button" @click="deleteItem(item)">删除</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, computed } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from 'axios'
import { uploadImage, deleteImage } from '@/api'

const tableData = ref([])
const fileInputs = reactive({})
const bannerFile = ref(null)
const bannerItem = ref({}) // banner 数据
let newBannerUrl = '' // 新的 banner 真正地址

/* 点击文件选择 */
const triggerBannerInput = () => {
  bannerFile.value?.click()
}

/* 分类（不含 banner） */
const otherCategories = computed(() => [
  { key: 'project', title: '项目岗位信息' },
  { key: 'member', title: '入选名单（杰出青年 / 大学生 / 小使者）' },
  { key: 'food_award', title: '食品奖项' }
])

/* 加载数据 */
const loadData = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/joinus/list')
    tableData.value = res.data || []
    bannerItem.value = tableData.value.find(item => item.category === 'banner') || {}
  } catch (e) {
    ElMessage.error('加载数据失败')
  }
}

/* 上传 Banner */
const handleBannerUpload = async (event) => {
  const file = event.target.files[0]
  if (!file || !bannerItem.value) return

  // 1. 立即本地预览
  bannerItem.value.imageUrl = URL.createObjectURL(file)

  try {
    // 2. 删除旧图（如果存在）
    if (bannerItem.value.realUrl) {
      await deleteImage(bannerItem.value.realUrl)
    }

    // 3. 上传到 OSS
    const res = await uploadImage(file)
    if (res.code === 200 && res.data.fileUrl) {
      newBannerUrl = res.data.fileUrl // 保存新的真实地址
      ElMessage.success('Banner 图片上传成功，请点击保存修改')
    } else {
      ElMessage.error('上传失败：' + res.message)
    }
  } catch (e) {
    ElMessage.error('上传失败')
  }
}

/* 保存 Banner 数据到数据库 */
const updateBanner = async () => {
  if (newBannerUrl) {
    bannerItem.value.imageUrl = newBannerUrl
    bannerItem.value.realUrl = newBannerUrl
  }

  try {
    if (bannerItem.value.id) {
      // 更新
      await axios.put(`http://localhost:8080/api/joinus/update/${bannerItem.value.id}`, bannerItem.value)
      ElMessage.success('Banner 更新成功')
    } else {
      // 新增：第一次上传
      await axios.post('http://localhost:8080/api/joinus/add', {
        category: 'banner',
        title: '首页 Banner',
        imageUrl: bannerItem.value.imageUrl
      })
      ElMessage.success('Banner 新增成功')
    }
    newBannerUrl = ''
    loadData()
  } catch (e) {
    ElMessage.error('保存失败')
  }
}


/* 上传其他图片 */
const triggerFileInput = (id) => {
  fileInputs[id]?.click()
}
const handleImageUpload = async (item, event) => {
  const file = event.target.files[0]
  if (!file) return

  try {
    if (item.imageUrl) {
      await deleteImage(item.imageUrl)
    }

    const res = await uploadImage(file)
    if (res.code === 200 && res.data.fileUrl) {
      item.imageUrl = res.data.fileUrl
      ElMessage.success('图片上传成功')
    } else {
      ElMessage.error('上传失败：' + res.message)
    }
  } catch (e) {
    ElMessage.error('上传失败')
  }
}

/* 保存数据到数据库 */
const updateItem = async (item) => {
  try {
    if (item.id) {
      await axios.put(`http://localhost:8080/api/joinus/update/${item.id}`, item)
      ElMessage.success('更新成功')
    } else {
      await axios.post('http://localhost:8080/api/joinus/add', item)
      ElMessage.success('新增成功')
    }
    loadData()
  } catch {
    ElMessage.error('保存失败')
  }
}

/* 删除数据 */
const deleteItem = async (item) => {
  ElMessageBox.confirm('确认删除该项吗？', '提示', { type: 'warning' })
    .then(async () => {
      if (item.imageUrl) {
        await deleteImage(item.imageUrl)
      }
      await axios.delete(`http://localhost:8080/api/joinus/delete/${item.id}`)
      ElMessage.success('删除成功')
      loadData()
    })
    .catch(() => {})
}

onMounted(() => loadData())
</script>

<style scoped>
.joinus-admin {
  padding: 2rem;
}
h2 {
  text-align: center;
  margin-bottom: 2rem;
}

.category-section {
  margin-bottom: 3rem;
}
.category-section h3 {
  font-size: 1.3rem;
  color: #409eff;
  margin-bottom: 1rem;
}

.form-section {
  background: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 6px;
  padding: 1rem;
  margin-bottom: 1rem;
}

.form-group {
  margin-bottom: 1rem;
}
.form-group label {
  display: block; 
  font-weight: bold;
  margin-bottom: 0.3rem;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.image-uploader {
  display: flex;
  flex-direction: column; 
  align-items: center;    
  text-align: center;
}
.image-preview {
  max-width: 100%;
  max-height: 200px;
  object-fit: contain;
  margin-bottom: 0.8rem;
}
.image-placeholder {
  width: 100%;
  height: 150px;
  line-height: 150px;
  background: #f5f5f5;
  color: #999;
  margin-bottom: 0.8rem;
}

.upload-button {
  background: #1890ff;
  color: white;
  border: none;
  padding: 0.4rem 1rem;
  border-radius: 4px;
  cursor: pointer;
}
.upload-button:hover {
  background: #40a9ff;
}

.button-group {
  display: flex;
  gap: 1rem;
  margin-top: 1rem;
  justify-content: center;
}
.save-button {
  background: #409eff;
  color: white;
  border: none;
  padding: 0.6rem 1rem;
  border-radius: 4px;
}
.delete-button {
  background: #f56c6c;
  color: white;
  border: none;
  padding: 0.6rem 1rem;
  border-radius: 4px;
}
</style>
