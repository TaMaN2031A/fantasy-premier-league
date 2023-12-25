import React from "react";
import logo from "./../../Home Page/assets/Lion.png";
import GroupCard from "./GroupCard";

function UserGroups(props) {

    const items = [
        { groupName: "Codeforces", groupId: 1, OwnerUsername: 'Amr Ahmed', participantsNo: 5, avgPoints: 1000 },
        { groupName: "Leetcode", groupId: 1, OwnerUsername: 'Amr Ahmed', participantsNo: 5, avgPoints: 1000 },
        { groupName: "Atcoder", groupId: 1, OwnerUsername: 'Amr Ahmed', participantsNo: 5, avgPoints: 1000 },
        { groupName: "Algorithms", groupId: 1, OwnerUsername: 'Amr Ahmed', participantsNo: 5, avgPoints: 1000 },
        { groupName: "Database", groupId: 1, OwnerUsername: 'Amr Ahmed', participantsNo: 5, avgPoints: 1000 },
        { groupName: "Software", groupId: 1, OwnerUsername: 'Amr Ahmed', participantsNo: 5, avgPoints: 1000 },
        { groupName: "Networks", groupId: 1, OwnerUsername: 'Amr Ahmed', participantsNo: 5, avgPoints: 1000 },
        { groupName: "AI", groupId: 1, OwnerUsername: 'Amr Ahmed', participantsNo: 5, avgPoints: 1000 }
      ];

    const jsxList = items.map((item) => (
      <GroupCard groupName={item.groupName} groupId={item.groupId} OwnerUsername={item.OwnerUsername} participantsNo={item.participantsNo} avgPoints={item.avgPoints} />
    ));

  return (
    <div className="bg-gradient-to-r from-slate-800 to-gray-900 min-h-screen min-w-full">
      {/* header of the page */}
      <div className="px-20 py-14 w-full flex flex-row">
        <div>
          <div className=" font-bold text-customGreen text-6xl underline underline-offset-8">Groups</div>
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
        </div>
        <div>
          <img src={logo} alt="Logo" className="w-40 h-40 mx-36 my-20" />
        </div>
      </div>

      <hr className="border-b-2 mb-20 border-gray-500 w-4/5 mx-auto" />

      <li className=" space-y-14">
        {jsxList}
      </li> 

    </div>
  );
}

export default UserGroups;
