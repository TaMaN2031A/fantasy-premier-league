import { Login } from "../Authentication/login"
import Signup from "../Authentication/signup";
import Logout from "../Authentication/Logout";
import {adminPrv, paths, userPrv} from "../collection";
import Functionalities from "../admin/functionalities";
import PickTeam from "../user/pickTeam";
import Transfer from "../user/transfer";
import Points from "../user/points";
import FantasyLeagues from "../user/fantasyLeagues";
import ForgetPassword from "../Authentication/forgetPassword";
import Error from "../Authentication/Error";
import Home from "../Home/Home";
import {League} from "../Home/Common/league";
import  Faq  from "../Home/Common/FAQ";


export const nav = [

    // user pages
    { path:     paths.pickTeam,  name: "Pick Team",     element: <PickTeam />,     status: userPrv  },
    { path:     paths.transfer,  name: "Transfer",     element: <Transfer />,     status: userPrv  },
    { path:     paths.points,  name: "History",     element: <Points />,     status: userPrv  },
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
    // { path:     paths.rule,    name: "Rule",       element: <Rule />,     status: "internal"  }, // getFaq={await fetchFaqData()}
    { path:     paths.home,     name: "Home",       element: <Home />,     status: "internal"},

    // error page
    { path:     paths.notFound,        name: "404",       element: <Error />,     status: "global"  }
]