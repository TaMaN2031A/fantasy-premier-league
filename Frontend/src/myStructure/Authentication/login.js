import React, { useEffect, useState } from "react";
import {Link, useNavigate} from "react-router-dom";
import { GetAuthDataFn } from "../wrapper";
import {login, signIn} from "../myServices/personAuthorizationService/registration";
import { adminPrv, userPrv } from "../collection";
import  Google  from "./Google";
import { gapi } from "gapi-script";
import { clientID } from "../collection";
import logo from './bg.png';
import plLogo from './logo.png';


export const Login = () => {
    const navigate = useNavigate();
    const { setPerson } = GetAuthDataFn();

    // must be converted into service

    const [info, setInfo] = useState({
        userNameOrEmail: "",
        password: "",
        role: "",
    });

    function inputChange(e) {
        /*
         * update the existing object by creating same obj (info)
         * but change name attribute with given value
         * */
        setInfo({ ...info, [e.target.name]: e.target.value });
    }

    function radioChange(e) {
        setInfo({ ...info, ["role"]: e.target.id });
    }

    useEffect(() => {
        function start() {
            gapi.client.init({
                clientId: clientID,
                scope: "",
            });
        }
        gapi.load("client:auth2", start);
    });

    useEffect(() => {
        /*
         * you can check validity of email given in this function.
         * */
        console.log(
            info.userNameOrEmail + " " + info.password + " " + info.role
        );
    }, [info]);

    const formStyle = {
        border: "1px solid #ccc",
            padding: "20px",
            borderRadius: "10px",
            width: "30%",
            backgroundColor: "white",
            background: "linear-gradient(to top, #f2f2f2, rgba(242, 242, 242, 0))"
    };

    const gradientStyle = {
        background: 'radial-gradient(circle, #4558D5 0%, rgba(0,0,0,1) 30%, rgba(0,0,0,1) 30%, rgba(0,0,0,1) 30%)',
    };

    const handleSubmit = async (e) => {
        // e.preventDefault();
        try{
           let ret = await signIn(info);
           if(ret === "Login successful"){
               await setPerson({
                   isAuthorized: true,
                   username: info.userNameOrEmail,
                   privilege: info.role,
                   personObj: {},
               });
               console.log(ret)
               navigate("/FAQ");
           } else {
               alert(ret)
           }
       } catch (e) {
           alert("login failed");
       }
        // navigate to admin home or user home
        // based on Role attribute
        // useEffect(
        //     console.log(info)
        // , [])
    };

    return (
        <section className="bg-gradient-to-r from-slate-800 to-gray-900">
            <div className="flex flex-col items-center justify-center px-6 py-2 mx-auto md:h-screen lg:py-0">

                {/*----------------------------------premier league img------------------------------------------*/}
                <img className="object-scale-down w-60 mb-10" src={plLogo} alt="logo"/>
                <div
                    className="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
                    <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
                        <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                            Sign in to your account
                        </h1>

                        {/*-----------------------------------form------------------------------------------*/}
                        <form className="space-y-4 md:space-y-6" action="#">

                            {/*-----------------------------------email------------------------------------------*/}
                            <div>
                                <label htmlFor="email"
                                       className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your
                                    email</label>
                                <input type="email" id="email"
                                       className="bg-gray-50 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                       placeholder="Enter email/username"
                                       name="userNameOrEmail"
                                       value={info.userNameOrEmail}
                                       onChange={inputChange}
                                       required/>
                            </div>

                            {/*-----------------------------------password------------------------------------------*/}
                            <div>
                                <label htmlFor="password"
                                       className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                                <input type="password" id="password"
                                       className="bg-gray-50 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                       placeholder="Password"
                                       name="password"
                                       value={info.password}
                                       onChange={inputChange}
                                       required/>
                            </div>

                            {/*-----------------------------------privilege------------------------------------------*/}
                            <div className="flex flex-wrap justify-center space-x-8">
                                <div className="flex items-center me-4">
                                    <input className="w-4 h-4 text-red-600 bg-gray-100 border-gray-300 focus:ring-red-500"
                                           type="radio" name="formHorizontalRadios"
                                           onChange={radioChange} id={userPrv} required/>
                                    <label htmlFor="red-radio"
                                           className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">User</label>
                                </div>
                                <div className="flex items-center me-4">
                                    <input className="w-4 h-4 text-green-600 bg-gray-100 border-gray-300 focus:ring-green-500"
                                           type="radio" name="formHorizontalRadios"
                                           onChange={radioChange} id={adminPrv} required
                                           />
                                    <label htmlFor="green-radio"
                                           className="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Admin</label>
                                </div>
                            </div>

                            {/*-----------------------------------submit------------------------------------------*/}
                            <button className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4
                                    focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5
                                    text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"
                                    type="button" onClick={handleSubmit}>
                                Sign in
                            </button>
                            <Google/>
                            {/*-----------------------------------signup - forget password------------------------------------------*/}
                            <div className="flex items-center justify-between">
                                <p className="text-sm font-light text-gray-500 dark:text-gray-400">
                                    Don’t have an account yet?
                                    <Link to="/signup"
                                          className="font-medium text-primary-600 hover:underline dark:text-primary-500">
                                        Sign up
                                    </Link>
                                </p>
                                <div className="flex items-center justify-between px-7">
                                    <Link to="/forgetPassword"
                                          className="text-sm font-medium text-white hover:underline dark:text-primary-500">
                                        Forgot password?
                                    </Link>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </section>
    );
};
