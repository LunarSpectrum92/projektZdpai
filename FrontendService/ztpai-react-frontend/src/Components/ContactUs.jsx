import React from 'react';
import {Container,Button,Form } from 'react-bootstrap';

const ContactUs = () => {
  return (
    <>
      <div className="bg-secondary text-white py-5">
        <Container>
          <h2>Contact Us</h2>
          <Form className='w-75'>
            <Form.Group className="mb-3 " controlId="exampleForm.ControlInput1">
             <Form.Label></Form.Label>
             <Form.Control type="email" placeholder="name@example.com" />
           </Form.Group>
           <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
             <Form.Control as="textarea"  placeholder='Description' rows={3} />
           </Form.Group>
         </Form>
          <Button variant="dark" size="lg" href="#contact">
            Contact Us
          </Button>
        </Container>
      </div>
    </>
  );
};

export default ContactUs;
