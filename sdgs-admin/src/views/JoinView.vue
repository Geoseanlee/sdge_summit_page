<template>
  <div class="joinus-management-container">
    <div class="header">
      <h1>加入我们页面管理</h1>
      <p>管理“加入我们”页面的 Banner、模块标题、项目岗位信息和入选名单。</p>
    </div>

    <el-form ref="formRef" :model="formData" label-width="120px" class="joinus-form">
      <!-- Banner 区域 -->
      <div class="form-section card">
        <h2>Banner 区域</h2>
        <el-form-item label="Banner 图片">
          <div class="image-upload-container">
            <el-upload
              :show-file-list="false"
              :before-upload="beforeImageUpload"
              :http-request="(options) => handleImageUpload(options, 'banner')"
              accept="image/*"
              class="image-uploader"
            >
              <div class="upload-trigger">
                <el-button type="primary">选择图片</el-button>
                <p class="upload-tip">支持 JPG、PNG 格式，最大 10MB</p>
              </div>
            </el-upload>
            <div v-if="bannerItem.imageUrl" class="image-preview">
              <el-image :src="bannerItem.imageUrl" style="width: 300px; height: 150px;" fit="cover" />
            </div>
          </div>
        </el-form-item>
      </div>

      <!-- 模块一：项目岗位信息 -->
      <div class="form-section card">
        <h2>项目岗位信息</h2>
        <el-form-item label="模块标题">
          <el-input v-model="projectTitleItem.title" placeholder="请输入项目岗位信息模块标题" />
        </el-form-item>
        <div v-for="(item, index) in projectItems" :key="item.localKey" class="project-card">
          <div class="project-header">
            <h3>岗位 {{ index + 1 }}</h3>
            <el-button type="danger" @click="removeProject(index)">删除</el-button>
          </div>
          <el-form-item label="标题">
            <el-input v-model="item.title" placeholder="请输入岗位标题" />
          </el-form-item>
          <el-form-item label="描述">
            <el-input type="textarea" rows="2" v-model="item.description" placeholder="请输入岗位描述" />
          </el-form-item>
          <el-form-item label="报名链接">
            <el-input v-model="item.linkUrl" placeholder="请输入报名链接" />
          </el-form-item>
        </div>
        <el-button type="primary" @click="addProject">添加岗位</el-button>
      </div>

      <!-- 模块二：入选名单 -->
      <div class="form-section card">
        <h2>入选名单</h2>
        <el-form-item label="模块标题">
          <el-input v-model="memberTitleItem.title" placeholder="请输入入选名单模块标题" />
        </el-form-item>
        <div v-for="(item, index) in memberItems" :key="item.localKey" class="member-card">
          <div class="member-header">
            <h3>卡片 {{ index + 1 }}</h3>
            <el-button type="danger" @click="removeMember(index)">删除</el-button>
          </div>
          <el-form-item label="标题">
            <el-input v-model="item.title" placeholder="请输入标题" />
          </el-form-item>
          <el-form-item label="图片">
            <div class="image-upload-container">
              <el-upload
                :show-file-list="false"
                :before-upload="beforeImageUpload"
                :http-request="(options) => handleImageUpload(options, item)"
                accept="image/*"
                class="image-uploader"
              >
                <div class="upload-trigger">
                  <el-button type="primary">选择图片</el-button>
                  <p class="upload-tip">支持 JPG、PNG 格式，最大 10MB</p>
                </div>
              </el-upload>
              <div v-if="item.imageUrl" class="image-preview">
                <el-image :src="item.imageUrl" style="width: 300px; height: 150px;" fit="cover" />
              </div>
            </div>
          </el-form-item>
        </div>
        <el-button type="primary" @click="addMember">添加卡片</el-button>
      </div>

      <!-- 模块三：食品奖项 -->
      <div class="form-section card">
        <h2>食品奖项</h2>
        <div v-for="(item, index) in foodAwardItems" :key="item.localKey" class="member-card">
          <div class="member-header">
            <h3>奖项 {{ index + 1 }}</h3>
            <el-button type="danger" @click="removeFoodAward(index)">删除</el-button>
          </div>
          <el-form-item label="标题">
            <el-input v-model="item.title" placeholder="请输入食品奖项标题" />
          </el-form-item>
          <el-form-item label="图片">
            <div class="image-upload-container">
              <el-upload
                :show-file-list="false"
                :before-upload="beforeImageUpload"
                :http-request="(options) => handleImageUpload(options, item)"
                accept="image/*"
                class="image-uploader"
              >
                <div class="upload-trigger">
                  <el-button type="primary">选择图片</el-button>
                  <p class="upload-tip">支持 JPG、PNG 格式，最大 10MB</p>
                </div>
              </el-upload>
              <div v-if="item.imageUrl" class="image-preview">
                <el-image :src="item.imageUrl" style="width: 300px; height: 150px;" fit="cover" />
              </div>
            </div>
          </el-form-item>
        </div>
        <el-button type="primary" @click="addFoodAward">添加食品奖项</el-button>
      </div>

      <!-- 统一保存按钮 -->
      <div class="form-actions">
        <el-button type="primary" @click="saveAll" :loading="saving">保存整个页面</el-button>
        <el-button @click="loadData">重新加载</el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from 'axios'
import { uploadImage } from '@/api'

const saving = ref(false)
const bannerItem = reactive({ id: null, imageUrl: '' })

// 标题数据
const projectTitleItem = reactive({ id: null, category: 'projectTitle', title: '项目岗位信息' })
const memberTitleItem = reactive({ id: null, category: 'memberTitle', title: '入选名单' })

// 模块数据
const projectItems = ref([])
const memberItems = ref([])
const foodAwardItems = ref([])

const beforeImageUpload = (file) => {
  const isImage = file.type.startsWith('image/')
  if (!isImage) ElMessage.error('只能上传图片文件!')
  return isImage
}

const handleImageUpload = async (options, itemOrField) => {
  const res = await uploadImage(options.file)
  if (res.code === 200) {
    if (itemOrField === 'banner') {
      bannerItem.imageUrl = res.data.fileUrl
    } else {
      itemOrField.imageUrl = res.data.fileUrl
    }
    ElMessage.success('图片上传成功')
  }
}

const loadData = async () => {
  const res = await axios.get('http://localhost:8080/api/joinus/list')
  const data = res.data || []

  const banner = data.find(i => i.category === 'banner')
  if (banner) {
    bannerItem.id = banner.id
    bannerItem.imageUrl = banner.imageUrl
  }

  const projectTitle = data.find(i => i.category === 'projectTitle')
  if (projectTitle) {
    projectTitleItem.id = projectTitle.id
    projectTitleItem.title = projectTitle.title
  }

  const memberTitle = data.find(i => i.category === 'memberTitle')
  if (memberTitle) {
    memberTitleItem.id = memberTitle.id
    memberTitleItem.title = memberTitle.title
  }

  projectItems.value = data
    .filter(i => i.category === 'project')
    .map(i => ({ ...i, localKey: crypto.randomUUID() }))

  memberItems.value = data
    .filter(i => i.category === 'member')
    .map(i => ({ ...i, localKey: crypto.randomUUID() }))

  foodAwardItems.value = data
    .filter(i => i.category === 'food_award')
    .map(i => ({ ...i, localKey: crypto.randomUUID() }))

  if (projectItems.value.length === 0) addProject()
  if (memberItems.value.length === 0) addMember()
}

const addProject = () => {
  projectItems.value.push({
    localKey: crypto.randomUUID(),
    title: '',
    description: '',
    linkUrl: '',
    category: 'project'
  })
}

const addMember = () => {
  memberItems.value.push({
    localKey: crypto.randomUUID(),
    title: '',
    imageUrl: '',
    category: 'member'
  })
}

const addFoodAward = () => {
  foodAwardItems.value.push({
    localKey: crypto.randomUUID(),
    title: '',
    imageUrl: '',
    category: 'food_award'
  })
}

const removeProject = async (index) => {
  const item = projectItems.value[index]
  if (item.id) {
    await ElMessageBox.confirm('确认删除该岗位吗？', '提示', { type: 'warning' })
    await axios.delete(`http://localhost:8080/api/joinus/delete/${item.id}`)
  }
  projectItems.value.splice(index, 1)
}

const removeMember = async (index) => {
  const item = memberItems.value[index]
  if (item.id) {
    await ElMessageBox.confirm('确认删除该卡片吗？', '提示', { type: 'warning' })
    await axios.delete(`http://localhost:8080/api/joinus/delete/${item.id}`)
  }
  memberItems.value.splice(index, 1)
}

const removeFoodAward = async (index) => {
  const item = foodAwardItems.value[index]
  if (item.id) {
    await ElMessageBox.confirm('确认删除该奖项吗？', '提示', { type: 'warning' })
    await axios.delete(`http://localhost:8080/api/joinus/delete/${item.id}`)
  }
  foodAwardItems.value.splice(index, 1)
}

const saveAll = async () => {
  saving.value = true

  if (bannerItem.id) {
    await axios.put(`http://localhost:8080/api/joinus/update/${bannerItem.id}`, bannerItem)
  } else {
    await axios.post('http://localhost:8080/api/joinus/add', {
      category: 'banner',
      imageUrl: bannerItem.imageUrl,
      title: 'Banner'
    })
  }

  for (const titleItem of [projectTitleItem, memberTitleItem]) {
    if (titleItem.id) {
      await axios.put(`http://localhost:8080/api/joinus/update/${titleItem.id}`, titleItem)
    } else {
      await axios.post('http://localhost:8080/api/joinus/add', titleItem)
    }
  }

  for (const item of [...projectItems.value, ...memberItems.value, ...foodAwardItems.value]) {
    if (item.id) {
      await axios.put(`http://localhost:8080/api/joinus/update/${item.id}`, item)
    } else {
      await axios.post('http://localhost:8080/api/joinus/add', item)
    }
  }

  ElMessage.success('保存成功')
  loadData()
  saving.value = false
}

onMounted(loadData)
</script>

<style scoped>
.joinus-management-container {
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
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}
.form-section h2 {
  color: #409eff;
  margin-bottom: 20px;
  border-bottom: 2px solid #f0f0f0;
  padding-bottom: 10px;
}
.image-upload-container {
  display: flex;
  align-items: flex-start;
  gap: 20px;
}
.upload-trigger {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.upload-tip {
  font-size: 12px;
  color: #909399;
  margin-top: 5px;
}
.image-preview {
  border: 1px solid #e4e7ed;
  border-radius: 6px;
  padding: 5px;
  background: #fafafa;
}
.project-card,
.member-card {
  border: 1px solid #e4e7ed;
  border-radius: 6px;
  padding: 20px;
  margin-bottom: 20px;
  background: #fafafa;
}
.project-header,
.member-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;
}
.form-actions {
  text-align: center;
  margin-top: 30px;
  padding: 20px;
  background: #f5f7fa;
  border-radius: 8px;
}
</style>
