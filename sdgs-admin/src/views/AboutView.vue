<template>
  <div class="about-management-container">
    <div class="header">
      <h1>关于我们页面管理</h1>
      <p>管理"关于我们"页面的所有内容，包括标题、介绍、优势展示、统计数据等。</p>
    </div>

    <el-form
      ref="formRef"
      :model="formData"
      :rules="rules"
      label-width="120px"
      class="about-form"
    >
      <!-- 基本信息 -->
      <div class="form-section card">
        <h2>基本信息</h2>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="语言" prop="locale">
              <el-select v-model="formData.locale" placeholder="选择语言">
                <el-option label="中文" value="zh-CN" />
                <el-option label="English" value="en-US" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="formData.status">
                <el-radio :label="1">已发布</el-radio>
                <el-radio :label="0">草稿</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- Hero区域 -->
      <div class="form-section card">
        <h2>Hero区域</h2>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="页面主标题" prop="headerTitle">
              <el-input v-model="formData.headerTitle" placeholder="请输入页面主标题" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="顶部标签" prop="headerTags">
              <el-input
                v-model="formData.headerTags"
                placeholder="请输入标签，用逗号分隔，如：中国品牌，走向世界,中国文化，世界传播"
                type="textarea"
                :rows="2"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="按钮文字" prop="heroBtnText">
              <el-input v-model="formData.heroBtnText" placeholder="如：更多" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="按钮链接" prop="heroBtnLink">
              <el-input v-model="formData.heroBtnLink" placeholder="如：# 或 https://..." />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="Hero图片" prop="heroImg">
              <div class="image-upload-container">
                <el-input v-model="formData.heroImg" placeholder="请输入图片URL" />
                <el-button type="primary" @click="selectImage('heroImg')">选择图片</el-button>
              </div>
              <el-image
                v-if="formData.heroImg"
                :src="formData.heroImg"
                style="width: 200px; height: 120px; margin-top: 10px;"
                fit="cover"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- 引言段落 -->
      <div class="form-section card">
        <h2>引言段落</h2>
        <el-form-item label="引言内容" prop="introHtml">
          <el-input
            v-model="formData.introHtml"
            type="textarea"
            :rows="6"
            placeholder="请输入引言段落内容，支持HTML格式"
          />
        </el-form-item>
      </div>

      <!-- 三大优势 -->
      <div class="form-section card">
        <h2>三大优势</h2>
        <div v-for="(advantage, index) in advantages" :key="index" class="advantage-item">
          <div class="advantage-header">
            <h3>优势 {{ index + 1 }}</h3>
            <el-button type="danger" @click="removeAdvantage(index)" :disabled="advantages.length <= 1">
              删除
            </el-button>
          </div>
          <el-row :gutter="20">
            <el-col :span="24">
              <el-form-item :label="`优势${index + 1}图片`">
                <div class="image-upload-container">
                  <el-input v-model="advantage.image" placeholder="请输入图片URL" />
                  <el-button type="primary" @click="selectImage(`advantage-${index}`)">选择图片</el-button>
                </div>
                <el-image
                  v-if="advantage.image"
                  :src="advantage.image"
                  style="width: 200px; height: 120px; margin-top: 10px;"
                  fit="cover"
                />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item :label="`优势${index + 1}标题`">
                <el-input v-model="advantage.title" placeholder="请输入标题" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item :label="`优势${index + 1}描述`">
                <el-input
                  v-model="advantage.desc"
                  type="textarea"
                  :rows="3"
                  placeholder="请输入描述内容"
                />
              </el-form-item>
            </el-col>
          </el-row>
        </div>
        <el-button type="primary" @click="addAdvantage" :disabled="advantages.length >= 5">
          添加优势
        </el-button>
      </div>

      <!-- 四色统计卡 -->
      <div class="form-section card">
        <h2>四色统计卡</h2>
        <div v-for="(stat, index) in stats" :key="index" class="stat-item">
          <div class="stat-header">
            <h3>统计卡 {{ index + 1 }}</h3>
            <el-button type="danger" @click="removeStat(index)" :disabled="stats.length <= 1">
              删除
            </el-button>
          </div>
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item :label="`统计卡${index + 1}颜色`">
                <el-color-picker v-model="stat.color" />
              </el-form-item>
            </el-col>
            <el-col :span="18">
              <el-form-item :label="`统计卡${index + 1}标题`">
                <el-input v-model="stat.title" placeholder="如：覆盖国家" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item :label="`统计卡${index + 1}描述`">
                <el-input
                  v-model="stat.desc"
                  type="textarea"
                  :rows="2"
                  placeholder="如：192 个国家<br>2500 万人次"
                />
              </el-form-item>
            </el-col>
          </el-row>
        </div>
        <el-button type="primary" @click="addStat" :disabled="stats.length >= 6">
          添加统计卡
        </el-button>
      </div>

      <!-- Logo列表 -->
      <div class="form-section card">
        <h2>Logo列表</h2>

        <!-- 部分平台及媒体资源 -->
        <div class="logo-section">
          <h3>部分平台及媒体资源</h3>
          <div class="logo-list">
            <div v-for="(logo, index) in mediaLogos" :key="index" class="logo-item">
              <el-input v-model="mediaLogos[index]" placeholder="请输入Logo URL" />
              <el-button type="danger" @click="removeLogo('media', index)">删除</el-button>
            </div>
          </div>
          <el-button type="primary" @click="addLogo('media')">添加Logo</el-button>
        </div>

        <!-- 特别合作伙伴 -->
        <div class="logo-section">
          <h3>特别合作伙伴</h3>
          <div class="logo-list">
            <div v-for="(logo, index) in specialLogos" :key="index" class="logo-item">
              <el-input v-model="specialLogos[index]" placeholder="请输入Logo URL" />
              <el-button type="danger" @click="removeLogo('special', index)">删除</el-button>
            </div>
          </div>
          <el-button type="primary" @click="addLogo('special')">添加Logo</el-button>
        </div>

        <!-- 合作伙伴 -->
        <div class="logo-section">
          <h3>合作伙伴</h3>
          <div class="logo-list">
            <div v-for="(logo, index) in partnerLogos" :key="index" class="logo-item">
              <el-input v-model="partnerLogos[index]" placeholder="请输入Logo URL" />
              <el-button type="danger" @click="removeLogo('partner', index)">删除</el-button>
            </div>
          </div>
          <el-button type="primary" @click="addLogo('partner')">添加Logo</el-button>
        </div>
      </div>

      <!-- 操作按钮 -->
      <div class="form-actions">
        <el-button type="primary" @click="saveData" :loading="saving">保存</el-button>
        <el-button @click="loadData">重新加载</el-button>
        <el-button @click="previewData">预览</el-button>
      </div>
    </el-form>

    <!-- 图片选择对话框 -->
    <el-dialog v-model="imageDialogVisible" title="选择图片" width="80%">
      <div class="image-selector">
        <div class="image-grid">
          <div
            v-for="image in imageList"
            :key="image.fileUrl"
            class="image-select-item"
            @click="selectImageFromList(image.fileUrl)"
          >
            <el-image :src="image.fileUrl" fit="cover" />
          </div>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, computed } from 'vue'
import { ElMessage} from 'element-plus'
import { get, put, listImages } from '@/api'

const formRef = ref(null)
const saving = ref(false)
const imageDialogVisible = ref(false)
const imageList = ref([])
const currentImageField = ref('')

// 表单数据
const formData = reactive({
  id: null,
  locale: 'zh-CN',
  headerTitle: '',
  headerTags: '',
  heroBtnText: '',
  heroBtnLink: '',
  heroImg: '',
  introHtml: '',
  advantagesJson: '',
  statsJson: '',
  mediaJson: '',
  specialJson: '',
  partnerJson: '',
  status: 1
})

// 表单验证规则
const rules = {
  locale: [{ required: true, message: '请选择语言', trigger: 'change' }],
  headerTitle: [{ required: true, message: '请输入页面主标题', trigger: 'blur' }],
  status: [{ required: true, message: '请选择状态', trigger: 'change' }]
}

// 三大优势数据
const advantages = ref([
  { image: '', title: '', desc: '' }
])

// 四色统计卡数据
const stats = ref([
  { color: '#4A9AD4', title: '', desc: '' }
])

// Logo列表数据
const mediaLogos = ref([''])
const specialLogos = ref([''])
const partnerLogos = ref([''])

// 计算属性：将数据转换为JSON字符串
const advantagesJson = computed(() => JSON.stringify(advantages.value))
const statsJson = computed(() => JSON.stringify(stats.value))
const mediaJson = computed(() => JSON.stringify(mediaLogos.value.filter(logo => logo.trim())))
const specialJson = computed(() => JSON.stringify(specialLogos.value.filter(logo => logo.trim())))
const partnerJson = computed(() => JSON.stringify(partnerLogos.value.filter(logo => logo.trim())))

onMounted(() => {
  loadData()
  loadImageList()
})

// 加载数据
const loadData = async () => {
  try {
    const res = await get('/api/public/about-overview', { locale: formData.locale })
    if (res.code === 200 && res.data) {
      const data = res.data
      Object.assign(formData, data)

      // 解析JSON数据
      if (data.advantagesJson) {
        try {
          advantages.value = JSON.parse(data.advantagesJson)
        } catch {
          advantages.value = [{ image: '', title: '', desc: '' }]
        }
      }

      if (data.statsJson) {
        try {
          stats.value = JSON.parse(data.statsJson)
        } catch  {
          stats.value = [{ color: '#4A9AD4', title: '', desc: '' }]
        }
      }

      if (data.mediaJson) {
        try {
          mediaLogos.value = JSON.parse(data.mediaJson)
        } catch  {
          mediaLogos.value = ['']
        }
      }

      if (data.specialJson) {
        try {
          specialLogos.value = JSON.parse(data.specialJson)
        } catch  {
          specialLogos.value = ['']
        }
      }

      if (data.partnerJson) {
        try {
          partnerLogos.value = JSON.parse(data.partnerJson)
        } catch  {
          partnerLogos.value = ['']
        }
      }

      ElMessage.success('数据加载成功')
    } else {
      ElMessage.warning('暂无数据，请先创建')
    }
  } catch (error) {
    ElMessage.error(`加载数据失败：${error.message || '网络错误'}`)
  }
}

// 保存数据
const saveData = async () => {
  try {
    await formRef.value.validate()

    saving.value = true

    // 更新JSON字段
    formData.advantagesJson = advantagesJson.value
    formData.statsJson = statsJson.value
    formData.mediaJson = mediaJson.value
    formData.specialJson = specialJson.value
    formData.partnerJson = partnerJson.value

    const res = await put(`/api/admin/about-overview/${formData.id || 1}`, formData)
    if (res.code === 200) {
      ElMessage.success('保存成功')
      loadData() // 重新加载数据
    } else {
      ElMessage.error(`保存失败：${res.message}`)
    }
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error(`保存失败：${error.message || '网络错误'}`)
    }
  } finally {
    saving.value = false
  }
}

// 预览数据
const previewData = () => {
  console.log('预览数据：', {
    formData,
    advantages: advantages.value,
    stats: stats.value,
    mediaLogos: mediaLogos.value,
    specialLogos: specialLogos.value,
    partnerLogos: partnerLogos.value
  })
  ElMessage.info('数据预览已输出到控制台')
}

// 加载图片列表
const loadImageList = async () => {
  try {
    const res = await listImages({ maxKeys: 100 })
    if (res.code === 200) {
      imageList.value = res.data.files || []
    }
  } catch (error) {
    console.error('加载图片列表失败：', error)
  }
}

// 选择图片
const selectImage = (field) => {
  currentImageField.value = field
  imageDialogVisible.value = true
}

// 从图片列表选择
const selectImageFromList = (imageUrl) => {
  if (currentImageField.value === 'heroImg') {
    formData.heroImg = imageUrl
  } else if (currentImageField.value.startsWith('advantage-')) {
    const index = parseInt(currentImageField.value.split('-')[1])
    advantages.value[index].image = imageUrl
  }
  imageDialogVisible.value = false
}

// 三大优势操作
const addAdvantage = () => {
  advantages.value.push({ image: '', title: '', desc: '' })
}

const removeAdvantage = (index) => {
  advantages.value.splice(index, 1)
}

// 统计卡操作
const addStat = () => {
  stats.value.push({ color: '#4A9AD4', title: '', desc: '' })
}

const removeStat = (index) => {
  stats.value.splice(index, 1)
}

// Logo操作
const addLogo = (type) => {
  switch (type) {
    case 'media':
      mediaLogos.value.push('')
      break
    case 'special':
      specialLogos.value.push('')
      break
    case 'partner':
      partnerLogos.value.push('')
      break
  }
}

const removeLogo = (type, index) => {
  switch (type) {
    case 'media':
      mediaLogos.value.splice(index, 1)
      break
    case 'special':
      specialLogos.value.splice(index, 1)
      break
    case 'partner':
      partnerLogos.value.splice(index, 1)
      break
  }
}
</script>

<style scoped>
.about-management-container {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
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

.form-section h2 {
  color: #409eff;
  margin-bottom: 20px;
  border-bottom: 2px solid #f0f0f0;
  padding-bottom: 10px;
}

.image-upload-container {
  display: flex;
  gap: 10px;
  align-items: center;
}

.advantage-item, .stat-item {
  border: 1px solid #e4e7ed;
  border-radius: 6px;
  padding: 20px;
  margin-bottom: 20px;
  background: #fafafa;
}

.advantage-header, .stat-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.advantage-header h3, .stat-header h3 {
  margin: 0;
  color: #606266;
}

.logo-section {
  margin-bottom: 30px;
}

.logo-section h3 {
  color: #606266;
  margin-bottom: 15px;
}

.logo-list {
  margin-bottom: 15px;
}

.logo-item {
  display: flex;
  gap: 10px;
  align-items: center;
  margin-bottom: 10px;
}

.form-actions {
  text-align: center;
  margin-top: 30px;
  padding: 20px;
  background: #f5f7fa;
  border-radius: 8px;
}

.image-selector {
  max-height: 400px;
  overflow-y: auto;
}

.image-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
  gap: 15px;
}

.image-select-item {
  cursor: pointer;
  border: 2px solid transparent;
  border-radius: 6px;
  overflow: hidden;
  transition: border-color 0.3s;
}

.image-select-item:hover {
  border-color: #409eff;
}

.image-select-item .el-image {
  width: 100%;
  height: 100px;
}
</style>
