import { useCallback, useState } from 'react';
import { useDropzone } from 'react-dropzone';
import { Container, Form, Button, Row, Col } from 'react-bootstrap';

function PhotosDragAndDrop(submit) {
  const [preview, setPreview] = useState(null);
  const [acceptedFiles, setAcceptedFiles] = useState([]);

  const onDrop = useCallback((acceptedFiles) => {
    setAcceptedFiles(acceptedFiles);
    const file = new FileReader();

    file.onload = function () {
      setPreview(file.result);
    };

    if (acceptedFiles[0]) {
      file.readAsDataURL(acceptedFiles[0]);
    }
  }, []);

  const { getRootProps, getInputProps, isDragActive } = useDropzone({
    onDrop,
  });

  async function handleOnSubmit(e) {
    e.preventDefault();

    if (!acceptedFiles[0]) return;

    const formData = new FormData();
    formData.append('file', acceptedFiles[0]);
    formData.append('upload_preset', '<Your Upload Preset>');
    formData.append('api_key', process.env.REACT_APP_CLOUDINARY_API_KEY);

    const results = await fetch('http://localhost:9020/api/products/product/photos', {
      method: 'POST',
      body: formData,
    }).then((r) => r.json());

    console.log('results', results);
  }

  return (
    <div className="">
      <h1 className="text-center my-4">Contact Us</h1>

      <Form className="border-0 p-4 rounded" onSubmit={handleOnSubmit}>
        

        <Form.Group className="mb-3" controlId="image">
          <Form.Label>Image</Form.Label>
          <div {...getRootProps()} className="border p-3 text-center cursor-pointer">
            <input {...getInputProps()} />
            {isDragActive ? <p>Drop the files here ...</p> : <p>Drag 'n' drop some files here, or click to select files</p>}
          </div>
        </Form.Group>

        {preview && (
          <div className="mb-3 text-center">
            <img src={preview} alt="Upload preview" className="img-fluid" />
          </div>
        )}

      </Form>
    </ div>
  );
}

export default PhotosDragAndDrop;
