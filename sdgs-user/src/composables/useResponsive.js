import { ref, computed, onMounted, onUnmounted } from 'vue'

/**
 * 响应式设计组合式API
 * 提供断点检测、屏幕尺寸信息和响应式工具
 */
export function useResponsive() {
  // 响应式数据
  const windowWidth = ref(window.innerWidth)
  const windowHeight = ref(window.innerHeight)

  // 断点定义（与CSS变量保持一致）
  const breakpoints = {
    xs: 480,
    sm: 768,
    md: 1024,
    lg: 1280,
    xl: 1440
  }

  // 更新窗口尺寸
  const updateWindowSize = () => {
    windowWidth.value = window.innerWidth
    windowHeight.value = window.innerHeight
  }

  // 计算属性 - 当前断点
  const currentBreakpoint = computed(() => {
    const width = windowWidth.value
    if (width < breakpoints.xs) return 'xs'
    if (width < breakpoints.sm) return 'sm'
    if (width < breakpoints.md) return 'md'
    if (width < breakpoints.lg) return 'lg'
    return 'xl'
  })

  // 计算属性 - 设备类型判断
  const isMobile = computed(() => windowWidth.value < breakpoints.sm)
  const isTablet = computed(() => 
    windowWidth.value >= breakpoints.sm && windowWidth.value < breakpoints.lg
  )
  const isDesktop = computed(() => windowWidth.value >= breakpoints.lg)

  // 计算属性 - 更详细的断点判断
  const isXs = computed(() => windowWidth.value < breakpoints.xs)
  const isSm = computed(() => 
    windowWidth.value >= breakpoints.xs && windowWidth.value < breakpoints.sm
  )
  const isMd = computed(() => 
    windowWidth.value >= breakpoints.sm && windowWidth.value < breakpoints.md
  )
  const isLg = computed(() => 
    windowWidth.value >= breakpoints.md && windowWidth.value < breakpoints.lg
  )
  const isXl = computed(() => windowWidth.value >= breakpoints.lg)

  // 计算属性 - 范围判断
  const isSmallScreen = computed(() => windowWidth.value < breakpoints.md)
  const isMediumScreen = computed(() => 
    windowWidth.value >= breakpoints.md && windowWidth.value < breakpoints.xl
  )
  const isLargeScreen = computed(() => windowWidth.value >= breakpoints.xl)

  // 计算属性 - 方向判断
  const isPortrait = computed(() => windowHeight.value > windowWidth.value)
  const isLandscape = computed(() => windowWidth.value > windowHeight.value)

  // 工具函数 - 根据断点返回不同的值
  const responsive = (values) => {
    const width = windowWidth.value
    
    if (typeof values === 'object') {
      if (width >= breakpoints.xl && values.xl !== undefined) return values.xl
      if (width >= breakpoints.lg && values.lg !== undefined) return values.lg
      if (width >= breakpoints.md && values.md !== undefined) return values.md
      if (width >= breakpoints.sm && values.sm !== undefined) return values.sm
      if (width >= breakpoints.xs && values.xs !== undefined) return values.xs
      return values.default || values.xs || values.sm || values.md || values.lg || values.xl
    }
    
    return values
  }

  // 工具函数 - 根据设备类型返回值
  const device = (values) => {
    if (isMobile.value && values.mobile !== undefined) return values.mobile
    if (isTablet.value && values.tablet !== undefined) return values.tablet
    if (isDesktop.value && values.desktop !== undefined) return values.desktop
    return values.default || values.desktop || values.mobile
  }

  // 工具函数 - 检查是否匹配指定断点
  const matches = (breakpoint) => {
    const width = windowWidth.value
    switch (breakpoint) {
      case 'xs': return width < breakpoints.xs
      case 'sm': return width >= breakpoints.xs && width < breakpoints.sm
      case 'md': return width >= breakpoints.sm && width < breakpoints.md
      case 'lg': return width >= breakpoints.md && width < breakpoints.lg
      case 'xl': return width >= breakpoints.lg
      case 'mobile': return width < breakpoints.sm
      case 'tablet': return width >= breakpoints.sm && width < breakpoints.lg
      case 'desktop': return width >= breakpoints.lg
      default: return false
    }
  }

  // 工具函数 - 检查是否小于指定断点
  const below = (breakpoint) => {
    const width = windowWidth.value
    return width < (breakpoints[breakpoint] || breakpoint)
  }

  // 工具函数 - 检查是否大于指定断点
  const above = (breakpoint) => {
    const width = windowWidth.value
    return width >= (breakpoints[breakpoint] || breakpoint)
  }

  // 工具函数 - 检查是否在指定范围内
  const between = (min, max) => {
    const width = windowWidth.value
    const minWidth = breakpoints[min] || min
    const maxWidth = breakpoints[max] || max
    return width >= minWidth && width <= maxWidth
  }

  // 工具函数 - 获取容器最大宽度
  const getContainerMaxWidth = () => {
    return responsive({
      xs: '100%',
      sm: '720px',
      md: '960px',
      lg: '1200px',
      xl: '1320px'
    })
  }

  // 工具函数 - 获取网格列数
  const getGridCols = (config) => {
    return responsive({
      xs: config.xs || 1,
      sm: config.sm || config.xs || 2,
      md: config.md || config.sm || 3,
      lg: config.lg || config.md || 4,
      xl: config.xl || config.lg || 4
    })
  }

  // 生命周期钩子
  onMounted(() => {
    window.addEventListener('resize', updateWindowSize)
    window.addEventListener('orientationchange', updateWindowSize)
  })

  onUnmounted(() => {
    window.removeEventListener('resize', updateWindowSize)
    window.removeEventListener('orientationchange', updateWindowSize)
  })

  return {
    // 响应式数据
    windowWidth,
    windowHeight,
    currentBreakpoint,
    
    // 设备类型判断
    isMobile,
    isTablet,
    isDesktop,
    
    // 断点判断
    isXs,
    isSm,
    isMd,
    isLg,
    isXl,
    
    // 屏幕尺寸判断
    isSmallScreen,
    isMediumScreen,
    isLargeScreen,
    
    // 方向判断
    isPortrait,
    isLandscape,
    
    // 工具函数
    responsive,
    device,
    matches,
    below,
    above,
    between,
    getContainerMaxWidth,
    getGridCols,
    
    // 断点常量
    breakpoints
  }
}

/**
 * 媒体查询组合式API
 * 提供CSS媒体查询的JavaScript实现
 */
export function useMediaQuery(query) {
  const matches = ref(false)
  let mediaQuery = null

  const updateMatches = () => {
    if (mediaQuery) {
      matches.value = mediaQuery.matches
    }
  }

  onMounted(() => {
    mediaQuery = window.matchMedia(query)
    matches.value = mediaQuery.matches
    
    // 添加监听器
    if (mediaQuery.addEventListener) {
      mediaQuery.addEventListener('change', updateMatches)
    } else {
      // 兼容老版本浏览器
      mediaQuery.addListener(updateMatches)
    }
  })

  onUnmounted(() => {
    if (mediaQuery) {
      if (mediaQuery.removeEventListener) {
        mediaQuery.removeEventListener('change', updateMatches)
      } else {
        // 兼容老版本浏览器
        mediaQuery.removeListener(updateMatches)
      }
    }
  })

  return matches
}

/**
 * 触摸设备检测组合式API
 */
export function useTouchDevice() {
  const isTouchDevice = computed(() => {
    return 'ontouchstart' in window || 
           navigator.maxTouchPoints > 0 || 
           navigator.msMaxTouchPoints > 0
  })

  const hasCoarsePointer = useMediaQuery('(pointer: coarse)')
  const hasFinePointer = useMediaQuery('(pointer: fine)')
  const canHover = useMediaQuery('(hover: hover)')
  const prefersReducedMotion = useMediaQuery('(prefers-reduced-motion: reduce)')
  const prefersHighContrast = useMediaQuery('(prefers-contrast: high)')
  const prefersDarkMode = useMediaQuery('(prefers-color-scheme: dark)')

  return {
    isTouchDevice,
    hasCoarsePointer,
    hasFinePointer,
    canHover,
    prefersReducedMotion,
    prefersHighContrast,
    prefersDarkMode
  }
} 