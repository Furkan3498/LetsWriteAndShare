import { Component , useEffect , useState } from "react"
import { useParams } from "react-router-dom"
import { getUser } from "./api";
import { useRouteParamApiRequest } from "@/Shared/hooks/useRouteParamApiRequest";



export function User() {

    const {apiProgress, data : user, error} = useRouteParamApiRequest('id', getUser() )
    
    return (
     <>
         { apiProgress }
         {user &&    <ProfileCard user={user}/> }
         {error && {error} }
     </>
    );
}