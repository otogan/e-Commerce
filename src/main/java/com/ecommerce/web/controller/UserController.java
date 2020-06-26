package com.ecommerce.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {


    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("name", "Alex");
        model.addAttribute("lastname", "Desouza");
        model.addAttribute("role", "Player");
        model.addAttribute("date", "6/25/2020");
        return "profile";
    }
}
