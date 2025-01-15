import React, { useContext, useEffect } from 'react';
import {CartProductsContext} from '../Contexts/CartProductsContext.jsx';
const Timer = () => {





  const { updateTimer, isActive,  minutes, seconds, isCartFull } = useContext(CartProductsContext);
  
  
  useEffect(() => {
    if (!isActive) return;
    const interval = setInterval(updateTimer, 1000);
    return () => clearInterval(interval);
  }, [isActive]);

  
if(isActive){  
  return (
    <div className="container text-center mt-5">
      <div className="row justify-content-center">
        <div className="col-6">
          <h1 className="mb-4">reset in:</h1>
          <div className="d-flex justify-content-around align-items-center border p-4 rounded bg-light">
            <div className="time-box">
              <h2 className="display-4">{minutes < 10 ? "0" + minutes : minutes}</h2>
              <p className="text-muted">Minutes</p>
            </div>
            <span className="fs-1">:</span>
            <div className="time-box">
              <h2 className="display-4">{seconds < 10 ? "0" + seconds : seconds}</h2>
              <p className="text-muted">Seconds</p>
            </div>
          </div>

        </div>
      </div>
    </div>
  );
}else{
    return null
}
};

export default Timer;
