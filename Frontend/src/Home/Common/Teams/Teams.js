import React from 'react';
import { InsertTeam } from './insertTeam';
import { DeleteTeam } from './deleteTeam';
import logo from "../../User/Groups/assets/header.png";
function Teams(props) {
    return (
        <div className="bg-gradient-to-r from-slate-800 to-gray-900 min-h-screen min-w-full">
      {/* header of the page */}
      <div className="px-20 py-10 w-full">
        <div className="flex items-center mt-9">
          <span class="material-symbols-outlined text-7xl">groups_2 </span>
          <div className=" font-bold text-slate-950 text-6xl pl-9">Teams</div>
        </div>
        <div className="text-slate-50 text-3xl px-4 ml-10 py-9 tracking-widest w-auto font-light">
          <span className="text-customGreen font-bold">Here</span>{" "}
          <span>you can add and delete teams in the league</span>
        </div>
      </div>
      <img
        src={logo}
        alt="Logo"
        className="fixed pointer-events-none left-80 opacity-25 blur-3xl h-screen"
      />

        <div>
            <InsertTeam />
            <DeleteTeam />
        </div>
    </div>
        
    );
}

export default Teams;