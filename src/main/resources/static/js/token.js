// URL에서 토큰 추출
function getTokenFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('token');
}

// 토큰을 localStorage에 저장
const token = getTokenFromUrl();
console.log(`token : ${token}`);
if (token) {
    localStorage.setItem('authToken', token);
    // 필요하다면 URL에서 토큰 파라미터 제거
    window.history.replaceState(null, '', window.location.pathname);
}
