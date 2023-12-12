import React from "react";
import Profile from "./Profile";
import plLogo from "../../Security/assets/PremierLeagueLogo.png";
import { GetAuthDataFn } from "../../Routes/wrapper";
import {adminList, commonList, userList} from "../../Routes/RenderNavigation";
import {adminPrivilege} from "../../collection";

function Home(props) {

    const { person } = GetAuthDataFn();
    const myCommonList = commonList();
    const myAdminList = adminList();
    const myUserList = userList();

  let list = [...myCommonList, ...(person.isAuthorized && person.privilege === adminPrivilege) ? myAdminList: myUserList];

  return (
    <div className="bg-gradient-to-r from-slate-800 to-gray-900 min-h-screen">
      <img className="object-scale-down mx-auto shadow-md hover:shadow-lg transition duration-300 ease-in-out"  src={plLogo} alt="logo" />
      <div className=" justify-center text-center">
        <Profile />
        <hr className="border-b-2 border-gray-500 w-3/4 mx-auto" />
        <div className="flex flex-wrap justify-center mt-10">{list}</div>
      </div>
    </div>
  );
}

export default Home;
