import {serverHost} from "../../collection";
import axios from "axios";

const API_BASE_URL = serverHost + '/leagueStatistics';

const handleRequest = async (requestPromise) => {
	try {
		const response = await requestPromise;
		return response.data;
	} catch (error) {
		return "failed";
	}
};

export const fetchFaqData = async () => {
	return handleRequest(
		axios.get( serverHost + '/getLeagueStandings')
	);
};

