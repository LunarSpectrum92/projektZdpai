import React, { useState,useContext, useEffect } from 'react';
import { Navbar, Nav, NavDropdown, Offcanvas, Form, Button,Badge } from 'react-bootstrap';
import { CartProductsContext } from '../Contexts/CartProductsContext';
import {Link, useNavigate} from 'react-router-dom'
import useGetFetch from "../hooks/useGetFetch.jsx";
import useGetFetchWithCallback from '../hooks/useGetFetchWithCallback';


const OffcanvasNavbar = ({token}) => {
  const [showOffcanvas, setShowOffcanvas] = useState(false);
  const { cart} = useContext(CartProductsContext);
  const [cartNumber, setCartNumber] = useState(0);
  const handleOffcanvasToggle = () => setShowOffcanvas(!showOffcanvas);
  const navigate = useNavigate();


  const { data: categories, loadingCategories, errorCategories } = useGetFetch(
    "http://localhost:8222/api/categories/categories",
    token
  );





  const [searchTerm, setSearchTerm] = useState("");

  const handleSearch = async (e) => {
    e.preventDefault(); 
    console.log("Fetched Data");

    if (!searchTerm.trim()) return; 


      navigate("/products", {
        replace: true,
        state: {
          url: `http://localhost:8222/api/products/product/search?prompt=${encodeURIComponent(searchTerm)}`,
        },
      });
      
      
      console.log("Fetched Data");

  };


useEffect(() => {
  setCartNumber(cart.reduce((accumulator ,prod) =>{return accumulator + prod.quantityCart } , 0))
}, [cart])


  return (
    <Navbar expand="lg" bg="light" variant="light" sticky='top'>
      <Navbar.Brand href="/" className="ms-4">
        GreenThreads
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="offcanvasNavbarDropdown-expand-lg" className="me-2 border-0" onClick={handleOffcanvasToggle} />
      <Navbar.Collapse id="offcanvasNavbarDropdown-expand-lg">
        <Navbar.Offcanvas
          id="offcanvasNavbarDropdown-expand-lg"
          aria-labelledby="offcanvasNavbarDropdown-expand-lg"
          placement="end"
          show={showOffcanvas}
          onHide={handleOffcanvasToggle} 
        >
          <Offcanvas.Header closeButton>
            <Offcanvas.Title id="offcanvasNavbarDropdown-expand-lg">
              Offcanvas
            </Offcanvas.Title>
          </Offcanvas.Header>
          <Offcanvas.Body>
            <Nav className="justify-content-end flex-grow-1 pe-3">
              <Nav.Link href="/products">Products</Nav.Link>

            {loadingCategories ? (
                      <Spinner animation="border" />
                    ) : errorCategories ? (
                      <p>Błąd ładowania kategorii: {errorCategories}</p>
                    ) : (
                      <NavDropdown
                      title="Categories"
                      className={`me-2 ${showOffcanvas ? 'mb-5' : ''}`} 
                      id="offcanvasNavbarDropdown"
                    >
                       
                             <NavDropdown.Item
                            as={Link}
                            to="/products"
                            state={{
                              url: "http://localhost:8222/api/products/product/all"
                            }}
                            eventKey="All"
                          >
                            All  
                        </NavDropdown.Item>
                        {categories && categories.length > 0 ? (
                          categories.map((category) => (
                            <NavDropdown.Item
                            as={Link}
                            to="/products"
                            
                            state={{
                              url: `http://localhost:8222/api/products/product/category?categoryId=${encodeURIComponent(category.categoryId)}`
                            }}
                            eventKey={category.categoryId}
                          >
                              {category.categoryName}
                            </NavDropdown.Item>
                          ))
                        
                        ) : (
                          <NavDropdown.Item disabled>No categories available</NavDropdown.Item>
                        )}

            </NavDropdown>

                    )

                    }
            </Nav>
            <Form className="d-flex" onSubmit={handleSearch}>
  <Form.Control
    type="search"
    placeholder="Search for products"
    className="me-2"
    aria-label="Search"
    value={searchTerm}
    onChange={(e) => setSearchTerm(e.target.value)}
  />
  <Button type="submit" variant="outline-success" className="me-2">
    Search
  </Button>
              <Link to='/account' className="me-1 w-50">
              <Button variant="outline-success" className="me-1">Account</Button>
              </Link>

              <Link to='/cart' className="me-1 w-50">
              <Button variant="warning" className="me-1 w-100" >cart {cartNumber === 0 ?  null : <Badge bg="secondary"  >{cartNumber} </Badge> }</Button>
              </Link>
            </Form>




                    
          </Offcanvas.Body>
        </Navbar.Offcanvas>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default OffcanvasNavbar;
