import React, { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';

const LoginRedirect: React.FC = () => {
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        // URL에서 토큰과 리프레시 토큰을 추출
        const params = new URLSearchParams(location.search);
        const token = params.get('token');
        const refreshToken = params.get('refreshToken');

        if (token && refreshToken) {
            // 쿠키에 토큰 저장
            Cookies.set('access_token', token.split(' ')[1], { expires: 1 }); // 1일 후 만료
            Cookies.set('refresh_token', refreshToken.split(' ')[1], { expires: 7 }); // 7일 후 만료

            // 루트 경로로 리다이렉트
            navigate('/');
        } else {
            // 토큰이 없으면 로그인 실패 처리
            console.error('Tokens not found in URL');
            // 필요 시 에러 처리 로직 추가
        }
    }, [location, navigate]);

    return <div>Redirecting...</div>; // 리다이렉트 중 메시지 표시
};

export default LoginRedirect;
