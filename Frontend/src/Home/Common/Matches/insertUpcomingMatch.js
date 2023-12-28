import React, {useEffect, useState} from "react";


import { insertUpcomingMatchServ } from "../../../Services/Matches/Matches";



export const InsertUpcomingMatch = () => {
    const [upcomingMatch, setUpcomingMatch] = useState({
        homeID: null,
        awayID: null,
        week: null,
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
        
        <div>
        {/* Join Group */}
      <div>
      <form className="space-y-4  w-4/5 ml-40">
        <div>
          <label
            for="insertPlayer"
            className="block mb-2 text-2xl font-medium text-white "
          >
            Insert upcomingMatch
          </label>
          <br></br>
          <br></br>
          <label
            for="insertPlayer"
            className="block mb-2 text-2xl font-medium text-white "
          >
            Home Team ID
          </label>
          <input
            name="name"
            id="name"
            value={upcomingMatch.homeID}
            placeholder="Enter home Match id"
            onChange={
                inputChange
            }
            className="bg-gray-50 border border-gray-300 text-gray-900 text-xl rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            required
          />
        </div>
        <div>
        <label
            for="insertPlayer"
            className="block mb-2 text-2xl font-medium text-white "
          >
            Away Team ID
          </label>
            <input
                type="number"
                id="awayid"
                placeholder="Enter away Match id"
                name="awayid"
                value={upcomingMatch.awayID}
                onChange={inputChange}
                className="border border-gray-300 p-2 rounded-md w-full"
                required
            />
        </div>

        <div>
        <label
            for="insertPlayer"
            className="block mb-2 text-2xl font-medium text-white "
          >
            Week number
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

        <div>
        <label
            for="insertPlayer"
            className="block mb-2 text-2xl font-medium text-white "
          >
            Stadium Name
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
        // <div className="border border-gray-300 p-5 rounded-lg bg-black w-2/5 mx-auto mt-10">
        //     <form>
        //         {/* ------------------ home id part ---------------------- */}
        //         <div className="mb-4">
        //             <label htmlFor="formBasicHomeID" className="block text-sm font-medium text-gray-600">
        //                 Home Team ID
        //             </label>
        //             <input
        //                 type="text"
        //                 id="formBasicHomeID"
        //                 placeholder="Enter Home Team ID please"
        //                 name="homeID"
        //                 value={upcomingMatch.homeID}
        //                 onChange={inputChange}
        //                 className="mt-1 p-2 block w-full border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300"
        //                 required
        //             />
        //         </div>

        //         {/* ------------------ away id part ---------------------- */}
        //         <div className="mb-4">
        //             <label htmlFor="formBasicAwayID" className="block text-sm font-medium text-gray-600">
        //                 Away Team ID
        //             </label>
        //             <input
        //                 type="text"
        //                 id="formBasicAwayID"
        //                 placeholder="Enter Away Team ID please"
        //                 name="awayID"
        //                 value={upcomingMatch.awayID}
        //                 onChange={inputChange}
        //                 className="mt-1 p-2 block w-full border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300"
        //                 required
        //             />
        //         </div>

        //         {/* ------------------ week number part ---------------------- */}
        //         <div className="mb-4">
        //             <label htmlFor="formBasicWeekNum" className="block text-sm font-medium text-gray-600">
        //                 Week Number Part
        //             </label>
        //             <input
        //                 type="text"
        //                 id="formBasicWeekNum"
        //                 placeholder="Enter Week Number please"
        //                 name="week"
        //                 value={upcomingMatch.week}
        //                 onChange={inputChange}
        //                 className="mt-1 p-2 block w-full border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300"
        //                 required
        //             />
        //         </div>

        //         {/* ------------------ stadium part ---------------------- */}
        //         <div className="mb-4">
        //             <label htmlFor="formBasicStadium" className="block text-sm font-medium text-gray-600">
        //                 Stadium
        //             </label>
        //             <input
        //                 type="text"
        //                 id="formBasicStadium"
        //                 placeholder="Enter stadium of match"
        //                 name="stadium"
        //                 value={upcomingMatch.stadium}
        //                 onChange={inputChange}
        //                 className="mt-1 p-2 block w-full border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300"
        //                 required
        //             />
        //         </div>

        //         <button
        //             type="button"
        //             className="bg-dark text-white py-2 px-4 rounded"
        //             onClick={handleSubmit}
        //         >
        //             Submit
        //         </button>
        //     </form>
        // </div>


    )

};