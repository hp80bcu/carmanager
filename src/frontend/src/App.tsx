import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Home from "./routes/Home";
import SearchPage from "./routes/SearchPage";
import Nav from "./components/Nav";
import Purchase from "./routes/Purchase";
import Sale from "./routes/Sale";

function App() {
  return (
    <>
      <Router>
        <Nav />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/searchpage" element={<SearchPage />} />
          <Route path="/purchase" element={<Purchase />} />
          <Route path="/sale" element={<Sale />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
