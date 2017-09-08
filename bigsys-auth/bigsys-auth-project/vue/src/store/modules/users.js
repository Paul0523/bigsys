import {inittdata} from '../../mock/table'

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
        commit('ADD_USER', data)
        resolve()
      })
    }
  }
}

export default sidebar
