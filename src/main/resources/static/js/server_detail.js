import {getServer} from "./server_fetch.js";

const path = window.location.pathname;

const serverId = path.split('/');
const servers = await getServer(serverId);

//TODO 서버 설정
document.addEventListener('DOMContentLoaded',async () => {

});

// TODO 환경변수
document.addEventListener('DOMContentLoaded',async () => {

});
