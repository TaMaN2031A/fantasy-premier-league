import React from "react";

function RuleCard(props) {
  return (
    <div className="flex flex-row items-start mb-5 transform hover:scale-y-105 transition duration-700">
      <div className="hidden sm:flex items-center justify-center p-3 mr-3 rounded-full bg-indigo-500 text-white border-4 border-white text-xl font-semibold">
        <svg
          width="30px"
          fill="white"
          height="30px"
          viewBox="0 0 24 24"
          xmlns="http://www.w3.org/2000/svg"
        >
          <g data-name="Layer 2">
            <g data-name="menu-arrow">
              <rect
                width="24"
                height="24"
                transform="rotate(180 12 12)"
                opacity="0"
              ></rect>
              <path d="M17 9A5 5 0 0 0 7 9a1 1 0 0 0 2 0 3 3 0 1 1 3 3 1 1 0 0 0-1 1v2a1 1 0 0 0 2 0v-1.1A5 5 0 0 0 17 9z"></path>
              <circle cx="12" cy="19" r="1"></circle>
            </g>
          </g>
        </svg>
      </div>
      <div className="rounded-xl p-5 px-10 w-full flex bg-white items-center">
        <h4 className="text-2xl leading-6 font-medium text-black">
          {props.rule}
        </h4>
      </div>
    </div>
  );
}

export default RuleCard;
