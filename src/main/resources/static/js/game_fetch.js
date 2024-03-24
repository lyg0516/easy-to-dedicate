import * as commApi from "./common_fetch.js";


async function getGame(gameId) {
    const endpoint = `/games/${gameId}`
    return await commApi.fetchApi(endpoint);
}

