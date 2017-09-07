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
        <el-button type="primary" @click="dialogFormVisible = true">新增</el-button>
      </div>
    </div>
    <my-table :thead="thead" :tdata="tdata"></my-table>
    <el-dialog title="新增用户" :visible.sync="dialogFormVisible">
      <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
        <el-form-item label="用户名">
          <el-input v-model="formLabelAlign.name"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="formLabelAlign.phone"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="formLabelAlign.password" type="password"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser()">确 定</el-button>
      </div>
    </el-dialog>
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
  import {thead, inittdata} from '../../mock/table'
  export default {
    components: {
      MyTable
    },
    data () {
      return {
        username: 'fds',
        tdata: inittdata,
        dialogFormVisible: false,
        labelPosition: 'right',
        formLabelAlign: {
          name: '',
          region: '',
          type: ''
        }
      }
    },
    computed: {
      thead () {
        return thead
      }
    },
    watch: {
      username () {
        var filterdata = []
        if (!this.username) {
          this.tdata = inittdata
          return
        }
        inittdata.forEach((data, index) => {
          if (data.name.indexOf(this.username) !== -1) {
            filterdata.push(data)
          }
        })
        this.tdata = filterdata
      }
    },
    methods: {
      addUser: function () {
        this.dialogFormVisible = false
        this.$store.dispatch('TEST', this.formLabelAlign).then(() => {
          alert('新增成功')
        }).catch(() => {
          alert('新增失败')
        })
      }
    }
  }

</script>
