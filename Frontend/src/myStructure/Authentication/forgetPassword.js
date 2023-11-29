import React, {useState} from 'react';
import { MDBCollapse, MDBBtn } from 'mdb-react-ui-kit';
import Form from "react-bootstrap/Form";
import {forgetPassword, signUp, updatePassword} from "../myServices/personAuthorizationService/registration";
import {userPrv} from "../collection";
import {useNavigate} from "react-router-dom";


function ForgetPassword() {

    const navigate = useNavigate();

    // first level data required.
    const [isOpen, setIsOpen] = useState(false);
    const formStyle = {
        border: "1px solid #ccc",
        padding: "20px",
        borderRadius: "10px",
        width: "30%",
        backgroundColor: "white",
        background: "linear-gradient(to top, #f2f2f2, rgba(242, 242, 242, 0))"
    };
    const [email, setEmail] = useState("");
    const [isEmailShowDisabled, setEmailShowDisabled] = useState(false);

    // second level data required
    const [reqAddInfo, setReqAddInfo] = useState({
        token: "",
        password: "",
        confirmedPassword: ""
    })
    function inputChangeLvl1(e) {
        setEmail(e.target.value);
    }
    function inputChangeLvl2(e) {
        setReqAddInfo({ ...reqAddInfo, [e.target.name]: e.target.value });
    }
    const toggleOpen = () => setIsOpen(!isOpen);

    async function performOperation() {
        let ret = await forgetPassword({"email": email});
        console.log(ret)
        if(ret === "Mail Sent Successfully") {
            toggleOpen()
            setEmailShowDisabled(true)
        } else {
            alert(ret)
        }
    }

    const updatePasswordSubmit = async () => {
        if(reqAddInfo.password !== reqAddInfo.confirmedPassword) {
            alert("password and confirmed password are not identical");
            return;
        }

        let ret = await updatePassword({email, ...reqAddInfo});
        console.log(ret)
        if(ret === "password update successful") {
            navigate("/login");
        } else {
            alert(ret)
        }
    };

    return (
        <>
            <div style={formStyle}>
                <Form>
                    <Form.Group controlId="email">
                        <Form.Label>Email</Form.Label>
                        <Form.Control
                            type="email"
                            placeholder="Enter email"
                            name="email"
                            value={email}
                            onChange={inputChangeLvl1}
                            disabled={isEmailShowDisabled}/> <br/>
                    </Form.Group>
                    <button
                        type="button"
                        className="btn btn-primary"
                        onClick={performOperation}
                        disabled={isEmailShowDisabled}>
                            enter New password
                    </button>
                </Form>
            </div>

            <div style={formStyle}>
                <MDBCollapse open={isOpen}>
                    <Form>
                        {/* ------------------ token part ---------------------- */}
                        <Form.Group controlId="token">
                            <Form.Label>token</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Enter token"
                                name="token"
                                value={reqAddInfo.token}
                                onChange={inputChangeLvl2}  required /> <br/>
                        </Form.Group>

                        {/* ------------------ password part ---------------------- */}
                        <Form.Group controlId="password">
                            <Form.Label>password</Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="Enter password"
                                name="password"
                                value={reqAddInfo.password}
                                onChange={inputChangeLvl2}  required /> <br/>
                        </Form.Group>

                        {/* ------------------ confirmedPassword part ---------------------- */}
                        <Form.Group controlId="confirmedPassword">
                            <Form.Label>confirmedPassword</Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="Enter confirmedPassword"
                                name="confirmedPassword"
                                value={reqAddInfo.confirmedPassword}
                                onChange={inputChangeLvl2}  required /> <br/>
                        </Form.Group>
                        {/* ------------------ update button part ---------------------- */}

                        <button
                            type="button"
                            className="btn btn-primary"
                            onClick={updatePasswordSubmit}>
                            submit information
                        </button>
                    </Form>
                </MDBCollapse>
            </div>
        </>
    );

}

export default ForgetPassword;