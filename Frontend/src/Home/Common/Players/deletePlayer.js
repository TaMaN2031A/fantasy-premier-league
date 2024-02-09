import React, { useState } from "react";
import { deletePlayerServ } from "../../../Services/Players/Player";

export const DeletePlayer = () => {
    const [player, setPlayer] = useState({
        id: null,
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

        <div>
        {/* Join Group */}
      <div>
      <form className="space-y-4  w-4/5 ml-40">
        <div>
          <label
            for="teamId"
            className="block mb-2 text-2xl font-medium text-white"
          >
            Delete Player
          </label>
          <input
            name="id"
            id="id"
            value={player.id}
            placeholder="Enter Player ID"
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
          Delete
        </button>
      </form>
      <hr className="border-b-2 mt-20 mb-10 border-gray-500 w-4/5 mx-auto" />
    </div>
        </div>
        // <div className="border border-gray-300 p-4 rounded-lg w-2/5 bg-black">
        //     <form>
        //         {/* ID Part */}
        //         <div className="mb-4">
        //             <label htmlFor="playerId" className="block">
        //                 Player Delete
        //             </label>
        //             <input
        //                 type="text"
        //                 id="playerId"
        //                 placeholder="Enter id of player"
        //                 name="id"
        //                 value={player.id}
        //                 onChange={inputChange}
        //                 className="border border-gray-300 p-2 rounded-md w-full"
        //                 required
        //             />
        //         </div>

        //         <button
        //             type="button"
        //             className="bg-gray-800 text-white py-2 px-4 rounded-md"
        //             onClick={handleSubmit}
        //         >
        //             Submit
        //         </button>
        //     </form>
        // </div>
    );
};