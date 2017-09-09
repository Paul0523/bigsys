import Vue from 'vue'
import Vuex from 'vuex'
import sidebar from './modules/sidebar'
import users from './modules/users'
import getters from './getter'
import loginStore from './modules/login'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    sidebar,
    users,
    loginStore
  },
  getters
})

export default store
