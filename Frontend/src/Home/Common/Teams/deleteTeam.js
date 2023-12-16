import React, { useEffect, useState } from "react";
import { deleteTeamServ } from "../../../Services/Teams/Team";

export const DeleteTeam = () => {
    const [team, setTeam] = useState({
        id: 0,
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
            console.log(team.id);

            const result = await deleteTeamServ(parseInt(team.id));
            console.log(result);
            // Handle the result as needed
        } catch (error) {
            console.error(error.message);
            // Handle the error as needed
        }
    };
    const formStyle = {
        border: "1px solid #ccc",
        padding: "20px",
        borderRadius: "10px",
        width: "30%",
        backgroundColor: "white",
    };
    return (
        <div className="border border-gray-300 p-5 rounded-lg w-2/5 bg-black">
            <form>
                {/* ID Part */}
                <div className="mb-4">
                    <label htmlFor="teamId" className="block">
                        Team Delete
                    </label>
                    <input
                        type="text"
                        id="teamId"
                        placeholder="Enter id of team"
                        name="id"
                        value={team.id}
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