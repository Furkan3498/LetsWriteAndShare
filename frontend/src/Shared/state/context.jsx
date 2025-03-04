import { createContext, useEffect, useState } from "react";
import { loadAuthState, storeAuthState } from "./storage";
import { useReducer } from "react";

export const AuthContext = createContext();


const authReducer = (authState, action) =>{
    switch(action.type){
        case 'login-success':
            return action.data
        case 'logout-success':
            return{id:0}
        default:
            throw new Error('unknown action: ${action.type}')        
    }

}

export function AuthenticationContext({children}) {

     const [authState,dispatch  ] = useReducer (authReducer,    loadAuthState())
  //  const[auth, setAuth] = useState(loadAuthState());

  useEffect(() =>{
    storeAuthState(authState);

  },[authState])

   //  const onLoginSuccess = (data) => {
  //       setAuth(data);
   //      storeAuthState(data);
  //   };
  //   const onLogoutSuccess= ()=>{
  //      setAuth({id :0})
  //       storeAuthState({id :0})
  //   }


    return(
        <AuthContext.Provider
        value={{
            ...authState,
          dispatch
        }}
        
        >
                {children}


        </AuthContext.Provider>
    );
}