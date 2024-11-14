
import React, { useEffect, useState } from "react";
import SideNav from "../../components/SideNav";
import SearchBar from "../../components/Searchbar/Searchbar";
import CarList from "../cars/CarItem/CarList";
import { Car, SearchResult } from "../../components/Car";
// import carsData from '../../components/cars.json'; // 예시 데이터

const SearchPage = () => {
  const [searchResults, setSearchResults] = useState<Car[]>([]);

  const totalCars = 150000; // 예시 데이터 값 (API 호출로 가져온 데이터로 설정 가능)

  const handleSearch = async (searchTerm: string) => {
    // 검색 로직 구현 (예: API 호출, 데이터베이스 조회 등)
    const response = await fetch(`/api/cars?search=${searchTerm}`);
    const data: SearchResult = await response.json();
    setSearchResults(data.cars);
  };

  useEffect(() => {
    // 초기 데이터 로딩
    const fetchData = async () => {
      const response = await fetch("/api/cars");
      const data: SearchResult = await response.json();
      setSearchResults(data.cars);
    };
    fetchData();
  }, []);

  return (
    <>
      <SideNav />
      <SearchBar placeholder={`현재 ${totalCars.toLocaleString()} 대의 차량이 등록되어 있습니다!`} onSearch={handleSearch} />
      {/* <CarList cars={searchResults} /> */}
    </>
  );
};

export default SearchPage;
