import React, {useEffect, useState} from "react";


import { insertUpcomingMatchServ } from "../../../Services/Matches/Matches";



export const InsertUpcomingMatch = () => {
    const [upcomingMatch, setUpcomingMatch] = useState({
        homeID: 0,
        awayID: 0,
        week: 0,
        stadium : ""
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
            const result = await insertUpcomingMatchServ(upcomingMatch);
            console.log(result);
            // Handle the result as needed
          } catch (error) {
            console.error(error.message);
            // Handle the error as needed
          }

    };

    return (
        <div className="border border-gray-300 p-5 rounded-lg bg-black w-2/5 mx-auto mt-10">
            <form>
                {/* ------------------ home id part ---------------------- */}
                <div className="mb-4">
                    <label htmlFor="formBasicHomeID" className="block text-sm font-medium text-gray-600">
                        Home Team ID
                    </label>
                    <input
                        type="text"
                        id="formBasicHomeID"
                        placeholder="Enter Home Team ID please"
                        name="homeID"
                        value={upcomingMatch.homeID}
                        onChange={inputChange}
                        className="mt-1 p-2 block w-full border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300"
                        required
                    />
                </div>

                {/* ------------------ away id part ---------------------- */}
                <div className="mb-4">
                    <label htmlFor="formBasicAwayID" className="block text-sm font-medium text-gray-600">
                        Away Team ID
                    </label>
                    <input
                        type="text"
                        id="formBasicAwayID"
                        placeholder="Enter Away Team ID please"
                        name="awayID"
                        value={upcomingMatch.awayID}
                        onChange={inputChange}
                        className="mt-1 p-2 block w-full border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300"
                        required
                    />
                </div>

                {/* ------------------ week number part ---------------------- */}
                <div className="mb-4">
                    <label htmlFor="formBasicWeekNum" className="block text-sm font-medium text-gray-600">
                        Week Number Part
                    </label>
                    <input
                        type="text"
                        id="formBasicWeekNum"
                        placeholder="Enter Week Number please"
                        name="week"
                        value={upcomingMatch.week}
                        onChange={inputChange}
                        className="mt-1 p-2 block w-full border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300"
                        required
                    />
                </div>

                {/* ------------------ stadium part ---------------------- */}
                <div className="mb-4">
                    <label htmlFor="formBasicStadium" className="block text-sm font-medium text-gray-600">
                        Stadium
                    </label>
                    <input
                        type="text"
                        id="formBasicStadium"
                        placeholder="Enter stadium of match"
                        name="stadium"
                        value={upcomingMatch.stadium}
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