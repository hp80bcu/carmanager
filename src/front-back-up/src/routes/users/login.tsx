import { Box, Container, TextField } from "@mui/material";
import "./Home.css";

export default function Home() {
  return (
    <>
      <div className="container">
        <div className="ad">
          <text>A.D</text>
        </div>
      </div>
      <div className="container">
        <div className="modelsearch">
          <text >모델 검색</text>
          <div className="modelsearchAreaLine"></div>
        </div>
      </div>
    </>
  );
}