<template>
  <v-row>
    <v-col :cols="cols" v-for="card in cardList" :key="card.img">
      <v-card hover>
        <v-img class="white--text" :src="card.img">
          <!-- <v-card-title  class=" fill-height grey--text">Top 10 Australian beaches</v-card-title> -->
        </v-img>

        <v-card-text>
          <!-- <p class="text-left text--primary">{{card.title}}</p> -->
          <!-- <p class="text-left font-italic">{{card.content}}</p> -->
        </v-card-text>

        <v-card-actions>
          <v-switch
            v-model="use"
            :value="card.use"
            label="选用"
            color="indigo"
            hide-details
          ></v-switch>
          <div class="flex-grow-1"></div>
          <v-icon v-if="use == card.use" color="green"
            >mdi-check-decagram</v-icon
          >
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import { updateLoginPage, getConfig } from "../../../../api/config.js";
export default {
  name: "LoginPageCard",
  components: {},
  props: {},
  data() {
    return {
      use: null,
      cols: 4,
      cardList: [
        {
          img: require("@/assets/img/loginTmpBlack.png"),
          use: "loginBlack",
          title: "门户登录页",
          content: "黑色",
        },
        {
          img: require("@/assets/img/loginTmpBlue.png"),
          use: "loginBlue",
          title: "门户登录页",
          content: "蓝色",
        },
        {
          img: require("@/assets/img/loginTmpGreen.png"),
          use: "loginGreen",
          title: "门户登录页",
          content: "绿色",
        },
        {
          img: require("@/assets/img/loginTmpRed.png"),
          use: "loginRed",
          title: "门户登录页",
          content: "红色",
        },
      ],
    };
  },
  watch: {
    use(val) {
      //  console.log(val)
      //  if(val==null){
      //    this.use='loginBlack';
      //    return ;
      //  }
      if (val != null) {
        const params = {
          loginPage: this.use,
        };
        updateLoginPage(params).then((response) => {});
      }
    },
  },
  computed: {},
  methods: {
    initLoginPage() {
      getConfig().then((response) => {
        this.use = response.data.data.loginPage;
      });
    },
  },
  created() {},
  mounted() {
    if (this.$vuetify.breakpoint.name == "xs") {
      this.cols = 12;
    }
    this.initLoginPage();
  },
};
</script>
<style></style>
