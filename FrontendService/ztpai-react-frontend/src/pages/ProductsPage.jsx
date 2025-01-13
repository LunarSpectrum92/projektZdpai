import React, { useState, useContext, useEffect } from "react";
import {
  Image,
  Container,
  Row,
  Col,
  Card,
  Button,
  Dropdown,
  DropdownButton,
  ButtonGroup,
  Form,
} from "react-bootstrap";
import NavBar from "../Components/NavBar.jsx";
// @ts-ignore
import reactLogo from "../assets/rb_3269.png";
import Footer from "../Components/Footer.jsx";
import { CartProductsContext } from "../Contexts/CartProductsContext.jsx";
import { products } from "../assets/products.js";
import PaginationProducts from "../Components/PaginationProducts.jsx";

const MainPage = () => {
  const { addToCart } = useContext(CartProductsContext);

  // Ustawienie początkowego stanu
  const [state, setState] = useState({
    data: products, // Wszystkie produkty
    limit: 9, // Liczba produktów na stronę
    activePage: 1, // Aktywna strona
  });

  // Funkcja do zmiany strony
  const handlePageChange = (pageNumber) => {
    setState((prev) => ({
      ...prev,
      activePage: pageNumber,
    }));
  };

  // Funkcja do obsługi dodawania do koszyka
  const handleAddToCart = (product) => {
    addToCart(product);
    alert(`${product.productName} został dodany do koszyka!`);
  };

  // Obliczenie produktów wyświetlanych na aktualnej stronie
  const indexOfLastProduct = state.activePage * state.limit;
  const indexOfFirstProduct = indexOfLastProduct - state.limit;
  const displayProducts = state.data.slice(indexOfFirstProduct, indexOfLastProduct);

  return (
    <>
      <NavBar />

      <Container className="my-5 ">
        <h2 className="mb-4">Trending</h2>
        {/* Filtry */}
        <DropdownButton
          as={ButtonGroup}
          variant="outline-dark"
          title="categories"
          className="mb-4 me-2"
        >
          <Dropdown.Item eventKey="1">Action</Dropdown.Item>
          <Dropdown.Item eventKey="2">Another action</Dropdown.Item>
          <Dropdown.Item eventKey="3" active>
            Active Item
          </Dropdown.Item>
          <Dropdown.Divider />
          <Dropdown.Item eventKey="4">Separated link</Dropdown.Item>
        </DropdownButton>
        <DropdownButton
          as={ButtonGroup}
          variant="outline-dark"
          title="brands"
          className="mb-4 me-2"
        >
          <Dropdown.Item eventKey="1">Action</Dropdown.Item>
          <Dropdown.Item eventKey="2">Another action</Dropdown.Item>
          <Dropdown.Item eventKey="3" active>
            Active Item
          </Dropdown.Item>
          <Dropdown.Divider />
          <Dropdown.Item eventKey="4">Separated link</Dropdown.Item>
        </DropdownButton>
        <DropdownButton
          as={ButtonGroup}
          variant="outline-dark"
          title="trending"
          className="mb-4 me-2"
        >
          <Dropdown.Item eventKey="1">Action</Dropdown.Item>
          <Dropdown.Item eventKey="2">Another action</Dropdown.Item>
          <Dropdown.Item eventKey="3" active>
            Active Item
          </Dropdown.Item>
          <Dropdown.Divider />
          <Dropdown.Item eventKey="4">Separated link</Dropdown.Item>
        </DropdownButton>
        <DropdownButton
          as={ButtonGroup}
          variant="outline-dark"
          title="price"
          className="mb-4 me-2"
        >
          <Dropdown.Item eventKey="1">Action</Dropdown.Item>
          <Dropdown.Item eventKey="2">Another action</Dropdown.Item>
          <Dropdown.Item eventKey="3" active>
            Active Item
          </Dropdown.Item>
          <Dropdown.Divider />
          <Dropdown.Item eventKey="4">Separated link</Dropdown.Item>
        </DropdownButton>

        {/* Produkty */}
        <Row className="g-3 mb-2">
          {displayProducts.map((product) => (
            <Col md={4} sm={6} xs={6} key={product.productId}>
              <Card
                className="bg-bg-light-subtle h-100 shadow-sm"
                style={{ border: "0px" }}
              >
                <Card.Img variant="top" src={reactLogo} />
                <Card.Body className="d-flex flex-column">
                  <Card.Title>{product.productName}</Card.Title>
                  <Card.Text>
                    {product.description}
                    <br />
                    <p>
                      <b>{product.price}</b> PLN
                    </p>
                  </Card.Text>
                  <div className="mt-auto">
                    <Button
                      variant="outline-dark"
                      className="w-0"
                      onClick={() => handleAddToCart(product)}
                    >
                      Add to Cart
                    </Button>
                  </div>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>

        {/* Paginacja */}
        <PaginationProducts
          paginationNumber={Math.ceil(state.data.length / state.limit)}
          activePage={state.activePage}
          onPageChange={handlePageChange}
        />
      </Container>

      <div className="bg-secondary text-white py-5">
        <Container>
          <h2>Contact Us</h2>
          <Form className="w-75">
            <Form.Group
              className="mb-3 "
              controlId="exampleForm.ControlInput1"
            >
              <Form.Label></Form.Label>
              <Form.Control type="email" placeholder="name@example.com" />
            </Form.Group>
            <Form.Group
              className="mb-3"
              controlId="exampleForm.ControlTextarea1"
            >
              <Form.Control as="textarea" placeholder="Description" rows={3} />
            </Form.Group>
          </Form>
          <Button variant="dark" size="lg" href="#contact">
            Contact Us
          </Button>
        </Container>
      </div>

      <Footer />
    </>
  );
};

export default MainPage;
