import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    code: null,
    codingStatus: false,
    isDark: false,
  },
  getters: {
    getCode: (state) => state.code,
    getStatus: (state) => state.codingStatus,
    getTheme: (state) => state.isDark,
  },
  mutations: {
    changeCode(state, newCode) {
      state.code = newCode;
      state.codingStatus = true;
    },
    reset(state) {
      state.code = "";
      state.codingStatus = false;
    },
    changeDark(state){
      state.isDark = true
    },
    changeLight(state){
      state.isDark = false
    }
  },
  actions: {},
  modules: {},
});
