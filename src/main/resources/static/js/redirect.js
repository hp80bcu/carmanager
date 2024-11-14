// redirect.js

// URL에서 쿼리 파라미터 추출
const urlParams = new URLSearchParams(window.location.search);
const token = urlParams.get('token');
const refreshToken = urlParams.get('refreshToken');

// token과 refreshToken이 있을 경우 localStorage에 저장
if (token && refreshToken) {
    localStorage.setItem('token', token);
    localStorage.setItem('refreshToken', refreshToken);
}

// '/loginhome'으로 리디렉션
window.location.href = '/loginhome';