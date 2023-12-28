import React, { useState } from "react";
import { useQuery } from "react-query";
import {
  getAllPublicGroups,
  addUserToGroup,
} from "../../../Services/Groups/Groups";
import { GetAuthDataFn } from "../../../Routes/wrapper";
import logo from "./assets/header.png";

function JoinGroups() {
  const { person } = GetAuthDataFn();
  const [groupID, setGroupID] = useState("");
  const { data, isLoading, error, refetch } = useQuery(
    "JoinGroups",
    () => getAllPublicGroups(person.username),
    { refetchOnWindowFocus: false }
  );

  const handleButtonClick = async (userName, groupID) => {
    await addUserToGroup({ userName, groupID });
    setGroupID("");
    await refetch();
  };

  if (isLoading) {
    return (
      <section className="bg-gradient-to-r from-slate-800 to-gray-900">
        <div className="flex flex-col items-center justify-center px-6 py-2 mx-auto md:h-screen lg:py-0">
          <div
            role="status"
            className="max-w-md p-4 space-y-4 border border-gray-200 divide-y divide-gray-200 rounded shadow animate-pulse dark:divide-gray-700 md:p-6 dark:border-gray-700"
          >
            <div className="flex items-center justify-between">
              <div>
                <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
              </div>
              <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
            </div>
            <div className="flex items-center justify-between pt-4">
              <div>
                <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
              </div>
              <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
            </div>
            <div className="flex items-center justify-between pt-4">
              <div>
                <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
              </div>
              <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
            </div>
            <div className="flex items-center justify-between pt-4">
              <div>
                <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
              </div>
              <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
            </div>
            <div className="flex items-center justify-between pt-4">
              <div>
                <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
              </div>
              <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
            </div>
            <span className="sr-only">Loading...</span>
          </div>
        </div>
      </section>
    );
  }

  if (error) {
    return <p>Error loading users</p>;
  }

  return (
    <div className="bg-gradient-to-r from-slate-800 to-gray-900 min-h-screen min-w-full">
      {/* header of the page */}
      <div className="px-20 py-10 w-full">
        <div className="flex items-center mt-9">
          <span class="material-symbols-outlined text-7xl">groups_2 </span>
          <div className=" font-bold text-slate-950 text-6xl pl-9">Groups</div>
        </div>
        <div className="text-slate-50 text-3xl px-4 ml-10 py-9 tracking-widest w-auto font-light">
          <span className="text-customGreen font-bold">Here</span>{" "}
          <span>you can find all the puclic groups in the fantasy.</span>
          <br></br>
          <span className="text-customGreen font-bold">Join</span>{" "}
          <span>any public group and private group with its code.</span>
        </div>
      </div>
      <img
        src={logo}
        alt="Logo"
        className="fixed pointer-events-none left-80 opacity-25 blur-3xl h-screen"
      />

      {/* Join Group */}
      <div>
        <form className="space-y-4  w-4/5 ml-40">
          <div>
            <label
              for="email"
              className="block mb-2 text-2xl font-medium text-gray-900 dark:text-white"
            >
              Enter Code
            </label>
            <input
              name="code"
              id="code"
              value={groupID}
              placeholder="Enter the code of the group"
              onChange={(e) => {
                setGroupID(e.target.value);
              }}
              className="bg-gray-50 border border-gray-300 text-gray-900 text-xl rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
              required
            />
          </div>
          <button
            type="button"
            onClick={() => {
              handleButtonClick(person.username, groupID);
            }}
            className="min-w-full focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-2xl px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
          >
            Join
          </button>
        </form>
        <hr className="border-b-2 mt-20 mb-10 border-gray-500 w-4/5 mx-auto" />
      </div>

      <div className="mx-auto max-w-screen-xl px-4 opacity-75 pb-28 lg:px-12 ">
        <div className="text-5xl mb-8 text-gray-700 bg-gray-50 tracking-widest dark:bg-gray-700 text-center dark:text-gray-400 mt-5 px-4 py-3 rounded-2xl dark:bg-opacity-25">
          Public Groups
        </div>{" "}
        <div className="bg-white dark:bg-gray-800 relative shadow-md sm:rounded-lg overflow-hidden ">
          <div className="flex flex-col md:flex-row items-center justify-between space-y-3 md:space-y-0 md:space-x-4 p-4"></div>
          <div className="overflow-x-auto">
            <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
              <thead className="text-base text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                <tr>
                  <th scope="col" className="px-4 py-3">
                    Group Name
                  </th>
                  <th scope="col" className="px-4 py-3">
                    Group ID
                  </th>
                  <th scope="col" className="px-4 py-3">
                    Owner Name
                  </th>
                  <th scope="col" className="px-4 py-3">
                    Participants
                  </th>
                  <th scope="col" className="px-4 py-3">
                    Average Points
                  </th>
                  <th scope="col" className="px-4 py-3">
                    Action
                  </th>
                </tr>
              </thead>
              <tbody>
                {data.map((group, index) => (
                  <tr
                    key={index}
                    className="border-b text-lg dark:border-gray-700"
                  >
                    <td className="px-4 py-3">{group.groupName}</td>
                    <td className="px-4 py-3">{group.groupID}</td>
                    <td className="px-4 py-3">{group.ownerName}</td>
                    <td className="px-4 py-3">{group.noParticipants}</td>
                    <td className="px-4 py-3">{group.avgPoints}</td>
                    <td className="px-4 py-3">
                      <button
                        id={`user-${index}-dropdown-button`}
                        data-dropdown-toggle={`user-${index}-dropdown`}
                        className="bg-csut items-center p-0.5 text-lg w-2/3 font-medium text-center text-white hover:text-gray-800 rounded-lg dark:text-white dark:bg-customGreen dark:hover:text-black dark:focus:ring-2 dark:focus:ring-slate-500 dark:focus:ring-offset-2"
                        type="button"
                        onClick={(event) => {
                          handleButtonClick(person.username, group.groupID);
                        }}
                      >
                        Join
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}

export default JoinGroups;
