<template>
  <div class="summit-admin">
    <h2>峰会信息管理</h2>

    <!-- 数据统计 -->
    <div class="stats-section">
      <div class="stat-card">
        <h3>议程数量</h3>
        <p class="stat-number">{{ scheduleCount }}</p>
      </div>
      <div class="stat-card">
        <h3>嘉宾数量</h3>
        <p class="stat-number">{{ guestCount }}</p>
      </div>
    </div>

    <!-- 操作按钮 -->
    <div class="action-buttons">
      <button @click="showAddForm('schedule')" class="add-button">添加议程</button>
      <button @click="showAddForm('guest')" class="add-button">添加嘉宾</button>
      <button @click="refreshData" class="refresh-button">刷新数据</button>
    </div>

    <!-- 议程管理 -->
    <div class="data-section">
      <h3>议程管理</h3>
      <div class="schedule-groups">
        <div v-for="(group, date) in groupedScheduleData" :key="date" class="schedule-group">
          <div class="group-header">
            <h4 class="group-title">{{ date || '未分类' }}</h4>
            <div class="group-actions" v-if="date !== '未分类'">
              <button @click="editGroupName('schedule', date)" class="group-edit-btn" title="修改分组名称">
                ✏️
              </button>
              <button @click="deleteGroup('schedule', date)" class="group-delete-btn" title="删除整个分组">
                🗑️
              </button>
            </div>
          </div>
          <div class="table-container">
            <table class="data-table">
              <thead>
                <tr>
                  <th>时间</th>
                  <th>活动</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="item in group" :key="item.id">
                  <td>{{ item.time }}</td>
                  <td>{{ item.event }}</td>
                  <td>
                    <button @click="editItem(item)" class="edit-btn">编辑</button>
                    <button @click="deleteItem(item.id)" class="delete-btn">删除</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- 嘉宾管理 -->
    <div class="data-section">
      <h3>嘉宾管理</h3>
      <div class="guest-groups">
        <div v-for="(group, category) in groupedGuestData" :key="category" class="guest-group">
          <div class="group-header">
            <h4 class="group-title">{{ category || '未分类' }}</h4>
            <div class="group-actions" v-if="category !== '未分类'">
              <button @click="editGroupName('guest', category)" class="group-edit-btn" title="修改分组名称">
                ✏️
              </button>
              <button @click="deleteGroup('guest', category)" class="group-delete-btn" title="删除整个分组">
                🗑️
              </button>
            </div>
          </div>
          <div class="table-container">
            <table class="data-table">
              <thead>
                <tr>
                  <th>嘉宾姓名</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="item in group" :key="item.id">
                  <td>{{ item.guestName }}</td>
                  <td>
                    <button @click="editItem(item)" class="edit-btn">编辑</button>
                    <button @click="deleteItem(item.id)" class="delete-btn">删除</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- 添加/编辑表单弹窗 -->
    <div v-if="showForm" class="modal-overlay" @click="closeForm">
      <div class="modal-content" @click.stop>
        <h3>{{ isEditing ? '编辑' : '添加' }}{{ formType === 'schedule' ? '议程' : '嘉宾' }}</h3>

        <form @submit.prevent="submitForm">
          <!-- 议程表单 -->
          <div v-if="formType === 'schedule'">
            <div class="form-group">
              <label for="date">日期（如：8月24日）</label>
              <input type="text" id="date" v-model="formData.date" placeholder="请输入日期，如：8月24日" />
            </div>
            <div class="form-group">
              <label for="time">时间（如：17:00前）</label>
              <input type="text" id="time" v-model="formData.time" placeholder="请输入时间，如：17:00前、上午、下午等" />
            </div>
            <div class="form-group">
              <label for="event">活动内容</label>
              <textarea id="event" v-model="formData.event" rows="3" required placeholder="请输入活动内容"></textarea>
            </div>
          </div>

          <!-- 嘉宾表单 -->
          <div v-if="formType === 'guest'">
            <div class="form-group">
              <label for="categoryTitle">分类标题</label>
              <input type="text" id="categoryTitle" v-model="formData.categoryTitle" required placeholder="请输入分类标题" />
            </div>
            <div class="form-group">
              <label for="guestName">嘉宾姓名</label>
              <input type="text" id="guestName" v-model="formData.guestName" required placeholder="请输入嘉宾姓名" />
            </div>
          </div>

          <div class="form-actions">
            <button type="submit" class="save-button">保存</button>
            <button type="button" @click="closeForm" class="cancel-button">取消</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive, computed } from 'vue';
import {
  getSummitCompleteData,
  addSummitData,
  updateSummitData,
  deleteSummitData
} from '@/api';
import { ElNotification, ElMessageBox } from 'element-plus';

// 数据状态
const scheduleData = ref([]);
const guestData = ref([]);
const scheduleCount = ref(0);
const guestCount = ref(0);

// 分组后的数据
const groupedScheduleData = computed(() => {
  const groups = {};
  scheduleData.value.forEach(item => {
    const date = item.date || '未分类';
    if (!groups[date]) {
      groups[date] = [];
    }
    groups[date].push(item);
  });

  // 对每个组内的数据按ID排序
  Object.keys(groups).forEach(date => {
    groups[date].sort((a, b) => (a.id || 0) - (b.id || 0));
  });

  // 将对象转换为按日期排序的数组，然后再转回对象
  const sortedGroups = {};
  const dateKeys = Object.keys(groups).sort((a, b) => {
    // 未分类排在最后
    if (a === '未分类') return 1;
    if (b === '未分类') return -1;

    // 提取日期中的数字进行比较
    const extractNumber = (dateStr) => {
      const match = dateStr.match(/(\d+)月(\d+)日/);
      if (match) {
        return parseInt(match[1]) * 100 + parseInt(match[2]); // 月*100 + 日
      }
      return 0;
    };

    return extractNumber(a) - extractNumber(b);
  });

  dateKeys.forEach(key => {
    sortedGroups[key] = groups[key];
  });

  return sortedGroups;
}); const groupedGuestData = computed(() => {
  const groups = {};
  guestData.value.forEach(item => {
    const category = item.categoryTitle || '未分类';
    if (!groups[category]) {
      groups[category] = [];
    }
    groups[category].push(item);
  });

  // 对每个组内的数据按ID排序
  Object.keys(groups).forEach(category => {
    groups[category].sort((a, b) => (a.id || 0) - (b.id || 0));
  });

  return groups;
});

// 表单状态
const showForm = ref(false);
const isEditing = ref(false);
const formType = ref('schedule'); // 'schedule' 或 'guest'
const formData = reactive({
  id: null,
  date: '',
  time: '',
  event: '',
  categoryTitle: '',
  guestName: '',
  dataType: 'schedule'
});

// 获取数据
const fetchData = async () => {
  try {
    const res = await getSummitCompleteData();
    if (res.code === 200 && res.data) {
      scheduleData.value = res.data.schedule || [];
      guestData.value = res.data.guests || [];
      scheduleCount.value = scheduleData.value.length;
      guestCount.value = guestData.value.length;
    }
  } catch (error) {
    console.error('获取峰会数据失败:', error);
    ElNotification({
      title: '错误',
      message: '数据加载失败！',
      type: 'error',
      duration: 4000
    });
  }
};

// 显示添加表单
const showAddForm = (type) => {
  formType.value = type;
  isEditing.value = false;
  resetFormData();
  formData.dataType = type;
  showForm.value = true;
};

// 编辑项目
const editItem = (item) => {
  formType.value = item.dataType || (item.date ? 'schedule' : 'guest');
  isEditing.value = true;

  // 填充表单数据
  Object.keys(formData).forEach(key => {
    if (item.hasOwnProperty(key)) {
      formData[key] = item[key];
    }
  });

  showForm.value = true;
};

// 删除项目
const deleteItem = async (id) => {
  try {
    await ElMessageBox.confirm(
      '确定要删除这条记录吗？此操作不可撤销！',
      '确认删除',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
    );

    const res = await deleteSummitData(id);
    if (res.code === 200) {
      ElNotification({
        title: '成功',
        message: '删除成功！',
        type: 'success',
        duration: 3000
      });
      await fetchData();
    } else {
      ElNotification({
        title: '错误',
        message: `删除失败: ${res.message}`,
        type: 'error',
        duration: 4000
      });
    }
  } catch (error) {
    if (error !== 'cancel') {
      console.error('删除失败:', error);
      ElNotification({
        title: '错误',
        message: '删除过程中发生错误！',
        type: 'error',
        duration: 4000
      });
    }
  }
};

// 提交表单
const submitForm = async () => {
  try {
    const submitData = { ...formData };

    let res;
    if (isEditing.value) {
      res = await updateSummitData(submitData.id, submitData);
    } else {
      res = await addSummitData(submitData);
    }

    if (res.code === 200) {
      ElNotification({
        title: '成功',
        message: `${isEditing.value ? '更新' : '添加'}成功！`,
        type: 'success',
        duration: 3000
      });
      closeForm();
      await fetchData();
    } else {
      ElNotification({
        title: '错误',
        message: `${isEditing.value ? '更新' : '添加'}失败: ${res.message}`,
        type: 'error',
        duration: 4000
      });
    }
  } catch (error) {
    console.error('提交失败:', error);
    ElNotification({
      title: '错误',
      message: '提交过程中发生错误！',
      type: 'error',
      duration: 4000
    });
  }
};

// 关闭表单
const closeForm = () => {
  showForm.value = false;
  resetFormData();
};

// 重置表单数据
const resetFormData = () => {
  Object.keys(formData).forEach(key => {
    if (key === 'dataType') {
      formData[key] = 'schedule';
    } else {
      formData[key] = key === 'id' ? null : '';
    }
  });
};

// 刷新数据
const refreshData = async () => {
  await fetchData();
  ElNotification({
    title: '成功',
    message: '数据已刷新！',
    type: 'success',
    duration: 3000
  });
};

// 编辑分组名称
const editGroupName = async (type, oldName) => {
  try {
    const { value: newName } = await ElMessageBox.prompt(
      `请输入新的${type === 'schedule' ? '日期' : '分类'}名称：`,
      '修改分组名称',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputValue: oldName,
        inputValidator: (value) => {
          if (!value || value.trim() === '') {
            return '名称不能为空！';
          }
          return true;
        }
      }
    );

    if (newName && newName.trim() !== oldName) {
      // 获取该分组下的所有项目
      const targetData = type === 'schedule' ? scheduleData.value : guestData.value;
      const targetField = type === 'schedule' ? 'date' : 'categoryTitle';
      const itemsToUpdate = targetData.filter(item => item[targetField] === oldName);

      // 批量更新所有项目的分组名称
      for (const item of itemsToUpdate) {
        const updateData = { ...item };
        updateData[targetField] = newName.trim();

        const res = await updateSummitData(item.id, updateData);
        if (res.code !== 200) {
          throw new Error(`更新项目 ${item.id} 失败: ${res.message}`);
        }
      }

      ElNotification({
        title: '成功',
        message: `分组名称已更新！共更新 ${itemsToUpdate.length} 个项目`,
        type: 'success',
        duration: 4000
      });

      await fetchData();
    }
  } catch (error) {
    if (error !== 'cancel') {
      console.error('更新分组名称失败:', error);
      ElNotification({
        title: '错误',
        message: '更新分组名称失败！',
        type: 'error',
        duration: 4000
      });
    }
  }
};

// 删除整个分组
const deleteGroup = async (type, groupName) => {
  try {
    const targetData = type === 'schedule' ? scheduleData.value : guestData.value;
    const targetField = type === 'schedule' ? 'date' : 'categoryTitle';
    const itemsToDelete = targetData.filter(item => item[targetField] === groupName);

    await ElMessageBox.confirm(
      `确定要删除整个"${groupName}"分组吗？这将删除该分组下的所有 ${itemsToDelete.length} 个项目，此操作不可撤销！`,
      '确认删除分组',
      {
        confirmButtonText: '确定删除',
        cancelButtonText: '取消',
        type: 'warning',
        dangerouslyUseHTMLString: true
      }
    );

    // 批量删除所有项目
    for (const item of itemsToDelete) {
      const res = await deleteSummitData(item.id);
      if (res.code !== 200) {
        throw new Error(`删除项目 ${item.id} 失败: ${res.message}`);
      }
    }

    ElNotification({
      title: '成功',
      message: `分组"${groupName}"已删除！共删除 ${itemsToDelete.length} 个项目`,
      type: 'success',
      duration: 4000
    });

    await fetchData();
  } catch (error) {
    if (error !== 'cancel') {
      console.error('删除分组失败:', error);
      ElNotification({
        title: '错误',
        message: '删除分组失败！',
        type: 'error',
        duration: 4000
      });
    }
  }
};

// 组件挂载时获取数据
onMounted(() => {
  fetchData();
});
</script>

<style scoped>
.summit-admin {
  padding: 2rem;
  font-family: 'Arial', sans-serif;
}

h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: #333;
}

.stats-section {
  display: flex;
  gap: 2rem;
  margin-bottom: 2rem;
}

.stat-card {
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 1.5rem;
  flex: 1;
  text-align: center;
}

.stat-card h3 {
  margin: 0 0 1rem 0;
  color: #666;
}

.stat-number {
  font-size: 2rem;
  font-weight: bold;
  color: #4CAF50;
  margin: 0;
}

.action-buttons {
  display: flex;
  gap: 1rem;
  margin-bottom: 2rem;
}

.add-button {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
}

.add-button:hover {
  background-color: #45a049;
}

.refresh-button {
  background-color: #2196F3;
  color: white;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
}

.refresh-button:hover {
  background-color: #1976D2;
}

.data-section {
  margin-bottom: 2rem;
}

.data-section h3 {
  margin-bottom: 1rem;
  color: #333;
  border-bottom: 2px solid #4CAF50;
  padding-bottom: 0.5rem;
}

.schedule-groups,
.guest-groups {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.schedule-group,
.guest-group {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  overflow: hidden;
  background-color: #fafafa;
}

.group-title {
  background-color: #f5f5f5;
  padding: 1rem;
  margin: 0;
  color: #333;
  font-weight: bold;
  border-bottom: 1px solid #e0e0e0;
  font-size: 1.1rem;
}

.group-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #f5f5f5;
  border-bottom: 1px solid #e0e0e0;
}

.group-header .group-title {
  background-color: transparent;
  border-bottom: none;
  padding: 1rem;
  flex: 1;
}

.group-actions {
  display: flex;
  gap: 0.5rem;
  padding: 0 1rem;
}

.group-edit-btn,
.group-delete-btn {
  background: none;
  border: none;
  font-size: 1.2rem;
  cursor: pointer;
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  transition: background-color 0.2s;
}

.group-edit-btn:hover {
  background-color: rgba(255, 152, 0, 0.1);
}

.group-delete-btn:hover {
  background-color: rgba(244, 67, 54, 0.1);
}

.table-container {
  overflow-x: auto;
}

.data-table {
  width: 100%;
  border-collapse: collapse;
  background-color: white;
  margin: 0;
  table-layout: fixed;
}

.schedule-group .data-table,
.guest-group .data-table {
  border-radius: 0;
  box-shadow: none;
}

/* 议程表格列宽设置 */
.schedule-group .data-table th:nth-child(1),
.schedule-group .data-table td:nth-child(1) {
  width: 15%;
}

.schedule-group .data-table th:nth-child(2),
.schedule-group .data-table td:nth-child(2) {
  width: 65%;
}

.schedule-group .data-table th:nth-child(3),
.schedule-group .data-table td:nth-child(3) {
  width: 20%;
  text-align: center;
}

/* 嘉宾表格列宽设置 */
.guest-group .data-table th:nth-child(1),
.guest-group .data-table td:nth-child(1) {
  width: 75%;
}

.guest-group .data-table th:nth-child(2),
.guest-group .data-table td:nth-child(2) {
  width: 25%;
  text-align: center;
}

.data-table th,
.data-table td {
  padding: 0.75rem 1rem;
  text-align: left;
  border-bottom: 1px solid #ddd;
  vertical-align: middle;
}

.data-table th {
  background-color: #f5f5f5;
  font-weight: bold;
  color: #333;
}

.data-table td {
  line-height: 1.5;
}

/* 时间列更紧凑的内边距 */
.schedule-group .data-table th:nth-child(1),
.schedule-group .data-table td:nth-child(1) {
  padding: 0.75rem 0.5rem;
  text-align: center;
}

/* 活动列左对齐，更多空间 */
.schedule-group .data-table th:nth-child(2),
.schedule-group .data-table td:nth-child(2) {
  padding: 0.75rem 1rem;
  text-align: left;
  word-wrap: break-word;
}

/* 操作列居中对齐 */
.data-table td:last-child {
  white-space: nowrap;
  text-align: center;
}

.data-table tr:hover {
  background-color: #f9f9f9;
}

.data-table tbody tr {
  height: 60px;
}

.data-table tbody td {
  padding: 0.75rem 1rem;
}

.edit-btn {
  background-color: #ff9800;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 0.5rem;
  font-size: 0.875rem;
  display: inline-block;
  vertical-align: middle;
}

.edit-btn:hover {
  background-color: #f57c00;
}

.delete-btn {
  background-color: #f44336;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.875rem;
  display: inline-block;
  vertical-align: middle;
}

.delete-btn:hover {
  background-color: #d32f2f;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background-color: white;
  padding: 2rem;
  border-radius: 8px;
  width: 90%;
  max-width: 500px;
  max-height: 80vh;
  overflow-y: auto;
}

.modal-content h3 {
  margin-top: 0;
  margin-bottom: 1.5rem;
  color: #333;
}

.form-group {
  margin-bottom: 1rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
  color: #666;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.form-group textarea {
  resize: vertical;
}

.form-actions {
  display: flex;
  gap: 1rem;
  justify-content: flex-end;
  margin-top: 1.5rem;
}

.save-button {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
}

.save-button:hover {
  background-color: #45a049;
}

.cancel-button {
  background-color: #6c757d;
  color: white;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
}

.cancel-button:hover {
  background-color: #5a6268;
}
</style>