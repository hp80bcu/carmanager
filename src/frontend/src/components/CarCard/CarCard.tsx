import React from 'react';
import './CarCard.css'; // CSS 파일

interface CarProps {
  id: number;
  model: string;
  year: number;
  mileage: number;
  price: number;
  imageUrl: string; // 이미지 URL
}

const CarCard: React.FC<CarProps> = ({ id, model, year, mileage, price, imageUrl }) => {
  return (
    <div className="car-card">
      <img src={imageUrl} alt={model} />
      <h2>{model}</h2>
      <p>연식: {year}</p>
      <p>주행거리: {mileage}km</p>
      <p>가격: {price}원</p>
      <button>상세보기</button>
    </div>
  );
};

export default CarCard;