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
import "@/utils/cm-setting.js"

import { codemirror } from "vue-codemirror";
import { TypeMap } from "@/utils/template";

export default {
  name: "CodeEditor",
  props: {
    code: String,
    type: String,
  },
  watch: {
    type: function (val) {
      this.$set(this.cmOptions,"mode",TypeMap[val])
    },
  },
  components: {
    codemirror,
  },
  data: () => {
    return {
      cmOptions: {
        tabSize: 4,
        mode: "",
        theme: "idea",
        lineNumbers: true,
        line: true,
        matchBrackets: true,
        smartIndent: true,
        styleActiveLine: true,
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