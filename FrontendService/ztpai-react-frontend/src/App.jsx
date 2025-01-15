import 'bootstrap/dist/css/bootstrap.min.css';
import useAuth from './hooks/useAuth.jsx';
import MainPage from './pages/MainPage';
import CartPage from './pages/CartPage';
import ErrorPage from './pages/ErrorPage.jsx';
import ProductPage from './pages/ProductPage.jsx';
import ProductsPage from './pages/ProductsPage.jsx';
import CartProductsContextProvider from './Contexts/CartProductsContext.jsx';
import React from 'react';
import {createBrowserRouter, RouterProvider} from 'react-router-dom'
import AccontPage from './pages/AccontPage.jsx';
import AboutUs from './pages/AboutUs.jsx';


const App = () => {


  const [isLogin, token] = useAuth();

const router = createBrowserRouter([
  {
    path: '/',
    element:  <MainPage /> ,
    errorElement: <ErrorPage/>,
  },
  {
    path: '/error',
    element: <ErrorPage/>,
  },
  {
    path: '/cart',
    element: <CartPage/>
  },
  {
    path: '/product/:productId',
    element: <ProductPage />
  },{
    path: '/products', 
    element: <ProductsPage />
  },{
    path: '/account',
    element: <AccontPage />
  },{
    path: "/about",
    element: <AboutUs/>
  }
])  

  return (

  <CartProductsContextProvider>
      <RouterProvider router={router}/>
  </CartProductsContextProvider>

);
};



export default App
