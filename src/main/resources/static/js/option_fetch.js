import * as commApi from "./common_fetch.js";

export async function getOption(serverId) {
    const endpoint = `/options/${serverId}`
    return await commApi.fetchApi(endpoint);
}
