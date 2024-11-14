import { Link, useNavigate } from "react-router-dom";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import "./Nav.css";
import "./Custom-Link.css";
import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Nav() {
  const [username, setUsername] = useState<string | null>(null);
  const [userId, setUserId] = useState<bigint | null>(null);

  const navigate = useNavigate(); // useNavigate 훅 사용

  const getCookie = (name: string) => {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop()?.split(';').shift();
    return null;
  };

  // 로그아웃 처리 함수
  const logout = async () => {
    try {
      // 백엔드의 로그아웃 API 호출
      await axios.get("/users/logout");

      // 상태 갱신
      setUsername(null);

      // 로그인 페이지로 리디렉션
      navigate("/");
    } catch (error) {
      console.error("Logout failed:", error);
    }
  };

  useEffect(() => {
    const fetchUserInfo = async () => {
      const accessToken = getCookie('Authorization');
      console.log("액세스 토큰: " + accessToken);

      if (accessToken) {
        try {
          const response = await axios.get('/users/userinfo', {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          });
          setUsername(response.data.username); // 사용자 이름 설정
          setUserId(response.data.userId);

          console.log("Fetched User ID:", response.data.userId); // User ID 확인용 로그
        } catch (error) {
          console.error('Failed to fetch user info:', error);
        }
      }
    };

    fetchUserInfo();
  }, []);

  // userId 변경 시 콘솔에 로그 출력
  useEffect(() => {
    if (userId !== null) {
      console.log("User ID:", userId);
    }
  }, [userId]);

  return (
      <>
        <Box sx={{ display: "flex", justifyContent: "flex-end", alignItems: "center" }}>
          {username ? (
              // 로그인 상태일 때 "님 환영합니다"와 로그아웃 버튼 표시
              <>
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
                <Button
                    variant="text"
                    sx={{
                      m: 0,
                      color: "black",
                      display: { xs: "none", sm: "none", lg: "block" },
                      marginLeft: "10px", // 텍스트와 버튼 사이의 간격 조정
                    }}
                    onClick={logout}  // 로그아웃 시 로그아웃 함수 실행
                >
                  로그아웃
                </Button>
              </>
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

          {/* userId가 존재할 때만 링크를 렌더링 */}
          {userId !== null && (
              <div className="item right">
                <Link to={`/users/${userId}`} className="custom-link">
                  <img src="/Image/myinfo.png" height="50" alt="내 정보" />
                </Link>
              </div>
          )}

          <div className="item rightend">
            <Link to={"/bookmark"} className="custom-link">
              <img src="/Image/bookmark.png" height="50" alt="찜 목록" />
            </Link>
          </div>
        </div>
      </>
  );
}

