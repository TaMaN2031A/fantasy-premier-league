export const adminPrv = "ADMIN";
export const userPrv = "USER";
export const hostOfBack = "http://localhost:8080";
export const clientID = "157567928601-tpi54p30l3rpsdlmrh7on2fmfup3tpct.apps.googleusercontent.com";

export const defaultPersonState = () => {
    return {
        isAuthorized: false,  /* to check if there is a  */
        username: "",
        privilege: "",
        personObj: {}
    }
}