import axios from "./axios";
import TokenManager from "./TokenManager";

const CHAT_URL = 'chat';

const ChatAPI =  {
    createChat: async (prop) => {
        const response = await axios.post(CHAT_URL, prop, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        });
        return response.data;
    },

    joinChat: async (prop, props) => {
        const response = await axios.put(CHAT_URL + '/join/' + prop, props, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data;
    },
    
    leaveChat: async (prop, props) => {
        const response = await axios.put(CHAT_URL + '/leave/' + prop, props, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data;
    },

    getAllChats: async () => {
        const response = await axios.get(CHAT_URL);
        return response.data.chats;
    },

    getChat: async (prop) => {
        const response = await axios.get(CHAT_URL + '/' + prop, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data;
    }
}

export default ChatAPI;