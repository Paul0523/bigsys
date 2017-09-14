<template>
  <el-menu default-active="2" style="" class="el-menu-vertical-demo nav-wrapper" @open="handleOpen" @close="handleClose" theme="dark">
    <sidebar-item :menus="menus" :parentIndex="''"></sidebar-item>
  </el-menu>
</template>

<style>
  .nav-wrapper {
    transition: width 0.28s ease-out;
    width: 180px;height: 100%;
    position: fixed;top: 0;
    bottom: 0;
    left: 0;
    z-index: 1001;
    overflow-y: auto;
  }
  .nav-wrapper::-webkit-scrollbar{
    display: none;
  }
</style>

<script>
  import SidebarItem from './SidebarItem'
  import axios from 'axios'
  import menus from '../../mock/menus'
  var id = 10000
  export default {
    components: {
      SidebarItem
    },
    created () {
      this.$store.dispatch('GET_MENUS')
      axios.get('/api/menu/getMenus').then((response) => {
        if (response.data.data === 'all') {
          this.menus = menus
        } else {
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
          var copymenu = []
          solveMenu(copymenu, menus, response.data.data)
          this.menus = copymenu
        }
      })
    },
    methods: {
      handleOpen (key, keyPath) {
        console.log(key, keyPath)
      },
      handleClose (key, keyPath) {
        console.log(key, keyPath)
      },
      filter (resMenus, menus) {

      }
    },
    data () {
      return {
        menus: menus
      }
    }
  }
</script>
