import React, {createContext, useContext, useEffect, useState} from 'react';
import defaultPlayer from "../Assets/defaultPlayer.avif";
import PlayerModal from "./PlayerModal";
import {position} from "../../../collection";
import {LineUpContextContextFn} from "./Transfers";


function PlayerCard({number, givenPosition}) {

	const {lineUp} = LineUpContextContextFn();
	const [isModalOpen, setIsModalOpen] = useState(false);

	const openModal = () => { setIsModalOpen(true) };
	const closeModal = () => { setIsModalOpen(false) };

	// Customize styles based on position or any other criteria
	let cardStyle = '';

	if(lineUp[number] === null) {
		cardStyle = 'bg-white';
	} else if (givenPosition === position.GK) {
		cardStyle = 'bg-green-500';
	} else if (givenPosition === position.DEF) {
		cardStyle = 'bg-blue-500';
	} else if (givenPosition === position.MID) {
		cardStyle = 'bg-gray-500';
	} else if (givenPosition === position.FWD) {
		cardStyle = 'bg-red-500';
	}

	let dynamicPlayerName = () => {
		return lineUp[number] === null ? "Add a player" : lineUp[number]
	};
	return (
		<>
			<button className={`p-0 rounded-lg shadow-md ${cardStyle}`} value={number} onClick={openModal}>
				<img
					src={defaultPlayer}
					alt={`Player ${number}`}
					className="w-3/4 rounded-full mx-auto p-0"
				/>
				<p className="text-black text-bold text-center  inline-flex w-1/2"> {dynamicPlayerName()} </p>
			</button>

			{isModalOpen && <PlayerModal isOpen={isModalOpen} closeModal={closeModal} position={givenPosition} number={number}/>}
		</>
	);
}

export default PlayerCard;