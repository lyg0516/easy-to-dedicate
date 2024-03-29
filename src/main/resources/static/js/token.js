const TOKEN_KEY = 'authToken';

// URL에서 토큰 추출
function getTokenFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('token');
}

// 토큰을 localStorage에 저장
const token = getTokenFromUrl();
if (token) {
    window.localStorage.setItem(TOKEN_KEY, token);
    // 필요하다면 URL에서 토큰 파라미터 제거
    window.history.replaceState(null, '', window.location.pathname);
}


export function getToken() {
    return window.localStorage.getItem(TOKEN_KEY);
}
export function deleteToken() {
    window.localStorage.removeItem(TOKEN_KEY);
}

