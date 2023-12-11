import { hostOfBack } from "../collection";

const API_BASE_URL = hostOfBack + '/upcomingMatch';

export const insertUpcomingMatchServ = async (data) => {
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
export const deleteUpcomingMatchServ = async (id) => {
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