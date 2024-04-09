package com.example.sfm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    @GetMapping("/login")
    public String login(){
        return "loginn";
    }
    @GetMapping("/signup")
    public String signup(){
        return "signupp";
    }
}
