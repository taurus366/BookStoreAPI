package com.example.bookstore.data.repositories;

import com.example.bookstore.data.entities.Session;
import com.example.bookstore.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User getUserBySession(Session session);
    User getUserByEmail(String email);
}
