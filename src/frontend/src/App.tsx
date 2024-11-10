import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Home from "./routes/Home";
import SearchPage from "./routes/SearchPage";
import Purchase from "./routes/Purchase";
import Sale from "./routes/Sale";
import UserInfoModify from "./routes/users/UserInfoModify";
import Login from "./routes/users/login";


function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/searchpage" element={<SearchPage />} />
          <Route path="/purchase" element={<Purchase />} />
          <Route path="/sale" element={<Sale />} />
          <Route path="/users/:userId" element={<UserInfoModify />} /> {/* 수정된 부분 */}
          <Route path="/users/login" element={<Login />} />
        </Routes>
      </Router>
      
    </>
  );
}





export default App;