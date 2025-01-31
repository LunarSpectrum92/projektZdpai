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
  Carousel,
  Spinner
} from 'react-bootstrap';
import NavBar from '../Components/NavBar.jsx';
import Footer from '../Components/Footer.jsx';
import reactLogo from '../assets/rb_3269.png';
import { useNavigate } from "react-router-dom";
import useGetFetch from "../hooks/useGetFetch.jsx";
import useFetch from "../hooks/usePostFetch.jsx";

const ProductPage = ({ token, url, client }) => {
  const { productId } = useParams();
  const navigate = useNavigate();

  const [keycloakClientData, setKeycloakClientData] = useState(null);
  const [products, setProducts] = useState(null);
  const [clientData, setClientData] = useState(null);
  const [newReview, setNewReview] = useState({ commentBody: '', score: 0 });
  const [product, setProduct] = useState({});
  const [comments, setComments] = useState([]); // Initialize as empty array

  // Fetch all products
  const { data: productsFirst, loading: productsLoading, error: productsError } = useGetFetch(
    "http://localhost:8222/api/products/product/all", 
    token
  );

  // Fetch comments for the current product
  const { data: commentsData, loading: commentsLoading, error: commentsError } = useGetFetch(
    product.productId ? `http://localhost:8222/api/comments/comment/product/${encodeURIComponent(product.productId)}` : null,
    token
  );

  // Fetch client data
  const { data: clientResponseData, loading: clientLoading, error: clientError } = useGetFetch(
    keycloakClientData?.sub 
      ? `http://localhost:8222/api/clients/client/keycloak/${encodeURIComponent(keycloakClientData.sub)}`
      : null,
    token
  );

  const { sendRequest: postComment, loading: postCommentLoading, error: postCommentError } = useFetch(
    "http://localhost:8222/api/comments/comment", 
    token
  );

  // Set client data when available
  useEffect(() => {
    if (clientError) {
      console.error('Error fetching client data:', clientError);
    }
    if (clientResponseData) {
      setClientData(clientResponseData);
    }
  }, [clientResponseData, clientError]);

  // Set Keycloak client data when available
  useEffect(() => {
    if (token && client?.idTokenParsed) {
      setKeycloakClientData(client.idTokenParsed);
    }
  }, [token, client]);

  // Set products when data is available
  useEffect(() => {
    if (productsFirst?.length > 0) {
      setProducts(productsFirst);
    }
  }, [productsFirst]);

  // Set comments when data is available
  useEffect(() => {
    if (commentsData) {
      // Ensure commentsData is an array before setting it
      setComments(Array.isArray(commentsData) ? commentsData : []);
    }
  }, [commentsData]);

  // Set current product when products are available
  useEffect(() => {
    if (!products?.length) return;
    
    const filteredProduct = products.find(p => p.productId === parseInt(productId));
    if (!filteredProduct) {
      navigate("/error", { replace: true });
      return;
    }
    setProduct(filteredProduct);
  }, [products, productId, navigate]);

  const handleSubmitReview = async (e) => {
    e.preventDefault();
    
    if (!clientData?.userId) {
      alert("You must be logged in to submit a review.");
      return;
    }

    const reviewData = {
      commentBody: newReview.commentBody,
      score: parseInt(newReview.score),
      userId: clientData.userId,
      productId: product.productId,
    };

    try {
      const newComment = await postComment(reviewData);
      console.log(newComment)
      if (newComment) {
        alert("Review submitted successfully!");
        setNewReview({ commentBody: '', score: 0 });
        setComments(prevComments => Array.isArray(prevComments) ? [...prevComments, newComment] : [newComment]);
        window.location.reload();
      }
    } catch (err) {
      console.error("Error submitting review:", err);
      alert("Failed to submit review. Please try again.");
    }
  };

  if (productsLoading || clientLoading) {
    return (
      <div className="d-flex flex-column min-vh-100">
        <NavBar token={token}/>
        <Container className="d-flex justify-content-center align-items-center flex-grow-1">
          <Spinner animation="border" role="status" />
        </Container>
        <Footer />
      </div>
    );
  }

  if (productsError || clientError) {
    return (
      <div className="d-flex flex-column min-vh-100">
        <NavBar />
        <Container className="d-flex justify-content-center align-items-center flex-grow-1">
          <div className="text-center">
            <h3>Error loading data</h3>
            <p>Please try again later.</p>
          </div>
        </Container>
        <Footer />
      </div>
    );
  }

  // Ensure comments is always an array before rendering
  const commentsList = Array.isArray(comments) ? comments : [];

  return (
    <div className="d-flex flex-column min-vh-100">
      <NavBar />
      <Container className="mt-5">
        <Row>
          <Col md={6}>
            <Carousel>
              {product?.photoPaths?.length > 0 ? (
                product.photoPaths.map((photo, index) => (
                  <Carousel.Item key={index}>
                    <Image src={photo} className="d-block w-100" alt={`Product view ${index + 1}`} />
                  </Carousel.Item>
                ))
              ) : (
                <Carousel.Item>
                  <Image src={reactLogo} className="d-block w-100" alt="Default product" />
                </Carousel.Item>
              )}
            </Carousel>
          </Col>
          <Col md={6} className="d-flex flex-column justify-content-center">
            <h2>{product.productName}</h2>
            <h3>{product.price}</h3>
            <p>{product.description}</p>
            <Form>
              <Form.Group controlId="productQuantity">
                <Form.Label>Product Quantity: {product.quantity}</Form.Label>
                <Form.Control 
                  type="number" 
                  max={product.quantity} 
                  min={0} 
                  placeholder="Enter quantity" 
                />
              </Form.Group>
              <Button variant="dark" className="mt-3">Buy</Button>
            </Form>
          </Col>
        </Row>

        <h4 className="mt-5 mb-4">Latest reviews</h4>
        {commentsLoading ? (
          <Spinner animation="border" role="status" />
        ) : commentsList.length > 0 ? (
          commentsList.map((review, index) => (
            <Card key={index} className="mb-4">
              <Card.Body>
                <Card.Text>{review.commentBody}</Card.Text>
              </Card.Body>
              <Card.Footer className="text-muted">
                {review.userId} - {new Date(review.commentDate).toLocaleDateString()} - {'⭐'.repeat(review.score)}
              </Card.Footer>
            </Card>
          ))
        ) : (
          <p>No reviews available for this product.</p>
        )}


        <div className="comment-section bg-light py-4 px-3 mb-5 mt-5">
          <h4 className="mt-2 mb-5">Add Your Comment</h4>
          <Form onSubmit={handleSubmitReview}>
            <Form.Group controlId="reviewRating">
              <Form.Label>Rating</Form.Label>
              <Form.Control
                type="number"
                max={5}
                min={0}
                value={newReview.score}
                onChange={(e) => setNewReview({ ...newReview, score: e.target.value })}
              />
              <Form.Control
                as="textarea"
                className="mt-2"
                placeholder="Comment"
                rows={3}
                value={newReview.commentBody}
                onChange={(e) => setNewReview({ ...newReview, commentBody: e.target.value })}
              />
            </Form.Group>
            <Button 
              type="submit" 
              variant="dark" 
              className="mt-3" 
              disabled={postCommentLoading}
            >
              {postCommentLoading ? 'Submitting...' : 'Submit'}
            </Button>
          </Form>
        </div>
        </Container>
      <Footer />
    </div>
  );
};


export default ProductPage;