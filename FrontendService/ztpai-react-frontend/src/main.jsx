import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import 'bootstrap/dist/css/bootstrap.min.css';
import MainPage from './pages/MainPage.jsx';
import CartPage from './pages/CartPage.jsx';
import ErrorPage from './pages/ErrorPage.jsx';
import CartProductsContextProvider from './Contexts/CartProductsContext.jsx';
import {createBrowserRouter, RouterProvider} from 'react-router-dom'
import React from 'react';
import App from './App.jsx';



  
  

//<CartProductsContextProvider>
createRoot(document.getElementById('root')).render(
  <StrictMode>
      <App/>
  </StrictMode>,
)