import React, { useState, useContext, useEffect } from 'react';
import {  Image, Container, Row, Col, Card,Button,  Carousel, Form ,Dropdown, DropdownButton ,ButtonGroup   } from 'react-bootstrap';
import NavBar from '../Components/NavBar.jsx'
// @ts-ignore
import reactLogo from '../assets/rb_3269.png'
import Footer from '../Components/Footer.jsx';
import {CartProductsContext} from '../Contexts/CartProductsContext.jsx';
import { products } from '../assets/products.js';
import ContactUs from '../Components/ContactUs.jsx';

const MainPage = () => {

  const {addToCart} = useContext(CartProductsContext);

  const [displayProducts, setDisplayProducts] = useState([]);

  useEffect(() => {
   setDisplayProducts(products);
  }, []);
   

  const handleAddToCart = (product) => {
    alert("added to cart")
    addToCart(product);

  };



    return (
    <>

        <NavBar/>    
      <div className="bg-light text-center py-5">
        <Container>
        <Row>
          <Col md={6} className="order-2 order-md-1">
            <Carousel variant="dark">
              <Carousel.Item interval={10000}>
                <Image src={reactLogo} className="" fluid />
              </Carousel.Item>
              <Carousel.Item interval={10000}>
                <Image src={reactLogo} className="" fluid />
              </Carousel.Item>
              <Carousel.Item interval={10000}>
                <Image src={reactLogo} className="" fluid />
              </Carousel.Item>
            </Carousel>
          </Col>
            
          <div className="w-100 d-md-none"></div>
            
          <Col md={6} className="d-flex flex-column justify-content-center align-items-center order-1 order-md-2 py-4">
            <div>
              <h1 className="display-4 fw-bold">GreenThreads</h1>
              <p className="lead">Style That Loves Nature</p>
              <Button variant="outline-secondary me-2" size="lg" href="#about">
                Man
              </Button>
              <Button variant="dark" size="lg" href="#about">
                Woman
              </Button>
            </div>
          </Col>
        </Row>
        </Container>
      </div>

      
      <Container className="my-5 ">
        <h2 className=" mb-4">Trending</h2>
        <DropdownButton
            as={ButtonGroup}
            variant="dark"
            title="filtres"
            className="mb-4"
          >
            <Dropdown.Item eventKey="1">Action</Dropdown.Item>
            <Dropdown.Item eventKey="2">Another action</Dropdown.Item>
            <Dropdown.Item eventKey="3" active>
              Active Item
            </Dropdown.Item>
            <Dropdown.Divider />
            <Dropdown.Item eventKey="4">Separated link</Dropdown.Item>
          </DropdownButton>

        <Row className="g-3 ">
        {displayProducts.map((card) =>
            <Col md={4} sm={6} xs={6} key={card.productId}>
            <Card className=" bg-bg-light-subtle h-100 shadow-sm " style={{border: '0px'}}>
                <Card.Img variant="top" className='' src={reactLogo} />
                <Card.Body className="d-flex flex-column">
                  <Card.Title>{card.productName}</Card.Title>
                  <Card.Text>
                    {card.description}
                    <br />
                    <p><b>{card.price}</b> PLN</p>
                  </Card.Text>
                  <div className="mt-auto">
                    <Button variant="outline-dark" className="w-0" onClick={() => handleAddToCart(card)}>Add to Cart</Button>
                  </div>
                </Card.Body>
            </Card>
            </Col>
        )}

        </Row>
      </Container>

      <ContactUs/>

        <Footer/>
    </>
  );
};

export default MainPage;
