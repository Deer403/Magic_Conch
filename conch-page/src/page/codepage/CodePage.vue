<template>
  <div>
    <CodeBar />
    <CodeElement />
    <div class="title">
      <span>输出</span>
    </div>
    <CodeContent />
  </div>
</template>

<script>
import CodeBar from "./components/Bar";
import CodeElement from "./components/CodeElement";
import CodeContent from "./components/CodeContent";

export default {
  components: {
    CodeBar,
    CodeElement,
    CodeContent,
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
</style>