package com.example.bookstore.data.repositories;

import com.example.bookstore.data.entities.ShoppingCart;
import com.example.bookstore.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CartRepository extends JpaRepository<ShoppingCart,Long> {

    void deleteAllByUser(User user);
   Set<ShoppingCart> findAllByUser(User user);
}
