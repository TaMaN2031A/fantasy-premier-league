import { React, useState } from "react";
import { addRuleService } from "../../../Services/Rule/Rule";

function InsertRule(props) {
  const [rule, setRule] = useState("");

  const InsertRule = async () => {
    try {
      await addRuleService({ rule });
      setRule("");
      await props.func();
    } catch (err) {
      console.error("Error inserting item or fetching data:", err);
    }
  };

  return (
    <>
      <p className="text-4xl mx-auto font-medium text-white">
        Insert a new Rule here.
      </p>
      <form className="space-y-4 mt-10">
        <div>
          <label
            for="email"
            className="block mb-2 text-2xl font-medium text-gray-900 dark:text-white"
          >
            Your Rule
          </label>
          <input
            name="question"
            id="question"
            value={rule}
            onChange={(e) => setRule(e.target.value)}
            className="bg-gray-50 border border-gray-300 text-gray-900 text-xl rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            required
          />
        </div>
        <button
          type="button"
          onClick={() => InsertRule()}
          className="min-w-full focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-2xl px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
        >
          Insert
        </button>
      </form>
      <hr className="border-b-2 mt-20 mb-10 border-gray-500 w-4/5 mx-auto" />
    </>
  );
}

export default InsertRule;
