import * as commApi from './common_fetch.js';

const PREFIX_URI = '/servers'


export async function getServer(serverId) {
    const endpoint = `${PREFIX_URI}/${serverId}`;
    return await commApi.fetchApi(endpoint);
}

export async function getServers() {
    const endpoint = PREFIX_URI
    return await commApi.fetchApi(endpoint);
}

export async function createServer(option) {
    const endpoint = `${PREFIX_URI}`;

    //TODO 요청 후 결과 반환
    return await commApi.fetchApi(endpoint,'POST',option);

    //TODO 추가 로직
}

export async function restartServer(serverId) {
    const endpoint = `${PREFIX_URI}/${serverId}/restart`;
    return await commApi.fetchApi(endpoint);
}

//TODO 삭제 말고 정지에 관한 요청이 추가로 필요한가??
export async function deleteServer(serverId) {
    const endpoint = `${PREFIX_URI}/${serverId}`;
    return await commApi.fetchApi(endpoint, 'DELETE');
}
