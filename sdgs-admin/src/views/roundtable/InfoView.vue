<template>
  <div class="info-container">
    <h2>圆桌会议主页设置</h2>
    <el-form :model="form" label-width="120px" style="max-width:600px">
      <el-form-item label="主标题">
        <el-input v-model="form.title" placeholder="请输入主标题" />
      </el-form-item>
      <el-form-item label="内容">
        <el-input
          type="textarea"
          rows="8"
          v-model="form.content"
          placeholder="请输入内容，支持HTML"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSave" :loading="saving">保存</el-button>
        <el-button @click="fetchInfo">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { get, put } from '@/api'

const form = ref({ id: null, title: '', content: '' })
const saving = ref(false)

const fetchInfo = async () => {
  try {
    const res = await get('/api/forum-info/latest')
    if (res.code === 200) {
      form.value = {
        id: res.data.id,
        title: res.data.title || '',
        content: res.data.content || ''
      }
    }
  } catch (e) {
    ElMessage.error('加载失败')
  }
}

const handleSave = async () => {
  if (!form.value.id) return
  saving.value = true
  try {
    const res = await put(`/api/forum-info/manage/${form.value.id}`, form.value)
    if (res.code === 200) {
      ElMessage.success('保存成功')
    } else {
      ElMessage.error(res.message || '保存失败')
    }
  } catch (e) {
    ElMessage.error('保存失败')
  } finally {
    saving.value = false
  }
}

onMounted(fetchInfo)
</script>

<style scoped>
.info-container {
  padding: 20px;
  background: #fff;
  border-radius: 8px;
}
</style> 