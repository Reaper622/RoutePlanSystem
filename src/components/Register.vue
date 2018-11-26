<template>
    <div class="main">
        <div class="register">
            <div class="topPanel">
                <span>注册路径优化系统</span>
            </div>
            <el-row  class="row" >
                       <el-col :span="12" :offset="2">
                           <el-input
                            placeholder="输入用户ID"
                            prefix-icon="el-icon-edit"
                            v-model="userIdInput">
                            </el-input>
                       </el-col>
            </el-row>
            <el-row  class="row" >
                       <el-col :span="12" :offset="2">
                           <el-input
                            placeholder="输入用户名"
                            prefix-icon="el-icon-edit"
                            v-model="usernameInput">
                            </el-input>
                       </el-col>
            </el-row>
            <el-row  class="row" >
                       <el-col :span="12" :offset="2">
                           <el-input
                            placeholder="输入密码"
                            prefix-icon="el-icon-edit"
                            v-model="passwordInput">
                            </el-input>
                       </el-col>
            </el-row>
            <el-row  class="row" >
                       <el-col :span="12" :offset="2">
                           <el-input
                            placeholder="确认密码"
                            prefix-icon="el-icon-edit"
                            v-model="repasswordInput">
                            </el-input>
                       </el-col>
            </el-row>
            <el-row  class="row" >
                       <el-col :span="12" :offset="2">
                           <el-input
                            placeholder="输入邮箱"
                            prefix-icon="el-icon-edit"
                            v-model="emailAddressInput">
                            </el-input>
                       </el-col>
                       <el-col :span="1" :offset="1">
                           <el-button type="primary"  icon="el-icon-message" @click="verifyEmail" :loading="emailLoading">验证邮箱</el-button>
                       </el-col>
            </el-row>
            <el-row  class="row" >
                       <el-col :span="12" :offset="2">
                           <el-input
                            placeholder="输入邮箱验证码"
                            prefix-icon="el-icon-edit"
                            v-model="emailVerifyCodeInput">
                            </el-input>
                       </el-col>
            </el-row>
            <el-row class="row reg" type="flex" justify="center">
                <el-col :span="6">
                    <el-button type="primary" size="medium" @click="regist">立 即 注 册</el-button>
                </el-col>
                <el-col :span="6" :offset="2">
                    <el-button  size="medium" @click="toLogin">前 去 登 录</el-button>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
import qs from 'qs'
export default {
    data(){
        return{
            userIdInput:'',
            usernameInput:'',
            passwordInput:'',
            repasswordInput:'',
            emailAddressInput:'',
            emailVerifyCodeInput:'',
            emailLoading:false,//验证邮箱加载
        }
    },
    methods:{
      //跳转到登录界面
      toLogin(){
        this.$router.replace({name:'loginLink'});
      },
      //注册事件
      regist(){
        if(this.checkUsername() && this.checkPassword()&& this.checkRepassword() && this.checkEmail() && this.checkEmailVerifyCode()){
          //发送注册请求
          this.$axios.post('/userSystem/user',qs.stringify({
            userId:this.userIdInput,
            userName:this.usernameInput,
            password:this.passwordInput,
            rePassword:this.repasswordInput,
            eMail:this.emailAddressInput,
            mailCode:this.emailVerifyCodeInput
          }))
            .then( res => {
              console.log(res);
              if(res.data.status == 1){
                this.$router.replace({name:'loginLink'});
              }
            })
        }
      },
      //检查用户名
      checkUsername(){
        if(this.usernameInput === ''){
          this.$alert('输入用户名为空','提示',{
            confirmButtonText: '我知道了'
          })
        }else{
          return true;
        }
      },
      //检查密码
      checkPassword(){
        if(this.passwordInput === ''){
          this.$alert('输入密码为空','提示',{
            confirmButtonText: '我知道了'
          })
        }else{
          return true;
        }
      },
      //检查两次密码输入是否相同
      checkRepassword(){
        if(this.passwordInput != this.repasswordInput){
          this.$alert('输入两次密码不一致','提示',{
            confirmButtonText: '我知道了'
          })
        }else{
          return true;
        }
      },
      //检查邮箱
      checkEmail(){
        let reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if(reg.test(this.emailAddressInput)){
          return true;
        }else{
          this.$alert('邮箱格式有误','提示',{
            confirmButtonText: '我知道了'
          })
        }
      },
      //检查邮箱验证码
      checkEmailVerifyCode(){
        if(this.emailVerifyCodeInput === ''){
          this.$alert('邮箱验证码为空','提示',{
            confirmButtonText: '我知道了'
          })
        }else{
          return true;
        }
      },
      //给邮箱发送验证码
      verifyEmail(){
        if(this.checkEmail()){
            this.emailLoading = true;
            setTimeout(() => {
              this.emailLoading = false
            },7000);
          this.$notify({
            title:'请稍后',
            message: '这可能需要一段时间，请稍等',
            type: 'info'
          })
          //发送验证码请求
          this.$axios.get('/userSystem/user/eMailCode',{
            params:{
              eMail:this.emailAddressInput
            }
          })
            .then( res => {
              //发送成功
              if(res.data.status == 1){
                this.$notify({
                  title: '成功',
                  message: '验证码发送成功，请前往邮箱检查',
                  type: 'success'
                })
              }else{
                this.$notify({
                  title: '失败',
                  message: '验证码发送失败，请稍后重试',
                  type: 'waring'
                })
              }
            } )
        }
      }
    }
}
</script>

<style scoped>
    .main{
        position: absolute;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        height: 100%;
        width: 100%;
    }
    .register{
        position: relative;
        height: 600px;
        width: 400px;
        border: 2px solid #A1BAD4;
    }
    .topPanel{
        width: 100%;
        height: 60px;
        background: #F7F7F7;
        text-align: center;
        border-bottom: 2px solid #409EFF;
    }
    .topPanel span{
        position: relative;
        display: inline-block;
        letter-spacing: 0.5em;
        font-size: 30px;
        line-height: 60px;
        color: #409EFF;
    }
    .row{
        padding-top: 30px;
    }
    .reg{
        padding-top: 60px;
    }
</style>

