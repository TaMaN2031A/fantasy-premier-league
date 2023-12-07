export const fetchTeamsData = async () => {
    const response = await fetch('http://localhost:8080/team/getAll', {method:'GET'});
    if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const jsonresult = await response.json();
    return jsonresult;
};
export const insertTeamServ = async (name) => {
    try {
        const response = await fetch(`http://localhost:8080/team/insert/${name}`, {
          method: 'POST',
          mode: 'cors',
          credentials: 'same-origin',
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
export const deleteTeamServ = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/team/delete/${id}`, {
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