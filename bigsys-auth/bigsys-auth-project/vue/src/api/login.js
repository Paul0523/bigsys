import axios from 'axios'
import querystring from 'querystring'
import router from '../router'

axios.interceptors.response.use(
  response => {
    console.log('打印响应。。')
    if (response.data.status === 'NEEDLOGIN') {
      alert(response.data.msg)
      router.push({ path: '/login' })
    }
    return response
  },
  error => {
    if (error.response) {
    }
    return Promise.reject(error.response.data)   // 返回接口返回的错误信息
  })

export default {
  login (data) {
    return axios.post('/api/login.html', querystring.stringify(data))
  }
}
