import React, {useEffect, useState} from "react";
import Form from "react-bootstrap/Form";
import {Button} from "react-bootstrap";
import { insertPlayerServ } from "../myServices/Player";



export const InsertPlayer = () => {
    const [player, setPlayer] = useState({
        name: "",
        position: "",
        number_in_team:0,
        id_of_team: 0,
    });
    function inputChange (e) {
        /*
        * update the existing object by creating same obj (info)
        * but change name attribute with given value
        * */
        setPlayer({...player, [e.target.name]: e.target.value})
    }

    useEffect(() => {
        /*
        * you can check validity of email given in this function.
        * */
        console.log(player.name + " " + player.position + " " + player.number_in_team + " " + player.id_of_team);
    }, [player]);

    const handleSubmit = async ()=> {

        try {
            const result = await insertPlayerServ(player);
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
                {/* ------------------ player name part ---------------------- */}
                <Form.Group controlId="formBasicPlayerName">
                    <Form.Label>Player Name</Form.Label>
                    <Form.Control
                        type="name"
                        placeholder="Enter Player Name please"
                        name="name"
                        value={player.name}
                        onChange={inputChange}  required /> <br/>
                </Form.Group>

                {/* ------------------ position part ---------------------- */}
                <Form.Group controlId="formBasicPlayerPosition">
                    <Form.Label>Position</Form.Label>
                    <Form.Control
                        type="position"
                        placeholder="Enter Position"
                        name="position"
                        value={player.position}
                        onChange={inputChange} required /> <br/>
                </Form.Group>

                {/* ------------------ t-shirt number of team part ---------------------- */}
                <Form.Group controlId="formBasicTshirtNumber">
                    <Form.Label>T-Shirt Number</Form.Label>
                    <Form.Control
                        type="number_in_team"
                        placeholder="Enter number of T-Shirt"
                        name="number_in_team"
                        value={player.number_in_team}
                        onChange={inputChange} required /> <br/>
                </Form.Group>

                {/* ------------------  id of team part ---------------------- */}
                <Form.Group controlId="formBasicTeamId">
                    <Form.Label>Team</Form.Label>
                    <Form.Control
                        type="id_of_team"
                        placeholder="Enter id of team"
                        name="id_of_team"
                        value={player.id_of_team}
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