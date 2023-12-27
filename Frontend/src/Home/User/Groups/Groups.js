import React, { useEffect } from "react";
import { useLocation } from "react-router-dom";
import logo from "./assets/header.png";
import { GetAuthDataFn } from "../../../Routes/wrapper";

function Groups(props) {
  const location = useLocation();
  const groupInfo = new URLSearchParams(location.search);
  const groupId = groupInfo.get("id");
  const groupName = groupInfo.get("name");

  const { person } = GetAuthDataFn();

  const data = [
    {
      firstName: "Amr",
      lastName: "Ahmed",
      username: "amrahmed",
      region: "Europe",
      points: 1000,
    },
    {
      firstName: "Emily",
      lastName: "Johnson",
      username: "emilyj",
      region: "North America",
      points: 850,
    },
    {
      firstName: "Luis",
      lastName: "Garcia",
      username: "luisg",
      region: "South America",
      points: 920,
    },
    {
      firstName: "Amr",
      lastName: "Ahmed",
      username: "AmrAhmed",
      region: "Europe",
      points: 1000,
    },
    {
      firstName: "Emily",
      lastName: "Johnson",
      username: "emilyj",
      region: "North America",
      points: 850,
    },
    {
      firstName: "Luis",
      lastName: "Garcia",
      username: "luisg",
      region: "South America",
      points: 920,
    },
    {
      firstName: "Amr",
      lastName: "Ahmed",
      username: "amrahmed",
      region: "Europe",
      points: 1000,
    },
    {
      firstName: "Emily",
      lastName: "Johnson",
      username: "emilyj",
      region: "North America",
      points: 850,
    },
    {
      firstName: "Luis",
      lastName: "Garcia",
      username: "luisg",
      region: "South America",
      points: 920,
    },
    {
      firstName: "Amr",
      lastName: "Ahmed",
      username: "amrahmed",
      region: "Europe",
      points: 1000,
    },
    {
      firstName: "Emily",
      lastName: "Johnson",
      username: "emilyj",
      region: "North America",
      points: 850,
    },
    {
      firstName: "Luis",
      lastName: "Garcia",
      username: "luisg",
      region: "South America",
      points: 920,
    },
    {
      firstName: "Amr",
      lastName: "Ahmed",
      username: "amrahmed",
      region: "Europe",
      points: 1000,
    },
    {
      firstName: "Emily",
      lastName: "Johnson",
      username: "emilyj",
      region: "North America",
      points: 850,
    },
    {
      firstName: "Luis",
      lastName: "Garcia",
      username: "luisg",
      region: "South America",
      points: 920,
    },
    // Add more objects as needed...
  ];

  return (
    <section className="bg-gray-50 dark:bg-gray-900 p-3 sm:p-5 min-h-screen">
      <img
        src={logo}
        alt="Logo"
        className="fixed pointer-events-none left-80 opacity-25 blur-3xl h-screen"
      />
      <div className="mx-auto max-w-screen-xl opacity-75 pb-10 px-4 lg:px-12">
        <div className="text-5xl text-gray-700 bg-gray-50 tracking-widest dark:bg-gray-700 text-center dark:text-gray-400 mt-5 px-4 py-3 rounded-2xl dark:bg-opacity-25">
          {groupName}
        </div>{" "}
        <div className="bg-white dark:bg-gray-800 relative mt-12 shadow-md sm:rounded-lg overflow-hidden ">
          <div className="flex flex-col md:flex-row items-center justify-between space-y-3 md:space-y-0 md:space-x-4 p-4"></div>
          <div className="overflow-x-auto">
            <table className="w-full text-sm text-left dark:bg-opacity-75 text-gray-500 dark:text-gray-400">
              <thead className="text-base text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                <tr>
                  <th scope="col" className="px-4 py-3">
                    First Name
                  </th>
                  <th scope="col" className="px-4 py-3">
                    Last Name
                  </th>
                  <th scope="col" className="px-4 py-3">
                    Username
                  </th>
                  <th scope="col" className="px-4 py-3">
                    Region
                  </th>
                  <th scope="col" className="px-4 py-3">
                    Points
                  </th>
                </tr>
              </thead>
              <tbody>
                {data.map((user, index) => (
                  <tr
                    key={index}
                    className={`border-b text-lg dark:border-gray-700 text-gray-400 ${
                      user.username === person.username ? "bg-gray-900" : ""
                    }`}
                  >
                    <td className="px-4 py-3">{user.firstName}</td>
                    <td className="px-4 py-3">{user.lastName}</td>
                    <td className="px-4 py-3">{user.username}</td>
                    <td className="px-4 py-3">{user.region}</td>
                    <td className="px-4 py-3">{user.points}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </section>
  );
}

export default Groups;
