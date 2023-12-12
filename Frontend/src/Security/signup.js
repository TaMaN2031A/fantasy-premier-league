import React, {useState} from 'react';
import {Link, useNavigate} from "react-router-dom";
import {GetAuthDataFn} from "../Routes/wrapper";
import {signUp} from "../Services/Authentication/registration";
import {paths, responses, toastStyle, userPrivilege} from "../collection";
import plLogo from "./assets/PremierLeagueLogo.png";
import {toast, ToastContainer} from "react-toastify";

function Signup() {
    const navigate = useNavigate();
    const { setPerson } = GetAuthDataFn();

    /*
    * need to check on 2 passwords.
    * */
    const isValidGmailEmail = (email) => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            return false;
        }

        const domain = email.split('@')[1].toLowerCase();
        const validGmailDomains = ['gmail.com', 'googlemail.com'];
        return validGmailDomains.includes(domain);
    };

    const [info, setInfo] = useState({
        firstName: "",
        lastName: "",
        userName: "",
        email: "",
        region: "Region",
        password: "",
        confirmedPassword: ""
    });

    /*
     * update the existing object by creating same obj (info)
     * but change name attribute with given value
     * */
    function inputChange(e) {
        setInfo({ ...info, [e.target.name]: e.target.value });
    }

    const regions = ['Africa', 'Asia', 'Europe', 'America']; // List of regions
    const [isOpen, setIsOpen] = useState(false);
    const selectRegion = (region) => {
        setIsOpen(false); // Close the dropdown after selecting a region
        setInfo({ ...info, ["region"]: region });
    };

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    };

    const handleSubmit = async () => {
        if(!isValidGmailEmail(info.email)){
            toast.error(responses.MailNotValid, toastStyle)
            return;
        }
        if(info.password !== info.confirmedPassword) {
            toast.error(responses.notEqualPasswords, toastStyle)
            return;
        }

        let ret = await signUp(info);
        if(ret === responses.signUpSuccessfully){
            await setPerson({
                isAuthorized: true,
                username: info.userName,
                privilege: userPrivilege,
                personObj: {}
            });
            toast.success(ret, toastStyle);
            navigate(paths.home);
        } else {
            toast.error(ret, toastStyle);
        }
    };

    return (
        <section className="bg-gradient-to-r from-slate-800 to-gray-900">
            <div className="flex flex-col items-center justify-center px-6 py-2 mx-auto md:h-screen lg:py-0">
                <ToastContainer/>

                {/*----------------------------------premier league img------------------------------------------*/}
                <img className="object-scale-down w-60 mb-10" src={plLogo} alt="logo"/>
                <div
                    className="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
                    <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
                        <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900
                            md:text-2xl dark:text-white text-center justify-center">
                            Sign up
                        </h1>

                        {/*-----------------------------------form------------------------------------------*/}
                        <form className="space-y-4 md:space-y-6">

                            <div className="flex">
                                {/*-----------------------------------first name------------------------------------------*/}
                                <div>
                                    <label htmlFor="First"
                                           className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                                        Your First Name
                                    </label>
                                    <input
                                        className="bg-gray-50 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                        type="text"
                                        placeholder="Enter firstName"
                                        name="firstName"
                                        value={info.firstName}
                                        onChange={inputChange} required/>
                                </div>

                                {/*-----------------------------------last name------------------------------------------*/}
                                <div className="ml-5">
                                    <label htmlFor="Second"
                                           className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                                        Your Last Name
                                    </label>
                                    <input
                                        className="bg-gray-50 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                        type="text"
                                        placeholder="Enter lastName"
                                        name="lastName"
                                        value={info.lastName}
                                        onChange={inputChange} required/>
                                </div>
                            </div>

                            <div className="flex">
                                {/*-----------------------------------Username------------------------------------------*/}
                                <div>
                                    <label htmlFor="Username"
                                           className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                                        Your Username
                                    </label>
                                    <input
                                        className="bg-gray-50 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                        type="text"
                                        placeholder="Enter userName"
                                        name="userName"
                                        value={info.userName}
                                        onChange={inputChange} required/>
                                </div>

                                {/*-----------------------------------regions------------------------------------------*/}
                                <div className="relative ml-4 mt-7">
                                    <button
                                        id="dropdownDefaultButton"
                                        onClick={toggleDropdown}
                                        className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                                        type="button">
                                        {info.region}{' '}
                                        <svg
                                            className={`w-2.5 h-2.5 ms-3 ${isOpen ? 'transform rotate-180' : ''}`}
                                            aria-hidden="true"
                                            xmlns="http://www.w3.org/2000/svg"
                                            fill="none"
                                            viewBox="0 0 10 6"
                                        >
                                            <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="m1 1 4 4 4-4" />
                                        </svg>
                                    </button>

                                    <div
                                        id="dropdown"
                                        className={`${
                                            isOpen ? 'block' : 'hidden'
                                        } absolute z-10 bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700`}
                                    >
                                        <ul className="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdownDefaultButton">
                                            {regions.map((region, index) => (
                                                <li key={index}>
                                                    <button
                                                        onClick={() => selectRegion(region)}
                                                        className="block w-full px-4 py-2 text-left hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">
                                                        {region}
                                                    </button>
                                                </li>
                                            ))}
                                        </ul>
                                    </div>
                                </div>
                            </div>


                            {/*-----------------------------------email------------------------------------------*/}
                            <div>
                                <label htmlFor="email"
                                       className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your
                                    email</label>
                                <input
                                    className="bg-gray-50 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    type="email"
                                    placeholder="Enter email"
                                    name="email"
                                    value={info.email}
                                    onChange={inputChange} required/>
                            </div>

                            {/*-----------------------------------password------------------------------------------*/}
                            <div>
                                <label htmlFor="password"
                                       className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                                <input
                                    className="bg-gray-50 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    type="password"
                                    placeholder="Enter password"
                                    name="password"
                                    value={info.password}
                                    onChange={inputChange} required/>
                            </div>

                            {/*-----------------------------------check password------------------------------------------*/}
                            <div>
                                <label htmlFor="password"
                                       className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Confirm
                                    password</label>
                                <input
                                    className="bg-gray-50 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    type="password"
                                    placeholder="Enter confirmedPassword"
                                    name="confirmedPassword"
                                    value={info.confirmedPassword}
                                    onChange={inputChange} required/>
                            </div>

                            {/*-----------------------------------submit------------------------------------------*/}
                            <button className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4
                                    focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5
                                    text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"
                                    type="button" onClick={handleSubmit}>
                                Sign up
                            </button>

                            {/*-----------------------------------log-in------------------------------------------*/}
                            <div className="inline-flex">
                                <p className="text-sm font-light text-gray-500 dark:text-gray-400">
                                    Don’t have an account yet?
                                    <Link to="/login"
                                          className="font-medium text-primary-600 hover:underline dark:text-primary-500">
                                        log in
                                    </Link>
                                </p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default Signup;
