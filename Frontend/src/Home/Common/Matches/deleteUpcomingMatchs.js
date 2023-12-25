import React, {useEffect,  useState} from "react";

import {deleteUpcomingMatchServ} from "../../../Services/Matches/Matches"

export const DeleteUpcomingMatch = () => {
    const [upcomingMatch, setUpcomingMatch] = useState({
        id: 0
    });
    function inputChange (e) {
        /*
        * update the existing object by creating same obj (info)
        * but change name attribute with given value
        * */
        setUpcomingMatch({...upcomingMatch, [e.target.name]: e.target.value})
    }



    const handleSubmit = async ()=> {
        try {
            console.log(upcomingMatch.id);

            const result = await deleteUpcomingMatchServ(parseInt(upcomingMatch.id));
            console.log(result);
            // Handle the result as needed
          } catch (error) {
            console.error(error.message);
            // Handle the error as needed
          }
    };
    const formStyle =
        {
            border: '1px solid #ccc',
            padding: '20px',
            borderRadius: '10px',
            width: '30%',
            backgroundColor: 'white'
        }
    return (
        <div className="border border-gray-300 p-5 rounded-lg bg-black w-2/5 mx-auto mt-10">
            <form>
                {/* ------------------ id part ---------------------- */}
                <div className="mb-4">
                    <label htmlFor="formId" className="block text-sm font-medium text-gray-600">
                        Upcoming Delete
                    </label>
                    <input
                        type="text"
                        id="formId"
                        placeholder="Enter id of upcoming match"
                        name="id"
                        value={upcomingMatch.id}
                        onChange={inputChange}
                        className="mt-1 p-2 block w-full border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300"
                        required
                    />
                </div>

                <button
                    type="button"
                    className="bg-dark text-white py-2 px-4 rounded"
                    onClick={handleSubmit}
                >
                    Submit
                </button>
            </form>
        </div>


    )

};