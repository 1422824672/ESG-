import request from '@/utils/request'

// 查询公司信息列表
export function listCsurpCompany(query) {
  return request({
    url: '/csrup/csurpCompany/list',
    method: 'get',
    params: query
  })
}

// 查询公司信息详细
export function getCsurpCompany(id) {
  return request({
    url: '/csrup/csurpCompany/' + id,
    method: 'get'
  })
}

// 新增公司信息
export function addCsurpCompany(data) {
  return request({
    url: '/csrup/csurpCompany',
    method: 'post',
    data: data
  })
}

// 修改公司信息
export function updateCsurpCompany(data) {
  return request({
    url: '/csrup/csurpCompany',
    method: 'put',
    data: data
  })
}

// 删除公司信息
export function delCsurpCompany(id) {
  return request({
    url: '/csrup/csurpCompany/' + id,
    method: 'delete'
  })
}

// 查询公司下拉列表
export function selectList() {
  return request({
    url: '/csrup/csurpCompany/selectList',
    method: 'get'
  })
}
