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

export const fetchLeagueStandings = async () => {
	console.log("fetching league standings");
	return handleRequest(
		axios.get( API_BASE_URL + '/getLeagueStandings')
	);
};

export const fetchTopScorers = async () => {
	return handleRequest(
		axios.get( API_BASE_URL + '/getTopScorers')
	);
}
export const fetchTopAssists = async () => {
	return handleRequest(
		axios.get( API_BASE_URL + '/getTopAssists')
	);
}

export const fetchTopCleanSheets = async () => {
	return handleRequest(
		axios.get( serverHost + '/getTopCleanSheets')
	);
}

