<template>
  <div class="app-wrapper">
    <sidebar class="sidebar-container"></sidebar>
    <div class="main-container" style="background: deepskyblue; width: 100%; height: 1000px;">
      <navbar></navbar>
      <app-main></app-main>
    </div>
  </div>
</template>

<style>



</style>

<script>
  import Sidebar from './Sidebar'
  import Navbar from './Navbar'
  import AppMain from './AppMain'

  export default {
    name: 'layout',
    components: {
      Navbar,
      Sidebar,
      AppMain
    },
    computed: {
      sidebar () {
        return this.$store.state.app.sidebar
      }
    }
  }
</script>

<style>
  .main-container {
    padding-left: 180px;
    box-sizing: border-box;
  }
</style>
