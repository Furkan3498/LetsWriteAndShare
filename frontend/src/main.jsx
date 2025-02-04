import React, { StrictMode } from 'react'
import ReactDom from 'react-dom/client'

 

import "./styles.scss"
import "./locales"
import { RouterProvider } from 'react-router-dom'
import router from './router/index.js'




ReactDom.createRoot(document.getElementById('root')).render(
  
    <RouterProvider router={router}  />
  
)
