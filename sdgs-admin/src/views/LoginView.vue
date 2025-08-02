<template>
  <div class="login-container">
    <div class="login-box">
      <div class="login-title">
        <h2>{{ isChangePasswordMode ? '修改密码' : '登录' }}</h2>
      </div>

      <!-- 登录模式 -->
      <el-form v-if="!isChangePasswordMode" ref="loginFormRef" :model="loginForm" :rules="loginRules" class="login-form"
        @submit.prevent="handleLogin">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" placeholder="请输入账号" size="large" prefix-icon="User" clearable />
        </el-form-item>

        <el-form-item prop="password">
          <el-input v-model="loginForm.password" type="password" placeholder="请输入密码" size="large" prefix-icon="Lock"
            show-password clearable @keyup.enter="handleLogin" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" size="large" :loading="loading" class="login-btn" @click="handleLogin">
            登录
          </el-button>
        </el-form-item>

        <div class="login-footer">
          <el-link type="primary" @click="handleChangePassword">
            修改密码
          </el-link>
        </div>
      </el-form>

      <!-- 修改密码模式 -->
      <el-form v-else ref="changePasswordFormRef" :model="changePasswordForm" :rules="changePasswordRules"
        class="login-form" @submit.prevent="handleChangePasswordSubmit">
        <el-form-item prop="adminUsername">
          <el-input v-model="changePasswordForm.adminUsername" placeholder="请输入超级管理员账号" size="large"
            prefix-icon="UserFilled" clearable />
        </el-form-item>

        <el-form-item prop="adminPassword">
          <el-input v-model="changePasswordForm.adminPassword" type="password" placeholder="请输入超级管理员密码" size="large"
            prefix-icon="Lock" show-password clearable />
        </el-form-item>

        <el-form-item prop="targetUsername">
          <el-input v-model="changePasswordForm.targetUsername" placeholder="请输入要修改的账号" size="large" prefix-icon="User"
            clearable />
        </el-form-item>

        <el-form-item prop="newPassword">
          <el-input v-model="changePasswordForm.newPassword" type="password" placeholder="请输入新密码" size="large"
            prefix-icon="Key" show-password clearable @keyup.enter="handleChangePasswordSubmit" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" size="large" :loading="loading" class="login-btn"
            @click="handleChangePasswordSubmit">
            修改密码
          </el-button>
        </el-form-item>

        <div class="login-footer">
          <el-link type="primary" @click="backToLogin">
            返回登录
          </el-link>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { User, Lock, UserFilled, Key } from '@element-plus/icons-vue'

// 路由实例
const router = useRouter()

// 响应式数据
const loginFormRef = ref()
const changePasswordFormRef = ref()
const loading = ref(false)
const isChangePasswordMode = ref(false)

// 登录表单数据
const loginForm = reactive({
  username: '',
  password: ''
})

// 修改密码表单数据
const changePasswordForm = reactive({
  adminUsername: '',
  adminPassword: '',
  targetUsername: '',
  newPassword: ''
})

// 登录表单验证规则
const loginRules = {
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
    { min: 3, max: 20, message: '账号长度应为3-20个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度应为6-20个字符', trigger: 'blur' }
  ]
}

// 修改密码表单验证规则
const changePasswordRules = {
  adminUsername: [
    { required: true, message: '请输入超级管理员账号', trigger: 'blur' },
    { min: 3, max: 20, message: '账号长度应为3-20个字符', trigger: 'blur' }
  ],
  adminPassword: [
    { required: true, message: '请输入超级管理员密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度应为6-20个字符', trigger: 'blur' }
  ],
  targetUsername: [
    { required: true, message: '请输入要修改的账号', trigger: 'blur' },
    { min: 3, max: 20, message: '账号长度应为3-20个字符', trigger: 'blur' }
  ],
  newPassword: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度应为6-20个字符', trigger: 'blur' }
  ]
}

// 登录处理函数
const handleLogin = async () => {
  if (!loginFormRef.value) return

  try {
    const valid = await loginFormRef.value.validate()
    if (!valid) return

    loading.value = true

    // 这里添加实际的登录逻辑
    // 模拟API调用
    setTimeout(() => {
      loading.value = false
      ElMessage.success('登录成功！')
      // 登录成功后跳转到首页
      router.push('/')
    }, 1000)

  } catch (error) {
    loading.value = false
    console.error('登录失败:', error)
  }
}

// 切换到修改密码模式
const handleChangePassword = () => {
  isChangePasswordMode.value = true
  // 清空表单数据
  Object.assign(changePasswordForm, {
    adminUsername: '',
    adminPassword: '',
    targetUsername: '',
    newPassword: ''
  })
}

// 修改密码处理函数
const handleChangePasswordSubmit = async () => {
  if (!changePasswordFormRef.value) return

  try {
    const valid = await changePasswordFormRef.value.validate()
    if (!valid) return

    loading.value = true

    // 这里添加实际的修改密码逻辑
    // 模拟API调用
    setTimeout(() => {
      loading.value = false
      ElMessage.success('密码修改成功！')
      // 修改成功后返回登录模式
      backToLogin()
    }, 1000)

  } catch (error) {
    loading.value = false
    console.error('修改密码失败:', error)
  }
}

// 返回登录模式
const backToLogin = () => {
  isChangePasswordMode.value = false
  // 清空表单数据
  Object.assign(loginForm, {
    username: '',
    password: ''
  })
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.login-box {
  width: 400px;
  padding: 40px;
  background: rgba(255, 255, 255, 0.95);
  border-radius: 12px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
  backdrop-filter: blur(10px);
}

.login-title {
  text-align: center;
  margin-bottom: 30px;
}

.login-title h2 {
  color: #333;
  font-size: 28px;
  font-weight: 600;
  margin: 0;
}

.login-form {
  width: 100%;
}

.login-form .el-form-item {
  margin-bottom: 24px;
}

.login-btn {
  width: 100%;
  height: 44px;
  font-size: 16px;
  font-weight: 500;
  border-radius: 8px;
}

.login-footer {
  text-align: center;
  margin-top: 20px;
}

.login-footer .el-link {
  font-size: 14px;
}

/* 响应式设计 */
@media (max-width: 480px) {
  .login-box {
    width: 90%;
    padding: 30px 20px;
  }

  .login-title h2 {
    font-size: 24px;
  }
}
</style>