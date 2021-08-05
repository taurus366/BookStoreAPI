package com.example.bookstore.data.service;

import com.example.bookstore.data.entities.Session;

public interface SessionService {

    Session postSession(Session session);
    Session getSessionByToken(String token);
}
