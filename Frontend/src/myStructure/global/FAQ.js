import React, {useEffect, useReducer, useState} from "react";
import { json, useNavigate } from "react-router-dom";
import Form from "react-bootstrap/Form";
import {Button, Row} from "react-bootstrap";
import { GetAuthDataFn } from "../wrapper";
import { login } from "../myServices/personAuthorizationService/getData"
import {adminPrv, userPrv} from "../collection";
import './SoccerTable.css';

export const Faq = () => {    
    const [info, setInfo] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/faq/getAll', {method:'GET'})
        .then(result => result.json())
        .then(jsonresult => {
            setInfo(jsonresult)
        })
        .then(console.log("Got FAQ Successfully"))
        .catch(err => console.error(err))
    }, [])
   
    const formStyle =
        {
            border: '1px solid #ccc',
            padding: '20px',
            borderRadius: '10px',
            width: '30%',
            backgroundColor: 'white'
        }
        return (
            <div className="table-container">                    
                <h3>FAQ</h3>
                <table className="soccer-table">
                    <thead>
                        <tr>
                            <th>Question</th>
                            <th>Answer</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        {info.map((item, index) => (
                            <tr key={index}>
                                <td>{item.question}</td>
                                <td>{item.answer}</td>
                                <td>{item.date}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        );
};
           

