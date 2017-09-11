import Vue from 'vue'
import Router from 'vue-router'
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading

Vue.use(Router)

/* layout */
import Layout from '../views/layout/Layout'
import Login from '../views/Login'
import MyTable from '../views/components/MyTable'
import user from '../views/pages/user'
import role from '../views/pages/role'
import error404 from '../views/pages/404'

/**
 * icon : the icon show in the sidebar
 * hidden : if `hidden:true` will not show in the sidebar
 * redirect : if `redirect:noredirect` will no redirct in the levelbar
 * noDropdown : if `noDropdown:true` will has no submenu
 * meta : { role: ['admin'] }  will control the page role
 **/
export const constantRouterMap = [
  {
    path: '/sys',
    component: Layout,
    redirect: '/404',
    name: '用户管理',
    children: [
      {path: 'user', component: user, name: '主页2'}
    ]
  }, {
    path: '/',
    component: Layout,
    redirect: '/404',
    name: '主页',
    children: [
      {path: 'index', component: MyTable, name: '主页3'}
    ]
  }, {
    path: '/sys',
    component: Layout,
    redirect: '/404',
    name: '角色',
    children: [
      {path: 'role', component: role, name: '主页3'}
    ]
  }, {
    path: '/404',
    component: error404,
    name: '404页面'
  }, {
    path: '/login',
    component: Login,
    name: '登录'
  }
]

export default new Router({
  // mode: 'history', //后端支持可开
  routes: constantRouterMap
})
