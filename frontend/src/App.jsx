import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import logo from  './assets/LetsWriteAndShare.png'
import { LanguageSelector } from "./Shared/components/LanguageSelector";
import { Link ,Outlet } from 'react-router-dom'
import { useTranslation } from 'react-i18next';

function App() {

  const {t} = useTranslation();


  return (
    <>
       <nav className="navbar navbar-expand bg-body-tertiary shadow-sm">
       <div className="container-fluid">
       <Link className="navbar-brand" to="/">
        <img src={logo} width={60}/>  
        LetsWriteAndShare
       </Link>
        <ul className="navbar-nav">
          <li className="navbar-item">
            <Link class Name="nav-link" to = "/signup"> {t('signUp')}</Link>
          </li>
        </ul>
       </div>
       </nav>
       <div className="container mt-3">
     <Outlet/>
     <LanguageSelector />
     </div>
    </>
  );
}

export default App
