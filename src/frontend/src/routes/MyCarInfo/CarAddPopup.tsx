import React, { useState } from "react";
import { Button, TextField, Modal, Box, InputAdornment } from "@mui/material";

interface CarAddPopupProps {
  onClose: () => void;
  onOpenNext: () => void;
}

const CarAddPopup: React.FC<CarAddPopupProps> = ({ onClose, onOpenNext}) => {
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
    onOpenNext(); // 두 번째 팝업 열기
  };
  return (
    <>
    <Modal
      open={true}
      onClose={onClose}
      aria-labelledby="car-modal-title"
      aria-describedby="car-modal-description"
    >
      <Box
        sx={{
          position: "absolute",
          top: "40%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          width: 350,
          height: "auto",
          bgcolor: "background.paper",
          boxShadow: 24,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <h2
          id="car-modal-title"
          style={{ marginRight: "1.2rem", marginTop: "3rem" }}
        >
          차량번호 입력
        </h2>
        <p style={{ fontWeight: "bold", marginTop: "0.5rem" }}>
          등록할 차량 번호를 입력해주세요.
        </p>
        <TextField
          autoFocus
          margin="dense"
          id="carNumber"
          type="text"
          value={carNumber}
          onChange={handleInputChange}
          placeholder="123가4567"
          className="custom-textfield"
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <Box
                  sx={{
                    backgroundColor: "#009688", 
                    borderRadius: "0 4px 4px 0",
                    padding: "13px",
                    display: "flex",
                    alignItems: "center",
                    cursor: "pointer",
                  }}
                  onClick={handleSearch}
                >
                  <img src="/Image/search.png" height="30" alt="검색"></img>
                </Box>
              </InputAdornment>
            ),
            style: {
              paddingRight: 0,
            },
          }}
        />
        <Box
          sx={{
            width: "78%",
            height: "1px",
            backgroundColor: "#D9D9D9",
            marginTop: "3.5rem",
          }}
        />
        <p
          style={{
            fontSize: "13px",
            textAlign: "center",
            margin: "2rem 4px 10px 4px",

            fontWeight: "bolder",
          }}
        >
          월말에 차량 등록 시 차량번호 확인 서비스 이용에 제한이 있을 수
          있습니다.
        </p>
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            width: "100%",
            marginTop: "1rem",
          }}
        >
          <Button
            onClick={onClose}
            sx={{
              height: "50px",
              fontSize: "1rem", 
              flex: 1,
              backgroundColor: "#00BFA5",
              color: "#FFFFFF",
              fontWeight: "bold",
              borderRadius: "0px",
              "&:hover": {
                backgroundColor: "#009688", 
              },
            }}
          >
            취소
          </Button>
          <Button
            onClick={handleSubmit}
            sx={{
              height: "50px", 
              fontSize: "1rem", 
              flex: 1,
              backgroundColor: "#00BFA5",
              color: "#FFFFFF",
              fontWeight: "bold",
              borderRadius: "0px", 
              "&:hover": {
                backgroundColor: "#009688", 
              },
            }}
          >
            추가
          </Button>
        </Box>
      </Box>
    </Modal>
    </>
  );
};

export default CarAddPopup;
