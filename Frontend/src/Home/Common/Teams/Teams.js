import React from 'react';
import { InsertTeam } from './insertTeam';
import { DeleteTeam } from './deleteTeam';

function Teams(props) {
    return (
        <div>
            <InsertTeam />
            <DeleteTeam />
        </div>
    );
}

export default Teams;