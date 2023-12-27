import React , { useState } from 'react';
import Pagination from '../../Admin/Promotion/Pagination';
import { useQuery} from "react-query";
import {GetAllPublicGroups, addUserToGroup} from "../../../Services/Groups/Groups";
import { GetAuthDataFn } from "../../../Routes/wrapper";
function JoinGroups(props) {
    const[person] = GetAuthDataFn();
    const [PageNo, setPageNo] = useState(1);
    
    const[groupID , setGroupID] = useState("");

    const { data, isLoading, error, refetch } = useQuery(
        ['UsersKey', PageNo],
        () => [{groupName : "hello" , code : "dlfjkdl"}], //GetAllUsers(PageNo-1),
        { refetchOnWindowFocus: false }
    );
    



    const handleButtonClick =  async (userName , groupID)=>{
           await addUserToGroup(userName , groupID);
           await refetch(PageNo);
    }


    

    if(isLoading){
        return ( <section className="bg-gradient-to-r from-slate-800 to-gray-900">
            <div className="flex flex-col items-center justify-center px-6 py-2 mx-auto md:h-screen lg:py-0">
                <div role="status"
                     className="max-w-md p-4 space-y-4 border border-gray-200 divide-y divide-gray-200 rounded shadow animate-pulse dark:divide-gray-700 md:p-6 dark:border-gray-700">
                    <div className="flex items-center justify-between">
                        <div>
                            <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                            <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
                        </div>
                        <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
                    </div>
                    <div className="flex items-center justify-between pt-4">
                        <div>
                            <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                            <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
                        </div>
                        <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
                    </div>
                    <div className="flex items-center justify-between pt-4">
                        <div>
                            <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                            <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
                        </div>
                        <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
                    </div>
                    <div className="flex items-center justify-between pt-4">
                        <div>
                            <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                            <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
                        </div>
                        <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
                    </div>
                    <div className="flex items-center justify-between pt-4">
                        <div>
                            <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-600 w-24 mb-2.5"></div>
                            <div className="w-32 h-2 bg-gray-200 rounded-full dark:bg-gray-700"></div>
                        </div>
                        <div className="h-2.5 bg-gray-300 rounded-full dark:bg-gray-700 w-12"></div>
                    </div>
                    <span className="sr-only">Loading...</span>
                </div>
            </div>
        </section>);
    }
    if (error) {
        return <p>Error loading users</p>;
    }
    
    return (
        <div className="bg-gradient-to-r from-slate-800 to-gray-900 min-h-screen min-w-full">
      
            
        {/* <section className="bg-gray-50 dark:bg-gray-900 p-3 sm:p-5 md:h-screen"> */}

            <div className="px-20 py-14 w-full flex flex-row">
                <div className="flex items-center mt-9">
                    <span class="material-symbols-outlined text-7xl">Join </span>
                    <div className=" font-bold text-slate-950 text-6xl pl-9">
                        Groups
                    </div>
                </div>
                <div className="text-slate-50 text-3xl px-4 ml-10 py12 tracking-widest w-auto font-light">
                    <span className="text-customGreen font-bold">Here</span>{" "}
                    <span>you can find all the puclic groups in the fantasy.</span>
                    <br></br>
                    <span className="text-customGreen font-bold">Join</span>{" "}
                    <span>
                        you can join any public group and private group with its code.
                    </span>
                </div>
        
                
            </div>
        <div className= "items-center">
            <span className="text-customGreen font-bold items-center">Public Groups</span>{" "}
        </div>
        
        <div className="mx-auto max-w-screen-xl px-4 lg:px-12 ">
            <div className="bg-white dark:bg-gray-800 relative shadow-md sm:rounded-lg overflow-hidden ">
                <div className="flex flex-col md:flex-row items-center justify-between space-y-3 md:space-y-0 md:space-x-4 p-4">
                </div>
                <div className="overflow-x-auto">
                    <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                        <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                        <tr>
                            <th scope="col" className="px-4 py-3">
                                Group Name
                            </th>
                            <th scope="col" className="px-4 py-3">
                                Code 
                            </th>
                            <th scope="col" className="px-4 py-3">
                                Actions
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        {data.map((group, index) => (
                            <tr key={index} className="border-b dark:border-gray-700">
                                <td className="px-4 py-3 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                    {group.groupName}
                                </td>
                                <td className="px-4 py-3">{group.code}</td>
                                <td className="px-4 py-3 flex items-center justify-end">
                                    <button
                                        id={`user-${index}-dropdown-button`}
                                        data-dropdown-toggle={`user-${index}-dropdown`}
                                        className="inline-flex items-center p-0.5 text-sm font-medium text-center text-gray-500 hover:text-gray-800 rounded-lg focus:outline-none dark:text-gray-400 dark:hover:text-gray-100"
                                        type="button"
                                        onClick={() => handleButtonClick(person.userName , group.code)}
                                    >
                                       Join
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
        </div>
        </div>

        <div>
            <p className="text-4xl font-medium text-white">
                Find By Code
            </p>
      <form className="space-y-4 mt-10">
        <div>
          <label
            for="email"
            className="block mb-2 text-2xl font-medium text-gray-900 dark:text-white"
          >
            Enter Code 
          </label>
          <input
            name="code"
            id="code"
            value={groupID}
            onChange={(e) => {setGroupID(e.target.value)}}
            className="bg-gray-50 border border-gray-300 text-gray-900 text-xl rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            required
          />
        </div>
        <button
          type="button"
          onClick={() => {handleButtonClick(person.userName , groupID )}}
          className="min-w-full focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-2xl px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
        >
          Join 
        </button>
      </form>
      <hr className="border-b-2 mt-20 mb-10 border-gray-500 w-4/5 mx-auto" />
        </div>
    {/* </section> */}
    </div>


    
        
    



        

    );
}

export default JoinGroups;