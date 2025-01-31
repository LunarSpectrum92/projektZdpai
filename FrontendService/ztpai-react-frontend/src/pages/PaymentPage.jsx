import React, { useState, useEffect, useContext } from 'react';
import { useNavigate, useLocation } from "react-router-dom";
import { Container, Form, Button, Alert, Row, Col, Card } from 'react-bootstrap';
import NavBar from '../Components/NavBar.jsx';
import Footer from '../Components/Footer.jsx';
import usePostFetch from "../hooks/usePostFetch.jsx";
import {CartProductsContext} from '../Contexts/CartProductsContext.jsx';



const PaymentPage = ({ token }) => {
  const { clearCart } = useContext(CartProductsContext);

  const navigate = useNavigate();
  const location = useLocation();
  const { orderId, customerId } = location.state || {};
  const [amount, setAmount] = useState('');
  const [paymentMethod, setPaymentMethod] = useState('BLIK');
  const [message, setMessage] = useState(null);

  const { data: response, loading, error, sendRequest } = usePostFetch(`http://localhost:8222/api/payments/payment`, token);

  useEffect(() => {
    if (response) {
      navigate("/"); 
    }
  }, [response, navigate]);
  useEffect(() => {
    if (error) {
      setMessage({ type: 'danger', text: 'Please enter a correct amount.' });
    }
  }, [error]);
  const handleSubmit = (e) => {
    e.preventDefault();

    if (!amount || isNaN(amount) || parseFloat(amount) <= 0) {
      setMessage({ type: 'danger', text: 'Please enter a valid amount.' });
      return;
    }
    
    Pay();
    if(error) clearCart();

  };

  const Pay = async () => {
    if (!orderId || !customerId) return;

    const payData = {
      orderId,
      customerId,
      amount,
      paymentMethod
    };
    
    try {
      const res = await sendRequest(payData);

    } catch (err) {
      setMessage({ type: 'danger', text: 'Payment failed, retrying...' });
      setTimeout(() => Pay(), 2000); 
    }
  };






  return (
    <div className="d-flex flex-column min-vh-100">
      <NavBar token={token}/>
      <Container className="my-5 flex-grow-1">
        <Row className="justify-content-center">
          <Col xs={12} md={8} lg={6}>
            <Card>
              <Card.Body>
                <h1 className="mb-4 text-center">Payment</h1>
                {message && <Alert variant={message.type}>{message.text}</Alert>}
                <Form onSubmit={handleSubmit}>
                  <Form.Group className="mb-3" controlId="amount">
                    <Form.Label>Amount (PLN)</Form.Label>
                    <Form.Control
                      type="number"
                      placeholder="Enter amount"
                      value={amount}
                      onChange={(e) => setAmount(e.target.value)}
                      required
                    />
                  </Form.Group>

                  <Form.Group className="mb-3" controlId="paymentMethod">
                    <Form.Label>Payment Method</Form.Label>
                    <Form.Select value={paymentMethod} onChange={(e) => setPaymentMethod(e.target.value)}>
                      <option value="BLIK">BLIK</option>
                      <option value="CASH">Cash</option>
                      <option value="CARD">Card</option>
                      <option value="PAYPAL">PayPal</option>
                    </Form.Select>
                  </Form.Group>

                  <Button variant="primary" type="submit" block>
                    Pay Now
                  </Button>
                </Form>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
      <Footer />
    </div>
  );
};

export default PaymentPage;
