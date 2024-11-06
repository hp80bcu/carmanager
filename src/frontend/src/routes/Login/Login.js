import React from 'react';
import './LoginStyles.css';
import Nav from "../../components/Nav";

const Login = () => {
  return (
    <>
      <Nav />
      <div className="login-container">
        <div className="login-card">
          <h2>Login</h2>
          <p>소셜 로그인으로 간편하게 차 매니저를 이용해보세요!</p>
          <div className="social-icons">
            <img src="/Image/icon_naver.png" alt="Naver" className="icon" />
            <img src="/Image/icon_google.png" alt="Google" className="icon" />
            <img src="/Image/icon_kakao.png" alt="Kakao" className="icon" />
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
