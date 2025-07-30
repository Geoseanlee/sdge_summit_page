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
                    <el-table v-else :data="scheduleData" :span-method="mergeRows" style="width: 100%" size="large"
                        :show-header="false" :cell-style="setCellStyle" class="borderless-table">
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
                        <div v-for="(category, index) in guestCategories" :key="category.id || index"
                            class="category-section">
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

// 获取峰会完整数据（包含议程和嘉宾）
const fetchSummitData = async () => {
    try {
        loading.value = true
        const response = await request.get('/summit/complete')
        if (response && response.schedule && response.guests) {
            // 处理议程数据 - 按日期分组并排序
            if (Array.isArray(response.schedule)) {
                // 首先按日期分组
                const scheduleGroups = {}
                response.schedule.forEach(item => {
                    const date = item.date || '未分类'
                    if (!scheduleGroups[date]) {
                        scheduleGroups[date] = []
                    }
                    scheduleGroups[date].push(item)
                })

                // 对每个分组内的数据按ID排序
                Object.keys(scheduleGroups).forEach(date => {
                    scheduleGroups[date].sort((a, b) => (a.id || 0) - (b.id || 0))
                })

                // 按日期排序分组
                const sortedDates = Object.keys(scheduleGroups).sort((a, b) => {
                    // 未分类排在最后
                    if (a === '未分类') return 1
                    if (b === '未分类') return -1

                    // 提取日期中的数字进行比较
                    const extractNumber = (dateStr) => {
                        const match = dateStr.match(/(\d+)月(\d+)日/)
                        if (match) {
                            return parseInt(match[1]) * 100 + parseInt(match[2])
                        }
                        return 0
                    }

                    return extractNumber(a) - extractNumber(b)
                })

                // 重新组织数据，每个日期组的第一项显示日期，其他项日期为空
                const flatScheduleData = []
                sortedDates.forEach(date => {
                    const items = scheduleGroups[date]
                    items.forEach((item, index) => {
                        flatScheduleData.push({
                            ...item,
                            date: index === 0 ? date : '', // 只有第一项显示日期
                            time: item.time || '',
                            event: item.event || ''
                        })
                    })
                })

                scheduleData.value = flatScheduleData
            } else {
                scheduleData.value = []
            }

            // 处理嘉宾数据
            if (Array.isArray(response.guests)) {
                // 按分类组织嘉宾数据
                const guestMap = new Map()
                response.guests.forEach(guest => {
                    if (!guestMap.has(guest.categoryTitle)) {
                        guestMap.set(guest.categoryTitle, [])
                    }
                    guestMap.get(guest.categoryTitle).push(guest.guestName)
                })

                guestCategories.value = Array.from(guestMap.entries()).map(([title, guests]) => ({
                    title,
                    guests
                }))
            } else {
                guestCategories.value = []
            }
        } else {
            // 如果后端返回null或空数据，使用默认数据
            scheduleData.value = []
            guestCategories.value = []
        }
    } catch (error) {
        console.error('获取峰会数据失败:', error)
        ElMessage.error('获取峰会数据失败')
        scheduleData.value = []
        guestCategories.value = []
    } finally {
        loading.value = false
    }
}

// 页面挂载时获取数据
onMounted(() => {
    fetchSummitData()
})

// 合并单元格方法
const mergeRows = ({ row, column, rowIndex, columnIndex }) => {
    if (columnIndex === 0 && scheduleData.value.length > 0) { // 日期列
        const currentDate = scheduleData.value[rowIndex]?.date

        // 如果当前行有日期值，计算需要合并的行数
        if (currentDate && currentDate.trim() !== '') {
            let mergeCount = 1

            // 向下查找相同日期组的行（空日期属于同一天）
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
            // 当前行是空日期，需要隐藏（属于上一个有日期的行）
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
            // 遇到有日期的行，表示新的日期组开始
            if (i > 0) {
                // 检查之前是否已经有其他日期组了
                let hasDateBefore = false
                for (let j = 0; j < i; j++) {
                    if (scheduleData.value[j]?.date && scheduleData.value[j].date.trim() !== '') {
                        hasDateBefore = true
                        break
                    }
                }
                if (hasDateBefore) {
                    currentDateGroupIndex++
                }
            }
            currentGroupStartIndex = i
        }

        if (i === rowIndex) {
            indexInGroup = i - currentGroupStartIndex
        }
    }

    // 日期列和时间列的颜色逻辑
    const isEvenGroup = currentDateGroupIndex % 2 === 0
    const dateColor = isEvenGroup ? '#bae1f6' : '#d7f1fc'
    const timeColor = isEvenGroup ? '#d7f1fc' : '#ffffff'

    // 事件列的颜色逻辑 - 根据实际日期组的内容确定
    let eventColor = '#bae1f6' // 默认值

    // 获取当前日期组的标识
    let currentDateName = ''
    for (let i = currentGroupStartIndex; i >= 0; i--) {
        if (scheduleData.value[i]?.date && scheduleData.value[i].date.trim() !== '') {
            currentDateName = scheduleData.value[i].date
            break
        }
    }

    if (currentDateName.includes('8月24日')) {
        // 8月24日组：全部 #bae1f6
        eventColor = '#bae1f6'
    } else if (currentDateName.includes('8月25日')) {
        // 8月25日组：交替颜色
        eventColor = indexInGroup % 2 === 0 ? '#e1f3ff' : '#bae1f6'
    } else if (currentDateName.includes('8月26日')) {
        // 8月26日组：单独一项 #e1f3ff
        eventColor = '#e1f3ff'
    } else if (currentDateName === '未分类') {
        // 未分类组：复杂交替模式
        // 根据在未分类组内的索引决定颜色
        if (indexInGroup === 0) {
            eventColor = '#bae1f6' // 圆桌对话
        } else if (indexInGroup === 1) {
            eventColor = '#e1f3ff' // 午餐
        } else if (indexInGroup === 2) {
            eventColor = '#bae1f6' // 各板块分论坛
        } else if (indexInGroup === 3) {
            eventColor = '#e1f3ff' // 晚宴与颁奖酒会
        } else if (indexInGroup === 4) {
            eventColor = '#bae1f6' // 午餐
        } else if (indexInGroup === 5) {
            eventColor = '#e1f3ff' // 大咖分享与交流
        } else {
            eventColor = indexInGroup % 2 === 0 ? '#bae1f6' : '#e1f3ff'
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
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 80px;
}

.main-content {
    padding: 32px 0;
}

.page-title {
    font-size: 32px;
    font-weight: 600;
    color: #0167A5;
    margin: 32px 0 48px 0;
    text-align: center;
}

.guest-title {
    margin: 80px 0 48px 0;
}

.empty-state {
    text-align: center;
    padding: 40px 20px;
    color: #999;
    font-size: 16px;
}



/* 议程卡片样式 */
.schedule-card {
    margin-bottom: 80px;
    border: none;
    box-shadow: none;
    background: transparent;
    max-width: 1000px;
    margin-left: auto;
    margin-right: auto;
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
    max-width: 1000px;
    margin-left: auto;
    margin-right: auto;
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
        padding: 0 40px;
        max-width: 100%;
    }

    .schedule-card,
    .guest-card {
        max-width: 100%;
    }

    .guest-title {
        margin: 60px 0 32px 0;
    }
}

@media (max-width: 480px) {
    .container {
        padding: 0 20px;
    }

    .page-title {
        font-size: 20px;
        margin: 24px 0 32px 0;
    }

    .guest-title {
        margin: 40px 0 24px 0;
    }
}
</style>