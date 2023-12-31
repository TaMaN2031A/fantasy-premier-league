import React, {useEffect} from 'react';
import {GetAuthDataFn} from "../Routes/wrapper";
import {useNavigate} from "react-router-dom";
import {defaultPersonState, paths} from "../collection";

function Logout() {
    const {person, setPerson} = GetAuthDataFn();
    setPerson(defaultPersonState());
    const navigate = useNavigate();

    /*
    * a warning for bad setState here.
    * */
    useEffect(() => {
        navigate(paths.login)
    }, [navigate, person])

    console.log("in log out")
    return (
        <></>
    );
}

export default Logout;
