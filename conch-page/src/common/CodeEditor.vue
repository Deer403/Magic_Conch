<template>
  <div class="codeeditor-wrapper">
    <codemirror
      ref="cmEditor"
      :options="cmOptions"
      :value="code"
      @input="codeChange"
    />
  </div>
</template>

<script>
//引入编辑器设置
import "@/utils/cm-setting.js";

import { codemirror } from "vue-codemirror";
import { TypeMap } from "@/utils/template";

export default {
  name: "CodeEditor",
  mounted() {},
  props: {
    code: String,
    type: String,
    dark: { type: Boolean, default: false },
  },
  watch: {
    type: function (val) {
      this.$set(this.cmOptions, "mode", TypeMap[val]);
    },
    dark: function (val) {
      if (val) {
        this.$set(this.cmOptions, "theme", "midnight");
      } else {
        this.$set(this.cmOptions, "theme", "idea");
      }
    },
  },
  components: {
    codemirror,
  },
  data: () => {
    return {
      cmOptions: {
        tabSize: 4, //tan缩进数
        mode: "", //语法高亮的类型
        theme: "idea", //主题名字
        lineNumbers: true, //显示行数
        // line: true, //显示行数
        matchBrackets: true, // 括号匹配
        smartIndent: true, // 智能缩进
        styleActiveLine: true, //设置光标所在行高亮t
        autoCloseBrackets: true, // 输入和退格时成对
        autoRefresh: true, // 自动刷新
      },
    };
  },
  methods: {
    codeChange(newCode) {
      // console.log(newCode);
      this.$store.commit("changeCode", newCode);
    },
  },
};
</script>

<style lang="css" scoped>
.codeeditor-wrapper {
  height: 600px;
}

/* .codeeditor-wrapper >>> .CodeMirror {
  height: 600px !important;
} */

.codeeditor-wrapper >>> .CodeMirror {
  height: 600px;
}
/* .codeeditor-wrapper >>> .CodeMirror-scroll{
  height: 100% !important;
}

.codeeditor-wrapper >>> .vue-codemirror{
  height: 100% !important;
} */
</style>