import { Login } from "../Security/login"
import Signup from "../Security/signup";
import Logout from "../Security/Logout";
import ForgetPassword from "../Security/forgetPassword";
import Error from "../Security/Error";

import {adminPrv, paths, userPrv} from "../collection";

import PickTeam from "../Home/User/Pick Team/PickTeam";
import Transfer from "../Home/User/Transfers/Transfers";
import History from "../Home/User/History/History";
import Groups from "../Home/User/Groups/Groups";

import MatchStatistics from "../Home/Admin/Match Statistics/MatchStatistics";
import Promotion from "../Home/Admin/Promotion/Promotion";

import Home from "../Home/Home Page/Home";
import {League} from "../Home/Common/League Tables/league";
import  Faq  from "../Home/Common/FAQ/FAQ";
import Rule  from "../Home/Common/Rule/Rule";

export const nav = [

    // user pages
    { path:     paths.pickTeam,  name: "Pick Team",     element: <PickTeam />,     status: userPrv  },
    { path:     paths.transfer,  name: "Transfer",     element: <Transfer />,     status: userPrv  },
    { path:     paths.history,  name: "History",     element: <History />,     status: userPrv  },
    { path:     paths.groups,  name: "Groups",     element: <Groups />,     status: userPrv  },

    // admin pages
    { path:     paths.promotion,  name: "Promotion",     element: <Promotion />,     status: adminPrv  },
    { path:     paths.matchStatistics,  name: "Match Statistics",     element: <MatchStatistics />,     status: adminPrv  },

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
    { path:     paths.rule,    name: "Rule",       element: <Rule />,     status: "internal"  }, // getFaq={await fetchFaqData()}
    { path:     paths.home,     name: "Home",       element: <Home />,     status: "global"  },

    // error page
    { path:     paths.notFound,        name: "404",       element: <Error />,     status: "global"  }
]