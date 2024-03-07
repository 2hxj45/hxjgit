<template>
    <div>
        <pagehead titles="我的收藏"></pagehead>
        <div class="content">
            <div class="eachone"  v-for="(item,index) in list" :key="index">
                <div class="eachones"  v-if="item.title" @click="clickoneco(item)">
                   {{item.title}}
                   <img src="../../assets/img/rights.png" alt="">
                 </div>

            </div>
        </div>
    </div>
</template>
<script>
import {queryMyCollect} from '@/api/user.js'
export default {
  data(){
      return{
          list:'',
      }
  },
  mounted(){
      this.init()
  },
  methods:{
      init(){
          queryMyCollect().then((res) => {
            if(res.code == 200){
                //我的收藏

                this.list = res.data
                console.log("11111我的收藏", this.list)
            }else{
                this.$toast.fail(res.msg)
            }
        }).catch((error) => {
            this.$toast.fail("系统开点小差。。");
        })

      },
      clickoneco(it){
          console.log(it)

        this.$router.push({
          path: "/myscnewsbyid",
          query: {id: it.newsId},
          });
      }

  }
}
</script>
<style lang="scss" scoped>
.content{
    padding: 65px 12px 12px 12px;
    .eachone{
        position: relative;
        .eachones{
            background-color: #fff;
            margin-top: 10px;
            font-size: 14px;
            padding: 9px 0 9px 5px;
            overflow: hidden;/*超出部分隐藏*/
            white-space: nowrap;/*不换行*/
            text-overflow:ellipsis;
            // width: ;
              &>img{
                  position: absolute;
                  right: 0;
                  width: 20px;
            }
        }

    }
}
</style>
