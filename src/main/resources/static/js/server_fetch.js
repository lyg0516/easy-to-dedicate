import fetchApi from './common_fetch';

const PREFIX_URI = '/servers'
let OPTION_FORM = {
    game_id: 1,
    member_id: 'test',
    term: 'FIXED',
    slot: 16,
    location: 'default',
    days: 30,
}



async function getServer(serverId) {
    const endpoint = `${PREFIX_URI}/${serverId}`;
    return await fetchApi(endpoint);
}

async function getServers() {
    const endpoint = PREFIX_URI
    return await fetchApi(endpoint);
}

async function createServer(type, option) {
    //TODO Type 정의

    //TODO OPTION_FORM 매핑

    //TODO 요청 후 결과 반환

    //TODO 추가 로직
}

async function restartServer(serverId) {
    const endpoint = `${PREFIX_URI}/${serverId}/restart`;
    return await fetchApi(endpoint);
}

//TODO 삭제 말고 정지에 관한 요청이 추가로 필요한가??
async function deleteServer(serverId) {
    const endpoint = `${PREFIX_URI}/${serverId}`;
    return await fetchApi(endpoint, 'DELETE');
}
