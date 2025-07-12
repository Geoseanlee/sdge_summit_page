import request from '@/utils/request'

export function get(url, params) {
  return request({
    url,
    method: 'get',
    params
  })
}

export function post(url, data) {
  return request({
    url,
    method: 'post',
    data
  })
}

export function put(url, data) {
  return request({
    url,
    method: 'put',
    data
  })
}

export function del(url) {
  return request({
    url,
    method: 'delete'
  })
} 