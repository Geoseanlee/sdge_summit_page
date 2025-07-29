<template>
  <div class="manage-container">
    <div class="toolbar">
      <div class="left">
        <el-button type="primary" @click="openDialog">新增</el-button>
        <el-button :disabled="!multipleSelection.length" @click="handleBatchDelete">删除选中</el-button>
      </div>
      <div class="right">
        <el-input v-model="searchInput" placeholder="搜索标题" style="width:200px" />
        <el-button :icon="Search" @click="handleSearch" />
      </div>
    </div>

    <el-table
      :data="pagedList"
      style="width: 100%"
      @selection-change="handleSelectionChange"
      @sort-change="handleSort"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="title" label="标题" sortable="custom" />
      <el-table-column prop="description" label="副标题" sortable="custom" />
      <el-table-column
        prop="updateTime"
        label="发布时间"
        width="200"
        :formatter="formatTime"
        sortable="custom"
      />
      <el-table-column label="操作" width="160">
        <template #default="{ row }">
          <el-button size="small" type="primary" @click="editRow(row)">修改</el-button>
          <el-button size="small" type="danger" @click="deleteRow(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <div class="forum-pagination">
      <div class="custom-pagination">
        <span class="pagination-info">第{{ currentPage }}页，共{{ totalPages }}页</span>
        <el-button type="text" :disabled="currentPage===1" @click="changePage(currentPage-1)">&lt;</el-button>
        <template v-for="page in pageList" :key="page">
          <el-button
            v-if="page!=='...'"
            type="text"
            :class="{ active: currentPage===page }"
            @click="changePage(page)"
          >{{ page }}</el-button>
          <span v-else class="ellipsis">...</span>
        </template>
        <el-button type="text" :disabled="currentPage===totalPages" @click="changePage(currentPage+1)">&gt;</el-button>
      </div>
    </div>

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
import { Search } from '@element-plus/icons-vue'
import { get, post, put, del } from '@/api'

const list = ref([])
const searchInput = ref('')
const searchValue = ref('')
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
  if (!searchValue.value) return list.value
  return list.value.filter((i) => {
    const key = searchValue.value
    return (
      (i.title && i.title.includes(key)) ||
      (i.description && i.description.includes(key))
    )
  })
})

// 排序
const sortProp = ref('')
const sortOrder = ref('') // 'ascending' | 'descending'

const sortedList = computed(() => {
  if (!sortProp.value) return filteredList.value
  const data = [...filteredList.value]
  data.sort((a, b) => {
    let v1 = a[sortProp.value]
    let v2 = b[sortProp.value]
    if (sortProp.value === 'updateTime') {
      v1 = new Date(v1)
      v2 = new Date(v2)
    }
    if (v1 == null) return -1
    if (v2 == null) return 1
    if (v1 === v2) return 0
    const res = typeof v1 === 'string' ? v1.localeCompare(v2) : v1 - v2
    return sortOrder.value === 'ascending' ? res : -res
  })
  return data
})

const handleSearch = () => {
  searchValue.value = searchInput.value.trim()
  currentPage.value = 1
}

// 分页
const pageSize = 20
const currentPage = ref(1)
const totalPages = computed(() => Math.ceil(filteredList.value.length / pageSize))
const pagedList = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return sortedList.value.slice(start, start + pageSize)
})

const pageList = computed(() => {
  const listPages = []
  if (totalPages.value <= 5) {
    for (let i = 1; i <= totalPages.value; i++) listPages.push(i)
    return listPages
  }
  if (currentPage.value <= 3) {
    listPages.push(1, 2, 3, 4, '...', totalPages.value)
    return listPages
  }
  if (currentPage.value >= totalPages.value - 2) {
    listPages.push(1, '...', totalPages.value - 3, totalPages.value - 2, totalPages.value - 1, totalPages.value)
    return listPages
  }
  listPages.push(1, '...', currentPage.value - 1, currentPage.value, currentPage.value + 1, '...', totalPages.value)
  return listPages
})

const changePage = (page) => {
  if (typeof page === 'number' && page >= 1 && page <= totalPages.value) {
    currentPage.value = page
  }
}

const handleSort = ({ prop, order }) => {
  sortProp.value = order ? prop : ''
  sortOrder.value = order || ''
  currentPage.value = 1
}

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

// 格式化时间：2025-07-29T20:08:07 -> 2025-07-29, 20:08:07
const formatTime = (_, __, value) => {
  if (!value) return ''
  return String(value).replace('T', ', ')
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

.forum-pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
.custom-pagination {
  display: flex;
  align-items: center;
  gap: 6px;
}
.pagination-info {
  margin-right: 12px;
}
.active {
  font-weight: bold;
  color: #409eff;
}
.ellipsis {
  color: #999;
}

/* 放大表格与控件字号 */
.manage-container :deep(.el-table .cell) {
  font-size: 16px;
}

.toolbar :deep(.el-button),
.toolbar :deep(.el-input__inner) {
  font-size: 16px;
}
</style> 