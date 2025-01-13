//import { createContext, useState } from "react";
//import {products} from "../assets/products.js"
//
//
//
//
//export const ProductsContext = createContext([]);
//
//
//
//
//const ProductsContextProvider = ({children}) =>{
//
//    
//
//    const [products, setProducts] = useState([]);
//
//
//    useEffect(() => {
//        localStorage.setItem('cartProducts', JSON.stringify(cartProducts));
//    }, [cartProducts]);
//
//
//    const addToCart = (product) => {
//      setCartProducts((prev) => {
//          const existingProduct = prev.find(p => p.productId === product.productId);
//          
//          // Jeśli produkt istnieje w koszyku, zaktualizuj ilość
//          if (existingProduct) {
//              return prev.map(p => 
//                  p.productId === product.productId 
//                      ? { ...p, quantity: (p.quantity || 1) + 1 }
//                      : p
//              );
//          }
//          
//          // Jeśli produkt nie istnieje, dodaj go z ilością 1
//          return [...prev, { ...product, quantity: 1 }];
//      });
//  };
// 
//
//
//  const removeFromCart = (productId) => {
//    setCartProducts((prev) => {
//      const existingProduct = prev.find((p) => p.productId === productId);
//      if (existingProduct) {
//        if (existingProduct.quantity > 1) {
//          console.log("1: -", cartProducts)
//  
//          return prev.map((p) =>
//            p.productId === productId
//              ? { ...p, quantity: p.quantity - 1 } 
//              : p
//          );
//        } else {
//          console.log("2: -", cartProducts)
//          return prev.filter((p) => p.productId !== productId);
//        }
//      }
//      return prev;
//    });
//  
//  
//  };
// 
//
//
//
//    return(
//        <CartProductsContext.Provider value={{products, cartProducts, addToCart, removeFromCart}}>
//            {children}
//        </CartProductsContext.Provider>
//    );
//
//}
//export default ProductsContextProvider; 
//