<template>
    <div>
         <pagehead titles="公交地铁"></pagehead>
         <div class="content">
             <div class="searparam">
                <van-field  class="field" v-model="params.city" label="城市" placeholder="请输入城市名" />
                <van-field v-model="params.transitno" label="线路" placeholder="请输入公交线路名" />          
                <div class="searchbtm" @click="searchbus">
                    查询
                </div>
             </div>
             <div class="buscontent" v-if="busdata">
                 <div class="transitno">
                    {{busdata[0].transitno}} 
                 </div>
                 <div class="startstation">
                     <span>{{busdata[0].startstation}}</span>
                     <img src="../../assets/img/right.png" alt="">
                     <span>{{busdata[0].endstation}}</span>
                 </div>
                 <div class="starttime">
                     <div class="smcss"  style=" background-color: #3f8fd9;">
                         首
                     </div>
                     <span>
                         {{busdata[0].starttime}}
                     </span>
                     <div class="smcss" style=" background-color: #694d9f;">
                         末
                     </div>
                     <span>
                         {{busdata[0].endtime}}
                     </span>
                     <span class="price" > 
                        票价： {{busdata[0].price}}元
                     </span>
                 </div>
                 <div class="station">
                     <div class="stationeach" v-for="(item,index) in busdata[0].list" :key="index">
                         <img src="../../assets/img/xx.png" alt="">
                        {{item.station}}
                     </div>
                 </div>
             </div>
                       
         </div>
    </div>
</template>
<script>
import {getQuerybus} from '@/api/user.js'
export default {
    data(){
        return{
            params:{
                appkey:'8d9ab996c18b5367595238c400bd61b1',
                city:'',
                transitno:'',
            },
            busdata:''
        }
        
    },

        methods:{
        searchbus(){
             getQuerybus(this.params).then((res) => {
                if(res.code== '10000'){
                   this.busdata = res.result.result
                //    console.log(res.result.result)
                }else{
                    this.$toast.fail(res.msg)
                }
            }).catch((error) => {
                this.$toast.fail("数据正在加载中...");
            })
        }
    }

}
</script>
<style lang="scss" scoped>
.content{
    background-color: #fff;
     padding: 65px 12px 12px 12px;
     .searparam{
        //  position: fixed;
        //  top: 50px;
        //  left: 0;
        //  padding: 10px 12px 0 12px;
        //  background-color: #fff;
        //  width: 100%;
        //  margin: 0 12px;
         .searchbtm{
             width: 100%;
             background-color: #1296db;
             color: #fff;
             text-align: center;
             line-height: 40px;
             font-size: 14px;
             border-radius: 10px;
             margin: 10px 0
         }
     }
     .buscontent{
          font-size: 14px;
          padding-left: 10px;
         
         .transitno{
             font-size: 16px;
             font-weight: bold;
         }
         .startstation{
              display: flex;
              line-height: 30px;
               margin-left: 5px;
             &>img{
                 width: 25px;
             }
         }
         .starttime{
              display: flex;
              align-items: center;
              .smcss{
                  width: 20px;
                  border-radius: 20px;
                  text-align: center;
                  color: #fff;
                  margin:0 5px
              }
              .price{
                  padding-left: 15px;
              }
         }
         .station{
             margin-top:10px;

             .stationeach{
                 line-height: 20px;
                 display: flex;
                 align-items: center;
                 &>img{
                     height: 25px;
                     margin: 0 10px;
                 }
             }
         }
     }
}
</style>
