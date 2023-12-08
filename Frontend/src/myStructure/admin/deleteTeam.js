import React, {useEffect,  useState} from "react";
import Form from "react-bootstrap/Form";
import {Button} from "react-bootstrap";
import { deleteTeamServ } from "../myServices/Team";



export const DeleteTeam = () => {
    const [team, setTeam] = useState({
        id: 0
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
            console.log(team.id);

            const result = await deleteTeamServ(parseInt(team.id));
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

                {/* ------------------  id part ---------------------- */}
                <Form.Group controlId="formId">
                    <Form.Label>Team Delete</Form.Label>
                    <Form.Control
                        type="id"
                        placeholder="Enter id of player"
                        name="id"
                        value={team.id}
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