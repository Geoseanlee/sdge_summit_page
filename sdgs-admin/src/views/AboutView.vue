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

          <el-col :span="24">
            <el-form-item label="Hero图片" prop="heroImg">
              <div class="image-upload-container">
                <el-upload
                  ref="heroUploadRef"
                  :show-file-list="false"
                  :before-upload="beforeImageUpload"
                  :http-request="(options) => handleImageUpload(options, 'heroImg')"
                  accept="image/*"
                  class="image-uploader"
                >
                  <div class="upload-trigger">
                    <el-button type="primary">选择图片</el-button>
                    <p class="upload-tip">支持 JPG、PNG、GIF 格式，最大10MB</p>
                  </div>
                </el-upload>
              </div>
              <div v-if="formData.heroImg" class="image-preview">
                <el-image
                  :src="formData.heroImg"
                  style="width: 200px; height: 120px;"
                  fit="cover"
                />
              </div>
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
        <el-form-item label="区域标题">
          <el-input v-model="formData.advantagesTitle" placeholder="如：三大优势" />
        </el-form-item>
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
                  <el-upload
                    :ref="`advantageUploadRef${index}`"
                    :show-file-list="false"
                    :before-upload="beforeImageUpload"
                    :http-request="(options) => handleImageUpload(options, `advantage-${index}`)"
                    accept="image/*"
                    class="image-uploader"
                  >
                    <div class="upload-trigger">
                      <el-button type="primary">选择图片</el-button>
                      <p class="upload-tip">支持 JPG、PNG、GIF 格式，最大10MB</p>
                    </div>
                  </el-upload>
                </div>
                <div v-if="advantage.image" class="image-preview">
                  <el-image
                    :src="advantage.image"
                    style="width: 200px; height: 120px;"
                    fit="cover"
                  />
                </div>
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
        <div v-for="(stat, index) in stats" :key="index" class="stat-item">
          <div class="stat-header">
            <h3>统计卡 {{ index + 1 }}</h3>
            <el-button type="danger" @click="removeStat(index)" :disabled="stats.length <= 1">
              删除
            </el-button>
          </div>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item :label="`标题`" :prop="`stats.${index}.title`">
                <el-input v-model="stat.title" placeholder="如：覆盖国家" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label="`颜色`" :prop="`stats.${index}.color`">
                <el-select v-model="stat.color" placeholder="选择颜色">
                  <el-option label="蓝色" value="#1890ff" />
                  <el-option label="橙色" value="#fa8c16" />
                  <el-option label="绿色" value="#52c41a" />
                  <el-option label="红色" value="#f5222d" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item :label="`描述`" :prop="`stats.${index}.desc`">
                <el-input
                  v-model="stat.desc"
                  type="textarea"
                  :rows="3"
                  placeholder="如：192 个国家，2500 万人次"
                />
              </el-form-item>
            </el-col>
          </el-row>
        </div>
        <el-button type="primary" @click="addStat">添加统计卡</el-button>
      </div>

      <!-- Logo列表 -->
      <div class="form-section card">
        <h2>Logo列表</h2>

        <!-- 部分平台及媒体资源 -->
        <div class="logo-section">
          <el-form-item label="区域标题">
            <el-input v-model="formData.mediaTitle" placeholder="如：部分平台及媒体资源" />
          </el-form-item>
          <div class="logo-list">
            <div v-for="(logo, index) in mediaLogos" :key="index" class="logo-item">
              <div class="logo-upload-container">
                <el-upload
                  :ref="`mediaLogoUploadRef${index}`"
                  :show-file-list="false"
                  :before-upload="beforeImageUpload"
                  :http-request="(options) => handleLogoUpload(options, 'media', index)"
                  accept="image/*"
                  class="logo-uploader"
                >
                  <div class="logo-upload-trigger">
                    <el-button type="primary" size="small">更换图片</el-button>
                  </div>
                </el-upload>
              </div>
              <div v-if="mediaLogos[index]" class="logo-preview">
                <el-image
                  :src="mediaLogos[index]"
                  style="width: 80px; height: 60px;"
                  fit="contain"
                />
              </div>
            </div>
          </div>
          <el-button type="primary" @click="addLogo('media')">添加Logo</el-button>
        </div>

        <!-- 特别合作伙伴 -->
        <div class="logo-section">
          <el-form-item label="区域标题">
            <el-input v-model="formData.specialTitle" placeholder="如：特别合作伙伴" />
          </el-form-item>
          <div class="logo-list">
            <div v-for="(logo, index) in specialLogos" :key="index" class="logo-item">
              <div class="logo-upload-container">
                <el-upload
                  :ref="`specialLogoUploadRef${index}`"
                  :show-file-list="false"
                  :before-upload="beforeImageUpload"
                  :http-request="(options) => handleLogoUpload(options, 'special', index)"
                  accept="image/*"
                  class="logo-uploader"
                >
                  <div class="logo-upload-trigger">
                    <el-button type="primary" size="small">更换图片</el-button>
                  </div>
                </el-upload>
              </div>
              <div v-if="specialLogos[index]" class="logo-preview">
                <el-image
                  :src="specialLogos[index]"
                  style="width: 80px; height: 60px;"
                  fit="contain"
                />
              </div>
            </div>
          </div>
          <el-button type="primary" @click="addLogo('special')">添加Logo</el-button>
        </div>

        <!-- 合作伙伴 -->
        <div class="logo-section">
          <el-form-item label="区域标题">
            <el-input v-model="formData.partnerTitle" placeholder="如：合作伙伴" />
          </el-form-item>
          <div class="logo-list">
            <div v-for="(logo, index) in partnerLogos" :key="index" class="logo-item">
              <div class="logo-upload-container">
                <el-upload
                  :ref="`partnerLogoUploadRef${index}`"
                  :show-file-list="false"
                  :before-upload="beforeImageUpload"
                  :http-request="(options) => handleLogoUpload(options, 'partner', index)"
                  accept="image/*"
                  class="logo-uploader"
                >
                  <div class="logo-upload-trigger">
                    <el-button type="primary" size="small">更换图片</el-button>
                  </div>
                </el-upload>
              </div>
              <div v-if="partnerLogos[index]" class="logo-preview">
                <el-image
                  :src="partnerLogos[index]"
                  style="width: 80px; height: 60px;"
                  fit="contain"
                />
              </div>
            </div>
          </div>
          <el-button type="primary" @click="addLogo('partner')">添加Logo</el-button>
        </div>
      </div>


      <!-- 操作按钮 -->
      <div class="form-actions">
        <el-button type="primary" @click="saveData" :loading="saving">保存</el-button>
        <el-button @click="loadData">重新加载</el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, computed } from 'vue'
import { ElMessage } from 'element-plus'
import { get, put, uploadImage, deleteImage } from '@/api'
import request from '@/utils/request'

const formRef = ref(null)
const saving = ref(false)
const heroUploadRef = ref(null)

// 表单数据
const formData = reactive({
  id: null,
  locale: 'zh-CN',
  headerTitle: '',
  headerTags: '',
  heroBtnText: '更多', // 固定值
  heroBtnLink: '/about/more', // 固定值
  heroImg: '',
  introHtml: '',
  advantagesTitle: '三大优势', // 新增
  advantagesJson: '',
  statsTitle: '数据统计', // 新增
  statsJson: '',
  mediaTitle: '部分平台及媒体资源', // 新增
  mediaJson: '',
  specialTitle: '特别合作伙伴', // 新增
  specialJson: '',
  partnerTitle: '合作伙伴', // 新增
  partnerJson: '',
  status: 1
})

// Logo区域标题
// const logoSectionTitles = reactive({
//   media: '部分平台及媒体资源',
//   special: '特别合作伙伴',
//   partner: '合作伙伴'
// })

// 三大优势标题
// const advantagesTitle = ref('三大优势')

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

// 存储原始图片URL，用于删除旧图片
const originalImages = ref({
  heroImg: '',
  advantages: [],
  mediaLogos: [],
  specialLogos: [],
  partnerLogos: []
})

// 计算属性：将数据转换为JSON字符串
const advantagesJson = computed(() => JSON.stringify(advantages.value))
const statsJson = computed(() => JSON.stringify(stats.value))
const mediaJson = computed(() => JSON.stringify(mediaLogos.value.filter(logo => logo.trim())))
const specialJson = computed(() => JSON.stringify(specialLogos.value.filter(logo => logo.trim())))
const partnerJson = computed(() => JSON.stringify(partnerLogos.value.filter(logo => logo.trim())))

onMounted(() => {
  loadData()
})

// 加载数据 - 使用完整的API路径
const loadData = async () => {
  try {
    // 使用完整的API路径，让Vite代理处理
    const res = await request({
      url: '/api/public/about-overview',
      method: 'get',
      params: { locale: formData.locale }
    })

    if (res.code === 200 && res.data) {
      const data = res.data
      Object.assign(formData, data)

      // 强制设置固定值，不允许用户修改
      formData.heroBtnText = '更多'
      formData.heroBtnLink = '/about/more' // 修改为内部路由路径

      // 设置默认标题（如果数据库中没有值）
      if (!formData.advantagesTitle) formData.advantagesTitle = '三大优势'
      if (!formData.statsTitle) formData.statsTitle = '数据统计'
      if (!formData.mediaTitle) formData.mediaTitle = '部分平台及媒体资源'
      if (!formData.specialTitle) formData.specialTitle = '特别合作伙伴'
      if (!formData.partnerTitle) formData.partnerTitle = '合作伙伴'

      // 保存原始图片URL
      originalImages.value.heroImg = data.heroImg || ''

      // 解析JSON数据
      if (data.advantagesJson) {
        try {
          advantages.value = JSON.parse(data.advantagesJson)
          // 保存原始优势图片URL
          originalImages.value.advantages = advantages.value.map(item => item.image || '')
        } catch {
          advantages.value = [{ image: '', title: '', desc: '' }]
          originalImages.value.advantages = ['']
        }
      }

      if (data.statsJson) {
        try {
          stats.value = JSON.parse(data.statsJson)
        } catch {
          stats.value = [{ color: '#4A9AD4', title: '', desc: '' }]
        }
      }

      if (data.mediaJson) {
        try {
          mediaLogos.value = JSON.parse(data.mediaJson)
          originalImages.value.mediaLogos = [...mediaLogos.value]
        } catch {
          mediaLogos.value = ['']
          originalImages.value.mediaLogos = ['']
        }
      }

      if (data.specialJson) {
        try {
          specialLogos.value = JSON.parse(data.specialJson)
          originalImages.value.specialLogos = [...specialLogos.value]
        } catch {
          specialLogos.value = ['']
          originalImages.value.specialLogos = ['']
        }
      }

      if (data.partnerJson) {
        try {
          partnerLogos.value = JSON.parse(data.partnerJson)
          originalImages.value.partnerLogos = [...partnerLogos.value]
        } catch {
          partnerLogos.value = ['']
          originalImages.value.partnerLogos = ['']
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

// 图片上传前的验证
const beforeImageUpload = (file) => {
  const isImage = file.type.startsWith('image/')
  const isLt10M = file.size / 1024 / 1024 < 10

  if (!isImage) {
    ElMessage.error('只能上传图片文件!')
    return false
  }
  if (!isLt10M) {
    ElMessage.error('图片大小不能超过 10MB!')
    return false
  }
  return true
}

// 处理图片上传 - 使用完整的API路径
const handleImageUpload = async (options, field) => {
  try {
    const res = await uploadImage(options.file)
    if (res.code === 200) {
      // 从阿里云OSS响应中获取文件URL
      const newImageUrl = res.data.fileUrl

      // 删除旧图片
      await deleteOldImage(field)

      // 更新图片URL
      if (field === 'heroImg') {
        formData.heroImg = newImageUrl
      } else if (field.startsWith('advantage-')) {
        const index = parseInt(field.split('-')[1])
        advantages.value[index].image = newImageUrl
      }

      ElMessage.success('图片上传成功')
    } else {
      ElMessage.error(`上传失败：${res.message}`)
    }
  } catch (error) {
    ElMessage.error(`上传失败：${error.message || '网络错误'}`)
  }
}

// 处理Logo上传 - 使用完整的API路径
const handleLogoUpload = async (options, type, index) => {
  try {
    const res = await uploadImage(options.file)
    if (res.code === 200) {
      // 从阿里云OSS响应中获取文件URL
      const newImageUrl = res.data.fileUrl

      // 删除旧Logo
      await deleteOldLogo(type, index)

      // 更新Logo URL
      switch (type) {
        case 'media':
          mediaLogos.value[index] = newImageUrl
          break
        case 'special':
          specialLogos.value[index] = newImageUrl
          break
        case 'partner':
          partnerLogos.value[index] = newImageUrl
          break
      }

      ElMessage.success('Logo上传成功')
    } else {
      ElMessage.error(`上传失败：${res.message}`)
    }
  } catch (error) {
    ElMessage.error(`上传失败：${error.message || '网络错误'}`)
  }
}

// 删除旧图片
const deleteOldImage = async (field) => {
  try {
    let oldImageUrl = ''

    if (field === 'heroImg') {
      oldImageUrl = originalImages.value.heroImg
    } else if (field.startsWith('advantage-')) {
      const index = parseInt(field.split('-')[1])
      oldImageUrl = originalImages.value.advantages[index] || ''
    }

    if (oldImageUrl && oldImageUrl.startsWith('http')) {
      await deleteImage(oldImageUrl)
      console.log('旧图片删除成功:', oldImageUrl)
    }
  } catch (error) {
    console.error('删除旧图片失败:', error)
  }
}

// 删除旧Logo
const deleteOldLogo = async (type, index) => {
  try {
    let oldLogoUrl = ''

    switch (type) {
      case 'media':
        oldLogoUrl = originalImages.value.mediaLogos[index] || ''
        break
      case 'special':
        oldLogoUrl = originalImages.value.specialLogos[index] || ''
        break
      case 'partner':
        oldLogoUrl = originalImages.value.partnerLogos[index] || ''
        break
    }

    if (oldLogoUrl && oldLogoUrl.startsWith('http')) {
      await deleteImage(oldLogoUrl)
      console.log('旧Logo删除成功:', oldLogoUrl)
    }
  } catch (error) {
    console.error('删除旧Logo失败:', error)
  }
}

// 保存数据 - 使用完整的API路径
const saveData = async () => {
  try {
    await formRef.value.validate()

    saving.value = true

    // 强制设置固定值，确保保存时也是正确的
    formData.heroBtnText = '更多'
    formData.heroBtnLink = '/about/more' // 修改为内部路由路径

    // 更新JSON字段
    formData.advantagesJson = advantagesJson.value
    formData.statsJson = statsJson.value
    formData.mediaJson = mediaJson.value
    formData.specialJson = specialJson.value
    formData.partnerJson = partnerJson.value

    // 使用完整的API路径
    const res = await request({
      url: `/api/admin/about-overview/${formData.id || 1}`,
      method: 'put',
      data: formData
    })

    if (res.code === 200) {
      ElMessage.success('保存成功')
      // 更新原始图片URL
      originalImages.value.heroImg = formData.heroImg
      originalImages.value.advantages = advantages.value.map(item => item.image || '')
      originalImages.value.mediaLogos = [...mediaLogos.value]
      originalImages.value.specialLogos = [...specialLogos.value]
      originalImages.value.partnerLogos = [...partnerLogos.value]
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

// 三大优势操作
const addAdvantage = () => {
  advantages.value.push({ image: '', title: '', desc: '' })
  originalImages.value.advantages.push('')
}

// 修改 removeAdvantage 函数，移除删除图片的逻辑
const removeAdvantage = (index) => {
  // 不再删除优势图片，因为上传新图片时会自动删除旧图片
  advantages.value.splice(index, 1)
  originalImages.value.advantages.splice(index, 1)
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
      originalImages.value.mediaLogos.push('')
      break
    case 'special':
      specialLogos.value.push('')
      originalImages.value.specialLogos.push('')
      break
    case 'partner':
      partnerLogos.value.push('')
      originalImages.value.partnerLogos.push('')
      break
  }
}

const removeLogo = async (type, index) => {
  try {
    let logoUrl = ''

    switch (type) {
      case 'media':
        logoUrl = mediaLogos.value[index]
        mediaLogos.value.splice(index, 1)
        originalImages.value.mediaLogos.splice(index, 1)
        break
      case 'special':
        logoUrl = specialLogos.value[index]
        specialLogos.value.splice(index, 1)
        originalImages.value.specialLogos.splice(index, 1)
        break
      case 'partner':
        logoUrl = partnerLogos.value[index]
        partnerLogos.value.splice(index, 1)
        originalImages.value.partnerLogos.splice(index, 1)
        break
    }

    if (logoUrl && logoUrl.startsWith('http')) {
      await deleteImage(logoUrl)
      ElMessage.success('Logo删除成功')
    }
  } catch (error) {
    ElMessage.error(`删除Logo失败：${error.message || '网络错误'}`)
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
  margin-bottom: 15px;
}

.image-uploader {
  display: inline-block;
}

.upload-trigger {
  text-align: center;
}

.upload-tip {
  font-size: 12px;
  color: #909399;
  margin-top: 5px;
}

.image-preview {
  margin-top: 15px;
  border: 1px solid #e4e7ed;
  border-radius: 6px;
  padding: 10px;
  background: #fafafa;
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
  border: 1px solid #e4e7ed;
  border-radius: 6px;
  padding: 20px;
  background: #fafafa;
}

.logo-list {
  margin-bottom: 15px;
}

.logo-item {
  display: flex;
  gap: 10px;
  align-items: center;
  margin-bottom: 15px;
  padding: 10px;
  border: 1px solid #e4e7ed;
  border-radius: 4px;
  background: white;
}

.logo-upload-container {
  min-width: 100px;
}

.logo-uploader {
  display: inline-block;
}

.logo-upload-trigger {
  text-align: center;
}

.logo-preview {
  border: 1px solid #e4e7ed;
  border-radius: 4px;
  padding: 5px;
  background: white;
}

.field-tip {
  font-size: 12px;
  color: #909399;
  margin-top: 5px;
  font-style: italic;
}

.form-actions {
  text-align: center;
  margin-top: 30px;
  padding: 20px;
  background: #f5f7fa;
  border-radius: 8px;
}
</style>
