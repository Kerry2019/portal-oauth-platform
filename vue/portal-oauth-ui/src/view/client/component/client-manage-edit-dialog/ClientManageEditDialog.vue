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
                  v-if="type == 'NEW'"
                  required
                  v-model="item.clientId"
                  :rules="[() => !!item.clientId || '必填']"
                  label="客户端编号"
                ></v-text-field>
                <v-text-field
                  v-if="type != 'NEW'"
                  disabled
                  v-model="item.clientId"
                  label="客户端编号"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="6">
                <v-text-field
                  required
                  v-model="item.clientSecret"
                  label="密钥"
                  :rules="[() => !!item.clientId || '必填']"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="6">
                <v-text-field
                  required
                  v-model="item.webServerRedirectUri"
                  label="重定向url"
                  hide-details
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="3">
                <v-text-field
                  required
                  type="number"
                  v-model="item.accessTokenValidity"
                  label="AccessToken有效期"
                  hide-details
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="3">
                <v-text-field
                  required
                  type="number"
                  v-model="item.refreshTokenValidity"
                  label="RefreshToken有效期"
                  hide-details
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="12" md="12">
                <v-select
                  required
                  v-model="item.authorizedGrantTypesList"
                  :items="authorizedGrantTypesItems"
                  attach
                  chips
                  label="授权范围"
                  multiple
                  hide-details
                ></v-select>
              </v-col>
              <v-col cols="12" sm="12" md="12">
                <v-textarea
                  required
                  v-model="item.clientDescription"
                  label="系统描述"
                  rows="1"
                  auto-grow
                  hide-details
                ></v-textarea>
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
import { addClient, updateClient } from "../../../../api/client.js";
export default {
  name: "ClientManageEditDialog",
  components: {},
  props: {
    type: String,
    visible: Boolean,
    item: {
      clientId: "",
      clientSecret: "",
      authorizedGrantTypesList: [
        "password",
        "refresh_token",
        "authorization_code",
        "implicit",
      ],
      webServerRedirectUri: "",
      accessTokenValidity: 0,
      refreshTokenValidity: 0,
    },
    dark: Boolean,
  },
  data() {
    return {
      //是否授权 autoapprove 值列表
      // autoapproveItems: [
      //   {
      //     text: "是",
      //     value: "true"
      //   },
      //   {
      //     text: "否",
      //     value: "false"
      //   }
      // ],
      //授权类型 authorizedGrantTypes 值列表
      authorizedGrantTypesItems: [
        "password",
        "refresh_token",
        "authorization_code",
        "implicit",
      ],
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
          addClient(this.item).then((response) => {
            this.$refs.form.reset();
            this.$emit("queryData");
          });
        }
        //EDIT 编辑
        else {
          updateClient(this.item).then((response) => {
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
