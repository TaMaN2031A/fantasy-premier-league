import React, {createContext, useContext, useEffect, useState} from 'react';
import PlayerCard from "./PlayerCard";
import {position, responses, toastStyle} from "../../../collection";
import {useQuery} from "react-query";
import {fetchCurrentFormation, updateTransfer} from "../../../Services/Transfers/Transfers";
import {GetAuthDataFn} from "../../../Routes/wrapper";
import {toast, ToastContainer} from "react-toastify";


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
    const { person } = GetAuthDataFn();

    // list of (players without points) >> (player, teamName, isStarter)
    // captain, viceCaptain
    // bench boost, tripleCaptain
    const { data, isLoading, error} =
        useQuery("fetchPlayers", () => fetchCurrentFormation(person.username), {refetchOnWindowFocus: false});


    const [lineUp, setLineUp] = useState([
        null, null, null, null, null,
        null, null, null, null, null,
        null, null, null, null, null
    ]);

    // case of team is complete.
    useEffect(() => {
        if (!(data && Array.isArray(data))) return;
        console.log(data);
        let listOfPlayersData = data.players;
        const goalkeepers = listOfPlayersData.filter(p => p.player.position === position.GK);
        const defenders = listOfPlayersData.filter(p => p.player.position === position.DEF);
        const midfielders = listOfPlayersData.filter(p => p.player.position === position.MID);
        const forwards = listOfPlayersData.filter(p => p.player.position === position.FWD);

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

    let handleSaveTeam = async () => {
        let data = {
            username: person.username,
            players: lineUp
        }
        let ret = await updateTransfer(data);
        if(ret === responses.updateTransferSuccessfully){
            toast.success(ret, toastStyle);
        } else {
            toast.error(ret, toastStyle);
        }
    }

    /*
	* span >> make the area of div of size x >> break new line
	* end >> create area up to index y-1 in current row
	* */
    return (
        <>
            <ToastContainer/>
            <LineUpContext.Provider value={{lineUp, setLineUp}}>
                <div className="grid grid-cols-5 gap-4">
                    <div className="col-start-2 col-end-3 ...">
                        <PlayerCard number={0} givenPosition={"GK"}/>
                    </div>
                    <div className="col-start-4 col-span-1 ...">
                        <PlayerCard number={1} givenPosition={"GK"}/>
                    </div>

                    <div className="col-start-1 col-end-2 ...">
                        <PlayerCard number={2} givenPosition={"DEF"}/>
                    </div>
                    <div className="col-start-2 col-end-3 ...">
                        <PlayerCard number={3} givenPosition={"DEF"}/>
                    </div>
                    <div className="col-start-3 col-end-4 ...">
                        <PlayerCard number={4} givenPosition={"DEF"}/>
                    </div>
                    <div className="col-start-4 col-end-5 ...">
                        <PlayerCard number={5} givenPosition={"DEF"}/>
                    </div>
                    <div className="col-start-5 col-span-1 ...">
                        <PlayerCard number={6} givenPosition={"DEF"}/>
                    </div>

                    <div className="col-start-1 col-end-2 ...">
                        <PlayerCard number={7} givenPosition={"MID"}/>
                    </div>
                    <div className="col-start-2 col-end-3 ...">
                        <PlayerCard number={8} givenPosition={"MID"}/>
                    </div>
                    <div className="col-start-3 col-end-4 ...">
                        <PlayerCard number={9} givenPosition={"MID"}/>
                    </div>
                    <div className="col-start-4 col-end-5 ...">
                        <PlayerCard number={10} givenPosition={"MID"}/>
                    </div>
                    <div className="col-start-5 col-span-1 ...">
                        <PlayerCard number={11} givenPosition={"MID"}/>
                    </div>

                    <div className="col-start-2 col-end-3 ...">
                        <PlayerCard number={12} givenPosition={"FWD"}/>
                    </div>
                    <div className="col-start-3 col-end-4 ...">
                        <PlayerCard number={13} givenPosition={"FWD"}/>
                    </div>
                    <div className="col-start-4 col-span-1 ...">
                        <PlayerCard number={14} givenPosition={"FWD"}/>
                    </div>
                </div>
            </LineUpContext.Provider>
            <button onClick={handleSaveTeam}
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mx-auto block">
                Save
            </button>

        </>
    );
}

export default Transfers;