import React, { useState, useEffect } from 'react';
import NavBar from '../Components/NavBar';
import Footer from '../Components/Footer';
import { Spinner, Tabs, Row, Nav, Tab, Accordion, Col, Card, Button } from "react-bootstrap";
import AddressForm from '../Components/AddressForm';
import { User, MapPin, ShoppingBag } from 'lucide-react';
import useGetFetch from "../hooks/useGetFetch";
import ProductsComponent from '../Components/ProductsComponent.jsx';
import PostProductForm from '../Components/PostProductForm.jsx';
import CategoryForm from '../Components/CategoryForm.jsx';
import Orders from '../Components/Orders.jsx';


import PhotosDragAndDrop from '../Components/PhotosDragAndDrop.jsx';



const AccountPage = ({ token}) => {
   
    return (
      <div className=" d-flex flex-column min-vh-100" >

            <NavBar token={token}/>
            <div className="flex-grow-1">
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
                                <PostProductForm token={token} url={`http://localhost:8222/api/products/product`}/>
                            </Tab.Pane>
                            <Tab.Pane eventKey="third">
                                <CategoryForm token={token} url={`http://localhost:8222/api/categories/category`}/>
                            </Tab.Pane>
                          </Tab.Content>
                        </Col>
                      </Row>
                    </Tab.Container>


                  </Tab>
                  <Tab eventKey="clients" title="clients">
                  <Tab.Container id="left-tabs-example" defaultActiveKey="second" >
                  <Row>
                        <Col sm={3}>
                          <Nav variant="pills" className="flex-column">
                            <Nav.Item>
                              <Nav.Link eventKey="a">clients adresses</Nav.Link>
                            </Nav.Item>
                            <Nav.Item>
                              <Nav.Link eventKey="b">keycloack dashboard</Nav.Link>
                            </Nav.Item>
                           
                            
                          </Nav>
                        </Col>
                        <Col sm={9}>
                          <Tab.Content>
                            <Tab.Pane eventKey="a">

                            </Tab.Pane>
                            <Tab.Pane eventKey="b">
                              <a href='http://localhost:7080/'> <Button> Dashboard </Button> </a>
                            </Tab.Pane>
                            
                          </Tab.Content>
                        </Col>
                      </Row>
                      </Tab.Container>
                  </Tab>

                  <Tab eventKey="odrers" title="odrers">
                  <Tab.Container id="left-tabs-example" defaultActiveKey="third" >

                  <Row>
                        <Col sm={3}>
                          <Nav variant="pills" className="flex-column">
                            <Nav.Item>
                              <Nav.Link eventKey="first">view orders</Nav.Link>
                            </Nav.Item>
                            
                            
                          </Nav>
                        </Col>
                        <Col sm={9}>
                          <Tab.Content>
                            <Tab.Pane eventKey="first">
                                <Orders token={token} />
                            </Tab.Pane>
                            
                          </Tab.Content>
                        </Col>
                      </Row>
                      </Tab.Container>

                  </Tab>
                </Tabs>
                </div>
            <Footer />
        </div>
    );
};

export default AccountPage;