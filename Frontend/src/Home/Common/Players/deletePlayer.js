import React, { useState } from "react";
import { deletePlayerServ } from "../../../Services/Players/Player";

export const DeletePlayer = () => {
    const [player, setPlayer] = useState({
        id: 0,
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
            console.log(player.id);

            const result = await deletePlayerServ(parseInt(player.id));
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
        <div className="border border-gray-300 p-4 rounded-lg w-2/5 bg-black">
            <form>
                {/* ID Part */}
                <div className="mb-4">
                    <label htmlFor="playerId" className="block">
                        Player Delete
                    </label>
                    <input
                        type="text"
                        id="playerId"
                        placeholder="Enter id of player"
                        name="id"
                        value={player.id}
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