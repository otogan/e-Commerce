package com.ecommerce.web.controller;

import com.ecommerce.web.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    private final List<User> users = new ArrayList<>();

    public UserController() {
        addUser("Onur", "Togan", "onur", "pass", "admin");
        addUser("Chad", "Larsen", "chad", "1234", "user");
        addUser("Ruby", "Waters", "ruby", "abc", "user");
        addUser("Louis", "Metcalfe", "louis", "password", "user");
        addUser("Keaton", "Fellows", "keaton", "mycat", "user");
        addUser("Keyan", "Lord", "key", "mama", "user");
    }

    private User addUser(String firstName, String lastName, String username, String password, String role) {
        int id = users.size();
        User user = new User(id, firstName, lastName, username, password, role, LocalDate.now());
        users.add(user);
        return user;
    }

    private void updateProfileModel(User user, Model model) {
        model.addAttribute("name", user.getFirstName());
        model.addAttribute("lastname", user.getLastName());
        model.addAttribute("role", user.getRole());
        model.addAttribute("date", user.getDateCreated().toString());
    }

    private User findValidUser(User user) {
        for (User next : users) {
            if (next.validate(user.getUsername(), user.getPassword())) {
                return next;
            }
        }
        return null;
    }

    private boolean usernameExists(String username) {
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username)) {
                return true;
            }
        }
        return false;
    }

    @GetMapping("/user/profile")
    public String profile(@RequestParam() int id, Model model) {
        updateProfileModel(users.get(id), model);
        return "profile";
    }

    @PostMapping("/user/register")
    public String register(@RequestBody User user, Model model) {
        if (usernameExists(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Username not accepted!");
        }
        User newUser = addUser(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), "user");
        updateProfileModel(newUser, model);
        return "profile";
    }

    @PostMapping("/user/validate")
    public String validate(@RequestBody User user, Model model) {
        User validUser = findValidUser(user);
        if (validUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        updateProfileModel(validUser, model);
        return "profile";
    }

}
