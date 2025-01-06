import React, { useState } from 'react';
import { Navbar, Nav, NavDropdown, Offcanvas, Form, Button } from 'react-bootstrap';

const OffcanvasNavbar = () => {
  const [showOffcanvas, setShowOffcanvas] = useState(false);

  
  const handleOffcanvasToggle = () => setShowOffcanvas(!showOffcanvas);

  return (
    <Navbar expand="lg" bg="light" variant="light" sticky='top'>
      <Navbar.Brand href="#" className="ms-4">
        Brand
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="offcanvasNavbarDropdown-expand-lg" className="me-2" onClick={handleOffcanvasToggle} />
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
              <Nav.Link href="#home">Home</Nav.Link>
              <Nav.Link href="#link">Link</Nav.Link>
              <NavDropdown
                title="Dropdown"
                className={`me-2 ${showOffcanvas ? 'mb-5' : ''}`} 
                id="offcanvasNavbarDropdown"
              >
                <NavDropdown.Item href="#action3">Action</NavDropdown.Item>
                <NavDropdown.Item href="#action4">Another action</NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item href="#action5">Something else here</NavDropdown.Item>
              </NavDropdown>
            </Nav>
            <Form className="d-flex">
              <Form.Control
                type="search"
                placeholder="Search for products"
                className="me-2"
                aria-label="Search"
              />
              <Button variant="outline-success" className="me-2">
                Search
              </Button>
              <Button variant="warning" className="me-1">LogIn</Button>
            </Form>
          </Offcanvas.Body>
        </Navbar.Offcanvas>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default OffcanvasNavbar;
