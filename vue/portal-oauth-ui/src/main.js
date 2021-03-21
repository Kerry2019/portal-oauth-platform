import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "@/assets/style/vuetify";
// import '../mock';
import "./api/util/global_variable";
import VueClipboard from "vue-clipboard2";
import axios from "axios";

//粘贴板
Vue.use(VueClipboard);
//axios
axios.defaults.baseURL = process.env.VUE_APP_API;

Vue.config.productionTip = false;

new Vue({
  router,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
