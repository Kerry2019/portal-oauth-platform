<template>
  <v-card style="min-height: 100%" :dark="themeDark">
    <div class="d-none d-sm-flex pc-table-div">
      <left-drawer></left-drawer>
      <router-view />
    </div>
    <div class="d-flex d-sm-none mobile-table-div">
      <router-view />
      <bottom-drawer></bottom-drawer>
    </div>
    <speed-dial @themeDark="changeDark"></speed-dial>
  </v-card>
</template>

<script>
import LeftDrawer from "@/component/LeftDrawer";
import BottomDrawer from "@/component/BottomDrawer";
import SpeedDial from "@/component/SpeedDial";
import Global from "@/api/util/global_variable";
export default {
  name: "home",
  components: {
    LeftDrawer,
    BottomDrawer,
    SpeedDial,
  },
  props: {},
  data() {
    return {
      themeDark: Global.dark,
    };
  },
  watch: {},
  computed: {},
  methods: {
    changeDark(value) {
      Global.dark = value;
      this.themeDark = value;
    },
  },
  created() {},
  mounted() {},
};
</script>
<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.pc-table-div {
  margin: 4rem 20px 0 150px;
}

.mobile-table-div {
  margin-top: 1rem;
}
</style>
