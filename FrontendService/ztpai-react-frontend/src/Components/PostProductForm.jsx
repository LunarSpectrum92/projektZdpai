import React, { useEffect, useState, useCallback } from "react";
import { Form, Button, Col, Row, Container } from "react-bootstrap";
import { useDropzone } from "react-dropzone";
import usePostFetch from "../hooks/usePostFetch.jsx";

const PostProductForm = ({ token, productId, url }) => {
    const [formData, setFormData] = useState({
        productName: "",
        description: "",
        brand: "",
        price: "",
        quantity: "",
        category: "",
        discount: "",
        photoIds: []
    });
    const [errors, setErrors] = useState({});
    const [preview, setPreview] = useState(null);
    const [acceptedFiles, setAcceptedFiles] = useState([]);
    
    const { data: response, loading, error, sendRequest } = usePostFetch(url, token);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: ["price", "quantity", "discount"].includes(name) ? parseFloat(value) || "" : value
        }));
        setErrors((prev) => ({ ...prev, [name]: "" }));
    };

    const validate = () => {
        let formErrors = {};
        if (!formData.productName) formErrors.productName = "Nazwa produktu jest wymagana.";
        if (!formData.description) formErrors.description = "Opis jest wymagany.";
        if (!formData.brand) formErrors.brand = "Marka jest wymagana.";
        if (!formData.price || isNaN(formData.price)) formErrors.price = "Cena musi być liczbą.";
        if (!formData.quantity || isNaN(formData.quantity)) formErrors.quantity = "Ilość musi być liczbą.";
        if (!formData.category) formErrors.category = "Przynajmniej jedna kategoria jest wymagana.";
        if (formData.discount === "" || isNaN(formData.discount)) formErrors.discount = "Rabat musi być liczbą.";
        setErrors(formErrors);
        return Object.keys(formErrors).length === 0;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (validate()) {
            const jsonBlob = new Blob([JSON.stringify({ ...formData, category: formData.category.split(",").map(Number) })], { type: "application/json" });
            const formDataToSend = new FormData();
            formDataToSend.append("data", jsonBlob);
            acceptedFiles.forEach((file) => formDataToSend.append("photoS", file));
            sendRequest(formDataToSend);
        }
    };

    useEffect(() => {
        if (response) {
            window.location.reload();
        }
    }, [response]);

    const onDrop = useCallback((acceptedFiles) => {
        setAcceptedFiles(acceptedFiles);
        const file = new FileReader();
        file.onload = () => setPreview(file.result);
        if (acceptedFiles[0]) file.readAsDataURL(acceptedFiles[0]);
    }, []);

    const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

    return (
        <Container>
            <Form onSubmit={handleSubmit}>
                <Row className="mb-3">
                    <Form.Group as={Col} controlId="formProductName">
                        <Form.Label>Nazwa produktu</Form.Label>
                        <Form.Control type="text" name="productName" value={formData.productName} onChange={handleChange} isInvalid={!!errors.productName} />
                        <Form.Control.Feedback type="invalid">{errors.productName}</Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group as={Col} controlId="formBrand">
                        <Form.Label>Marka</Form.Label>
                        <Form.Control type="text" name="brand" value={formData.brand} onChange={handleChange} isInvalid={!!errors.brand} />
                        <Form.Control.Feedback type="invalid">{errors.brand}</Form.Control.Feedback>
                    </Form.Group>
                </Row>
                <Form.Group className="mb-3" controlId="formDescription">
                    <Form.Label>Opis</Form.Label>
                    <Form.Control as="textarea" name="description" value={formData.description} onChange={handleChange} isInvalid={!!errors.description} />
                    <Form.Control.Feedback type="invalid">{errors.description}</Form.Control.Feedback>
                </Form.Group>
                <Row className="mb-3">
                    <Form.Group as={Col} controlId="formPrice">
                        <Form.Label>Cena</Form.Label>
                        <Form.Control type="number" name="price" step="0.01" value={formData.price} onChange={handleChange} isInvalid={!!errors.price} />
                        <Form.Control.Feedback type="invalid">{errors.price}</Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group as={Col} controlId="formQuantity">
                        <Form.Label>Ilość</Form.Label>
                        <Form.Control type="number" name="quantity" value={formData.quantity} onChange={handleChange} isInvalid={!!errors.quantity} />
                        <Form.Control.Feedback type="invalid">{errors.quantity}</Form.Control.Feedback>
                    </Form.Group>
                </Row>
                <Form.Group className="mb-3" controlId="formDiscount">
                    <Form.Label>Rabat (%)</Form.Label>
                    <Form.Control type="number" name="discount" value={formData.discount} onChange={handleChange} isInvalid={!!errors.discount} />
                    <Form.Control.Feedback type="invalid">{errors.discount}</Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formCategory">
                    <Form.Label>Kategorie (ID oddzielone przecinkami)</Form.Label>
                    <Form.Control type="text" name="category" value={formData.category} onChange={handleChange} isInvalid={!!errors.category} />
                    <Form.Control.Feedback type="invalid">{errors.category}</Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="image">
                    <Form.Label>Zdjęcie produktu</Form.Label>
                    <div {...getRootProps()} className="border p-3 text-center cursor-pointer">
                        <input {...getInputProps()} />
                        {isDragActive ? <p>Upuść pliki tutaj...</p> : <p>Przeciągnij i upuść plik tutaj lub kliknij, aby wybrać</p>}
                    </div>
                </Form.Group>
                {preview && <div className="mb-3 text-center"><img src={preview} alt="Podgląd" className="img-fluid" /></div>}
                <Button variant="primary" type="submit" disabled={loading}>{loading ? "Wysyłanie..." : "Wyślij"}</Button>
                {error && <p className="text-danger mt-2">{error}</p>}
            </Form>
        </Container>
    );
};

export default PostProductForm;
