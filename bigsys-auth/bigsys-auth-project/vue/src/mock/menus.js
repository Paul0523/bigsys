export default [
  {
    name: 'aa',
    hasSub: true,
    menus: [
      {
        name: 'bb',
        link: '/index',
        hasSub: false
      }
    ]
  },
  {
    name: 'aa',
    link: '/',
    hasSub: false
  },
  {
    name: 'aa',
    link: '/',
    hasSub: false
  },
  {
    name: '系统管理',
    link: '/',
    hasSub: true,
    menus: [
      {
        name: '权限管理',
        link: '/auth/index',
        hasSub: false
      },
      {
        name: '用户管理',
        link: '/user/index',
        hasSub: false
      }
    ]
  }
]
