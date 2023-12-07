import { Login } from "../Authentication/login"
import Signup from "../Authentication/signup";
import Logout from "../Authentication/Logout";
import {adminPrv, userPrv} from "../collection";
import { League } from "../global/league";
import Functionalities from "../admin/functionalities";
import Faq from "../global/FAQ";
import PickTeam from "../user/pickTeam";
import Transfer from "../user/transfer";
import Points from "../user/points";
import FantasyLeagues from "../user/fantasyLeagues";
import UserAccount from "../user/userAccount";
import AdminAccount from "../admin/adminAccount";
import ForgetPassword from "../Authentication/forgetPassword";
import { InsertPlayer } from "../admin/insertPlayer";
import { DeletePlayer } from "../admin/deletePlayer";
import { fetchFaqData } from "../myServices/Faq_Rule";
import { fetchPlayersData } from "../myServices/Player";
import { fetchTeamsData } from "../myServices/Team";
import { InsertTeam } from "../admin/insertTeam";
import { DeleteTeam } from "../admin/deleteTeam";
import { InsertUpcomingMatch } from "../admin/insertUpcomingMatch";
import { DeleteUpcomingMatch } from "../admin/deleteUpcomingMatchs";


export const nav = [
    { path:     "/",         name: "league",        element: <League />,     status: "global"  },
    { path:     "/FAQ",    name: "FAQ",       element: <Faq faqData={await fetchFaqData()} />,     status: "global"  },

    // user pages
    { path:     "/pickTeam",  name: "PickTeam",     element: <PickTeam />,     status: userPrv  },
    { path:     "/transfer",  name: "Transfer",     element: <Transfer />,     status: userPrv  },
    { path:     "/points",  name: "Points",     element: <Points />,     status: userPrv  },
    { path:     "/fantasyLeagues",  name: "fantasyLeagues",     element: <FantasyLeagues />,     status: userPrv  },
    { path:     "/userAccount",  name: "UserAccount",     element: <UserAccount />,     status: userPrv  },

    // admin pages ** need some edit for making start page same as windows settings.
    { path:     "/adminAccount",  name: "AdminAccount",     element: <AdminAccount />,     status: adminPrv  },
    { path:     "/functionalities",  name: "Functionalities",     element: <Functionalities />,     status: adminPrv  },
    { path:     "/insertPlayer",  name: "Insert Player",     element: <InsertPlayer />,     status: adminPrv  },
    { path:     "/deletePlayer",  name: "Delete Player",     element: <DeletePlayer />,     status: adminPrv  },
    { path:     "/insertTeam",  name: "Insert Team",     element: < InsertTeam/>,     status: adminPrv  },
    { path:     "/deleteTeam",  name: "Delete Team",     element: < DeleteTeam/>,     status: adminPrv  },
    { path:     "/insertUpcomingMatch",  name: "Insert Upcoming Match",     element: < InsertUpcomingMatch/>,     status: adminPrv  },
    { path:     "/deleteUpcomingTeam",  name: "Delete Upcoming Team",     element: < DeleteUpcomingMatch/>,     status: adminPrv  },

    // handled by condition with logout (person state update).
    { path:     "/login",    name: "Login",       element: <Login />,    status: "external"  },
    { path:     "/signup",    name: "Signup",     element: <Signup />,    status: "external"  },
    { path:     "/forgetPassword", name: "ForgetPass", element: <ForgetPassword />, status: "external" },
    { path:     "/logout",    name: "Logout",     element: <Logout />,    status: "internal"  }
]


// log out you need to make the page in external page
// >> rerender with other menu that isAuthorized = false.
//