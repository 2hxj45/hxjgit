/**
 * 基础路由
 * @type { *[] }
 */
export const constantRouterMap = [

  {
    path: '/',
    name:'/',
    component: () => import('@/views/index/index'),
    meta: {
      title: '新闻首页',
    }
  },
 
  {
    path: '/login',
    name:'/login',
    component: () => import('@/views/login/index'),
    meta: {
      title: '登录',
    }
  },
 
  {
    path: '/register',
    name:'/Register',
    component: () => import('@/views/login/register'),
    meta: {
      title: '注册',
    }
  },
  {
    path: '/personalcenter',
    name:'/PersonalCenter',
    component: () => import('@/views/index/personalcenter'),
    meta: {
      title: '个人中心',
    }
  },
  {
    path: '/mysc',
    name:'/Mysc',
    component: () => import('@/views/index/mysc'),
    meta: {
      title: '我的收藏',
    }
  },
  {
    path: '/myscnewsbyid',
    name:'/myscnewsbyid',
    component: () => import('@/views/index/myscnewsbyid'),
    meta: {
      title: '资讯详情',
    }
  },
  {
    path: '/newsdetails',
    name: 'newsDetails',
    component: () => import('@/views/index/newsdetails'),
    meta: {
      title: '新闻详情',
    }
  },
  {
    path: '/toolindex',
    name: 'ToolIndex',
    component: () => import('@/views/tools/toolindex'),
    meta: {
      title: '木盒工具',
    }
  },
  {
    path: '/searchphone',
    name: 'searchPhone',
    component: () => import('@/views/tools/searchphone'),
    meta: {
      title: '木盒工具',
    }
  },
  {
    path: '/searchip',
    name: 'searchIp',
    component: () => import('@/views/tools/searchip'),
    meta: {
      title: '木盒工具',
    }
  },
  {
    path: '/queryweather',
    name: 'queryWeather',
    component: () => import('@/views/tools/queryWeather'),
    meta: {
      title: '木盒工具',
    }
  },{
    path: '/queryEpidemic',
    name: 'queryEpidemic',
    component: () => import('@/views/tools/queryEpidemic'),
    meta: {
      title: '木盒工具',
    }
  },{
    path: '/querybussubway',
    name: 'querybussubway',
    component: () => import('@/views/tools/querybussubway'),
    meta: {
      title: '木盒工具',
    }
  },
  {
    path:'/login',
    name:'Login',
    component: () => import('@/views/login/index'),
    meta: {
      title: '登录/注册',
    }
  },
  // {
  //   path: '/ticketinfo',
  //   name: 'Ticketinfo',
  //   component: () => import('@/views/ticketinfo/ticketinfo'),
  //   meta: {
  //     title: '抢票设置',
  //   }
  // },
  // {
  //   path: '/trainnumber',
  //   name: 'Trainnumber',
  //   component: () => import('@/views/ticketinfo/trainnumber'),
  //   meta: {
  //     title: '备选出行车次',
  //   }
  // },{
  //   path: '/passenger',
  //   name: 'Passenger',
  //   component: () => import('@/views/ticketinfo/passenger'),
  //   meta: {
  //     title: '选择乘车人',
  //   }
  // }

  // {
  //   path: '/',
  //   component: () => import('@/views/layouts/index'),
  //   redirect: '/home',
  //   meta: {
  //     title: '首页',
  //     keepAlive: false
  //   },
  //   children: [
  //     {
  //       path: '/home',
  //       name: 'Home',
  //       component: () => import('@/views/home/index'),
  //       meta: { title: '首页', keepAlive: false }
  //     },
  //     {
  //       path: '/about',
  //       name: 'About',
  //       component: () => import('@/views/home/about'),
  //       meta: { title: '关于我', keepAlive: false }
  //     }
  //   ]
  // }
]
