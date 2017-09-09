import loginapi from '../../api/login'

const loginStore = {
  state: {
    isLogin: false
  },
  mutations: {
    LOGIN (state, data) {
      this.isLogin = true
    }
  },
  actions: {
    LOGIN ({ commit }, data) {
      return new Promise((resolve, reject) => {
        loginapi.login(data).then((response) => {
          commit('LOGIN', response.data)
          resolve()
        }).catch((error) => {
          reject(error)
        })
      })
    }
  }
}

export default loginStore
