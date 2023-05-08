import request from '@/utils/request'

// 查询指标列表
export function listCsurpIndex(query) {
  return request({
    url: '/csrup/csurpIndex/list',
    method: 'get',
    params: query
  })
}

// 查询指标详细
export function getCsurpIndex(id) {
  return request({
    url: '/csrup/csurpIndex/' + id,
    method: 'get'
  })
}

// 新增指标
export function addCsurpIndex(data) {
  return request({
    url: '/csrup/csurpIndex',
    method: 'post',
    data: data
  })
}

// 修改指标
export function updateCsurpIndex(data) {
  return request({
    url: '/csrup/csurpIndex',
    method: 'put',
    data: data
  })
}

// 删除指标
export function delCsurpIndex(id) {
  return request({
    url: '/csrup/csurpIndex/' + id,
    method: 'delete'
  })
}
