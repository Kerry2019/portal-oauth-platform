import axios from "axios";

//查询
export const queryClients = (params) =>
  axios.post("/paas/client/queryClients", params);

//新增
export const addClient = (params) =>
  axios.post("/paas/client/addClient", params);

//删除
export const deleteClient = (params) =>
  axios.post("/paas/client/deleteClient", params);

//编辑
export const updateClient = (params) =>
  axios.post("/paas/client/updateClient", params);
