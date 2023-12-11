import { Login } from "../Authentication/login"
import Signup from "../Authentication/signup";
import Logout from "../Authentication/Logout";
import {adminPrv, paths, userPrv} from "../collection";
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
import Error from "../Authentication/Error";
import { InsertPlayer } from "../admin/insertPlayer";
import { DeletePlayer } from "../admin/deletePlayer";
import { fetchFaqData } from "../myServices/Faq_Rule";
import { fetchPlayersData } from "../myServices/Player";
import { fetchTeamsData } from "../myServices/Team";
import { InsertTeam } from "../admin/insertTeam";
import { DeleteTeam } from "../admin/deleteTeam";
import { InsertUpcomingMatch } from "../admin/insertUpcomingMatch";
import { DeleteUpcomingMatch } from "../admin/deleteUpcomingMatchs";
import Home from "../Home/Home";

/*
* faq-rule >> remove global
* default page >> login >> success >> home
* functionality req >> link its page.
* default router
* refactoring code path must be a variable.
* more than one request and default requested data to when we need it.
* regions effect
* */

export const nav = [

    // user pages
    { path:     paths.pickTeam,  name: "PickTeam",     element: <PickTeam />,     status: userPrv  },
    { path:     paths.transfer,  name: "Transfer",     element: <Transfer />,     status: userPrv  },
    { path:     paths.points,  name: "Points",     element: <Points />,     status: userPrv  },
    { path:     paths.fantasyLeagues,  name: "fantasyLeagues",     element: <FantasyLeagues />,     status: userPrv  },
    // { path:     "/userAccount",  name: "UserAccount",     element: <UserAccount />,     status: userPrv  },

    // admin pages ** need some edit for making start page same as windows settings.
    // { path:     "/adminAccount",  name: "AdminAccount",     element: <AdminAccount />,     status: adminPrv  },
    { path:     "/functionalities",  name: "Functionalities",     element: <Functionalities />,     status: adminPrv  },
    // { path:     "/insertPlayer",  name: "Insert Player",     element: <InsertPlayer />,     status: adminPrv  },
    // { path:     "/deletePlayer",  name: "Delete Player",     element: <DeletePlayer />,     status: adminPrv  },
    // { path:     "/insertTeam",  name: "Insert Team",     element: < InsertTeam/>,     status: adminPrv  },
    // { path:     "/deleteTeam",  name: "Delete Team",     element: < DeleteTeam/>,     status: adminPrv  },
    // { path:     "/insertUpcomingMatch",  name: "Insert Upcoming Match",     element: < InsertUpcomingMatch/>,     status: adminPrv  },
    // { path:     "/deleteUpcomingTeam",  name: "Delete Upcoming Team",     element: < DeleteUpcomingMatch/>,     status: adminPrv  },

    // handled by condition with logout (person state update).
    { path:     paths.login,    name: "Login",       element: <Login />,    status: "external"  },
    { path:     paths.signup,    name: "Signup",     element: <Signup />,    status: "external"  },
    { path:     paths.forgetPassword, name: "ForgetPass", element: <ForgetPassword />, status: "external" },
    { path:     paths.logout,    name: "Logout",     element: <Logout />,    status: "internal"  },
    { path:     paths.league,         name: "League",        element: <League />,     status: "internal"  }, // getPlayers={await fetchPlayersData()} getTeams={await fetchTeamsData()}
    { path:     paths.faq,    name: "FAQ",       element: <Faq />,     status: "internal"  }, // getFaq={await fetchFaqData()}
    { path:     paths.home,     name: "Home",       element: <Home />,     status: "internal"},

    // error page
    { path:     paths.notFound,        name: "404",       element: <Error />,     status: "global"  }
]


// log out you need to make the page in external page
// >> rerender with other menu that isAuthorized = false.
//