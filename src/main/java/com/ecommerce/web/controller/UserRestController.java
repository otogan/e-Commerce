package com.ecommerce.web.controller;

import com.ecommerce.web.data.entity.User;
import com.ecommerce.web.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    UserRepository repository;

    @Autowired
    public UserRestController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public Iterable<User> getAll() {
        return repository.findAll();
    }
}
