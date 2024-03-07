<template>
  <div>
    <pagehead titles="IP地址查询"></pagehead>
    <div class="phones">    
        <van-field   v-model="params.ip" label="IP地址" placeholder="请输入ip地址" >
           <template #right-icon>
            <van-icon name="search" @click="SearchIp" />
          </template>

        </van-field>
        <div  class="printbox">
          <div v-if="returnInfo">
            <div class="fontcow">国家：{{returnInfo.Country}}{{returnInfo.city}}</div>
            <div class="fontcow">省份区域：{{returnInfo.Province}}</div>
            <div class="fontcow">城市：{{returnInfo.City}}</div>
            <div class="fontcow">运营商：{{returnInfo.Isp}}</div>  
          </div>       
        </div>
    </div>
  </div>
</template>
<script>
import {getSearchIp} from '@/api/user.js'

export default {
  data(){
    return{
      params:{
        key:'662a081fb4faf4160c9b74b8b7cef66a',
        ip:''
      },
      returnInfo:{},
      value: '',
    }
  },
  methods:{
    SearchIp(){
      getSearchIp(this.params).then((res) => {
        if(res.resultcode == '200'){
          this.returnInfo = res.result
        }else{
         this.$toast.fail(res.reason)
        }
      }).catch((error) => {
          this.$toast.fail("系统开点小差。。");
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.phones{
  padding: 65px 12px 12px 12px;
  .printbox{
    margin-top: 15px;
    background-color: #fff;
    height:200px;
    border: 1px #c7c7c7 dashed;
    padding: 20px;
    .fontcow{
      font-size: 15px;
      line-height: 30px;
    }
  }
}
</style>
