<template>
  <div class="about-more-admin">
    <el-card class="box-card page-config-card">
      <template #header>
        <div class="card-header">
          <span>页面通用内容配置</span>
          <el-button type="primary" :loading="configSaving" @click="handleSaveConfig">保存配置</el-button>
        </div>
      </template>
      <el-form :model="pageConfig" label-width="100px" v-loading="configLoading">
        <el-form-item label="主标题">
          <el-input v-model="pageConfig.title" placeholder="例如：世博全球青年联盟使命与愿景" />
        </el-form-item>
        <el-form-item label="底部段落">
          <el-input v-model="pageConfig.finalParagraph" type="textarea" :rows="5" />
        </el-form-item>
      </el-form>
    </el-card>
    <div class="header">
      <h2>动态内容区块管理</h2>
      <el-button type="primary" @click="handleOpenDialog()">新增区块</el-button>
    </div>

    <el-table :data="sections" style="width: 100%" v-loading="loading">
      <el-table-column prop="sortOrder" label="排序" width="80" />
      <el-table-column prop="title" label="标题" />
      <el-table-column prop="isPublished" label="状态" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.isPublished ? 'success' : 'info'">
            {{ scope.row.isPublished ? '已发布' : '草稿' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="scope">
          <el-button size="small" @click="handleOpenDialog(scope.row)">编辑</el-button>
          <el-popconfirm title="确定删除吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button size="small" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialogVisible" :title="isEditMode ? '编辑区块' : '新增区块'" width="60%">
      <el-form :model="currentSection" label-width="80px">
        <el-form-item label="标题">
          <el-input v-model="currentSection.title" />
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="currentSection.content" type="textarea" :rows="8" />
        </el-form-item>
        <el-form-item label="排序值">
          <el-input-number v-model="currentSection.sortOrder" :min="0" />
        </el-form-item>
        <el-form-item label="状态">
          <el-switch v-model="currentSection.isPublished" active-text="发布" inactive-text="草稿" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSave">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue';
import { ElMessage } from 'element-plus';
// 导入正确的API函数
import {
  getAboutMoreSections,
  createAboutMoreSection,
  updateAboutMoreSection,
  deleteAboutMoreSection,
  getAboutMoreConfig,      // <-- 新增导入
  updateAboutMoreConfig    // <-- 新增导入
} from '@/api';

// --- 动态区块的状态 (保持不变) ---
const loading = ref(true);
const sections = ref([]);
const dialogVisible = ref(false);
const isEditMode = ref(false);
const currentSection = reactive({
  id: null,
  title: '',
  content: '',
  sortOrder: 0,
  isPublished: true
});

// --- 页面配置的状态 (保持不变) ---
const configLoading = ref(true);
const configSaving = ref(false);
const pageConfig = reactive({
  title: '',
  finalParagraph: ''
});

// --- 使用正确的API函数 ---

// 获取页面配置
const fetchPageConfig = async () => {
  configLoading.value = true;
  try {
    const res = await getAboutMoreConfig(); // <-- 使用正确的函数
    if (res && res.data && res.data.configData) {
      const data = JSON.parse(res.data.configData);
      pageConfig.title = data.title;
      pageConfig.finalParagraph = data.finalParagraph;
    }
  } catch (error) {
    ElMessage.error('页面配置加载失败');
    console.error(error);
  } finally {
    configLoading.value = false;
  }
};

// 保存页面配置
const handleSaveConfig = async () => {
  configSaving.value = true;
  try {
    await updateAboutMoreConfig(pageConfig); // <-- 使用正确的函数
    ElMessage.success('配置保存成功！');
  } catch (error) {
    ElMessage.error('配置保存失败');
    console.error(error);
  } finally {
    configSaving.value = false;
  }
};

// --- 原有函数 (保持不变) ---
const fetchSections = async () => {
  loading.value = true;
  try {
    const res = await getAboutMoreSections();
    sections.value = res.data;
  } catch (error) {
    ElMessage.error('数据加载失败');
  } finally {
    loading.value = false;
  }
};

const handleOpenDialog = (section = null) => {
  if (section) {
    isEditMode.value = true;
    Object.assign(currentSection, section);
  } else {
    isEditMode.value = false;
    Object.assign(currentSection, {
      id: null,
      title: '',
      content: '',
      sortOrder: sections.value.length + 1,
      isPublished: true
    });
  }
  dialogVisible.value = true;
};

const handleSave = async () => {
  try {
    if (isEditMode.value) {
      await updateAboutMoreSection(currentSection.id, currentSection);
      ElMessage.success('更新成功');
    } else {
      await createAboutMoreSection(currentSection);
      ElMessage.success('新增成功');
    }
    dialogVisible.value = false;
    fetchSections();
  } catch (error) {
    ElMessage.error('保存失败');
  }
};

const handleDelete = async (id) => {
  try {
    await deleteAboutMoreSection(id);
    ElMessage.success('删除成功');
    fetchSections();
  } catch (error) {
    ElMessage.error('删除失败');
  }
};

// 组件加载时获取所有数据
onMounted(() => {
  fetchSections();
  fetchPageConfig();
});
</script>

<style scoped>
.about-more-admin {
  padding: 20px;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
/* 开始：为配置卡片新增的样式 */
.page-config-card {
  margin-bottom: 30px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
/* 结束：新增样式 */
</style>