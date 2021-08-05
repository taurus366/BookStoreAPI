package com.example.bookstore.data.service.impl;

import com.example.bookstore.data.entities.ShoppingCart;
import com.example.bookstore.data.entities.User;
import com.example.bookstore.data.repositories.CartRepository;
import com.example.bookstore.data.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void postCart(ShoppingCart shoppingCart) {
    this.cartRepository.save(shoppingCart);
    }

    @Override
    public Set<ShoppingCart> getAllCartByUser(User user) {
        return this.cartRepository.findAllByUser(user);
    }

    @Override
    public void putCart(Set<ShoppingCart> carts) {
    this.cartRepository.saveAll(carts);
    }

    @Override
    public void deleteCart(ShoppingCart cart) {
    this.cartRepository.delete(cart);
    }

    @Override
    public void deleteAllCartByUser(User user) {
    this.cartRepository.deleteAllByUser(user);
    }
}
