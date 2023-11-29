export const fetchFaqData = async () => {
    const response = await fetch('http://localhost:8080/faq/getAll', { method: 'GET' });
    if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const jsonresult = await response.json();
    return jsonresult;
};
export const fetchPlayersData = async () => {
    const response = await fetch('http://localhost:8080/player/getAll', {method:'GET'});
    if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const jsonresult = await response.json();
    return jsonresult;
};
export const fetchTeamsData = async () => {
    const response = await fetch('http://localhost:8080/team/getAll', {method:'GET'});
    if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const jsonresult = await response.json();
    return jsonresult;
};