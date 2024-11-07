import { Link } from "react-router-dom";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import "./Nav.css";
import "./Custom-Link.css";


import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';

export default function Nav() {
  const [username, setUsername] = useState<string | null>(null);

  useEffect(() => {
    const fetchUserInfo = async () => {
      const accessToken = Cookies.get('access_token');
      console.log("액세스 토큰 " + accessToken);
      if (accessToken) {
        try {

          const response = await axios.get('/users/userinfo', {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          });
          setUsername(response.data.username); // 사용자 이름 설정
        } catch (error) {
          console.error('Failed to fetch user info', error);
          setUsername(null); // 오류 발생 시 사용자 이름 초기화
        }
      }
    };

    fetchUserInfo();
  }, []);

  return (
      <>
        <Box sx={{ display: "flex", justifyContent: "flex-end" }}>
          {username ? (
              <Button
                  variant="text"
                  sx={{
                    m: 0,
                    color: "black",
                    display: { xs: "none", sm: "none", lg: "block" },
                  }}
              >
                {username}님 환영합니다
              </Button>
          ) : (
              <Button
                  variant="text"
                  sx={{
                    m: 0,
                    color: "black",
                    display: { xs: "none", sm: "none", lg: "block" },
                  }}
              >
                <Link to={"/users/login"} className="custom-link">
                  로그인
                </Link>
              </Button>
          )}
        </Box>
        <div className="h-container">
          <div className="logo">
            <Link to={"/"}>
              <img src="/Image/logo.png" height="80" alt="로고" />
            </Link>
          </div>
          <div className="item middle">
            <Link to={"/searchpage"} className="custom-link">
              검색
            </Link>
          </div>
          <div className="item middle">
            <Link to={"/purchase"} className="custom-link">
              구매
            </Link>
          </div>
          <div className="item middle">
            <Link to={"/sale"} className="custom-link">
              판매
            </Link>
          </div>
          <div className="item middle">
            <div className="hypen">
              <text>|</text>
            </div>
          </div>
          <div className="item right">
            <Link to={"/mycar"} className="custom-link">
              <img src="/Image/mycar.png" height="50" alt="내 차량" />
            </Link>
          </div>
          <div className="item right">
            <Link to={"/users/userinfomodify"} className="custom-link">
              <img src="/Image/myinfo.png" height="50" alt="내 정보" />
            </Link>
          </div>
          <div className="item rightend">
            <Link to={"/bookmark"} className="custom-link">
              <img src="/Image/bookmark.png" height="50" alt="찜 목록" />
            </Link>
          </div>
        </div>
      </>
  );
}
