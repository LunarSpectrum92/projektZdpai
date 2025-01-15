import React, { useState, useContext, useEffect } from 'react';
import NavBar from '../Components/NavBar.jsx';
// @ts-ignore
import reactLogo from '../assets/rb_3269.png';
import Footer from '../Components/Footer.jsx';
import { Form, Button, Col, Row, Nav, Tab, Accordion, ListGroup, Card } from "react-bootstrap";
import AddressForm from '../Components/AddressForm.jsx';
import { User, MapPin, ShoppingBag } from 'lucide-react'; // Import icons

const AccontPage = () => {

    const client = {
        "userId": 1,
        "name": "Jan",
        "surname": "Kowalski",
        "phone": "+48123456789",
        "keycloakId": "a1b2c3d4-e5f6-7890-1234-56789abcdefg",
        "createdAt": "2025-01-14T12:34:56",
        "address": {
            "country": "Polska",
            "city": "Warszawa",
            "street": "Kwiatowa",
            "houseNumber": "12",
            "flatNumber": "5",
            "postalCode": "00-123"
        },
        "photoId": 101
    }

    return (
        <>
            <NavBar />
            <Tab.Container id="left-tabs-example" defaultActiveKey="first">
                <Row className="mx-5 my-5">
                    <Col sm={3}>
                        <Nav variant="pills" className="flex-column">
                            <Nav.Item>
                                <Nav.Link eventKey="first" className="mb-2">
                                    <MapPin size={18} className="me-2" /> Address Data
                                </Nav.Link>
                            </Nav.Item>
                            <Nav.Item>
                                <Nav.Link eventKey="second" className="mb-2">
                                    <User size={18} className="me-2" /> Account
                                </Nav.Link>
                            </Nav.Item>
                            <Nav.Item>
                                <Nav.Link eventKey="third" className="mb-2">
                                    <ShoppingBag size={18} className="me-2" /> My Orders
                                </Nav.Link>
                            </Nav.Item>
                        </Nav>
                    </Col>
                    <Col sm={9} xs={12} md={6} lg={6}>
                        <Tab.Content>
                            <Tab.Pane eventKey="first">
                                <h3>Your data</h3>
                                <Card className='my-3'>
                                    <ListGroup variant="flush">
                                        <ListGroup.Item><strong>Telefon:</strong> {client.phone}</ListGroup.Item>
                                        <ListGroup.Item><strong>Kraj:</strong> {client.address.country}</ListGroup.Item>
                                        <ListGroup.Item><strong>Miasto:</strong> {client.address.city}</ListGroup.Item>
                                        <ListGroup.Item><strong>Ulica:</strong> {client.address.street}</ListGroup.Item>
                                        <ListGroup.Item><strong>Nr domu:</strong> {client.address.houseNumber}</ListGroup.Item>
                                        <ListGroup.Item><strong>Nr mieszkania:</strong> {client.address.flatNumber}</ListGroup.Item>
                                        <ListGroup.Item><strong>Kod pocztowy:</strong> {client.address.postalCode}</ListGroup.Item>
                                    </ListGroup>
                                </Card>
                                <Accordion>
                                    <Accordion.Item eventKey="1">
                                        <Accordion.Header>Change data</Accordion.Header>
                                        <Accordion.Body>
                                            <AddressForm />
                                        </Accordion.Body>
                                    </Accordion.Item>
                                </Accordion>
                            </Tab.Pane>
                            <Tab.Pane eventKey="second">
                                <a href="http://localhost:7080/realms/eCommerce-realm/account">redirect to my account</a>
                            </Tab.Pane>
                            <Tab.Pane eventKey="third">
                                <p>moje zamowienia</p>
                            </Tab.Pane>
                        </Tab.Content>
                    </Col>
                </Row>
            </Tab.Container>

            <Footer />
        </>
    );
};

export default AccontPage;
