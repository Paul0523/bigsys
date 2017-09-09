import axios from 'axios'
import querystring from 'querystring'
import {USER_ADDORUPDATE, USER_PAGE, USER_DELETE} from './api'

export default {
  addUser (data) {
    return axios.post(USER_ADDORUPDATE, data)
  },
  page (filter) {
    return axios.get(USER_PAGE + '?' + querystring.stringify(filter))
  },
  delete (id) {
    return axios.get(USER_DELETE + '?id=' + id)
  }
}
