import React, { useState } from "react";
import Nav from "../../components/Nav";
import "./MyCarinfo.css";

// 팝업 컴포넌트
interface CarAddPopupProps {
  onClose: () => void;
}

const CarAddPopup: React.FC<CarAddPopupProps> = ({ onClose }) => {
  return (
    <div className="popup-container">
      <h2>차량 번호 입력</h2>
      <p>등록된 차량 번호를 입력해주세요.</p>
      <input type="text" placeholder="123가1234" />
      <button className="search-button">검색</button>
      <p className="warning-text">
        잘못된 정보 입력 시 오류가 발생할 수 있습니다.
      </p>
      <div className="button-group">
        <button className="cancel-button" onClick={onClose}>
          취소
        </button>
        <button className="confirm-button">추가</button>
      </div>
    </div>
  );
};

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
        <div className="my-car-container">
          <h2>내 차 관리</h2>
          <p>내 차 관리는 차매니저에서!</p>
          <p>차량을 추가하시면 다양한 정보를 한 눈에 볼 수 있습니다.</p>
          <button className="add-car-button" onClick={handleAddCar}>차량 추가</button>
        </div>
        {isPopupOpen && <CarAddPopup onClose={handleClose} />}
      </>
    );
  };
  
  export default MyCarInfo;
