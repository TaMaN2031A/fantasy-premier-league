import React, { useState } from "react";
import { insertTeamServ } from "../../../Services/Teams/Team";

export const InsertTeam = () => {
    const [team, setTeam] = useState({
        name: "",
    });
    function inputChange(e) {
        /*
         * update the existing object by creating same obj (info)
         * but change name attribute with given value
         * */
        setTeam({ ...team, [e.target.name]: e.target.value });
    }



    const handleSubmit = async () => {
        try {
            const result = await insertTeamServ(team.name);
            console.log(result);
            // Handle the result as needed
        } catch (error) {
            console.error(error.message);
            // Handle the error as needed
        }
    };

    return (
        <div>
        {/* Join Group */}
      <div>
      <form className="space-y-4  w-4/5 ml-40">
        <div>
          <label
            for="insertTeam"
            className="block mb-2 text-2xl font-medium text-white"
          >
            Insert Team
          </label>
          <input
            name="name"
            id="name"
            value={team.name}
            placeholder="Enter Team Name"
            onChange={
                inputChange
            }
            className="bg-gray-50 border border-gray-300 text-gray-900 text-xl rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            required
          />
        </div>
        <button
          type="button"
          onClick={
            handleSubmit
          }
          className="min-w-full focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-2xl px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
        >
          Insert
        </button>
      </form>
      <hr className="border-b-2 mt-20 mb-10 border-gray-500 w-4/5 mx-auto" />
    </div>
        </div>
    );
};