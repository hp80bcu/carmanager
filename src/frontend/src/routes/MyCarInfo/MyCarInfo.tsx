import React, { useState } from "react";
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogTitle } from '@mui/material';
import Nav from "../../components/Nav";
import CarAddPopup from "./CarAddPopup";
import "./MyCarinfo.css";

// 메인 컴포넌트
interface MyCarInfoProps {}

const MyCarInfo: React.FC<MyCarInfoProps> = () => {
  const [isPopupOpen, setIsPopupOpen] = useState(false);

  const handleAddCar = () => {
    setIsPopupOpen(true);
  };

  const handleClose = () => {
    setIsPopupOpen(false);
  };

  return (
    <>
      <Nav />
      <h2>내 차 관리</h2>
      <div className="my-car-container">
        <p style={{fontWeight:"bold", fontSize:"20px"}}>내 차 관리는 차매니저에서!</p>
        <p style={{color:"#7A7A7A"}}>차량을 추가하시면 다양한 정보를 한 눈에 볼 수 있습니다.</p>
        <button className="add-car-button" onClick={handleAddCar}>
          차량 추가
        </button>
      </div>
      {isPopupOpen && <CarAddPopup onClose={handleClose} />}
    </>
  );
};

export default MyCarInfo;
