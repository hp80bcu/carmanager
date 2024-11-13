import React, { useState, useEffect } from "react";
import {
  InputLabel,
  FormHelperText,
  FormControl,
  MenuItem,
  styled,
} from "@mui/material";
import Select, { SelectChangeEvent } from "@mui/material/Select";
import Nav from "../../components/Nav";
import "./Home.css";

export default function Home() {
  const [manufacturer, setManufacturer] = useState("");
  const [model, setModel] = useState("");
  const [detailModel, setDetailModel] = useState("");

  const manufacturers = ["현대", "기아", "BMW", "벤츠"];
  const models = ["쏘나타", "K5", "5시리즈", "E클래스"];
  const detailModels = ["2.0", "2.5", "3.0", "3.5"];

  const handleSearch = () => {
    console.log("검색 조건:", { manufacturer, model, detailModel });
    // 여기에 실제 검색 로직 구현
  };

  const Container = styled("div")({
    display: "grid",
    gridTemplateColumns: "repeat(3, 1fr)",
    gap: "0px",
    marginTop: "50px",
    marginBottom: "20px",
    marginLeft: "50px",
    marginRight: "50px",
  });

  return (
    <>
      <Nav />
      <div className="container">
        <div className="ad">
          <text>A.D</text>
        </div>
      </div>
      <div className="container">
        <div className="modelsearch">
          <text>모델 검색</text>
          <div className="modelsearchAreaLine">
            <Container>
              <FormControl sx={{ m: 1, minWidth: 120 }} size="small">
                <InputLabel id="manufacturers">제조사</InputLabel>
                <Select
                  labelId="manufacturers"
                  id="manufacturers"
                  label="제조사"
                  value={ manufacturer }
                  
                  onChange={(e) => setManufacturer(e.target.value as string)}
                >
                  {manufacturers.map((manufacturer) => (
                    <MenuItem key={manufacturer} value={manufacturer}>
                      {manufacturer}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
              <FormControl sx={{ m: 1, minWidth: 120 }} size="small">
                <InputLabel id="models">모델명</InputLabel>
                <Select
                  labelId="models"
                  id="models"
                  value={model}
                  label="모델명"
                  onChange={(e) => setModel(e.target.value as string)}
                >
                  {models.map((model) => (
                    <MenuItem key={model} value={model}>
                      {model}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
              <FormControl sx={{ m: 1, minWidth: 120 }} size="small">
                <InputLabel id="manufacturers">세부모델</InputLabel>
                <Select
                  labelId="detailModel"
                  id="detailModel"
                  value={detailModel}
                  label="세부모델"
                  onChange={(e) => setDetailModel(e.target.value as string)}
                >
                  {detailModels.map((detailModel) => (
                    <MenuItem key={detailModel} value={detailModel}>
                      {detailModel}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Container>
            <button
              type="button"
              className="button-container"
              onClick={handleSearch}
            >
              검색
            </button>
          </div>
        </div>
      </div>
    </>
  );
}
