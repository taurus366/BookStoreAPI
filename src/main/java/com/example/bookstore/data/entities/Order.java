package com.example.bookstore.data.entities;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @Expose()
    @Column
    private String address;

    @Expose
    @Column(name = "book_count")
    private int bookCount;

    @Expose
    @Column(name = "full_name")
    private String fullName;

    @Expose
    @Column(name = "order_date")
    private String orderDate;

    @Expose
    @Column(name = "phone_number")
    private String phoneNumber;

    @Expose
    @Column(name = "quantity_price")
    private double quantityPrice;

    @Expose
    @ManyToOne
    private User user;

    @Expose
    @ManyToOne
    private Book book;

    public Order() {
    }

    public Order(String address, int bookCount, String orderDate, String phoneNumber, double quantityPrice, User user, Book book) {
        this.address = address;
        this.bookCount = bookCount;
        this.orderDate = orderDate;
        this.phoneNumber = phoneNumber;
        this.quantityPrice = quantityPrice;
        this.user = user;
        this.book = book;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getQuantityPrice() {
        return quantityPrice;
    }

    public void setQuantityPrice(double quantityPrice) {
        this.quantityPrice = quantityPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
