<template>
  <div>
    <div>
      <div class="tb-filter">
        <el-input placeholder="用户名" class="my-input" v-model="username">
          <template slot="prepend">用户名:</template>
        </el-input>
        <el-input placeholder="电话" class="my-input" v-model="username">
          <template slot="prepend">电话:</template>
        </el-input>
        <el-button type="primary" @click="addUser()">新增</el-button>
      </div>
    </div>
    <my-table :thead="thead" :tdata="tdata"></my-table>
  </div>
</template>

<style>
  .tb-filter {
    margin-bottom: 20px;
  }
  .my-input {
    width: 200px;
  }
</style>

<script>
  import MyTable from './MyTable'
  export default {
    components: {
      MyTable
    },
    data () {
      return {
        username: '测试',
        thead: [
          {
            prop: 'date',
            label: '日期',
            width: '180'
          }, {
            prop: 'name',
            label: '名称',
            width: '180'
          }, {
            prop: 'address',
            label: '地址'
          }
        ],
        tdata: [{
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }]
      }
    },
    methods: {
      addUser: function () {
        alert('添加一个用户')
      }
    }
  }

</script>
