package com.example.sfm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller //JSON으로 반환
@RequiredArgsConstructor
public class LoginController {



    @GetMapping("api/v1/homepage")
    public String hello(Model model){
        model.addAttribute("data","hello?");
        return "hello";

    }

    @GetMapping("/home")
    public String page(Model model) {
        return "home"; // 로그인 폼 템플릿 이름
    }


    //현재 members/new로 웹페이지 상에서 등록
    // members로 list 출력

    // 근데 웹 홈페이지가 따로 있어서, 거기서 버튼을 누르면 각 기능에
    // 해당하는 url로 찾아가야 함

    //Todo : LoginController에 웹 홈페이지 만들기
    // 버튼을 만드는 등의 기능은 타임리프 인가? 그 부분 정리


    // 해당 부분은 JPA 강의에서 가져옴

}
