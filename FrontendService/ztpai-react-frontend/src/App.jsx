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
import AdminPage from './pages/AdminPage.jsx';



const App = () => {


const [client, isLogin, token] = useAuth();


const router = createBrowserRouter([
  {
    path: '/',
    element:  <MainPage token={token} /> ,
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
    element: <ProductPage token={token}/>
  },{
    path: '/products', 
    element: <ProductsPage token={token}/>
  },{
    path: '/account',
    element: <AccontPage token={token} client={client}/>
  },{
    path: "/about",
    element: <AboutUs/>
  },{
    path: "/admin",
    element: <AdminPage token={token}/>
  }
])  

  return (

  <CartProductsContextProvider>
      <RouterProvider router={router}/>
  </CartProductsContextProvider>

);
};



export default App
