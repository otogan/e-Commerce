package com.ecommerce.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(@RequestParam(name = "name", required = false, defaultValue = "Bot") String name, Model model) {
        model.addAttribute("name", name);
        return "about";
    }

}
