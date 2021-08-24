package com.example.bookstore.data.entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart extends BaseEntity{
    @Expose
    @Column(name = "book_count")
    private int bookCount;

    @Expose
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

    @ManyToOne
    private User user;

    public ShoppingCart() {
    }

    public ShoppingCart(int bookCount, Book book, User user) {
        this.bookCount = bookCount;
        this.book = book;
        this.user = user;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
