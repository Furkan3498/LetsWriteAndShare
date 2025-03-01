import axios from "axios";
import { useEffect, useState } from "react";   
//import { Input } from "../../components/Input";
import { Input } from "@/components/Input";
import { useTranslation } from "react-i18next";
import { Button } from "@/Shared/components/Button";
import { login } from "./api";


import { useContext } from "react";
import { AuthContext } from "@/Shared/state/context";  // AuthContext'i import et
import { useNavigate } from "react-router-dom";

export function Login() {
  const { onLoginSuccess } = useContext(AuthContext);


  const [email ,setEmail] = useState();
  const [password ,setPassword] = useState();
  const [apiProgress, setApiProgress] = useState();
  const [errors , setErrors] = useState({});
  const [generalError  , setGeneralError] = useState();
  const { t } = useTranslation();
  const navigate = useNavigate();


 

  useEffect(() =>{
 
    setErrors(function(lastErrors){
      return{
        ...lastErrors,
        email: undefined
      };
    });
  }, [email])
  useEffect(() =>{
    
    setErrors(function(lastErrors){
      return{
        ...lastErrors,
        password: undefined
      }
    });
  }, [password])
   
 
  const onSubmit = async (event) => {
    event.preventDefault();
    setGeneralError();
    setApiProgress(true);
  
    try {
      const response = await login({ email, password });
     
      if (response.data && response.data.userDto) {
        
        onLoginSuccess(response.data.userDto); 
        navigate("/")
      } else {
        console.error('User data not found in response');
      }
    } catch (axiosError) {
      console.error('Error during login:', axiosError);
      if (axiosError.response?.data) {
        if (axiosError.response.data.status === 400) {
          setErrors(axiosError.response.data.validationErrors);
        } else {
          setGeneralError(axiosError.response.data.message);
        }
      } else {
        setGeneralError(t('genericError'));
      }
    } finally {
      setApiProgress(false);
    }
  };



    
    


    return (
    <div className="container">
      <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sml-2">
      <form className="card" onSubmit={onSubmit}>
      <div className="text-center card-header">
      <h1>{t('login')}</h1>
      </div>
      <div className="card-body">
       
       <Input id="email" label={t('email')} error={errors.email } onChange={(event) => setEmail(event.target.value)}     />
       <Input id="password" label={t('password')} error={errors.password } onChange={(event) => setPassword(event.target.value)}  type = "password"   />
      
    
   
        {generalError && (
      <div className="alert alert-danger" role="alert">
        {generalError} </div>)}
      <div className="text-center">
      <Button  disabled={!email || !password}
      apiProgress={apiProgress}
      >
       
        {t("login")}
      </Button>
      </div>
      </div>
      
      
    
    </form>
   
      </div>
    
    </div>
  );
}