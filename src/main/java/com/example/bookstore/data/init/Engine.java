package com.example.bookstore.data.init;

import com.example.bookstore.data.entities.Book;
import com.example.bookstore.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Engine implements CommandLineRunner {

    @Autowired
    private final BookService bookService;

    public Engine(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public void run(String... args) throws Exception {

        Book book = new Book("ali' Book", "ali zinal", 2.54, new Timestamp(System.currentTimeMillis()), "ali's home", 255, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Book_stub_img.svg/1024px-Book_stub_img.svg.png");
        Book book1 = new Book("ali' Book", "ali zinal", 2.54, new Timestamp(System.currentTimeMillis()), "ali's home", 255, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Book_stub_img.svg/1024px-Book_stub_img.svg.png");
        Book book2= new Book("ali' Book", "ali zinal", 2.54, new Timestamp(System.currentTimeMillis()), "ali's home", 255, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Book_stub_img.svg/1024px-Book_stub_img.svg.png");
        Book book3 = new Book("ali' Book", "ali zinal", 2.54, new Timestamp(System.currentTimeMillis()), "ali's home", 255, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Book_stub_img.svg/1024px-Book_stub_img.svg.png");

        this.bookService.postBook(book);
        this.bookService.postBook(book1);
        this.bookService.postBook(book2);
        this.bookService.postBook(book3);

    }
}
