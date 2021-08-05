package com.example.bookstore.data.service;

import com.example.bookstore.data.entities.Session;
import com.example.bookstore.data.entities.User;

public interface UserService {

    User getUserByAuthToken(Session authToken);
    User getUserByEmail(String email);
    User postUser(User user);
}
