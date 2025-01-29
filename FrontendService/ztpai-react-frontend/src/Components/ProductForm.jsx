import React, { useEffect, useState } from "react";
import { Form, Button, Col, Row } from "react-bootstrap";
import usePutFetch from "../hooks/usePutFetch.jsx";
import usePostFetch from "../hooks/usePostFetch.jsx";

import useGetFetch from "../hooks/useGetFetch.jsx";

const ProductForm = ({ token, productId, url }) => {
    const [formData, setFormData] = useState({
      productId: productId || "",
      productName: "",
      description: "",
      brand: "",
      price: "",
      quantity: "",
      category: [],
      discount: "",
    });
  
    const [errors, setErrors] = useState({});

    const { data: response, loading, error, sendRequest } = productId
    ? usePutFetch(url, token) 
    : usePostFetch(url, token);
  

  
    const handleChange = (e) => {
      const { name, value } = e.target;
  
      setFormData((prevData) => ({
        ...prevData,
        [name]: name === "category" ? value.split(",").map(Number) : value,
      }));
  
      setErrors((prev) => ({ ...prev, [name]: "" }));
    };
  
    const validate = () => {
      let formErrors = {};
  
      if (!formData.productName) formErrors.productName = "Nazwa produktu jest wymagana.";
      if (!formData.description) formErrors.description = "Opis jest wymagany.";
      if (!formData.brand) formErrors.brand = "Marka jest wymagana.";
      if (!formData.price || isNaN(formData.price)) {
        formErrors.price = "Cena jest wymagana i musi być liczbą.";
      }
      if (!formData.quantity || isNaN(formData.quantity)) {
        formErrors.quantity = "Ilość jest wymagana i musi być liczbą.";
      }
      if (!formData.category || formData.category.length === 0) {
        formErrors.category = "Przynajmniej jedna kategoria jest wymagana.";
      }
      if (formData.discount && isNaN(formData.discount)) {
        formErrors.discount = "Zniżka musi być liczbą.";
      }
  
      setErrors(formErrors);
      return Object.keys(formErrors).length === 0;
    };
  
    const handleSubmit = (e) => {
      e.preventDefault();
  
      if (validate()) {
        sendRequest(formData); 
      }
    };
  
    useEffect(() => {
      if (response) {
        window.location.reload();
      }
    }, [response]);
  
    return (
      <Form onSubmit={handleSubmit}>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="formProductName">
            <Form.Label>Nazwa produktu</Form.Label>
            <Form.Control
              type="text"
              name="productName"
              value={formData.productName}
              onChange={handleChange}
              isInvalid={!!errors.productName}
            />
            <Form.Control.Feedback type="invalid">{errors.productName}</Form.Control.Feedback>
          </Form.Group>
  
          <Form.Group as={Col} controlId="formBrand">
            <Form.Label>Marka</Form.Label>
            <Form.Control
              type="text"
              name="brand"
              value={formData.brand}
              onChange={handleChange}
              isInvalid={!!errors.brand}
            />
            <Form.Control.Feedback type="invalid">{errors.brand}</Form.Control.Feedback>
          </Form.Group>
        </Row>
  
        <Form.Group className="mb-3" controlId="formDescription">
          <Form.Label>Opis</Form.Label>
          <Form.Control
            as="textarea"
            name="description"
            value={formData.description}
            onChange={handleChange}
            isInvalid={!!errors.description}
          />
          <Form.Control.Feedback type="invalid">{errors.description}</Form.Control.Feedback>
        </Form.Group>
  
        <Row className="mb-3">
          <Form.Group as={Col} controlId="formPrice">
            <Form.Label>Cena</Form.Label>
            <Form.Control
              type="number"
              name="price"
              step="0.01"
              value={formData.price}
              onChange={handleChange}
              isInvalid={!!errors.price}
            />
            <Form.Control.Feedback type="invalid">{errors.price}</Form.Control.Feedback>
          </Form.Group>
  
          <Form.Group as={Col} controlId="formQuantity">
            <Form.Label>Ilość</Form.Label>
            <Form.Control
              type="number"
              name="quantity"
              value={formData.quantity}
              onChange={handleChange}
              isInvalid={!!errors.quantity}
            />
            <Form.Control.Feedback type="invalid">{errors.quantity}</Form.Control.Feedback>
          </Form.Group>
  
          <Form.Group as={Col} controlId="formDiscount">
            <Form.Label>Zniżka (%)</Form.Label>
            <Form.Control
              type="number"
              name="discount"
              value={formData.discount}
              onChange={handleChange}
              isInvalid={!!errors.discount}
            />
            <Form.Control.Feedback type="invalid">{errors.discount}</Form.Control.Feedback>
          </Form.Group>
        </Row>
  
        <Form.Group className="mb-3" controlId="formCategory">
          <Form.Label>Kategorie (ID oddzielone przecinkami)</Form.Label>
          <Form.Control
            type="text"
            name="category"
            value={formData.category.join(",")}
            onChange={handleChange}
            isInvalid={!!errors.category}
          />
          <Form.Control.Feedback type="invalid">{errors.category}</Form.Control.Feedback>
        </Form.Group>
  
        <Button variant="primary" type="submit" disabled={loading}>
          {loading ? "Wysyłanie..." : "Wyślij"}
        </Button>
  
        {error && <p className="text-danger mt-2">{error}</p>}
        {response && <p className="text-success mt-2">Produkt został zapisany!</p>}
      </Form>
    );
  };
  
  export default ProductForm;