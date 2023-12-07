import React, {useEffect,  useState} from "react";
import Form from "react-bootstrap/Form";
import {Button} from "react-bootstrap";
import {deleteUpcomingMatchServ} from "../myServices/UpcomingMatch"

export const DeleteUpcomingMatch = () => {
    const [upcomingMatch, setUpcomingMatch] = useState({
        id: 0
    });
    function inputChange (e) {
        /*
        * update the existing object by creating same obj (info)
        * but change name attribute with given value
        * */
        setUpcomingMatch({...upcomingMatch, [e.target.name]: e.target.value})
    }

    useEffect(() => {
        /*
        * you can check validity of email given in this function.
        * */
        console.log(upcomingMatch.id);
    }, [upcomingMatch]);

    const handleSubmit = async ()=> {
        try {
            console.log(upcomingMatch.id);

            const result = await deleteUpcomingMatchServ(parseInt(upcomingMatch.id));
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
                    <Form.Label>Upcoming Delete</Form.Label>
                    <Form.Control
                        type="id"
                        placeholder="Enter id of upcoming match"
                        name="id"
                        value={upcomingMatch.id}
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