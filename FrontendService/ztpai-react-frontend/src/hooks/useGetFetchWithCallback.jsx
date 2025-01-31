import { useState, useCallback } from 'react';
import axios from 'axios';

const useGetFetchWithCallback = (initialUrl, token) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchData = useCallback(async (url) => {
    if (!token) {
      return;
    }

    if (!url && !initialUrl) {
      return;
    }


    if(!url){
      setLoading(true);
      setError(null);
  
      try {
        const config = {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        };
console.log(initialUrl);
  
        const response = await axios.get(initialUrl, config);
        setData(response.data);
      } catch (err) {
        setError(err.response?.data?.message || err.message || 'An error occurred');
      } finally {
        setLoading(false);
      }
    }else{
      
    setLoading(true);
    setError(null);

    try {
      const config = {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      };
console.log(url);
      const response = await axios.get(url, config);
      setData(response.data);
    } catch (err) {
      setError(err.response?.data?.message || err.message || 'An error occurred');
    } finally {
      setLoading(false);
    }
    }

  }, [token, initialUrl]);

  return {
    data,
    loading,
    error,
    fetchData
  };
};


export default useGetFetchWithCallback;
