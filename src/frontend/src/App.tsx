import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Home from "./routes/Home";
import SearchPage from "./routes/SearchPage";
import Purchase from "./routes/Purchase";
import Sale from "./routes/Sale";
import UserInfoModify from "./routes/UserInfoModify";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/searchpage" element={<SearchPage />} />
          <Route path="/purchase" element={<Purchase />} />
          <Route path="/sale" element={<Sale />} />
          <Route path="/userinfomodify" element={<UserInfoModify />} />
        </Routes>
      </Router>
      
    </>
  );
}

export default App;
