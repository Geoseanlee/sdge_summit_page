<template>
  <div class="home-admin">
    <h2>首页信息管理</h2>

    <form @submit.prevent="saveChanges">
      <!-- 顶部区域 -->
      <div class="form-section">
        <h3>顶部区域</h3>
        <div class="form-group">
          <label for="heroTitle1">标题1</label>
          <input type="text" id="heroTitle1" v-model="homeData.heroTitle1" />
        </div>
        <div class="form-group">
          <label for="heroSubtitle1">副标题1</label>
          <input type="text" id="heroSubtitle1" v-model="homeData.heroSubtitle1" />
        </div>
        <div class="form-group">
          <label for="heroTitle2">标题2</label>
          <input type="text" id="heroTitle2" v-model="homeData.heroTitle2" />
        </div>
        <div class="form-group">
          <label for="heroSubtitle2">副标题2</label>
          <input type="text" id="heroSubtitle2" v-model="homeData.heroSubtitle2" />
        </div>
        <div class="form-group">
          <label for="heroImageUrl">顶部区域图片</label>
          <div class="image-uploader">
            <img :src="homeData.heroImageUrl" v-if="homeData.heroImageUrl" class="image-preview" alt="顶部区域图片预览" />
            <div v-else class="image-placeholder">暂无图片</div>
            <input type="file" @change="e => handleImageUpload('heroImageUrl', e)" accept="image/*"
              style="display: none;" :ref="el => (fileInputs['heroImageUrl'] = el)" />
            <button @click.prevent="triggerFileInput('heroImageUrl')" class="upload-button">更换图片</button>
          </div>
        </div>
      </div>

      <!-- SDGs介绍区域 -->
      <div class="form-section">
        <h3>SDGs介绍区域</h3>
        <div class="form-group">
          <label for="sdgsDescription1">SDGs描述1</label>
          <textarea id="sdgsDescription1" rows="4" v-model="homeData.sdgsDescription1"></textarea>
        </div>
        <div class="form-group">
          <label for="sdgsDescription2">SDGs描述2</label>
          <textarea id="sdgsDescription2" rows="4" v-model="homeData.sdgsDescription2"></textarea>
        </div>
      </div>

      <!-- 探索区域（世博会介绍） -->
      <div class="form-section">
        <h3>探索区域（世博会介绍）</h3>
        <div class="form-group">
          <label for="expoTitle">世博会标题</label>
          <input type="text" id="expoTitle" v-model="homeData.expoTitle" />
        </div>
        <div class="form-group">
          <label for="expoSubtitle">世博会副标题</label>
          <input type="text" id="expoSubtitle" v-model="homeData.expoSubtitle" />
        </div>
        <div class="form-group">
          <label for="expoImageUrl">世博会背景图片</label>
          <div class="image-uploader">
            <img :src="homeData.expoImageUrl" v-if="homeData.expoImageUrl" class="image-preview" alt="世博会背景图片预览" />
            <div v-else class="image-placeholder">暂无图片</div>
            <input type="file" @change="e => handleImageUpload('expoImageUrl', e)" accept="image/*"
              style="display: none;" :ref="el => (fileInputs['expoImageUrl'] = el)" />
            <button @click.prevent="triggerFileInput('expoImageUrl')" class="upload-button">更换图片</button>
          </div>
        </div>
      </div>

      <!-- 联盟介绍区域 -->
      <div class="form-section">
        <h3>世博全球青年联盟介绍</h3>
        <div class="form-group">
          <label for="allianceTitle">联盟标题</label>
          <input type="text" id="allianceTitle" v-model="homeData.allianceTitle" />
        </div>
        <div class="form-group">
          <label for="allianceDescription1">联盟描述1</label>
          <textarea id="allianceDescription1" rows="4" v-model="homeData.allianceDescription1"></textarea>
        </div>
        <div class="form-group">
          <label for="allianceDescription2">联盟描述2</label>
          <textarea id="allianceDescription2" rows="4" v-model="homeData.allianceDescription2"></textarea>
        </div>
      </div>

      <!-- 相关数据区域 -->
      <div class="form-section">
        <h3>相关行动数据区域</h3>
        <div class="form-group">
          <label for="dataTitle">数据区域标题</label>
          <input type="text" id="dataTitle" v-model="homeData.dataTitle" />
        </div>
        <div class="form-group">
          <label for="dataImageUrl">数据图表图片</label>
          <div class="image-uploader">
            <img :src="homeData.dataImageUrl" v-if="homeData.dataImageUrl" class="image-preview" alt="数据图表图片预览" />
            <div v-else class="image-placeholder">暂无图片</div>
            <input type="file" @change="e => handleImageUpload('dataImageUrl', e)" accept="image/*"
              style="display: none;" :ref="el => (fileInputs['dataImageUrl'] = el)" />
            <button @click.prevent="triggerFileInput('dataImageUrl')" class="upload-button">更换图片</button>
          </div>
        </div>
      </div>

      <!-- 加入我们区域 -->
      <div class="form-section">
        <h3>加入我们区域</h3>
        <div class="form-group">
          <label for="joinTitle">加入我们标题</label>
          <input type="text" id="joinTitle" v-model="homeData.joinTitle" />
        </div>
        <div class="form-group">
          <label for="joinItem1">加入项目1</label>
          <input type="text" id="joinItem1" v-model="homeData.joinItem1" />
        </div>
        <div class="form-group">
          <label for="joinItem2">加入项目2</label>
          <input type="text" id="joinItem2" v-model="homeData.joinItem2" />
        </div>
        <div class="form-group">
          <label for="joinItem3">加入项目3</label>
          <input type="text" id="joinItem3" v-model="homeData.joinItem3" />
        </div>
        <div class="form-group">
          <label for="joinImageUrl">加入我们图片</label>
          <div class="image-uploader">
            <img :src="homeData.joinImageUrl" v-if="homeData.joinImageUrl" class="image-preview" alt="加入我们图片预览" />
            <div v-else class="image-placeholder">暂无图片</div>
            <input type="file" @change="e => handleImageUpload('joinImageUrl', e)" accept="image/*"
              style="display: none;" :ref="el => (fileInputs['joinImageUrl'] = el)" />
            <button @click.prevent="triggerFileInput('joinImageUrl')" class="upload-button">更换图片</button>
          </div>
        </div>
      </div>

      <button type="submit" class="save-button">保存更改</button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue';
import { getHomeInfo, updateHomeInfo, uploadImage } from '@/api';

const homeData = ref({
  id: null,
  heroTitle1: '',
  heroSubtitle1: '',
  heroTitle2: '',
  heroSubtitle2: '',
  heroImageUrl: '',
  sdgsDescription1: '',
  sdgsDescription2: '',
  expoTitle: '',
  expoSubtitle: '',
  expoImageUrl: '',
  allianceTitle: '',
  allianceDescription1: '',
  allianceDescription2: '',
  dataTitle: '',
  dataImageUrl: '',
  joinTitle: '',
  joinItem1: '',
  joinItem2: '',
  joinItem3: '',
  joinImageUrl: '',
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
      homeData.value[fieldName] = res.data.fileUrl;
      alert('图片上传成功！');
    } else {
      alert(`图片上传失败: ${res.message}`);
    }
  } catch (error) {
    console.error('图片上传失败:', error);
    alert('图片上传过程中发生错误！');
  }
};

// 获取数据
const fetchData = async () => {
  try {
    const res = await getHomeInfo();
    if (res.code === 200 && res.data) {
      homeData.value = res.data;
    } else {
      // 如果没有数据，可能需要提示用户进行初始化创建
      console.log('尚未配置首页信息');
    }
  } catch (error) {
    console.error('获取首页信息失败:', error);
    alert('数据加载失败！');
  }
};

// 在组件挂载时获取数据
onMounted(() => {
  fetchData();
});

const saveChanges = async () => {
  if (!homeData.value.id) {
    alert('数据ID不存在，无法保存！请先创建一条记录。');
    return;
  }
  try {
    const res = await updateHomeInfo(homeData.value.id, homeData.value);
    if (res.code === 200) {
      alert('数据保存成功！');
    } else {
      alert(`保存失败: ${res.message}`);
    }
  } catch (error) {
    console.error('保存失败:', error);
    alert('保存过程中发生错误！');
  }
};
</script>

<style scoped>
.home-admin {
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
  display: block;
  width: 100%;
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
