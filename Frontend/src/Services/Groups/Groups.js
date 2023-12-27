import { serverHost } from "../../collection";
import axios from "axios";
const handleRequest = async (requestPromise) => {
  try {
    const response = await requestPromise;
    return response.data;
  } catch (error) {
    return "failed";
  }
};

export const fetchYourGroups = async (username) => {
    return handleRequest(axios.get(serverHost + "/groups/yourgrous", username));
};

export const createGroup = async (data) => {
    return handleRequest(axios.post(serverHost + "/groups/create", data));
};

export const getGroupInfo = async (id) => {
    return handleRequest(axios.get(serverHost + "/groups/getGroupInfo", id));
};
