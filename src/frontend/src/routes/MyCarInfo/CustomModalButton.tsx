import * as React from 'react';
import { Button } from '@mui/material';
import styled from '@emotion/styled';

const StyledButton = styled(Button)(({ theme }) => ({
  // 버튼 스타일 커스터마이징
  backgroundColor: '#2ecc71', // 버튼 배경색 (예시)
  color: '#fff',
  '&:hover': {
    backgroundColor: '#27ae60', // 호버 시 색상 변경
  },
}));

const CustomModalButton = ({ onClick, children }: { onClick: () => void; children: React.ReactNode }) => {
  return (
    <StyledButton variant="contained" color="primary" onClick={onClick}>
      {children}
    </StyledButton>
  );
};

export default CustomModalButton;