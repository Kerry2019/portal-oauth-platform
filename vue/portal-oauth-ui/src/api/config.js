import axios from "axios";

//查询
export const getConfig = (params) =>
  axios.post("/paas/config/getConfig", params);

export const updateLoginPage = (params) =>
  axios.post("/paas/config/updateLoginPage", params);
