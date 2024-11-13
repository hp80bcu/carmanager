export interface Car {
    id: number;
    model: string;
    year: number;
    mileage: number;
    fuelType: string;
    price: number;
    // ... 기타 속성
  }
  
  export interface SearchResult {
    cars: Car[];
  }

