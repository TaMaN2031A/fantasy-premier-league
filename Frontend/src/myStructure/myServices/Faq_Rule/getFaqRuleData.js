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



export const fetchPlayersData = async () => {
    const response = await fetch(hostOfBack + '/player/getAll', {method:'GET'});
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error(`HTTP error! Status: ${response.status}`);
    }
};
export const fetchTeamsData = async () => {
    const response = await fetch(hostOfBack + '/team/getAll', {method:'GET'});
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error(`HTTP error! Status: ${response.status}`);
    }
};