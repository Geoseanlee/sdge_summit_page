<template>
  <div class="background-admin">
    <h2>世博会背景页面管理</h2>

    <form @submit.prevent="saveChanges">
      <!-- 第一部分 -->
      <div class="form-section">
        <h3>第一部分</h3>
        <div class="form-group">
          <label for="part1MainTitle">主标题</label>
          <textarea id="part1MainTitle" rows="2" v-model="backgroundData.part1MainTitle"></textarea>
        </div>

        <!-- 子部分1 -->
        <div class="sub-section">
          <h4>子部分1</h4>
          <div class="form-group">
            <label for="part1Section1Title">标题</label>
            <textarea id="part1Section1Title" rows="2" v-model="backgroundData.part1Section1Title"></textarea>
          </div>
          <div class="form-group">
            <label for="part1Section1Content">内容</label>
            <textarea id="part1Section1Content" rows="5" v-model="backgroundData.part1Section1Content"></textarea>
          </div>
          <div class="form-group">
            <label for="part1Section1Image">图片</label>
            <div class="image-uploader">
              <img :src="backgroundData.part1Section1Image" v-if="backgroundData.part1Section1Image" class="image-preview" alt="子部分1图片预览"/>
              <div v-else class="image-placeholder">暂无图片</div>
              <input type="file" @change="e => handleImageUpload('part1Section1Image', e)" accept="image/*" style="display: none;" :ref="el => (fileInputs['part1Section1Image'] = el)" />
              <button @click.prevent="triggerFileInput('part1Section1Image')" class="upload-button">更换图片</button>
            </div>
          </div>
        </div>

        <!-- 子部分2 -->
        <div class="sub-section">
          <h4>子部分2</h4>
          <div class="form-group">
            <label for="part1Section2Title">标题</label>
            <textarea id="part1Section2Title" rows="2" v-model="backgroundData.part1Section2Title"></textarea>
          </div>
          <div class="form-group">
            <label for="part1Section2Content">内容</label>
            <textarea id="part1Section2Content" rows="5" v-model="backgroundData.part1Section2Content"></textarea>
          </div>
          <div class="form-group">
            <label for="part1Section2Image">图片</label>
            <div class="image-uploader">
              <img :src="backgroundData.part1Section2Image" v-if="backgroundData.part1Section2Image" class="image-preview" alt="子部分2图片预览"/>
              <div v-else class="image-placeholder">暂无图片</div>
              <input type="file" @change="e => handleImageUpload('part1Section2Image', e)" accept="image/*" style="display: none;" :ref="el => (fileInputs['part1Section2Image'] = el)" />
              <button @click.prevent="triggerFileInput('part1Section2Image')" class="upload-button">更换图片</button>
            </div>
          </div>
        </div>

        <!-- 子部分3 -->
        <div class="sub-section">
          <h4>子部分3</h4>
          <div class="form-group">
            <label for="part1Section3Title">标题</label>
            <textarea id="part1Section3Title" rows="2" v-model="backgroundData.part1Section3Title"></textarea>
          </div>
          <div class="form-group">
            <label for="part1Section3Content">内容</label>
            <textarea id="part1Section3Content" rows="5" v-model="backgroundData.part1Section3Content"></textarea>
          </div>
          <div class="form-group">
            <label for="part1Section3Image">图片</label>
            <div class="image-uploader">
              <img :src="backgroundData.part1Section3Image" v-if="backgroundData.part1Section3Image" class="image-preview" alt="子部分3图片预览"/>
              <div v-else class="image-placeholder">暂无图片</div>
              <input type="file" @change="e => handleImageUpload('part1Section3Image', e)" accept="image/*" style="display: none;" :ref="el => (fileInputs['part1Section3Image'] = el)" />
              <button @click.prevent="triggerFileInput('part1Section3Image')" class="upload-button">更换图片</button>
            </div>
          </div>
        </div>
      </div>

      <!-- 第二部分 -->
      <div class="form-section">
        <h3>第二部分</h3>
        <div class="form-group">
          <label for="part2MainTitle">主标题</label>
          <textarea id="part2MainTitle" rows="2" v-model="backgroundData.part2MainTitle"></textarea>
        </div>
        <div class="form-group">
          <label for="part2SubTitle">副标题</label>
          <textarea id="part2SubTitle" rows="2" v-model="backgroundData.part2SubTitle"></textarea>
        </div>
        <div class="form-group">
          <label for="part2Content">内容</label>
          <textarea id="part2Content" rows="8" v-model="backgroundData.part2Content"></textarea>
        </div>
      </div>

      <button type="submit" class="save-button">保存更改</button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue';
import { getExpoBackgroundData, saveExpoBackgroundData, updateExpoBackgroundData, uploadImage } from '@/api';

const backgroundData = ref({
  id: null,
  part1MainTitle: '',
  part1Section1Title: '',
  part1Section1Content: '',
  part1Section1Image: '',
  part1Section2Title: '',
  part1Section2Content: '',
  part1Section2Image: '',
  part1Section3Title: '',
  part1Section3Content: '',
  part1Section3Image: '',
  part2MainTitle: '',
  part2SubTitle: '',
  part2Content: '',
});

const fileInputs = reactive({});

const triggerFileInput = (fieldName) => {
  fileInputs[fieldName].click();
};

const handleImageUpload = async (fieldName, event) => {
  const file = event.target.files[0];
  if (!file) return;

  try {
    // 使用 api 中的 uploadImage 函数
    const res = await uploadImage(file);

    if (res.code === 200 && res.data.fileUrl) {
      backgroundData.value[fieldName] = res.data.fileUrl;
      alert('图片上传成功！');
    } else {
      alert(`图片上传失败: ${res.message || '未知错误'}`);
    }
  } catch (error) {
    console.error('图片上传失败:', error);
    alert('图片上传过程中发生错误！');
  }
};

// 获取数据
const fetchData = async () => {
  try {
    // 使用 api 中的 getExpoBackgroundData 函数
    const res = await getExpoBackgroundData();

    if (res.code === 200 && res.data) {
      backgroundData.value = res.data;
    } else {
      console.log('尚未配置世博会背景信息');
    }
  } catch (error) {
    console.error('获取世博会背景信息失败:', error);
    alert('数据加载失败！');
  }
};

// 在组件挂载时获取数据
onMounted(() => {
  fetchData();
});

const saveChanges = async () => {
  try {
    let res;

    // 根据是否有 ID 来决定是创建还是更新
    if (backgroundData.value.id) {
      // 使用 api 中的 updateExpoBackgroundData 函数
      res = await updateExpoBackgroundData(backgroundData.value);
    } else {
      // 使用 api 中的 saveExpoBackgroundData 函数
      res = await saveExpoBackgroundData(backgroundData.value);
    }

    if (res.code === 200) {
      // 如果是创建操作，更新ID
      if (!backgroundData.value.id && res.data?.id) {
        backgroundData.value.id = res.data.id;
      }
      alert('数据保存成功！');
    } else {
      alert(`保存失败: ${res.message || '未知错误'}`);
    }
  } catch (error) {
    console.error('保存失败:', error);
    alert('保存过程中发生错误！');
  }
};
</script>

<style scoped>
.background-admin {
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

.sub-section {
  background-color: #fff;
  border: 1px solid #eee;
  border-radius: 6px;
  padding: 1rem;
  margin-bottom: 1.5rem;
}

.sub-section h4 {
  margin-top: 0;
  margin-bottom: 1rem;
  color: #666;
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
  line-height: 1.5;
  min-height: 40px;
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
