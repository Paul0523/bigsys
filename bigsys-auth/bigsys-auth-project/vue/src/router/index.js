import Vue from 'vue'
import Router from 'vue-router'
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading

Vue.use(Router)

/* layout */
import Layout from '../views/layout/Layout'
import Login from '../views/Login'
import MyTable from '../views/components/MyTable'
import MyTableWithFilter from '../views/components/MyTableWithFilter'

/**
 * icon : the icon show in the sidebar
 * hidden : if `hidden:true` will not show in the sidebar
 * redirect : if `redirect:noredirect` will no redirct in the levelbar
 * noDropdown : if `noDropdown:true` will has no submenu
 * meta : { role: ['admin'] }  will control the page role
 **/
export const constantRouterMap = [
  {
    path: '/auth',
    component: Layout,
    name: '用户管理',
    children: [
      {path: 'index', component: MyTableWithFilter, name: '主页1'}
    ]
  }, {
    path: '/user',
    component: Layout,
    name: '权限',
    children: [
      {path: 'index', component: MyTable, name: '主页2'}
    ]
  }, {
    path: '/',
    component: Layout,
    name: '主页',
    children: [
      {path: 'index', component: MyTable, name: '主页3'}
    ]
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
