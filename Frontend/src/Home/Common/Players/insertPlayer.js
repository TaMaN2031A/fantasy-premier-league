import React, { useState } from "react";
import { insertPlayerServ } from "../../../Services/Players/Player";

export const InsertPlayer = () => {
    const [player, setPlayer] = useState({
        name: "",
        position: "",
        number_in_team: 0,
        id_of_team: 0,
    });
    function inputChange(e) {
        /*
         * update the existing object by creating same obj (info)
         * but change name attribute with given value
         * */
        setPlayer({ ...player, [e.target.name]: e.target.value });
    }

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
        <div className="border p-4 rounded-lg w-2/5 bg-black">
            <form>
                {/* Player Name */}
                <div className="mb-4">
                    <label htmlFor="playerName" className="block">
                        Player Name
                    </label>
                    <input
                        type="text"
                        id="playerName"
                        placeholder="Enter Player Name please"
                        name="name"
                        value={player.name}
                        onChange={inputChange}
                        className="border border-gray-300 p-2 rounded-md w-full"
                        required
                    />
                </div>

                {/* Position */}
                <div className="mb-4">
                    <label htmlFor="playerPosition" className="block">
                        Position
                    </label>
                    <input
                        type="text"
                        id="playerPosition"
                        placeholder="Enter Position"
                        name="position"
                        value={player.position}
                        onChange={inputChange}
                        className="border border-gray-300 p-2 rounded-md w-full"
                        required
                    />
                </div>

                {/* T-Shirt Number */}
                <div className="mb-4">
                    <label htmlFor="tshirtNumber" className="block">
                        T-Shirt Number
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

                {/* Team ID */}
                <div className="mb-4">
                    <label htmlFor="teamId" className="block">
                        Team
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
                    className="bg-gray-800 text-white py-2 px-4 rounded-md"
                    onClick={handleSubmit}
                >
                    Submit
                </button>
            </form>
        </div>
    );
};