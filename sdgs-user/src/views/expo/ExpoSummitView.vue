<template>
    <div class="expo-summit">
        <!-- 面包屑导航 -->
        <div class="breadcrumb-nav">
            <div class="container">
                <el-breadcrumb separator=">">
                    <el-breadcrumb-item>
                        <router-link to="/" class="breadcrumb-link">
                            <el-icon>
                                <House />
                            </el-icon>
                        </router-link>
                    </el-breadcrumb-item>
                    <el-breadcrumb-item>
                        <router-link to="/expo" class="breadcrumb-link">世博会</router-link>
                    </el-breadcrumb-item>
                    <el-breadcrumb-item>2025年大版世博会</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
        </div>

        <!-- 主要内容 -->
        <div class="main-content">
            <div class="container">
                <!-- 峰会简明议程标题 -->
                <h1 class="page-title">峰会简明议程</h1>

                <!-- 议程表格 -->
                <el-card class="schedule-card" v-loading="loading">
                    <div v-if="scheduleData.length === 0 && !loading" class="empty-state">
                        暂无议程信息
                    </div>
                    <el-table v-else :data="scheduleData" :span-method="mergeRows" style="width: 100%" size="large" :show-header="false" :cell-style="setCellStyle" class="borderless-table">
                        <el-table-column prop="date" width="120" align="center" :show-overflow-tooltip="false">
                            <template #default="scope">
                                <div class="date-cell">
                                    {{ scope.row.date || '' }}
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column prop="time" width="150" align="center">
                            <template #default="scope">
                                <div class="time-cell">
                                    {{ scope.row.time || '' }}
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column prop="event" align="center">
                            <template #default="scope">
                                <div class="event-cell">
                                    <div class="event-content">
                                        <div class="main-event">
                                            {{ scope.row.event || '' }}
                                        </div>
                                    </div>
                                </div>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>

                <!-- 峰会拟邀请嘉宾 -->
                <h1 class="page-title guest-title">峰会拟邀请嘉宾</h1>
                
                <el-card class="guest-card" v-loading="loading">
                    <div v-if="guestCategories.length === 0 && !loading" class="empty-state">
                        暂无嘉宾信息
                    </div>
                    <div v-else class="guest-categories">
                        <div v-for="(category, index) in guestCategories" :key="category.id || index" class="category-section">
                            <h2 class="category-title">{{ category.title || '' }}</h2>
                            <ul class="guest-list">
                                <li v-for="(guest, guestIndex) in category.guests" :key="guestIndex">
                                    <span class="bullet">•</span>
                                    {{ guest || '' }}
                                </li>
                            </ul>
                        </div>
                    </div>
                </el-card>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { House, User } from '@element-plus/icons-vue'
import request from '@/utils/request'
import { ElMessage } from 'element-plus'

// 响应式数据
const scheduleData = ref([])
const guestCategories = ref([])
const loading = ref(false)

// 获取峰会议程数据
const fetchScheduleData = async () => {
    try {
        loading.value = true
        const response = await request.get('/summit/schedule')
        if (response && Array.isArray(response)) {
            scheduleData.value = response
        } else {
            // 如果后端返回null或空数据，使用默认数据
            scheduleData.value = []
        }
    } catch (error) {
        console.error('获取峰会议程失败:', error)
        ElMessage.error('获取峰会议程失败')
        scheduleData.value = []
    } finally {
        loading.value = false
    }
}

// 获取嘉宾分类数据
const fetchGuestData = async () => {
    try {
        loading.value = true
        const response = await request.get('/summit/guests')
        if (response && Array.isArray(response)) {
            // 转换后端数据格式为前端需要的格式
            guestCategories.value = response.map(category => ({
                title: category.title,
                guests: category.guests ? category.guests.map(guest => guest.guestName) : []
            }))
        } else {
            // 如果后端返回null或空数据，使用默认数据
            guestCategories.value = []
        }
    } catch (error) {
        console.error('获取峰会嘉宾信息失败:', error)
        ElMessage.error('获取峰会嘉宾信息失败')
        guestCategories.value = []
    } finally {
        loading.value = false
    }
}

// 页面挂载时获取数据
onMounted(() => {
    fetchScheduleData()
    fetchGuestData()
})

// 合并单元格方法
const mergeRows = ({ row, column, rowIndex, columnIndex }) => {
    if (columnIndex === 0 && scheduleData.value.length > 0) { // 日期列
        const currentDate = scheduleData.value[rowIndex]?.date
        
        // 如果当前行有日期值，计算需要合并的行数
        if (currentDate && currentDate.trim() !== '') {
            let mergeCount = 1
            
            // 向下查找相同日期的行（空日期属于同一天）
            for (let i = rowIndex + 1; i < scheduleData.value.length; i++) {
                const nextDate = scheduleData.value[i]?.date
                if (!nextDate || nextDate.trim() === '') {
                    mergeCount++
                } else {
                    break
                }
            }
            
            return [mergeCount, 1]
        } else {
            // 当前行是空日期，需要隐藏
            return [0, 0]
        }
    }
}

// 计算颜色逻辑
const getRowColors = (rowIndex) => {
    if (scheduleData.value.length === 0) return null
    
    // 找到当前行所属的日期组和在组内的索引
    let currentDateGroupIndex = 0
    let indexInGroup = 0
    let currentGroupStartIndex = 0
    
    // 从头开始遍历，计算当前行属于第几个日期组
    for (let i = 0; i <= rowIndex; i++) {
        const row = scheduleData.value[i]
        if (row?.date && row.date.trim() !== '') {
            // 遇到有日期的行
            if (i > 0) {
                // 检查之前是否已经有日期组了
                let hasDateBefore = false
                for (let j = 0; j < i; j++) {
                    if (scheduleData.value[j]?.date && scheduleData.value[j].date.trim() !== '') {
                        hasDateBefore = true
                        break
                    }
                }
                if (hasDateBefore) {
                    currentDateGroupIndex++
                    currentGroupStartIndex = i
                }
            }
        }
        
        if (i === rowIndex) {
            indexInGroup = i - currentGroupStartIndex
        }
    }
    
    // 第一列和第二列的颜色逻辑（保持原有逻辑）
    const isEvenGroup = currentDateGroupIndex % 2 === 0
    const dateColor = isEvenGroup ? '#bae1f6' : '#d7f1fc'
    const timeColor = isEvenGroup ? '#d7f1fc' : '#ffffff'
    
    // 第三列的颜色逻辑（按您的模板数据）
    let eventColor = '#bae1f6' // 默认值
    
    if (currentDateGroupIndex === 0) {
        // 第1个日期组（8月24日）：全部 #bae1f6
        eventColor = '#bae1f6'
    } else if (currentDateGroupIndex === 1) {
        // 第2个日期组（8月25日）：复杂模式
        // 索引: 0,1 -> #e1f3ff; 2,3 -> #bae1f6; 4,5,6 -> #e1f3ff; 7 -> #bae1f6
        if (indexInGroup <= 1) {
            eventColor = '#e1f3ff'
        } else if (indexInGroup <= 3) {
            eventColor = '#bae1f6'
        } else if (indexInGroup <= 6) {
            eventColor = '#e1f3ff'
        } else {
            eventColor = '#bae1f6'
        }
    } else if (currentDateGroupIndex === 2) {
        // 第3个日期组（8月26日）：#e1f3ff, #bae1f6, #e1f3ff
        if (indexInGroup === 0) {
            eventColor = '#e1f3ff'
        } else if (indexInGroup === 1) {
            eventColor = '#bae1f6'
        } else {
            eventColor = '#e1f3ff'
        }
    } else {
        // 其他日期组：按奇偶交替
        eventColor = isEvenGroup ? '#bae1f6' : '#e1f3ff'
    }
    
    return { dateColor, timeColor, eventColor }
}

// 设置单元格样式
const setCellStyle = ({ row, column, rowIndex }) => {
    const colors = getRowColors(rowIndex)
    if (!colors) return {}
    
    if (column.property === 'date') {
        return { backgroundColor: colors.dateColor }
    } else if (column.property === 'time') {
        return { backgroundColor: colors.timeColor }
    } else if (column.property === 'event') {
        return { backgroundColor: colors.eventColor }
    }
    return {}
}
</script>

<style scoped>
.expo-summit {
    min-height: 100vh;
    background-color: #ffffff;
}

/* 面包屑导航样式 */
.breadcrumb-nav {
    background-color: #ffffff;
    padding: 16px 0;
    border-bottom: none;
}

.breadcrumb-link {
    color: #0167A5;
    text-decoration: none;
    display: flex;
    align-items: center;
    font-weight: 600;
    font-size: 16px;
}

.breadcrumb-link:hover {
    color: #0167A5;
}

/* Element Plus面包屑样式覆盖 */
.breadcrumb-nav :deep(.el-breadcrumb__item) {
    color: #0167A5;
    font-size: 16px;
}

.breadcrumb-nav :deep(.el-breadcrumb__item .el-breadcrumb__inner) {
    color: #0167A5;
    font-weight: 600;
    font-size: 16px;
}

.breadcrumb-nav :deep(.el-breadcrumb__item .el-breadcrumb__separator) {
    color: #0167A5;
    font-size: 16px;
}

.breadcrumb-nav :deep(.el-breadcrumb__item:last-child .el-breadcrumb__inner) {
    color: #0167A5;
    font-weight: 600;
    font-size: 16px;
}

/* 主要内容样式 */
.container {
    max-width: 1400px;
    margin: 0 auto;
    padding: 0 20px;
}

.main-content {
    padding: 32px 0;
}

.page-title {
    font-size: 32px;
    font-weight: 600;
    color: #0167A5;
    margin: 16px 0 48px 0;
    text-align: center;
}

.guest-title {
    margin: 100px 0 100px 0;
}

.empty-state {
    text-align: center;
    padding: 40px 20px;
    color: #999;
    font-size: 16px;
}



/* 议程卡片样式 */
.schedule-card {
    margin-bottom: 32px;
    border: none;
    box-shadow: none;
    background: transparent;
}

.schedule-card :deep(.el-card__body) {
    padding: 0;
    border: none;
    background: transparent;
}

/* 无边框表格样式 */
.borderless-table {
    border: none !important;
}

.borderless-table :deep(.el-table__inner-wrapper) {
    border: none !important;
}

.borderless-table :deep(.el-table__body-wrapper) {
    border: none !important;
}

.borderless-table :deep(.el-table__cell) {
    border: none !important;
    border-right: none !important;
    border-bottom: none !important;
}

.borderless-table :deep(.el-table__row) {
    border: none !important;
}

.borderless-table :deep(.el-table tbody tr) {
    border: none !important;
}

.borderless-table :deep(.el-table--border) {
    border: none !important;
}

.borderless-table :deep(.el-table--border::after) {
    display: none !important;
}

.borderless-table :deep(.el-table--border::before) {
    display: none !important;
}

.date-cell {
    padding: 12px 8px;
    font-weight: 600;
    font-size: 16px;
    text-align: center;
    color: #000000;
}

.time-cell {
    padding: 12px 8px;
    font-weight: 600;
    font-size: 16px;
    text-align: center;
    color: #000000;
}

.event-cell {
    padding: 12px 16px;
    text-align: center;
    color: #000000;
}

.date-tag {
    font-weight: 600;
    padding: 8px 16px;
}

.time-text {
    font-weight: 600;
    font-size: 16px;
}

.event-content {
    padding: 8px 0;
}

.main-event {
    font-weight: 600;
    color: #000000;
    margin-bottom: 8px;
    font-size: 16px;
}

.sub-events {
    margin-top: 8px;
}

.sub-event {
    margin-bottom: 4px;
    padding-left: 16px;
    position: relative;
}

.sub-event::before {
    content: '•';
    position: absolute;
    left: 0;
    color: #0167A5;
    font-weight: bold;
}

/* 嘉宾卡片样式 */
.guest-card {
    margin-bottom: 32px;
    border: none;
    box-shadow: none;
    background: transparent;
}

.guest-card :deep(.el-card__body) {
    padding: 0;
    border: none;
    background: transparent;
}

.category-section {
    margin-bottom: 40px;
    padding-bottom: 20px;
    border-bottom: 2px solid #b6c7ea;
}

.category-section:last-child {
    border-bottom: none;
    margin-bottom: 0;
}

.category-title {
    font-size: 20px;
    font-weight: 600;
    color: #0167A5;
    margin: 0 0 16px 0;
    padding-bottom: 8px;
    border-bottom: 2px solid #e6f3ff;
}

.guest-list {
    list-style: none;
    padding: 0;
    margin: 0;
}

.guest-list li {
    padding: 8px 0;
    color: #555;
    line-height: 1.6;
    display: flex;
    align-items: flex-start;
    gap: 8px;
}

.bullet {
    color: #0167A5;
    font-weight: bold;
    margin-top: 2px;
    flex-shrink: 0;
}

/* 空状态样式 */
.empty-state {
    text-align: center;
    padding: 60px 20px;
    color: #999;
    font-size: 16px;
}

/* 响应式设计 */
@media (max-width: 768px) {
    .page-title {
        font-size: 24px;
    }

    .main-content {
        padding: 16px 0;
    }

    .container {
        padding: 0 16px;
    }
}
</style>