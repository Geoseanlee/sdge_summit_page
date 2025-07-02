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
                <el-card class="schedule-card">
                    <el-table :data="scheduleData" :span-method="mergeRows" style="width: 100%" size="large" :show-header="false" :cell-style="setCellStyle" class="borderless-table">
                        <el-table-column prop="date" width="120" align="center" :show-overflow-tooltip="false">
                            <template #default="scope">
                                <div class="date-cell">
                                    {{ scope.row.date }}
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column prop="time" width="150" align="center">
                            <template #default="scope">
                                <div class="time-cell">
                                    {{ scope.row.time }}
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column prop="event" align="center">
                            <template #default="scope">
                                <div class="event-cell">
                                    <div class="event-content">
                                        <div class="main-event">
                                            {{ scope.row.event }}
                                        </div>
                                    </div>
                                </div>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>

                <!-- 峰会拟邀请嘉宾 -->
                <h1 class="page-title guest-title">峰会拟邀请嘉宾</h1>
                
                <el-card class="guest-card">
                    <div class="guest-categories">
                        <div v-for="(category, index) in guestCategories" :key="index" class="category-section">
                            <h2 class="category-title">{{ category.title }}</h2>
                            <ul class="guest-list">
                                <li v-for="guest in category.guests" :key="guest">
                                    <span class="bullet">•</span>
                                    {{ guest }}
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
import { ref } from 'vue'
import { House, User } from '@element-plus/icons-vue'

// 响应式数据

// 议程数据
const scheduleData = ref([
    {
        date: '8月24日',
        time: '17:00前',
        event: '入住酒店&领取参展证件',
        dateColor: '#bae1f6',
        timeColor: '#d7f1fc',
        eventColor: '#bae1f6'
    },
    {
        date: '',
        time: '18:00-20:00',
        event: '欢迎晚宴',
        dateColor: '#bae1f6',
        timeColor: '#d7f1fc',
        eventColor: '#bae1f6'
    },
    {
        date: '8月25日',
        time: '',
        event: '峰会开幕致辞',
        dateColor: '#d7f1fc',
        timeColor: '#ffffff',
        eventColor: '#e1f3ff'
    },
    {
        date: '',
        time: '上午',
        event: '主题分享',
        dateColor: '#d7f1fc',
        timeColor: '#ffffff',
        eventColor: '#e1f3ff'
    },
    {
        date: '',
        time: '',
        event: '圆桌对话',
        dateColor: '#d7f1fc',
        timeColor: '#ffffff',
        eventColor: '#bae1f6'
    },
    {
        date: '',
        time: '12:00-13:00',
        event: '午餐',
        dateColor: '#d7f1fc',
        timeColor: '#ffffff',
        eventColor: '#bae1f6'
    },
    {
        date: '',
        time: '',
        event: '',
        dateColor: '#d7f1fc',
        timeColor: '#ffffff',
        eventColor: '#e1f3ff'
    },
    {
        date: '',
        time: '下午',
        event: '各板块分论坛',
        dateColor: '#d7f1fc',
        timeColor: '#ffffff',
        eventColor: '#e1f3ff'
    },
    {
        date: '',
        time: '',
        event: '',
        dateColor: '#d7f1fc',
        timeColor: '#ffffff',
        eventColor: '#e1f3ff'
    },
    {
        date: '',
        time: '18:00-21:00',
        event: '晚宴与颁奖酒会',
        dateColor: '#d7f1fc',
        timeColor: '#ffffff',
        eventColor: '#bae1f6'
    },
    {
        date: '8月26日',
        time: '上午',
        event: '参访大阪吹田德川分病院、NEC日本电气株式会社、无印良品社、日本皇家医疗俱乐部、丰田集团（可选）',
        dateColor: '#bae1f6',
        timeColor: '#d7f1fc',
        eventColor: '#e1f3ff'
    },
    {
        date: '',
        time: '12:00-14:00',
        event: '午餐',
        dateColor: '#bae1f6',
        timeColor: '#d7f1fc',
        eventColor: '#bae1f6'
    },
    {
        date: '',
        time: '下午',
        event: '大咖分享与交流',
        dateColor: '#bae1f6',
        timeColor: '#d7f1fc',
        eventColor: '#e1f3ff'
    }
])

// 嘉宾分类数据
const guestCategories = ref([
    {
        title: '1. 大阪世博会主办方及政府代表——世博会组委会领导',
        guests: [
            '世博会组委会领导（主席、副主席、会长、副会长、秘书长）',
            '日本政府官员（国家级、部级及相关部门领导、如经济产业省、文化厅、文部科学省等）',
            '中国政府相关部门官员（科技、文化、教育、商务等领域）',
            '领事馆/大使馆'
        ]
    },
    {
        title: '2. 国际组织及机构代表',
        guests: [
            '联合国教科文组织（UNESCO）官员',
            '经合组织（OECD）、世界经济论坛（WEF）、国际教育协会（IEA）等国际组织代表',
            '全球科技与创新机构代表（如IEEE、世界人工智能大会等）'
        ]
    },
    {
        title: '3. 世界500强及商界领袖',
        guests: [
            '全球及亚洲500强企业高管（总裁、副总裁、董事等）',
            '重点邀请企业：本田（Honda）、无印良品（MUJI）、丰田（Toyota）、',
            '松下（Panasonic）、索尼（Sony）、软银（SoftBank）等；',
            '中国代表企业：华为、阿里巴巴、腾讯、比亚迪、字节跳动等',
            '中日商会及企业协会领导',
            '日本经济团体联合会（经团联）代表',
            '中国贸促会、日本商工会议所等机构领导',
            '中日商会会长'
        ]
    },
    {
        title: '4. 学术及研究机构代表',
        guests: [
            '国际顶尖高校教授及学者（哈佛、斯坦福、牛津、东京大学、北京大学、清华大学等）',
            '科技及未来趋势专家（人工智能、绿色能源、生命科学等领域）',
            '教育及文化研究专家'
        ]
    },
    {
        title: '5. 体育界及奥运冠军',
        guests: [
            '奥运会、亚运会、世锦赛冠军（跨项目，如体操、游泳、田径等）',
            '日本及中国的体育明星及体育组织负责人'
        ]
    },
    {
        title: '6. 文化、艺术及娱乐界代表',
        guests: [
            '知名艺人及娱乐明星（电影、音乐、戏剧等）',
            '社交媒体及数字内容创作者（各领域KOL、大V、知名网红）',
            '文化学者及艺术家（书画、摄影、雕塑、设计、动画等领域）',
            '知名IP及品牌代表（动漫、游戏、文学IP等）'
        ]
    },
    {
        title: '7. 企业家及行业合作伙伴',
        guests: [
            '上午论坛：大型企业代表（跨国企业、行业龙头企业高管）',
            '下午分论坛：中小型领域企业代表（创新型企业、创业公司、独角兽企业创始人）',
            '投资及金融机构代表（风险投资、私募基金、银行高管）'
        ]
    }
])

// 合并单元格方法
// 合并单元格方法
const mergeRows = ({ row, column, rowIndex, columnIndex }) => {
    if (columnIndex === 0) { // 日期列
        if (rowIndex === 0) {
            return [2, 1] // 8月24日合并2行（第0-1行）
        } else if (rowIndex === 1) {
            return [0, 0] // 隐藏第1行的日期
        } else if (rowIndex === 2) {
            return [8, 1] // 8月25日合并8行（第2-9行）
        } else if (rowIndex >= 3 && rowIndex <= 9) {
            return [0, 0] // 隐藏第3-9行的日期
        } else if (rowIndex === 10) {
            return [3, 1] // 8月26日合并3行（第10-12行）
        } else if (rowIndex >= 11 && rowIndex <= 12) {
            return [0, 0] // 隐藏第11-12行的日期
        }
    }
}

// 设置单元格样式
const setCellStyle = ({ row, column }) => {
    if (column.property === 'date') {
        return { backgroundColor: row.dateColor }
    } else if (column.property === 'time') {
        return { backgroundColor: row.timeColor }
    } else if (column.property === 'event') {
        return { backgroundColor: row.eventColor }
    }
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