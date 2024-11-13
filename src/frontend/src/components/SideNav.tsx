import React from "react";
import { Link } from "react-router-dom";
import "./SideNav.css"; // CSS 파일
import "./Custom-Link.css";

const SideNav: React.FC = () => {
  return (
    <nav className="side-navigation">
      <ul>
        <div className="">
          <Link to={"/"}>
            <img src="/Image/logo.png" height="80" alt="로고"></img>
          </Link>
        </div>
      </ul>

      <ul>
        <div className="items">
          <Link to={"/searchpage"} className="custom-link">
            검색
          </Link>
        </div>
      </ul>

      <ul>
        <div className="items">
          <Link to={"/purchase"} className="custom-link">
            구매
          </Link>
        </div>
      </ul>

      <ul>
        <div className="items">
          <Link to={"/sale"} className="custom-link">
            판매
          </Link>
        </div>
      </ul>
    </nav>
  );
};

export default SideNav;
