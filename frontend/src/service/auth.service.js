import axios from "axios";
import Cookies from 'js-cookie';
import authHeader from '../service/auth-header';
import {serverProxy} from './server-proxy-constant';


const API_URL_SIGNIN = serverProxy+"/api/v1/authentication/";
const API_URL_SIGNUP = serverProxy+"/api/v1/registration/";
const API_URL_FORGOT_PASSWORD = serverProxy+"/api/v1/users/accounts/update_forgot_password"
const API_URL_SEND_EMAIL_FORGOT_PASSWORD = serverProxy+"/api/v1/users/accounts/email/email_token";
const API_URL_UPDATE_PASSWORD = serverProxy+"/api/v1/users/accounts/update_password";
const API_URL_VERIFY_USER_EXISTS = serverProxy+"/api/v1/users/username/";
const API_URL_VERIFY_EMAIL_EXISTS = serverProxy+"/api/v1/users/";



const headers =   authHeader() ;
const region = localStorage.getItem("userRegion")


const forgotPassword = (password,confirmPassword,token) => {
    return axios.post(API_URL_FORGOT_PASSWORD,{password,confirmPassword,token}).then(
        (response) => {
            return response.status;
        })
        .catch((error)=>{
            return error.response.status;
        });
};

const updatePassword = (oldPassword, password, confirmPassword) => {
    return axios.post(API_URL_UPDATE_PASSWORD, {
        oldPassword, password, confirmPassword
    }, headers)
        .then((response) => {
            return response.status
        })
};

const sendEmailForgotPassword = (email) => {
    return axios.post(API_URL_SEND_EMAIL_FORGOT_PASSWORD, { email }).then(
        (response) => {
            console.log(response)
            return response.status
        })
        .catch(
            (error) => {
                console.log(error)
            });
};

const register = (username, email, password, confirmPassword) => {
    return axios.post(API_URL_SIGNUP, {
        username,
        email,
        password,
        confirmPassword,
        region,
    });
};



const login = (username, password) => {
    return axios
        .post(API_URL_SIGNIN, {
            username,
            password,
        })
        .then((response) => {

            if (response.data) {
                setCurrentUser(response.data)
                return response;
            }

        });
};

const verifyUserExists = (username) => {
    return axios.get(API_URL_VERIFY_USER_EXISTS+username).then(
        (response) => {
            return response
        })
        .catch(error => {
        });
};

const verifyEmailExists = (email) => {
    return axios.get(API_URL_VERIFY_EMAIL_EXISTS+email).then(
        (response) => {
            return response
        })
        .catch(error => {
        });
};

const logout = () => {
    Cookies.remove("wtu");
};


const getCurrentUser = () => {
    return Cookies.get("wtu");
};

const setCurrentUser = (jwt) => {
    return Cookies.set('wtu',jwt,{expires:7});
}
export default {
    register,
    login,
    logout,
    getCurrentUser,
    verifyUserExists,
    verifyEmailExists,
    updatePassword,
    sendEmailForgotPassword,
    forgotPassword,
};