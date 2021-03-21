<template>
  <v-bottom-navigation
    scroll-target="#scroll-area-1"
    hide-on-scroll
    fixed
    horizontal
  >
    <v-btn
      text
      color="primary"
      v-for="(item, i) in items"
      :key="i"
      @click="onRouter(item)"
    >
      <span v-text="item.title"></span>
      <v-icon v-text="item.icon"></v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      items: [
        {
          title: "客户端",
          icon: "mdi-table-edit",
          router: "client",
        },
        {
          title: "登录页",
          icon: "mdi-web",
          router: "loginPage",
        },
        {
          title: "账号",
          icon: "mdi-account-multiple-outline",
          router: "user",
        },
      ],
    };
  },
  watch: {},
  computed: {},
  methods: {
    onRouter(item) {
      this.$router.push({ name: item.router });
    },
  },
  created() {},
  mounted() {},
};
</script>
<style></style>
