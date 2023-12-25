import React from "react";
import logo from "./cup.png";
import "./../../Home Page/Card.css";

function GroupCard(props) {
  return (
    <div className="relative w-full h-40 flex justify-center group">
      <button
        className="w-4/5 relative rounded-3xl text-gray-200 font-light shadow-2xl flex flex-row items-center
        border-8 border-grey p-4 transition-all duration-1000 hover:scale-110 hover:ring-4 hover:bg-customGreen ring-gold card-shine-effect"
      >
        <div className="px-16 text-7xl tracking-widest group-hover:text-black transition-all duration-1000">
          {props.groupName}
        </div>
        <span class="material-symbols-outlined mt-6 text-4xl text-customGreen group-hover:text-white transition-all duration-1000">
          engineering
        </span>
        <div className="px-4 text-3xl font-medium  tracking-wide mt-6 group-hover:text-black transition-all duration-1000">
          {props.OwnerUsername}
        </div>
        <span class="material-symbols-outlined mt-6 text-5xl px-8 text-customGreen group-hover:text-white transition-all duration-1000">
          groups
        </span>
        <div className="text-3xl font-medium tracking-widest mt-6 group-hover:text-black transition-all duration-1000">
          {props.participantsNo}
        </div>
        <span class="material-symbols-outlined mt-6 text-5xl px-8 text-customGreen group-hover:text-white transition-all duration-1000">
          timeline
        </span>
        <div className="text-3xl font-medium  tracking-widest mt-6 group-hover:text-black transition-all duration-1000">
          {props.avgPoints}
        </div>
        <div className="opacity-10 overflow-hidden absolute right-7">
          <img src={logo} alt="Logo" className="pt-4 w-auto h-auto" />
        </div>
      </button>
    </div>
  );
}

export default GroupCard;
