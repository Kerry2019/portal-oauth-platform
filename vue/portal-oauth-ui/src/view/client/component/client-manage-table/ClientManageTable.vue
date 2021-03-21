<template>
  <v-card hover class="card">
    <!-- title 表格标题 -->
    <v-card-title>
      <v-btn class="mx-2" fab dark color="indigo" small @click="onAdd">
        <v-icon dark>mdi-plus</v-icon>
      </v-btn>
      <div class="flex-grow-1"></div>
      <!-- Clients
      <div class="flex-grow-1"></div>-->
      <v-text-field
        v-model="searchValue"
        v-on:keyup.13="queryTableData"
        color="grey"
        label="Search"
        single-line
        hide-details
        clearable
      ></v-text-field>
      <v-btn icon @click="queryTableData">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
    </v-card-title>
    <!-- table 表格 -->
    <v-data-table
      :headers="headers"
      :items="tableData"
      :loading="loading"
      :server-items-length="pageAmount"
      loading-text="加载中... 请稍等"
      class="elevation-1"
      :options.sync="options"
    >
      <template v-slot:item.action="{ item }">
        <v-icon small class="mr-2" @click="onView(item)">mdi-view-list</v-icon>
        <v-icon small class="mr-2" @click="onEdit(item)">mdi-pencil</v-icon>
        <v-icon small class="mr-2" @click="onDelete(item)">mdi-delete</v-icon>
      </template>
      <template v-slot:no-data>
        <p class="text-center">暂无数据</p>
      </template>
    </v-data-table>
    <!-- dialog 编辑框 -->
    <client-manage-edit-dialog
      :type="editDialogType"
      :visible="editDialogVisible"
      :item="editDialogItem"
      :dark="editDialogDark"
      @changeVisible="changeEditVisible"
      @queryData="queryTableData"
    ></client-manage-edit-dialog>
    <!-- dialog 删除框 -->
    <client-manage-delete-dialog
      :visible="deleteDialogVisible"
      :item="deleteDialogItem"
      :dark="deleteDialogDark"
      @changeVisible="changeDeleteVisible"
      @queryData="queryTableData"
    ></client-manage-delete-dialog>
    <!-- dialog 详情框 -->
    <client-manage-view-dialog
      :visible="viewDialogVisible"
      :item="currentItem"
      :dark="viewDialogDark"
      @changeVisible="changeViewVisible"
    ></client-manage-view-dialog>
  </v-card>
</template>

<script>
import { queryClients } from "../../../../api/client.js";
import ClientManageEditDialog from "@/view/client/component/client-manage-edit-dialog/ClientManageEditDialog";
import ClientManageDeleteDialog from "@/view/client/component/client-manage-delete-dialog/ClientManageDeleteDialog";
import ClientManageViewDialog from "@/view/client/component/client-manage-view-dialog/ClientManageViewDialog";
import GLOBAL from "@/api/util/global_variable";
export default {
  name: "ClientManageTable",
  components: {
    ClientManageEditDialog,
    ClientManageDeleteDialog,
    ClientManageViewDialog,
  },
  props: {},
  data: () => ({
    options: {},
    pageAmount: 0,
    loading: true,
    searchValue: "",
    currentItem: {},
    //新增、编辑框
    editDialogVisible: false,
    editDialogType: "NEW",
    editDialogDark: false,
    editDialogItem: {},
    //删除框
    deleteDialogVisible: false,
    deleteDialogDark: false,
    deleteDialogItem: {},
    //详情框
    viewDialogVisible: false,
    viewDialogDark: false,

    //表头
    headers: [
      {
        text: "客户端编码",
        value: "clientId",
        align: "left",
      },
      {
        text: "密钥",
        value: "clientSecret",
        align: "left",
      },
      {
        text: "授权范围",
        value: "authorizedGrantTypes",
        align: "left",
      },
      {
        text: "重定向url",
        value: "webServerRedirectUri",
        align: "left",
      },
      {
        text: "AccessToken有效期",
        value: "accessTokenValidity",
        align: "left",
        width: 80,
      },
      {
        text: "RefreshToken有效期",
        value: "refreshTokenValidity",
        align: "left",
        width: 80,
      },
      {
        text: "",
        value: "action",
        width: 120,
      },
    ],
    //表数据
    tableData: [],
    //是否授权 autoapprove 值列表
  }),

  computed: {},

  watch: {
    options: {
      handler() {
        this.queryTableData();
      },
      deep: true,
    },
  },

  created() {},

  methods: {
    /**
     * table 数据查询
     */
    queryTableData() {
      this.loading = true;
      const { page, itemsPerPage } = this.options;
      const params = {
        page: page,
        pageSize: itemsPerPage,
        key: this.searchValue,
      };
      queryClients(params).then((response) => {
        //  console.log('response:'+JSON.stringify(response));
        this.tableData = response.data.data.result;
        this.pageAmount = response.data.data.count;
        this.loading = false;
      });
    },
    /**
     * 新增
     */
    onAdd() {
      this.editDialogType = "NEW";
      this.editDialogItem = { autoapprove: true };
      this.editDialogVisible = true;
      this.editDialogDark = GLOBAL.dark;
    },
    /**
     * 编辑行
     */
    onEdit(item) {
      this.editDialogType = "EDIT";
      this.editDialogItem = Object.assign({}, item);
      this.editDialogVisible = true;
      this.editDialogDark = GLOBAL.dark;
    },
    /**
     * 查看行详情
     */
    onView(item) {
      this.currentItem = item;
      this.viewDialogDark = GLOBAL.dark;
      this.viewDialogVisible = true;
    },
    /**
     * 删除行
     */
    onDelete(item) {
      this.deleteDialogDark = GLOBAL.dark;
      this.deleteDialogItem = item;
      this.deleteDialogVisible = true;
    },
    /**
     * 接收子组件ClientManageEditDialog传递的visible值，并赋值给editDialogVisible
     */
    changeEditVisible(value) {
      this.editDialogVisible = value;
    },

    changeDeleteVisible(value) {
      this.deleteDialogVisible = value;
    },

    changeViewVisible(value) {
      this.viewDialogVisible = value;
    },
  },
};
</script>
<style lang="less" scoped>
.card {
  margin-bottom: 4rem;
}
</style>
