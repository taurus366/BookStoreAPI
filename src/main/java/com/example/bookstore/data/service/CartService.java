package com.example.bookstore.data.service;

import com.example.bookstore.data.entities.ShoppingCart;
import com.example.bookstore.data.entities.User;

import java.util.Set;

public interface CartService {

    void postCart(ShoppingCart shoppingCart);
    Set<ShoppingCart> getAllCartByUser(User user);
    void putCart(Set<ShoppingCart> carts);
    void deleteCart(ShoppingCart cart);
    void deleteAllCartByUser(User user);
}
