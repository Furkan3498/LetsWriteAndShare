import { createBrowserRouter } from "react-router-dom";
import { SignUp } from "@/pages/SignUp";
import { Home } from "@/pages/Home";
import App from "@/App";
import { Activation } from "@/pages/Activation";

export default createBrowserRouter([
  {
        path: "/",
        Component: App,
        children : [ 
            {
                path : "/",
                index : true,
                Component : Home
            },
            {
                path : "/signUp",
                Component : SignUp
            },
            {
                path : "/activation/:token",
                Component :Activation
            },
        ]



  }

])
