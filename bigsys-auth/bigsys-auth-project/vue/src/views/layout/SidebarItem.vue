<template>
  <div>
    <template v-for="(item, index) in menus">
      <el-submenu v-if="item.hasSub" :index="index.toString()">
        <template slot="title"><i class="el-icon-message"></i>{{item.name}}</template>
        <sidebar-item :menus="item.menus" :parentIndex="parentIndex? parentString + '-' + index.toString() : index.toString()"></sidebar-item>
      </el-submenu>
      <router-link v-if="!item.hasSub" class="pan-btn yellow-btn" :to="item.link"><el-menu-item :index="parentIndex? parentIndex + '-' + index.toString() : index.toString()"><i class="el-icon-menu"></i>{{item.name}}</el-menu-item></router-link>
    </template>
  </div>
</template>

<script>
  export default {
    name: 'SidebarItem',
    props: {
      menus: {
        type: Array
      },
      parentIndex: {
        type: String
      }
    }
  }

</script>
