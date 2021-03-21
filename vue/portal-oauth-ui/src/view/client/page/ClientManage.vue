<template>
  <div>
    <client-manage-table></client-manage-table>
  </div>
</template>

<script>
import ClientManageTable from "@/view/client/component/client-manage-table/ClientManageTable";

export default {
  name: "ClientManage",
  components: {
    ClientManageTable,
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
