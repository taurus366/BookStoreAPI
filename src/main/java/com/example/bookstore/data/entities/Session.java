package com.example.bookstore.data.entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Expose
    @Column(name = "auth_token")
    private String authToken;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;


    public Session() {
    }

    public Session(String authToken, Timestamp createdTime, User user) {
        this.authToken = authToken;
        this.createdTime = createdTime;
        this.user = user;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
