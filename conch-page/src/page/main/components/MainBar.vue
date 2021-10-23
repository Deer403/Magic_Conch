<template>
  <div class="bar-wrapper">
    <div class="left-box hidden-sm-and-down">
      <img class="logo-img" src="@/assets/logo/logo-32.png" />
      <span class="">在线编程沙盒</span>
    </div>
    <div class="right-box" v-bind:class="{ hover: isHover }">
      <el-input
        ref="searchRef"
        v-model="searchValue"
        placeholder=""
        @focus="searchFocus"
        @blur="searchBlur"
      >
        <i
          slot="prefix"
          class="el-input__icon el-icon-search"
          :class="{ iHover: isHover }"
        ></i>
      </el-input>
      <div class="login-btn" @click="toLoginPage" v-if="!getLogin">登录</div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "MainBar",
  computed: {
    ...mapGetters(["getLogin"]),
  },
  data: function () {
    return {
      isHover: false,
      searchValue: "",
    };
  },
  methods: {
    searchFocus() {
      this.isHover = true;
    },
    searchBlur() {
      this.isHover = false;
      // this.$refs.searchRef.clear();
    },
    toLoginPage() {
      this.$router.push("/login");
    },
  },
};
</script>

<style lang="css" scoped>
.right-box >>> .el-input__inner {
  background-color: #102243;
  border-color: rgba(174, 221, 255, 0.6);
  color: #fff;
  outline: 0;
  /* font-family: "logofont"; */
}

.hover >>> .el-input__inner {
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
  box-shadow: 0 0 0 2px rgb(60 167 220 / 76%);
}

/* .right-box >>> .el-input,
.el-input--prefix {
  user-select: none;
} */

.bar-wrapper {
  height: 84px;
  /* background: #122445; */
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.left-box {
  /* user-select: none; */
  display: flex;
  width: 300px;
  padding-left: 30px;
  align-items: center;
  font-size: 24px;
  color: #fff;
  /* font-family: "logofont"; */
  font-family: "Microsoft YaHei";
}

.hover {
  width: 100%;
  animation: search 0.56s ease;
}

.iHover {
  color: #3ca7dc;
}

.logo-img {
  margin-top: 5px;
  margin-right: 5px;
  width: 28px;
}

.right-box {
  padding-right: 30px;
  display: flex;
  align-items: center;
}

@keyframes search {
  from {
    width: 300px;
  }
  to {
    width: 100%;
  }
}

@media only screen and (max-width: 991px) {
  .bar-wrapper {
    justify-content: center;
  }
}

.login-btn {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 20px;
  height: 40px;
  max-width: 85px;
  min-width: 85px;
  background: rgb(13, 29, 58);
  color: rgba(174, 221, 255, 0.8);
  font-weight: 600;
  border-radius: 4px;
  cursor: pointer;
}
</style>