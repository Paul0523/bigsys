export default [
  {
    name: 'aa',
    hasSub: true,
    menus: [
      {
        name: 'bb',
        link: '/index',
        hasSub: false,
        menus: []
      }
    ]
  },
  {
    name: 'aa',
    link: '/',
    hasSub: false,
    menus: []
  },
  {
    name: 'aa',
    link: '/',
    hasSub: false,
    menus: []
  },
  {
    name: '系统管理',
    link: '/',
    hasSub: true,
    menus: [
      {
        name: '角色管理',
        link: '/sys/role',
        hasSub: false,
        menus: []
      },
      {
        name: '用户管理',
        link: '/sys/user',
        hasSub: false,
        menus: []
      }
    ]
  }
]
