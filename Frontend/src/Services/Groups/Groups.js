import {serverHost} from "../../collection";
import axios from "axios";
const handleRequest = async (requestPromise) => {
    try {
        const response = await requestPromise;
        return response.data.content;
    } catch (error) {
        return "failed";
    }
};

export const GetAllUsers = async (userName) => {
    return handleRequest(
    axios.get(serverHost+'/group/getPublicGroups?userName='+userName)
    );
};

export const addUserToGroup = async (userName , groupID) => {
    return handleRequest(
    axios.get(serverHost+'/group/getPublicGroups?userName='+userName + "&groupID=" + groupID)
    );
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