package com.example.bookstore.data.init;

import com.example.bookstore.data.entities.*;
import com.example.bookstore.data.service.BookService;
import com.example.bookstore.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Engine implements CommandLineRunner {

    @Autowired
    private final BookService bookService;

    @Autowired
    private final UserService userService;

    public Engine(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {

        Book book = new Book("The New York Cafe", "Michael Dean", 22.54, new Timestamp(System.currentTimeMillis()), "English's home", 257, "https://english-e-reader.net/covers/The_New_York_Cafe-Michael_Dean.jpg");
        Book book1 = new Book("Blue Moon Beach", "Sue Murray", 25.55, new Timestamp(System.currentTimeMillis()), "Murray's home", 255, "https://english-e-reader.net/covers/Blue_Moon_Beach-Sue_Murray.jpg");
        Book book2= new Book("Future Energy", "Alex Raynham", 35.54, new Timestamp(System.currentTimeMillis()), "Alex's home", 370, "https://english-e-reader.net/covers/Future_Energy-Alex_Raynham.jpg");
        Book book3 = new Book("Catch Me If You Can", "Frank W. Abagnale", 57.54, new Timestamp(System.currentTimeMillis()), "Alex's home", 300, "https://english-e-reader.net/covers/Catch_Me_If_You_Can-Frank_W_Abagnale.jpg");

        this.bookService.postBook(book);
        this.bookService.postBook(book1);
        this.bookService.postBook(book2);
        this.bookService.postBook(book3);


//   ADMIN PROFILE
        User user = new User("admin", "admin", "adminadmin@gmail.com", "12341234", "0894396766", Gender.Male,"street seventeen number nine");
        user.setRole(Role.ADMIN);
        Session session = new Session("saduasuidhywi535i3ohjfkl", new Timestamp(System.currentTimeMillis()), user);
        user.setSession(session);

        userService.postUser(user);

    }
}
