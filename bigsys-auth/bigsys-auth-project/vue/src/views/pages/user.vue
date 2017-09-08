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
        <el-button type="primary" @click="search()">查询</el-button>
        <el-button type="primary" @click="dialogFormVisible = true">新增</el-button>
      </div>
    </div>
    <my-table :thead="thead" :tdata="users"></my-table>
    <el-dialog title="新增用户" :visible.sync="dialogFormVisible">
      <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
        <el-form-item label="日期">
          <el-input v-model="formLabelAlign.date"></el-input>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="formLabelAlign.name"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="formLabelAlign.address" @key.enter="addUser()"></el-input>
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
  import MyTable from '../components/MyTable'
  import {thead, inittdata} from '../../mock/table'
  import { mapGetters } from 'vuex'
  export default {
    components: {
      MyTable
    },
    data () {
      return {
        username: 'fds',
        dialogFormVisible: false,
        labelPosition: 'right',
        formLabelAlign: {
          date: '',
          name: '',
          address: ''
        }
      }
    },
    computed: {
      ...mapGetters(['users']),
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
        this.$store.dispatch('ADD_USER', this.formLabelAlign).then(() => {
        }).catch(() => {
        })
      },
      search () {
        alert('查询成功！')
      }
    }
  }

</script>
