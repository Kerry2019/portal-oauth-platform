<template>
  <v-row justify="center">
    <v-dialog v-model="visible" max-width="290">
      <v-card :dark="dark">
        <v-card-title class="headline">删除</v-card-title>
        <v-card-text>确认要删除该账号吗？</v-card-text>
        <v-card-actions>
          <div class="flex-grow-1"></div>
          <v-btn color="grey" text @click="onCancel">取消</v-btn>
          <v-btn color="primary" text @click="onConfirm">确认</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { deleteUser } from "../../../../api/user.js";
export default {
  name: "UserManageDeleteDialog",
  components: {},
  props: {
    visible: Boolean,
    dark: Boolean,
    item: Object,
  },
  data() {
    return {};
  },
  watch: {},
  computed: {},
  methods: {
    //取消
    onCancel() {
      this.$emit("changeVisible", false);
    },
    //确认
    onConfirm() {
      deleteUser(this.item).then((response) => {
        this.$emit("queryData");
      });
      this.$emit("changeVisible", false);
    },
  },
  created() {},
  mounted() {},
};
</script>
<style></style>
