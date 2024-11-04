import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

import Home from "./routes/Home";
import SearchPage from "./routes/SearchPage";
import Purchase from "./routes/Purchase";
import Sale from "./routes/Sale";
import UserInfoModify from "./routes/users/UserInfoModify";


function App() {
  const [hello, setHello] = useState('');

  useEffect(() => {
      axios.get('/api/test')
          .then((res) => {
              setHello(res.data);
          })
  }, []);
  return (
      <div className="App">
          백엔드 데이터 : {hello} 입니다.
      </div>
  );
}

// function App() {
//   return (
//     <>
//       <Router>
//         <Routes>
//           <Route path="/" element={<Home />} />
//           <Route path="/searchpage" element={<SearchPage />} />
//           <Route path="/purchase" element={<Purchase />} />
//           <Route path="/sale" element={<Sale />} />
//           <Route path="/users/userinfomodify" element={<UserInfoModify />} />
//           <Route path="/users/login" element={<Home />} />
//         </Routes>
//       </Router>
      
//     </>
//   );
// }





export default App;