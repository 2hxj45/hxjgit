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

    </div>
  </div>
</template>
<script>
import { getToken } from '@/utils/auth'
import {newsById} from '@/api/user.js'
export default {
  data(){
    return{
        id:'',
        newsdetail:''
    }
  },
  mounted(){
    this.init()
  },
  methods:{
    init(){
        this.id = this.$route.query.id
          const newsid={
          newsId:JSON.parse(this.id) 
        }
         newsById(newsid).then((res) => {
            if(res.code == 200){
              console.log("根据id查新闻",res)
             this.newsdetail = res.data
            }else{
                this.$toast.fail(res.msg)
            }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })
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

    }
 
}


</style>