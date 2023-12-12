import {Route, Routes} from "react-router-dom";
import { GetAuthDataFn } from "../Routes/wrapper";
import { nav } from "./navObjs";
import {adminPrivilege, commonPrivilege, userPrivilege} from "../collection";
import Card from "../Home/Home Page/Card";


/*
* add routing mapping dynamically based on case we are in
* */

export const RenderRoutes = () => {
    const { person } = GetAuthDataFn();
    console.log('rendering ' + person);

    function jsxRoute (r, i) {
        return <Route key={i} path={r.path} element={r.element}/>
    }

    return (
         <Routes>
             {
                 nav.map((r, i) => {
                     return gotNeededNav(r, i, person, jsxRoute);
                 })
             }
         </Routes>
    )
}

function gotNeededNav (r, i, person, jsxFn) {
   if(r.status === "global") {
       return jsxFn(r, i);
   } else if (!person.isAuthorized && r.status === "external") {
       return jsxFn(r, i);
   } else if (person.isAuthorized && r.status === "internal") {
       return jsxFn(r, i);
   } else if (person.isAuthorized && person.privilege === r.status) {
       return jsxFn(r, i);
   } else if (person.isAuthorized && r.status === commonPrivilege) {
       return jsxFn(r, i);
   } else return false;
}

export const commonList = () => {
    return nav.map((card) => (
        card.status === commonPrivilege  && <Card Title={card.Title} details={card.description} path={card.path}/>
    ));
}

export const adminList = () => {
    return nav.map((card) => (
        card.status === adminPrivilege  && <Card Title={card.Title} details={card.description} path={card.path}/>
    ));
}

export const userList = () => {
    return nav.map((card) => (
        card.status === userPrivilege  && <Card Title={card.Title} details={card.description} path={card.path}/>
    ));
}