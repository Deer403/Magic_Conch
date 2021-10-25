<template>
  <div class="bar-wrapper">
    <el-row
      type="flex"
      justify="space-between"
      align="middle"
      class="bar-container"
      v-bind:class="{ dark: getTheme }"
    >
      <el-col :xs="10" :md="3">
        <el-select
          v-model="type"
          placeholder="请选择"
          size="small"
          class="lang-select"
          @change="changeType"
        >
          <el-option
            v-for="item in codeType"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-col>
      <el-col :xs="12" :md="4" class="bar-btns">
        <el-button type="warning" size="small" @click="reset" plain
          >重置</el-button
        >
        <el-button type="primary" size="small" @click="runCode" plain
          >运行</el-button
        >
        <el-button type="success" size="small" @click="uploadCode" plain
          >提交</el-button
        >
      </el-col>
    </el-row>
  </div>
</template>

<script>
import "element-ui/lib/theme-chalk/display.css";

import { mapGetters } from "vuex";

export default {
  name: "CodeBar",
  computed: { ...mapGetters(["getCode", "getStatus", "getTheme"]) },
  props: {
    codeType: Array,
  },
  mounted() {
    this.value = this.codeType[0];
  },
  data: () => {
    return {
      // options: ["JavaScript", "Java", "Go", "C++", "Python"],
      type: "",
      lastType: "",
    };
  },
  methods: {
    reset() {
      this.$store.commit("reset");
      localStorage.clear();
      console.log(this.getCode, this.getStatus);
      this.type = "";
      this.lastType = "";
      this.$emit("reset");
    },
    runCode() {
      if (this.getCode && this.type) {
        this.$emit("clickRunCode");
      } else {
        this.errorMsg("运行失败，请输入代码并选择好语言类型");
      }
    },
    changeType(newType) {
      if (this.getStatus) {
        localStorage.setItem(this.lastType, this.getCode);
      }
      this.$emit("changeType", newType);
      this.lastType = newType;
    },
    uploadCode() {
      if (this.getCode && this.type) {
        this.successMsg("上传成功！");
      } else {
        this.errorMsg("上传失败，请输入代码并选择好语言类型");
      }
    },
    successMsg(msg) {
      this.$message({
        showClose: true,
        message: msg,
        type: "success",
      });
    },
    errorMsg(msg) {
      this.$message({
        showClose: true,
        message: msg,
        type: "warning",
      });
    },
  },
};
</script>

<style lang="less" scoped>
.dark {
  background: #0f192a;
}
.bar-wrapper {
  height: 40px;
  background-color: #f7f7f7;
  border-bottom: 1px solid #ccc;
  // box-sizing: border-box;
  // flex-direction: row;
}

.bar-container {
  height: 40px;
  flex-wrap: wrap;
  flex-direction: row;
  padding-left: 10px;
  padding-right: 10px;
}

.lang-select {
  width: 100%;
  // margin-left: 10px;
}

.bar-btns {
  display: flex;
  justify-content: right;
}
</style>