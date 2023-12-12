import React, {createContext, useContext, useState} from 'react';
import {RenderRoutes} from "./RenderNavigation";
import {useNavigate} from "react-router-dom";
import {defaultPersonState} from "../collection";


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