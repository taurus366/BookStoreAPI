package com.example.bookstore.data.repositories;

import com.example.bookstore.data.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
    Book getBookById(Long id);
}
