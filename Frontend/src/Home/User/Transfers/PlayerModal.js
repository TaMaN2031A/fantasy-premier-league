import React, {useEffect, useState} from 'react';
import {useQuery} from "react-query";
import {fetchFAQData} from "../../../Services/FAQ/Faq_Rule";
import {GetAuthDataFn} from "../../../Routes/wrapper";
import {fetchplayerByPosition} from "../../../Services/Transfers/Transfers";
import {LineUpContextContextFn} from "./Transfers";
import {select} from "@material-tailwind/react";


const PlayerModal = ({ isOpen, closeModal, position, number }) => {

	const { data, isLoading, error} =
		useQuery("fetchPlayersByPosition", () => fetchplayerByPosition(position), {refetchOnWindowFocus: false});

	const { person } = GetAuthDataFn();
	const { lineUp, setLineUp } = LineUpContextContextFn();
	const [selectedPlayer, setSelectedPlayer] = useState("");

	const handleSelectChange = (e) => {
		setSelectedPlayer(e.target.value);
	}

	useEffect(() => {
		// iterate on data and search for player in lineUp if exists delete it form data
		if (!(data && Array.isArray(data))) return;
		// Create a new array by filtering out players that exist in playerList
		// const filteredData = data.filter((player) => {
		// 	const isPlayerInList = lineUp.some(
		// 		(listPlayer) => (listPlayer.player.name === player.player.name)
		// 	);
		// 	return !isPlayerInList;
		// });
		// setAllowablePlayers(filteredData);
	}, [data]);


	if(isLoading) return (<p>is loading....</p>);
	if (error) return <p>Error: {error.message}</p>;


	const handleSave = async () => {
		let arr = lineUp;
		arr[number] = selectedPlayer;
		console.log(arr[number])
		setLineUp(arr);
		closeModal();
	};

	const cancelSave = () => {
		setSelectedPlayer("");
		closeModal();
	};

	return (
		<div
			className={`fixed z-10 inset-0 overflow-y-auto ${
				isOpen ? "block" : "hidden"
			}`}
		>
			<div className="flex items-center justify-center min-h-screen pt-4 px-4 pb-20 text-center">
				<div className="fixed inset-0 transition-opacity" aria-hidden="true">
					<div className="absolute inset-0 bg-gray-500 opacity-75" />
				</div>
				<span
					className="hidden sm:inline-block sm:align-middle sm:h-screen"
					aria-hidden="true"
				>
          &#8203;
        </span>
				<div
					className="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full"
					role="dialog"
					aria-modal="true"
					aria-labelledby="modal-headline"
				>

					{/*--------------------------------------------drop down list-------------------------------------*/}
					<div className="my-4">
						<label htmlFor="playerDropdown" className="text-sm font-semibold">
							Select a Player:
						</label>
						<select
							id="playerDropdown"
							onChange={handleSelectChange}
							value={selectedPlayer}
							className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:border-blue-500"
						>
							<option value="" disabled>
								Select a player
							</option>
							{data.map((player) => (
								<option key={player.id} value={player.name}>
									{player.name}
								</option>
							))}
						</select>

						{selectedPlayer && (
							<div className="mt-4">
								<p className="text-lg font-semibold">You selected: {selectedPlayer}</p>
								{/* Add additional content or actions based on the selected player */}
							</div>
						)}
					</div>

					<div className="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
						<button
							onClick={handleSave}
							className="w-full inline-flex justify-center rounded-md border border-transparent px-4 py-2 bg-blue-600 text-base font-medium text-white hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 sm:ml-3 sm:w-auto sm:text-sm"
						>
							Save
						</button>
						<button
							onClick={cancelSave}
							className="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm"
						>
							Cancel
						</button>
					</div>
				</div>
			</div>
		</div>
	);
}

export default PlayerModal;