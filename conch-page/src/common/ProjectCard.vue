<template>
  <div class="card-wrapper" @click="cardClick">
    <div class="card-info-wrapper">
      <div class="card-info" v-if="info || hot">
        <span>{{getInfo()}}</span>
      </div>
    </div>
    <div class="card-content">
      <div class="card-logo"><img :src="imgSrc" /></div>
      <div class="card-text-box">
        <span class="text-box-name">{{ cardName }}</span>
        <span class="text-box-info">{{ cardInfo }}</span>
      </div>
    </div>
    <div class="card-none"></div>
  </div>
</template>

<script>
export default {
  name: "ProjectCard",
  props: {
    info: String,
    cardName: String,
    cardInfo: String,
    imgSrc: String,
    hot: String,
  },
  methods: {
    cardClick() {
      this.$emit("click");
    },
    getInfo(){
      if(this.info){
        return this.info
      }else if(this.hot){
        return "热门"
      }
    }
  },
};
</script>

<style scoped>
.card-wrapper {
  display: flex;
  flex-direction: column;
  width: 230px;
  height: 85px;
  border-radius: 2px;
  box-shadow: 0 0 0 2px rgb(174 221 255 / 10%);
  margin-right: 20px;
  cursor: pointer;
}

.card-info-wrapper {
  min-height: 16px;
}

.card-info {
  display: flex;
  margin-left: auto;
  align-items: center;
  justify-content: center;
  width: 20%;
  height: 16px;
  padding: 0 6px;
  border-radius: 1px;
  color: #0d1d3a;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 1px;
  text-transform: uppercase;
  background: #26ffca;
}

.card-content {
  display: flex;
  align-items: center;
  /* height: 60px; */
  padding: 5px 22px;
  margin-top: 5px;
  /* margin-bottom: 20px; */
}

.card-text-box {
  margin-left: 15px;
}

.card-logo {
  width: 32px;
  height: 32px;
}

.card-logo > img {
  width: 32px;
  height: 32px;
}

.card-text-box {
  display: flex;
  flex-direction: column;
}

.card-none {
  height: 100%;
}

.text-box-name {
  font-size: 14px;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.text-box-info {
  margin-top: 5px;
  color: #aeddff;
  font-size: 11px;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  opacity: 0.6;
}
</style>