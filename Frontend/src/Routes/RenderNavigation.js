import {Route, Routes} from "react-router-dom";
import { GetAuthDataFn } from "../Routes/wrapper";
import { nav } from "./navObjs";
import {adminPrivilege, commonPrivilege, externalPrivilege, internalPrivilege, userPrivilege} from "../collection";
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
   } if (!person.isAuthorized && r.status === externalPrivilege) {
       return jsxFn(r, i);
   } if (person.isAuthorized && r.status === internalPrivilege) {
       return jsxFn(r, i);
   } if (person.isAuthorized && person.privilege === r.status) {
       return jsxFn(r, i);
   } if (person.isAuthorized && r.status === commonPrivilege) {
       return jsxFn(r, i);
   } else return false;
}

const createdCard = (card) => <Card Title={card.Title} details={card.description} path={card.path}/>

export const commonList = () => {
    return nav.map((card) => (
        card.status === commonPrivilege  && createdCard(card)
    ));
}

export const adminList = () => {
    return nav.map((card) => (
        card.status === adminPrivilege  && createdCard(card)
    ));
}

export const userList = () => {
    return nav.map((card) => (
        card.status === userPrivilege  && createdCard(card)
    ));
}