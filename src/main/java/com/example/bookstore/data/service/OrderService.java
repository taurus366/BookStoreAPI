package com.example.bookstore.data.service;

import com.example.bookstore.data.entities.Order;
import com.example.bookstore.data.entities.User;

import java.util.Set;

public interface OrderService {

    void postOrder(Order order);
    Set<Order> getAllOrdersByTime();
    void deleteAllByUser(User user);
    Set<Order> findOrderByUser(User user);
}
