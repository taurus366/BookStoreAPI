package com.example.bookstore.data.web;

import com.example.bookstore.data.entities.Book;
import com.example.bookstore.data.entities.ShoppingCart;
import com.example.bookstore.data.entities.User;
import com.example.bookstore.data.service.BookService;
import com.example.bookstore.data.service.CartService;
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

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private final Gson gson;
    @Autowired
    private final UserService userService;
    @Autowired
    private final BookService bookService;
    @Autowired
    private final SessionService sessionService;
    @Autowired
    private final CartService cartService;




    public CartController(Gson gson, UserService userService, BookService bookService, SessionService sessionService, CartService cartService) {
        this.gson = gson;
        this.userService = userService;
        this.bookService = bookService;
        this.sessionService = sessionService;
        this.cartService = cartService;
    }



    @RequestMapping(value = "/post" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postCart(@RequestBody String cartAndAuthTokenJson){
        String bookId = gson.fromJson(cartAndAuthTokenJson, JsonObject.class).get("id").getAsString();

        Book book = this.bookService.getBookById(gson.fromJson(cartAndAuthTokenJson, JsonObject.class).get("id").getAsLong());
        User user = this.sessionService.getSessionByToken(gson.fromJson(cartAndAuthTokenJson, JsonObject.class).get("authToken").getAsString()) != null ?  this.sessionService.getSessionByToken(gson.fromJson(cartAndAuthTokenJson, JsonObject.class).get("authToken").getAsString()).getUser() : null;

        if (user != null && book != null){
            Set<ShoppingCart> shoppingCarts = this.cartService.getAllCartByUser(user);
            for (ShoppingCart item: shoppingCarts) {
                if (item.getBook().getId().equals(book.getId())){
                    item.setBookCount(item.getBookCount() + 1);
                    this.cartService.putCart(shoppingCarts);
                    return new ResponseEntity<>(gson.toJson("Book already was in your Cart , we have updated book count!"), new HttpHeaders(), HttpStatus.OK);
                }
            }
            this.cartService.postCart(new ShoppingCart(1, book, user));

            return new ResponseEntity<>(gson.toJson("Successful added " + book.getTitle() + " to your Cart!"), new HttpHeaders(),HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson("Please log in again or this book already was deleted!"), new HttpHeaders(), HttpStatus.UNAUTHORIZED);

    }

    @RequestMapping(value = "/get" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCartOfTheUser(@RequestBody String authTokenJson){
        User user = this.sessionService.getSessionByToken(gson.fromJson(authTokenJson, JsonObject.class).get("authToken").getAsString()) != null ? this.sessionService.getSessionByToken(gson.fromJson(authTokenJson, JsonObject.class).get("authToken").getAsString()).getUser() : null;
        if (user != null){
            List<ShoppingCart> shoppingCartList = user.getShoppingCartList();
            return new ResponseEntity<>(gson.toJson(shoppingCartList), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson("Please log in!"), HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/put" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editBookQuantity (@RequestBody String json){
        User user = this.sessionService.getSessionByToken(gson.fromJson(json, JsonObject.class).get("authToken").getAsString()) != null ? this.sessionService.getSessionByToken(gson.fromJson(json, JsonObject.class).get("authToken").getAsString()).getUser() : null;
        String type = gson.fromJson(json, JsonObject.class).get("type").getAsString();
        long cartId = gson.fromJson(json, JsonObject.class).get("cartId").getAsLong();

        if (user != null){
            Set<ShoppingCart> shoppingCarts = this.cartService.getAllCartByUser(user);
            String message = type.equals("plus") ? "Successful updated quantity of book" : type.equals("minus") ? "Successful updated quantity of book" : type.equals("delete") ? "Successful deleted the book from your cart!" : "";
            if (type.equals("minus") || type.equals("plus")){
                for (ShoppingCart shoppingCart : shoppingCarts) {
                    if (shoppingCart.getId() == cartId && shoppingCart.getUser() == user){
                        if (type.equals("plus")){
                            shoppingCart.setBookCount(shoppingCart.getBookCount() + 1);
                        }else {
                            shoppingCart.setBookCount(shoppingCart.getBookCount() - 1);
                        }
                        this.cartService.putCart(shoppingCarts);
                        return new ResponseEntity<>(gson.toJson(message), new HttpHeaders(),HttpStatus.OK);
                    }
                }
                return new ResponseEntity<>(gson.toJson("We couldn't find the item! please contact with admin of the site"), new HttpHeaders(),HttpStatus.NOT_FOUND);

            }else if (type.equals("delete")){
                shoppingCarts
                        .forEach(item -> {
                            if (item.getId() == cartId && item.getUser() == user){
                                this.cartService.deleteCart(item);
                            }
                        });
                return new ResponseEntity<>(gson.toJson(message), new HttpHeaders(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(gson.toJson("Please log in again maybe have problem with session token"), new HttpHeaders(),HttpStatus.UNAUTHORIZED);
    }
}
