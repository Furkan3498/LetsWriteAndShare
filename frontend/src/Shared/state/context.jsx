import { createContext, useContext, useEffect, useState } from "react";
import { loadAuthState, storeAuthState } from "./storage";
import { useReducer } from "react";

export const AuthContext = createContext();
export const AuthDispatchContext = createContext();

export function useAuthState(){
    return useContext(AuthContext);
}
export function useAuthDispatch(){
    return useContext(AuthDispatchContext);
}


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
        value={authState} >
            <AuthDispatchContext.Provider value={dispatch}>
            {children}
            </AuthDispatchContext.Provider>
                


        </AuthContext.Provider>
    );
}