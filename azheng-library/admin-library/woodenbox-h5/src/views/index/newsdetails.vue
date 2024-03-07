<template>
  <div class="container">
    <pagehead titles="木盒资讯"></pagehead>
    <div class="news">
      <div class="newtitle">
       {{newsdetail.title}}
      </div>
      <div class="froms">
        <div>
          来源：{{newsdetail.src}}
        </div>
        <div>
          时间：{{newsdetail.time}}
        </div>
      </div>
      <div class="newscontent" v-html="newsdetail.content"></div>

      <div class="bottomcss">
        

        <div class="dz">
          <img :src="simgurl" alt="" @click="sc"  :class="{animate:scflag == 1}">
          <span>{{collectNumber}}</span>
          <img :src="zimgurl" alt="" @click="dz"  :class="{animate:dzflag == 1}">
          <span>{{giveLikeNumber}}</span>
        </div>
      </div>


      <div class="plq">
        <!-- 评论区 -->
        <div class="jxpl">
          精选评论
        </div>
        <div class="plnr" v-if="comments.length == 0"> 
            <div class="nonecomm">
              还没有人评论，快来抢沙发吧~
            </div>
        </div>

        <div v-else class="eachcomm">
          <div class="eachcomms" v-for="(item,index) in comments" :key="index">
             <div class="addcon">
                <div class="eachcommss">
                  <img src="../../assets/img/tx.png" alt="">
                  <span>{{item.nickname}}</span>
                  <div class="commontime">
                      {{item.createTime.substring(0,10)}}
                  </div>
                </div>
                <div class="hiufu">
                  <div class="hiufus" @click="huifuone(item)">回复</div>
                  <img @click="dzcomment(item)" v-if="item.whetherGiveLike == 1" src="../../assets/img/zan-s.png" alt=""> 
                  <img @click="dzcomment(item)" v-else src="../../assets/img/zan.png" alt=""> 
                  <span>{{item.likenumInt}}</span>
                </div>

             </div>
            
             <div class="commoncontent">
               {{item.content}}
             </div>
             <div v-if="openchildflag == index" class="huifucontent">
                <div v-for="(it,ind) in childcommdata"  :key="ind" class="huifucontenta">
                      <span>{{it.nickname}}</span> 回复<span>{{it.beNickname}}：</span>{{it.content}}
                </div>

             </div>
            
            <div class="btmlookchild" v-if="item.childComments>0 &&openchildflag!= index" @click="lookchildcomm(item,index)">
              查看评论回复信息
            </div>

          </div>
        </div>
      </div>

      <div class="comment">
        <input type="text" v-model="onecomment" placeholder="发表你的神评妙论">
        <div class="cmibtm" @click="submitcomm">
          发表
        </div>
      </div>

    </div>
    <van-action-sheet v-model="showhuifu" >
      <div class="showhuifu">
        <van-field
          v-model="huifuparams.content"
          center
          clearable
          label="回复"
          placeholder="请输入回复内容"
        >
          <template #button>
            <van-button size="small" type="primary" @click="submithuifu">确认</van-button>
          </template>
        </van-field>
      </div>


    </van-action-sheet>
  </div>
</template>
<script>
import { getToken } from '@/utils/auth'
import {insert,userForUserComment,insertComment,deleteinsert,querycomment,usecomment,giveLike,deletelike,newsLikeCollect,newsGiveLikeOrNo} from '@/api/user.js'
export default {
  data(){
    return{
      newsdetail:{},
      simgurl:require('../../assets/img/shouc.png'),
      scflag:0,
      collectNumber:'',//新闻收藏数量
      zimgurl:require('../../assets/img/zan.png'),
      dzflag:0,
      giveLikeNumber:'',//新闻点赞数量

      onecomment:'',
      comments:[],
      // id:'',//收藏页的新闻id
      showhuifu:false,//回复新闻弹窗
      huifuparams:{
        beUserId:'',//被评论人ID
        commentId:'',//在哪条评论下id
        content:'',//回复内容
      },
      childcommdata:"",//子评论信息
      openchildflag:-1,

      itemflag:'',//记住当前打开的子评论
      indexflag:-1,//记住当前打开的子评论
    }
  },
  mounted(){
    this.init()
  },
  methods:{
    init(){
      this.newsdetail = this.$route.query.name
      // console.log("qq",this.$route.query.id)
      // if(this.$route.query.id ){//从收藏页进来
      //    this.id = this.$route.query.id
      //    console.log("如果有id",this.id)
      //   const newsid={
      //     newsId:JSON.parse(this.id) 
      //   }
      //    newsById(newsid).then((res) => {
      //       if(res.code == 200){
      //         console.log("根据id查新闻",res)
             
      //       }else{
      //           this.$toast.fail(res.msg)
      //       }
      //   }).catch((error) => {
      //       this.$toast.fail("系统开点小差。。");
      //   })
      // }
     

      console.log("页面传参",this.$route.query.name)

    if(this.newsdetail.id == null) return
     const collectInfo={
          newsId:this.newsdetail.id
        }
     this.selectcomment()
      newsGiveLikeOrNo(collectInfo).then((res) => {
            if(res.code == 200){
              console.log("11111111查出新闻是否评论或点赞：",res)
              if(res.data.collectInfo == true){//已收藏
                this.scflag = 1
                 this.simgurl = require('../../assets/img/shouc-s.png')
              }
               if(res.data.giveLikeInfo == true){//已点赞
                this.dzflag = 1
                this.zimgurl = require('../../assets/img/zan-s.png')
              }
            }else{
                this.$toast.fail(res.msg)
            }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })

        this.selectscdz();
        
    },
    //点赞和收藏数量
    selectscdz(){
      const collectInfo={
          newsId:this.newsdetail.id
      }
      newsLikeCollect(collectInfo).then((res) => {
            if(res.code == 200){
              console.log("11111111点赞和收藏数量：",res)
              this.collectNumber = res.data.collectNumber;
              this.giveLikeNumber = res.data.giveLikeNumber;
            }else{
                this.$toast.fail(res.msg)
            }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })
    },
    //查出新闻评论
    selectcomment(){
      const collectInfo={
          newsId:this.newsdetail.id
      }
      querycomment(collectInfo).then((res) => {
            if(res.code == 200){
            console.log("11111111查出新闻评论：",res.data)
            this.comments = res.data
            }else{
                this.$toast.fail(res.msg)
            }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })

        
    },
   
    sc(){
       const collectInfo={
          newsId:this.newsdetail.id
      }
      if(this.scflag == 0) {
        insert(collectInfo).then((res) => {
            if(res.code == 200){
              this.simgurl = require('../../assets/img/shouc-s.png')
              this.scflag = 1
              this.$toast("收藏成功");
              console.log("sc",res)
              this.selectscdz()
            }else{
                this.$toast.fail(res.msg)
            }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })
      }else if( this.scflag == 1){
         deleteinsert(collectInfo).then((res) => {
            if(res.code == 200){
              this.simgurl = require('../../assets/img/shouc.png')
              this.scflag = 0
              this.$toast("已取消收藏");
              console.log("sc",res)
              this.selectscdz()
            }else{
              this.$toast.fail(res.msg)
            }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })          
      }
       
    },
    dz(){
      const collectInfo={
        newsId:this.newsdetail.id
      }
     if(this.dzflag == 0) {
       
        giveLike(collectInfo).then((res) => {
            if(res.code == 200){
              this.zimgurl = require('../../assets/img/zan-s.png')
              this.dzflag = 1
              this.$toast("点赞成功");
              console.log("sc",res)
              this.selectscdz()
            }else{
                this.$toast.fail(res.msg)
            }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })
      }else if( this.dzflag == 1){
         deletelike(collectInfo).then((res) => {
            if(res.code == 200){
              this.zimgurl = require('../../assets/img/zan.png')
              this.dzflag = 0
              this.$toast("已取消点赞");
              this.selectscdz()
              console.log("sc",res)
            }else{
                this.$toast.fail(res.msg)
            }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })       
      }
      
    },
    submitcomm(){
      console.log("11",getToken())
      if(this.onecomment == '') return  this.$toast.fail("请输入评论");
      const params ={
       newsId:this.newsdetail.id,
       content:this.onecomment
     }
      usecomment(params).then((res) => {
          if(res.code == 200){
            this.$toast("发表评论成功");
            this.onecomment = ''
            this.selectcomment();//刷新查询评论
          }else if(res.code == '-1'){
            this.$toast.fail(res.msg+",请重新登录")
          }else{
              this.$toast.fail(res.msg)
          }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })
     
      // this.comments.push({
      //   onecomment:this.onecomment
      // })
      // this.onecomment = ''

      console.log(this.comments)
    },
    //回复评论
    huifuone(item){
      console.log(item)
      this.huifuparams.beUserId = item.userId;
      this.huifuparams.commentId = item.commentId;
      this.showhuifu = true;
    },
    submithuifu(){
       if(this.huifuparams.content == '') return  this.$toast.fail("请输入回复内容");
       
       insertComment(this.huifuparams).then((res) => {
            if(res.code == 200){
              this.$toast("回复评论成功");
              this.showhuifu = false;
              this.huifuparams.content = ''
              // this.childcommdata = ''//由于顺序改变将子评论清零

              this.selectcomment()
              this.lookchildcomms()
              
            }else{
                this.$toast.fail(res.msg)
            }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })    


    },
    lookchildcomm(item,index){
      this.itemflag = item;
      this.indexflag = index;
       this.lookchildcomms();
    },
    lookchildcomms(){
      if(this.itemflag == '' )  return
        const params={
          commentId:this.itemflag.commentId
        }
        userForUserComment(params).then((res) => {
            if(res.code == 200){
            console.log("11111111查出新闻子评论：",res.data)
              this.childcommdata = res.data
              this.openchildflag = this.indexflag 
              console.log("this.openchildflag",this.openchildflag)
            }else{
                this.$toast.fail(res.msg)
            }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })
    },


    dzcomment(item){
      console.log("评论点赞",item)
      const params={
          commentId:item.commentId
        }
      if(item.whetherGiveLike == 1){//已经点赞
         deletelike(params).then((res) => {
            if(res.code == 200){
              // this.zimgurl = require('../../assets/img/zan.png')
              // this.dzflag = 0
              this.$toast("已取消点赞");
              this.selectcomment()
              console.log("sc",res)
            }else{
                this.$toast.fail(res.msg)
            }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })
      }else {
         giveLike(params).then((res) => {
            if(res.code == 200){
              // this.zimgurl = require('../../assets/img/zan-s.png')
              // this.dzflag = 1
              this.$toast("点赞成功");
              console.log("sc",res)
              this.selectcomment() //查询评论信息
            }else{
                this.$toast.fail(res.msg)
            }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })
      }
    }
  }
}
</script>
<style lang="scss" scoped>
.container{
.news{
  padding: 65px 12px 62px 12px;
  // position: fixed;
  .newtitle{
   color: #333;
   text-align: center;
   font-size: 19px;
   font-weight: bold;
  }
  .froms{
    color: #999999;
    padding-top:20px;
    display: flex;
    align-items: conter;
    justify-content: space-around;
  }
  .newscontent{
    // padding: 0 10px;
    font-size: 16px;
    line-height: 27px;
  
  }
  /deep/ .art_img_mini_img{
    width: 100%;
  }
  /deep/ .art_img_tit{
    text-align: center;
    font-weight: 600;
    font-size: 12px;
  }
  /deep/ .art_p{
    text-indent: 2em;
  }
  /deep/ p{
     text-indent: 2em;
  }
  /deep/ img{
     width: 100%;
  }

  .bottomcss{
    height: 28px;
    .dz{
      display: flex;
      align-items: center;
      float: right;
      &>img{
        padding: 5px;
        width:25px;
      }
    }
  }
  .plq{
    .jxpl{
      padding:7px;
      // border-bottom: 1px solid #ccc;
      font-size: 14px;
      margin-bottom: 6px;
    }
    .plnr{
        height: 70px;
        text-align: center;
        padding-top:20px
    }
    .eachcomm{
      .eachcomms{
        margin-bottom: 8px;
        .addcon{
          display: flex;
          justify-content: space-between;
           .eachcommss{
            display: flex;
            align-items: center;
            &>img{
              height: 30px;
              width: 30px;
            }
            &>span{
              padding-left: 10px;
            }
             .commontime{
                padding-left: 10px;
                color: #ccc;
              }
          }
          .hiufu{
            display: flex;
            align-items: center;
            .hiufus{
              color: #fff;
              margin-right: 10px;
              background-color: #ccc;
              // padding: 2px;
              text-align: center;
              width: 30px;
              border-radius: 5px;
            }
            &>img{
              width:20px;
              height: 20px;
            }
          }

        }
       
        .commoncontent{
          padding-left: 40px;
          padding-top: 8px;
        }
        .huifucontent{
          padding-left: 40px;
          padding-top: 8px;
          .huifucontenta{
             padding-bottom: 8px;
             &>span{
               color: rgb(21, 180, 243);
               padding:0 5px;
             }
          }
        }
        .btmlookchild{
          padding-left: 40px;
          padding-top: 10px;
          color: rgb(21, 180, 243);

        }
       

      }
    }
  }

  .comment{
    height: 40px;
    width: 350px;
    position: fixed;
    bottom: 0;
   
    display: flex;
    align-items: center;
    padding: 8px 0;
    
    border-top:1px solid #ccc;
    background-color: #fff;
    // justify-content: center;
    &>input{
      width: 270px;
      height: 28px;
       border:none;
    }
    .cmibtm{
      margin-left: 20px;
      background-color: #1296db;
      border: 1px solid #1296db;
      border-radius: 5px;
      color: #fff;
      text-align: center;
      width: 50px;
      line-height: 28px;
    }
  }

}
.showhuifu{
  padding: 20px 0;
}
 
  
}


.animate {
        animation: scaleDraw 1s ease-in-out;
    }
  /* 点赞动画效果 */
  @keyframes scaleDraw{
    0% {
           transform: scale(1);  /*开始为原始大小*/
    }
    25% {
          transform: scale(1.2); /*放大1.1倍*/
    }
    50% {
        transform: scale(1);
    }
    75% {
        transform: scale(1.1);
    }
   
  }

</style>