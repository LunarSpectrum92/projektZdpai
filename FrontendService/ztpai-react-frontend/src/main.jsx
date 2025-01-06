import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import 'bootstrap/dist/css/bootstrap.min.css';
import NavBar from './Components/NavBar.jsx';
import MainPage from './pages/MainPage.jsx';


createRoot(document.getElementById('root')).render(
  <StrictMode>
   <MainPage/>
    
  </StrictMode>,
)
