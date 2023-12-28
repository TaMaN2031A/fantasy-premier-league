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

export const getAllPublicGroups = async (userName) => {
    console.log("fetching groups", userName);
  return handleRequest(
    axios.get(serverHost + "/group/getPublicGroups/" + userName)
  );
};

export const addUserToGroup = async (data) => {
  return handleRequest(axios.post(serverHost + "/group/addUserToGroup", data)
  );
};

// done
export const fetchYourGroups = async (username) => {
  console.log("fetching groups", username);
  return handleRequest(
    axios.get(serverHost + "/group/getUserGroups/" + username)
  );
};

// done
export const createGroup = async (data) => {
  return handleRequest(axios.post(serverHost + "/group/createGroup", data));
};

// done
export const getGroupInfo = async (id, userName) => {
  return handleRequest(
    axios.get(serverHost + "/group/getSpecificGroupInfo/" + id + "/" + userName)
  );
};
