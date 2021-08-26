package com.example.bookstore.data.service.impl;

import com.example.bookstore.data.entities.Order;
import com.example.bookstore.data.entities.User;
import com.example.bookstore.data.repositories.OrderRepository;
import com.example.bookstore.data.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void postOrder(Order order) {
        this.orderRepository.save(order);
    }

    @Override
    public Set<Order> getAllOrdersByTime() {
        return this.orderRepository.findAllOrdersOrderByAsc();
    }

    @Override
    public void deleteAllByUser(User user) {
    this.orderRepository.deleteAllByUser(user);
    }

    @Override
    public Set<Order> findOrderByUser(User user) {
        return this.orderRepository.findAllByUser(user);
    }

    @Override
    public void deleteById(int id) {
        this.orderRepository.deleteById((long)id);
    }
}
