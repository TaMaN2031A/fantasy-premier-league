export const fetchFaqData = async () => {
    try {
      const response = await fetch('http://localhost:8080/faq/getAll', { method: 'GET' });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const jsonResult = await response.json();
      return jsonResult;
    } catch (error) {
      throw new Error(`Error during data fetching: ${error.message}`);
    }
  };