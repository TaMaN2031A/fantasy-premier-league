import React, {useEffect, useReducer, useState} from "react";
import { json, useNavigate } from "react-router-dom";
import Form from "react-bootstrap/Form";
import {Button, Row} from "react-bootstrap";
import { GetAuthDataFn } from "../wrapper";
import { login } from "../myServices/personAuthorizationService/getData"
import {adminPrv, userPrv} from "../collection";
import './SoccerTable.css';

export const League = () => {    
    const [playerInfo, setPlayerInfo] = useState([]);
    const [teamInfo, setTeamInfo] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/team/getAll', {method:'GET'})
        .then(result => result.json())
        .then(jsonresult => {
            setTeamInfo(jsonresult)
        })
        .then(console.log("Got Teams Successfully"))
        .catch(err => console.error(err))
    }, [])
    useEffect(() => {
        fetch('http://localhost:8080/player/getAll', {method:'GET'})
        .then(result => result.json())
        .then(jsonresult => {
            setPlayerInfo(jsonresult)
        })
        .then(console.log("Got Players Successfully"))
        .catch(err => console.error(err))
    }, [])
    const formStyle =
        {
            width: '30%',
            backgroundColor: 'white'
        }
        return (
            <div className="table-container">
                <h3>Teams</h3>
                <table className="soccer-table">
                    <thead>
                        <tr>
                            <th>Team ID</th>
                            <th>Team</th>
                            <th>Points</th>
                            <th>Goals For</th>
                            <th>Goals Conceded</th>
                            <th>Goal Difference</th>
                        </tr>
                    </thead>
                    <tbody>
                        {teamInfo.map((item, index) => (
                            <tr key={index}>
                                <td>{item.id}</td>
                                <td>{item.name}</td>
                                <td>{item.points}</td>
                                <td>{item.goals_for}</td>
                                <td>{item.goals_conceded}</td>
                                <td>{item.goals_difference}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
                <h3>Players</h3>
                <table className="soccer-table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Goals</th>
                            <th>Assists</th>
                            <th>Team</th>
                            <th>T-shirt Number</th>
                        </tr>
                    </thead>
                    <tbody>
                        {playerInfo.map((item, index) => (
                            <tr key={index}>
                                <td>{item.name}</td>
                                <td>{item.goals}</td>
                                <td>{item.assists}</td>
                                <td>{item.team_id.name}</td>
                                <td>{item.number_in_team}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        );
};