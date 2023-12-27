import React from "react";
import logo from "./assets/header.png";
import GroupCard from "./GroupCard";
import CreateGroup from "./CreateGroup";
import Loading from "../../Common/FAQ/Loading";
import { useQuery } from "react-query";
import { fetchYourGroups } from "../../../Services/Groups/Groups";
import { GetAuthDataFn } from "../../../Routes/wrapper";

function UserGroups() {
  const { person } = GetAuthDataFn();
  const { data, isLoading, error, refetch } = useQuery(
    "UserGroups",
    () => fetchYourGroups(person.username),
    { refetchOnWindowFocus: false }
  );

  if (error) {
    return <p>Error: {error.message}</p>;
  }

  return (
    <div className="bg-gradient-to-r from-slate-800 to-gray-900 min-h-screen min-w-full">
      {/* header of the page */}
      <div className="px-20 py-14 w-full flex flex-row">
        <div>
          <div className="flex items-center mt-9">
            <span class="material-symbols-outlined text-7xl">groups_2 </span>
            <div className=" font-bold text-slate-950 text-6xl pl-9">
              Groups
            </div>
          </div>
          <div className="text-slate-50 text-3xl px-4 ml-10 py-12 tracking-widest w-auto font-light">
            <span className="text-customGreen font-bold">Here</span>{" "}
            <span>you can find all the groups that you've joined.</span>
            <br></br>
            <span className="text-customGreen font-bold">Engage</span>{" "}
            <span>
              with fellow enthusiasts, form leagues, and join competitive
              groups.
            </span>
            <br></br>
            <span className="text-customGreen font-bold">Create</span>{" "}
            <span>a new group either public or private</span>
            <br></br>
            <span className="text-customGreen font-bold">Invite</span>{" "}
            <span>your friends and compete together for higher ranking.</span>
          </div>

          <CreateGroup func={refetch} />
        </div>
        <img
          src={logo}
          alt="Logo"
          className="fixed pointer-events-none left-80 opacity-25 blur-3xl h-screen"
        />
      </div>

      <hr className="border-b-2 mb-20 border-gray-500 w-4/5 mx-auto" />
      <ul className="pb-20">
        {isLoading ? (
          // skeletonLoading
          <div className="ml-44">
            <Loading />
          </div>
        ) : (
          // quetions and answers
          data.map((item, index) => (
            <li className="text-left mb-16">
              <GroupCard
                groupName={item.groupName}
                groupId={item.groupID}
                OwnerUsername={item.ownerName}
                participantsNo={item.noParticipants}
                avgPoints={item.avgPoints}
              />{" "}
            </li>
          ))
        )}
      </ul>
    </div>
  );
}

export default UserGroups;