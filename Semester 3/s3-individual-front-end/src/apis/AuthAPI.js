import axios from "./axios";
import TokenManager from "./TokenManager";

const LOGIN_URL = 'login'

const AuthAPI = {
    login: async (email, password) => {
        await axios.post(LOGIN_URL, { email, password })
            .then(response => response.data.accessToken)
            .then(accessToken => TokenManager.setAccessToken(accessToken))
        return TokenManager.getClaims();
    }
}

export default AuthAPI;