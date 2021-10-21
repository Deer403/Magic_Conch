<template>
  <div class="bar-wrapper">
    <div class="left-box">
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
        <i slot="prefix" class="el-input__icon el-icon-search" :class="{iHover:isHover}"></i>
      </el-input>
    </div>
  </div>
</template>

<script>
export default {
  name: "MainBar",
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
  background: #122445;
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
  font-family: "logofont";
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
}

@keyframes search {
  from {
    width: 300px;
  }
  to {
    width: 100%;
  }
}
</style>