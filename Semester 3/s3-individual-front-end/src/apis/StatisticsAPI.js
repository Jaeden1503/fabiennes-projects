import axios from "./axios";
import TokenManager from "./TokenManager";

const STATISTICS_URL = 'statistics';

const StatisticsAPI = {
    getTopPosts: async () => {
        const response = await axios.get(STATISTICS_URL + '/topposts', {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data.popularPosts;
    },

    getVSCount: async () => {
        const response = await axios.get(STATISTICS_URL + '/bandormusician', {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data;
    },

    getInstrumentCount: async () => {
        const response = await axios.get(STATISTICS_URL + '/instrument', {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data;
    }
}

export default StatisticsAPI;