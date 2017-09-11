<template>
  <el-menu :default-active="activeIndex2" class="el-menu-demo navbar" style="position: fixed; left: 180px; right: 0px; z-index: 10000;" mode="horizontal" @select="handleSelect">
    <el-submenu index="1" style="float: right;">
      <template slot="title">{{username}}</template>
      <router-link to="/login"><el-menu-item index="2-1">退出</el-menu-item></router-link>
      <el-menu-item index="2-2" @click="editPwd">修改密码</el-menu-item>
    </el-submenu>
  </el-menu>
</template>


<script>
  export default {
    data () {
      return {
        activeIndex2: '1'
      }
    },
    computed: {
      username () {
        return window.sessionStorage.getItem('username')
      }
    },
    methods: {
      handleSelect (key, keyPath) {
        console.log(key, keyPath)
      },
      editPwd () {
//        this.$store.dispatch('GET_MENUS')
      }
    }
  }
</script>
