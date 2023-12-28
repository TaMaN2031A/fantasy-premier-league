import React, { useState } from "react";
import { insertPlayerServ } from "../../../Services/Players/Player";

export const InsertPlayer = () => {
    const [player, setPlayer] = useState({
        name: "",
        position: "FWD",
        number_in_team: null,
        id_of_team: 0,
    });
    function inputChange(e) {
        /*
         * update the existing object by creating same obj (info)
         * but change name attribute with given value
         * */
        setPlayer({ ...player, [e.target.name]: e.target.value });
    }

    const positions = ["GK", "DEF", "MID", "FWD"]; // List of regions
    const [isOpen, setIsOpen] = useState(false);
    const selectPosition = (position) => {
        setIsOpen(false); // Close the dropdown after selecting a region
        setPlayer({ ...player, ["position"]: position });
    };

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    };

    const handleSubmit = async () => {
        try {
            const result = await insertPlayerServ(player);
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
            for="insertPlayer"
            className="block mb-2 text-2xl font-medium text-white "
          >
            Insert Player
          </label>
          <input
            name="name"
            id="name"
            value={player.name}
            placeholder="Enter player Name"
            onChange={
                inputChange
            }
            className="bg-gray-50 border border-gray-300 text-gray-900 text-xl rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            required
          />
        </div>
        <div className="relative  mt-7">
                        <button
                            id="dropdownDefaultButton"
                            onClick={toggleDropdown}
                            className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                            type="button">
                            {player.position}{' '}
                            <svg
                                className={`w-2.5 h-2.5 ms-3 ${isOpen ? 'transform rotate-180' : ''}`}
                                aria-hidden="true"
                                xmlns="http://www.w3.org/2000/svg"
                                fill="none"
                                viewBox="0 0 10 6"
                            >
                            <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="m1 1 4 4 4-4" />
                            </svg>
                            
                        </button>

                            <div
                                id="dropdown"
                                className={`${
                                    isOpen ? 'block' : 'hidden'
                                } absolute z-10 bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700`}
                            >
                            <ul className="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdownDefaultButton">
                            {positions.map((position, index) => (
                            <li key={index}>
                            <button
                                    onClick={() => selectPosition(position)}
                                    className="block w-full px-4 py-2 text-left hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">
                                    {position}
                            </button>
                    </li>
                ))}
                </ul>
            </div>
        </div>
        <div>
        <label
            for="insertPlayer"
            className="block mb-2 text-2xl font-medium text-white"
          >
            T-shirt Number
          </label>
            <input
                type="number"
                id="tshirtNumber"
                placeholder="Enter number of T-Shirt"
                name="number_in_team"
                value={player.number_in_team}
                onChange={inputChange}
                className="border border-gray-300 p-2 rounded-md w-full"
                required
            />
        </div>

        <div>
        <label
            for="insertPlayer"
            className="block mb-2 text-2xl font-medium text-white"
          >
            Team ID
          </label>
          <input
                        type="text"
                        id="teamId"
                        placeholder="Enter id of team"
                        name="id_of_team"
                        value={player.id_of_team}
                        onChange={inputChange}
                        className="border border-gray-300 p-2 rounded-md w-full"
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