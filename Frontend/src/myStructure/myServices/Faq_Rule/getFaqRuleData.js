import {hostOfBack} from "../../collection";

export const fetchFaqData = async () => {
    const response = await fetch(hostOfBack + '/faq/getAll', { method: 'GET' });
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error(`HTTP error! Status: ${response.status}`);
    }
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