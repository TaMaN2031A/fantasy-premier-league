import { GoogleLogin } from "react-google-login";
import { clientID } from "../collection";
import "./Google.css";

const Google = () => {
  const onSuccess = (response) => {
    console.log("Login successful", response);
    // connect with back here.
  };

  const onFailure = (response) => {
    console.log("Login Failed!", response);
  };
  return (
    <GoogleLogin
      className="custom-google-button"
      clientId={clientID}
      buttonText="Sign in"
      onSuccess={onSuccess}
      onFailure={onFailure}
      cookiePolicy={"single_host_origin"}
    />
  );
};

export default Google;
