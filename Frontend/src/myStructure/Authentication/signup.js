import React, {useState} from 'react';
import logo from "./bg.png";
import Form from "react-bootstrap/Form";
import {useNavigate} from "react-router-dom";
import {GetAuthDataFn} from "../wrapper";
import {formStyle} from "./login";
import {login, signIn} from "../myServices/personAuthorizationService/registration";

function Signup() {
    const navigate = useNavigate();
    const { setPerson } = GetAuthDataFn();

    /*
    * need to check on 2 passwords.
    * */

    const [info, setInfo] = useState({
        firstName: "",
        lastName: "",
        userName: "",
        email: "",
        region: "",
        password: "",
        confirmedPassword: ""
    });

    /*
     * update the existing object by creating same obj (info)
     * but change name attribute with given value
     * */
    function inputChange(e) {
        setInfo({ ...info, [e.target.name]: e.target.value });
    }

    const formStyle = {
        border: "1px solid #ccc",
        padding: "20px",
        borderRadius: "10px",
        width: "30%",
        backgroundColor: "white",
        background: "linear-gradient(to top, #f2f2f2, rgba(242, 242, 242, 0))"

};

    const handleSubmit = async () => {
        try {
            // success
            ///---------------------- must take whole info from request. ------------------------
            let ret = await signIn(info);
            await setPerson({
                isAuthorized: true,
                username: info.emailUsername,
                privilege: info.privilege,
                personObj: {},
            });
            // navigate to admin home or user home
            // based on privilege attribute
            // useEffect(
            //     console.log(info)
            // , [])
            navigate("/");
        } catch (error) {
            // failure(reject in login in service)
            // make an error....
            console.log("error");
        }
    };

    return (
        <>
            <div className='bg-image' id="image" style={{height: "0px"}}>
                <img src={logo} alt="abod"  />
            </div>
            <div className="container">
                <div className="row mt-lg-5">
                    <div className="col-12">
                        <div style={formStyle}>
                            <Form>

                                {/* ------------------ firstName part ---------------------- */}
                                <Form.Group controlId="firstName">
                                    <Form.Label>firstName</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Enter firstName"
                                        name="firstName"
                                        value={info.firstName}
                                        onChange={inputChange}  required /> <br/>
                                </Form.Group>

                                {/* ------------------ lastName part ---------------------- */}
                                <Form.Group controlId="lastName">
                                    <Form.Label>Email lastName</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Enter lastName"
                                        name="lastName"
                                        value={info.lastName}
                                        onChange={inputChange}  required /> <br/>
                                </Form.Group>

                                {/* ------------------ userName part ---------------------- */}
                                <Form.Group controlId="userName">
                                    <Form.Label>Email userName</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Enter userName"
                                        name="userName"
                                        value={info.userName}
                                        onChange={inputChange}  required /> <br/>
                                </Form.Group>

                                {/* ------------------ email part ---------------------- */}
                                <Form.Group controlId="email">
                                    <Form.Label>Email Email</Form.Label>
                                    <Form.Control
                                        type="email"
                                        placeholder="Enter email"
                                        name="email"
                                        value={info.email}
                                        onChange={inputChange}  required /> <br/>
                                </Form.Group>

                                {/* ------------------ region part ---------------------- */}
                                <Form.Group controlId="region">
                                    <Form.Label>Email region</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Enter region"
                                        name="region"
                                        value={info.region}
                                        onChange={inputChange}  required /> <br/>
                                </Form.Group>

                                {/* ------------------ password part ---------------------- */}
                                <Form.Group controlId="formBasicEmail">
                                    <Form.Label>Email password</Form.Label>
                                    <Form.Control
                                        type="password"
                                        placeholder="Enter password"
                                        name="password"
                                        value={info.password}
                                        onChange={inputChange}  required /> <br/>
                                </Form.Group>

                                {/* ------------------ confirmedPassword part ---------------------- */}
                                <Form.Group controlId="formBasicEmail">
                                    <Form.Label>Email password</Form.Label>
                                    <Form.Control
                                        type="password"
                                        placeholder="Enter password"
                                        name="password"
                                        value={info.password}
                                        onChange={inputChange}  required /> <br/>
                                </Form.Group>

                            </Form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Signup;