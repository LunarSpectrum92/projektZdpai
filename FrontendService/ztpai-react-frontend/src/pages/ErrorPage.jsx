import {Button,Alert,Row, Col,  Container, Nav} from 'react-bootstrap';
import NavBar from '../Components/NavBar.jsx'
import React from 'react';




const ErrorPage = () => {

  
    return (
        <>
        <NavBar/>
          <Container className="text-center d-flex flex-column justify-content-center align-items-center" style={{ height: "100vh" }}>

            <Row>
              <Col>
                <h1 className="display-1">404</h1>
                <p className="lead">Oops! The page you are looking for doesn't exist.</p>
                <Button variant="primary" href="/">
                  Go Back to Home
                </Button>
              </Col>
            </Row>
          </Container>
        </>   
);
};

export default ErrorPage;
