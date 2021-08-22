package com.example.bookstore.data.web;

import com.example.bookstore.data.entities.Role;
import com.example.bookstore.data.entities.Session;
import com.example.bookstore.data.entities.User;
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

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final Gson gson;
    @Autowired
    private final UserService userService;
    @Autowired
    private final SessionService sessionService;

    public UserController(Gson gson, UserService userService, SessionService sessionService) {
        this.gson = gson;
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public ResponseEntity<String> logUser(@RequestBody String userJson) {
//        String authToken = gson.fromJson(userJson, JsonObject.class).get("authToken").getAsString();
//        User user = sessionService.getSessionByToken(authToken).getUser();

        User user = gson.fromJson(userJson, User.class);
        if (checkIfUserExist(user.getEmail(), user.getPassword())) {
            User respond = this.userService.getUserByEmail(user.getEmail());

            return new ResponseEntity<>(gson.toJson(respond), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson("Email or password are Invalid!"), HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody String userJson) {
        User user = gson.fromJson(userJson, User.class);

        if (checkIfUserExist(user.getEmail(), null)) {
            return new ResponseEntity<>(gson.toJson("Email is already exist!"), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
        }

        Session session = new Session(generateAuthToken(), new Timestamp(System.currentTimeMillis()), user);
        user.setSession(session);
        user.setRole(Role.CLIENT);

        User createdUser = this.userService.postUser(user);

        return new ResponseEntity<>(gson.toJson(createdUser), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/change/information", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> changeUserInformation(@RequestBody String userAndSessionJson) {
        User user = this.sessionService.getSessionByToken(gson.fromJson(userAndSessionJson, JsonObject.class).get("authToken").getAsString()).getUser();
        User userChange = gson.fromJson(userAndSessionJson, User.class);
        if (user != null) {

            String newPassword = userChange.getPassword() == null ? "" : userChange.getPassword();
            String newAddress = userChange.getAddress() == null ? "" : userChange.getAddress();
            String newPhoneNumber = userChange.getPhoneNumber() == null ? "" : userChange.getPhoneNumber();
            String newFirstName = userChange.getFirstName() == null ? "" : userChange.getFirstName();
            String newSecondName = userChange.getSecondName() == null ? "" : userChange.getSecondName();

            if (newPassword.length() > 5) {
                user.setPassword(newPassword);
            }
            if (newAddress.length() > 5) {
                user.setAddress(newAddress);
            }
            if (newPhoneNumber.length() > 5) {
                user.setPhoneNumber(newPhoneNumber);
            }
            if (newFirstName.length() > 5) {
                user.setFirstName(newFirstName);
            }
            if (newSecondName.length() > 5) {
                user.setSecondName(newSecondName);
            }


            return new ResponseEntity<>(gson.toJson(this.userService.postUser(user)), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson("Please log in!"), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }


    private Boolean checkIfUserExist(String email, String password) {
        User user = this.userService.getUserByEmail(email);
        if (password == null) {
            return user != null;
        }
        if (user == null) {
            return false;
        }
        return user.getEmail().equals(email) && user.getPassword().equals(password);
    }

    private String generateAuthToken() {
        SecureRandom secureRandom = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();

        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);


    }
}
