import { serverHost } from "../../collection";
import {Link, useNavigate} from "react-router-dom";
import axios from 'axios';
const API_BASE_URL = serverHost + '/playedMatch';


const handleRequest = async (requestPromise) => {
    try {
        const response = await requestPromise;
        console.log("success");
        console.log(response.data);
        return response.data;
    } catch (error) {
        return "failed";
    }
};


export const addMatchStatistics = async (info) => {
    return handleRequest(
        axios.post(`${API_BASE_URL}/insert`, info)
    );
};