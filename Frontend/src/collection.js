import {nav} from "./Routes/navObjs";
import Card from "./Home/Home Page/Card";

export const adminPrivilege = "ADMIN";
export const userPrivilege = "USER";
export const commonPrivilege = "COMMON";
export const externalPrivilege = "EXTERNAL";
export const internalPrivilege = "INTERNAL";

export const serverHost = "http://localhost:8080";
export const clientID = "157567928601-tpi54p30l3rpsdlmrh7on2fmfup3tpct.apps.googleusercontent.com";

export const paths = {
    login: "/login",
    logout: "/logout",
    signup: "/signup",
    forgetPassword: "/forgetPassword",

    home: "/home",
    league: "/league",
    matches: "/matches",
    faq: "/FAQ",
    rule: "/Rule",

    pickTeam: "/pickTeam",
    transfer: "/transfer",
    history: "/history",
    groups: "/groups",
    players: "/players",
    team: "/teams",
    promotion: "/promotion",
    matchStatistics: "/matchStatistics",
    notFound: "*"
}

export const toastStyle = {style: {background: '#000000'}};


export const defaultPersonState = () => {
    return {
        isAuthorized: false,  /* to check if there is a  */
        username: "",
        privilege: "",
        personObj: {}
    }
}

export const responses = {
    loginSuccessfully: "Login successful",
    wrongCredentials: "Incorrect username or password",
    noUser: "User does not exist",
    dataExist: "credentials already exists",
    errorGeneratedInFront: "Error has Occurred try again",
    tokenDigitsNotFilled: "token digits not filled",
    tokenIsWrong: "token is wrong",
    notEqualPasswords: "passwords are not equal",
    signUpSuccessfully: "Sign up successful",
    MailSentSuccessfully: "Mail Sent Successfully",
    MailSendingFailed: "Email is wrong",
    MailNotValid: "Email is not valid",
    ErrorOccurred: "Error has Occurred try again",
    PassUpdateFailed: "Password Update Failed Try again",
    MaliciousPasswordUpdate: "didn't enter email in first place",
    InvalidToken: "Invalid Token Try again",
    PassUpdateSuccessfully: "password update successful"
}

