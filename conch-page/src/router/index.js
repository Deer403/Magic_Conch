import Vue from "vue";
import VueRouter from "vue-router";
import CodePage from "@/page/codepage/CodePage";
import Main from "@/page/main/Main";
import Login from "@/page/login/Login";


Vue.use(VueRouter);

const routes = [
  {
    path: "/code",
    name: "CodePage",
    component: CodePage,
    meta: {
      // 页面标题title
      title: "编程盒子",
    },
  },
  {
    path: "/",
    name: "MainPage",
    component: Main,
    meta: {
      // 页面标题title
      title: "编程盒子",
    },
  },
  {
    path: "/login",
    name: "LoginPage",
    component: Login,
    meta: {
      // 页面标题title
      title: "登录页面",
    },
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})


export default router;
