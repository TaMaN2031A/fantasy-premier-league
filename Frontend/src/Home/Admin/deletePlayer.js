// import React, {useEffect,  useState} from "react";
// import {  useNavigate } from "react-router-dom";
// import Form from "react-bootstrap/Form";
// import {Button, Row} from "react-bootstrap";
// import { deletePlayerServ } from "../myServices/Player";
//
//
//
// export const DeletePlayer = () => {
//     const [player, setPlayer] = useState({
//         id: 0
//     });
//     function inputChange (e) {
//         /*
//         * update the existing object by creating same obj (info)
//         * but change name attribute with given value
//         * */
//         setPlayer({...player, [e.target.name]: e.target.value})
//     }
//
//
//     const handleSubmit = async ()=> {
//         try {
//             console.log(player.id);
//
//             const result = await deletePlayerServ(parseInt(player.id));
//             console.log(result);
//             // Handle the result as needed
//           } catch (error) {
//             console.error(error.message);
//             // Handle the error as needed
//           }
//     };
//     const formStyle =
//         {
//             border: '1px solid #ccc',
//             padding: '20px',
//             borderRadius: '10px',
//             width: '30%',
//             backgroundColor: 'white'
//         }
//     return (
//         <div style={formStyle}>
//             <Form>
//
//                 {/* ------------------  id part ---------------------- */}
//                 <Form.Group controlId="formId">
//                     <Form.Label>Player Delete</Form.Label>
//                     <Form.Control
//                         type="id"
//                         placeholder="Enter id of player"
//                         name="id"
//                         value={player.id}
//                         onChange={inputChange} required /> <br/>
//                 </Form.Group>
//
//                 <Button
//                     type="button"
//                     variant="dark"
//                     onClick={handleSubmit}>
//                     submit
//                 </Button>
//             </Form>
//         </div>
//
//     )
//
// };