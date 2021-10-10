<template>
  <div class="bar-wrapper">
    <el-row
      type="flex"
      justify="space-between"
      align="middle"
      class="bar-container"
    >
      <el-col :xs="10" :md="3">
        <el-select
          v-model="value"
          placeholder="请选择"
          size="small"
          class="lang-select"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.value"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-col>
      <el-col :xs="12" :md="4" class="bar-btns">
        <el-button type="warning" size="small" plain>重置</el-button>
        <el-button type="primary" size="small" plain>运行</el-button>
        <el-button type="success" size="small" plain>提交</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import "element-ui/lib/theme-chalk/display.css";
export default {
  name: "CodeBar",
  data: () => {
    return {
      options: [
        {
          value: "JavaScript",
        },
        {
          value: "Java",
        },
        {
          value: "Go",
        },
        {
          value: "C++",
        },
        {
          value: "Python",
        },
      ],
      value: "JavaScript",
    };
  },
};
</script>

<style lang="less" scoped>
.bar-wrapper {
  height: 40px;
  background-color: #f7f7f7;
  border-bottom: 1px solid #ccc;
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