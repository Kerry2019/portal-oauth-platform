<template>
  <v-dialog v-model="visible" max-width="850px">
    <v-card :dark="dark">
      <v-card-title>
        <span class="headline">{{ title }}</span>
      </v-card-title>

      <v-card-text>
        <v-container>
          <v-form ref="form" v-model="formValid" lazy-validation>
            <v-row>
              <v-col cols="12" sm="6" md="6">
                <v-text-field
                  v-if="type === 'NEW'"
                  required
                  v-model="item.username"
                  :rules="[() => !!item.username || '必填']"
                  label="账号"
                ></v-text-field>
                <v-text-field
                  v-if="type !== 'NEW'"
                  disabled
                  v-model="item.username"
                  label="账号"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="6">
                <v-text-field
                  required
                  v-model="item.password"
                  label="密码"
                  :rules="[() => !!item.password || '必填']"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="6">
                <v-text-field
                  required
                  v-model="item.name"
                  label="姓名"
                  hide-details
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="6">
                <v-text-field
                  required
                  v-model="item.email"
                  :rules="[() => !!item.email || '必填']"
                  label="邮箱"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-form>
        </v-container>
      </v-card-text>

      <v-card-actions>
        <div class="flex-grow-1"></div>
        <v-btn color="grey" text @click="onCancel">取消</v-btn>
        <v-btn color="primary" text @click="onConfirm">确认</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { addUser, updateUser } from "../../../../api/user.js";
export default {
  name: "UserManageEditDialog",
  components: {},
  props: {
    type: String,
    visible: Boolean,
    item: {
      username: "",
      password: "",
      name: "",
      email: "",
    },
    dark: Boolean,
  },
  data() {
    return {
      //表单 校验
      formValid: false,
    };
  },
  watch: {},
  computed: {
    title() {
      return this.type === "NEW" ? "新增" : "编辑";
    },
  },
  methods: {
    //取消
    onCancel() {
      this.$refs.form.reset();
      this.$emit("changeVisible", false);
    },
    //确认
    onConfirm() {
      if (this.$refs.form.validate()) {
        //NEW 新增
        if (this.type == "NEW") {
          addUser(this.item).then((response) => {
            this.$refs.form.reset();
            this.$emit("queryData");
          });
        }
        //EDIT 编辑
        else {
          updateUser(this.item).then((response) => {
            this.$refs.form.reset();
            this.$emit("queryData");
          });
        }
        this.$emit("changeVisible", false);
      }
    },
  },
  created() {},
  mounted() {},
};
</script>
<style></style>
