<template>
  <el-table
    :data="tdata"
    stripe
    style="width: 100%">
    <el-table-column v-for="(item, index) in thead"
      :prop="item.prop"
      :label="item.label"
      :width="item.width"
      :key="index">
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
    props: [
      'thead',
      'tdata'
    ]
  }
</script>
