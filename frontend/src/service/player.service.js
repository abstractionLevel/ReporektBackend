import axios from 'axios';
import {serverProxy} from './server-proxy-constant';


var userRegion = localStorage.getItem('userRegion');
if(userRegion) {
    userRegion = userRegion.replace(/ /g,"_"); 

}

const FETCH_PLAYER = serverProxy+"/api/v1/players/search?username=";
const FETCH_TOP_PLAYERS = serverProxy+"/api/v1/players/top?region=";
const FETCH_LATEST_PLAYERS = serverProxy+"/api/v1/players/latest?region=";
 
const searchPlayer = (username) => {
    return axios.get(FETCH_PLAYER+username+'&region='+userRegion).then(
        (response) => { 
            return response.data.nickname;
        })
        .catch(error => {
        });
};

const fetchTopPlayer = () => {
    return axios.get(FETCH_TOP_PLAYERS+userRegion).then(
        (response) => {
            return response.data;
        })
        .catch(error => {
            console.log(error)
        });
};

const fetchLatestPlayers = () => {
    return axios.get(FETCH_LATEST_PLAYERS+userRegion).then(
        (response) => {
            return response.data
        })
        .catch(error=>{
            console.log(error)
        });
}

export default {
    searchPlayer,
    fetchTopPlayer,
    fetchLatestPlayers,
};