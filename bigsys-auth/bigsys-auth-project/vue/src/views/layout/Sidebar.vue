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
  import { mapGetters } from 'vuex'
  export default {
    components: {
      SidebarItem
    },
    created () {
      this.$store.dispatch('GET_MENUS')
    },
    methods: {
      handleOpen (key, keyPath) {
        console.log(key, keyPath)
      },
      handleClose (key, keyPath) {
        console.log(key, keyPath)
      }
    },
    computed: {
      ...mapGetters(['menus'])
    }
  }
</script>
