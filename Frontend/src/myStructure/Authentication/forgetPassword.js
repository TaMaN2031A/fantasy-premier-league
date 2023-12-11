import React, {useEffect, useState} from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


import {forgetPassword, updatePassword} from "../myServices/personAuthorizationService/registration";
import {useNavigate} from "react-router-dom";
import plLogo from "./logo.png";
import {paths, toastStyle} from "../collection";
function ForgetPassword() {

    /*
    * used variables and states.
    * */
    // handle format
    const formArray = [1, 2, 3];
    const [step, setStep] = useState(formArray[0])
    const navigate = useNavigate();


    // handle got data
    const [email, setEmail] = useState('');
    const [token, setToken] = useState(['', '', '', '', '', '']);
    const [passwords, setPasswords] = useState({first: '', second: ''});

    /*
    * needed functions
    * */

    function updateEmail(e) {
        setEmail(e.target.value);
    }

    function updateDigit(e) {
        let {name, value} = e.target;
        name = parseInt(name);
        const tokenCopy = [...token];
        tokenCopy[name] = value;
        console.log(name)
        setToken(tokenCopy);
    }

    function updatePasswords(e) {
        setPasswords({...passwords, [e.target.name]: e.target.value});
    }

    async function goToPuttingToken () {
        let ret = await forgetPassword({"email": email});
        console.log(ret)
        if(ret === "Mail Sent Successfully") {
            toast.success('Mail Sent Successfully', toastStyle);
            setStep(step + 1)
        } else {
            toast.error('Entered email is not registered or not valid', toastStyle);
        }
    }

    function goToUpdatingPassword() {
        let allExists = true;
        for(let i = 0; i < token.length; i++) {
            if(token[i] === '') {
                allExists = false;
                break;
            }
        }
        if(allExists) {
            setStep(step + 1)
        } else {
            toast.error('Please fill up all input field', toastStyle);
        }
    }

    async function goToBackForSaving() {
        if (passwords.first !== passwords.second) {
            toast.error('password and confirmed password are not identical', toastStyle);
            return;
        }
        let ret = await updatePassword({
            "email": email,
            "token": token.join(''),
            "password": passwords.first
        });
        console.log(ret)
        if (ret === "password update successful") {
            toast.success('password update successful', toastStyle);
            setTimeout(() => {
                navigate(paths.login);
            }, 2000);
        } else {
            toast.error('Entered token is not valid', toastStyle);
            setStep(step - 1);
        }
    }

    useEffect((e) => {
        console.log(token)
    }, [token])

    return (
        <div
            className="flex-col w-screen h-screen bg-slate-300 flex justify-center items-center bg-gradient-to-r from-slate-800 to-gray-900">
            <img className="object-scale-down w-60 mb-10" src={plLogo} alt="logo"/>

            <ToastContainer/>
            <div
                className="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
                <div className="p-6 space-y-4 md:space-y-6 sm:p-8">

                    <div className='flex justify-center items-center'>
                        {
                            formArray.map((v, i) => <>
                                <div
                                    className={`w-[35px] my-3 text-white rounded-full ${step - 1 === i || step - 1 === i + 1 || step === formArray.length ? 'bg-blue-500' : 'bg-slate-400'} h-[35px] flex justify-center items-center`}>
                                    {v}
                                </div>
                                {
                                    i !== formArray.length - 1 && <div
                                        className={`w-[85px] h-[2px] ${step === i + 2 || step === formArray.length ? 'bg-blue-500' : 'bg-slate-400'}`}></div>
                                }
                            </>)
                        }
                    </div>
                    {
                        step === 1 && <div>
                            {/*-----------------------------------email------------------------------------------*/}
                            <h1 className=" text-center justify-center mb-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                                Enter your email
                            </h1>
                            <div>
                                <label htmlFor="email"
                                       className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                                    Your email
                                </label>
                                <input
                                    className="bg-gray-50 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600
                                    block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white
                                    dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    type="email" id="email"
                                    placeholder="Enter email/username"
                                    name="userNameOrEmail"
                                    value={email}
                                    onChange={updateEmail}
                                    required/>
                            </div>
                            <div className='mt-4 flex justify-center items-center'>
                                <button onClick={goToPuttingToken}
                                        className='px-3 py-2 text-lg rounded-md w-full text-white bg-blue-500'>Next
                                </button>
                            </div>
                        </div>
                    }

                    {
                        step === 2 && <div>
                            {/*-----------------------------------token------------------------------------------*/}
                            <h1 className="text-center justify-center mb-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                                Check your email for token
                            </h1>
                            <div className="flex items-center justify-center gap-4">
                                {
                                    token.map((v, key) => {
                                        return <input className="w-10 h-10 border rounded-lg p-1 text-center mx-auto hover:border-blue-200 focus:outline-none
                                            focus:ring focus:ring-blue-400 placeholder:font-medium font-bold text-md-center bg-gray-50 text-gray-900
                                            dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
                                                      type="text" placeholder="-" maxLength="1" min="1" minLength="1"
                                                      max="1"
                                                      id={"verify" + key} onChange={updateDigit} name={key + ''}
                                                      value={token[key]} required/>
                                    })
                                }
                            </div>
                            <div className='mt-4 flex justify-center items-center'>
                                <button onClick={goToUpdatingPassword}
                                        className='px-3 py-2 text-lg rounded-md w-full text-white bg-blue-500'>Next
                                </button>
                            </div>
                        </div>
                    }

                    {
                        step === 3 && <div>
                            {/*-----------------------------------password------------------------------------------*/}
                            <div>
                                <h1 className=" mb-2 text-center justify-center mb-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                                    Update your password
                                </h1>
                                <label htmlFor="password"
                                       className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                                <input
                                    className="mb-2 bg-gray-50 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    type="password"
                                    placeholder="Enter password"
                                    name="first"
                                    value={passwords.first}
                                    onChange={updatePasswords} required/>
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
                                    name="second"
                                    value={passwords.second}
                                    onChange={updatePasswords} required/>
                            </div>
                            <div className='mt-4 flex justify-center items-center'>
                                <button onClick={goToBackForSaving}
                                        className='px-3 py-2 text-lg rounded-md w-full text-white bg-blue-500'>
                                    submit
                                </button>
                            </div>
                        </div>
                    }

                </div>
            </div>
        </div>
    );
}

export default ForgetPassword;