package com.example.bookstore.data.web;

import com.example.bookstore.data.entities.Order;
import com.example.bookstore.data.entities.Role;
import com.example.bookstore.data.entities.ShoppingCart;
import com.example.bookstore.data.entities.User;
import com.example.bookstore.data.service.CartService;
import com.example.bookstore.data.service.OrderService;
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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private final Gson gson;
    @Autowired
    private final SessionService sessionService;

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final CartService cartService;

    @Autowired
    private final UserService userService;

//    @Autowired
//    private final




    public OrderController(Gson gson, SessionService sessionService, OrderService orderService, CartService cartService, UserService userService) {
        this.gson = gson;
        this.sessionService = sessionService;
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @RequestMapping(value = "/post",method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postOrder (@RequestBody String authTokenJson){
        String authToken = gson.fromJson(authTokenJson, JsonObject.class).get("authToken").getAsString();

        User user = this.sessionService.getSessionByToken(authToken) != null ? this.sessionService.getSessionByToken(authToken).getUser() : null;

        if (user != null){
            List<ShoppingCart> shoppingCartList = user.getShoppingCartList();
            if (shoppingCartList.size() > 0){
                for (ShoppingCart shoppingCart : shoppingCartList) {
                    Timestamp ts = new Timestamp(System.currentTimeMillis());

                    Order order = new Order(user.getAddress(), shoppingCart.getBookCount(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts), user.getPhoneNumber(), shoppingCart.getBook().getPrice(), user, shoppingCart.getBook());
                    this.orderService.postOrder(order);
                }
                this.cartService.getAllCartByUser(user);
                return new ResponseEntity<>(gson.toJson("Successful ordered Thank you!"), new HttpHeaders(), HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(gson.toJson("Your cart is empty!"), new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gson.toJson("Please log in again"), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOrders(@RequestBody String authTokenJson){
        String authToken = gson.fromJson(authTokenJson, JsonObject.class).get("authToken").getAsString();
        User user = this.sessionService.getSessionByToken(authToken) != null ? this.sessionService.getSessionByToken(authToken).getUser() : null;
        if (user != null){
            if (user.getRole().equals(Role.ADMIN)){
                Set<Order> orderSet = this.orderService.getAllOrdersByTime();
                HashMap<String, Set<Order>> setHashMap = new LinkedHashMap<>();

                for (Order order : orderSet) {
                    String fullName = order.getUser().getFirstName() + " " + order.getUser().getSecondName();

                    if (!setHashMap.containsKey(fullName)){
                        setHashMap.put(fullName, new LinkedHashSet<>());
                        setHashMap.get(fullName).add(order);
                    }else if (setHashMap.containsKey(fullName)){
                        setHashMap.get(fullName).add(order);
                    }
                }
                return new ResponseEntity<>(gson.toJson(setHashMap), new HttpHeaders(), HttpStatus.OK);
            }
            return new ResponseEntity<>(gson.toJson("Please log in as ADMIN!"), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(gson.toJson("Please log in"), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}
