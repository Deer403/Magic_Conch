import axios from "axios";
import { Message } from "element-ui";

const service = axios.create({
  baseURL: "http://127.0.0.1:8090/",
});

service.defaults.headers.post["Content-Type"] =
  "application/json; charset=UTF-8";

service.interceptors.request.use(
  (config) => {
    return config;
  },
  (error) => {
    console.log(error);
    return Promise.reject(error);
  }
);

// response interceptor
service.interceptors.response.use(
  (response) => {
    const res = response.data;
    return res;
  },
  (error) => {
    console.log("err" + error); // for debug
    Message({
      message: error.message,
      type: "error",
      duration: 5 * 1000,
    });
    return Promise.reject(error);
  }
);

export default service;
