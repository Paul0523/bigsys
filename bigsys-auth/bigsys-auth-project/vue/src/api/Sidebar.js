import menus from '../mock/menus'

export function getMenus () {
  return new Promise(function (resolve, reject) {
    var data = menus
    resolve(data)
  })
}
