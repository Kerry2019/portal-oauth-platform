// src/plugins/vuetify.js

import Vue from "vue";
import Vuetify from "vuetify";
import "vuetify/dist/vuetify.min.css";
import colors from "vuetify/lib/util/colors";
import "@mdi/font/css/materialdesignicons.css"; // Ensure you are using css-loader

Vue.use(Vuetify);

export default new Vuetify({
  icons: {
    iconfont: "mdi", // default - only for display purposes
  },
  theme: {
    themes: {
      light: {
        primary: colors.blue.darken1,
        secondary: colors.grey.darken1,
        accent: colors.shades.black,
        error: colors.red.accent3,
        checkList: colors.lightGreen.base,
      },
      dark: {
        primary: colors.lightGreen.base,
      },
    },
  },
});
