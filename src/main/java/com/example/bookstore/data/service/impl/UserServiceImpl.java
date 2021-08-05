package com.example.bookstore.data.service.impl;

import com.example.bookstore.data.entities.Session;
import com.example.bookstore.data.entities.User;
import com.example.bookstore.data.repositories.UserRepository;
import com.example.bookstore.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getUserByAuthToken(Session authToken) {
        return this.userRepository.getUserBySession(authToken);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.getUserByEmail(email);
    }

    @Override
    public User postUser(User user) {
        return this.userRepository.save(user);
    }
}
