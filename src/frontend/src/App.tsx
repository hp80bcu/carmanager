import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Home from "./routes/Home/Home";
import SearchPage from "./routes/SearchPage/SearchPage";
import Purchase from "./routes/Purchase/Purchase";
import Sale from "./routes/Sale/Sale";
import UserInfoModify from "./routes/UserinfoModify/UserInfoModify";
import Login from "./routes/Login/Login";
import MyCarInfo from "./routes/MyCarInfo/MyCarInfo";
import CarRegister from "./routes/MyCarInfo/CarRegister";
import Bookmark from "./routes/Bookmark/Bookmark";


function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/searchpage" element={<SearchPage />} />
          <Route path="/purchase" element={<Purchase />} />
          <Route path="/sale" element={<Sale />} />
          <Route path="/mycar" element={<MyCarInfo />} />
          <Route path="/mycarRegister" element={<CarRegister />} />
          <Route path="/userinfomodify" element={<UserInfoModify />} />
          <Route path="/bookmark" element={<Bookmark />} />
          <Route path="/login" element={<Login />} />
        </Routes>
      </Router>
      
    </>
  );
}

export default App;
