import { hostOfBack } from "../collection";
const API_BASE_URL = hostOfBack + '/player';

export const fetchPlayersData = async () => {
    const response = await fetch(`${API_BASE_URL}/getAll`, {method:'GET'});
    if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const jsonresult = await response.json();
    return jsonresult;
};

export const insertPlayerServ = async (data) => {
    try {
        const response = await fetch(`${API_BASE_URL}/insert`, {
          method: 'POST',
          mode: 'cors',
          credentials: 'same-origin',
          body: JSON.stringify(data),
          headers: { 'Content-Type': 'application/json' },
        });

        if (!response.ok) {
          throw new Error('Network response was not ok');
        }

        return await response.json();
      } catch (error) {
        throw new Error(`Error during data fetching: ${error.message}`);
      }
};
export const deletePlayerServ = async (id) => {
        try {
          const response = await fetch(`${API_BASE_URL}/delete/${id}`, {
            method: 'DELETE',
            mode: 'cors',
            credentials: 'same-origin',
            headers: { 'Content-Type': 'application/json' },
          });

          if (!response.ok) {
            throw new Error('Network response was not ok');
          }

          return await response.json();
        } catch (error) {
          throw new Error(`Error during data deletion: ${error.message}`);
        }
};