<template>
  <v-dialog v-model="visible" max-width="850px">
    <v-card :dark="dark">
      <v-card-title>
        <span class="font-italic">clientId：{{ item.clientId }}</span>
        <!-- <div class="flex-grow-1"></div>
        <v-btn @click="onCancel" text>CLOSE</v-btn>-->
      </v-card-title>

      <v-card-text>
        <v-container>
          <v-form>
            <v-row>
              <v-col cols="12" sm="6" md="5">
                <v-text-field
                  v-model="item.clientSecret"
                  label="密钥"
                  hide-details
                  readonly
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="6">
                <v-text-field
                  v-model="basicAuth"
                  label="Basic Authorization"
                  hide-details
                  readonly
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="12" md="12">
                <v-textarea
                  v-model="item.clientDescription"
                  label="系统描述"
                  rows="1"
                  auto-grow
                  hide-details
                  readonly
                ></v-textarea>
              </v-col>
              <v-col cols="12" sm="12" md="12">
                <v-textarea
                  v-model="redirectUri"
                  label="访问地址"
                  rows="1"
                  auto-grow
                  prefix
                  persistent-hint
                  :hint="clientOauthUri"
                  v-if="item.webServerRedirectUri == null"
                >
                  <template v-slot:append-outer>
                    <v-btn
                      icon
                      v-if="copyVisible"
                      v-clipboard:copy="clientOauthUri"
                      v-clipboard:success="onCopySuccess"
                      v-clipboard:error="onCopyError"
                    >
                      <v-icon>mdi-content-copy</v-icon>
                    </v-btn>
                    <p v-if="!copyVisible" class="subtitle-1">Copied!</p>
                  </template>
                </v-textarea>
                <v-textarea
                  v-model="item.webServerRedirectUri"
                  label="访问地址"
                  rows="1"
                  auto-grow
                  prefix
                  readonly
                  persistent-hint
                  :hint="clientOauthUri"
                  v-if="item.webServerRedirectUri != null"
                >
                  <template v-slot:append-outer>
                    <v-btn
                      icon
                      v-if="copyVisible"
                      v-clipboard:copy="clientOauthUri"
                      v-clipboard:success="onCopySuccess"
                      v-clipboard:error="onCopyError"
                    >
                      <v-icon>mdi-content-copy</v-icon>
                    </v-btn>
                    <p v-if="!copyVisible" class="subtitle-1">Copied!</p>
                  </template>
                </v-textarea>
              </v-col>

              <!-- <a class="subtitle-1 red--text" @click="onVisit">访问</a> -->
            </v-row>
          </v-form>
        </v-container>
      </v-card-text>

      <v-card-actions>
        <v-btn class="mx-2" small dark fab color="pink" @click="onVisit"
          >访问</v-btn
        >
        <div class="flex-grow-1"></div>
        <v-btn text @click="onCancel">CLOSE</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
let Base64 = require("js-base64").Base64;
export default {
  name: "ClientManageViewDialog",
  components: {},
  props: {
    visible: Boolean,
    item: {
      clientId: "",
      clientSecretPlain: "",
      webServerRedirectUri: "",
      clientDescription: "",
    },
    dark: Boolean,
  },
  data() {
    return {
      oauthUri:
        process.env.VUE_APP_API + "/oauth/authorize?response_type=code&",
      copyVisible: true,
      redirectUri: "",
    };
  },
  watch: {},
  computed: {
    basicAuth() {
      return (
        "Basic " +
        Base64.encode(this.item.clientId + ":" + this.item.clientSecretPlain)
      );
    },

    clientOauthUri() {
      if (this.item.webServerRedirectUri == null) {
        return (
          this.oauthUri +
          "client_id=" +
          this.item.clientId +
          "&redirect_uri=" +
          this.redirectUri
        );
      } else {
        return (
          this.oauthUri +
          "client_id=" +
          this.item.clientId +
          "&redirect_uri=" +
          this.item.webServerRedirectUri
        );
      }
    },
  },
  methods: {
    onCopySuccess() {
      this.copyVisible = false;
      var that = this;
      setTimeout(function () {
        that.copyVisible = true;
      }, 2000);
    },

    onCopyError() {},

    onCancel() {
      this.$emit("changeVisible", false);
    },
    onVisit() {
      var url =
        this.oauthUri +
        "client_id=" +
        this.item.clientId +
        "&redirect_uri=" +
        this.item.webServerRedirectUri;
      if (this.item.webServerRedirectUri == null) {
        url =
          this.oauthUri +
          "client_id=" +
          this.item.clientId +
          "&redirect_uri=" +
          this.redirectUri;
      }
      window.open(url, "_blank");
    },
  },
  created() {},
  mounted() {},
};
</script>
<style></style>
