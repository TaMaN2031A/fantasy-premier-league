import React, {useEffect, useState} from "react";
import './SoccerTable.css';
import { fetchPlayersData, fetchTeamsData } from "../myServices/Faq_Rule/getFaqRuleData";

export const League = () => {    
    const [playerInfo, setPlayerInfo] = useState([]);
    const [teamInfo, setTeamInfo] = useState([]);
    useEffect(() => {
        const fetchPlayerData = async () => {
            try {
                const result = await fetchPlayersData();
                setPlayerInfo(result);
                console.log("Got Players Successfully");
            } catch (err) {
                console.error(err);
            } 
        };
        fetchPlayerData();
    }, []);

    useEffect(() => {
        const fetchTeamData = async () => {
            try {
                const result = await fetchTeamsData();
                setTeamInfo(result);
                console.log("Got Teams Successfully");
            } catch (err) {
                console.error(err);
            } 
        };

        fetchTeamData();
    }, []);

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
                            <td>{item.team.name}</td>
                            <td>{item.number_in_team}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};