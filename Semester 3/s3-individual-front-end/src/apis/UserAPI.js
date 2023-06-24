import axios from "./axios";
import TokenManager from "./TokenManager";

const USER_URL = 'users';

const UserAPI =  {
    getUser: async (prop) => {
        const response = await axios.get(USER_URL + '/' + prop, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data;
    },

    createUser: async (prop) => {
        const response = await axios.post(USER_URL, prop)
        return response.data;
    },

    updateUser: async (prop, props) => {
        const response = await axios.put(USER_URL + '/' + prop, props, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data;
    },

    getAllUsers: async () => {
        const response = await axios.get(USER_URL, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data.users;
    }
}

export default UserAPI;