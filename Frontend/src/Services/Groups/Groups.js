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


// export const fetchFAQData = async () => {
//   return handleRequest(axios.get(serverHost + "/faq/getAll"));
// };

// export const deleteFAQService = async (id) => {
//   return handleRequest(axios.delete(serverHost + "/faq/delete/" + id));
// };

// export const addFAQService = async (data) => {
//   return handleRequest(axios.post(serverHost + "/faq/insert", data));
// };

// export const updateFAQService = async (data) => {
//   return handleRequest(axios.put(serverHost + "/faq/update", data));
// };
