package com.example.bookstore.data.service.impl;

import com.example.bookstore.data.entities.Book;
import com.example.bookstore.data.repositories.BookRepository;
import com.example.bookstore.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceImpl implements BookService {


@Autowired
private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public void postBook(Book book) {
    this.bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
    this.bookRepository.delete(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book getBookById(long id) {
        return this.bookRepository.getBookById(id);
    }
}
