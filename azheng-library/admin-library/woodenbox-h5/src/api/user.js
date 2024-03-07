import api from './index'
// axios
import request from '@/utils/request'

//登录
export function login(data) {
  return request({
    url: api.Login,
    method: 'post',
    data
  })
}
//注册
export function register(data) {
  return request({
    url: api.Register,
    method: 'post',
    data
  })
}
//退出登录
export function logout(data) {
  return request({
    url: api.Logout,
    method: 'get',
    data
  })
}

//查出个人信息
export function getUserInfo(data) {
  return request({
    url: api.getUserInfo,
    method: 'post',
    data
  })
}
//完善信息
export function perfectInformation(data) {
  return request({
    url: api.perfectInformation,
    method: 'post',
    data
  })
}
//修改密码
export function changPassword(data) {
  return request({
    url: api.changPassword,
    method: 'post',
    data
  })
}
//把参数给后端请求新闻数据
export function getnewstoback(data) {
  return request({
    url: api.getnewstoback,
    method: 'post',
    data
  })
}
//向后端查出新闻数据
export function getNews(data) {
  return request({
    url: api.getNews,
    method: 'post',
    data
  })
}
//收藏
export function insert(data) {
  return request({
    url: api.insert,
    method: 'post',
    data
  })
}
//取消收藏
export function deleteinsert(data) {
  return request({
    url: api.deleteinsert,
    method: 'post',
    data
  })
}
//我的收藏
export function queryMyCollect(data) {
  return request({
    url: api.queryMyCollect,
    method: 'post',
    data
  })
}
//根据id查询新闻
export function newsById(data) {
  return request({
    url: api.newsById,
    method: 'post',
    data
  })
}
//查出评论
export function querycomment(data) {
  return request({
    url: api.querycomment,
    method: 'post',
    data
  })
}
//发表评论
export function usecomment(data) {
  return request({
    url: api.usecomment,
    method: 'post',
    data
  })
}
//回复评论
export function insertComment(data) {
  return request({
    url: api.insertComment,
    method: 'post',
    data
  })
}
//
//查询子评论
export function userForUserComment(data) {
  return request({
    url: api.userForUserComment,
    method: 'post',
    data
  })
}
//点赞
export function giveLike(data) {
  return request({
    url: api.giveLike,
    method: 'post',
    data
  })
}
//取消点赞
export function deletelike(data) {
  return request({
    url: api.deletelike,
    method: 'post',
    data
  })
}
//新闻点赞和收藏次数
export function newsLikeCollect(data) {
  return request({
    url: api.newsLikeCollect,
    method: 'post',
    data
  })
}
//新闻是否已被点赞或收藏
export function newsGiveLikeOrNo(data) {
  return request({
    url: api.newsGiveLikeOrNo,
    method: 'post',
    data
  })
}
// 新闻资讯 get 方法
export function getNewsInfo(params) {
  return request({
    url: api.NewsInfo,
    method: 'get',
    params,
    hideloading: true
  })
}
// 新闻频道分类 get 方法
export function getNewsChannel(params) {
  return request({
    url: api.NewsChannel,
    method: 'get',
    params,
    hideloading: true
  })
}
// 搜索新闻
export function getSearchNews(params) {
  return request({
    url: api.SearchNews,
    method: 'get',
    params,
    hideloading: true
  })
}
//手机号归属地查询
export function getSearchPhone(params) {
  return request({
    url: api.searchPhone,
    method: 'get',
    params,
    hideloading: true
  })
}
//ip地址查询
export function getSearchIp(params) {
  return request({
    url: api.searchIp,
    method: 'get',
    params,
    hideloading: true
  })
}
//天气查询
export function getQueryWeather(params) {
  return request({
    url: api.queryWeather,
    method: 'get',
    params,
    hideloading: true
  })
}
//天气生活指数查询
export function getQueryWeatherLife(params) {
  return request({
    url: api.queryWeatherLife,
    method: 'get',
    params,
    hideloading: true
  })
}
//疫情中高风险地区查询
export function getqueryEpidemic(params) {
  return request({
    url: api.queryEpidemic,
    method: 'get',
    params,
    hideloading: true
  })
}
//地区查询
export function getqueryEpidemiceArea(params) {
  return request({
    url: api.queryEpidemiceArea,
    method: 'get',
    params,
    hideloading: true
  })
}
//查询出行
export function getqueryspringTravel(params) {
  return request({
    url: api.queryspringTravel,
    method: 'get',
    params,
    hideloading: true
  })
}
//查询核酸检测机构
export function getQueryhsjg(params) {
  return request({
    url: api.queryhsjg,
    method: 'get',
    params,
    hideloading: true
  })
}

//查询公交
export function getQuerybus(params) {
  return request({
    url: api.querybus,
    method: 'get',
    params,
    hideloading: true
  })
}
