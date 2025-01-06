import React, { useState } from 'react';
import {  Image, Container, Row, Col, Card,Button,  Carousel, Form ,Dropdown, DropdownButton ,ButtonGroup   } from 'react-bootstrap';
import NavBar from '../Components/NavBar.jsx'
import reactLogo from '../assets/rb_3269.png'
import Footer from '../Components/Footer.jsx';



const MainPage = () => {

    const cardData = [
        {
          id: 1,
          title: "Card Title 1",
          description: "This is the description for card 1.",
          imageUrl: reactLogo,
        },
        {
          id: 2,
          title: "Card Title 2",
          description: "This is the description for card 2.",
          imageUrl: reactLogo,
        },
        {
          id: 3,
          title: "Card Title 3",
          description: "This is the description for card 3.",
          imageUrl: reactLogo,
        },        {
            id: 4,
            title: "Card Title 4",
            description: "This is the description for card 1.",
            imageUrl: reactLogo,
          },
          {
            id: 5,
            title: "Card Title 5",
            description: "This is the description for card 2.",
            imageUrl: reactLogo,
          },
          {
            id: 6,
            title: "Card Title 6",
            description: "This is the description for card 3.",
            imageUrl: reactLogo,
          },
      ];



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

        <Row className="g-3">
        {cardData.map((card) =>
            <Col md={4} sm={6}>
            <Card className="h-100 shadow-sm bg-light">
                <Card.Img variant="top" src={card.imageUrl} />
                <Card.Body>
                <Card.Title>{card.title}</Card.Title>
                <Card.Text>
                    {card.description}    
                </Card.Text>
                <Button variant="dark">Go somewhere</Button>
                </Card.Body>
            </Card>
            </Col>
        )}

        </Row>
      </Container>

      <div className="bg-secondary text-white py-5">
        <Container>
          <h2>Contact Us</h2>
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
             <Form.Label>Your Email address</Form.Label>
             <Form.Control type="email" placeholder="name@example.com" />
           </Form.Group>
           <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
             <Form.Control as="textarea"  placeholder='Description' rows={3} />
           </Form.Group>
         </Form>
          <Button variant="dark" size="lg" href="#contact">
            Contact Us
          </Button>
        </Container>
      </div>

        <Footer/>
    </>
  );
};

export default MainPage;
