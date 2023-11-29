import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Form from "react-bootstrap/Form";
import { Button, Row } from "react-bootstrap";
import { GetAuthDataFn } from "../wrapper";
import {login, signIn} from "../myServices/personAuthorizationService/registration";
import { adminPrv, userPrv } from "../collection";
import  Google  from "./Google";
import { gapi } from "gapi-script";
import { clientID } from "../collection";
import logo from './bg.png';


export const Login = () => {
    const navigate = useNavigate();
    const { setPerson } = GetAuthDataFn();

    // must be converted into service

    const [info, setInfo] = useState({
        emailUsername: "",
        password: "",
        privilege: "",
    });
    function inputChange(e) {
        /*
         * update the existing object by creating same obj (info)
         * but change name attribute with given value
         * */
        setInfo({ ...info, [e.target.name]: e.target.value });
    }
    function radioChange(e) {
        setInfo({ ...info, ["privilege"]: e.target.id });
    }

    useEffect(() => {
        function start() {
            gapi.client.init({
                clientId: clientID,
                scope: "",
            });
        }
        gapi.load("client:auth2", start);
    });

    useEffect(() => {
        /*
         * you can check validity of email given in this function.
         * */
        console.log(
            info.emailUsername + " " + info.password + " " + info.privilege
        );
    }, [info]);

    const formStyle = {
        border: "1px solid #ccc",
            padding: "20px",
            borderRadius: "10px",
            width: "30%",
            backgroundColor: "white",
    };

    const handleSubmit = async () => {
        try {
            // success
            ///---------------------- must take whole info from request. ------------------------
            let ret = await signIn(info);
            console.log(ret)
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
                                {/* ------------------ username part ---------------------- */}
                                <Form.Group controlId="formBasicEmail">
                                    <Form.Label>Email address/username</Form.Label>
                                    <Form.Control
                                        type="email"
                                        placeholder="Enter email/username"
                                        name="emailUsername"
                                        value={info.emailUsername}
                                        onChange={inputChange}  required /> <br/>
                                </Form.Group>

                                {/* ------------------ password part ---------------------- */}
                                <Form.Group controlId="formBasicPassword">
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control
                                        type="password"
                                        placeholder="Password"
                                        name="password"
                                        value={info.password}
                                        onChange={inputChange} required /> <br/>
                                </Form.Group>

                                {/* ------------------ privileges part ---------------------- */}
                                <Form.Group as={Row}>
                                    <Form.Label>
                                        your privilege
                                    </Form.Label> <br/>
                                    <div className="col-5">
                                        <Form.Check
                                            type="radio"
                                            label="User"
                                            name="formHorizontalRadios"
                                            onChange={radioChange}
                                            id={userPrv} required
                                        />
                                    </div>
                                    <div className="col-5">
                                        <Form.Check
                                            type="radio"
                                            label="Admin"
                                            name="formHorizontalRadios"
                                            onChange={radioChange}
                                            id={adminPrv} required
                                        />
                                    </div>
                                </Form.Group>
                                <Button
                                    type="submit"
                                    variant="dark"
                                    onClick={handleSubmit}>
                                    submit
                                </Button>
                                <Google/>
                            </Form>
                        </div>
                    </div>
                </div>
            </div>
        </>

    );
};

function Background() {
    let backgroundImageUrl = "public/logo512.png"
    let styleImgObj = {
        backgroundImage: `url(${backgroundImageUrl})`,
        height: "100%",
        weight: "100%"
    }
    return (

        <div className="bg-i" style={styleImgObj}>
            <img src='https://drive.google.com/file/d/1egio5vRvtGuy3hg7agMh9R-pwJuer9-u/view?usp=sharing' className='img-fluid' alt="abod" />
        </div>


//     <div class="container" *ngIf="login" id="loginContainer">
//         <div class="row border border-secondary" id="allDataLogin">
//         <div class="col" id="formPart">
//         <form  id="formPocket">
//
//         <!-- hello -->
//         <div form-group class="row" id="helloLabel">
//         <div class="col mb-3 p-2 text-center">
//         <span class="fs-1" > Hello! </span>
// </div>
// </div>
//
//     <!-- register -->
//     <div form-group class="row g-0">
//         <div class="col m-3 p-2 text-center" style="border-radius: 20px;">
//             <span class="fs-4" > Use your Account </span>
//         </div>
//     </div>
//
//     <!-- email -->
//     <div form-group class="row g-0">
//         <div class="col mb-3 mx-3" >
//             <label for="" class="form-label"  >Email address</label>
//             <input type="text" class="form-control" placeholder="enter email address" #email (blur)="setEmail(email.value)" >
//         </div>
//     </div>
//
//     <!-- password -->
//     <div form-group class="row g-0">
//         <div class="col mb-3 mx-3" >
//             <label for="" class="form-label"  >password</label>
//             <input type="password" class="form-control" placeholder="enter password" #password (blur)="setPass(password.value)">
//         </div>
//     </div>
//
//     <!-- switch -->
//     <div form-group class="row g-0 m-3 text-center">
//         <a href="" (click)="login = false"> go to sign up</a>
// </div>
//
//     <div form-group class="row g-0 text-center" id="logInBtn">
//         <button class="btn mt-3 btn-primary" (click)="signInFn()">Log in</button>
// </div>
//
// </form>
// </div>
// </div>
// </div>
    );
}