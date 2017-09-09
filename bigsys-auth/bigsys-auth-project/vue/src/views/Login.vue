<template>
  <div class="login-container">
    <el-form class="card-box login-form" autoComplete="on" :model="loginForm" :rules="loginRules" ref="loginForm" label-position="left">
      <h3 class="title">系统登录</h3>

      <el-form-item prop="username">
        <el-input name="username" type="text" v-model="loginForm.username" autoComplete="on" placeholder="邮箱" />
      </el-form-item>

      <el-form-item prop="password">
        <el-input name="password" :type="pwdType" @keyup.enter.native="handleLogin" v-model="loginForm.password" autoComplete="on"
                  placeholder="密码" />
      </el-form-item>

      <el-button type="primary" style="width:100%;margin-bottom:30px;" :loading="loading" @click.native.prevent="handleLogin">登录</el-button>

      <div class='tips'>账号:admin 密码随便填</div>
      <div class='tips'>账号:editor  密码随便填</div>

      <el-button class='thirdparty-button' type="primary" @click='showDialog=true'>打开第三方登录</el-button>
    </el-form>

    <el-dialog title="第三方验证" :visible.sync="showDialog">
      本地不能模拟，请结合自己业务进行模拟！！！<br/><br/><br/>
      邮箱登录成功,请选择第三方验证<br/>
    </el-dialog>

  </div>
</template>

<script>
  export default {
    components: {},
    name: 'login',
    data () {
      return {
        loginForm: {
          username: 'admin',
          password: '1111111'
        },
        loginRules: {
        },
        pwdType: 'password',
        loading: false,
        showDialog: false
      }
    },
    methods: {
      showPwd () {
        if (this.pwdType === 'password') {
          this.pwdType = ''
        } else {
          this.pwdType = 'password'
        }
      },
      handleLogin () {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true
            this.$store.dispatch('LOGIN', this.loginForm).then(() => {
              this.loading = false
              this.$router.push({ path: '/sys/user' })
              // this.showDialog = true
            }).catch(() => {
              this.loading = false
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      }
    }
  }
</script>

<style>
  .login-container {
    background: #2d3a4b;
    height: 100vh;
  }
  input {
    background: transparent !important;
    border: 0px;
    -webkit-appearance: none;
    border-radius: 0px;
    padding: 12px 5px 12px 15px;
    color: #eee !important;
    height: 47px !important;
  }
  input:-webkit-autofill {
    -webkit-box-shadow: 0 0 0px 1000px #293444 inset !important;
    -webkit-text-fill-color: #fff !important;
  }
  .el-input {
    display: inline-block;
    height: 47px;
    width: 100%;
  }
  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;
  }
  .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 400px;
    padding: 35px 35px 15px 35px;
    margin: 120px auto;
    box-sizing: border-box;
  }
  .title {
    font-size: 26px;
    font-weight: 400;
    color: #eee;
    margin: 0px auto 40px auto;
    text-align: center;
    font-weight: bold;
  }
  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: #889aa4;
    cursor: pointer;
  }
  .thirdparty-button{
    position: absolute;
    right: 35px;
    bottom: 28px;
  }
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: #889aa4;
    vertical-align: middle;
    width: 30px;
    display: inline-block;

  }
</style>
