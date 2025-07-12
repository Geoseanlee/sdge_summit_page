import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/layout/Layout.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: Layout,
      redirect: '/home',
      children: [
        {
          path: 'home',
          name: 'home',
          component: () => import('../views/HomeView.vue')
        },
        {
          path: 'expo',
          name: 'expo',
          component: () => import('../views/ExpoView.vue')
        },
        {
          path: 'expo/background',
          name: 'expo-background',
          component: () => import('../views/expo/BackgroundView.vue')
        },
        {
          path: 'expo/summit',
          name: 'expo-summit',
          component: () => import('../views/expo/SummitView.vue')
        },
        {
          path: 'roundtable',
          name: 'roundtable',
          component: () => import('../views/RoundtableView.vue')
        },
        {
          path: 'updates',
          name: 'updates',
          component: () => import('../views/UpdatesView.vue')
        },
        {
          path: 'about',
          name: 'about',
          component: () => import('../views/AboutView.vue')
        },
        {
          path: 'about/more',
          name: 'about-more',
          component: () => import('../views/about/MoreView.vue')
        },
        {
          path: 'join',
          name: 'join',
          component: () => import('../views/JoinView.vue')
        },
        {
          path: 'image-management',
          name: 'image-management',
          component: () => import('../views/ImageManagementView.vue')
        },
        {
          path: 'account',
          name: 'account',
          component: () => import('../views/AccountView.vue')
        }
      ]
    }
  ]
})

export default router
