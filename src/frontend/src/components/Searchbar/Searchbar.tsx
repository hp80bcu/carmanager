import React, { useState } from "react";
import {
  Paper,
  IconButton,
} from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import InputBase from "@mui/material/InputBase";

interface SearchBarProps {
  onSearch: (searchTerm: string) => void;
  placeholder?: string;
}

const SearchBar: React.FC<SearchBarProps> = ({ onSearch }) => {
  const [searchTerm, setSearchTerm] = useState("");

  const handleSearch = () => {
    //검색어를 이용한 실제 검색 로직 수행
    onSearch(searchTerm);
    console.log("검색어:", searchTerm);
    // 예시: API 호출, 데이터 조회 등
  };

  return (
    <>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Paper
          component="form"
          sx={{
            p: "0px 4px",
            display: "flex",
            alignItems: "center",
            width: "100vw",
            marginLeft: "20rem",
            marginRight: "15rem",
            boxShadow: "none",
            border: "1px solid #D9D9D9", // 테두리 추가
            borderRadius: "10px", // 둥근 모서리
            backgroundColor: "#fffff", // 배경색 변경
          }}
        >
          <InputBase
            sx={{ ml: 1, flex: 1 }}
            placeholder="xxx,xxx 대의 차량이 등록 되어 있습니다!."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            // inputProps={{ "aria-label": "search google maps" }}
          />
          <IconButton
            type="button"
            sx={{ p: "10px" }}
            aria-label="search"
            onClick={handleSearch}
          >
            <SearchIcon />
          </IconButton>
        </Paper>
      </div>
    </>
  );
};

export default SearchBar;
