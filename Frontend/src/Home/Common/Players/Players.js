import React from 'react';
import { InsertPlayer } from './insertPlayer';
import { DeletePlayer } from './deletePlayer';

function Players(props) {
    return (
        <div>
            <InsertPlayer />
            <DeletePlayer />
        </div>
    );
}

export default Players;