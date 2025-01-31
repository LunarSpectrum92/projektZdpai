import React, { useState, useEffect } from 'react';
import NavBar from '../Components/NavBar';
import Footer from '../Components/Footer';
import { Spinner, Col, Row, Nav, Tab, Accordion, ListGroup, Card } from "react-bootstrap";
import AddressForm from '../Components/AddressForm';
import { User, MapPin, ShoppingBag } from 'lucide-react';
import useGetFetch from "../hooks/useGetFetch";
import OrdersForClient from "../Components/OrdersForClient";



const AccountPage = ({ token, client }) => {
    const [keycloakClientData, setKeycloakClientData] = useState(null);
    const [clientData, setClientData] = useState(null);
    const [error, setError] = useState(null);

    const { data, loading, error: fetchError } = useGetFetch(
        keycloakClientData?.sub 
            ? `http://localhost:8222/api/clients/client/keycloak/${encodeURIComponent(keycloakClientData.sub)}`
            : null,
        token
    );
    
    useEffect(() => {
        if (token && client?.idTokenParsed) {
            setKeycloakClientData(client.idTokenParsed);
        }
    }, [token, client]);

    useEffect(() => {
        if (fetchError) {
            setError(fetchError);
        }
        if (data) {
            setClientData(data);
        }
    }, [data, fetchError]);

    if (loading || !clientData) {
        return (
            <div className="d-flex justify-content-center align-items-center min-vh-100">
                <Spinner animation="border" />
            </div>
        );
    }

    if (error) {
        return (
            <div className="alert alert-danger m-5" role="alert">
                Error loading data: {error}
            </div>
        );
    }

    const address = clientData.address || {};
    
    const addressFields = [
        { label: 'Phone', value: clientData.phone },
        { label: 'Country', value: address.country },
        { label: 'City', value: address.city },
        { label: 'Street', value: address.street },
        { label: 'House Number', value: address.houseNumber },
        { label: 'Flat Number', value: address.flatNumber },
        { label: 'Postal Code', value: address.postalCode },
    ];
    return (
        <div className="d-flex flex-column min-vh-100"> 
            <NavBar token={token}/>
            <div className="flex-grow-1"> 
                
                <Tab.Container id="account-tabs" defaultActiveKey="address">
                    <Row className="mx-5 my-5">
                        <Col sm={3}>
                            <Nav variant="pills" className="flex-column">
                                <Nav.Item>
                                    <Nav.Link eventKey="address" className="mb-2">
                                        <MapPin size={18} className="me-2" /> Address Data
                                    </Nav.Link>
                                </Nav.Item>
                                <Nav.Item>
                                    <Nav.Link eventKey="account" className="mb-2">
                                        <User size={18} className="me-2" /> Account
                                    </Nav.Link>
                                </Nav.Item>
                                <Nav.Item>
                                    <Nav.Link eventKey="orders" className="mb-2">
                                        <ShoppingBag size={18} className="me-2" /> My Orders
                                    </Nav.Link>
                                </Nav.Item>
                            </Nav>
                        </Col>
                        <Col sm={9} xs={12} md={6} lg={6}>
                            <Tab.Content>
                                <Tab.Pane eventKey="address">
                                    <h4>Your Data</h4>
                                    <Card className="my-3">
                                        <ListGroup variant="flush">
                                            {addressFields.map(({ label, value }) => (
                                                <ListGroup.Item key={label}>
                                                    <strong>{label}:</strong> {value || 'Not provided'}
                                                </ListGroup.Item>
                                            ))}
                                        </ListGroup>
                                    </Card>
                                    <Accordion>
                                        <Accordion.Item eventKey="0">
                                            <Accordion.Header>Change Data</Accordion.Header>
                                            <Accordion.Body>
                                                <AddressForm token={token} />
                                            </Accordion.Body>
                                        </Accordion.Item>
                                    </Accordion>
                                </Tab.Pane>
                                <Tab.Pane eventKey="account">
                                    <a href="http://localhost:7080/realms/eCommerce-realm/account" 
                                       className="btn btn-primary">
                                        Go to My Account
                                    </a>
                                </Tab.Pane>
                                <Tab.Pane eventKey="orders">
                                    <h4>My Orders</h4>
                                    <OrdersForClient token={token} clientId={clientData.userId}/>
                                </Tab.Pane>
                            </Tab.Content>
                        </Col>
                    </Row>
                </Tab.Container>
            </div>  
            <Footer />
        </div>
    );
    
};

export default AccountPage;