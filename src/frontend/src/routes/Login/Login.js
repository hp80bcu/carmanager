import React from 'react';
<<<<<<<< HEAD:src/frontend/src/routes/Login/Login.js
import './LoginStyles.css';
========
import './login.css';
>>>>>>>> d56a26640c7155cd8665c1ea7e0701985a56d51c:src/frontend/src/routes/users/login.tsx
import Nav from "../../components/Nav";

const Login = () => {
    const handleLogin = (provider: string) => {
        window.location.href = `http://localhost:8080/oauth2/authorization/${provider}`;
        };

  return (
    <>
      <Nav />
      <div className="login-container">
        <div className="login-card">
          <h2>Login</h2>
          <p>소셜 로그인으로 간편하게 차 매니저를 이용해보세요!</p>
          <div className="social-icons">
            <img src="/Image/icon_naver.png" alt="Naver" className="icon" onClick={() => handleLogin('naver')}/>
            <img src="/Image/icon_google.png" alt="Google" className="icon" onClick={() => handleLogin('google')}/>
            <img src="/Image/icon_kakao.png" alt="Kakao" className="icon" onClick={() => handleLogin('kakao')}/>
          </div>
          <div className="logo-section">
            <img src="/Image/logo.png" alt="차 매니저" className="car-logo" />
          </div>
        </div>
      </div>
    </>
  );
};

export default Login;