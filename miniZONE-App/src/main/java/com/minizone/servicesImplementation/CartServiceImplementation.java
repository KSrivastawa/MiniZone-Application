package com.minizone.servicesImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minizone.*;

@Service
public class CartServiceImplementation implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;



    @Override
    public String addProductToCart(Long cartId, Long productID) throws CartException {
        String message = "Not Added!";

        Product product = productRepository.findById(productID).orElseThrow(()-> new CartException("Product not found!")); 

        if(product != null) {

            Cart addToCart = cartRepository.findById(cartId).orElseThrow(()-> new CartException("Cart not found!"));

            if(addToCart != null) {
                addToCart.getProductList().add(product);

                cartRepository.save(addToCart);

                message = "Product Added to Cart!";
            }

        }

        return message;
    }

    @Override
    public String deleteProductFromCartById(Long cartID, Long productID) throws CartException {

        String message = "Not Deleted!";

        Cart cart = cartRepository.findById(cartID).orElseThrow(()-> new CartException("Cart Not Found!"));

        if(cart != null) {

            Product product = productRepository.findById(productID).orElseThrow(()->new CartException("Product Not Found!"));

            if(product != null) {

                for(int i=0; i<cart.getProductList().size(); i++) {
                    if(cart.getProductList().get(i).getProductId()== productID) {
                        cart.getProductList().remove(i);
                    }
                }
                cartRepository.save(cart);

                message = "Product Deleted From Cart!";

            }

        }

        return message;

    }






}