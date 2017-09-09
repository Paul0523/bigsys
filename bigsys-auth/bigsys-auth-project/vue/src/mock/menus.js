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
        name: '角色管理',
        link: '/sys/role',
        hasSub: false
      },
      {
        name: '权限管理',
        link: '/sys/auth',
        hasSub: false
      },
      {
        name: '用户管理',
        link: '/sys/user',
        hasSub: false
      }
    ]
  }
]
