import { useContext, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import logo from  './assets/LetsWriteAndShare.png'
import { LanguageSelector } from "./Shared/components/LanguageSelector";
import { data, Link ,Outlet } from 'react-router-dom'
import { useTranslation } from 'react-i18next';
import { Login } from './pages/Login';
import { Navbar } from './Shared/components/NavBar';


function App() {
  const [ authState, setAuthState] = useState({
    id: 0
  })

  const onLoginSuccess = (data) =>{
    console.log('Auth state before update:', authState); // authState'i önceden kontrol edin
  setAuthState(data);
  console.log('Auth state after update:', data); // authState güncellendikten sonra kontrol edin

  }
  return (
    <>
     <Navbar authState={authState}/>
      <div className="container mt-3">
       
        <Login onLoginSuccess={onLoginSuccess}/>
        {/* <Outlet /> */}
        <LanguageSelector />
      </div>
    </>
  );
}

export default App;