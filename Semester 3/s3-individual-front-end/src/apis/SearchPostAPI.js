import axios from "./axios";
import TokenManager from "./TokenManager";

const SEARCHPOST_URL = 'searchposts';

const SearchPostAPI =  {
    getAllSearchPosts: async () => {
        const response = await axios.get(SEARCHPOST_URL);
        return response.data.searchPosts;
    },

    getSearchPost: async (prop) => {
        const response = await axios.get(SEARCHPOST_URL + '/searchpost/' + prop)
        return response.data;
    },

    getSearchPostAndComments: async (prop) => {
        const response = await axios.get(SEARCHPOST_URL + '/' + prop, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        console.log(response.data);
        return response.data;
    },

    filterSearchPosts: async (prop, prop2, prop3) => {
        const response = await axios.get(SEARCHPOST_URL + '/filter', {
            params: {
                searchingBand: prop,
                instrument: prop2,
                province: prop3
            }
        });
        return response.data.searchPosts;
    },
    
    getSearchPostsByUser: async (prop) => {
        const response = await axios.get(SEARCHPOST_URL + '/user/' + prop, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data.searchPosts;
    },

    createSearchPost: async (prop) => {
        console.log(prop);
        const response = await axios.post(SEARCHPOST_URL, prop, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data;
    },

    deleteSearchPost: async (prop) => {
        const response = await axios.delete(SEARCHPOST_URL + '/' + prop, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data;
    },

    getAllPostsPaginated: async (prop) => {
        const response = await axios.get(SEARCHPOST_URL + '/paginated/' + prop, {
            headers: { Authorization: `Bearer ${TokenManager.getAccessToken()}` }
        })
        return response.data;
    }
}

export default SearchPostAPI;