import './HomeStyle.css'
import { useState } from 'react'

function Login() {
  const handleSubmit = (e: { preventDefault: () => void; }) => {e.preventDefault();}
  const [id, setId] = useState("")
  const [pw, setPw] = useState("")
  
  return (
    <div id="login-form">
      <form>
        <h4>로그인 하시겠습니까?</h4>
        <label>ID</label>
        <input value={id} id="id" type="text"></input><br></br>
        <label>PW</label>
        <input value={pw} id="pw" type="text"></input><br></br>
        <button onClick={handleSubmit}>로그인</button><br></br>
        <a>아직 회원이 아니에요. </a>
      </form>
    </div>
  );
}

function Home() {
  return (
    <div>
      <h1>안녕하세요!</h1>
      <Login />
    </div>
  );
}

export default Home;