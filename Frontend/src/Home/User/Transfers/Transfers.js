import React, {createContext, useContext, useEffect, useState} from 'react';
import PlayerCard from "./PlayerCard";
import {position} from "../../../collection";
import {useQuery} from "react-query";
import {fetchCurrentFormation} from "../../../Services/Transfers/Transfers";


const LineUpContext = createContext();
export const LineUpContextContextFn = () => useContext(LineUpContext);

class PlayerWithoutPoints {
    player;
    teamName;
    isStarter;
    constructor(player, teamName, isStarter) {
        this.player = player;
        this.teamName = teamName;
        this.isStarter = isStarter;
    }
}


function Transfers() {
    const { data, isLoading, error} =
        useQuery("fetchPlayers", () => fetchCurrentFormation, {refetchOnWindowFocus: false});


    const [lineUp, setLineUp] = useState(
        new Array(15).fill(new PlayerWithoutPoints(null, null, false))
    );
    useEffect(() => {
        const goalkeepers = data.filter(playerWithoutPoints => playerWithoutPoints.player.position === position.GK);
        const defenders = data.filter(playerWithoutPoints => playerWithoutPoints.player.position === position.DEF);
        const midfielders = data.filter(playerWithoutPoints => playerWithoutPoints.player.position === position.MID);
        const forwards = data.filter(playerWithoutPoints => playerWithoutPoints.player.position === position.FWD);

        // firstly append goalkeepers to lineUp then defenders then midfielders then forwards
        let lineUp = [];
        lineUp = lineUp.concat(goalkeepers);
        lineUp = lineUp.concat(defenders);
        lineUp = lineUp.concat(midfielders);
        lineUp = lineUp.concat(forwards);
        setLineUp(lineUp);
    }, [data]);

    if (error) return <p>Error: {error.message}</p>;
    if(isLoading) return (<p>is loading....</p>);

    /*
	* span >> make the area of div of size x >> break new line
	* end >> create area up to index y-1 in current row
	* */
    return (
        <LineUpContext.Provider value={{lineUp, setLineUp}}>
            <div className="grid grid-cols-5 gap-4">
                <div className="col-start-2 col-end-3 ...">
                    <PlayerCard number={0} />
                </div>
                <div className="col-start-4 col-span-1 ...">
                    <PlayerCard number={1} />
                </div>

                <div className="col-start-1 col-end-2 ...">
                    <PlayerCard number={2} />
                </div>
                <div className="col-start-2 col-end-3 ...">
                    <PlayerCard number={3} />
                </div>
                <div className="col-start-3 col-end-4 ...">
                    <PlayerCard number={4} />
                </div>
                <div className="col-start-4 col-end-5 ...">
                    <PlayerCard number={5} />
                </div>
                <div className="col-start-5 col-span-1 ...">
                    <PlayerCard number={6} />
                </div>

                <div className="col-start-1 col-end-2 ...">
                    <PlayerCard  number={7} />
                </div>
                <div className="col-start-2 col-end-3 ...">
                    <PlayerCard  number={8} />
                </div>
                <div className="col-start-3 col-end-4 ...">
                    <PlayerCard  number={9} />
                </div>
                <div className="col-start-4 col-end-5 ...">
                    <PlayerCard  number={10} />
                </div>
                <div className="col-start-5 col-span-1 ...">
                    <PlayerCard  number={11} />
                </div>

                <div className="col-start-2 col-end-3 ...">
                    <PlayerCard  number={12} />
                </div>
                <div className="col-start-3 col-end-4 ...">
                    <PlayerCard  number={13} />
                </div>
                <div className="col-start-4 col-span-1 ...">
                    <PlayerCard  number={14} />
                </div>
            </div>
        </LineUpContext.Provider>
    );
}

export default Transfers;