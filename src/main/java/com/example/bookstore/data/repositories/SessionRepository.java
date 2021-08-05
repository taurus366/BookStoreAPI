package com.example.bookstore.data.repositories;

import com.example.bookstore.data.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {

    Session getSessionByAuthToken(String authToken);
}
