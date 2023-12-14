import React from "react";
import { deleteRuleService } from "../../../Services/Rule/Rule";

function DeleteRule(props) {
  const removeRule = async () => {
    try {
      await deleteRuleService(props.ruleID);
      await props.func();
    } catch (err) {
      console.error("Error deleting item or fetching data:", err);
    }
  };

  return (
    <button
      onClick={() => removeRule()}
      type="button"
      className="focus:outline-none w-48 h-16 text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-xl px-5 mx-12 py-2.5 me-2 mb-2"
    >
      Delete
    </button>
  );
}

export default DeleteRule;
