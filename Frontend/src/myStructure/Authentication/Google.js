import { GoogleLogin } from "react-google-login";
import {clientID, paths, toastStyle} from "../collection";
import "./Google.css";
import {adminPrv, userPrv} from "../collection";
import {googleAuthSignIn, signIn} from "../myServices/personAuthorizationService/registration";
import {useNavigate} from "react-router-dom";
import {GetAuthDataFn} from "../wrapper";
import {toast, ToastContainer} from "react-toastify";
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
            toast.success("Login Successful!", toastStyle);
        } catch (e) {
            toast.error("Login Failed!", toastStyle);
            return;
        }
        await setPerson({
            isAuthorized: true,
            username: info.userNameOrEmail,
            privilege: info.Role,
            personObj: {},
        });

        navigate(paths.home)
    };

    const onFailure = (response) => {
        console.log("Login Failed!", response);
        toast.error("Login Failed!", toastStyle);
    };
    return (
        <div className="container">
            <ToastContainer/>

            <GoogleLogin
                className="custom-google-button"
                clientId={clientID}
                buttonText="Sign in"
                onSuccess={onSuccess}
                onFailure={onFailure}
                cookiePolicy={"single_host_origin"}
            />
        </div>
    );
};


export default Google;
