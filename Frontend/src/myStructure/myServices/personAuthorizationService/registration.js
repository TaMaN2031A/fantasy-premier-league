import axios from 'axios';
import {hostOfBack} from "../../collection";

const API_BASE_URL = hostOfBack + '/register';

const handleRequest = async (requestPromise) => {
    try {
        const response = await requestPromise;
        return response.data;
    } catch (error) {
        return "failed";
    }
};

export const signUp = async (info) => {
    const requestData = { ...info };
    delete requestData.confirmedPassword;

    return handleRequest(
        axios.post(`${API_BASE_URL}/sign-up`, requestData)
    );
};

export const signIn = async (info) => {
    console.log(info)
    return handleRequest(
        axios.post(`${API_BASE_URL}/sign-in`, info)
    );
};

export const forgetPassword = async (info) => {
    return handleRequest(
        axios.post(`${API_BASE_URL}/ForgetPassword`, info)
    );
};

export const updatePassword = async (info) => {
    console.log(info)
    return handleRequest(
        axios.post(`${API_BASE_URL}/updatePassword`, info)
    );
};

export const googleAuthSignIn = async (info) => {
    return handleRequest(
        axios.post(`${API_BASE_URL}/oauth/google`, info)
    );
};
