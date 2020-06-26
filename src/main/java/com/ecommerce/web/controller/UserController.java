package com.ecommerce.web.controller;

import com.ecommerce.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private void addUser(String firstName, String lastName, String username, String password, String role) {
        users.add(new User(users.size(), firstName, lastName, username, password, role, LocalDate.now()));
    }

    @GetMapping("/user/profile")
    public String profile(@RequestParam() int id, Model model) {
        User user = users.get(id);
        model.addAttribute("name", user.getFirstName());
        model.addAttribute("lastname", user.getLastName());
        model.addAttribute("role", user.getRole());
        model.addAttribute("date", user.getDateCreated().toString());
        return "profile";
    }


}
