package com.example.sfm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private BandController bandController;
    private MemberController memberController;

    public LoginController(BandController bandController, MemberController memberController) {
        this.bandController = bandController;
        this.memberController = memberController;
    }

    @GetMapping("api/v1/homepage")
    public String hello(Model model){
        model.addAttribute("data","hello?");
        return "hello";

    }


}
