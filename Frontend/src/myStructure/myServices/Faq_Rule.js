import { hostOfBack } from "../collection";
const API_BASE_URL = hostOfBack + '/faq';

export const fetchFaqData = async () => {
    try {
      const response = await fetch(`${API_BASE_URL}/getAll`, { method: 'GET' });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const jsonResult = await response.json();
      return jsonResult;
    } catch (error) {
      throw new Error(`Error during data fetching: ${error.message}`);
    }
  };

