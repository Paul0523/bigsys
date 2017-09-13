<template>
  <el-menu :default-active='activeIndex2' class='el-menu-demo navbar'
           style='position: fixed; left: 180px; right: 0px; z-index: 10000;' mode='horizontal'>
    <el-submenu index='1' style='float: right;'>
      <template slot='title'>{{username}}</template>
      <el-menu-item index='2-1' @click='exit()'>退出</el-menu-item>
      <el-menu-item index='2-2' @click='editPwd'>修改密码</el-menu-item>
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
      exit () {
        this.set('MySessionId', '1111', -1)
        this.$router.push({ path: '/login' })
      },
      editPwd () {
//        this.$store.dispatch('GET_MENUS')
      },
      set: function (name, value, days) {
        var d = new Date()
        d.setTime(d.getTime() + 24 * 60 * 60 * 1000 * days)
        window.document.cookie = name + '=' + value + ';path=/;expires=' + d.toGMTString()
      },
      get: function (name) {
        var v = window.document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)')
        return v ? v[2] : null
      },
      delete: function (name) {
        this.set(name, '', -1)
      }
    }
  }
</script>
