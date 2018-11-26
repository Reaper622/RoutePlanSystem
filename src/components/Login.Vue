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
                           type="password"
                           prefix-icon="el-icon-view"
                           v-model="passwordInput">
                           </el-input>
                       </el-col>
                   </el-row>
                   <el-row type="flex" class="row" justify="start">
                       <el-col :span="10" :offset="4">
                           <el-input
                           placeholder="点击获取验证码"
                           prefix-icon="el-icon-edit"
                           v-model="verifyInput">
                           </el-input>
                       </el-col>
                       <el-col :span="6" :offset="1">
                            <img :src="verifyCodeURL" class="verifyCode" v-show="verifyCodeGetted" @click="verifyCode" />
                       </el-col>
                   </el-row>
                   <el-row type="flex" class="row buttons" justify="space-around">
                       <el-col :span="8"><el-button type="primary" round @click="login">立 即 登 录</el-button></el-col>
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
import qs from "qs";
export default {
  name: "Login",
  data() {
    return {
      usernameInput: "",
      passwordInput: "",
      verifyInput: "",
      verifyCodeGetted: true,
      verifyCodeURL: ""
    };
  },
  mounted() {
    this.verifyCode();
  },
  methods: {
    toRegister: function() {
      this.$router.replace({ name: "registerLink" });
    },
    login() {
      if (this.usernameInput == "") {
        this.$alert("请输入用户名!", "提示", {
          confirmButtonText: "我知道了"
        });
      } else if (this.passwordInput == "") {
        this.$alert("请输入密码", "提示", {
          confirmButtonText: "我知道了"
        });
      } else {
        this.$axios
          .post(
            "http://172.18.88.23:8081/RoutePlanSystem/userSystem/session/user",
            qs.stringify({
              userId: this.usernameInput,
              password: this.passwordInput,
              code: this.verifyInput
            })
          )
          .then(res => {
            console.log(res);
            if (res.data.status == 1) {
              //把登录信息存入state
              this.$store.commit("loginState", {
                userId: this.usernameInput,
                userName: res.data.userMessage.userName
              });
              this.$router.replace({ name: "homeLink" });
            } else if (res.data.status == 2) {
              this.$alert("用户名或密码错误!", "通知", {
                confirmButtonText: "我知道了",
                type: "error"
              });
            } else if (res.data.status == 3) {
              this.$alert("验证码错误或为空！", "通知", {
                confirmButtonText: "我知道了",
                type: "error"
              });
              this.verifyCode(); //再次请求验证码
            } else {
              this.$alert("登录失败！", "通知", {
                confirmButtonText: "我知道了",
                type: "error"
              });
            }
          });
      }
    },
    verifyCode() {
      this.$axios
        .get(
          "http://172.18.88.23:8081/RoutePlanSystem/userSystem/verifyCode",
          {
            responseType: "arraybuffer"
          }
        )
        .then(res => {
          return (
            "data:image/png;base64," +
            btoa(
              new Uint8Array(res.data).reduce(
                (data, byte) => data + String.fromCharCode(byte),
                ""
              )
            )
          );
        })
        .then(data => {
          console.log(data);
          this.verifyCodeURL = data;
        });
    }
  }
};
</script>
<style scoped>
.main {
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
.loginBody {
  position: relative;
  margin: auto;
  width: 600px;
  height: 500px;
  border: 3px solid #a1bad4;
}
.topPanel {
  width: 100%;
  height: 60px;
  background: #f7f7f7;
  border-bottom: 3px solid #409eff;
}
.topPanel img {
  position: relative;
  display: inline-block;
  width: 40px;
  height: 40px;
  left: 40px;
  top: 10px;
}
.topPanel span {
  position: relative;
  display: inline-block;
  letter-spacing: 0.5em;
  left: 150px;
  font-size: 30px;
  line-height: 60px;
  color: #409eff;
}
.userSection {
  width: 600px;
  height: 400px;
}
.baiduTech {
  position: relative;
  vertical-align: top;
  height: 400px;
  width: 200px;
  display: inline-block;
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
.baiduTech span {
  position: relative;
  display: inline-block;
  width: 200px;
  text-align: center;
  font-size: 14px;
  top: 150px;
  color: gray;
}
.loginSection {
  position: relative;
  display: inline-block;
  vertical-align: top;
  left: 25px;
  top: 100px;
  width: 350px;
  height: 300px;
}
.row {
  padding-bottom: 30px;
}
.bottomPanel {
  position: relative;
  width: 600px;
  height: 40px;
  background: #383838;
  text-align: center;
  font-size: 16px;
  color: white;
  line-height: 40px;
}
.verifyCode {
  cursor: pointer;
  height: 40px;
}
</style>
