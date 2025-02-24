import axios from "axios";
import { useEffect, useState } from "react";   
import { Input } from "../../components/Input";
import { useTranslation } from "react-i18next";


export function Login(){


  const [email ,setEmail] = useState();
  const [password ,setPassword] = useState();
  const [apiProgress, setApiProgress] = useState(false)
  const [errors , setErrors] = useState({});
  const [generalError  , setGeneralError] = useState();
  const { t } = useTranslation();


 

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
   
 
 const onSubmit = async (event) =>{

  event.preventDefault();
  setSuccessMessage();
  setGeneralError();
  setApiProgress(true);
  
  try {
   // const  response =  await signUp({
   //    username,
   //    email,
   //  password,
   //  })
   //  setSuccessMessage(response.data.message);
  }catch (axiosError){

  //   if(axiosError.response?.data
  //    )
  //   { 
   //    if( axiosError.response.data.status === 400){
   //    setErrors(axiosError.response.data.validationErrors)}
   //    else{
   //      setGeneralError(axiosError.response.data.message) 
  //     }
    // }else{
   //    setGeneralError(t('genericError'));
    // }
  }finally{
   //  setApiProgress(false)
  }
 
 // .then((response) =>{
   // setSuccessMessage(response.data.message)
  //})
  //.finally(() =>setApiProgress(false))
 }





    
    


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
      <button 
      className="btn btn-primary"
      disabled={ apiProgress  || (!password || password !== passwordRepeat)}  >
        { apiProgress &&  <span class="spinner-border spinner-border-sm" aria-hidden="true"></span>}
        {t('login')}   </button>
      </div>
      </div>
      
      
    
    </form>
   
      </div>
    
    </div>
  );
}