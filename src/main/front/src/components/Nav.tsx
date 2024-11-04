import { Link } from "react-router-dom";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import "./Nav.css";
import "./Custom-Link.css";

export default function Nav() {
  return (
    <>
      <Box sx={{ display: "flex", justifyContent: "flex-end" }}>
        <Button
          variant="text"
          sx={{
            m: 0,
            color: "black",
            display: { xs: "none", sm: "none", lg: "block" },
          }}
        >
          로그인
        </Button>
      </Box>
      <div className="h-container">
        <div className="logo">
          <Link to={"/"}>
            <img src="/Image/logo.png" height="80" alt="로고"></img>
          </Link>
        </div>
        <div className="item middle">
          <Link to={"/searchpage"} className="custom-link">
            검색
          </Link>
        </div>
        <div className="item middle">
          <Link to={"/purchase"} className="custom-link">
            구매
          </Link>
        </div>
        <div className="item middle">
          <Link to={"/sale"} className="custom-link">
            판매
          </Link>
        </div>
        <div className="item middle">
          <div className="hypen">
            <text>|</text>
          </div>
        </div>
        <div className="item right">
          <Link to={"/mycar"} className="custom-link">
            <img src="/Image/mycar.png" height="50" alt="내 차량"></img>
          </Link>
        </div>
        <div className="item right">
          <Link to={"/users/userinfomodify"} className="custom-link">
          <img src="/Image/myinfo.png" height="50" alt="내 정보"></img>
          </Link>
        </div>
        <div className="item rightend">
          <Link to={"/bookmark"} className="custom-link">
          <img src="/Image/bookmark.png" height="50" alt="찜 목록"></img>
          </Link>
        </div>
      </div>
    </>
  );
}