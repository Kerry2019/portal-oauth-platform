<template>
  <!-- <v-card style="height: -webkit-fill-available;"> -->
  <v-navigation-drawer expand-on-hover permanent fixed>
    <template v-slot:prepend>
      <v-list>
        <v-list-item>
          <v-list-item-avatar>
            <v-img :src="require('@/assets/img/qq.jpg')"></v-img>
          </v-list-item-avatar>
        </v-list-item>

        <v-list-item link two-line>
          <v-list-item-content>
            <v-list-item-title class="title">Kerry Wu</v-list-item-title>
            <v-list-item-subtitle>kerry.wu@definesys.com</v-list-item-subtitle>
          </v-list-item-content>
          <v-list-item-action>
            <v-icon>mdi-menu-down</v-icon>
          </v-list-item-action>
        </v-list-item>
      </v-list>
    </template>

    <v-divider></v-divider>

    <v-list nav dense>
      <v-list-item-group v-model="item" color="primary">
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          @click="onRouter(item)"
        >
          <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon>
          <v-list-item-title v-text="item.title"></v-list-item-title>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-navigation-drawer>
  <!-- </v-card> -->
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      item: 0,
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
