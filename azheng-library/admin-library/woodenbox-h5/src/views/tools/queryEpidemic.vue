<template>
    <div>
         <pagehead titles="防疫政策指南"></pagehead>
         <div class="content">
             <div class="contents">
                 <div @click="opensearchgoto(1)">
                     查询出行防疫政策
                 </div>
                 <div @click="opensearchgoto(2)">
                     查询核酸检测机构
                 </div>
                 <div @click="opensearchgoto(3)">
                     全国风险地区汇总
                 </div>
             </div>        
         </div>
          <van-action-sheet v-model="showone" :title=title>
            <div class="onecontent" v-if = "showoneflag == 1">
                <div class="displaycss">
                    <div class="picktitle">
                        请选择出行城市
                    </div>
                    
                    <div class="contentinput">
                        <div class="contentinputs">
                            <input v-model="startarea"  type="text" placeholder="出发地" @click="openpickareashow(1)">
                    
                            <img src="../../assets/img/jh.png" alt="">
                        
                            <input v-model="arivearea" type="text" placeholder="到达地" @click="openpickareashow(2)">
                        </div>                                             
                        <div class="cxbtm" @click="querygoto()">
                            查询
                        </div>
                    </div>                   
                </div>  

                <div class="tracontent">
                    <div class="tracontents" v-if="travelData">
                        <div class="onereo" >
                            <div style=" background-color: #3f8fd9;">
                                出
                            </div>
                            <div>
                                {{travelData.from_info.city_name}}
                            </div>
                            <div class="risklevel" style=" background-color: #def6dc; color:#689f62" v-if="travelData.from_info.risk_level == 0">
                                暂无
                            </div>
                            <div class="risklevel" style=" background-color: #def6dc; color:#689f62"  v-if="travelData.from_info.risk_level == 1">
                                低风险
                            </div>
                            <div class="risklevel"  style=" background-color: #f15a22; color:#ffffff" v-if="travelData.from_info.risk_level == 2" >
                                中风险
                            </div>
                            <div class="risklevel"  style=" background-color: #d93a49; color:#ffffff" v-if="travelData.from_info.risk_level == 3">
                                高风险
                            </div><div class="risklevel" style=" background-color:#f15a22; color:#ffffff" v-if="travelData.from_info.risk_level ==4">
                                部分地区中风险
                            </div><div class="risklevel" style=" background-color: #d93a49; color:#ffffff"  v-if="travelData.from_info.risk_level == 5">
                                部分地区高风险
                            </div><div class="risklevel" style=" background-color: #d93a49; color:#ffffff"  v-if="travelData.from_info.risk_level == 6">
                                部分地区中、高风险
                            </div>
                        </div>
                        <div class="zcdesc">
                            <div class="zcdescs">
                                <div>
                                    高风险地区，进入政策：
                                </div>
                                <div>
                                    {{travelData.from_info.high_in_desc}}
                                </div>
                            </div>
                            <div class="zcdescs">
                                <div>
                                    低风险地区，进入政策：
                                </div>
                                <div>
                                    {{travelData.from_info.low_in_desc}}
                                </div>

                            </div>
                            <div class="zcdescs">
                                <div>
                                    出行政策：
                                </div>
                                <div>
                                    {{travelData.from_info.out_desc}}
                                </div>

                            </div>                           
                        </div>
                        <div class="healthcode"> 
                            <!-- 健康码 -->
                            <div>
                               {{travelData.from_info.health_code_name}} ({{travelData.from_info.health_code_desc}})
                            </div>
                            <div>
                                <img :src="travelData.from_info.health_code_picture" alt="">
                                 <a :href="travelData.from_info.health_code_gid">使用教程</a>
                            </div>
                            <div>
                               
                            </div>
                        </div>
                        <div class="onereo" >
                            <div style=" background-color: #694d9f;">
                                到
                            </div>
                            <div>
                                {{travelData.to_info.city_name}}
                            </div>
                            <div class="risklevel" style=" background-color: #def6dc; color:#689f62" v-if="travelData.to_info.risk_level == 0">
                                暂无
                            </div>
                            <div class="risklevel" style=" background-color: #def6dc; color:#689f62"  v-if="travelData.to_info.risk_level == 1">
                                低风险
                            </div>
                            <div class="risklevel"  style=" background-color: #f15a22; color:#ffffff" v-if="travelData.to_info.risk_level == 2" >
                                中风险
                            </div>
                            <div class="risklevel"  style=" background-color: #d93a49; color:#ffffff" v-if="travelData.to_info.risk_level == 3">
                                高风险
                            </div><div class="risklevel" style=" background-color:#f15a22; color:#ffffff" v-if="travelData.to_info.risk_level ==4">
                                部分地区中风险
                            </div><div class="risklevel" style=" background-color: #d93a49; color:#ffffff"  v-if="travelData.to_info.risk_level == 5">
                                部分地区高风险
                            </div><div class="risklevel" style=" background-color: #d93a49; color:#ffffff"  v-if="travelData.to_info.risk_level == 6">
                                部分地区中、高风险
                            </div>
                        </div>
                        <div class="zcdesc">
                            <div class="zcdescs">
                                <div>
                                    高风险地区，进入政策：
                                </div>
                                <div>
                                    {{travelData.to_info.high_in_desc}}
                                </div>
                            </div>
                            <div class="zcdescs">
                                <div>
                                    低风险地区，进入政策：
                                </div>
                                <div>
                                    {{travelData.to_info.low_in_desc}}
                                </div>
                            </div>
                            <div class="zcdescs">
                                <div>
                                    出行政策：
                                </div>
                                <div>
                                    {{travelData.to_info.out_desc}}
                                </div>
                            </div>                          
                        </div>
                         <div class="healthcode"> 
                            <!-- 健康码 -->
                            <div>
                               {{travelData.to_info.health_code_name}} ({{travelData.to_info.health_code_desc}})：
                            </div>
                            <div>
                                <img :src="travelData.to_info.health_code_picture" alt="">
                                 <a :href="travelData.to_info.health_code_gid">使用教程</a>
                            </div>
                            <div>                              
                            </div>
                        </div>
                    </div>                    
                </div>
            </div>  

            <div class="onecontent" v-if = "showoneflag == 2">
                <div class="jgsearch">
                    <div class="jgsearchs">
                         <div class="descity"> 城市</div>
                         <input type="text"  v-model="hsjgarea" placeholder="请选择要查询的城市"  @click="openpickareashow(3)">
                         <div class="hsjgbtm" @click="submitsearch">查询</div>
                    </div>


                </div>
                <div class="jglist" v-if="jgData">
                    <!-- 机构列表 -->
                    <div class="shi">
                        <span>{{jgData.province}} </span> <span>{{jgData.city}}</span> 核酸检测机构
                        <div class="tips">数据来源卫健委发布整理,更新同步可能有延迟,具体以实际为准</div>
                    </div>
                    <div class="jglists" v-for="(item,index) in jgData.data" :key="index">
                       <div>
                         {{index+1}}、 {{item.name}}
                       </div>
                       <div>
                           地址：{{item.address}}
                       </div>
                       <div>
                           电话：{{item.phone}}
                       </div>
                    </div>
                </div>
            </div>  
            <div class="onecontent" v-if = "showoneflag == 3">
                 <div class="riskarea">
                    <div class="titlerisk">
                        <div>更新时间：{{riskAreadata.updated_date}}</div>
                    </div>
                    <div class="listrisk">
                        <div class="listtitle">
                            高风险地区  （数量：{{riskAreadata.high_count}}）
                        </div>  
                        <div class="listtitlelist">
                            <div  v-for="(item,index) in riskAreadata.high_list" :key="index">
                                {{item.area_name}}:
                                <div class="one" v-for="(items,indexs) in item.communitys" :key="indexs">
                                {{items}}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="listrisk">
                        <div  class="listtitle">
                            中风险地区  （数量：{{riskAreadata.middle_count}}）
                        </div>                     
                        <div class="listtitlelist">                           
                            <div v-for="(item,index) in riskAreadata.middle_list" :key="index">
                                {{item.area_name}}:
                                <div class="one" v-for="(items,indexs) in item.communitys" :key="indexs">
                                {{items}}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </van-action-sheet>

        <van-action-sheet v-model="pickareashow" title="选择城市">
            <div class="pickhi">
                <van-picker show-toolbar  :columns="areaData" @confirm="onConfirm" />
                
            </div>
        </van-action-sheet>

    </div>
</template>
<script>
import {getqueryEpidemic,getqueryEpidemiceArea,getqueryspringTravel,getQueryhsjg} from '@/api/user.js'
export default {

    data(){
        return{
             params:{
                key:'e28e9a36a7059ea37a7995f56a1a9b71',
                from:'',//	出发地城市ID
                to:'',//	目的地城市ID
            },
            paramshsjg:{
                 key:'e28e9a36a7059ea37a7995f56a1a9b71',
                 city_id:''//机构城市id
            },
            riskAreadata:{},
            showoneflag:-1,
            title:'',
            showone:false,
            pickareashow:false,//选择地区flag
            startarea:'',//出发地
            arivearea:'',//到达地
            cascaderValue:'',//地区组件绑定值
            areaData:[],//地区数据
            standarflag:'',//出发到达flag

            travelData:'',//出行数据

            hsjgarea:'',//显示机构城市
            jgData:'' ,//机构数据
        }
       
    },
    mounted(){
       this.getarea()
    },
    methods:{
        opensearchgoto(it){
            this.showone = true
           
            if(it == 1){
                 this.showoneflag = 1
                 this.title= '查询出行防疫政策'
            }else if(it == 2){
                this.showoneflag = 2
                 this.title= '查询核酸检测机构'
            }else if(it == 3){
                 this.showoneflag = 3
                 this.title= '全国风险地区汇总'
                 this. searchRiskArea();
            }

        },

        searchRiskArea(){
            getqueryEpidemic(this.params).then((res) => {
                if(res.error_code == '0'){
                   this.riskAreadata = res.result
                   console.log(this.riskAreadata)
                }else{
                    this.$toast.fail(res.reason)
                }
            }).catch((error) => {
                this.$toast.fail("数据正在加载中...");
            })
        },
        //获取地区
        getarea(){
             getqueryEpidemiceArea(this.params).then((res) => {
                if(res.error_code == '0'){
                
                    this.areaData = res.result
                    for(let i=0;i< this.areaData.length;i++){
                        
                        this.$set(this.areaData[i],'text',this.areaData[i].province)
                        this.$set(this.areaData[i],'children',this.areaData[i].citys)

                        if(this.areaData[i].children){
                            for(let j=0; j < this.areaData[i].children.length;j++){
                                this.$set(this.areaData[i].children[j],'text',this.areaData[i].children[j].city)
                            }
                        }                      
                    }

                   console.log("处理后数据", this.areaData)

                }else{
                    this.$toast.fail(res.reason)
                }
            }).catch((error) => {
                this.$toast.fail("数据正在加载中...");
            })
        },

        openpickareashow(item){
            this.pickareashow = true
            this.standarflag = item
        },

         onConfirm(value, index) {
            console.log(`当前值：${value}, 当前索引：${index}` );
            console.log(index,value)
            if(this.standarflag == 1){
                this.startarea = `${value}` //显示出发
                this.params.from = this.areaData[index[0]].children[index[1]].city_id
                this.pickareashow = false
                console.log( this.params.from )
            }else if(this.standarflag == 2){
                 this.arivearea =  `${value}` //显示到达
                 this.params.to = this.areaData[index[0]].children[index[1]].city_id
                 this.pickareashow = false
                 console.log( this.params.to )
            }else if(this.standarflag == 3){
                 this.hsjgarea =  `${value}` //显示机构
                  this.paramshsjg.city_id = this.areaData[index[0]].children[index[1]].city_id
                  this.pickareashow = false
            }

        },
        querygoto(){           
            if(this.params.from == '') return this.$toast("请选择出发城市")
            if(this.params.to == '') return this.$toast("请选择到达城市")
             getqueryspringTravel( this.params).then((res) => {
                if(res.error_code == '0'){
                   this.travelData = res.result
                   console.log(this.travelData)
                }else{
                    this.$toast.fail(res.reason)
                }
            }).catch((error) => {
                this.$toast.fail("数据正在加载中...");
            })
        },
        submitsearch(){
              if(this.paramshsjg.city_id == '') return this.$toast("请选择查询城市")

               getQueryhsjg( this.paramshsjg).then((res) => {
                    if(res.error_code == '0'){
                       this.jgData = res.result
                        console.log("机构",res.result)
                    }else{
                        this.$toast.fail(res.reason)
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
    padding: 65px 12px 12px 12px;

    .contents{
        margin-top: 50px;
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        height: 300px;
        justify-content: space-around;
        &>div{
            line-height: 50px;
            text-align: center;
            font-size: 16px;
            width: 160px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 2px 2px 5px #ccc;
        }
    }
}
.onecontent{
    
     padding: 16px 16px 100px 16px;
      .riskarea{
        .titlerisk{
            font-size: 16px;
            padding-bottom: 5px;
            &>div{
                padding: 5px 0;
                font-size: 12px;
            }
        }
        .listrisk{
            border-top:1px solid #ccc ;
            .listtitle{
                font-size: 16px;
                line-height: 30px;
                font-weight: bold;
            }
            .listtitlelist{
                font-size: 14px;
                line-height: 23px;
                padding-bottom: 10px;
                &>div{
                    font-weight: bold;
                   .one{
                        font-weight:500;

                    }
                }
            }
        }
    }
    .displaycss{

        font-size: 14px;
        .picktitle{
            width: 100px;
        }
        
        .contentinput{
            margin-top:10px;
             display: flex;
             align-items: center;

             .contentinputs{
                  display: flex;
                  align-items: center;

                  &>input{
                    width: 120px;
                    border:none;
                    // border-bottom: 1px solid #ccc;
                    text-align: center;

                }
                &>img{
                    width: 20px;
                }

             }
            
            .cxbtm{
                background-color: #1296db;
                border: 1px solid #1296db;
                border-radius: 5px;
                margin-left: 20px;
                padding: 3px;
                color: #fff;
                width: 50px;
                text-align: center;
                // flex:1
            }
        }
       
    }
    .tracontent{
        margin-top: 10px;
        .tracontents{
            .onereo{
                display: flex;
                align-items: center;
                :nth-child(1){
                    color: #fff;
                   
                    width: 25px;
                    line-height: 25px;
                    text-align: center;
                    border-radius: 50%;
                }
                :nth-child(2){
                    font-size: 14px;
                    font-weight: bold;
                    padding: 0 10px;
                }
                .risklevel{
                    font-size: 10px;
                    background-color: #def6dc;
                    border-radius: 5px;
                    padding: 2px;
                }
            }
            .zcdesc{
                padding-left: 35px;
                .zcdescs{
                   :nth-child(1){
                       font-size: 12px;
                       font-weight: bold;
                       padding: 6px 0;
                   } 
                }
            }

            .healthcode{
                padding-left: 35px;
                :nth-child(1){
                    font-size: 12px;
                    font-weight: bold;
                    padding: 6px 0;
                }
                :nth-child(2){
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                    &>img{
                        width: 180px;
                    }
                  
                   
                }
            }
        }

    }
    .jgsearch{
        .jgsearchs{
            display: flex;
            align-items: center;
            font-size: 14px;
             &>input{
                width: 220px;
                border:none;
                text-align: center;

            }
            .descity{
                width: 60px;
                text-align: center;
            }
            .hsjgbtm{
                 background-color: #1296db;
                border: 1px solid #1296db;
                border-radius: 5px;
                margin-left: 20px;
                padding: 3px;
                color: #fff;
                width:50px;
                text-align: center;
                // flex:1
               line-height: 22px;
                
                
            }

        }
    }
    .jglist{
        .shi{
            padding: 10px  0;
            font-size: 14px;
            font-weight: bold;
             line-height:25px;
            .tips{
                 font-size: 12px;
                 font-weight: 500;
                 color: rgb(167, 163, 163);
                 line-height: 18px;
            }
        }
        .jglists{
            border-bottom: 1px solid #ccc;
            margin-bottom: 10px;
            line-height: 20px;
            :nth-child(2){
                padding-left:20px;
            
            }
            :nth-child(3){
                padding-left: 20px;
            }
            
        }
    }
}
.pickhi{
    padding: 16px 16px 100px 16px;
    
}
</style>