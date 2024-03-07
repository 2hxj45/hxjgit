const api = {
  // Login: '/user/login',
  // UserInfo: '/user/userinfo',
  // UserName: '/user/name'  

  // Login:'/',

  Login:'backgroundapi/login',
  Register:'backgroundapi/common/verifyRegister',
  Logout:'backgroundapi/logout',//退出登录
  getUserInfo: 'backgroundapi/common/getUserInfo',//查出个人信息
  perfectInformation: 'backgroundapi/common/perfectInformation',//完善信息
  changPassword:'backgroundapi/common/changPassword',//修改密码

 
  getnewstoback:'backgroundapi/setNews',//把参数给后端请求新闻数据
  getNews:'backgroundapi/getNews',//向后端查出新闻数据
  
  insert:'backgroundapi/collectInfo/insert',//收藏
  deleteinsert:'backgroundapi/collectInfo/delete',//取消收藏
  newsById:'backgroundapi/query/newsById',//根据id查询新闻
  queryMyCollect:'backgroundapi/collect/queryMyCollect',//我的收藏
  giveLike:'backgroundapi/giveLike/insertInfo',//点赞
  deletelike:'backgroundapi/giveLike/delete',//取消点赞
  querycomment:'backgroundapi/query/comment',//查出评论
  usecomment:'backgroundapi/use/comment',//发表评论
  insertComment:'backgroundapi/comment/insertComment',//回复评论
  userForUserComment:'backgroundapi/query/userForUserComment',//查出子评论
  newsLikeCollect:'backgroundapi/query/newsGiveLikeAndCollect',//新闻点赞和收藏次数
  newsGiveLikeOrNo:'backgroundapi/query/newsGiveLikeOrNo',//新闻是否已被点赞或收藏


  // NewsInfo: '/jdnewsapi/get' ,
  NewsChannel: '/jdnewsapi/channel',
  SearchNews: '/jdnewsapi/newSearch',
  
  searchPhone: '/juheapi/mobile/get',
  searchIp: '/juheapi/ip/ipNew',
  queryWeather:'/juheapi/simpleWeather/query',
  queryWeatherLife:'/juheapi/simpleWeather/life',
  queryEpidemic:'/juheapi/springTravel/risk',
  queryEpidemiceArea:'/juheapi/springTravel/citys',
  queryspringTravel:'/juheapi/springTravel/query',
  queryhsjg:'/juheapi/springTravel/hsjg' ,  

  querybus:'/jdnewsapi/transitLine'
  

}

export default api
