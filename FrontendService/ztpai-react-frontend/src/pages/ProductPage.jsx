import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import {
  Container,
  Row,
  Col,
  Card,
  Button,
  Form,
  Image,
  Carousel
} from 'react-bootstrap';
import NavBar from '../Components/NavBar.jsx';
import Footer from '../Components/Footer.jsx';
import reactLogo from '../assets/rb_3269.png'
import { useNavigate } from "react-router-dom";
import useGetFetch from "../hooks/useGetFetch.jsx";

const ProductPage = ({ token, url }) => {

    const productId = useParams();
    let navigate = useNavigate();



    const [products, setProducts] = useState(null);

  

    const { data: productsFirst, loading, error } = useGetFetch("http://localhost:8222/api/products/product/all", token);
    




    const [reviews, setReviews] = useState([
    { name: 'Reviewer name', title: 'sdf', body: 'Review Body', date: new Date().toLocaleDateString(), rating: 2 },
    { name: 'Reviewer name', title: 'asd', body: 'Review Body', date: new Date().toLocaleDateString(), rating: 4 },
  ]);




  
  const [newReview, setNewReview] = useState({ name: '', title: '', body: '', rating: 0 });
  const [product, setProduct] = useState({});

  useEffect(() => {
    console.log("productsFirst:", productsFirst);
  
    if (!productsFirst || productsFirst.length === 0) return; 
  
    setProducts(productsFirst); 
  }, [productsFirst]);
  
  useEffect(() => {
    if (!products || products.length === 0) return;
  
    console.log("products:", products);
  
    const filteredProduct = products.filter(
      (product1) => product1.productId === parseInt(productId.productId)
    );
  
    if (filteredProduct.length === 0) {
      navigate("/error", { replace: true });
      return;
    }
  
    setProduct(filteredProduct[0]);
  }, [products]);
  



  const handleSubmitReview = (e) => {
    e.preventDefault();
    setReviews([...reviews, { ...newReview, date: new Date().toLocaleDateString() }]);
    setNewReview({ name: '', title: '', body: '', rating: 0 });
  };

  



  return (
    <>
    
      <NavBar />
      <Container className="mt-5">
        <Row className=''>
          <Col md={6}>
            <Carousel>
              <Carousel.Item>
                <Image src={reactLogo} className="d-block w-100" alt="Product" />
              </Carousel.Item>
              <Carousel.Item>
                <Image src={reactLogo} className="d-block w-100" alt="Product" />
              </Carousel.Item>
            </Carousel>
          </Col>
          <Col md={6} className="d-flex flex-column justify-content-center">
            <h2>{product.productName}</h2>
            <h3>{product.price}</h3>
            <p>{product.description}</p>
            <Form>
              <Form.Group controlId="productQuantity">
                <Form.Label>Product Quantity: {product.quantity}</Form.Label>
                <Form.Control type="number"  max={product.quantity}  min={0} placeholder="Enter quantity" />
              </Form.Group>
              <Button variant="dark" className="mt-3">Buy</Button>
            </Form>
          </Col>
        </Row>

  <h4 className="mt-5 mb-4">Latest reviews</h4>
  {reviews.map((review, index) => (
    <Card key={index} className=" transparent-card mb-4 " >
      <Card.Header as="h6">{review.title}</Card.Header>
      <Card.Body>
        <Card.Text>{review.body}</Card.Text>
      </Card.Body>
      <Card.Footer className="text-muted">
        {review.name} - {review.date} - {Array.from({ length: review.rating }).map(() => '⭐')}
      </Card.Footer>
    </Card>
  ))}
  <div className="comment-section bg-light py-4 px-3 mb-5 mt-5 ">
  <div>
    <h4 className="mt-2 mb-5">Add Your Comment</h4>
    <Form onSubmit={handleSubmitReview} className="mb-5">
      <Form.Group controlId="reviewRating">
        <Form.Group className="mb-3">
          <Form.Label>Rating</Form.Label>
          <Form.Control
            type="number"
            max={5}
            min={0}
            value={newReview.rating}
            onChange={(e) => setNewReview({ ...newReview, rating: e.target.value })}
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Title</Form.Label>
          <Form.Control
            type="text"
            value={newReview.title}
            onChange={(e) => setNewReview({ ...newReview, title: e.target.value })}
          />
        </Form.Group>
        <Form.Control
          as="textarea"
          className="mt-2"
          placeholder="comment"
          rows={3}
          value={newReview.body}
          onChange={(e) => setNewReview({ ...newReview, body: e.target.value })}
        />
        <Form.Control
          type="text"
          className="mt-2"
          placeholder="Your Name"
          value={newReview.name}
          onChange={(e) => setNewReview({ ...newReview, name: e.target.value })}
        />
      </Form.Group>
      <Button type="submit" variant="dark" className="mt-3">
        Submit
      </Button>
    </Form>
  </div>
</div>
      </Container>
      <Footer />
    </>
  );
};

export default ProductPage;
