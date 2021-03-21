<template>
  <div>
    <user-manage-table></user-manage-table>
  </div>
</template>

<script>
import UserManageTable from "@/view/user/component/user-manage-table/UserManageTable";

export default {
  name: "UserManage",
  components: {
    UserManageTable,
  },
  props: {},
  data() {
    return {
      headData: {
        drawer: false,
      },
    };
  },
  watch: {},
  computed: {},
  methods: {},
  created() {},
  mounted() {},
};
</script>
<style></style>
