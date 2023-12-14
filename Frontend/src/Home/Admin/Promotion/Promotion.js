import React, { useState} from 'react';
import { useQuery} from "react-query";
import {GetAllUsers, promoteUserToAdmin} from "../../../Services/Promotion/Promotion";
import Pagination from "./Pagination";
function Promotion() {
    const [PageNo, setPageNo] = useState(1);
    // const [Searched, setSearched] = useState(false);



// Function to handle dropdown change
    const { data, isLoading, error, refetch } = useQuery(
        ['UsersKey', PageNo],
        () => GetAllUsers(PageNo-1),
        { refetchOnWindowFocus: false }
    );

    const [selectedOption, setSelectedOption] = useState('email');

// Function to handle dropdown change
    const handleDropdownChange = (event) => {
        setSelectedOption(event.target.value);
    };

    // const [searchValue, setSearchValue] = useState('');
    //
    // const handleSearchChange = (event) => {
    //     setSearchValue(event.target.value);
    // };

    // const handleSearchSubmit = async (event) => {
    //     event.preventDefault();
    //     // Use the searchValue for your logic (e.g., searching, filtering, etc.)
    //     console.log('Search value:', searchValue,selectedOption);
    //     // const updateUser = useMutation(async (userName) => {
    //         setSearched(true);
    //         await SearchBySpecifications(PageNo, selectedOption, searchValue)
    // };
    //
    //     // Call your backend function to get something based on the userName
    //     const result = await SearchBySpecifications(PageNo,selectedOption,searchValue);
    //     // Update the user data in the query with the new result
    //     const updatedData = result[0];
    //     return updatedData;
    // }, {
    //     onSuccess: (newData) => {
    //         // After the mutation is successful, update the query data
    //         refetch(newData);
    //     },
    // });

// Example of refetching with the current PageNo
//     const handleRefetch = () => {
//         refetch();
//     };

// You can also pass a specific pageNo
//     const handleRefetchWithPage = (page) => {
//         refetch(page);
//     };

    // useEffect(() => {
    //     handleRefetchWithPage(PageNo-1); // Assuming refetch accepts an object with parameters
    // }, [PageNo]);
    const handlePageChange =  (newPage) => {
        // Add any additional logic here before updating the page
         setPageNo(newPage);
        // Add any additional logic here after updating the page
    };
    const handleButtonClick =  async (userName)=>{
           await promoteUserToAdmin(userName);
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
    // const reFetchData = async () => {
    //     // Update the parameters in the state
    //     // setPageNo(newPageNo);
    //     // newPageNo
    //     // Call refetch without passing newPageNo as a parameter
    //     await refetch().then((data) => console.log(data));
    // };
    // const PromoteUser = async (id){
    //
    // }

    return (
        <section className="bg-gray-50 dark:bg-gray-900 p-3 sm:p-5 md:h-screen">
            <div className="mx-auto max-w-screen-xl px-4 lg:px-12 ">
                <div className="bg-white dark:bg-gray-800 relative shadow-md sm:rounded-lg overflow-hidden ">
                    <div className="flex flex-col md:flex-row items-center justify-between space-y-3 md:space-y-0 md:space-x-4 p-4">
                        {/*<div className="w-full md:w-1/2">*/}
                        {/*    <form className="flex items-center" >*/}
                        {/*        <label htmlFor="simple-search" className="sr-only">*/}
                        {/*            Search*/}
                        {/*        </label>*/}
                        {/*        <div className="relative w-full">*/}
                        {/*            <div className="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">*/}
                        {/*                <svg*/}
                        {/*                    aria-hidden="true"*/}
                        {/*                    className="w-5 h-5 text-gray-500 dark:text-gray-400"*/}
                        {/*                    fill="currentColor"*/}
                        {/*                    viewBox="0 0 20 20"*/}
                        {/*                    xmlns="http://www.w3.org/2000/svg"*/}
                        {/*                >*/}
                        {/*                    <path*/}
                        {/*                        fillRule="evenodd"*/}
                        {/*                        d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"*/}
                        {/*                        clipRule="evenodd"*/}
                        {/*                    />*/}
                        {/*                </svg>*/}
                        {/*            </div>*/}
                        {/*            <input*/}
                        {/*                type="text"*/}
                        {/*                id="simple-search"*/}
                        {/*                value={searchValue}*/}
                        {/*                onChange={handleSearchChange}*/}
                        {/*                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-500 focus:border-primary-500 block w-full pl-10 p-2 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"*/}
                        {/*                placeholder="Search"*/}
                        {/*                required*/}
                        {/*            />*/}
                        {/*        </div>*/}
                        {/*    </form>*/}
                        {/*</div>*/}
                        {/*<div className="w-full md:w-auto flex flex-col md:flex-row space-y-2 md:space-y-0 items-stretch md:items-center justify-end md:space-x-3 flex-shrink-0">*/}
                        {/*    <div className="flex items-center space-x-3 w-full md:w-auto">*/}
                        {/*        <select*/}
                        {/*            id="filterDropdownButton"*/}
                        {/*            data-dropdown-toggle="filterDropdown"*/}
                        {/*            className="w-full md:w-auto flex items-center justify-center py-2 px-4 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-primary-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"*/}
                        {/*            value={selectedOption}*/}
                        {/*            onChange={handleDropdownChange}*/}
                        {/*        >*/}
                        {/*            <option value="email">Email</option>*/}
                        {/*            <option value="userName">Username</option>*/}
                        {/*        </select>*/}
                        {/*        <div*/}
                        {/*            id="filterDropdown"*/}
                        {/*            className="z-10 hidden w-48 p-3 bg-white rounded-lg shadow dark:bg-gray-700"*/}
                        {/*        >*/}
                        {/*            <h6 className="mb-3 text-sm font-medium text-gray-900 dark:text-white">*/}
                        {/*                Choose brand*/}
                        {/*            </h6>*/}
                        {/*            <ul*/}
                        {/*                className="space-y-2 text-sm"*/}
                        {/*                aria-labelledby="filterDropdownButton"*/}
                        {/*            >*/}
                        {/*                <li className="flex items-center">*/}
                        {/*                    <input id="apple" type="checkbox" value=""*/}
                        {/*                           className="w-4 h-4 bg-gray-100 border-gray-300 rounded text-primary-600 focus:ring-primary-500 dark:focus:ring-primary-600 dark:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"/>*/}
                        {/*                    <label htmlFor="apple"*/}
                        {/*                           className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-100">Apple*/}
                        {/*                        (56)</label>*/}
                        {/*                </li>*/}
                        {/*                <li className="flex items-center">*/}
                        {/*                    <input id="fitbit" type="checkbox" value=""*/}
                        {/*                           className="w-4 h-4 bg-gray-100 border-gray-300 rounded text-primary-600 focus:ring-primary-500 dark:focus:ring-primary-600 dark:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"/>*/}
                        {/*                    <label htmlFor="fitbit"*/}
                        {/*                           className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-100">Microsoft*/}
                        {/*                        (16)</label>*/}
                        {/*                </li>*/}
                        {/*                <li className="flex items-center">*/}
                        {/*                    <input id="razor" type="checkbox" value=""*/}
                        {/*                           className="w-4 h-4 bg-gray-100 border-gray-300 rounded text-primary-600 focus:ring-primary-500 dark:focus:ring-primary-600 dark:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"/>*/}
                        {/*                    <label htmlFor="razor"*/}
                        {/*                           className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-100">Razor*/}
                        {/*                        (49)</label>*/}
                        {/*                </li>*/}
                        {/*                <li className="flex items-center">*/}
                        {/*                    <input id="nikon" type="checkbox" value=""*/}
                        {/*                           className="w-4 h-4 bg-gray-100 border-gray-300 rounded text-primary-600 focus:ring-primary-500 dark:focus:ring-primary-600 dark:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"/>*/}
                        {/*                    <label htmlFor="nikon"*/}
                        {/*                           className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-100">Nikon*/}
                        {/*                        (12)</label>*/}
                        {/*                </li>*/}
                        {/*                <li className="flex items-center">*/}
                        {/*                    <input id="benq" type="checkbox" value=""*/}
                        {/*                           className="w-4 h-4 bg-gray-100 border-gray-300 rounded text-primary-600 focus:ring-primary-500 dark:focus:ring-primary-600 dark:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"/>*/}
                        {/*                    <label htmlFor="benq"*/}
                        {/*                           className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-100">BenQ*/}
                        {/*                        (74)</label>*/}
                        {/*                </li>*/}
                        {/*            </ul>*/}
                        {/*        </div>*/}
                        {/*    </div>*/}
                        {/*</div>*/}
                    </div>
                    <div className="overflow-x-auto">
                        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                            <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                            <tr>
                                <th scope="col" className="px-4 py-3">
                                    User Name
                                </th>
                                <th scope="col" className="px-4 py-3">
                                    Email
                                </th>
                                <th scope="col" className="px-4 py-3">
                                    Actions
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            {data[0].map((user, index) => (
                                <tr key={index} className="border-b dark:border-gray-700">
                                    <td className="px-4 py-3 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                        {user.userName}
                                    </td>
                                    <td className="px-4 py-3">{user.email}</td>
                                    <td className="px-4 py-3 flex items-center justify-end">
                                        <button
                                            id={`user-${index}-dropdown-button`}
                                            data-dropdown-toggle={`user-${index}-dropdown`}
                                            className="inline-flex items-center p-0.5 text-sm font-medium text-center text-gray-500 hover:text-gray-800 rounded-lg focus:outline-none dark:text-gray-400 dark:hover:text-gray-100"
                                            type="button"
                                            onClick={() => handleButtonClick(user.userName)}
                                        >
                                           Promote
                                        </button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                    <nav className="flex flex-col md:flex-row justify-between items-start md:items-center space-y-3 md:space-y-0 p-4" aria-label="Table navigation">
      <span className="text-sm font-normal text-gray-500 dark:text-gray-400">
        Showing
        <span className="font-semibold text-gray-900 dark:text-white" style={{ marginLeft: '0.5em', marginRight: '0.5em' }}>
          {(PageNo - 1) * 10 + 1}-{PageNo * 10}
        </span>
        of
        <span className="font-semibold text-gray-900 dark:text-white" style={{ marginLeft: '0.5em', marginRight: '0.5em' }}>
          {data[1]}
        </span>
      </span>
                        <Pagination currentPage={PageNo} totalPages={Math.ceil(data[1]/10)} onPageChange={handlePageChange} />
                    </nav>
                </div>
            </div>
        </section>
    );
}

export default Promotion;