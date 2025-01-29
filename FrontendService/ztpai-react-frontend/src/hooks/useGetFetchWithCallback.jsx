import { useState, useCallback } from 'react';
import axios from 'axios';

const useGetFetchWithCallback = (url, token) => {
  
  const [urlFinal, setrlFinal] = useState(url)
  const [tokenFinal, setTokenFinal] = useState(url)

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);


  const sendRequest = useCallback((url) => {


    setError(null);
    if(token){ 
    
    const config = {
      headers: {
        authorization: `Bearer ${token}`,
      },
    };
  

      axios
      .get(url, config) 
      .then((response) => {
        setData(response.data);
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });

    }
    console.log(data)
      
    
  }, [url, token]); 

  return { data, loading, error, sendRequest };
};

export default useGetFetchWithCallback;
