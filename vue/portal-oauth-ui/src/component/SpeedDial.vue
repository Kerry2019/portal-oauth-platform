<template>
  <v-speed-dial
    v-model="fab"
    bottom
    right
    fixed
    transition="slide-y-reverse-transition"
    class="dial"
  >
    <template v-slot:activator>
      <v-btn v-model="fab" color="indigo" dark fab small>
        <v-icon v-if="fab">mdi-close</v-icon>
        <v-icon v-else>mdi-format-list-bulleted-square</v-icon>
      </v-btn>
    </template>
    <!-- <v-btn fab dark small color="red" @click="onFullScreen">
      <v-icon>mdi-location-exit</v-icon>
    </v-btn> -->
    <div class="d-none d-sm-flex">
      <v-btn fab light small color="white" @click="onFullScreen">
        <v-icon>mdi-fullscreen</v-icon>
      </v-btn>
    </div>
    <v-btn v-if="dark" fab light small color="white" @click="changeDark(false)">
      <v-icon color="orange accent-4">mdi-weather-sunny</v-icon>
    </v-btn>
    <v-btn v-if="!dark" fab dark small color="indigo" @click="changeDark(true)">
      <v-icon>mdi-weather-night</v-icon>
    </v-btn>
  </v-speed-dial>
</template>

<script>
import GLOBAL from "@/api/util/global_variable";
import screenfull from "screenfull";
export default {
  name: "SpeedDial",
  components: {},
  props: {},
  data() {
    return {
      fab: false,
      dark: false,
    };
  },
  watch: {},
  computed: {},
  methods: {
    //设置暗黑主题
    changeDark(val) {
      this.dark = val;
      this.$emit("themeDark", val);
      //全局变量
      GLOBAL.dark = val;
    },

    //全屏 npm install screenfull --save
    onFullScreen() {
      screenfull.toggle();
    },
  },
  created() {},
  mounted() {},
};
</script>
<style>
.dial {
  padding-bottom: 5%;
}
</style>
