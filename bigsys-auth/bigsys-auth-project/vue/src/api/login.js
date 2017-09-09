import axios from 'axios'
import querystring from 'querystring'

export default {
  login (data) {
    return axios.post('/api/login.html', querystring.stringify(data))
  }
}
