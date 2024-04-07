import {getServer} from "./server_fetch.js";
import {getOption} from "./option_fetch.js";

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
    const optData = await getOption(serverId);
    console.log(`option data : ${optData}`);

    const content = document.querySelector('.game-opt-content');
    // const rows = document.querySelectorAll('.game-opt-row');

    const row = content.querySelector('.game-opt-row').cloneNode(true);
    content.empty();

    const options = optData['game_option'];

    options.forEach((key, value) => {
        const cloneRow = row.cloneNode(true);
        const label = cloneRow.querySelector('label');
        label.setAttribute('for',key);
        label.textContent = key
        const input = cloneRow.querySelector('input');
        input.value = value;
        input.name = key;
    });

});
