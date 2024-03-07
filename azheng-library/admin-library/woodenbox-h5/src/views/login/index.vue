
<template>
    <div>
         <pagehead titles="登录"></pagehead>
         <div class="content">
            <div class="contents">
                 <div class="woodenboxlogo">
                    <img src="../../assets/img/woodenboxlogo.jpg" alt="">
                    <span>木盒移动端应用</span>
                </div>

                <div class="conunt">
                    <div class="password" style="padding-top:20px">
                        <span>账号</span>
                        <input type="number" v-model="data.userName" placeholder="请输入账号">
                    </div>
                    <div class="password" style="padding-bottom:10px">
                        <span>密码</span>
                        <input type="password"  v-model="data.password"  placeholder="请输入密码">
                    </div>
                </div>

                <div class="loginbtm" @click="login">
                    登录
                </div>

                <div class="zccss">
                    还没有账号？<span @click="gotoregister">去注册</span>
                </div>

            </div>

         </div>
    </div>
</template>
<script>
import {login} from '@/api/user.js'
import { setToken } from '@/utils/auth'
export default {
    data(){
        return{
            data:{
                userName:'',
                password:''
            }
        }
    },
    methods:{
        login(){
            if(this.data.userName == '') return  this.$toast("请输入账号")
            if(this.data.password == '') return  this.$toast("请输入密码")

             login(this.data).then((res) => {
                if(res.code== '200'){
                    console.log("登陆成功")
                   this.$toast({
                       duration:8000,
                       message:'登录成功'
                   })
                   setToken(res.data.token)
                    this.$router.push({
                        path:'/',
                    });


                }else{
                    this.$toast.fail(res.msg)
                }
            }).catch((error) => {
                this.$toast.fail("系统开点小差。。");
            })

        },
        gotoregister(){
             this.$router.push({
                path: "/register",
            });
        }
    }
}
</script>
<style lang="scss" scoped>
.content{
     padding: 65px 12px 12px 12px;
     height: 88vh;
     background-color: #fff;
    .contents{
         .woodenboxlogo{
             margin-top: 80px;
             display: flex;
             align-items: center;
             justify-content: center;
            &>img{
                height: 50px;
                border-radius:50%;
            }
            &>span{
                margin-left:10px;
                font-size: 20px;
            }
        }
        .conunt{
            margin-top: 20px;
            .password{
                display: flex;
                align-items: center;
                justify-content: center;
                margin-bottom: 30px;
                font-size: 18px;

                &>span{
                    margin-right: 20px;
                }
                &>input{
                    width: 200PX;
                    border:none ;
                    border-bottom: 1px solid #CCC;
                }
            }
        }
        .loginbtm{
            width: 250px;
            background-color:#FEA35E ;
            color: #fff;
            line-height: 45px;
            text-align: center;
            font-size: 16px;
            border-radius: 10px;
            margin:0 auto;
        }
        .zccss{
            margin-top:160px;
            text-align: center;
            &>span{
                color:#FEA35E;
            }
        }
    }
}
</style>
