import { createContext } from "react";
import React, {useEffect, useState } from "react";


export const CartProductsContext = createContext(null);

const CartProductsContextProvider = ({ children }) => {
  const [cart, setCart] = useState(() => {
    const savedCart = localStorage.getItem("cart");
    return savedCart ? JSON.parse(savedCart) : [];
  });





  const addToCart = async (product) => {
    const savedCart1 = localStorage.getItem("cart");
    console.log(savedCart1);
  
    let cartData = savedCart1 ? structuredClone(JSON.parse(savedCart1)) : [];
  
    let existingProduct = cartData.find(item => item.productId === product.productId);
  
    if (existingProduct) {
      existingProduct.quantityCart += 1;
    } else {
      const newProduct = { ...product, quantityCart: 1 };
      cartData.push(newProduct);
    }
  
    localStorage.setItem("cart", JSON.stringify(cartData));
  
    setCart(cartData);
  };
 






  const removeQuantity = (productId) => {
    setCart((prevCart) => {
      const updatedCart = prevCart
        .map((item) =>
          item.productId === productId ? { ...item, quantityCart: item.quantityCart - 1 } : item
        )
        .filter((item) => item.quantityCart > 0); 
        
        localStorage.removeItem("cart")
        localStorage.setItem("cart", JSON.stringify(updatedCart));
        
      return updatedCart;
    });
  };

 

  return (
    <CartProductsContext.Provider value={{ cart, addToCart, removeQuantity }}>
      {children}
    </CartProductsContext.Provider>
  );
};

export default CartProductsContextProvider;
