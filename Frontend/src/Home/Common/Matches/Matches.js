import React from 'react';
import {InsertUpcomingMatch} from "./insertUpcomingMatch";
import {DeleteUpcomingMatch} from "./deleteUpcomingMatchs";

function Matches(props) {
    return (
        <div>
            <InsertUpcomingMatch />
            <DeleteUpcomingMatch />
        </div>
    );
}

export default Matches;