import React from "react";
import Card from "./Card";
import Profile from "./Profile";
import plLogo from "../Authentication/logo.png";

function Home(props) {
  const items = [1, 2, 3, 4, 5, 6, 7, 8];

  const list = items.map(() => (
    <Card Title="Matches" details="HAve fun and play with friends" />
  ));

  return (
    <div className="bg-gradient-to-r from-slate-800 to-gray-900 min-h-screen">
      <img className="object-scale-down mx-auto shadow-md hover:shadow-lg transition duration-300 ease-in-out"  src={plLogo} alt="logo" />
      <div className=" justify-center text-center">
        <Profile />
        <hr className="border-b-2 border-gray-500 w-3/4 mx-auto" />
        <div className="flex flex-wrap justify-center">{list}</div>
      </div>
    </div>
  );
}

export default Home;
