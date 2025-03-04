import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";
import logo from "../../assets/LetsWriteAndShare.png";
import { useContext, useState } from "react";
import { AuthContext, useAuthDispatch, useAuthState } from "../../Shared/state/context";
import { useDispatch, useSelector } from "react-redux";
import { logoutSuccess, store } from "../../Shared/state/redux";


export function Navbar( 
   
    ) {
       
  const { t } = useTranslation();
  //const authState = useAuthState();
  //const dispatch = useAuthDispatch();
      const authState = useSelector((store) => store.auth);
      const dispatch = useDispatch();

  const onClickLogout = () =>{
     dispatch(logoutSuccess());
  }

  return (
    <nav className="container navbar navbar-expand bg-body-tertiary shadow-sm">
      <div className="container-fluid">
        <Link className="navbar-brand" to={"/"}>
          <img src={logo} width={60} alt="Logo" /> LetsWriteAndShare
        </Link>
        <ul className="navbar-nav">
          {authState?.id === 0 && (
            <>
              <li className="nav-item">
                <Link className="nav-link" to={"/login"}>
                  {t("login")}
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to={"/signup"}>
                  {t("signUp")}
                </Link>
              </li>
            </>
          )}
          {authState?.id > 0 && (
         
            <>
              <li className="nav-item">
                <Link className="nav-link" to={`/user/${authState.id}`}>
                  My Profile
                </Link>
              </li>
              <li className="nav-item">
                <span className="nav-link" role="button" onClick={onClickLogout}>
                  Logout
                </span>
              </li>
            </>
          )}
        </ul>
      </div>
    </nav>
  );
}
