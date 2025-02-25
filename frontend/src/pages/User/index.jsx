import { getUser } from "./api";
import { Alert } from "../../shared/components/Alert";
import { Spinner } from "../../shared/components/Spinner";
import { Component , useEffect , useState } from "react"
import { useParams } from "react-router-dom"

import { useRouteParamApiRequest } from "@/Shared/hooks/useRouteParamApiRequest";
import { ProfileCard } from "./components/ProfileCard";



export function User() {

    const {
        apiProgess,
        data: user,
        error,
      } = useRouteParamApiRequest("id", getUser);
    
    //getUser() den dolayı hata var
    
    
    return (
        <>
          {apiProgess && (
            <Alert styleType={"secondary"} center>
              <Spinner />
            </Alert>
          )}
          {user && <ProfileCard user={user} />}
          {error && <Alert styleType={"danger"}>{error}</Alert>}
        </>
      );
}