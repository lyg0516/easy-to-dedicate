import * as serverApi from "./server_fetch.js"
import { getUserId } from "./token.js";

//TODO 추후 게임 서버 옵션 API 조회하여 동적으로 생성하도록 한다.
const OPTION_FORM = {
    game_id: 1,
    member_id: getUserId(),
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


document.querySelectorAll('.buy-btn').forEach(btn => {
    btn.addEventListener('click', async () => {
        const orderType = btn.dataset.orderType;
        console.log(`order type : ${orderType}`);

        const option = createOption(orderType);

        await serverApi.createServer(option);
    })
})


function createOption(type, option) {
    const path = window.location.pathname;
    const splits = path.split('/');
    const gameId = splits[splits.length - 1];

    // TODO Member 매핑 로직 추가.

    // OPTION_FORM 매핑
    let requestOption = Object.assign(OPTION_FORM);
    requestOption.game_id = gameId;
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

    return requestOption;
}
