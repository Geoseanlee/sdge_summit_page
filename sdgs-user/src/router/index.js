import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView,
        },
        {
            path: '/expo',
            name: 'expo',
            component: () => import('../views/ExpoView.vue'),
        },
        {
            path: '/expo/background',
            name: 'expo-background',
            component: () => import('../views/expo/ExpoBackgroundView.vue'),
        },
        {
            path: '/expo/summit',
            name: 'expo-summit',
            component: () => import('../views/expo/ExpoSummitView.vue'),
        },
        {
            path: '/roundtable',
            name: 'roundtable',
            component: () => import('../views/RoundtableView.vue'),
        },
        {
            path: '/news',
            name: 'news',
            component: () => import('../views/NewsView.vue'),
        },
        {
            path: '/news/:id',
            name: 'news-detail',
            component: () => import('../views/news/NewsDetailView.vue'),
            props: true,
        },
        {
            path: '/about',
            name: 'about',
            component: () => import('../views/AboutView.vue'),
        },
        {
            path: '/about/more',
            name: 'about-more',
            component: () => import('../views/about/AboutMoreView.vue'),
        },
        {
            path: '/join-us',
            name: 'join-us',
            component: () => import('../views/JoinUsView.vue'),
        },
        {
            path: '/test-oss',
            name: 'test-oss',
            component: () => import('../views/TestOssView.vue'),
        },
    ],
})

export default router
