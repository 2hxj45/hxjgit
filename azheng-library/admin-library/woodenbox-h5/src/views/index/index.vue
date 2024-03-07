<template>
  <div class="content">
      <div class="head_css">
        <img class="logo" src="@/assets/img/woodenboxlogo.jpg" alt=""  @click="gotopercenter">
        <input  class="seachnews" v-model="seachkey" placeholder="输入您想要查找的资讯" type="text">
        <img @click="seachnews" class="seachlogo" src="@/assets/img/seach.png" alt="">
        <div class="tool_com">
          <img @click="toollist" class="tool" src="@/assets/img/tool.png"  alt="">
        </div>
      </div>

    <van-tabs class="tabs" v-model="channel" @click="choosechann" animated>
      <van-tab v-for="(item,index) in channellist" :key=index :title="item">

         <div class="newslist">
        <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
          <van-list
            v-model="loading"
            :finished="finished"
            finished-text="没有更多了哦"
            @load="onLoad"
          >
            <div @click="shownews(item)" class="onenews" v-for="(item,index) in newslist" :key ="index" >
              <!-- 左侧图片 -->
              <div class="newimgs">
                  <img class="newimg" :src="item.pic" alt="">
              </div>
              <!-- 右侧标题等 -->
              <div class="rightcon">
                <!-- 标题 -->
                <div class="font_t">
                  {{item.title}}
                </div>
                <!-- 来源时间 -->
                <div class="from_time">
                  <!-- 来源 -->
                  <div class="froms">
                    {{item.src}}
                  </div>
                  <!-- 时间 -->
                  <div class="times">
                    {{item.time}}
                  </div>
                </div>
              </div>

            </div>
          </van-list>
        </van-pull-refresh>
    </div>
      </van-tab>
    </van-tabs>
  </div>
</template>
<script>
import { getnewstoback,getNews,getNewsInfo ,getNewsChannel ,getSearchNews} from '@/api/user.js'
import { getToken } from '@/utils/auth'
export default {
  data(){
    return{
      seachkey:'', //搜索
      loading: false, // 触发load事件
      finished: false, // true 为加载完毕
      refreshing: false, //是否处于加载中状态
      start:0,//获取资讯起始位置
      pageIndex:1,//第几页
      newslist:[],
      channellist:[],//新闻频道分类
      channel: 0,
    }
  },
  mounted() {
    this.initchannel()
  },
  methods:{
    //get新闻频道分类
    initchannel() {
      const params = {appkey: '8d9ab996c18b5367595238c400bd61b1' }
      getNewsChannel(params).then((res) => {
        if(res.code == '10000'){
          this.channellist  = res.result.result
        }else{
         this.$toast.fail(res.msg)
        }
      }).catch((error) => {
          this.$toast("数据正在加载中...");
      })
    },
    choosechann(name,title){ //频道栏点击事件
    //this.$toast(name)
     this.onRefresh();
    },
    seachnews(){
     // this.$toast("用户点击了搜索")
      const param = {appkey: '8d9ab996c18b5367595238c400bd61b1',keyword:this.seachkey}
      getSearchNews(param).then((res) => {
        if(res.code == '10000'){
          this.newslist  = res.result.result.list
        }else{
         this.$toast.fail(res.msg)
        }
      }).catch((error) => {
           this.$toast("数据正在加载中...");
      })

    },
    toollist(){
      this.$router.push({
          path: "/toolindex",
      });
      // this.$toast("用户点击了工具")
    },

    //加载
    onLoad() {
      this.loading = false; //取消加载
      if (this.start >= 40) {
          this.finished = true;
      }else{
        const params = {
            appkey: '8d9ab996c18b5367595238c400bd61b1' ,
            channel:this.channellist[this.channel],
            start:this.start,
            pageIndex:this.pageIndex,
            pageSize:10
          }
        console.log("入参",params)

        //把参数给后端请求新闻数据
        getnewstoback(params).then((res) => {
          if(res.code == '200'){
            console.log(res)
            this.getnews(params)
          }else{
            this.$toast.fail(res.msg)
          }
        }).catch((error) => {
            this.$toast("数据正在加载中...");
        })

        this.start = this.start + 10;
        this.pageIndex = this.pageIndex +1
      }
    },

    getnews(params){
       //查出新闻数据
        getNews(params).then((res) => {
          if (this.refreshing) { //取消刷新
            this.list = [];
            this.refreshing = false;
          }
          console.log("1112",res)
          if(res.code == '200'){
              let resinfo  = res.data.list
              console.log("看看resinfo",resinfo)
              for(let i = 0;i< resinfo.length ;i++){
                this.newslist.push(resinfo[i]);
              }
              console.log("我拿到的", this.newslist)
          }else{
            this.$toast.fail(res.msg)
          }
        }).catch((error) => {
            this.$toast("数据正在加载中...");
        })

    },
    //刷新
    onRefresh() {
      // 清空列表数据
      this.finished = false;
      this.start = 0;
      this.pageIndex = 1;
      this.newslist = [];
      // 重新加载数据
      // 将 loading 设置为 true，表示处于加载状态
      this.loading = true;
      this.onLoad();
    },
    //查看新闻
    shownews(item){
      //跳转
      console.log("11",item)
        this.$router.push({
        path: "/newsdetails",
        query: {name: item },
        });

    },
    gotopercenter(){
      console.log("打印token",getToken())
      if(getToken() == undefined){ //登陆前
       this.$router.push({
            path: "/login",
        });

      }else{
        this.$router.push({
            path: "/personalcenter",
        });
      }
    }
  }
}
</script>
<style lang="scss" scoped>
/deep/ .van-tabs__wrap{
  position:fixed;
    top:53px;
    left:0;
    z-index: 10;
    width: 100%;
}



.content{
 .head_css{
  //  position: fixed;
  //  top:0;
    position:fixed;
    top:0;
    left:0;
    z-index: 10;
    width: 100%;
   height: 53px;
   background-color:#FEA35E;
   display: flex;
  //  justify-content: center;
   align-items: center;
  //  position: relative;
   .logo{
     height: 35px;
     padding:10px;
     border-radius:50%;
   }
   .seachlogo{
     position: absolute;
     top: 17px;
     left:65px;
     width: 20px;
     z-index: 999;

   }
   .seachnews{
     height: 30px;
     width: 55%;
     border-radius:40px;
     border: #FEA35E;
     padding: 0 10px 0 40px;
   }
   .tool_com{
     width: 17%;
    //  border: 1px red solid ;
     display: flex;
     justify-content: center;

    .tool{
      width: 25px;
    }
   }

 }
 .newslist{
   margin-top: 100px;
   .onenews{
    padding: 10px;
    margin:0 10px;
    border-bottom: 1px #D3D3D4 solid;
    display: flex;

    .newimgs{
      .newimg{
        height: 100px;
        width: 100px;
        }
    }
    .rightcon{
      padding-left: 10px;
      position: relative;
      width: 70%;
      .font_t{
        font-size: 15px;
        font-weight: bold;
        letter-spacing: 0.8px;
      }
      .from_time{
        position: absolute;
        bottom: 7px;
        display: flex;
        width: 100%;
        justify-content: space-around;
        .froms{
          color:#9e9e9e ;
        }
        .times{
          color:#9e9e9e ;
          padding-top: 2px;
        }

      }
    }
   }

 }
}
</style>
