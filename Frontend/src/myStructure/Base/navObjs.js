import { Login } from "../Authentication/login"
import Signup from "../Authentication/signup";
import Logout from "../Authentication/Logout";
import {adminPrv, userPrv} from "../collection";
import League from "../global/league";
import Functionalities from "../admin/functionalities";
import FAQ from "../global/FAQ";
import PickTeam from "../user/pickTeam";
import Transfer from "../user/transfer";
import Points from "../user/points";
import FantasyLeagues from "../user/fantasyLeagues";
import UserAccount from "../user/userAccount";
import AdminAccount from "../admin/adminAccount";

export const nav = [
    { path:     "/",         name: "league",        element: <League />,     status: "global"  },
    { path:     "/FAQ",    name: "FAQ",       element: <FAQ />,     status: "global"  },

    // user pages
    { path:     "/pickTeam",  name: "PickTeam",     element: <PickTeam />,     status: userPrv  },
    { path:     "/transfer",  name: "Transfer",     element: <Transfer />,     status: userPrv  },
    { path:     "/points",  name: "Points",     element: <Points />,     status: userPrv  },
    { path:     "/fantasyLeagues",  name: "fantasyLeagues",     element: <FantasyLeagues />,     status: userPrv  },
    { path:     "/userAccount",  name: "UserAccount",     element: <UserAccount />,     status: userPrv  },

    // admin pages ** need some edit for making start page same as windows settings.
    { path:     "/adminAccount",  name: "AdminAccount",     element: <AdminAccount />,     status: adminPrv  },
    { path:     "/functionalities",  name: "Functionalities",     element: <Functionalities />,     status: adminPrv  },

    // handled by condition with logout (person state update).
    { path:     "/login",    name: "Login",       element: <Login />,    status: "external"  },
    { path:     "/signup",    name: "Signup",     element: <Signup />,    status: "external"  },
    { path:     "/logout",    name: "Logout",     element: <Logout />,    status: "internal"  }
]


// log out you need to make the page in external page
// >> rerender with other menu that isAuthorized = false.
//