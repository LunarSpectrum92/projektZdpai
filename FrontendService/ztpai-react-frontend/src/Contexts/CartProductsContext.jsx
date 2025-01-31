import React, { createContext, useState, useEffect, useContext } from "react";

export const CartProductsContext = createContext(null);

const CartProductsContextProvider = ({ children }) => {
  const [cart, setCart] = useState(() => {
    const savedCart = localStorage.getItem("cart");
    console.log("Initial cart from localStorage:", savedCart);
    return savedCart ? JSON.parse(savedCart) : [];
  });

  const [minutes, setMinutes] = useState(2);
  const [seconds, setSeconds] = useState(0);
  const [isActive, setIsActive] = useState(false);

  const [deadline, setDeadline] = useState(Date.now() + 120000);

  useEffect(() => {
    if (isActive) {
      const newDeadline = Date.now() + minutes * 60 * 1000 + seconds * 1000;
      setDeadline(newDeadline);
      console.log("Timer started, new deadline:", new Date(deadline));
    }
  }, [isActive]);

  useEffect(() => {
    const storedTimer = JSON.parse(localStorage.getItem("timer"));
    console.log("Stored timer from localStorage:", storedTimer);
    if (storedTimer) {
      setMinutes(storedTimer.minutes);
      setSeconds(storedTimer.seconds);
      setIsActive(storedTimer.isActive);
    }

    const handleStorageChange = (event) => {
      if (event.key === "cart") {
        const updatedCart = event.newValue ? JSON.parse(event.newValue) : [];
        console.log("Cart updated in storage event:", updatedCart);
        setCart(updatedCart);
      }
    };

    window.addEventListener("storage", handleStorageChange);

    return () => {
      window.removeEventListener("storage", handleStorageChange);
    };
  }, []);

  useEffect(() => {
    console.log("Cart updated:", cart);
    if (cart.length > 0) {
      startTimer();
    } else {
      restartTimer();
    }
  }, [cart]);

  const updateTimer = () => {
    const timeLeft = deadline - Date.now();
   
    if (timeLeft <= 0) {
      console.log("Timer expired, resetting...");
      setMinutes(0);
      setSeconds(0);
      setIsActive(false);
      localStorage.setItem("timer", JSON.stringify({ minutes: 0, seconds: 0, isActive: false }));
      localStorage.removeItem("cart");
      setCart([]);
      return;
    }

    const newMinutes = Math.floor((timeLeft / 1000 / 60) % 60);
    const newSeconds = Math.floor((timeLeft / 1000) % 60);
    setMinutes(newMinutes);
    setSeconds(newSeconds);
    localStorage.setItem("timer", JSON.stringify({ minutes: newMinutes, seconds: newSeconds, isActive: true }));
  };



  const stopTimer = () => {
    console.log("Stopping timer...");
    setIsActive(false);
    localStorage.setItem("timer", JSON.stringify({ minutes, seconds, isActive: false }));
  };
  const startTimer = () => {
    console.log("Starting timer...");
    setMinutes(2);
    setSeconds(0);
    setDeadline(Date.now() + 2 * 60 * 1000); 
    setIsActive(true);
    localStorage.setItem("timer", JSON.stringify({ minutes: 2, seconds: 0, isActive: true }));
  };
  
  const restartTimer = () => {
    console.log("Restarting timer...");
    setMinutes(2);
    setSeconds(0);
    setIsActive(false);
    localStorage.setItem("timer", JSON.stringify({ minutes: 2, seconds: 0, isActive: false }));
  };


  const addToCart = (product) => {
    console.log("Adding product to cart:", product);
    setCart((prevCart) => {
      const updatedCart = prevCart.map((item) =>
        item.productId === product.productId
          ? { ...item, quantityCart: item.quantityCart + 1 }
          : item
      );
  
      if (!updatedCart.some((item) => item.productId === product.productId)) {
        updatedCart.push({ ...product, quantityCart: 1 });
      }
  
      localStorage.setItem("cart", JSON.stringify(updatedCart));
      return updatedCart;
    });
  };
  

  const addToCarMultiple = (product, quantity1) => {
    console.log("Adding product to cart:", product, "Quantity:", quantity1);
  
    setCart((prevCart) => {
      const updatedCart = prevCart.map((item) =>
        item.productId === product.productId
          ? { ...item, quantityCart: item.quantityCart + quantity1 } 
          : item
      );
  
      if (!updatedCart.some((item) => item.productId === product.productId)) {
        updatedCart.push({ ...product, quantityCart: quantity1 });
      }
  
      localStorage.setItem("cart", JSON.stringify(updatedCart));
      return updatedCart;
    });
  };



  useEffect(() => {
    if (!isActive) return;
    const interval = setInterval(updateTimer, 1000);
    return () => clearInterval(interval);
  }, [isActive]);

  const removeQuantity = (productId) => {
    console.log("Removing quantity from product:", productId);
    setCart((prevCart) => {
      const updatedCart = prevCart
        .map((item) =>
          item.productId === productId
            ? { ...item, quantityCart: item.quantityCart - 1 }
            : item
        )
        .filter((item) => item.quantityCart > 0);

      console.log("Updated cart after quantity removal:", updatedCart);
      localStorage.setItem("cart", JSON.stringify(updatedCart));
      return updatedCart;
    });
  };

  const clearCart = () => {
    console.log("Clearing the cart...");
    setCart([]);
    localStorage.removeItem("cart");
  };

  const isCartFull = () => {
    const full = cart.length > 0;
    console.log("Is the cart full?", full);
    return full;
  };

  return (
    <CartProductsContext.Provider
      value={{
        cart,
        addToCart,
        removeQuantity,
        clearCart,
        isCartFull,
        minutes,
        seconds,
        isActive,
        startTimer,
        stopTimer,
        restartTimer,
        updateTimer,
        setDeadline,
        addToCarMultiple
      }}
    >
      {children}
    </CartProductsContext.Provider>
  );
};

export default CartProductsContextProvider;
