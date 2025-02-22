import { useEffect , useState } from "react"
import { useParams } from "react-router-dom"
import {  activateUser } from "./api";
import { useRouteParamApiRequest } from "@/Shared/hooks/useRouteParamApiRequest";

export function Activation(){
          const {apiProgress, data,error} =    useRouteParamApiRequest('token', activateUser)
   
   return (
    <>

        { apiProgress &&  (<span class="spinner-border " aria-hidden="true"></span>)}
        {data.message && (
      <div className="alert alert-success">
        {data?.message} </div>)}
        {error && 
      <div className="alert alert-danger" >
        {error} </div>}
    </>
   );
}