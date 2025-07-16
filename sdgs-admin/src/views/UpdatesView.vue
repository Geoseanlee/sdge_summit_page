<template>
  <div class="updates-management-container">
    <!-- 列表视图 -->
    <div v-if="currentView === 'list'" class="list-view">
      <div class="header">
        <h1>动态管理</h1>
        <p>管理网站的动态内容，包括新增、编辑和删除动态。</p>
      </div>

      <!-- 操作栏 -->
      <div class="actions-bar card">
        <el-button type="primary" @click="showCreateForm" :icon="Plus">新增动态</el-button>
        <el-button @click="refreshNewsList" :icon="Refresh">刷新列表</el-button>
      </div>

      <!-- 动态列表 -->
      <div class="news-list-section card">
        <div class="list-header">
          <h2>动态列表</h2>
          <div class="list-stats">
            <span>总计: {{ newsList.length }} 条动态</span>
          </div>
        </div>

        <div v-if="loading" class="loading-state">
          <el-icon class="is-loading" :size="30"><Loading /></el-icon>
          <p>正在加载动态列表...</p>
        </div>

        <div v-else-if="newsList.length === 0" class="empty-state">
          <el-empty description="暂无动态，点击上方按钮新增动态" />
        </div>

        <div v-else class="news-table">
          <el-table :data="newsList" style="width: 100%" stripe>
            <el-table-column prop="id" label="ID" width="80" sortable />
            <el-table-column prop="title" label="主题" min-width="300">
              <template #default="{ row }">
                <div class="news-title">
                  <h4>{{ row.title }}</h4>
                  <p class="news-subtitle" v-if="row.subtitle">{{ row.subtitle }}</p>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="publishTime" label="发布时间" width="180" sortable>
              <template #default="{ row }">
                <span>{{ formatDate(row.publishTime) }}</span>
              </template>
            </el-table-column>
            <el-table-column prop="status" label="状态" width="100">
              <template #default="{ row }">
                <el-tag :type="row.status === 1 ? 'success' : 'info'">
                  {{ row.status === 1 ? '已发布' : '草稿' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="viewCount" label="阅读量" width="100" sortable>
              <template #default="{ row }">
                <span>{{ row.viewCount || 0 }}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="200" fixed="right">
              <template #default="{ row }">
                <div class="action-buttons">
                  <el-button size="small" @click="showEditForm(row)" :icon="Edit">编辑</el-button>
                  <el-button size="small" type="danger" @click="handleDelete(row)" :icon="Delete">删除</el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </div>

    <!-- 新增/编辑表单视图 -->
    <div v-else-if="currentView === 'form'" class="form-view">
      <div class="form-header card">
        <h2>{{ isEdit ? '编辑动态' : '新增动态' }}</h2>
        <p>{{ isEdit ? '修改现有动态内容' : '创建新的动态内容' }}</p>
      </div>

      <div class="edit-form card">
        <el-form :model="newsForm" :rules="rules" ref="newsFormRef" label-width="100px">
          <!-- 标题 -->
          <el-form-item label="标题" prop="title">
            <el-input v-model="newsForm.title" placeholder="请输入动态标题" maxlength="200" show-word-limit />
          </el-form-item>

          <!-- 副标题 -->
          <el-form-item label="副标题" prop="subtitle">
            <el-input v-model="newsForm.subtitle" placeholder="请输入副标题（可选）" maxlength="500" show-word-limit />
          </el-form-item>

          <!-- 发布时间 -->
          <el-form-item label="发布时间" prop="publishTime">
            <el-date-picker
              v-model="newsForm.publishTime"
              type="datetime"
              placeholder="选择发布时间"
              format="YYYY-MM-DD HH:mm"
              value-format="YYYY-MM-DD HH:mm:ss"
              style="width: 100%"
            />
          </el-form-item>

          <!-- 封面图片 -->
          <el-form-item label="封面图片">
            <div class="image-uploader">
              <div class="upload-area" @click="triggerFileInput">
                <div v-if="newsForm.coverImageUrl" class="image-preview">
                  <img :src="newsForm.coverImageUrl" alt="封面图片" />
                  <div class="image-actions">
                    <el-button type="danger" size="small" @click.stop="removeCoverImage">删除</el-button>
                  </div>
                </div>
                <div v-else class="upload-placeholder">
                  <el-icon :size="40"><Plus /></el-icon>
                  <p>点击上传封面图片</p>
                  <small>支持 JPG、PNG、GIF 格式，最大10MB</small>
                </div>
              </div>
              <input 
                ref="fileInput" 
                type="file" 
                accept="image/*" 
                @change="handleImageUpload" 
                style="display: none" 
              />
            </div>
          </el-form-item>

          <!-- 分类 -->
          <el-form-item label="分类">
            <el-select v-model="newsForm.category" placeholder="请选择分类">
              <el-option label="政策发布" value="政策发布" />
              <el-option label="气候行动" value="气候行动" />
              <el-option label="教育发展" value="教育发展" />
              <el-option label="健康福祉" value="健康福祉" />
              <el-option label="经济发展" value="经济发展" />
              <el-option label="环境保护" value="环境保护" />
              <el-option label="其他" value="其他" />
            </el-select>
          </el-form-item>

          <!-- 标签 -->
          <el-form-item label="标签">
            <el-input v-model="newsForm.tags" placeholder="请输入标签，用逗号分隔" />
          </el-form-item>

          <!-- 作者 -->
          <el-form-item label="作者">
            <el-input v-model="newsForm.author" placeholder="请输入作者" />
          </el-form-item>

          <!-- 来源 -->
          <el-form-item label="来源">
            <el-input v-model="newsForm.source" placeholder="请输入来源" />
          </el-form-item>

          <!-- 简介 -->
          <el-form-item label="简介" prop="summary">
            <el-input
              v-model="newsForm.summary"
              type="textarea"
              :rows="4"
              placeholder="请输入动态简介"
              maxlength="1000"
              show-word-limit
            />
          </el-form-item>

          <!-- 正文内容 -->
          <el-form-item label="正文内容" prop="content">
            <el-input
              v-model="newsForm.content"
              type="textarea"
              :rows="15"
              placeholder="请输入动态正文内容，支持HTML格式"
              style="width: 100%"
            />
            <div class="editor-tip">
              <small>提示：您可以使用HTML标签来格式化文本，如 &lt;h2&gt;、&lt;p&gt;、&lt;strong&gt; 等</small>
            </div>
          </el-form-item>

          <!-- 状态 -->
          <el-form-item label="状态">
            <el-radio-group v-model="newsForm.status">
              <el-radio :label="0">草稿</el-radio>
              <el-radio :label="1">已发布</el-radio>
            </el-radio-group>
          </el-form-item>

          <!-- 动态ID管理（仅新增时显示） -->
          <el-form-item v-if="!isEdit" label="ID管理">
            <el-checkbox v-model="useNextAvailableId">使用下一个可用的ID（重用被删除的ID）</el-checkbox>
            <div v-if="useNextAvailableId" class="next-id-info">
              <small>下一个可用ID: {{ nextAvailableId }}</small>
            </div>
          </el-form-item>

          <!-- 按钮 -->
          <el-form-item>
            <div class="form-actions">
              <el-button @click="backToList">取消</el-button>
              <el-button type="primary" @click="handleSubmit" :loading="submitting">
                {{ isEdit ? '保存修改' : '创建动态' }}
              </el-button>
            </div>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Refresh, Edit, Delete, Loading } from '@element-plus/icons-vue'
import { get, post, put, del, uploadImage } from '@/api'

// 视图状态管理
const currentView = ref('list') // 'list' | 'form'
const isEdit = ref(false)
const editingId = ref(null)

// 列表相关状态
const loading = ref(false)
const newsList = ref([])

// 表单相关状态
const submitting = ref(false)
const newsFormRef = ref(null)
const fileInput = ref(null)
const useNextAvailableId = ref(false)
const nextAvailableId = ref(null)

// 表单数据
const newsForm = reactive({
  title: '',
  subtitle: '',
  summary: '',
  content: '',
  coverImageUrl: '',
  category: '',
  tags: '',
  author: '',
  source: '',
  publishTime: '',
  status: 1,
  viewCount: 0
})

// 表单验证规则
const rules = {
  title: [
    { required: true, message: '请输入动态标题', trigger: 'blur' },
    { min: 1, max: 200, message: '标题长度在 1 到 200 个字符', trigger: 'blur' }
  ],
  summary: [
    { required: true, message: '请输入动态简介', trigger: 'blur' },
    { min: 1, max: 1000, message: '简介长度在 1 到 1000 个字符', trigger: 'blur' }
  ],
  content: [
    { required: true, message: '请输入动态正文', trigger: 'blur' }
  ]
}

// 新闻相关API封装，使用现有的基础API方法
const getAllNews = () => get('/api/news/admin/list')
const getNewsById = (id) => get(`/api/news/${id}`)
const createNews = (data) => post('/api/news/admin', data)
const createNewsWithId = (data) => post('/api/news/admin/withId', data)
const updateNews = (id, data) => put(`/api/news/admin/${id}`, data)
const deleteNews = (id) => del(`/api/news/admin/${id}`)
const uploadNewsImage = (file) => uploadImage(file)
const getNextAvailableId = () => get('/api/news/admin/nextId')

onMounted(() => {
  loadNewsList()
})

// ===== 列表功能 =====
const loadNewsList = async () => {
  loading.value = true
  try {
    const res = await getAllNews()
    if (res.code === 200) {
      newsList.value = res.data || []
    } else {
      ElMessage.error(`加载动态列表失败：${res.message}`)
    }
  } catch (error) {
    ElMessage.error(`加载动态列表失败：${error.message || '网络错误'}`)
  } finally {
    loading.value = false
  }
}

const refreshNewsList = () => {
  ElMessage.info('正在刷新动态列表...')
  loadNewsList()
}

const handleDelete = async (news) => {
  try {
    await ElMessageBox.confirm(
      `确定要删除动态 "${news.title}" 吗？删除后不可恢复！`,
      '确认删除',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
    )
    
    const res = await deleteNews(news.id)
    if (res.code === 200) {
      ElMessage.success('动态删除成功！')
      loadNewsList()
    } else {
      ElMessage.error(`删除失败：${res.message}`)
    }
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error(`删除失败：${error.message || '网络错误'}`)
    }
  }
}

const formatDate = (dateString) => {
  if (!dateString) return '-'
  const date = new Date(dateString)
  return date.toLocaleString('zh-CN', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit'
  })
}

// ===== 视图切换功能 =====
const showCreateForm = () => {
  resetForm()
  isEdit.value = false
  editingId.value = null
  currentView.value = 'form'
  // 设置默认发布时间和获取下一个可用ID
  newsForm.publishTime = new Date().toISOString().slice(0, 19).replace('T', ' ')
  fetchNextAvailableId()
}

const showEditForm = async (news) => {
  resetForm()
  isEdit.value = true
  editingId.value = news.id
  currentView.value = 'form'
  await loadNewsData(news.id)
}

const backToList = () => {
  currentView.value = 'list'
  resetForm()
}

// ===== 表单功能 =====
const resetForm = () => {
  Object.assign(newsForm, {
    title: '',
    subtitle: '',
    summary: '',
    content: '',
    coverImageUrl: '',
    category: '',
    tags: '',
    author: '',
    source: '',
    publishTime: '',
    status: 1,
    viewCount: 0
  })
  useNextAvailableId.value = false
  nextAvailableId.value = null
  if (newsFormRef.value) {
    newsFormRef.value.clearValidate()
  }
}

const fetchNextAvailableId = async () => {
  try {
    const res = await getNextAvailableId()
    if (res.code === 200) {
      nextAvailableId.value = res.data
    }
  } catch (error) {
    console.error('获取下一个可用ID失败:', error)
  }
}

const loadNewsData = async (id) => {
  try {
    const res = await getNewsById(id)
    if (res.code === 200 && res.data) {
      const data = res.data
      Object.assign(newsForm, {
        title: data.title || '',
        subtitle: data.subtitle || '',
        summary: data.summary || '',
        content: data.content || '',
        coverImageUrl: data.coverImageUrl || '',
        category: data.category || '',
        tags: data.tags || '',
        author: data.author || '',
        source: data.source || '',
        publishTime: data.publishTime || '',
        status: data.status || 1,
        viewCount: data.viewCount || 0
      })
    } else {
      ElMessage.error('加载动态数据失败')
      backToList()
    }
  } catch (error) {
    ElMessage.error('加载动态数据失败')
    backToList()
  }
}

const triggerFileInput = () => {
  fileInput.value.click()
}

const handleImageUpload = async (event) => {
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

  try {
    const res = await uploadNewsImage(file)
    if (res.code === 200) {
      // 后端返回的是详细信息对象，需要提取fileUrl
      newsForm.coverImageUrl = res.data.fileUrl
      ElMessage.success('图片上传成功')
    } else {
      ElMessage.error(`图片上传失败：${res.message}`)
    }
  } catch (error) {
    ElMessage.error(`图片上传失败：${error.message || '网络错误'}`)
  }
}

const removeCoverImage = () => {
  newsForm.coverImageUrl = ''
}

const handleSubmit = async () => {
  if (!newsFormRef.value) return
  
  try {
    await newsFormRef.value.validate()
    
    // 验证正文内容
    if (!newsForm.content || newsForm.content.trim() === '') {
      ElMessage.error('请输入动态正文内容')
      return
    }
    
    submitting.value = true
    
    const submitData = { ...newsForm }
    
    let res
    if (isEdit.value) {
      res = await updateNews(editingId.value, submitData)
    } else {
      // 新增模式，根据用户选择使用不同的API
      if (useNextAvailableId.value && nextAvailableId.value) {
        // 使用指定ID创建新闻
        submitData.id = nextAvailableId.value
        res = await createNewsWithId(submitData)
      } else {
        // 使用自动生成ID创建新闻
        res = await createNews(submitData)
      }
    }
    
    if (res.code === 200) {
      ElMessage.success(isEdit.value ? '动态修改成功' : '动态创建成功')
      backToList()
      loadNewsList()
    } else {
      ElMessage.error(res.message || '操作失败')
    }
  } catch (error) {
    if (error.message) {
      ElMessage.error(error.message)
    }
  } finally {
    submitting.value = false
  }
}
</script>

<style scoped>
.updates-management-container {
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

.header p {
  color: #666;
  font-size: 14px;
}

.card {
  background: white;
  border-radius: 8px;
  padding: 25px;
  margin-bottom: 30px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

/* ===== 列表视图样式 ===== */
.actions-bar {
  display: flex;
  gap: 10px;
  align-items: center;
  padding: 15px 25px;
}

.list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.list-header h2 {
  margin: 0;
  color: #333;
}

.list-stats {
  font-size: 14px;
  color: #666;
}

.loading-state,
.empty-state {
  text-align: center;
  padding: 50px 0;
  color: #909399;
}

.news-table {
  width: 100%;
}

.news-title h4 {
  margin: 0 0 5px 0;
  color: #333;
  font-size: 16px;
  font-weight: 500;
}

.news-subtitle {
  margin: 0;
  color: #666;
  font-size: 14px;
  line-height: 1.4;
}

.action-buttons {
  display: flex;
  gap: 8px;
}

.action-buttons .el-button {
  padding: 5px 10px;
}

/* ===== 表单视图样式 ===== */
.form-header {
  text-align: center;
  margin-bottom: 20px;
}

.form-header h2 {
  color: #333;
  margin: 0 0 10px 0;
}

.form-header p {
  color: #666;
  font-size: 14px;
  margin: 0;
}

.edit-form {
  max-width: 800px;
  margin: 0 auto;
}

.image-uploader {
  width: 100%;
}

.upload-area {
  border: 2px dashed #dcdfe6;
  border-radius: 8px;
  cursor: pointer;
  transition: border-color 0.3s;
  overflow: hidden;
}

.upload-area:hover {
  border-color: #409eff;
}

.upload-placeholder {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 40px;
  color: #999;
}

.upload-placeholder p {
  margin: 10px 0 5px 0;
  font-size: 14px;
}

.upload-placeholder small {
  color: #ccc;
  font-size: 12px;
}

.image-preview {
  position: relative;
  display: inline-block;
}

.image-preview img {
  width: 100%;
  max-width: 400px;
  height: auto;
  display: block;
}

.image-actions {
  position: absolute;
  top: 10px;
  right: 10px;
}

.editor-tip {
  margin-top: 5px;
  color: #909399;
  font-size: 12px;
}

.form-actions {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 30px;
}

.form-actions .el-button {
  padding: 12px 30px;
}

.next-id-info {
  margin-top: 8px;
  padding: 8px 12px;
  background: #f0f9ff;
  border-radius: 4px;
  border: 1px solid #bfdbfe;
}

.next-id-info small {
  color: #1e40af;
  font-weight: 500;
}

/* ===== 响应式设计 ===== */
@media (max-width: 768px) {
  .updates-management-container {
    padding: 15px;
  }

  .card {
    padding: 20px;
  }

  .actions-bar {
    flex-direction: column;
    gap: 15px;
  }

  .list-header {
    flex-direction: column;
    gap: 10px;
    align-items: flex-start;
  }

  .action-buttons {
    flex-direction: column;
    gap: 5px;
  }

  .edit-form {
    max-width: 100%;
  }

  .form-actions {
    flex-direction: column;
    gap: 15px;
  }
}
</style> 