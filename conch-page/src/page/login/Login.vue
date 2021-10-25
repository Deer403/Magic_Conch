<template>
  <div class="login-wrapper">
    <div class="login-top-bar">
      <div class="logo-wrapper">
        <img class="logo-img" src="@/assets/logo/logo-32.png" />
        <span class="">在线编程沙盒</span>
      </div>
    </div>
    <div class="login-main">
      <!-- <div class="login-title">登录</div> -->
      <div class="login-card">
        <el-row class="login-row">
          <el-col class="info-box hidden-md-and-down" :md="12">
            <div class="info-wrapper">
              <span class="info-t1">一个快速开发的编程沙盒</span>
              <div class="info-list">
                <div class="info-item">
                  <div class="info-item-top">
                    <img src="@/assets/icon/gou.png" />
                    <span>独立环境</span>
                  </div>
                  <div class="info-item-bottom">
                    每个项目的运行之间都是独立的。
                  </div>
                </div>
                <div class="info-item">
                  <div class="info-item-top">
                    <img src="@/assets/icon/gou.png" />
                    <span>快速启动</span>
                  </div>
                  <div class="info-item-bottom">不用配置环境，开始创作！</div>
                </div>
                <div class="info-item">
                  <div class="info-item-top">
                    <img src="@/assets/icon/gou.png" />
                    <span>到处运行</span>
                  </div>
                  <div class="info-item-bottom">
                    保存在云端，在哪都可以创作！
                  </div>
                </div>
              </div>
            </div>
          </el-col>
          <el-col class="login-box" :sm="24" :md="12">
            <div class="login-box-wrapper">
              <span class="login-text">登录</span>
              <span class="login-text-info"
                >不用注册，从你熟悉的网站登录吧！</span
              >
              <div class="login-btns-box" @click="toLogin">
                <div class="btn-box">
                  <img src="@/assets/icon/github.png" />
                  <span class="btn-box-text">使用Github登录</span>
                </div>
              </div>
              <div class="login-btns-box" @click="toLogin">
                <div class="btn-box">
                  <img src="@/assets/icon/wechat.png" />
                  <span class="btn-box-text">使用Wechat登录</span>
                </div>
              </div>
              <div class="login-btns-box" @click="toLogin">
                <div class="btn-box">
                  <img src="@/assets/icon/qq.png" />
                  <span class="btn-box-text">使用QQ登录</span>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import { Loading } from "element-ui";
export default {
  name: "LoginPage",
  methods: {
    toLogin() {
      let loadingInstance1 = Loading.service({ fullscreen: true,background:"rgba(13, 10, 49,0.3)" });
      setTimeout(() => {
        loadingInstance1.close();
        this.$store.commit("login");
        this.$router.push("/");
      }, 1000);
    },
  },
};
</script>

<style scoped>
.login-wrapper {
  width: 100vw;
  height: 100vh;
  background: linear-gradient(to right bottom, #0d1d3a 0, #1a2f55 52%, #365a83);
  /* overflow-x: hidden; */
  overflow-y: scroll;
}

.login-top-bar {
  height: 84px;
  background: #122445;
  display: flex;
  align-items: center;
}

.logo-wrapper {
  display: flex;
  width: 300px;
  padding-left: 30px;
  align-items: center;
  font-size: 24px;
  color: #fff;
  /* font-family: "logofont"; */
  font-family: "Microsoft YaHei";
}

.logo-img {
  margin-top: 5px;
  margin-right: 5px;
  width: 28px;
}

.login-main {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 65%;
  height: 100%;
  margin: 0 auto;
  padding-top: 50px;
  color: #fff;
}

.login-title {
  /* display: flex; */
  /* justify-content: center; */
  font-size: 40px;
}

.login-card {
  display: flex;
  width: 660px;
  height: 400px;
  margin-top: 100px;
  background: #0d1d3a;
  border: 1px solid #26ffca;
  box-sizing: border-box;
  box-shadow: rgb(0 0 0 / 12%) 0px 4px 4px, rgb(0 0 0 / 24%) 0px 16px 32px;
  border-radius: 8px;
}

.login-row {
  height: 100%;
  width: 100%;
}

.info-box {
  display: flex;
  height: 100%;
  padding-top: 30px;
  flex-direction: column;
  /* align-items: center; */
  background: linear-gradient(to right bottom, #0d1d3a 0, #1a2f55 52%, #365a83);
}

.info-wrapper {
  margin: 0 auto;
}

.info-t1 {
  font-size: 19px;
  font-weight: 700;
}

.info-list {
  margin-top: 50px;
}

.info-item {
  /* width: 215px; */
  /* display: flex; */
  margin-bottom: 40px;
}

.info-item-top {
  display: flex;
  align-items: center;
}

.info-item-top > img {
  width: 18px;
  height: 18px;
}

.info-item-top > span {
  margin-left: 5px;
  font-size: 16px;
}

.info-item-bottom {
  margin-top: 10px;
  font-size: 14px;
}

.login-box {
  display: flex;
  background: #122445;
  height: 100%;
}

.login-box-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100%;
  padding-top: 30px;
  margin: 0 auto;
}

.login-text {
  font-size: 18px;
  font-weight: 700;
}

.login-text-info {
  margin-top: 8px;
  font-size: 14px;
  color: #fff;
}

.login-btns-box {
  margin-top: 30px;
}

.btn-box:hover {
  width: 250px;
  animation: toBig 0.56s ease;
}

.btn-box {
  display: flex;
  align-items: center;
  height: 35px;
  width: 220px;
  background: #fff;
  border-radius: 4px;
  /* box-shadow: rgb(0 0 0 / 24%) 5px 2px 8px; */
  box-shadow: rgb(0 0 0 / 50%) 5px 10px 5px 2px;
  cursor: pointer;
  box-sizing: border-box;
  color: black;
  padding-left: 20px;
  margin-bottom: 20px;
}

.btn-box-text {
  flex: 1;
  display: flex;
  justify-content: center;
  font-size: 14px;
  letter-spacing: 2px;
}

@keyframes toBig {
  from {
    width: 220px;
  }
  to {
    width: 250px;
  }
}

@media only screen and (max-width: 768px) {
  .login-card {
    width: 330px;
  }
}
</style>