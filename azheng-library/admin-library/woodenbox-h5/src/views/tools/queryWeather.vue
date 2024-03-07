<template>
    <div>
        <pagehead titles="天气查询"></pagehead>
        <div class="weather">
            <van-field   v-model="params.city" label="城市" placeholder="请输入城市" >
                <template #right-icon>
                    <van-icon name="search" @click="Searchweather" />
                </template>
            </van-field>

            <div class="printbox" v-if="weatherresult.realtime" >
                <div class="citys">
                    {{weatherresult.city}}
                </div>
                <div class="realwea">
                    <span>实时天气</span>
                    <!-- <div class="eachone">
                        天气：{{weatherresult.realtime.info}}
                    </div>
                    <div  class="eachone">
                        温度：{{weatherresult.realtime.temperature}}℃
                    </div>
                    <div  class="eachone">
                        湿度：{{weatherresult.realtime.humidity}} %RH
                    </div>
                    <div  class="eachone"> 
                        风向：{{weatherresult.realtime.direct}}
                    </div>
                    <div  class="eachone">
                        风力：{{weatherresult.realtime.power}}
                    </div>
                    <div  class="eachone">
                        空气质量指数：{{weatherresult.realtime.aqi}}
                    </div> -->
                    <div class="temperature">
                        <span>{{weatherresult.realtime.temperature}}<span>℃</span></span>                
                    </div>
                    <div class="info">
                        {{weatherresult.realtime.info}}
                    </div>
                    <div class="humidity">
                        <div >
                             湿度：{{weatherresult.realtime.humidity}} %RH
                        </div>
                        <div>
                             风向：{{weatherresult.realtime.direct}}
                        </div>
                    </div>
                    <div class="humidity">
                         <div >
                            风力：{{weatherresult.realtime.power}}
                        </div>
                         <div>
                              空气质量指数：{{weatherresult.realtime.aqi}}
                        </div>
                    </div>

                </div>
                <div class="futureweas">
                    <span>近5天天气情况</span>
                    <div class="futurewea">
                        <div class="feachones" v-for="(item,index) in weatherresult.future" :key="index">
                            <div class="feachone">{{item.date.substring(5,10)}}</div>
                            <div class="feachone">{{item.temperature}}</div>
                            <div class="feachone">{{item.weather}}</div>
                            <div class="feachone">{{item.direct}}</div>
                            <div class="feachone"  v-for="(itemd,indexd) in widtable" :value="itemd.wid" :key="indexd" v-show="item.wid.day == itemd.wid">白天：{{itemd.weather}}</div>
                            <!-- <div class="feachone">{{item.wid.night}}</div> -->
                            <div class="feachone" ><div   v-for="(itemn,indexn) in widtable" :value="itemn.wid" :key="indexn" v-show="item.wid.night == itemn.wid">晚上：{{itemn.weather}}</div></div>
                        </div>
                    </div>
                </div>

                <div class="life">
                    <span>查看城市生活指数</span>
                    <div class="lifelist" v-if="weatherlife">
                        <div class="rowone">
                           <div class="lifeeachone" @click="opendia(lifelist[0],weatherlife.chuanyi)" >
                               <div>{{lifelist[0]}}</div>
                               <!-- <div>{{weatherlife.chuanyi.v}}</div>                              -->
                           </div>
                           <div class="lifeeachone" @click="opendia(lifelist[1],weatherlife.daisan)">
                               <div>{{lifelist[1]}}</div>
                               <!-- <div>{{weatherlife.daisan.v}}</div>                              -->
                           </div>
                           <div class="lifeeachone" @click="opendia(lifelist[2],weatherlife.diaoyu)">
                               <div>{{lifelist[2]}}</div>
                               <!-- <div>{{weatherlife.diaoyu.v}}</div>                              -->
                           </div>
                           <div class="lifeeachone" @click="opendia(lifelist[3],weatherlife.ganmao)">
                               <div>{{lifelist[3]}}</div>
                               <!-- <div>{{weatherlife.ganmao.v}}</div>                              -->
                           </div>
                           <div class="lifeeachone" @click="opendia(lifelist[4],weatherlife.guomin)">
                               <div>{{lifelist[4]}}</div>
                               <!-- <div>{{weatherlife.guomin.v}}</div>                              -->
                           </div>
                        </div>
                        <div class="rowone">
                           <div class="lifeeachone" @click="opendia(lifelist[5],weatherlife.kongtiao)">
                               <div>{{lifelist[5]}}</div>
                               <!-- <div>{{weatherlife.kongtiao.v}}</div>                              -->
                           </div>
                           <div class="lifeeachone" @click="opendia(lifelist[6],weatherlife.shushidu)">
                               <div>{{lifelist[6]}}</div>
                               <!-- <div>{{weatherlife.shushidu.v}}</div>                              -->
                           </div>
                           <div class="lifeeachone" @click="opendia(lifelist[7],weatherlife.xiche)">
                               <div>{{lifelist[7]}}</div>
                               <!-- <div>{{weatherlife.xiche.v}}</div>                              -->
                           </div>
                           <div class="lifeeachone" @click="opendia(lifelist[8],weatherlife.yundong)">
                               <div>{{lifelist[8]}}</div>
                               <!-- <div>{{weatherlife.yundong.v}}</div>                              -->
                           </div>
                           <div class="lifeeachone" @click="opendia(lifelist[9],weatherlife.ziwaixian)">
                               <div>{{lifelist[9]}}</div>
                               <!-- <div>{{weatherlife.ziwaixian.v}}</div>                              -->
                           </div>
                        </div>
                    </div>
                </div>
            </div>



        </div>
        <van-action-sheet v-model="show" :title="diadata.title">
            <div class="content">
                <div>{{diadata.v}}</div>
                <div> {{diadata.des}}</div>
                

            </div>
        </van-action-sheet>
    </div>
</template>
<script>
import {getQueryWeather,getQueryWeatherLife} from '@/api/user.js'
export default {
   data(){
       return{
          params:{
              key:'24980977ed2e8fcd45feadc45edc603a',
              city:''
          } ,
          weatherresult:{},
          weatherlife:{},
          lifelist:[
              "穿衣","带伞","钓鱼","感冒","过敏","空调","舒适度","洗车","运动","紫外线"
          ],
            show:false,
            diadata:{
                title:'',
                v:'',
                des:''
            },


          widtable: [
            {"wid": "00","weather": "晴" },
            {"wid": "01", "weather": "多云" },
            { "wid": "02", "weather": "阴"},
            {"wid": "03","weather": "阵雨"},
            {"wid": "04", "weather": "雷阵雨"},
            {"wid": "05","weather": "雷阵雨伴有冰雹"},
            { "wid": "06", "weather": "雨夹雪"},
            {"wid": "07", "weather": "小雨"},
            {"wid": "08", "weather": "中雨"},
            {"wid": "09", "weather": "大雨"},
            {"wid": "10", "weather": "暴雨" },
            { "wid": "11", "weather": "大暴雨"},
            { "wid": "12", "weather": "特大暴雨"},
            { "wid": "13", "weather": "阵雪"},
            {"wid": "14","weather": "小雪"},
            {"wid": "15", "weather": "中雪"},
            {"wid": "16", "weather": "大雪"},
            { "wid": "17", "weather": "暴雪"},
            { "wid": "18", "weather": "雾"},
            { "wid": "19", "weather": "冻雨" },
            {"wid": "20", "weather": "沙尘暴"},
            { "wid": "21","weather": "小到中雨"},
            {"wid": "22",  "weather": "中到大雨"},
            {"wid": "23","weather": "大到暴雨"},
            {"wid": "24","weather": "暴雨到大暴雨"},
            { "wid": "25", "weather": "大暴雨到特大暴雨"},
            {"wid": "26", "weather": "小到中雪"},
            { "wid": "27", "weather": "中到大雪" },
            { "wid": "28", "weather": "大到暴雪"},
            { "wid": "29", "weather": "浮尘"},
            {"wid": "30","weather": "扬沙"},
            { "wid": "31", "weather": "强沙尘暴"},
            {"wid": "53","weather": "霾"}
          ],

       }
   } ,
   methods:{
       Searchweather(){
           getQueryWeather(this.params).then((res) => {
            //    console.log(res)
               if(res.error_code == '0'){
                   this.weatherresult = res.result
               }else{
                   this.$toast.fail(res.reason)
               }
            }).catch((error) => {
                 this.$toast.fail("数据正在加载中...");
            })

            getQueryWeatherLife(this.params).then((res) => {
               if(res.error_code == '0'){
                   this.weatherlife = res.result.life
                    console.log("ss1", this.weatherlife)
                    // this.weatherlife.forEach((element,index) => {
                    //     element[lifename] = this.lifelist[index]
                    // });
                   //  console.log("111ss1", this.weatherlife)
               }else{
                   this.$toast.fail(res.reason)
               }
            }).catch((error) => {
                 this.$toast.fail("数据正在加载中...");
            })
             

       },
       opendia(title,content){
           
           this.diadata.title = title;
           this.diadata.v= content.v;
            this.diadata.des = content.des
            this.show = true

       }
   }
}
</script>

<style lang="scss" scoped>
.weather{
    padding: 65px 12px 12px 12px;
    .printbox{
    margin-top: 15px;
    background-color: #fff;
    //height:400px;
    border: 1px #c7c7c7 dashed;
    padding: 20px;
    .citys{
        font-size: 18px;
    }
    .realwea{
        margin-top: 10px;
        span{
            font-weight: bold;
            font-size: 15px;
            
        }
        // .eachone{
             
        //     line-height: 30px;
        //     font-size: 14px;
        // }
        .temperature{
            text-align: center;
            // border: 1px solid red;

            span{
                font-size:80px;
                position: relative;
                span{
                    position: absolute;
                    font-size: 20px;
                    top:0px;
                    // border: 1px solid rgb(97, 72, 72);
                }
            }
       
        }
        .info{
            text-align: center;
            font-size: 16px;
        }
        .humidity{
            margin-top: 10px;
            display: flex;
            justify-content: center;
            &>div{
                padding-right: 20px;
            }
        }

    }
    .futureweas{
          margin-top: 20px;
        span{
            font-weight: bold;
            font-size: 15px;
           
        }
        .futurewea{
            margin-top: 10px;
            display: flex;
            // justify-content: space-around;
            // overflow: hidden;
            white-space: nowrap;

            overflow-x: scroll;
            .feachones{
                .feachone{
                    height: 35px;
                    text-align: center;
                    width: 90px;
                }
            }
        }
    }
    .life{
          margin-top: 20px;
        span{
            font-weight: bold;
            font-size: 15px;
           
        }
        .lifelist{
            margin-top: 10px;
            .rowone{
                margin-top: 5px;
                display: flex;
                justify-content: space-evenly;
                .lifeeachone{

                    margin-right: 2px;
                    text-align: center;
                    width: 60px;
                    border: 1px solid #ccc;
                    box-shadow: 2px 2px 3px #ccc;
                   
                    padding:15px 0

                }
            }
        }
    }
   
  }
}
.content{
    padding: 16px 16px 100px 16px;
    color: #333;
    :nth-child(1){
        font-size: 16px;
        padding-bottom:10px  ;
        // border: 1px solid red;
    }
    :nth-child(2){
         font-size: 14px;
    }
}
</style>