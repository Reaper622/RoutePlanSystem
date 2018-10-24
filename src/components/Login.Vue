<template>
    <div class="main">
       <div class="loginBody">
           <div class="topPanel">
               <img src="../assets/UserSystem/Icon/van.png" alt="货车图标">
               <span>路径优化系统登录</span>
           </div>
           <div class="userSection">
               <div class="baiduTech">
                   <img src="../assets/UserSystem/images/baiduMap.jpg" alt="百度地图" >
                   <span>百度地图提供技术支持</span>
               </div>
               <div class="loginSection">
                   <el-row type="flex" class="row" justify="center">
                       <el-col :span="16">
                           <el-input
                            placeholder="输入用户名"
                            prefix-icon="el-icon-edit"
                            v-model="usernameInput">
                            </el-input>
                       </el-col>
                   </el-row>
                   <el-row type="flex" class="row" justify="center">
                       <el-col :span="16">
                           <el-input
                           placeholder="输入密码"
                           prefix-icon="el-icon-view"
                           v-model="passwordInput">
                           </el-input>
                       </el-col>
                   </el-row>
                   <el-row type="flex" class="row buttons" justify="space-around">
                       <el-col :span="8"><el-button type="primary" round>立 即 登 录</el-button></el-col>
                       <el-col :span="8"><el-button  round @click="toRegister">前 去 注 册</el-button></el-col>
                   </el-row>
               </div>
           </div>
            <div class="bottomPanel">
                &copy;Copyright:2018 Mis Lab 
            </div>
       </div>
    </div>
</template>
<script>
export default {
    name:'Login',
    data(){
        return{
            usernameInput:'',
            passwordInput:''
        }
    },
    methods:{
        toRegister:function(){
            this.$router.replace({name:'registerLink'});
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
    .loginBody{
        position: relative;
        margin: auto;
        width: 600px;
        height: 500px;
        border: 3px solid #A1BAD4;
    }
    .topPanel{
        width: 100%;
        height: 60px;
        background: #F7F7F7;
        border-bottom: 3px solid #409EFF;
    }
    .topPanel img{
        position: relative;
        display: inline-block;
        width: 40px;
        height: 40px;
        left: 40px;
        top: 10px;
    }
    .topPanel span{
        position: relative;
        display: inline-block;
        letter-spacing: 0.5em;
        left: 150px;
        font-size: 30px;
        line-height: 60px;
        color: #409EFF;
    }
    .userSection{
        width: 600px;
        height: 400px;
    }
    .baiduTech{
        position: relative;
        vertical-align: top;
        height: 400px;
        width: 200px;
        display: inline-block;
        border-right: 3px solid #409EFF;
        box-sizing: border-box;
    }
    .baiduTech img {
        position: relative;
        display: block;
        width: 150px;
        height: 150px;
        left: 25px;
        top: 100px;
        border-radius: 10px;
        margin: 0;
        padding: 0;
    }
    .baiduTech span{
        position: relative;
        display: inline-block;
        width: 200px;
        text-align: center;
        font-size: 14px;
        top: 150px;
        color: gray;
    }
    .loginSection{
        position: relative;
        display: inline-block;
        vertical-align: top;
        left: 25px;
        top: 100px;
        width: 350px;
        height: 300px;
    }
    .row{
        padding-bottom: 30px;
    }
    .buttons{
        padding-top: 50px;
    }
    .bottomPanel{
        position: relative;
        width: 600px;
        height: 40px;
        background: #383838;
        text-align: center;
        font-size: 16px;
        color: white;
        line-height: 40px;
    }
    
</style>
