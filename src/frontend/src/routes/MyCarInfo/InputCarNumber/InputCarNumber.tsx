import React from 'react';

export default function InputCarNumber() {
  return (
    <>
      <div className="popup-container" id="addCarPopup">
        <h2>차량 번호 입력</h2>
        <p>등록된 차량 번호를 입력해주세요.</p>
        <input type="text" placeholder="123가1234" />
        <button className="search-button">검색</button>
        <p className="warning-text">
          잘못된 정보 입력 시 오류가 발생할 수 있습니다.
        </p>
        <div className="button-group">
          <button className="cancel-button">취소</button>
          <button className="confirm-button">추가</button>
        </div>
      </div>
    </>
  );
}
