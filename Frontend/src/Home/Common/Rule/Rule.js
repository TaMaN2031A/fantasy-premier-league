import React from "react";
import { useQuery } from "react-query";
import { GetAuthDataFn } from "../../../Routes/wrapper";
import { adminPrivilege } from "../../../collection";
import { fetchRuleData } from "../../../Services/Rule/Rule";
import Loading from "../FAQ/Loading";
import RuleCard from "./RuleCard";
import InsertRule from "./InsertRule";
import DeleteRule from "./DeleteRule";
import UpdateRule from "./UpdateRule";

function Rule(props) {
  const { data, isLoading, error, refetch } = useQuery(
    "RuleData",
    fetchRuleData,
    { refetchOnWindowFocus: false }
  );
  const { person } = GetAuthDataFn();

  if (error) {
    return <p>Error: {error.message}</p>;
  }

  return (
    <div className="py-4 bg-gradient-to-r from-slate-800 to-gray-900 min-h-screen">
      <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 flex flex-col justify-between">
        {/* header */}
        <div className="text-center mt-11">
          <h3 className="sm:text-4xl leading-normal font-extrabold tracking-tight text-white">
            Fantasy <span className="text-indigo-600 text-6xl">Rules</span>
          </h3>
        </div>
        <hr className="border-b-2 mt-20 mb-10 border-gray-500 w-4/5 mx-auto" />

        {/* ONLY FOR ADMIN */}
        {/* insert new rule */}
        {person.privilege === adminPrivilege && <InsertRule func={refetch} />}

        <div className="mt-20 mb-12">
          <ul className="">
            {isLoading ? (
              // skeletonLoading
              <Loading />
            ) : (
              // quetions and answers
              data.map((item, index) => (
                <li className="text-left mb-16">
                  {console.log(item)}
                  <RuleCard rule={item.rule} />
                  {/* ONLY FOR ADMIN */}
                  {person.privilege === adminPrivilege && (
                    <div className="px-48 mt-8 ">
                      {/* update a specific rule */}
                      <UpdateRule func={refetch} ruleID={item.ruleID} />
                      {/* delete a specific rule */}
                      <DeleteRule func={refetch} ruleID={item.ruleID} />
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

export default Rule;
