import {getServer} from "./server_fetch.js";

const path = window.location.pathname;
const split = path.split('/');
const serverId = split[split.length-1];
console.log(serverId);

//TODO 서버 설정
document.addEventListener('DOMContentLoaded',async () => {
    const server = await getServer(serverId);

    const rows = document.querySelectorAll('.server-info-row');
    rows.forEach(row => {
        const input = row.querySelector('input');
        const name = input.getAttribute('name');
        input.value = server[name];
    });


});

// TODO 환경변수
document.addEventListener('DOMContentLoaded',async () => {

});
