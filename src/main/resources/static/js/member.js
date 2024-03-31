import {getServers} from "./server_fetch.js";
import {getUserId} from "./token.js";


document.addEventListener('DOMContentLoaded',async () => {
    const memberId = await getUserId();
    const serverTbody = document.querySelector('.server-tbody');
    const serverNoContent = document.querySelector('.no-content');
    if (memberId) {
        const servers = await getServers(memberId);
        if (!servers) {
            return
        }

        serverNoContent.style.display = 'none';
        servers.forEach(server => {
            // 새로운 행 생성
            const tr = document.createElement('tr');
            // 데이터 객체의 각 속성에 대해 셀 생성

            const gameTd = document.createElement('td');
            gameTd.textContent = server['game'];
            tr.appendChild(gameTd);
            const serverIdTd = document.createElement('td');
            serverIdTd.textContent = server['id'];
            tr.appendChild(serverIdTd);
            const slotTd = document.createElement('td');
            slotTd.textContent = server['slot'];
            tr.appendChild(slotTd);
            const dayTd = document.createElement('td');
            dayTd.textContent = server['days'];
            tr.appendChild(dayTd);
            const createTd = document.createElement('td');
            createTd.textContent = server['created_at'];
            tr.appendChild(createTd);

            // for (const td in tr.querySelectorAll('td')) {
            //     td.classList.add('noBorder'); // 클래스 추가, 필요에 따라 조정 가능
            // }
            serverTbody.appendChild(tr); // 완성된 행을 tbody에 추가
        });
    }
})

