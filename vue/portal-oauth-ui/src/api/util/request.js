import axios from "axios";
let instance = axios.create({
  baseURL: process.env.VUE_APP_API,
  timeout: 5000,
  headers: {
    "Content-Type": "application/x-www-form-urlencoded",
  },
  transformRequest: [
    function (data) {
      return data;
    },
  ],
  transformResponse: [
    function (data) {
      return data;
    },
  ],
  auth: {},
  responseType: "json",
  maxContentLength: 5000,
  validateStatus: function (status) {
    return status >= 200 && status < 300; // default
  },
  // showMessage:false
});
