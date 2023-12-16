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
        <div className="border border-gray-300 p-5 rounded-lg w-2/5 bg-black">
            <form>
                {/* Team Name Part */}
                <div className="mb-4">
                    <label htmlFor="teamName" className="block">
                        Team Name
                    </label>
                    <input
                        type="text"
                        id="teamName"
                        placeholder="Enter Team Name"
                        name="name"
                        value={team.name}
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