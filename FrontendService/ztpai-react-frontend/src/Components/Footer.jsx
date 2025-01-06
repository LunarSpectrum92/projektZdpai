import React from "react";
import { Container, Row, Col } from "react-bootstrap";


const Footer = () => {
  return (
    <footer className="bg-light py-4">
      <Container>
        <Row>
          <Col md={4} className="d-flex align-items-center justify-content-start mb-3 mb-md-0">
            <a href="#" className="text-dark me-3">
            </a>
            <a href="#" className="text-dark me-3">
            </a>
            <a href="#" className="text-dark me-3">
            </a>
            <a href="#" className="text-dark">
            </a>
          </Col>
          <Col md={8}>
            <Row>
              <Col xs={6} md={4}>
                <h5>Categories</h5>
                <ul className="list-unstyled">
                  <li><a href="#" className="text-dark">Women's Fashion</a></li>
                  <li><a href="#" className="text-dark">Men's Fashion</a></li>
                  <li><a href="#" className="text-dark">Kids' Clothing</a></li>
                  <li><a href="#" className="text-dark">Accessories</a></li>
                  <li><a href="#" className="text-dark">Shoes</a></li>
                  <li><a href="#" className="text-dark">Outerwear</a></li>
                </ul>
              </Col>
              <Col xs={6} md={4}>
                <h5>About Us</h5>
                <ul className="list-unstyled">
                  <li><a href="#" className="text-dark">Our Story</a></li>
                  <li><a href="#" className="text-dark">Sustainability</a></li>
                  <li><a href="#" className="text-dark">Careers</a></li>
                  <li><a href="#" className="text-dark">Press</a></li>
                  <li><a href="#" className="text-dark">Contact Us</a></li>
                </ul>
              </Col>
              <Col xs={6} md={4}>
                <h5>Help & Support</h5>
                <ul className="list-unstyled">
                  <li><a href="#" className="text-dark">FAQ</a></li>
                  <li><a href="#" className="text-dark">Shipping & Returns</a></li>
                  <li><a href="#" className="text-dark">Size Guide</a></li>
                  <li><a href="#" className="text-dark">Payment Methods</a></li>
                  <li><a href="#" className="text-dark">Privacy Policy</a></li>
                  <li><a href="#" className="text-dark">Terms & Conditions</a></li>
                </ul>
              </Col>
            </Row>
          </Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;
