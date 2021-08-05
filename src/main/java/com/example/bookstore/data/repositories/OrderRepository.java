package com.example.bookstore.data.repositories;

import com.example.bookstore.data.entities.Order;
import com.example.bookstore.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o ORDER BY o.orderDate ASC")
    Set<Order> findAllOrdersOrderByAsc();
    Set<Order> findAllByUser(User user);
    void deleteAllByUser(User user);
}
