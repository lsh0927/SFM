package com.example.sfm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("api/v1/homepage")
    public String hello(Model model){
        model.addAttribute("data","hello?");
        return "hello";
    }

    @GetMapping("/")
    public String page(Model model) {
        return "index"; // 로그인 폼 템플릿 이름
    }


}
