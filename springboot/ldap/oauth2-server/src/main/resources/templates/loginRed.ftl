<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <script src="https://cdn.bootcss.com/vue/2.5.17/vue.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@4.x/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
</head>

<body>
<div id="app">
    <v-app class="all">
        <div class="circle">
            <form action="/uaa/login" method="post" class="login-container">
                <h2 class="login-title">OAuth2 ‧ 登录</h2>

                <form-item>
                    <v-col cols="12" sm="12" md="12">
                        <v-text-field name="username" v-model="username" label="Username" solo rounded clearable></v-text-field>
                    </v-col>
                </form-item>
                <form-item>
                    <v-col cols="12" sm="12" md="12">
                        <v-text-field name="password" v-model="password" label="Password" solo rounded clearable
                                      :append-icon="passwordShow ? 'mdi-eye' : 'mdi-eye-off'" :type="passwordShow ? 'text' : 'password'"
                                      @click:append="passwordShow = !passwordShow"></v-text-field>
                    </v-col>
                </form-item>
                <form-item style="width:100%; text-align:center;">
                    <v-col cols="12" sm="12" md="12">
                        <v-btn type="primary" color="white" elevation="3" native-type="submit" :loading="loading">Login
                            In
                        </v-btn>
                    </v-col>
                </form-item>
                <p style="color: white" v-if="isError()">用户名/密码错误</p>
            </form>
        </div>
    </v-app>
</div>
</body>

<script src="https://cdn.jsdelivr.net/npm/vue@2.x/dist/vue.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.js"></script>
<script type="text/javascript">
    new Vue({
        el: '#app',
        vuetify: new Vuetify(),
        data: {
            passwordShow: false,
            loading: false,
            username: '',
            password: '',
        },
        init: function () {

        },
        methods: {
            isError: function () {
                return decodeURIComponent((new RegExp('[?|&]error=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
            }
        }
    })

</script>

<style lang="scss" scoped>
    .all {
        width: 100%;
        height: 100%;
        background-color: #e86752 !important;
    }

    .login-container {
        width: 20rem;
        margin: 1rem auto;
    }

    .login-title {
        text-align: center;
        font-size: 1.8rem;
        color: white;
        font-weight: 500;
        margin-bottom: 1rem;
    }

    .circle {
        width: 24rem;
        margin: auto;
        margin-top: 6rem;
        border-radius: 100%;
        background-color: #e86752;
        box-shadow: 10px 10px 5px #fafafa;
    }
</style>

</html>