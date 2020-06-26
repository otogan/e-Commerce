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

    private List<User> users = new ArrayList<>();

    public UserController() {
        users.add(new User(1L, "Chad", "Larsen", LocalDate.now(), "user"));
        users.add(new User(2L, "Ruby", "Waters", LocalDate.now(), "user"));
        users.add(new User(3L, "Louis", "Metcalfe", LocalDate.now(), "user"));
        users.add(new User(4L, "Keaton", "Fellows", LocalDate.now(), "user"));
        users.add(new User(5L, "Keyan", "Lord", LocalDate.now(), "user"));
    }

    @GetMapping("/profile")
    public String profile(@RequestParam(name = "id") int id, Model model) {
        User user = users.get(id);
        model.addAttribute("name", user.getFirstName());
        model.addAttribute("lastname", user.getLastName());
        model.addAttribute("role", user.getRole());
        model.addAttribute("date", user.getDateCreated().toString());
        return "profile";
    }

}
