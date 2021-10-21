<template>
  <div class="content-wrapper">
    <div v-for="(msg, index) in msgs" :key="index" class="msg-item">
      <span>console>></span>
      <span>{{ msg }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "CodeContent",
  data: () => {
    return {
      msgs: [],
    };
  },
  methods: {
    pushMsg(msg) {
      this.msgs.push(msg);
    },
    resetMsg() {
      this.msgs = [];
    },
    initMsg() {
      this.msgs = ["正在连接服务器", "已连接上服务器..."];
    },
  },
};
</script>

<style scoped>
.content-wrapper {
  padding-top: 10px;
  padding-left: 20px;
  font-family: Monospace !important;
  font-size: 16px;
}

.msg-item {
  margin: 5px 0px;
}
</style>