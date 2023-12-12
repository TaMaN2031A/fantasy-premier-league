import {hostOfBack} from "../../collection";
import axios from "axios";
const handleRequest = async (requestPromise) => {
    try {
        const response = await requestPromise;
        return response.data;
    } catch (error) {
        return "failed";
    }
};

export const fetchFaqData = async () => {
    return handleRequest(
        axios.get( hostOfBack + '/faq/getAll')
    );
};

