<template>
  <div class="codepage-wrapper">
    <CodeBar
      :codeType="codeType"
      @clickRunCode="clickRunCode"
      @changeType="changeType"
      @reset="resetCode"
    />
    <CodeElement :code="code" :type="type" :dark="getTheme" />
    <div class="title" v-bind:class="{ dark: getTheme }">
      <span>输出</span>
    </div>
    <CodeContent ref="CodeContent" />
    <div class="moon-wrapper">
      <el-button
        type="primary"
        icon="el-icon-arrow-left"
        size="medium"
        circle
        @click="returnHome"
      ></el-button>
      <el-button
        type="primary"
        :icon="icon"
        size="medium"
        circle
        @click="moonMode"
      ></el-button>
    </div>
  </div>
</template>

<script>
import CodeBar from "./components/CodeBar";
import CodeElement from "./components/CodeElement";
import CodeContent from "./components/CodeContent";

import { mapGetters } from "vuex";
import { runCode, getCodeType } from "@/api/run";
import CodeTemp from "@/utils/template";

export default {
  name: "CodePage",
  computed: {
    ...mapGetters(["getCode", "getTheme"]),
  },
  components: {
    CodeBar,
    CodeElement,
    CodeContent,
  },
  data: function () {
    return {
      codeType: [],
      code: "",
      type: "",
      icon: "el-icon-moon",
    };
  },
  mounted() {
    this.getType();
  },
  methods: {
    resetCode() {
      this.code = "";
      this.type = "";
    },
    getType() {
      getCodeType().then((res) => {
        // console.log(res);
        this.codeType = res;
      });
    },
    clickRunCode() {
      this.toRunCode();
    },
    toRunCode() {
      console.log(this.getCode);
      this.$refs.CodeContent.initMsg();
      runCode({
        code: this.getCode,
        type: this.type,
      }).then((response) => {
        console.log(response);
        const { output } = response;
        this.$refs.CodeContent.pushMsg(output);
      });
    },
    changeType(newType) {
      if (localStorage.getItem(newType)) {
        this.code = localStorage.getItem(newType);
      } else {
        const tempCode = CodeTemp[newType];
        this.code = tempCode;
      }
      this.type = newType;
    },
    returnHome() {
      this.$router.push("/");
    },
    moonMode() {
      if (!this.getTheme) {
        this.$store.commit("changeDark");
        this.icon = "el-icon-sunny";
      } else {
        this.$store.commit("changeLight");
        this.icon = "el-icon-moon";
      }
    },
  },
};
</script>

<style scoped>
.codepage-wrapper {
  height: 100vh;
}
.dark {
  background: #0f192a !important;
  color: #fff;
}
.title {
  height: 40px;
  background-color: #f7f7f7;
  border-bottom: 1px solid #ccc;
  border-top: 1px solid #ccc;
  padding-left: 5px;
  padding-top: 5px;
}
.moon-wrapper {
  position: absolute;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  align-items: flex-end;
  bottom: 30px;
  right: 20px;
}
.moon-wrapper > button {
  margin-bottom: 5px;
}
</style>