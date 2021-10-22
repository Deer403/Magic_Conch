<template>
  <div>
    <CodeBar
      :codeType="codeType"
      @clickRunCode="clickRunCode"
      @changeType="changeType"
      @reset="resetCode"
    />
    <CodeElement :code="code" :type="type" />
    <div class="title">
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
        icon="el-icon-moon"
        size="medium"
        circle
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
    ...mapGetters(["getCode"]),
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
    returnHome(){
      this.$router.push("/")
    }
  },
};
</script>

<style scoped>
.title {
  height: 40px;
  background-color: #f7f7f7;
  border-bottom: 1px solid #ccc;
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
.moon-wrapper>button{
  margin-bottom: 5px;
}
</style>