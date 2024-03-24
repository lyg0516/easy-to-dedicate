import * as commApi from './common_fetch.js';

const PREFIX_URI = '/servers'

//TODO 추후 게임 서버 옵션 API 조회하여 동적으로 생성하도록 한다.
const OPTION_FORM = {
    game_id: 1,
    member_id: '59b7c9ad-e6b4-46e9-92f1-5e3b326b9c4a',
    term: 'FIXED',
    slot: 16,
    location: 'default',
    days: 30,
}

let SERVER_TYPE = {
    SMALL:'SMALL',
    MIDDLE:'MIDDLE' ,
    MAX:'MAX',
}



async function getServer(serverId) {
    const endpoint = `${PREFIX_URI}/${serverId}`;
    return await commApi.fetchApi(endpoint);
}

async function getServers() {
    const endpoint = PREFIX_URI
    return await commApi.fetchApi(endpoint);
}

async function createServer(type, option) {
    const endpoint = `${PREFIX_URI}`;
    let requestOption = Object.assign(OPTION_FORM);
    //TODO Type 정의
    //TODO OPTION_FORM 매핑
    switch (type) {
        case 'SMALL':
            requestOption.slot = 4;
            break;
        case 'MIDDLE':
            requestOption.slot = 16;
            break;
        case 'MAX':
            requestOption.slot = 32;
            break;
        case 'CUSTOM':
            requestOption = option;
            break;
        default:
            console.error('정의 되지 않은 타입입니다!');
    }

    //TODO 요청 후 결과 반환
    return await commApi.fetchApi(endpoint,'POST',requestOption);

    //TODO 추가 로직
}

async function restartServer(serverId) {
    const endpoint = `${PREFIX_URI}/${serverId}/restart`;
    return await commApi.fetchApi(endpoint);
}

//TODO 삭제 말고 정지에 관한 요청이 추가로 필요한가??
async function deleteServer(serverId) {
    const endpoint = `${PREFIX_URI}/${serverId}`;
    return await commApi.fetchApi(endpoint, 'DELETE');
}


document.querySelectorAll('.buy-btn').forEach(btn => {
    btn.addEventListener('click', async () => {
        const orderType = btn.dataset.orderType;
        console.log(`order type : ${orderType}`);
        await createServer(orderType);
    })
})

console.log('서버 패치 관련 스크립트 파일 로드!');
