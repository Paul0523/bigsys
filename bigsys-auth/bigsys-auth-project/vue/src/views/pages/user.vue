<template>
  <div>
    <div>
      <div class="tb-filter">
        <el-input placeholder="用户名" class="my-input" @blur="initData()" v-model="username">
          <template slot="prepend">用户名:</template>
        </el-input>
        <el-input placeholder="电话" class="my-input" @blur="initData()" v-model="phone">
          <template slot="prepend">电话:</template>
        </el-input>
        <el-button type="primary" @click="initData()">查询</el-button>
        <el-button type="primary" @click="addUser()">新增</el-button>
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
                       label="电话"
                       :width="180">
      </el-table-column>
      <el-table-column prop="createTime"
                       label="创建时间"
                       :width="180">
      </el-table-column>
      <el-table-column prop="updateTime"
                       label="更新时间"
                       :width="180">
      </el-table-column>
      <el-table-column prop="operate"
                       label="操作">
        <template scope="scope">
          <el-button type="info" size="small" @click="modifyUser(scope.row)">编辑</el-button>
          <el-button @click="deleteUser(scope.row.id)" type="danger" size="small">删除</el-button>
        </template>
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
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
        <el-input v-model="formLabelAlign.id" type="hidden"></el-input>
        <el-form-item label="用户名">
          <el-input v-model="formLabelAlign.name"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="formLabelAlign.phone" @keyup.enter.native="saveUser"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveUser()">确 定</el-button>
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
        username: '',
        phone: '',
        title: '新增用户',
        dialogFormVisible: false,
        labelPosition: 'right',
        formLabelAlign: {
          id: '',
          name: '',
          phone: ''
        }
      }
    },
    methods: {
      initData () {
        console.log('初始化数据中。。。')
        this.pageNum = 1
        this.pageSize = 10
        this.$store.dispatch('GET_USER_PAGE', {name: this.username, phone: this.phone, pageNum: this.pageNum, pageSize: this.pageSize}).then((data) => {
          this.tdata = data.list
          this.total = data.total
        })
      },
      change () {
        this.$store.dispatch('GET_USER_PAGE', {name: this.username, phone: this.phone, pageNum: this.pageNum, pageSize: this.pageSize}).then((data) => {
          this.tdata = data.list
          this.total = data.total
        })
      },
      saveUser: function () {
        this.dialogFormVisible = false
        this.$store.dispatch('ADD_USER', this.formLabelAlign).then(() => {
          this.initData()
          this.formLabelAlign.id = ''
          this.formLabelAlign.name = ''
          this.formLabelAlign.phone = ''
        }).catch(() => {
        })
      },
      addUser () {
        this.title = '新增用户'
        this.formLabelAlign.id = ''
        this.formLabelAlign.name = ''
        this.formLabelAlign.phone = ''
        this.dialogFormVisible = true
      },
      modifyUser: function (user) {
        this.title = '修改用户'
        this.formLabelAlign.id = user.id
        this.formLabelAlign.name = user.name
        this.formLabelAlign.phone = user.phone
        this.dialogFormVisible = true
      },
      deleteUser (id) {
        this.$store.dispatch('DELETE_USER', id).then((response) => {
          console.log('删除成功！')
          this.initData()
        })
      },
      handleClick (scope, id) {
        console.log(scope)
        console.log(id)
      }
    }
  }
</script>
