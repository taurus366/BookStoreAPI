package com.example.bookstore.data.service;

import com.example.bookstore.data.entities.Book;

import java.util.List;

public interface BookService {

    void postBook(Book book);
    void deleteBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(long id);
}
