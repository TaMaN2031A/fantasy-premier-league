import React, {useEffect, useReducer, useState} from "react";
import { json, useNavigate } from "react-router-dom";
import { GetAuthDataFn } from "../../wrapper";
import {adminPrv, defaultPersonState, userPrv} from "../../collection";
import './SoccerTable.css';

export const League = ({ getPlayers, getTeams }) => {
    const [playerInfo, setPlayerInfo] = useState(getPlayers ||[]);
    const [teamInfo, setTeamInfo] = useState(getTeams||[]);
    const {person, setPerson}  = GetAuthDataFn();

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
                        <th>Player ID</th>
                        <th>Name</th>
                        <th>Goals</th>
                        <th>Assists</th>
                        <th>Team</th>

                    </tr>
                </thead>
                <tbody>
                    {playerInfo.map((item, index) => (
                        <tr key={index}>
                            <td>{item.id}</td>
                            <td>{item.name}</td>
                            <td>{item.goals}</td>
                            <td>{item.assists}</td>
                            <td>{item.team.name}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};
