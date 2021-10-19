import request from '@/utils/request'

export function runCode(data) {
  return request({
    url: '/run',
    method: 'POST',
    data
  })
}

export function getCodeType() {
    return request({
      url: '/codetype',
      method: 'GET',
    })
  }