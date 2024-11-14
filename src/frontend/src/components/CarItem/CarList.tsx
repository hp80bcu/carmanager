import React, { useEffect, useState } from 'react';
import axios from 'axios';
import CarCard from '../CarCard/CarCard';

interface CarData {
  id: string;
  imageUrl: string;
  name: string;
  year: string;
  mileage: string;
  location: string;
  fuelType: string;
  price: string;
}

const CarList: React.FC = () => {
  const [cars, setCars] = useState<CarData[]>([]);

  useEffect(() => {
    const fetchCars = async () => {
      try {
        const response = await axios.get('/api/cars');
        setCars(response.data);
      } catch (error) {
        console.error("Error fetching car data:", error);
      }
    };
    fetchCars();
  }, []);

  return (
    <div className="car-list">
      {cars.map(car => (
        <CarCard
          key={car.id}
          imageUrl={car.imageUrl}
          name={car.name}
          year={car.year}
          mileage={car.mileage}
          location={car.location}
          fuelType={car.fuelType}
          price={car.price}
        />
      ))}
    </div>
  );
};

export default CarList;