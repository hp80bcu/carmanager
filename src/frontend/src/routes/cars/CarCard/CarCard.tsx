import React from 'react';

interface CarCardProps {
  imageUrl: string;
  name: string;
  year: string;
  mileage: string;
  location: string;
  fuelType: string;
  price: string;
}

const CarCard: React.FC<CarCardProps> = ({ imageUrl, name, year, mileage, location, fuelType, price }) => {
  return (
    <div className="car-card">
      <img src={imageUrl} alt={name} className="car-image" />
      <h3>{name}</h3>
      <p>{year}</p>
      <p>{mileage} | {location}</p>
      <p>{fuelType}</p>
      <p>{price} 만원</p>
    </div>
  );
};

export default CarCard;