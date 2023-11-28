import React, {useEffect} from 'react';
import {defaultPersonState, GetAuthDataFn} from "../wrapper";
import {useNavigate} from "react-router-dom";

function Logout() {
    const { person, setPerson } = GetAuthDataFn();
    setPerson(defaultPersonState());
    const navigate = useNavigate();

    useEffect(() => {
        navigate("/")
    }, [navigate, person])
    
    console.log("in log out")
    return (
        <></>
    );
}

export default Logout;