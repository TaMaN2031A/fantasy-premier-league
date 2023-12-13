import { React, useState } from "react";
import { addFAQService } from "../../../Services/FAQ/Faq_Rule";

function InsertFAQ(props) {
  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");

  const insertFAQ = async () => {
    try {
      setAnswer("");
      setQuestion("");
      await addFAQService({ question, answer });
      await props.func;
    } catch (err) {
      console.error("Error inserting item or fetching data:", err);
    }
  };

  return (
    <>
      <p className="text-4xl mx-auto font-medium text-white">
        Insert a new FAQ here.
      </p>
      <form className="space-y-4 mt-10">
        <div>
          <label
            for="email"
            className="block mb-2 text-2xl font-medium text-gray-900 dark:text-white"
          >
            Your Question
          </label>
          <input
            name="question"
            id="question"
            value={question}
            onChange={(e) => setQuestion(e.target.value)}
            className="bg-gray-50 border border-gray-300 text-gray-900 text-xl rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            required
          />
          <label
            for="email"
            className="block mb-2 mt-4 text-2xl font-medium text-gray-900 dark:text-white"
          >
            Your Answer
          </label>
          <input
            name="answer"
            id="answer"
            value={answer}
            onChange={(e) => setAnswer(e.target.value)}
            className="bg-gray-50 border border-gray-300 text-gray-900 text-xl rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            required
          />
        </div>
        <button
          type="button"
          onClick={() => insertFAQ()}
          className="min-w-full focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-2xl px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
        >
          Insert
        </button>
      </form>
      <hr className="border-b-2 mt-20 mb-10 border-gray-500 w-4/5 mx-auto" />
    </>
  );
}

export default InsertFAQ;
