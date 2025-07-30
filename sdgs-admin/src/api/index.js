import request from '@/utils/request'

export function get(url, params) {
  return request({
    url,
    method: 'get',
    params,
  })
}

export function post(url, data) {
  return request({
    url,
    method: 'post',
    data,
  })
}

export function put(url, data) {
  return request({
    url,
    method: 'put',
    data,
  })
}

export function del(url) {
  return request({
    url,
    method: 'delete',
  })
}

// 获取最新的世博会页面信息
export function getExpoInfo() {
  return get('/api/expo/info')
}

// 更新世博会信息
export function updateExpoInfo(id, data) {
  return put(`/api/expo/manage/${id}`, data)
}

// 获取最新的首页信息
export function getHomeInfo() {
  return get('/api/home/info')
}

// 更新首页信息
export function updateHomeInfo(id, data) {
  return put(`/api/home/manage/${id}`, data)
}

// 上传图片
export function uploadImage(file) {
  const formData = new FormData()
  formData.append('file', file)
  return post('/api/file/upload/image', formData)
}

// 获取图片列表
export function listImages(params) {
  return get('/api/file/list', params)
}

// 删除图片
export function deleteImage(fileUrl) {
  return del(`/api/file/delete?fileUrl=${encodeURIComponent(fileUrl)}`)
}

//********************************************************************************
//下面代码由Drame完成注意区分！！！
//********************************************************************************

// 获取世博会背景页面数据
export function getExpoBackgroundData() {
  return get('/api/expo/background/data')
}

// 保存世博会背景页面数据
export function saveExpoBackgroundData(data) {
  return post('/api/expo/background/data', data)
}

// 更新世博会背景页面数据
export function updateExpoBackgroundData(data) {
  return put('/api/expo/background/data', data)
}

// 获取峰会完整数据
export function getSummitCompleteData() {
  return get('/api/summit/complete')
}

// 获取峰会议程数据
export function getSummitScheduleData() {
  return get('/api/summit/schedule')
}

// 获取峰会嘉宾数据
export function getSummitGuestData() {
  return get('/api/summit/guests')
}

// 添加峰会数据项
export function addSummitData(data) {
  return post('/api/summit/add', data)
}

// 更新峰会数据项
export function updateSummitData(id, data) {
  return put(`/api/summit/update/${id}`, data)
}

// 删除峰会数据项
export function deleteSummitData(id) {
  return del(`/api/summit/delete/${id}`)
}

//********************************************************************************
