import React from "react";

function Loading() {
  return (
    <div role="status" className="max-w-5xl animate-pulse">
      <ul className="mt-5 space-y-3">
        <li className="w-3/5 h-4 bg-gray-200 rounded-full dark:bg-gray-700"></li>
        <li className="w-4/5 h-4 bg-gray-200 rounded-full dark:bg-gray-700"></li>
        <li className="w-3/5 h-4 bg-gray-200 rounded-full dark:bg-gray-700"></li>
        <li className="w-4/5 h-4 bg-gray-200 rounded-full dark:bg-gray-700"></li>
        <li className="w-full h-4 bg-gray-200 rounded-full dark:bg-gray-700"></li>
      </ul>
    </div>
  );
}

export default Loading;
