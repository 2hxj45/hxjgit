<template>
  <div class="container">
    <div class="head_css">
        <img @click="goback" class="backimg" src="@/assets/img/back.png">
        <img class="logo" @click="gotopercenter" src="@/assets/img/woodenboxlogo.jpg" alt="">
        <div   class="headtitle">{{title}}</div>
    </div>
  </div>
</template>
<script>
import { getToken } from '@/utils/auth'
export default {
  props:{
    titles:{
      type:String,
      default:''
    }
  },
  data(){
    return{
      title:''
    }
  },
  mounted(){
    this.title = this.titles
  },
  methods:{
    goback(){
      this.$router.go(-1)
    },
    gotopercenter(){
      console.log(getToken())
      if(getToken() == ''){ //登陆前
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
.container{
  .head_css{
    position:fixed;
    top:0;
    left:0;
    z-index: 10;
    width: 100%;
    height: 53px;
    background-color:#FEA35E;
    display: flex;
    align-items: center;
    // position: relative;
    .backimg{
      position:absolute;
      height: 25px;
      left: 10px;
    }
    .logo{
     position: absolute;
     height: 40px;
     padding:10px;
     border-radius:50%;
     right: 0;
    }
    .headtitle{
      margin: 0 auto;
      color: #fff;
      font-size: 16px;
    }
  }
}
</style>
