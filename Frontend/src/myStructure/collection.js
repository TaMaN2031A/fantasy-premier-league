export const adminPrv = "ADMIN";
export const userPrv = "USER";
export const hostOfBack = "http://localhost:8080";
export const clientID = "157567928601-tpi54p30l3rpsdlmrh7on2fmfup3tpct.apps.googleusercontent.com";

export const paths = {
    login: "/login",
    logout: "/logout",
    signup: "/signup",
    forgetPassword: "/forgetPassword",

    home: "/home",
    league: "/league",
    faq: "/FAQ",
    rule: "/rule",

    pickTeam: "/pickTeam",
    transfer: "/transfer",
    points: "/points",
    fantasyLeagues: "/fantasyLeagues",

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