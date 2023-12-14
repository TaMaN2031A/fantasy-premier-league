import { serverHost } from "../../collection";
import axios from "axios";
const handleRequest = async (requestPromise) => {
  try {
    const response = await requestPromise;
    console.log(response.data);
    return response.data;
  } catch (error) {
    return "failed";
  }
};

export const fetchRuleData = async () => {
  return handleRequest(axios.get(serverHost + "/rule/getAll"));
};

export const deleteRuleService = async (id) => {
  return handleRequest(axios.delete(serverHost + "/rule/delete/" + id));
};

export const addRuleService = async (data) => {
  return handleRequest(axios.post(serverHost + "/rule/insert", data));
};

export const updateRuleService = async (data) => {
  return handleRequest(axios.put(serverHost + "/rule/update", data));
};
