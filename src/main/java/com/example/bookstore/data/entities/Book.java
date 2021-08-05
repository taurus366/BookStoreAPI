package com.example.bookstore.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "Books")
public class Book extends BaseEntity{

    @Column
    private String title;
    @Column
    private String author;
    @Column
    private double price;
    @Column(name = "issued_date")
    private Timestamp issuedDate;
    @Column(name = "publish_house")
    private String publishHouse;
    @Column
    private int page;
    @Column(name = "image_url")
    private String imgUrl;


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
