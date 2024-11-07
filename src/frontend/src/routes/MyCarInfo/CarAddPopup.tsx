import React, { useState } from "react";
import {
  Button,
  TextField,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  InputAdornment,
} from "@mui/material";
import "./CarAddPopup.css";

interface CarAddPopupProps {
  onClose: () => void;
}

const CarAddPopup: React.FC<CarAddPopupProps> = ({ onClose }) => {
  const [carNumber, setCarNumber] = useState<string>("");

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCarNumber(event.target.value);
  };

  const handleSearch = () => {
    // 검색 로직 구현 (예시: carNumber를 이용하여 검색 API 호출)
    console.log("검색어:", carNumber);
    // 실제 검색 로직 구현
  };

  const handleSubmit = () => {
    // 차량 정보 서버 전송 로직 (예시)
    console.log("차량 번호:", carNumber);
    onClose(); // 팝업 닫기
  };

  return (
    <Dialog
      open={true}
      onClose={onClose}
      PaperProps={{
        style: {
          position: "absolute",
          top: "30%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          maxWidth: "100rem",
          maxHeight: "400rem",
        },
      }}
    >
      <DialogTitle style={{alignSelf:"center", marginTop:"3rem"}}>차량번호 입력</DialogTitle>
      <DialogContent>
        <TextField
          autoFocus
          margin="dense"
          id="carNumber"
          label="차량번호"
          type="text"
          fullWidth
          value={carNumber}
          onChange={handleInputChange}
          className="custom-textfield"
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <img
                  src={"Image/search.png"}
                  alt="검색"
                  width="20"
                  height="20"
                  onClick={handleSearch}
                />
              </InputAdornment>
            ),
          }}
        />
        <p style={{alignSelf:"center",fontSize:"10px", fontWeight:"bold"}}>
          월말에 차량 등록 시 차량번호 확인 서비스 이용에 <br></br> <td></td> 제한이 있을 수
          있습니다.
        </p>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose}>취소</Button>
        <Button onClick={handleSubmit} variant="contained" color="primary">
          추가
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default CarAddPopup;
