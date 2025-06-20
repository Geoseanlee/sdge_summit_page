<template>
  <div class="join-us">
    <!-- 页面头部 -->
    <div class="page-header">
      <div class="container">
        <h1>加入我们</h1>
        <p>成为可持续发展的倡导者和实践者</p>
      </div>
    </div>

    <!-- 主要内容 -->
    <div class="join-content">
      <div class="container">
        <!-- 参与方式 -->
        <section class="participation-ways">
          <h2>参与方式</h2>
          <div class="ways-grid">
            <div class="way-card">
              <div class="way-icon">🌟</div>
              <h3>成为志愿者</h3>
              <p>参与各种SDG项目，为可持续发展贡献你的力量</p>
              <el-button type="primary" @click="showVolunteerForm = true">
                立即申请
              </el-button>
            </div>
            <div class="way-card">
              <div class="way-icon">💼</div>
              <h3>企业合作</h3>
              <p>与我们建立合作关系，共同推进可持续发展目标</p>
              <el-button @click="openPartnershipForm">
                了解合作
              </el-button>
            </div>
            <div class="way-card">
              <div class="way-icon">🎓</div>
              <h3>学术研究</h3>
              <p>参与SDG相关的学术研究和政策制定</p>
              <el-button @click="openResearchForm">
                参与研究
              </el-button>
            </div>
            <div class="way-card">
              <div class="way-icon">💝</div>
              <h3>捐赠支持</h3>
              <p>通过捐赠支持我们的项目和活动</p>
              <el-button @click="openDonationForm">
                支持我们
              </el-button>
            </div>
          </div>
        </section>

        <!-- 志愿者项目 -->
        <section class="volunteer-projects">
          <h2>志愿者项目</h2>
          <div class="projects-grid">
            <div
              v-for="project in volunteerProjects"
              :key="project.id"
              class="project-card"
            >
              <div class="project-image">
                项目图片
              </div>
              <div class="project-content">
                <h3>{{ project.title }}</h3>
                <p>{{ project.description }}</p>
                <div class="project-details">
                  <div class="project-info">
                    <span class="project-location">📍 {{ project.location }}</span>
                    <span class="project-duration">⏰ {{ project.duration }}</span>
                  </div>
                  <div class="project-volunteers">
                    需要 {{ project.volunteersNeeded }} 名志愿者
                  </div>
                </div>
                <el-button type="primary" size="small" @click="applyProject(project)">
                  申请参加
                </el-button>
              </div>
            </div>
          </div>
        </section>

        <!-- 志愿者故事 -->
        <section class="volunteer-stories">
          <h2>志愿者故事</h2>
          <div class="stories-grid">
            <div
              v-for="story in volunteerStories"
              :key="story.id"
              class="story-card"
            >
              <div class="story-avatar">
                头像
              </div>
              <div class="story-content">
                <h4>{{ story.name }}</h4>
                <p class="story-role">{{ story.role }}</p>
                <p class="story-quote">"{{ story.quote }}"</p>
              </div>
            </div>
          </div>
        </section>
      </div>
    </div>

    <!-- 志愿者申请表单 -->
    <el-dialog
      v-model="showVolunteerForm"
      title="志愿者申请"
      width="600px"
    >
      <el-form
        ref="volunteerFormRef"
        :model="volunteerForm"
        :rules="formRules"
        label-width="100px"
      >
        <el-form-item label="姓名" prop="name">
          <el-input v-model="volunteerForm.name" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="volunteerForm.email" type="email" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="volunteerForm.phone" />
        </el-form-item>
        <el-form-item label="感兴趣的领域" prop="interests">
          <el-checkbox-group v-model="volunteerForm.interests">
            <el-checkbox label="education">教育发展</el-checkbox>
            <el-checkbox label="environment">环境保护</el-checkbox>
            <el-checkbox label="health">健康福祉</el-checkbox>
            <el-checkbox label="poverty">消除贫困</el-checkbox>
            <el-checkbox label="climate">气候行动</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="个人简介" prop="bio">
          <el-input
            v-model="volunteerForm.bio"
            type="textarea"
            rows="4"
            placeholder="请简单介绍一下自己的背景和为什么想成为志愿者"
          />
        </el-form-item>
      </el-form>
      
      <template #footer>
        <el-button @click="showVolunteerForm = false">取消</el-button>
        <el-button type="primary" @click="submitVolunteerForm">提交申请</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'

// 响应式数据
const showVolunteerForm = ref(false)
const volunteerFormRef = ref(null)

// 志愿者申请表单数据
const volunteerForm = ref({
  name: '',
  email: '',
  phone: '',
  interests: [],
  bio: ''
})

// 表单验证规则
const formRules = {
  name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱格式', trigger: 'blur' }
  ],
  phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
  interests: [{ required: true, message: '请选择至少一个感兴趣的领域', trigger: 'change' }],
  bio: [{ required: true, message: '请填写个人简介', trigger: 'blur' }]
}

// 志愿者项目数据
const volunteerProjects = ref([
  {
    id: 1,
    title: '乡村教育支教项目',
    description: '前往偏远地区为当地儿童提供教育支持，帮助改善教育资源不足的问题。',
    location: '贵州山区',
    duration: '2周',
    volunteersNeeded: 10
  },
  {
    id: 2,
    title: '城市环保清洁行动',
    description: '组织城市清洁活动，提高公众环保意识，保护城市环境。',
    location: '北京',
    duration: '1天',
    volunteersNeeded: 50
  },
  {
    id: 3,
    title: '老年人健康关爱',
    description: '为社区老年人提供健康检查和生活照料服务。',
    location: '上海',
    duration: '长期',
    volunteersNeeded: 20
  }
])

// 志愿者故事数据
const volunteerStories = ref([
  {
    id: 1,
    name: '张小明',
    role: '教育志愿者',
    quote: '通过支教项目，我不仅帮助了山区的孩子们，也让自己的人生更有意义。'
  },
  {
    id: 2,
    name: '李小红',
    role: '环保志愿者',
    quote: '每一次环保行动都让我更深刻地理解保护地球的重要性。'
  },
  {
    id: 3,
    name: '王小强',
    role: '健康志愿者',
    quote: '帮助老年人改善健康状况，是我最快乐的事情。'
  }
])

// 方法
const submitVolunteerForm = async () => {
  try {
    await volunteerFormRef.value.validate()
    ElMessage.success('申请提交成功！我们会尽快与您联系。')
    showVolunteerForm.value = false
    volunteerForm.value = {
      name: '',
      email: '',
      phone: '',
      interests: [],
      bio: ''
    }
  } catch (error) {
    ElMessage.error('请检查表单信息')
  }
}

const applyProject = (project) => {
  ElMessage.info(`申请参加项目：${project.title}`)
}

const openPartnershipForm = () => {
  ElMessage.info('企业合作表单')
}

const openResearchForm = () => {
  ElMessage.info('学术研究申请表单')
}

const openDonationForm = () => {
  ElMessage.info('捐赠支持页面')
}
</script>

<style scoped>
.join-us {
  min-height: 100vh;
  background-color: #f8f9fa;
}

.page-header {
  background: linear-gradient(135deg, #8b5cf6 0%, #7c3aed 100%);
  color: white;
  padding: 60px 0;
  text-align: center;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

.page-header h1 {
  font-size: 3rem;
  margin-bottom: 1rem;
  font-weight: 700;
}

.page-header p {
  font-size: 1.2rem;
  opacity: 0.9;
}

.join-content {
  padding: 60px 0;
}

section {
  margin-bottom: 60px;
}

section h2 {
  font-size: 2rem;
  font-weight: 600;
  margin-bottom: 2rem;
  color: #333;
  text-align: center;
}

.ways-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 24px;
}

.way-card {
  background: white;
  padding: 32px;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s ease;
}

.way-card:hover {
  transform: translateY(-8px);
}

.way-icon {
  font-size: 4rem;
  margin-bottom: 16px;
}

.way-card h3 {
  font-size: 1.3rem;
  font-weight: 600;
  margin-bottom: 12px;
  color: #333;
}

.way-card p {
  color: #666;
  line-height: 1.6;
  margin-bottom: 20px;
}

.projects-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));
  gap: 24px;
}

.project-card {
  background: white;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
}

.project-card:hover {
  transform: translateY(-4px);
}

.project-image {
  height: 200px;
  background: #ddd;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
}

.project-content {
  padding: 24px;
}

.project-content h3 {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 12px;
  color: #333;
}

.project-content p {
  color: #666;
  line-height: 1.6;
  margin-bottom: 16px;
}

.project-details {
  margin-bottom: 16px;
}

.project-info {
  display: flex;
  gap: 16px;
  font-size: 0.9rem;
  color: #888;
  margin-bottom: 8px;
}

.project-volunteers {
  font-size: 0.9rem;
  color: #666;
  font-weight: 500;
}

.stories-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 24px;
}

.story-card {
  background: white;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  gap: 16px;
}

.story-avatar {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  background: #ddd;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
  flex-shrink: 0;
}

.story-content h4 {
  font-size: 1.1rem;
  font-weight: 600;
  margin-bottom: 4px;
  color: #333;
}

.story-role {
  font-size: 0.9rem;
  color: #888;
  margin-bottom: 12px;
}

.story-quote {
  color: #666;
  line-height: 1.6;
  font-style: italic;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .page-header h1 {
    font-size: 2rem;
  }
  
  .ways-grid,
  .projects-grid {
    grid-template-columns: 1fr;
  }
  
  .story-card {
    flex-direction: column;
    text-align: center;
  }
}
</style> 