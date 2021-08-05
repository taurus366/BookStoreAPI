package com.example.bookstore.data.service.impl;

import com.example.bookstore.data.entities.Session;
import com.example.bookstore.data.repositories.SessionRepository;
import com.example.bookstore.data.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionImpl implements SessionService {

    @Autowired
    private final SessionRepository sessionRepository;

    public SessionImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session postSession(Session session) {
        return this.sessionRepository.save(session);
    }

    @Override
    public Session getSessionByToken(String token) {
        return this.sessionRepository.getSessionByAuthToken(token);
    }
}
