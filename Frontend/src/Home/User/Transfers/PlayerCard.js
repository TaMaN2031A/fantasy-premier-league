import React, {createContext, useContext, useState} from 'react';
import defaultPlayer from "../Assets/defaultPlayer.jfif";
import PlayerModal from "./playerModal";
import {position} from "../../../collection";
import {LineUpContextContextFn} from "./Transfers";


function PlayerCard({number}) {

	const {lineUp, setLineUp} = LineUpContextContextFn();

	const [isModalOpen, setIsModalOpen] = useState(false);
	const [chosenPlayer, setChosenPlayer] = useState({});

	const openModal = () => {
		setIsModalOpen(true);
	};

	const closeModal = () => {
		setIsModalOpen(false);
	};

	// Customize styles based on position or any other criteria
	let cardStyle = '';
	if (lineUp[number].player.position === position.GK) {
		cardStyle = 'bg-green-500';
	} else if (lineUp[number].player.position === position.DEF) {
		cardStyle = 'bg-blue-500';
	} else if (lineUp[number].player.position === position.MID) {
		cardStyle = 'bg-gray-500';
	} else if (lineUp[number].player.position === position.FWD) {
		cardStyle = 'bg-red-500';
	}

	return (
		<>
			<button className={`p-0 rounded-lg shadow-md ${cardStyle}`} onClick={openModal}>
				<img
					src={defaultPlayer}
					alt={`Player ${number}`}
					className="w-3/4 rounded-full mx-auto p-0"
				/>
				<p className="text-white font-bold text-lg mb-2 inline-flex w-1/2 text-center">{number}</p>
				<p className="text-white text-sm text-center  inline-flex w-1/2">abdo</p>
			</button>

			<PlayerModal isOpen={isModalOpen} closeModal={closeModal} />

		</>
	);
}

export default PlayerCard;