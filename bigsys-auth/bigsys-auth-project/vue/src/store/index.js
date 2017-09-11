import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getter'
import loginapi from '../api/login'
import fullmenus from '../mock/menus'
import {inittdata} from '../mock/table'
import userapi from '../api/user'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    isLogin: window.sessionStorage.getItem('isLogin'),
    menus: [],
    users: inittdata,
    username: '',
    role: '',
    token: ''
  },
  mutations: {
    LOGIN (state, data) {
      this.isLogin = true
      state.username = data.data.name
      window.sessionStorage.setItem('username', data.data.name)
    },
    SET_MENUS (state, data) {
      state.menus = data
    },
    ADD_USER (state, data) {
      state.users.push(data)
    }
  },
  actions: {
    GET_MENUS ({ commit }) {
      return new Promise((resolve, reject) => {
        commit('SET_MENUS', fullmenus)
        resolve()
      })
    },
    LOGIN ({ commit }, data) {
      return new Promise((resolve, reject) => {
        loginapi.login(data).then((response) => {
          commit('LOGIN', response.data)
          resolve()
        }).catch((error) => {
          reject(error)
        })
      })
    },
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
  },
  getters
})

export default store
