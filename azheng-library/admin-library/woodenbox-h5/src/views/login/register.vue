<template>
    <div>
        <pagehead titles="注册"></pagehead>
        <div class="content">
            <div class="contents">
                 <div class="woodenboxlogo">
                    <img src="../../assets/img/woodenboxlogo.jpg" alt="">
                    <span>木盒移动端应用</span>
                </div>

                <div class="conunt">
                    <div class="password" style="padding-top:20px">
                        <span>账号</span>
                        <input type="number" v-model="info.userName" placeholder="请输入账号">
                    </div>
                    <div class="password" >
                        <span>昵称</span>
                        <input type="text" v-model="info.nickname" placeholder="请输入昵称">
                    </div>
                    <div class="password" style="padding-bottom:10px">
                        <span>密码</span>
                        <input type="password"  v-model="info.password"  placeholder="请输入密码">
                    </div>
                </div>

                <div class="loginbtm" @click="register">
                    注册
                </div>


            </div>
        </div>
    </div>
</template>

<script>
import {login,register} from '@/api/user.js'
import { setToken } from '@/utils/auth'
export default {
    data(){
        return{
            info:{

                userName:'',
                nickname:'',
                password:''
            }

        }
    },
    methods:{
        register(){
            if(this.info.nickname == '') return  this.$toast("请输入昵称")
            if(this.info.userName == '') return  this.$toast("请输入账号")
            if(this.info.password == '') return  this.$toast("请设置密码")
            register(this.info).then((res) => {
                if(res.code== '200'){
                    login(this.info).then((res) => {
                        if(res.code== '200'){
                            this.$toast("注册并登录成功")
                            setToken(res.data.token)
                                this.$router.push({
                                    path: '/',
                                });

                        }else{
                            this.$toast.fail(res.msg)
                        }
                    }).catch((error) => {
                        this.$toast.fail("系统开点小差。。");
                    })
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

    }
}
</style>
