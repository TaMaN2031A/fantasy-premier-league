import { Link, Route, Routes } from "react-router-dom";
import { GetAuthDataFn } from "../wrapper";
import { nav } from "./navObjs";


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

   /*
   * add navigation bar dynamically.
   * */

   export const RenderMenu = () => {
   
        const { person } = GetAuthDataFn();
   
        const MenuItem = ({r}) => {
             return (
                  <div className="menuItem"><Link to={r.path}>{r.name}</Link></div>
             )
        }
        function jsxMenu (r, i) {
            return (
                <MenuItem key={i} r={r}/>
            )
        }

       return (
             <div className="menu">
                  {
                      nav.map((r, i) => {
                          return gotNeededNav(r, i, person, jsxMenu);
                      })
                  }
             </div>
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
       } else return false;
   }