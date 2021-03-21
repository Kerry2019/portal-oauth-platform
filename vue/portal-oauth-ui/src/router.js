import Vue from "vue";
import Router from "vue-router";
import ClientManage from "@/view/client/page/ClientManage";
import LoginPageManage from "@/view/login-page/page/LoginPageManage";
import UserManage from "@/view/user/page/UserManage";
import Home from "@/view/home/page/Home";

Vue.use(Router);

const router = new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
      redirect: { name: "client" },
      children: [
        {
          path: "/client",
          name: "client",
          component: ClientManage,
        },
        {
          path: "/loginPage",
          name: "loginPage",
          component: LoginPageManage,
        },
        {
          path: "/user",
          name: "user",
          component: UserManage,
        },
      ],
    },
  ],
});

export default router;
