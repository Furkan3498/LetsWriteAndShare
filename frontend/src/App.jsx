import { useContext, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import logo from  './assets/LetsWriteAndShare.png'
import { LanguageSelector } from "./Shared/components/LanguageSelector";
import { data, Link ,Outlet } from 'react-router-dom'
import { useTranslation } from 'react-i18next';
import { Login } from './pages/Login';
import { Navbar } from './Shared/components/NavBar';
import { AuthContext, AuthenticationContext } from './Shared/state/context';
import { Provider } from 'react-redux';
import { store } from './Shared/state/redux';


function App() {
  

  return (
  //  <AuthenticationContext>
      <Provider store={store}>
      <Navbar />
      <div className="container mt-3">
       
       { /*<Login onLoginSuccess={onLoginSuccess}/>*/}
        <Outlet /> 
        <LanguageSelector />
      </div>
      </Provider>
    
   // </AuthenticationContext>
  );
}

export default App;