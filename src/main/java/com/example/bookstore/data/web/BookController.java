package com.example.bookstore.data.web;

import com.example.bookstore.data.entities.Book;
import com.example.bookstore.data.entities.Role;
import com.example.bookstore.data.entities.User;
import com.example.bookstore.data.service.BookService;
import com.example.bookstore.data.service.SessionService;
import com.example.bookstore.data.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final Gson gson;
    @Autowired
    private final BookService bookService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final SessionService sessionService;

    public BookController(Gson gson, BookService bookService, UserService userService, SessionService sessionService) {
        this.gson = gson;
        this.bookService = bookService;
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postBook(@RequestBody String bookAndAuthJson){

        Book book = gson.fromJson(bookAndAuthJson, Book.class);
        User user = this.sessionService.getSessionByToken(gson.fromJson(bookAndAuthJson, JsonObject.class).get("role").getAsString()).getUser();

        if (user != null && user.getRole().equals(Role.ADMIN)){
            this.bookService.postBook(book);
            return new ResponseEntity<>(gson.toJson("created!"), new HttpHeaders(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(gson.toJson("Please log in as ADMIN to ADD new Book to the system!"), new HttpHeaders(),HttpStatus.UNAUTHORIZED);
    }
}
