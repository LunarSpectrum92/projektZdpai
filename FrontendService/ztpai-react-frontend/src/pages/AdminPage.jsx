import React, { useState, useEffect } from 'react';
import NavBar from '../Components/NavBar';
import Footer from '../Components/Footer';
import { Spinner, Tabs, Row, Nav, Tab, Accordion, Col, Card } from "react-bootstrap";
import AddressForm from '../Components/AddressForm';
import { User, MapPin, ShoppingBag } from 'lucide-react';
import useGetFetch from "../hooks/useGetFetch";
import ProductsComponent from '../Components/ProductsComponent.jsx';
import ProductForm from '../Components/ProductForm.jsx';
import PhotosDragAndDrop from '../Components/PhotosDragAndDrop.jsx';




const AccountPage = ({ token}) => {
   
    return (
        <>
            <NavBar />
                <Tabs
                  defaultActiveKey="profile"
                  id="justify-tab-example"
                  className="mb-3"
                  justify
                >
                  <Tab eventKey="products" title="products">

                  <Tab.Container id="left-tabs-example" defaultActiveKey="first" >
                      <Row>
                        <Col sm={3}>
                          <Nav variant="pills" className="flex-column">
                            <Nav.Item>
                              <Nav.Link eventKey="first">update product</Nav.Link>
                            </Nav.Item>
                            <Nav.Item>
                              <Nav.Link eventKey="second">Add product</Nav.Link>
                            </Nav.Item>
                            <Nav.Item>
                              <Nav.Link eventKey="third">add categories</Nav.Link>
                            </Nav.Item>
                            
                          </Nav>
                        </Col>
                        <Col sm={9}>
                          <Tab.Content>
                            <Tab.Pane eventKey="first">
                                <ProductsComponent token={token} />
                            </Tab.Pane>
                            <Tab.Pane eventKey="second">
                                <PhotosDragAndDrop/>
                                <ProductForm token={token} url={`http://localhost:8222/api/products/product`}/>
                            </Tab.Pane>
                          </Tab.Content>
                        </Col>
                      </Row>
                    </Tab.Container>


                  </Tab>
                  <Tab eventKey="clients" title="clients">
                    Tab content for Profile
                  </Tab>
                  <Tab eventKey="categories" title="categories">
                    Tab content for Loooonger Tab
                  </Tab>
                  <Tab eventKey="odrers" title="odrers">
                    Tab content for Contact
                  </Tab>
                </Tabs>
            <Footer />
        </>
    );
};

export default AccountPage;