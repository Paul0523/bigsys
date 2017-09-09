import {inittdata} from '../../mock/table'
import userapi from '../../api/user'

const sidebar = {
  state: {
    users: inittdata
  },
  mutations: {
    ADD_USER (state, data) {
      state.users.push(data)
    }
  },
  actions: {
    ADD_USER ({ commit }, data) {
      return new Promise((resolve, reject) => {
        userapi.addUser(data).then(function (response) {
          resolve(response)
        }).catch(function (error) {
          reject(error)
        })
      })
    },
    GET_USER_PAGE ({ commit }, data) {
      return new Promise((resolve, reject) => {
        userapi.page(data).then(function (response) {
          resolve(response.data)
        }).catch(function (error) {
          reject(error)
        })
      })
    },
    DELETE_USER ({ commit }, id) {
      return new Promise((resolve, reject) => {
        userapi.delete(id).then((response) => {
          resolve(response.data)
        }).catch((error) => {
          reject(error)
        })
      })
    }
  }
}

export default sidebar
