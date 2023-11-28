import React, { useEffect, useReducer, useState } from "react";
import { useNavigate } from "react-router-dom";
import Form from "react-bootstrap/Form";
import { Button, Row } from "react-bootstrap";
import { GetAuthDataFn } from "../wrapper";
import { login } from "../myServices/personAuthorizationService/getData";
import { adminPrv, userPrv } from "../collection";
import  Google  from "./Google";
import { gapi } from "gapi-script";
import { clientID } from "../collection";

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

  const handleSubmit = async () => {
    try {
      // success
      ///---------------------- must take whole info from request. ------------------------
      await login(info.emailUsername, info.password);
      await setPerson({
        isAuthorized: true,
        username: info.emailUsername,
        privilege: info.privilege,
        personObj: {},
      });
      // navigate to admin home or user home
      // based on privilege attribute
      navigate("/");
    } catch (error) {
      // failure(reject in login in service)
      // make an error....
      console.log("error");
    }
  };

  const formStyle = {
    border: "1px solid #ccc",
    padding: "20px",
    borderRadius: "10px",
    width: "30%",
    backgroundColor: "white",
  };

  return (
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
            onChange={inputChange}
            required
          />{" "}
          <br />
        </Form.Group>

        {/* ------------------ password part ---------------------- */}
        <Form.Group controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            name="password"
            value={info.password}
            onChange={inputChange}
            required
          />{" "}
          <br />
        </Form.Group>

        {/* ------------------ privileges part ---------------------- */}
        <Form.Group as={Row}>
          <Form.Label>your privilege</Form.Label> <br />
          <div className="col-5">
            <Form.Check
              type="radio"
              label="User"
              name="formHorizontalRadios"
              onChange={radioChange}
              id={userPrv}
              required
            />
          </div>
          <div className="col-5">
            <Form.Check
              type="radio"
              label="Admin"
              name="formHorizontalRadios"
              onChange={radioChange}
              id={adminPrv}
              required
            />
          </div>
        </Form.Group>
        <Button type="submit" variant="dark" onClick={handleSubmit}>
          submit
        </Button>
        <Google />
      </Form>
    </div>
  );
};
