import React from 'react';
import {Link} from "react-router-dom";
import {paths} from "../collection";

function Error(props) {
    return (
        <section className="bg-gradient-to-r from-slate-800 to-gray-900">
            <section className="flex flex-col items-center justify-center px-6 py-2 mx-auto md:h-screen lg:py-0">
                <div className="text-center">
                    <h1 className="mb-4 text-6xl font-semibold text-red-500">404</h1>
                    <p className="mb-4 text-lg text-white">Oops! Looks like you're lost.</p>
                    <div className="animate-bounce">
                        <svg className="mx-auto h-16 w-16 text-red-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                                  d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8"></path>
                        </svg>
                    </div>
                    <p className="mt-4 text-white">Let's get you back <Link to={paths.login} className="text-blue-500">log-in</Link>.</p>
                </div>
            </section>
        </section>
    );
}

export default Error;