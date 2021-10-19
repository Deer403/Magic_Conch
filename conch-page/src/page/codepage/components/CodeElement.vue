<template>
  <div class="ce-wrapper"><CodeEditor :code="code" :type="type" /></div>
</template>

<script>
import CodeEditor from "common/CodeEditor";

export default {
  name: "CodeElement",
  props: {
    code: String,
    type: String,
  },
  components: {
    CodeEditor,
  },
  data: function () {
    return {
      // code: "",
    };
  },
};
</script>

<style lang="less" scoped>
.ce-wrapper {
  height: 600px;
}
</style>