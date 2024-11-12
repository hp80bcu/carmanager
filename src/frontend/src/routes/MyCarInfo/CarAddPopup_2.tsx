import React, { useState } from "react";
import { Button, Modal, Box } from "@mui/material";

interface CarAddPopup_2Props {
  onClose: () => void;
  onReopenFirstPopup?: () => void; // 선택적 prop으로 변경
}

const CarAddPopup_2: React.FC<CarAddPopup_2Props> = ({
  onClose,
  onReopenFirstPopup,
}) => {
  const handleNoClick = () => {
    onClose(); // 현재 팝업 닫기
    if (onReopenFirstPopup) {
        onReopenFirstPopup(); // 첫 번째 팝업 열기
      }
  };

  const handleSubmit = () => {
    // 차량 정보 서버 전송 로직 (예시)
    console.log("handleSubmit");
    onClose(); // 팝업 닫기
  };
  return (
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
        <img src="/Image/logo.png" alt="차량이미지"></img>
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
          추가하려는 차량이 맞으신가요?
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
            onClick={handleNoClick}
            sx={{
              height: "50px", // Adjust the height as needed
              fontSize: "1rem", // Adjust the font size
              flex: 1,
              backgroundColor: "#00BFA5",
              color: "#FFFFFF",
              fontWeight: "bold",
              borderRadius: "0px", // No border-radius for squared edges
              "&:hover": {
                backgroundColor: "#009688", // Darker shade on hover
              },
            }}
          >
            아니요(모델 변경)
          </Button>
          <Button
            onClick={handleSubmit}
            sx={{
              height: "50px", // Adjust the height as needed
              fontSize: "1rem", // Adjust the font size
              flex: 1,
              backgroundColor: "#00BFA5",
              color: "#FFFFFF",
              fontWeight: "bold",
              borderRadius: "0px", // No border-radius for squared edges
              "&:hover": {
                backgroundColor: "#009688", // Darker shade on hover
              },
            }}
          >
            확인(계속 진행)
          </Button>
        </Box>
      </Box>
    </Modal>
  );
};

export default CarAddPopup_2;
