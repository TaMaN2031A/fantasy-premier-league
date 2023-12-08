import React, {useEffect, useState} from "react";
import Form from "react-bootstrap/Form";
import {Button} from "react-bootstrap";
import { insertPlayerServ } from "../myServices/Player";
import { insertTeamServ } from "../myServices/Team";
import { insertUpcomingMatchServ } from "../myServices/UpcomingMatch";



export const InsertUpcomingMatch = () => {
    const [upcomingMatch, setUpcomingMatch] = useState({
        homeID: 0,
        awayID: 0,
        week: 0,
        stadium : ""
    });
    function inputChange (e) {
        /*
        * update the existing object by creating same obj (info)
        * but change name attribute with given value
        * */
        setUpcomingMatch({...upcomingMatch, [e.target.name]: e.target.value})
    }


    const handleSubmit = async ()=> {

        try {
            const result = await insertUpcomingMatchServ(upcomingMatch);
            console.log(result);
            // Handle the result as needed
          } catch (error) {
            console.error(error.message);
            // Handle the error as needed
          }

    };
    const formStyle =
        {
            border: '1px solid #ccc',
            padding: '20px',
            borderRadius: '10px',
            width: '30%',
            backgroundColor: 'white'
        }
    return (
        <div style={formStyle}>
            <Form>
                {/* ------------------ home id part ---------------------- */}
                <Form.Group controlId="formBasicHomeID">
                    <Form.Label>Home Team ID</Form.Label>
                    <Form.Control
                        type="homeTeamID"
                        placeholder="Enter Home Team ID please"
                        name="homeID"
                        value={upcomingMatch.homeID}
                        onChange={inputChange}  required /> <br/>
                </Form.Group>

                {/* ------------------ away id part ---------------------- */}
                <Form.Group controlId="formBasicAwayID">
                    <Form.Label>Away Team ID</Form.Label>
                    <Form.Control
                        type="name"
                        placeholder="Enter Away Team ID please"
                        name="awayID"
                        value={upcomingMatch.awayID}
                        onChange={inputChange}  required /> <br/>
                </Form.Group>


                {/* ------------------ week number part ---------------------- */}
                <Form.Group controlId="formBasicWeekNum">
                    <Form.Label>Week Number Part</Form.Label>
                    <Form.Control
                        type="name"
                        placeholder="Enter Week Number please"
                        name="week"
                        value={upcomingMatch.week}
                        onChange={inputChange}  required /> <br/>
                </Form.Group>

                {/* ------------------  stadium part ---------------------- */}
                <Form.Group controlId="formBasicStadium">
                    <Form.Label>Stadium</Form.Label>
                    <Form.Control
                        type="name"
                        placeholder="Enter stadium of match"
                        name="stadium"
                        value={upcomingMatch.stadium}
                        onChange={inputChange} required /> <br/>
                </Form.Group>

                <Button
                    type="button"
                    variant="dark"
                    onClick={handleSubmit}>
                    submit
                </Button>
            </Form>
        </div>

    )

};