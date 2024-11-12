import React, { useState } from "react";

import Nav from "../../components/Nav";
import CarAddPopup from "./CarAddPopup";
import CarAddPopup_1 from "./CarAddPopup_1";
import CarAddPopup_2 from "./CarAddPopup_2";
import "./CarRegister.css";

const CarRegister: React.FC = () => {
  const [isFirstPopupOpen, setIsFirstPopupOpen] = useState(false);
  const [isSecondPopupOpen, setIsSecondPopupOpen] = useState(false);
  const [isThirdPopupOpen, setIsThirdPopupOpen] = useState(false);

  const handleOpenFirstPopup = () => setIsFirstPopupOpen(true);
  const handleCloseFirstPopup = () => setIsFirstPopupOpen(false);

  const handleOpenSecondPopup = () => {
    setIsSecondPopupOpen(true);
    setIsFirstPopupOpen(false); // 첫 번째 팝업 닫기
  };
  const handleCloseSecondPopup = () => setIsSecondPopupOpen(false);

  const handleOpenThirdPopup = () => {
    setIsThirdPopupOpen(true);
    setIsSecondPopupOpen(false); // 두 번째 팝업 닫기
  };
  const handleCloseThirdPopup = () => setIsThirdPopupOpen(false);

  return (
    <>
      <Nav />
      <div className="container">
        <button className="add-car-button" onClick={handleOpenFirstPopup} style={{alignSelf:"start", marginLeft:"1rem"}}>
          차량 추가
        </button>
        </div>
      {isFirstPopupOpen && (
        <CarAddPopup
          onClose={handleCloseFirstPopup}
          onOpenNext={handleOpenSecondPopup}
        />
      )}

      {isSecondPopupOpen && (
        <CarAddPopup_1
          onClose={handleCloseSecondPopup}
          onReopenFirstPopup={handleOpenFirstPopup}
          onOpenThirdPopup={handleOpenThirdPopup}
        />
      )}

      {isThirdPopupOpen && (
        <CarAddPopup_2
          onClose={handleCloseThirdPopup}
          onReopenFirstPopup={handleOpenFirstPopup}
        />
      )}
    </>
  );
};

export default CarRegister;
