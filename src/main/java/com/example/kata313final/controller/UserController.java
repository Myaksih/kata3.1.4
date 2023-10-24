package com.example.kata313final.controller;

import com.example.kata313final.model.User;
import com.example.kata313final.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userServiceImp;

    @Autowired
    public UserController(UserService userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/user")
    public String showUser(Principal principal, ModelMap model) {
        User user = userServiceImp.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

}