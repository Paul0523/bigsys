export default [
  {
    name: '测试菜单',
    link: '/test',
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
