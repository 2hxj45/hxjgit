<template>
  <div class="container">
    <pagehead titles="木盒工具"></pagehead>
    <div class="toollist">
      <div class="waidiv">
        <div class="eachonetool" v-for="(item,index) in toollist" @click="linktotool(item.linkpath)" :key="index">
          <!-- <img src="@/assets/img/dq.png" alt=""> -->
          <img class="toollogo" :src=item.imgurl  alt="" />
          <div class="tooltitle">{{item.title}}</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data(){
    return{
      toollist:[{
        imgurl:require('@/assets/img/weat.png'),
        title:'天气查询'  ,
        linkpath:"/queryweather"
      },{
        imgurl:require('@/assets/img/xz.png'),
        title:'防疫政策指南'  ,
        linkpath:"/queryEpidemic"
      },{
        imgurl:require('@/assets/img/gj.png'),
        title:'公交地铁查询'  ,
        linkpath:"/querybussubway"
      },{
        imgurl:require('@/assets/img/dz.png'),
        title:'号码归属地查询',
        linkpath:"/searchphone"
      },{
        imgurl:require('@/assets/img/ip.png'),
        title:'IP地址查询',
        linkpath:"/searchip"
      },{
        imgurl:require('@/assets/img/more.png'),
        title:'更多小工具'  ,
        linkpath:1
      }]
    }
  },
  methods:{
    linktotool(linkpath){
      if(linkpath == 1){
          this.$toast("更多小工具正在努力开发中，敬请期待");
      }else{
         this.$router.push({
            path: linkpath ,
        });
      }

    }
  }
}
</script>
<style lang="scss" scoped>
.container{
  .toollist{
    padding: 65px 12px 12px 12px;
    .waidiv{
      width: 100%;
      display: flex;
			flex-wrap: wrap;
			width: 100%;
      .eachonetool{
        border: 1px rgb(204, 199, 199) solid;
        background-color: #fff;
        border-radius: 10px;
        padding: 5px;
        display: flex;
        width: calc(46% - 14px);
        margin: 10px 7px;
        // width: 49%;
        // justify-content: center;

        height: 60px;
        align-items: center;
        .toollogo{
          width: 30px;

          padding-left: 10px;
        }
        .tooltitle{
          padding-left: 3px;
          font-weight: bold;
          font-size: 14px;
            // text-align: center;
        }
      }
    }
  }

}
</style>
