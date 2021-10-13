<template>
  <div class="codeeditor-wrapper">
    <codemirror :options="cmOptions" />
  </div>
</template>

<script>
import "codemirror/lib/codemirror.css";
import "codemirror/theme/idea.css";
import 'codemirror/mode/javascript/javascript.js';
import { codemirror } from "vue-codemirror";

export default {
  name: "CodeEditor",
  components: {
    codemirror,
  },
  data: () => {
    return {
      cmOptions: {
        tabSize: 4,
        mode: "text/javascript",
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