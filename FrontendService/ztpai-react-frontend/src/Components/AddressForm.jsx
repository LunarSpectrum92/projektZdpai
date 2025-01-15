import React, { useState } from "react";
import { Form, Button, Col, Row } from "react-bootstrap";

const AddressForm = () => {
  const [formData, setFormData] = useState({
    country: "",
    city: "",
    street: "",
    houseNumber: "",
    flatNumber: "",
    postalCode: "",
    phone: "",
  });

  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });

    setErrors((prev) => ({ ...prev, [name]: "" }));
  };

  const validate = () => {
    let formErrors = {};
    const postalCodeRegex = /^\d{2}-\d{3}$/;
    const phoneRegex = /^(\+\d{1,3})?\d{9}$/;

    if (!formData.country) formErrors.country = "Kraj jest wymagany.";
    if (!formData.city) formErrors.city = "Miasto jest wymagane.";
    if (!formData.street) formErrors.street = "Ulica jest wymagana.";
    if (!formData.houseNumber) formErrors.houseNumber = "Numer domu jest wymagany.";
    if (!formData.postalCode) {
      formErrors.postalCode = "Kod pocztowy jest wymagany.";
    } else if (!postalCodeRegex.test(formData.postalCode)) {
      formErrors.postalCode = "Kod pocztowy musi mieć format XX-XXX.";
    }
    if (!formData.phone) {
      formErrors.phone = "Numer telefonu jest wymagany.";
    } else if (!phoneRegex.test(formData.phone)) {
      formErrors.phone =
        "Numer telefonu musi składać się z 9 cyfr, opcjonalnie z prefiksem kraju (np. +48).";
    }

    setErrors(formErrors);

    return Object.keys(formErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      console.log("Form submitted:", formData);
      alert("Formularz został poprawnie przesłany!");
    }
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Row className="mb-3">
        <Form.Group as={Col} controlId="formCountry">
          <Form.Label>Kraj</Form.Label>
          <Form.Control
            type="text"
            name="country"
            value={formData.country}
            onChange={handleChange}
            isInvalid={!!errors.country}
          />
          <Form.Control.Feedback type="invalid">{errors.country}</Form.Control.Feedback>
        </Form.Group>

        <Form.Group as={Col} controlId="formCity">
          <Form.Label>Miasto</Form.Label>
          <Form.Control
            type="text"
            name="city"
            value={formData.city}
            onChange={handleChange}
            isInvalid={!!errors.city}
          />
          <Form.Control.Feedback type="invalid">{errors.city}</Form.Control.Feedback>
        </Form.Group>
      </Row>

      <Row className="mb-3">
        <Form.Group as={Col} controlId="formStreet">
          <Form.Label>Ulica</Form.Label>
          <Form.Control
            type="text"
            name="street"
            value={formData.street}
            onChange={handleChange}
            isInvalid={!!errors.street}
          />
          <Form.Control.Feedback type="invalid">{errors.street}</Form.Control.Feedback>
        </Form.Group>

        <Form.Group as={Col} controlId="formHouseNumber">
          <Form.Label>Numer domu</Form.Label>
          <Form.Control
            type="text"
            name="houseNumber"
            value={formData.houseNumber}
            onChange={handleChange}
            isInvalid={!!errors.houseNumber}
          />
          <Form.Control.Feedback type="invalid">{errors.houseNumber}</Form.Control.Feedback>
        </Form.Group>

        <Form.Group as={Col} controlId="formFlatNumber">
          <Form.Label>Numer mieszkania</Form.Label>
          <Form.Control
            type="text"
            name="flatNumber"
            value={formData.flatNumber}
            onChange={handleChange}
          />
        </Form.Group>
      </Row>

      <Form.Group className="mb-3" controlId="formPostalCode">
        <Form.Label>Kod pocztowy</Form.Label>
        <Form.Control
          type="text"
          name="postalCode"
          value={formData.postalCode}
          onChange={handleChange}
          isInvalid={!!errors.postalCode}
        />
        <Form.Control.Feedback type="invalid">{errors.postalCode}</Form.Control.Feedback>
      </Form.Group>

      <Form.Group className="mb-3" controlId="formPhone">
        <Form.Label>Numer telefonu</Form.Label>
        <Form.Control
          type="text"
          name="phone"
          value={formData.phone}
          onChange={handleChange}
          isInvalid={!!errors.phone}
        />
        <Form.Control.Feedback type="invalid">{errors.phone}</Form.Control.Feedback>
      </Form.Group>

      <Button variant="primary" type="submit">
        Wyślij
      </Button>
    </Form>
  );
};

export default AddressForm;
