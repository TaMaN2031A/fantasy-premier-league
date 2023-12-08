import React, {useEffect, useState} from "react";
import Form from "react-bootstrap/Form";
import {Button} from "react-bootstrap";
import { insertPlayerServ } from "../myServices/Player";
import { insertTeamServ } from "../myServices/Team";



export const InsertTeam = () => {
    const [team, setTeam] = useState({
        name: ""
    });
    function inputChange (e) {
        /*
        * update the existing object by creating same obj (info)
        * but change name attribute with given value
        * */
        setTeam({...team, [e.target.name]: e.target.value})
    }


    const handleSubmit = async ()=> {

        try {
            const result = await insertTeamServ(team.name);
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
                {/* ------------------ Team name part ---------------------- */}
                <Form.Group controlId="formBasicPlayerName">
                    <Form.Label>Team Name</Form.Label>
                    <Form.Control
                        type="name"
                        placeholder="Enter Player Name please"
                        name="name"
                        value={team.name}
                        onChange={inputChange}  required /> <br/>
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