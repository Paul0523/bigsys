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
    <el-table
      :data="tdata"
      stripe
      style="width: 100%">
      <el-table-column prop="name"
                       label="用户名"
                       :width="180">
      </el-table-column>
      <el-table-column prop="phone"
                       label="电话">
      </el-table-column>
    </el-table>
    <div class="block" style="margin-top: 20px; text-align: center;">
      <el-pagination
        @size-change="change()"
        @current-change="change()"
        layout="prev, pager, next, total"
        :page-size="pageSize"
        :current-page.sync="pageNum"
        :total="total">
      </el-pagination>
    </div>
    <el-dialog title="新增用户" :visible.sync="dialogFormVisible">
      <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
        <el-form-item label="用户名">
          <el-input v-model="formLabelAlign.name"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="formLabelAlign.phone"></el-input>
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
  export default {
    created () {
      this.initData()
    },
    data () {
      return {
        pageNum: 1,
        pageSize: 10,
        total: 0,
        tdata: [],
        username: 'fds',
        dialogFormVisible: false,
        labelPosition: 'right',
        formLabelAlign: {
          name: '',
          phone: ''
        }
      }
    },
    methods: {
      initData () {
        this.pageNum = 1
        this.pageSize = 10
        this.$store.dispatch('GET_USER_PAGE', {pageNum: this.pageNum, pageSize: this.pageSize}).then((data) => {
          this.tdata = data.list
          this.total = data.total
        })
      },
      change () {
        this.$store.dispatch('GET_USER_PAGE', {pageNum: this.pageNum, pageSize: this.pageSize}).then((data) => {
          this.tdata = data.list
          this.total = data.total
        })
      },
      addUser: function () {
        this.dialogFormVisible = false
        this.$store.dispatch('ADD_USER', this.formLabelAlign).then(() => {
          this.initData()
        }).catch(() => {
        })
      }
    }
  }
</script>
