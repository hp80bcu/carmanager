import { Link } from "react-router-dom";
import Button from "@mui/material/Button";
import Box from '@mui/material/Box';
import "./Nav.css";
import "./Custom-Link.css";

export default function Nav() {
  return (
    <>
      <Box sx={{ display: 'flex', justifyContent: 'flex-end'}}>
        <Button
          variant="text"
          sx={{
            m: 0,
            color: "black",
            display: { xs: 'none', sm: 'none', lg: 'block' },
          }}>
          로그인
        </Button>
      </Box>
      <div className="h-container">
        <div className="logo">
          <Link to={"/"}>
            <img src="/logo.png" height="80" alt="로고"></img>
          </Link>
        </div>
        <div className="item middle">
          <Link to={"/searchpage"} className="custom-link">검색</Link>
        </div>
        <div className="item middle">
          <Link to={"/purchase"} className="custom-link">구매</Link>
        </div>
        <div className="item middle">
          <Link to={"/sale"} className="custom-link">판매</Link>
        </div>
        <div className="item middle">
          <div className="hypen">
            <text>|</text>
          </div>
        </div>
        <div className="item right">
          <Link to={"/userinfomodify"} className="custom-link">내차량</Link>
        </div>
        <div className="item right">
          <Link to={"/userinfomodify"} className="custom-link">내정보</Link>
        </div>
        <div className="item rightend">
          <Link to={"/userinfomodify"} className="custom-link">찜목록</Link>
        </div>
      </div>
    </>
  );
}
