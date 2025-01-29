import { useState, useCallback } from 'react';
import axios from 'axios';

const useFetch = (url, token) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);


  const sendRequest = useCallback(
    async (dataToSend) => {
      if (!token) {
        setError("Brak tokena autoryzacyjnego");
        return;
      }
  
      setLoading(true);
      setError(null);
  
      try {
        const config = {
          headers: {
            authorization: `Bearer ${token}`,
          },
        };
  
  
        const response = await axios.put(url, dataToSend, config);
  
        setData(response.data);
      } catch (err) {
        console.error("Błąd przy wysyłaniu danych: ", err);
        setError(err.message || "Wystąpił błąd");
      } finally {
        setLoading(false);
      }
    },
    [url, token]
  );
  
  return { data, loading, error, sendRequest };
};

export default useFetch;
