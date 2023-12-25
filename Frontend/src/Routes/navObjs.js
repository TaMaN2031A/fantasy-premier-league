import { Login } from "../Security/login"
import Signup from "../Security/signup";
import Logout from "../Security/Logout";
import ForgetPassword from "../Security/forgetPassword";

import {
    adminPrivilege,
    commonPrivilege,
    externalPrivilege,
    internalPrivilege,
    paths,
    userPrivilege
} from "../collection";

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
import Matches from "../Home/Common/Matches/Matches";
import Players from "../Home/Common/Players/Players";
import Teams from "../Home/Common/Teams/Teams";

import Error from "../Security/Error";
import UserGroups from "../Home/User/Groups/UserGroups";
import JoinGroups from "../Home/User/Groups/JoinGroups";

export const nav = [

     // security pages
     {
        path: paths.login,
        Title: "Login",
        element: <Login />,
        status: externalPrivilege
    },
    {
        path: paths.signup,
        Title: "Signup",
        element: <Signup />,
        status: externalPrivilege
    },
    {
        path: paths.forgetPassword,
        Title: "ForgetPass",
        element: <ForgetPassword />,
        status: externalPrivilege
    },
    {
        path: paths.logout,
        Title: "Logout",
        element: <Logout />,
        status: internalPrivilege
    },

    // error page
    {
        path: paths.notFound,
        Title: "404",
        element: <Error />,
        status: "global"
    },

    // Common Pages
    {
        path: paths.home,
        Title: "Home",
        description: "Welcome to the official Fantasy League website.",
        element: <Home />,
        status: internalPrivilege
    },
    {
        path: paths.faq,
        Title: "FAQ",
        description: "Answers to common queries, simplified for convenience.",
        element: <Faq />,
        status: commonPrivilege
    },
    {
        path: paths.rule,
        Title: "Rule",
        description: "Guidelines and regulations for a fair and enjoyable experience.",
        element: <Rule />,
        status: commonPrivilege
    },
    {
        path: paths.league,
        Title: "League Tables",
        description: "Track rankings and standings across different leagues and divisions.",
        element: <League />,
        status: commonPrivilege
    },
    {
        path: paths.matches,
        Title: "League Matches",
        description: "Upcoming, ongoing, and past game schedules and results.",
        element: <Matches />,
        status: commonPrivilege
    },
    {
        path: paths.team,
        Title: "Teams",
        description: "Information about various sports teams and their compositions.",
        element: <Teams />,
        status: commonPrivilege
    },
    {
        path: paths.players,
        Title: "Players",
        description: "Profiles and stats of individual athletes or team members.",
        element: <Players />,
        status: commonPrivilege
    },

    // user pages
    {
        path: paths.pickTeam,
        Title: "Pick Team",
        description: "Craft your winning roster with top players for your fantasy league.",
        element: <PickTeam />,
        status: userPrivilege
    },
    {
        path: paths.transfer,
        Title: "Transfer",
        description: "Manage player trades, acquisitions, and team adjustments for strategic gameplay.",
        element: <Transfer />,
        status: userPrivilege
    },
    {
        path: paths.history,
        Title: "History",
        description: "Review past performances, scores, and achievements in your fantasy games.",
        element: <History />,
        status: userPrivilege
    },
    {
        path: paths.groups,
        Title: "Groups",
        description: "Engage with fellow enthusiasts, form leagues, and join competitive groups.",
        element: <Groups />,
        status: userPrivilege
    },
    {
        path: paths.userGroups,
        Title: "User Groups",
        description: "View, manage user groups and create new group.",
        element: <UserGroups />,
        status: userPrivilege
    },
    {
        path: paths.joinGroups,
        Title: "Join Groups",
        description: "View and join public and private groups.",
        element: <JoinGroups />,
        status: userPrivilege
    },
    
    // admin pages
    {
        path:     paths.promotion,
        Title: "Promote Users",
        description: "Promote users to admin status.",
        element: <Promotion />,
        status: adminPrivilege
    },
    {
        path: paths.matchStatistics,
        Title: "Match Statistics",
        description: "View and manage match statistics.",
        element: <MatchStatistics />,
        status: adminPrivilege
    }

]