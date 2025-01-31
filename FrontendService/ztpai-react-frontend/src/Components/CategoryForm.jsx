import React, { useState, useEffect } from "react";
import { Form, Button, Container, Spinner } from "react-bootstrap";
import usePostFetch from "../hooks/usePostFetch.jsx";
import useGetFetch from "../hooks/useGetFetch.jsx";

const CategoryForm = ({ token, url }) => {
    const [formData, setFormData] = useState({
        categoryName: "",
        categoryParentId: ""
    });
    const [errors, setErrors] = useState({});
    
    const { data: response, loading, error, sendRequest } = usePostFetch(url, token);
    const { data: responseCategory, loading: loadingCategory, error: errorCategory } = useGetFetch(`http://localhost:8222/api/categories/categories`, token);


    useEffect(() => {
        if (response) {
            window.location.reload();
        }
    }, [response]);


    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: name === "categoryParentId" ? parseInt(value) || "" : value
        }));
        setErrors((prev) => ({ ...prev, [name]: "" }));
    };

    const validate = () => {
        let formErrors = {};
        if (!formData.categoryName) formErrors.categoryName = "Nazwa kategorii jest wymagana.";
        if (formData.categoryParentId !== "" && isNaN(formData.categoryParentId)) formErrors.categoryParentId = "ID nadrzędnej kategorii musi być liczbą.";
        setErrors(formErrors);
        return Object.keys(formErrors).length === 0;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (validate()) {
            sendRequest(formData);
        }
    };

    return (
        <Container>
            <Form onSubmit={handleSubmit}>
                <Form.Group className="mb-3" controlId="formCategoryName">
                    <Form.Label>Nazwa kategorii</Form.Label>
                    <Form.Control type="text" name="categoryName" value={formData.categoryName} onChange={handleChange} isInvalid={!!errors.categoryName} />
                    <Form.Control.Feedback type="invalid">{errors.categoryName}</Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formCategoryParentId">
                    <Form.Label>ID nadrzędnej kategorii (opcjonalne)</Form.Label>
                    <Form.Select name="categoryParentId" value={formData.categoryParentId} onChange={handleChange} isInvalid={!!errors.categoryParentId}>
                        <option value="">Wybierz kategorię</option>
                        {loadingCategory ? (
                            <option disabled>Ładowanie kategorii...</option>
                        ) : (
                            responseCategory?.map((category) => (
                                <option key={category.categoryId} value={category.categoryId}>{category.categoryName}</option>
                            ))
                        )}
                    </Form.Select>
                    <Form.Control.Feedback type="invalid">{errors.categoryParentId}</Form.Control.Feedback>
                </Form.Group>
                <Button variant="primary" type="submit" disabled={loading}>{loading ? "Wysyłanie..." : "Wyślij"}</Button>
                {error && <p className="text-danger mt-2">{error}</p>}
            </Form>
        </Container>
    );
};

export default CategoryForm;