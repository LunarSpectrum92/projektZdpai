// @ts-ignore
import reactLogo from '../assets/rb_3269.png';
import React, { useContext, useEffect } from 'react';
import { Container, Card, Button, Badge } from 'react-bootstrap';
import NavBar from '../Components/NavBar.jsx';
import Footer from '../Components/Footer.jsx';
import {CartProductsContext} from '../Contexts/CartProductsContext.jsx';

const CartPage = () => {
  const { cart, addToCart, removeQuantity } = useContext(CartProductsContext);



  return (
    <>
      <NavBar />
      <Container className="my-5">
        <h1>
          <Badge bg="secondary" className="my-2">
            Cart
          </Badge>
        </h1>
        {!cart || cart.length === 0 ? (
          <p>Your cart is empty.</p>
        ) : (
          cart.map((product) => (
            <Card className="my-3" key={product.productId}>
              <Card.Header as="h5">{product.discount ? 'Featured' : 'Product'}</Card.Header>
              <Card.Body className="d-md-flex align-items-center justify-content-between">
                <div className="d-md-flex align-items-center">
                  <div className="w-25">
                    <Card.Img variant="top" src={product.image || reactLogo} />
                  </div>
                  <div>
                    <Card.Title>{product.productName}</Card.Title>
                    <Card.Text>{product.description}</Card.Text>
                    <p>
                      <b>{product.price} PLN</b>
                    </p>
                  </div>
                </div>
                <div className="d-md-flex align-items-center justify-content-between">
                  <Badge bg="secondary" className="me-2">
                    {product.quantityCart}
                  </Badge>
                  <Button
                    variant="outline-dark"
                    className="me-1"
                    style={{ width: '40px' }}
                    onClick={() => addToCart(product)}
                  >
                    +
                  </Button>
                  <Button
                    variant="outline-dark"
                    className="ms-1"
                    style={{ width: '40px' }}
                    onClick={() => removeQuantity(product.productId)}
                  >
                    -
                  </Button>
                </div>
              </Card.Body>
            </Card>
          ))
        )}
      </Container>
      <Footer />

    </>
  );
};

export default CartPage;

