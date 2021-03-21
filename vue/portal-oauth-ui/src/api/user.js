import axios from "axios";

//查询
export const queryUsers = (params) =>
  axios.post("/paas/user/queryUsers", params);

//新增
export const addUser = (params) => axios.post("/paas/user/addUser", params);

//删除
export const deleteUser = (params) =>
  axios.post("/paas/user/deleteUser", params);

//编辑
export const updateUser = (params) =>
  axios.post("/paas/user/updateUser", params);
