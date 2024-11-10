import React, { useState } from "react";

// import { Box } from "@mui/material";
import Nav from "../../components/Nav";
// import { Button } from "@mui/material";
import "./UserInfoModify.css";

interface UserInfo {
  nickname: string;
  phoneNumber: string;
  address: string;
}

const UserInfoModify: React.FC = () => {
  const [userInfo, setUserInfo] = useState<UserInfo>({
    nickname: "",
    phoneNumber: "",
    address: "",
  });

  const handleWithdrawButtonClick = () => {
    // 여기에 회원 탈퇴 기능 구현
    console.log("회원 탈퇴 버튼 클릭됨!");
    // 예시:
    // - 회원 탈퇴 확인 다이얼로그 띄우기
    // - 백엔드 서버로 회원 탈퇴 요청 보내기 (API 호출)
    // - 사용자 세션 및 데이터 삭제
    // - 로그인 페이지로 이동
  }

  const handleCancleButtonClick = () => {
    // 여기에 취소 기능 구현
    console.log("취소 버튼 클릭됨!");
    // 예시:
    // - 회원 탈퇴 확인 다이얼로그 띄우기
    // - 백엔드 서버로 회원 탈퇴 요청 보내기 (API 호출)
    // - 사용자 세션 및 데이터 삭제
    // - 로그인 페이지로 이동
  }

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setUserInfo({ ...userInfo, [name]: value });
  };
  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // 여기에 실제 서버에 데이터를 전송하는 로직 추가
    console.log("Submit:", userInfo);
    console.log("저장 버튼 클릭됨!");
  };
  return (
    <>
      <Nav />
      <div className="container">
        <div className="user-info-edit">
          <h3>회원정보 수정</h3>
          <form onSubmit={handleSubmit}>
            <div>
              <label htmlFor="nickname">닉네임</label>
              <input
                type="text"
                id="nickname"
                name="nickname"
                placeholder="User"
                value={userInfo.nickname}
                onChange={handleChange}
                style={{ width: "100px" }}
              />
            </div>
            <div>
              <label htmlFor="phoneNumber">전화번호</label>
              <input
                type="text"
                id="phoneNumber"
                name="phoneNumber"
                placeholder="‘-’ 생략하여 입력"
                value={userInfo.phoneNumber}
                onChange={handleChange}
                style={{ width: "250px" }}
              />
            </div>
            <div>
              <label htmlFor="address">주소</label>
              <input
                type="text"
                id="address"
                name="address"
                placeholder="상세 주소를 입력"
                value={userInfo.address}
                onChange={handleChange}
                style={{ width: "250px" }}
              />
            </div>
            <div className="button-container">
              <button type="button" onClick={handleCancleButtonClick}>취소</button>
              <button type="submit">저장</button>
            </div>
          </form>
        </div>
          <div className="button-container2">
            <button type="button" className="delete-button" onClick={handleWithdrawButtonClick}>
              회원 탈퇴
            </button>
          </div>
      </div>
    </>
  );
};

export default UserInfoModify;