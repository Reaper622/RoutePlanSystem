<template>
    <div class="main">
        <div class="register">
            <div class="topPanel">
                <span>注册路径优化系统</span>
            </div>
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
                            placeholder="输入邮箱"
                            prefix-icon="el-icon-edit"
                            v-model="usernameInput">
                            </el-input>
                       </el-col>
                       <el-col :span="1" :offset="1">
                           <el-button type="primary"  icon="el-icon-message">验证邮箱</el-button>
                       </el-col>
            </el-row>
            <el-row  class="row" >
                       <el-col :span="12" :offset="2">
                           <el-input
                            placeholder="输入密码"
                            prefix-icon="el-icon-edit"
                            v-model="usernameInput">
                            </el-input>
                       </el-col>
            </el-row>
            <el-row  class="row" >
                       <el-col :span="12" :offset="2">
                           <el-input
                            placeholder="确认密码"
                            prefix-icon="el-icon-edit"
                            v-model="usernameInput">
                            </el-input>
                       </el-col>
            </el-row>
            <el-row  class="row" >
                       <el-col :span="12" :offset="2">
                           <el-input
                            placeholder="输入邮箱验证码"
                            prefix-icon="el-icon-edit"
                            v-model="usernameInput">
                            </el-input>
                       </el-col>
            </el-row>
            <el-row class="row reg" type="flex" justify="center">
                <el-col :span="6">
                    <el-button type="primary" size="medium">立 即 注 册</el-button>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            usernameInput:'',
            passwordInput:'',
            repasswordInput:'',
            emailAddressInput:'',
            emailVerifyCodeInput:''
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
        padding-top: 80px;
    }
</style>

