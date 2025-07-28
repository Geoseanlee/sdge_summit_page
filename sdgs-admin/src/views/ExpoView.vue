<template>
  <div class="expo-home-admin">
    <h2>世博会主页信息管理</h2>

    <form @submit.prevent="saveChanges">
      <!-- 主区域 -->
      <div class="form-section">
        <h3>主区域</h3>
        <div class="form-group">
          <label for="title">主标题</label>
          <input type="text" id="title" v-model="expoData.title" />
        </div>
        <div class="form-group">
          <label for="subtitle">副标题</label>
          <input type="text" id="subtitle" v-model="expoData.subtitle" />
        </div>
        <div class="form-group">
          <label for="description">描述文字</label>
          <textarea id="description" rows="5" v-model="expoData.description"></textarea>
        </div>
        <div class="form-group">
          <label for="imageUrl">主图片</label>
          <div class="image-uploader">
            <img :src="expoData.imageUrl" v-if="expoData.imageUrl" class="image-preview" alt="主图片预览" />
            <div v-else class="image-placeholder">暂无图片</div>
            <input type="file" @change="e => handleImageUpload('imageUrl', e)" accept="image/*" style="display: none;"
              :ref="el => (fileInputs['imageUrl'] = el)" />
            <button @click.prevent="triggerFileInput('imageUrl')" class="upload-button">更换图片</button>
          </div>
        </div>
      </div>

      <!-- 大卡片区域 -->
      <div class="form-section">
        <h3>大卡片区域</h3>
        <div class="form-group">
          <label for="cardTitle">卡片主标题</label>
          <input type="text" id="cardTitle" v-model="expoData.cardTitle" />
        </div>
        <div class="form-group">
          <label for="cardSubtitle">卡片副标题</label>
          <input type="text" id="cardSubtitle" v-model="expoData.cardSubtitle" />
        </div>
        <div class="form-group">
          <label for="cardDescription">卡片描述</label>
          <textarea id="cardDescription" rows="3" v-model="expoData.cardDescription"></textarea>
        </div>
        <div class="form-group">
          <label for="cardImageUrl">卡片图片</label>
          <div class="image-uploader">
            <img :src="expoData.cardImageUrl" v-if="expoData.cardImageUrl" class="image-preview" alt="卡片图片预览" />
            <div v-else class="image-placeholder">暂无图片</div>
            <input type="file" @change="e => handleImageUpload('cardImageUrl', e)" accept="image/*"
              style="display: none;" :ref="el => (fileInputs['cardImageUrl'] = el)" />
            <button @click.prevent="triggerFileInput('cardImageUrl')" class="upload-button">更换图片</button>
          </div>
        </div>
      </div>

      <!-- Section 1 -->
      <div class="form-section">
        <h3>内容区块 1</h3>
        <div class="form-group">
          <label for="sectionTitle1">区块1 标题</label>
          <input type="text" id="sectionTitle1" v-model="expoData.sectionTitle1" />
        </div>
        <div class="form-group">
          <label for="sectionImageUrl1">区块1 图片</label>
          <div class="image-uploader">
            <img :src="expoData.sectionImageUrl1" v-if="expoData.sectionImageUrl1" class="image-preview"
              alt="区块1图片预览" />
            <div v-else class="image-placeholder">暂无图片</div>
            <input type="file" @change="e => handleImageUpload('sectionImageUrl1', e)" accept="image/*"
              style="display: none;" :ref="el => (fileInputs['sectionImageUrl1'] = el)" />
            <button @click.prevent="triggerFileInput('sectionImageUrl1')" class="upload-button">更换图片</button>
          </div>
        </div>
      </div>

      <!-- Section 2 -->
      <div class="form-section">
        <h3>内容区块 2</h3>
        <div class="form-group">
          <label for="sectionTitle2">区块2 标题</label>
          <input type="text" id="sectionTitle2" v-model="expoData.sectionTitle2" />
        </div>
        <div class="form-group">
          <label for="sectionImageUrl2">区块2 图片</label>
          <div class="image-uploader">
            <img :src="expoData.sectionImageUrl2" v-if="expoData.sectionImageUrl2" class="image-preview"
              alt="区块2图片预览" />
            <div v-else class="image-placeholder">暂无图片</div>
            <input type="file" @change="e => handleImageUpload('sectionImageUrl2', e)" accept="image/*"
              style="display: none;" :ref="el => (fileInputs['sectionImageUrl2'] = el)" />
            <button @click.prevent="triggerFileInput('sectionImageUrl2')" class="upload-button">更换图片</button>
          </div>
        </div>
      </div>

      <!-- Section 3 -->
      <div class="form-section">
        <h3>内容区块 3</h3>
        <div class="form-group">
          <label for="sectionTitle3">区块3 标题</label>
          <input type="text" id="sectionTitle3" v-model="expoData.sectionTitle3" />
        </div>
        <div class="form-group">
          <label for="sectionImageUrl3">区块3 图片</label>
          <div class="image-uploader">
            <img :src="expoData.sectionImageUrl3" v-if="expoData.sectionImageUrl3" class="image-preview"
              alt="区块3图片预览" />
            <div v-else class="image-placeholder">暂无图片</div>
            <input type="file" @change="e => handleImageUpload('sectionImageUrl3', e)" accept="image/*"
              style="display: none;" :ref="el => (fileInputs['sectionImageUrl3'] = el)" />
            <button @click.prevent="triggerFileInput('sectionImageUrl3')" class="upload-button">更换图片</button>
          </div>
        </div>
      </div>

      <div class="button-group">
        <button type="submit" class="save-button">保存更改</button>
        <button type="button" class="reload-button" @click="reloadData">重新加载</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue';
import { getExpoInfo, updateExpoInfo, uploadImage } from '@/api';
import { ElNotification, ElMessageBox } from 'element-plus';

const expoData = ref({
  id: null, // 添加id字段来存储记录的ID
  title: '',
  subtitle: '',
  description: '',
  imageUrl: '',
  cardTitle: '',
  cardSubtitle: '',
  cardDescription: '',
  cardImageUrl: '',
  sectionTitle1: '',
  sectionImageUrl1: '',
  sectionTitle2: '',
  sectionImageUrl2: '',
  sectionTitle3: '',
  sectionImageUrl3: '',
});

const fileInputs = reactive({});

const triggerFileInput = (fieldName) => {
  fileInputs[fieldName].click();
};

const handleImageUpload = async (fieldName, event) => {
  const file = event.target.files[0];
  if (!file) return;

  try {
    const res = await uploadImage(file);
    if (res.code === 200 && res.data.fileUrl) {
      expoData.value[fieldName] = res.data.fileUrl;
      ElNotification({
        title: '成功',
        message: '图片上传成功！',
        type: 'success',
        duration: 3000
      });
    } else {
      ElNotification({
        title: '错误',
        message: `图片上传失败: ${res.message}`,
        type: 'error',
        duration: 4000
      });
    }
  } catch (error) {
    console.error('图片上传失败:', error);
    ElNotification({
      title: '错误',
      message: '图片上传过程中发生错误！',
      type: 'error',
      duration: 4000
    });
  }
};

// 获取数据
const fetchData = async () => {
  try {
    const res = await getExpoInfo();
    if (res.code === 200 && res.data) {
      expoData.value = res.data;
    } else {
      // 如果没有数据，可能需要提示用户进行初始化创建
      console.log('尚未配置世博会信息');
    }
  } catch (error) {
    console.error('获取世博会信息失败:', error);
    ElNotification({
      title: '错误',
      message: '数据加载失败！',
      type: 'error',
      duration: 4000
    });
  }
};

// 在组件挂载时获取数据
onMounted(() => {
  fetchData();
});

// 重新加载数据
const reloadData = async () => {
  try {
    await ElMessageBox.confirm(
      '确定要重新加载数据吗？这将丢失所有未保存的修改！',
      '确认重新加载',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
    );
    await fetchData();
    ElNotification({
      title: '成功',
      message: '数据已重新加载！',
      type: 'success',
      duration: 3000
    });
  } catch (error) {
    // 用户取消操作，不需要处理
    if (error !== 'cancel') {
      console.error('重新加载数据失败:', error);
    }
  }
};

const saveChanges = async () => {
  if (!expoData.value.id) {
    ElNotification({
      title: '错误',
      message: '数据ID不存在，无法保存！请先创建一条记录。',
      type: 'error',
      duration: 4000
    });
    // 此处可以引导至创建页面或实现创建逻辑
    return;
  }
  try {
    const res = await updateExpoInfo(expoData.value.id, expoData.value);
    if (res.code === 200) {
      ElNotification({
        title: '成功',
        message: '数据保存成功！',
        type: 'success',
        duration: 3000
      });
    } else {
      ElNotification({
        title: '错误',
        message: `保存失败: ${res.message}`,
        type: 'error',
        duration: 4000
      });
    }
  } catch (error) {
    console.error('保存失败:', error);
    ElNotification({
      title: '错误',
      message: '保存过程中发生错误！',
      type: 'error',
      duration: 4000
    });
  }
};
</script>

<style scoped>
.expo-home-admin {
  padding: 2rem;
  font-family: 'Arial', sans-serif;
}

h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: #333;
}

.form-section {
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 1.5rem;
  margin-bottom: 2rem;
}

.form-section h3 {
  margin-top: 0;
  margin-bottom: 1.5rem;
  border-bottom: 1px solid #eee;
  padding-bottom: 0.5rem;
  color: #555;
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

.form-group input[type="text"],
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

.save-button {
  width: 48%;
  padding: 1rem;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  font-weight: bold;
  transition: background-color 0.3s;
}

.save-button:hover {
  background-color: #45a049;
}

.reload-button {
  width: 48%;
  padding: 1rem;
  background-color: #ff9800;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  font-weight: bold;
  transition: background-color 0.3s;
}

.reload-button:hover {
  background-color: #f57c00;
}

.button-group {
  display: flex;
  justify-content: space-between;
  gap: 1rem;
}

.image-uploader {
  margin-top: 0.5rem;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  padding: 1rem;
  text-align: center;
}

.image-preview {
  max-width: 100%;
  max-height: 200px;
  margin-bottom: 1rem;
  border-radius: 4px;
}

.image-placeholder {
  width: 100%;
  height: 150px;
  line-height: 150px;
  background-color: #f5f5f5;
  color: #999;
  border-radius: 4px;
  margin-bottom: 1rem;
}

.upload-button {
  background-color: #1890ff;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
}

.upload-button:hover {
  background-color: #40a9ff;
}
</style>