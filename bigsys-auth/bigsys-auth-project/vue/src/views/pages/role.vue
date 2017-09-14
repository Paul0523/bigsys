<template>
  <div>
    <el-tree
      :data="data2"
      :props="defaultProps"
      show-checkbox
      node-key="id"
      ref="role"
      default-expand-all
      :expand-on-click-node="false"
      :render-content="renderContent">
    </el-tree>
    <el-dialog
      title="编辑角色访问权限"
      :visible.sync="dialogVisible"
      size="tiny"
      :before-close="handleClose">
      <el-tree
        :data="data3"
        :props="menuProps"
        show-checkbox
        node-key="id"
        ref="menu"
        default-expand-all
        :expand-on-click-node="false">
      </el-tree>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addMenus()">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="新增角色"
      :visible.sync="newChildDialog"
      size="tiny"
      :before-close="handleClose">
      <el-input placeholder="请输入Id" v-model="newChild.id">
        <template slot="prepend">Id</template>
      </el-input>
      <el-input placeholder="请输入名称" @keyup.enter.native="addNewChild()" v-model="newChild.name">
        <template slot="prepend">名称</template>
      </el-input>

      <span slot="footer" class="dialog-footer">
        <el-button @click="newChildDialog = false">取 消</el-button>
        <el-button type="primary" @click="addNewChild()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import ElButton from 'element-ui/packages/button/index'
  import ElInput from '../../../node_modules/element-ui/packages/input/src/input'
  import menus from '../../mock/menus'
  import axios from 'axios'
  let id = 1000

  export default {
    components: {
      ElButton,
      ElInput},
    data () {
      return {
        data2: [],
        data3: menus,
        defaultProps: {
          children: 'roles',
          label: 'name'
        },
        menuProps: {
          children: 'menus',
          label: 'name'
        },
        dialogVisible: false,
        newChildDialog: false,
        curStore: {},
        curData: {},
        newChild: {
          name: ''
        },
        checked: false
      }
    },
    created () {
      axios.get('/api/role/getRole').then((response) => {
        this.data2 = response.data.data
      })
      var addId = (menus) => {
        menus.forEach((menu) => {
          menu.id = id++
          if (menu.hasSub) {
            addId(menu.menus)
          }
        })
      }
      addId(menus)
    },
    methods: {
      getCheckedNodes () {
        console.log(this.$refs.role.getCheckedNodes())
      },
      getCheckedKeys () {
        console.log(this.$refs.role.getCheckedKeys())
      },
      append (store, data) {
        store.append({id: id++, label: 'testtest', children: []}, data)
      },
      addMenus () {
        console.log(this.$refs.menu.getCheckedNodes())
        var data = this.$refs.menu.getCheckedNodes()
        var params = {}
        params.links = []
        params.id = this.curData.id
        for (let i = 0; i < data.length; i++) {
          if (!data[i].hasSub) {
            params.links.push(data[i].link)
          }
        }
        axios.post('/api/role/addRoleMenus', params).then((response) => {
          this.dialogVisible = false
        })
      },
      remove (store, data) {
        if (data.label === 'admin') {
          alert('根管理员禁止删除！')
          return
        }
        axios.get('/api/role/deleteRole?id=' + data.id).then((response) => {
          store.remove(data)
        })
      },
      handleClose () {
        console.log('窗口关闭了')
        this.dialogVisible = false
      },
      addNewChild () {
        var data = {}
        data.name = this.newChild.name
        data.roles = []
        data.id = this.newChild.id
        data.parentId = this.curData.id
        axios.post('/api/role/addRole', data).then((response) => {
          this.curStore.append(data, this.curData)
          this.newChildDialog = false
        })
      },
      renderContent (createElement, {node, data, store}) {
        var that = this
        return createElement('span', [
          createElement('span', {domProps: {innerHTML: node.label + '&nbsp;&nbsp;&nbsp;&nbsp;'}}),
          createElement(ElButton, {
            props: {size: 'mini', type: 'success'},
            on: {
              click () {
                if (data.id === 'admin') {
                  alert('不能编辑根管理员角色！')
                  return
                }
                that.curData = data
                var roleMenus = axios.get('/api/role/getRoleMenus?role=' + data.parentId)
                var authedMenus = axios.get('/api/role/getRoleMenus?role=' + data.id)
                axios.all([roleMenus, authedMenus]).then((response) => {
                  var hit = (resmenu, link) => {
                    for (let i = 0; i < resmenu.length; i++) {
                      if (link === resmenu[i]) {
                        return true
                      }
                    }
                    return false
                  }
                  var solveMenu = (copymenu, menus, resmenu) => {
                    menus.forEach((menu) => {
                      var newmenu = {}
                      newmenu.id = id++
                      newmenu.link = menu.link
                      newmenu.name = menu.name
                      newmenu.hasSub = menu.hasSub
                      if (!menu.hasSub && hit(resmenu, menu.link)) {
                        copymenu.push(newmenu)
                      } else if (menu.hasSub) {
                        newmenu.menus = []
                        solveMenu(newmenu.menus, menu.menus, resmenu)
                        if (newmenu.menus.length > 0) {
                          copymenu.push(newmenu)
                        }
                      }
                    })
                  }

                  var solveCheckedMenu = (copymenu, ids, checkedMenu) => {
                    copymenu.forEach((menu) => {
                      if (!menu.hasSub && hit(checkedMenu, menu.link)) {
                        ids.push(menu.id)
                      } else if (menu.hasSub) {
                        solveCheckedMenu(menu.menus, ids, checkedMenu)
                      }
                    })
                  }
                  var resmenu = response[0].data.data
                  var copymenu = []
                  solveMenu(copymenu, menus, resmenu)
                  that.data3 = copymenu
                  if (data.parentId === 'admin') {
                    that.data3 = menus
                    copymenu = menus
                  }
                  var ids = []
                  var checkedMenu = response[1].data.data
                  solveCheckedMenu(copymenu, ids, checkedMenu)
                  that.dialogVisible = true
                  setTimeout(() => {
                    that.$refs.menu.setCheckedKeys(ids)
                  })
                })
              }
            }
          },
          ['编辑角色']
          ),
          createElement('span', {domProps: {innerHTML: '&nbsp;&nbsp;&nbsp;'}}),
          createElement(ElButton, {
            props: {size: 'mini', type: 'info'},
            on: {
              click () {
                that.curStore = store
                that.curData = data
                that.newChildDialog = true
              }
            }
          }, ['添加角色']),
          createElement('span', {domProps: {innerHTML: '&nbsp;&nbsp;&nbsp;'}}),
          createElement(ElButton, {
            props: {size: 'mini', type: 'danger'},
            on: {
              click () {
                if (data.id === 'admin') {
                  alert('不能删除根管理员！')
                  return
                }
                that.remove(store, data)
              }
            }
          }, ['删除角色'])
        ])
      }
    }
  }
</script>
