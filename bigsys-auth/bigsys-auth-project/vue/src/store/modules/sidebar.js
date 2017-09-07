import {getMenus} from '../../api/Sidebar'

const sidebar = {
  state: {
    menus: []
  },
  mutations: {
    SET_MENUS (state, data) {
      state.menus = data
    }
  },
  actions: {
    GET_MENUS ({ commit }) {
      getMenus().then((res) => {
        commit('SET_MENUS', res)
      })
    },
    TEST ({ commit }, data) {
      debugger
      console.log(data)
      return new Promise(function (resolve, reject) {
        // resolve()
        reject()
      })
    }
  }
}

export default sidebar
