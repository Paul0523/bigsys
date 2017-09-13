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
      <el-table-column prop="username"
                       label="用户名"
                       :width="100">
      </el-table-column>
      <el-table-column prop="name"
                       label="昵称"
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
          <el-input v-model="formLabelAlign.username"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="formLabelAlign.name"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="formLabelAlign.phone" @keyup.enter.native="saveUser"></el-input>
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="formLabelAlign.roleIds" @visible-change="selectRole" multiple placeholder="请选择">
            <el-option style="display: none"
                       v-for="item in options"
                       :key="item.id"
                       :label="item.name"
                       :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveUser()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="选择角色" :visible.sync="dialogRoleVisible">
      <el-tree
        :data="role"
        :props="roleProps"
        show-checkbox
        :check-strictly="true"
        @check-change="addRole"
        node-key="id"
        ref="role"
        default-expand-all
        :expand-on-click-node="false">
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogRoleVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogRoleVisible = false">确 定</el-button>
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
  import ElButton from '../../../node_modules/element-ui/packages/button/src/button'
  import axios from 'axios'
  import ElFormItem from '../../../node_modules/element-ui/packages/form/src/form-item'
  export default {
    components: {
      ElFormItem,
      ElButton},
    created () {
      this.initData()
    },
    computed: {
      lineRole () {
        return this.formLabelAlign.role
      }
    },
    data () {
      return {
        options: [],
        pageNum: 1,
        pageSize: 10,
        total: 0,
        tdata: [],
        username: '',
        phone: '',
        title: '新增用户',
        dialogFormVisible: false,
        dialogRoleVisible: false,
        labelPosition: 'right',
        roleProps: {
          label: 'name',
          children: 'roles'
        },
        role: [],
        formLabelAlign: {
          id: '',
          username: '',
          name: '',
          phone: '',
          roleIds: []
        }
      }
    },
    methods: {
      checkParentIsCheck (data) {
        var roleMap = {}
        this.options.forEach((option) => {
          roleMap[option.id] = option
        })
        if (this.formLabelAlign.roleIds.indexOf(data.parentId) !== -1) {
          return true
        } else {
          if (data.parentId) {
            return this.checkParentIsCheck(roleMap[data.parentId])
          } else {
            return false
          }
        }
      },
      checkChildrenIsCheck (data) {
        var roleMap = {}
        this.options.forEach((option) => {
          roleMap[option.id] = option
        })
        for (let i = 0; i < data.roles.length; i++) {
          var subRole = data.roles[i]
          if (this.formLabelAlign.roleIds.indexOf(subRole.id) !== -1) {
            return true
          }
          if (subRole.roles.length > 0 && this.checkChildrenIsCheck(subRole)) {
            return true
          }
        }
        return false
      },
      addRole (data, isCheck, subDatas) {
        console.log(subDatas)
        if ((this.checkParentIsCheck(data) || this.checkChildrenIsCheck(data)) && isCheck) {
          alert('有父角色或子角色已被选中，若要添加当前角色请取消其他选中角色')
          setTimeout(() => {
            var keys = this.$refs.role.getCheckedKeys()
            keys.remove(data.id)
            this.$refs.role.setCheckedKeys(keys)
          })
          return
        }
        if (isCheck) {
          this.formLabelAlign.roleIds.push(data.id)
        } else {
          this.formLabelAlign.roleIds.remove(data.id)
        }
      },
      selectRole (isDown) {
        if (isDown) {
          this.dialogRoleVisible = true
          setTimeout(() => {
            this.$refs.role.setCheckedKeys(this.formLabelAlign.roleIds)
          })
        }
      },
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
        this.formLabelAlign.username = ''
        this.formLabelAlign.name = ''
        this.formLabelAlign.phone = ''
        this.formLabelAlign.roleIds = []
        this.dialogFormVisible = true
        axios.get('/api/role/getRole').then((response) => {
          this.role = response.data.data
          this.options = []
          var addOptions = (role) => {
            role.forEach((item) => {
              this.options.push(item)
              if (item.roles.length > 0) {
                addOptions(item.roles)
              }
            })
          }
          addOptions(this.role)
          console.log(this.options)
        })
      },
      modifyUser: function (user) {
        this.title = '修改用户'
        this.formLabelAlign.id = user.id
        this.formLabelAlign.username = user.username
        this.formLabelAlign.name = user.name
        this.formLabelAlign.phone = user.phone
        this.formLabelAlign.roleIds = user.roleIds
        this.dialogFormVisible = true
        axios.get('/api/role/getRole').then((response) => {
          this.role = response.data.data
          this.options = []
          var addOptions = (role) => {
            role.forEach((item) => {
              this.options.push(item)
              if (item.roles.length > 0) {
                addOptions(item.roles)
              }
            })
          }
          addOptions(this.role)
          console.log(this.options)
        })
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
