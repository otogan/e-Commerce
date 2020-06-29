package com.ecommerce.web.controller;

import com.ecommerce.web.data.entity.User;
import com.ecommerce.web.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    private void updateProfileModel(User user, Model model) {
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("role", user.getRole());
        model.addAttribute("date", user.getDateCreated().toString());
    }

    @GetMapping("/user")
    public String profile(@RequestParam() long id, Model model) {
        User user = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateProfileModel(user, model);
        return "profile";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        User validUser = repository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        updateProfileModel(validUser, model);
        return "profile";
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user, Model model) {
        boolean userExists = repository.findByUsername(user.getUsername()).isPresent();
        if (userExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username exists!");
        }
        if (user.getUsername() == null) {
            user.setUsername(user.getEmail());
        }
        user.setDateCreated(LocalDate.now());
        user.setRole("USER");
        User newUser;
        try {
            newUser = repository.save(user);
        } catch (Exception e) {
            System.out.println("Could not create user!");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Couldn't save user.");
        }
        updateProfileModel(newUser, model);
        return "profile";
    }


}
