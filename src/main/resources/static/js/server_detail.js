import {getServer} from "./server_fetch.js";
import {getOption, updateOption} from "./option_fetch.js";

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
    const options = optData['game_option'];
    updateOptionUI(options);
});


document.querySelector('#game-opt-update')
    .addEventListener('click', async () => {
        const game = document.querySelector('input[name="game"]').value;
        const lowGame = game.toLowerCase();

        const form = document.querySelector('.game-opt-content');
        let formData = {};
        form.querySelectorAll('input').forEach(input => {
            if (input.name !== "" && input.name !== undefined) {
                formData[input.name] = input.value.trim();
            }
        });
        await updateOption(serverId, lowGame, formData);

        const optData = await getOption(serverId);
        const options = optData['game_option'];
        updateOptionUI(options);

        window.alert('게임 옵션 업데이트 완료!');
});



function updateOptionUI(options) {
    const content = document.querySelector('.game-opt-content');
    const row = content.querySelector('.game-opt-row');

    Object.entries(options).forEach(([key, value]) => {
        const cloneRow = row.cloneNode(true);
        const label = cloneRow.querySelector('label');
        label.setAttribute('for',key);
        label.textContent = key;
        const input = cloneRow.querySelector('input');
        input.value = value;
        input.name = key;
        content.appendChild(cloneRow);
    });

    row.style.display = 'none';
}
