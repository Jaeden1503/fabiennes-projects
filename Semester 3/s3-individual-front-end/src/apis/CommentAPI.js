import axios from "./axios";
import TokenManager from "./TokenManager";

const COMMENT_URL = 'comments';

const CommentAPI =  {
    getCommentsByUser: async (prop) => {
        const response = await axios.get(COMMENT_URL + '/user/' + prop, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data.comments;
    },

    createComment: async (prop) => {
        const response = await axios.post(COMMENT_URL, prop, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data;
    },

    deleteComment: async (prop) => {
        const response = await axios.delete(COMMENT_URL + '/' + prop, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data;
    },

    getAllComments: async () => {
        const response = await axios.get(COMMENT_URL, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data.comments;
    }
}

export default CommentAPI;