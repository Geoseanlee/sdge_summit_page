<template>
  <div class="manage-container">
    <div class="toolbar">
      <div class="left">
        <el-button type="primary" @click="openDialog">新增</el-button>
        <el-button :disabled="!multipleSelection.length" @click="handleBatchDelete">删除选中</el-button>
      </div>
      <div class="right">
        <el-input v-model="searchKey" placeholder="搜索标题" style="width:200px" />
        <el-button icon="Search" @click="fetchList" />
      </div>
    </div>

    <el-table
      :data="filteredList"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="title" label="标题" />
      <el-table-column prop="description" label="副标题" />
      <el-table-column prop="createTime" label="发布时间" width="180" />
      <el-table-column label="操作" width="160">
        <template #default="{ row }">
          <el-button size="small" type="primary" @click="editRow(row)">修改</el-button>
          <el-button size="small" type="danger" @click="deleteRow(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 编辑/新增对话框 -->
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="标题">
          <el-input v-model="form.title" />
        </el-form-item>
        <el-form-item label="副标题">
          <el-input v-model="form.description" />
        </el-form-item>
        <el-form-item label="标签">
          <el-input v-model="form.tag" placeholder="逗号分隔" />
        </el-form-item>
        <el-form-item label="封面图URL">
          <el-input v-model="form.imageUrl" />
        </el-form-item>
        <el-form-item label="跳转链接">
          <el-input v-model="form.url" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" @click="saveForm">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { get, post, put, del } from '@/api'

const list = ref([])
const searchKey = ref('')
const multipleSelection = ref([])

const dialogVisible = ref(false)
const dialogTitle = ref('')
const form = ref({ id: null, title: '', description: '', tag: '', imageUrl: '', url: '' })

const fetchList = async () => {
  const res = await get('/api/forums')
  if (res.code === 200) {
    list.value = res.data || []
  }
}

onMounted(fetchList)

const filteredList = computed(() => {
  if (!searchKey.value) return list.value
  return list.value.filter((i) => i.title && i.title.includes(searchKey.value))
})

const handleSelectionChange = (val) => {
  multipleSelection.value = val
}

const openDialog = () => {
  dialogTitle.value = '新增论坛'
  form.value = { id: null, title: '', description: '', tag: '', imageUrl: '', url: '' }
  dialogVisible.value = true
}

const editRow = (row) => {
  dialogTitle.value = '编辑论坛'
  form.value = { ...row }
  dialogVisible.value = true
}

const saveForm = async () => {
  if (!form.value.title) return ElMessage.warning('请输入标题')
  let res
  if (form.value.id) {
    res = await put(`/api/forums/${form.value.id}`, form.value)
  } else {
    res = await post('/api/forums', form.value)
  }
  if (res.code === 200) {
    ElMessage.success('保存成功')
    dialogVisible.value = false
    fetchList()
  } else {
    ElMessage.error(res.message || '保存失败')
  }
}

const deleteRow = async (row) => {
  try {
    await ElMessageBox.confirm('确定删除该条记录?', '提示', { type: 'warning' })
    const res = await del(`/api/forums/${row.id}`)
    if (res.code === 200) {
      ElMessage.success('删除成功')
      fetchList()
    }
  } catch (e) {}
}

const handleBatchDelete = async () => {
  if (!multipleSelection.value.length) return
  try {
    await ElMessageBox.confirm(`确定删除选中的 ${multipleSelection.value.length} 条记录?`, '提示', { type: 'warning' })
    for (const row of multipleSelection.value) {
      await del(`/api/forums/${row.id}`)
    }
    ElMessage.success('批量删除成功')
    fetchList()
  } catch (e) {}
}
</script>

<style scoped>
.manage-container {
  padding: 20px;
  background: #fff;
  border-radius: 8px;
}
.toolbar {
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;
}
.left > * {
  margin-right: 10px;
}
</style> 