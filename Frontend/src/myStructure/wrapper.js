import React, {createContext, useContext, useState} from 'react';
import {RenderMenu, RenderRoutes} from "./Base/RenderNavigation";
import {useNavigate} from "react-router-dom";
import {defaultPersonState} from "./collection";


// file responsibility
/*
* - have the context creation.
* - useAuthGlobalData function.
* - associated operations about Global data.
* */


// needed to be updated...
// when a component does not find a matching Provider above it in the component tree
const AuthContext = createContext();
export const GetAuthDataFn = () => useContext(AuthContext);

function Wrapper() {
    /*
    * firstly define state that
    * have all needed global data.
    * */
    const [person, setPerson] = useState(defaultPersonState());
    const navigate = useNavigate()
    // must be converted into service
    const signup = (dataCollectedObj) => {
        // Make a call to service

        // if request as admin
        //      >> approval small pop up window(in signup page)
        //      >> reject same as reject in login.

        // if request as user
        //      >> approval go to home page(in signup page), set the new person state.
        //      >> reject same as reject in login.
    }

    const logout = async () => {
        await setPerson(defaultPersonState())
        navigate("/")
    }



    return (
        /*
        *
        * */
        <AuthContext.Provider value={{person, setPerson}}>
            <>
                <RenderRoutes />
            </>

        </AuthContext.Provider>
    );
}

export default Wrapper;