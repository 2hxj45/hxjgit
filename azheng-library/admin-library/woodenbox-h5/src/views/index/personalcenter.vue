<template>
    <div>
        <pagehead titles="个人中心"></pagehead>

        <div class="content">
            <van-cell-group>
                <van-cell title="头像" value="" >
                    <template #right-icon>
                       <img src="../../assets/img/tx.png" class="tx" alt="">
                    </template>
                </van-cell>
                <van-cell title="昵称" v-model="getuserinfo.nickname"  />
                <van-cell title="账号" v-model="getuserinfo. userName"   />
                <van-cell title="性别" v-model="getuserinfo.gender"   />
                <van-cell title="年龄" v-model="getuserinfo.age" />
                <van-cell title="我的收藏" @click="gomysc" is-link/>
            </van-cell-group>
        </div>
        <div class="btms">
            <div  class="outlogin" @click="openalter(1)">
                修改个人信息
            </div>
            <div  class="outlogin" @click="openalter(2)">
                修改密码
            </div>
            <div  class="outlogin" @click="openalter(3)">
                退出登录
            </div>
        </div>
         <van-action-sheet v-model="showalter" :title=title>
            <div class="onecontent" v-if="showflag == 1">

                 <van-field
                   v-model= userinfo.nickname
                    placeholder="请输入昵称"
                    label="昵称"
                    :border= false
                />

                 <van-field
                    v-model= userinfo.age
                    placeholder="请输入年龄"
                    label="年龄"
                    :border= false
                    type="number"
                />
                 <van-cell title="性别" class="sexvancell">
                    <van-radio-group v-model= userinfo.gender  direction="horizontal">
                        <van-radio name="1">男</van-radio>
                        <van-radio name="0">女</van-radio>
                    </van-radio-group>
                </van-cell>
                <div class="submit" @click="submitinfo">
                  确认
                </div>
            </div>
            <div  class="onecontent" v-if="showflag == 2">
                 <van-field
                    v-model= password.originalPwd
                    placeholder="请输入原密码"
                    label="原密码"
                    type="password"
                    :border= false
                />
                 <van-field
                    v-model= password.newPwd
                    placeholder="请输入新密码"
                    label="新密码"
                     type="password"
                    :border= false
                />
                 <van-field
                   v-model= password.confirmPwd
                    placeholder="请输入确认密码"
                    label="确认密码"
                     type="password"
                    :border= false

                />
                 <div class="submit"  @click="changPassword">
                  确认
                </div>
            </div>
         </van-action-sheet>


    </div>
</template>
<script>
import {logout,getUserInfo,perfectInformation,changPassword,} from '@/api/user.js'
import { removeToken } from '@/utils/auth'
export default {
    data(){
        return{

            getuserinfo:'', //获取个人信息
            userinfo:{  //修改个人信息
                age:'',
                gender:'',
                nickname:''
            },
            showalter:false,
            title:'',
            showflag:'',
            password:{
                originalPwd:'',
                newPwd:'',
                confirmPwd:''
            }

        }
    },
    mounted(){
        this.inituserinfo()
    },
    methods:{
        //查出个人信息
        inituserinfo(){
            getUserInfo().then((res) => {
                if(res.code == 200){
                    console.log("111111",res.data)
                  this.getuserinfo = res.data
                  this.userinfo = res.data
                  if( this.getuserinfo.gender == 0 ){
                    this.getuserinfo.gender = '女'
                  }else if( this.getuserinfo.gender == 1){
                       this.getuserinfo.gender = '男'
                  }else{
                       this.getuserinfo.gender = '未设置'
                  }
                   if( this.getuserinfo.age == null){
                    this.getuserinfo.age  = '未设置'
                  }
                }else if(res.code = -1){
                    this.$toast.fail("登录失效，请重新登录")
                     this.$router.push({
                        path: "/login",
                    });
                }else{
                    this.$toast.fail(res.msg)
                }
            }).catch((error) => {
                this.$toast.fail("系统开点小差。。");
            })


        },

        openalter(it){
            if(it == 1){
                this.showflag = 1;
                this.title = '修改个人信息'
                this.showalter = true
            }else if(it == 2){
                this.showflag = 2;
                this.title = '修改密码'
                this.showalter = true
            }else if(it == 3){
                this.showflag = 3;
                //退出登录
               this.logout()

            }
        },
        logout(){
             logout().then((res) => {
                if(res.code== '200'){
                    this.$toast({
                        message:'退出登录成功'
                    })
                removeToken()
                    this.$router.push({
                        path:'/login',
                    });

                }else{
                    this.$toast.fail(res.msg)
                }
            }).catch((error) => {
                this.$toast.fail("系统开点小差。。");
            })

        },
        submitinfo(){
            if(this.userinfo.gender == '女'){
                this.userinfo.gender = 0
            }else if(this.userinfo.gender == '男'){
                 this.userinfo.gender = 1
            }
            if(this.userinfo.age >110){
                  this.$toast.fail("请填写真实年龄哦~")
                  return
            }
             perfectInformation(this.userinfo).then((res) => {
                if(res.code == '200'){
                    console.log("111111",res.data)
                     this.showalter = false
                     this.inituserinfo()
                }else{
                    this.$toast.fail(res.msg)
                }
            }).catch((error) => {
                this.$toast.fail("系统开点小差。。");
            })
        },
        changPassword(){
             changPassword(this.password).then((res) => {
                if(res.code == '200'){
                    console.log("111111",res.data)
                     this.showalter = false

                     this.logout()
                    this.$toast("密码修改成功,需要重新登录");
                }else{
                    this.$toast.fail(res.msg)
                }
            }).catch((error) => {
                this.$toast.fail("系统开点小差。。");
            })
        },
       gomysc(){
        this.$router.push({
            path: "/mysc",
          });
        }
    }
}
</script>
<style lang="scss" scoped>
.content{
     padding: 65px 12px 12px 12px;
    //  margin-bottom: 10px;
     .tx{
         width: 40px;
         border-radius: 50%;
     }

}
.btms{
    display: flex;
    flex-direction: column;
    height: 220px;
    justify-content: space-evenly;
    .outlogin{
        width: 280px;
        background-color:#1296db ;
        color: #fff;
        line-height: 45px;
        text-align: center;
        font-size: 16px;
        border-radius: 10px;
        margin:0 auto;
    }

}
.onecontent{
     padding: 16px 16px 50px 16px;
     .sexvancell{
        /deep/.van-cell__title{
            width: 100px;
        }
    }
    .submit{
        width: 200px;
        background-color:#1296db ;
        color: #fff;
        line-height: 40px;
        text-align: center;
        font-size:14px;
        border-radius: 10px;
        margin:0 auto;
        margin-top: 20px;
    }
}
</style>

