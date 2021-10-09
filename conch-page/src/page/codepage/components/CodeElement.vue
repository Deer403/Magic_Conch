<template>
  <div class="ce-wrapper">
    <CodeEditor />
  </div>
</template>

<script>
import CodeEditor from "common/CodeEditor";

export default {
  name: "CodeElement",
  components: {
    CodeEditor,
  },
};
</script>

<style lang="less">
.ce-wrapper {
  height: 600px;
}
</style>