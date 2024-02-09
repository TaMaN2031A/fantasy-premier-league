import { serverHost } from "../../collection";
import axios from "axios";

const handleRequest = async (requestPromise) => {
	try {
		const response = await requestPromise;
		return response.data;
	} catch (error) {
		return "failed";
	}
};

export const fetchplayerByPosition = async (position) => {
	console.log(position)
	return handleRequest(axios.get(serverHost + "/transfer/getPlayer/" + position));
};

// need to handle responseEntity cases.
export const fetchCurrentFormation = async (username) => {
	return handleRequest(axios.get(serverHost + "/pickTeam/getCurrentFormation/" + username));
}

// need to handle responseEntity cases.
export const updateTransfer = async (data) => {
	return handleRequest(axios.put(serverHost + "/transfer/update", data));
}