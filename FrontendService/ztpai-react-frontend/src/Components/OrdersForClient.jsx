import React, { useState, useEffect } from "react";
import { Spinner, Container, Row, Col, Card, Button } from "react-bootstrap";
import useGetFetch from "../hooks/useGetFetch.jsx";
import PaginationProducts from "../Components/PaginationProducts.jsx";

const OrdersForClient = ({ token, clientId }) => {
  useEffect(() => {
    console.log("clientId received:", clientId);
  }, [clientId]);

  const [defaultUrl, setDefaultUrl] = useState(null);

  useEffect(() => {
    if (clientId && !isNaN(clientId)) {
      setDefaultUrl(`http://localhost:8222/api/orders/order/client/${encodeURIComponent(clientId)}`);
    }
  }, [clientId]);

  const { data: orders, loading, error } = useGetFetch(defaultUrl, token);

  const ordersArray = Array.isArray(orders) ? orders : [];

  const [state, setState] = useState({
    filteredOrders: [],
    limit: 9,
    activePage: 1,
  });

  useEffect(() => {
    if (ordersArray) {
      setState((prev) => ({
        ...prev,
        filteredOrders: ordersArray.filter((order) => order.status != null),
      }));
    }
  }, [ordersArray]);

  const handlePageChange = (pageNumber) => {
    setState((prev) => ({
      ...prev,
      activePage: pageNumber,
    }));
  };

  const indexOfLastOrder = state.activePage * state.limit;
  const indexOfFirstOrder = indexOfLastOrder - state.limit;
  const displayOrders = state.filteredOrders.slice(indexOfFirstOrder, indexOfLastOrder);

  if (!clientId || isNaN(clientId)) {
    return <p>Błąd: Nieprawidłowy identyfikator klienta.</p>;
  }

  return (
    <Container className="my-5">
      {loading ? (
        <Spinner animation="border" />
      ) : error ? (
        <p>Błąd: {error}</p>
      ) : (
        <Row className="g-3 mb-2">
          {displayOrders.map((order) => {
            let colour = "";

            if (order.status === "REJECTED") {
              colour = "205, 83, 59";
            } else if (order.status === "SUCCEEDED") {
              colour = "78, 110, 88";
            } else if (order.status === "WAITING_FOR_PAYMENT") {
              colour = "152, 193, 217";
            } else if (order.status === "FINISHED") {
              colour = "245, 224, 183";
            }

            return (
              <Col md={12} sm={12} xs={12} key={order.orderId} className="">
                <Card className="h-100 border-1" style={{ backgroundColor: `rgba(${colour}, 0.5)` }}>
                  <Card.Body>
                    <Card.Text>
                      <strong>ID zamówienia:</strong> {order.orderId}
                      <br />
                      <strong>Kwota:</strong> {order.totalAmount} PLN
                      <br />
                      <strong>Data zamówienia:</strong> {new Date(order.orderDate).toLocaleString()}
                      <br />
                      <strong>ID klienta:</strong> {order.clientId}
                      <br />
                      <strong>Status:</strong> {order.status}
                      <br />
                      <strong>Produkty:</strong>
                      <ul>
                        {order.orderProductsList.map((product, index) => (
                          <li key={index}>id {product.productId} - szt.{product.quantity}</li>
                        ))}
                      </ul>
                    </Card.Text>
                  </Card.Body>
                </Card>
              </Col>
            );
          })}
        </Row>
      )}

      <PaginationProducts
        paginationNumber={Math.ceil(state.filteredOrders.length / state.limit)}
        activePage={state.activePage}
        onPageChange={handlePageChange}
      />
    </Container>
  );
};

export default OrdersForClient;
