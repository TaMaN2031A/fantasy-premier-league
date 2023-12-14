import {serverHost} from "../../collection";
import axios from "axios";
const handleRequest = async (requestPromise) => {
    try {
        const response = await requestPromise;
        return response.data.content;
    } catch (error) {
        return "failed";
    }
};
const handleRequestInCaseOfPage = async (requestPromise) => {
    try {
        const response = await requestPromise;
        console.log(response)
        return [response.data.content,response.data.totalElements];
    } catch (error) {
        return "failed";
    }
};

export const GetAllUsers = async (pageNo) => {
    return handleRequestInCaseOfPage(
    axios.get(serverHost+'/AdminPromotion/GetUsers?pageNo='+pageNo)
    );
};
export const SearchBySpecifications = async (pageNo,SpecificationType,value) => {
    return handleRequestInCaseOfPage(
        axios.get(serverHost + '/AdminPromotion/searchBy?specificationType='+SpecificationType+'&value='+value+'&page='+(pageNo-1)+'&size='+10
        )
    );
};
export const  promoteUserToAdmin = async (userName) => {
    return handleRequest(
        axios.put( serverHost + '/AdminPromotion/promote?UserNameOrEmail='+userName)
    );
};