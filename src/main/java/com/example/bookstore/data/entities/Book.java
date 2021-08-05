package com.example.bookstore.data.entities;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Books")
public class Book extends BaseEntity{

    @Expose
    @Column
    private String title;

    @Expose
    @Column
    private String author;

    @Expose
    @Column
    private double price;

    @Expose
    @Column(name = "issued_date")
    private Timestamp issuedDate;

    @Expose
    @Column(name = "publish_house")
    private String publishHouse;

    @Expose
    @Column
    private int page;

    @Expose
    @Column(name = "image_url")
    private String imgUrl;

    @OneToMany(mappedBy = "book")
    private List<Order> orderList;

    @OneToMany(mappedBy = "book")
    private List<ShoppingCart> shoppingCartList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Book() {
    }

    public Book(String title, String author, double price, Timestamp issuedDate, String publishHouse, int page, String imgUrl) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.issuedDate = issuedDate;
        this.publishHouse = publishHouse;
        this.page = page;
        this.imgUrl = imgUrl;
    }

    public List<ShoppingCart> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Timestamp issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getPublishHouse() {
        return publishHouse;
    }

    public void setPublishHouse(String publishHouse) {
        this.publishHouse = publishHouse;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
