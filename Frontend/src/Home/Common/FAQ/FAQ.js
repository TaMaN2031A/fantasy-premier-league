import React from "react";
import { fetchFAQData } from "../../../Services/FAQ/Faq_Rule";
import { useQuery } from "react-query";
import { GetAuthDataFn } from "../../../Routes/wrapper";
import { adminPrivilege } from "../../../collection";
import InsertFAQ from "./InsertFAQ";
import DeleteFAQ from "./DeleteFAQ";
import Loading from "./Loading";
import QA from "./QA";
import UpdateFAQ from "./UpdateFAQ";

function Faq() {
  const { data, isLoading, error, refetch } = useQuery("FAQData", fetchFAQData, {refetchOnWindowFocus: false});
  const { person } = GetAuthDataFn();

  if (error) {
    return <p>Error: {error.message}</p>;
  }

  return (
    <div className="py-4 bg-gradient-to-r from-slate-800 to-gray-900 min-h-screen">
      <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 flex flex-col justify-between">
        {/* header */}
        <div className="text-center">
          <p className="mt-4 leading-7 text-white font-regular text-5xl mb-10">
            F.A.Q
          </p>
          <h3 className="sm:text-4xl leading-normal font-extrabold tracking-tight text-white">
            Frequently Asked{" "}
            <span className="text-indigo-600 text-6xl">Questions</span>
          </h3>
        </div>
        <hr className="border-b-2 mt-20 mb-10 border-gray-500 w-4/5 mx-auto" />

        {/* ONLY FOR ADMIN */}
        {/* insert new FAQ */}
        {person.privilege === adminPrivilege && <InsertFAQ func={refetch} />}

        <div className="mt-20 mb-12">
          <ul className="">
            {isLoading ? (
              // skeletonLoading
              <Loading />
            ) : (
              // quetions and answers
              data.map((item, index) => (
                <li className="text-left mb-16">
                  <QA question={item.question} answer={item.answer} />
                  {/* ONLY FOR ADMIN */}
                  {person.privilege === adminPrivilege && (
                    <div className="px-48 mt-8 ">
                      {/* update a specific faq */}
                      <UpdateFAQ func={refetch} faqID={item.faqID} />
                      {/* delete a specific faq */}
                      <DeleteFAQ func={refetch} faqID={item.faqID} />
                    </div>
                  )}
                </li>
              ))
            )}
          </ul>
        </div>
      </div>
    </div>
  );
}

export default Faq;
