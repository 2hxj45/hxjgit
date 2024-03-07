<template>
  <div>
    <pagehead titles="号码归属地查询"></pagehead>
    <div class="phones">    
        <van-field  type="tel" v-model="params.phone" label="手机号码" placeholder="请输入手机号码" >
           <template #right-icon>
            <van-icon name="search" @click="SearchPhone" />
          </template>


        </van-field>
        <div  class="printbox">
          <div v-if="returnInfo">
            <div class="fontcow">归属地：{{returnInfo.province}}{{returnInfo.city}}</div>
            <div class="fontcow">运营商：{{returnInfo.company}}</div>
            <div class="fontcow">邮政编码：{{returnInfo.zip}}</div>
            <div class="fontcow">区号：{{returnInfo.areacode}}</div>  
          </div>       
        </div>
    </div>
  </div>
</template>
<script>
import {getSearchPhone} from '@/api/user.js'

export default {
  data(){
    return{
      params:{
        key:'ffdc4560e25494f9999eb899c55ef830',
        phone:''
      },
      returnInfo:{},

      value: '',
    }
  },
  methods:{
    SearchPhone(){
     this.returnInfo = {}
      getSearchPhone(this.params).then((res) => {
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
