import { GoogleLogin } from "react-google-login";
import { clientID } from "../collection";
import "./Google.css";
import {adminPrv, userPrv} from "../collection";
import {googleAuthSignIn, signIn} from "../myServices/personAuthorizationService/registration";
import {useNavigate} from "react-router-dom";
import {GetAuthDataFn} from "../wrapper";
const Google = () => {

    const navigate = useNavigate();
    const { setPerson } = GetAuthDataFn();

    /*
    * ended session with google with a response
    * */
    const onSuccess = async (response) => {
        console.log("Login successful", response);
        let res = response.profileObj;
        let info = {
            "email": res.email,
            "firstName": res.givenName,
            "lastName": res.familyName,
            "Role": userPrv
        }
        try{
            let ret = await googleAuthSignIn(info);
            console.log(ret)
            alert(ret);
        } catch (e) {
            alert("login failed");
            return;
        }
        await setPerson({
            isAuthorized: true,
            username: info.userNameOrEmail,
            privilege: info.Role,
            personObj: {},
        });

        navigate("/")
    };

    const onFailure = (response) => {
        console.log("Login Failed!", response);
        alert("Login Failed!")
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
