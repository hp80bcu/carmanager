import React from 'react';
import './CarItem.css'; // CSS 파일 임포트
import { Car } from '../Car';

interface CarItemProps {
  car: Car;
}

const CarItem: React.FC<CarItemProps> = ({ car }) => {
  return (
    <div className="car-item">
      <div>모델: {car.model}</div>
      <div>연식: {car.year}</div>
      <div>주행거리: {car.mileage}km</div>
      <div>연료: {car.fuelType}</div>
      <div>가격: {car.price}만원</div>
    </div>
  );
};

export default CarItem;