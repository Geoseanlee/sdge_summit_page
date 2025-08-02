<template>
  <div class="rich-text-editor">
    <QuillEditor
      ref="quillEditor"
      :content="content"
      :options="editorOptions"
      @update:content="onContentChange"
      @textChange="onTextChange"
      @ready="onReady"
      style="height: 400px;"
      contentType="html"
    />
  </div>
</template>

<script setup>
import { ref, reactive, watch, onMounted } from 'vue'
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import { ElMessage } from 'element-plus'
import { uploadImage } from '@/api'

const props = defineProps({
  modelValue: {
    type: String,
    default: ''
  },
  placeholder: {
    type: String,
    default: '请输入内容...'
  },
  height: {
    type: String,
    default: '400px'
  }
})

const emit = defineEmits(['update:modelValue'])

const quillEditor = ref(null)
const content = ref(typeof props.modelValue === 'string' ? props.modelValue : '')

// 富文本编辑器配置
const editorOptions = reactive({
  theme: 'snow',
  placeholder: props.placeholder,
  modules: {
    toolbar: {
      container: [
        ['bold', 'italic', 'underline', 'strike'],
        ['blockquote', 'code-block'],
        [{ 'header': 1 }, { 'header': 2 }],
        [{ 'list': 'ordered' }, { 'list': 'bullet' }],
        [{ 'script': 'sub' }, { 'script': 'super' }],
        [{ 'indent': '-1' }, { 'indent': '+1' }],
        [{ 'direction': 'rtl' }],
        [{ 'size': ['small', false, 'large', 'huge'] }],
        [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
        [{ 'color': [] }, { 'background': [] }],
        [{ 'font': [] }],
        [{ 'align': [] }],
        ['link', 'image', 'video'],
        ['clean']
      ],
      handlers: {
        image: imageHandler
      }
    }
  }
})

// 监听props变化
watch(() => props.modelValue, (newVal) => {
  const stringVal = typeof newVal === 'string' ? newVal : ''
  if (stringVal !== content.value) {
    content.value = stringVal
  }
})

// 监听内容变化
watch(content, (newVal) => {
  // 确保emit的内容是字符串格式
  emit('update:modelValue', typeof newVal === 'string' ? newVal : '')
})

// 内容变化处理
const onContentChange = (newContent) => {
  // 确保内容是字符串格式
  content.value = typeof newContent === 'string' ? newContent : ''
}

const onTextChange = (delta, oldDelta, source) => {
  // 可以在这里处理文本变化事件
}

const onReady = (quill) => {
  // 编辑器准备就绪
  console.log('Editor ready', quill)
}

// 图片上传处理
async function imageHandler() {
  const input = document.createElement('input')
  input.setAttribute('type', 'file')
  input.setAttribute('accept', 'image/*')
  input.click()

  input.onchange = async () => {
    const file = input.files[0]
    if (!file) return

    if (!file.type.startsWith('image/')) {
      ElMessage.error('请选择图片文件')
      return
    }

    if (file.size > 5 * 1024 * 1024) {
      ElMessage.error('图片大小不能超过5MB')
      return
    }

    try {
      const loadingMessage = ElMessage.loading('图片上传中...')
      
      const res = await uploadImage(file)
      loadingMessage.close()
      
      if (res.code === 200) {
        const quill = quillEditor.value.getQuill()
        const range = quill.getSelection()
        const imageUrl = res.data.fileUrl
        
        quill.insertEmbed(range.index, 'image', imageUrl)
        quill.setSelection(range.index + 1)
        
        ElMessage.success('图片上传成功')
      } else {
        ElMessage.error(`图片上传失败：${res.message}`)
      }
    } catch (error) {
      ElMessage.error(`图片上传失败：${error.message || '网络错误'}`)
    }
  }
}

// 获取纯文本内容
const getText = () => {
  if (quillEditor.value) {
    return quillEditor.value.getQuill().getText()
  }
  return ''
}

// 获取HTML内容
const getHTML = () => {
  return content.value
}

// 设置内容
const setContent = (newContent) => {
  content.value = newContent
}

// 清空内容
const clear = () => {
  content.value = ''
}

// 暴露方法给父组件
defineExpose({
  getText,
  getHTML,
  setContent,
  clear
})
</script>

<style scoped>
.rich-text-editor {
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  overflow: hidden;
}

.rich-text-editor :deep(.ql-container) {
  font-size: 14px;
  line-height: 1.5;
}

.rich-text-editor :deep(.ql-editor) {
  min-height: 300px;
  padding: 12px 15px;
}

.rich-text-editor :deep(.ql-editor.ql-blank::before) {
  color: #c0c4cc;
  font-style: normal;
}

.rich-text-editor :deep(.ql-toolbar) {
  border-bottom: 1px solid #dcdfe6;
  background: #fafafa;
}

.rich-text-editor :deep(.ql-toolbar .ql-formats) {
  margin-right: 15px;
}

.rich-text-editor :deep(.ql-toolbar .ql-picker-label) {
  border: none;
}

.rich-text-editor :deep(.ql-toolbar .ql-picker-options) {
  padding: 4px 0;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.rich-text-editor :deep(.ql-snow .ql-tooltip) {
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.rich-text-editor :deep(.ql-snow .ql-tooltip.ql-editing a.ql-action::after) {
  content: '保存';
}

.rich-text-editor :deep(.ql-snow .ql-tooltip a.ql-action::after) {
  content: '编辑';
}

.rich-text-editor :deep(.ql-snow .ql-tooltip a.ql-remove::before) {
  content: '删除';
}

.rich-text-editor :deep(.ql-snow .ql-tooltip[data-mode="link"]::before) {
  content: '请输入链接地址:';
}

.rich-text-editor :deep(.ql-snow .ql-tooltip[data-mode="formula"]::before) {
  content: '请输入公式:';
}

.rich-text-editor :deep(.ql-snow .ql-tooltip[data-mode="video"]::before) {
  content: '请输入视频地址:';
}

/* 富文本格式样式 */
.rich-text-editor :deep(.ql-editor strong) {
  font-weight: bold;
}

.rich-text-editor :deep(.ql-editor em) {
  font-style: italic;
}

.rich-text-editor :deep(.ql-editor u) {
  text-decoration: underline;
}

.rich-text-editor :deep(.ql-editor s) {
  text-decoration: line-through;
}

.rich-text-editor :deep(.ql-editor h1) {
  font-size: 2em;
  font-weight: bold;
  margin: 0.67em 0;
}

.rich-text-editor :deep(.ql-editor h2) {
  font-size: 1.5em;
  font-weight: bold;
  margin: 0.83em 0;
}

.rich-text-editor :deep(.ql-editor h3) {
  font-size: 1.17em;
  font-weight: bold;
  margin: 1em 0;
}

.rich-text-editor :deep(.ql-editor h4) {
  font-size: 1em;
  font-weight: bold;
  margin: 1.33em 0;
}

.rich-text-editor :deep(.ql-editor h5) {
  font-size: 0.83em;
  font-weight: bold;
  margin: 1.67em 0;
}

.rich-text-editor :deep(.ql-editor h6) {
  font-size: 0.67em;
  font-weight: bold;
  margin: 2.33em 0;
}

.rich-text-editor :deep(.ql-editor blockquote) {
  border-left: 4px solid #ccc;
  margin: 1em 0;
  padding: 0.5em 10px;
  background: #f9f9f9;
}

.rich-text-editor :deep(.ql-editor ul) {
  list-style-type: disc;
  padding-left: 1.5em;
}

.rich-text-editor :deep(.ql-editor ol) {
  list-style-type: decimal;
  padding-left: 1.5em;
}

.rich-text-editor :deep(.ql-editor li) {
  margin: 0.5em 0;
}

.rich-text-editor :deep(.ql-editor code) {
  background-color: #f4f4f4;
  padding: 2px 4px;
  border-radius: 4px;
  font-family: 'Courier New', monospace;
}

.rich-text-editor :deep(.ql-editor pre) {
  background-color: #f4f4f4;
  padding: 10px;
  border-radius: 4px;
  overflow-x: auto;
  font-family: 'Courier New', monospace;
}

.rich-text-editor :deep(.ql-editor a) {
  color: #409eff;
  text-decoration: underline;
}

.rich-text-editor :deep(.ql-editor a:hover) {
  color: #66b1ff;
}

.rich-text-editor :deep(.ql-editor img) {
  max-width: 100%;
  height: auto;
}
</style> 