package com.example.sfm.controller;

import com.example.sfm.domain.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
@Slf4j
@Controller
@RequiredArgsConstructor
@CrossOrigin("*")

public class LoginController {






    @GetMapping("api/v1/homepage")
    public String hello(Model model){
        model.addAttribute("data","hello?");
        return "hello";

        /*
        모델 객체는 컨트롤러에서 생성된 데이터를 뷰로 전달할때 사용하는 객체다
        전달하는 방법이 addAttribute(String name, Object value)
         value 객체를 name이름으로 전달해줌

         만약 리스트를 반환하고 싶다면
        model.addAttribute("list",memberService.findAll());
        이런 식으로
         */

        /*
        http 헤더: 전송에 필요한 모든 부가정보
        http 바디: 실제 전송할 데이터
         */

        /*
        get
        리소스 조회
        서버에 전달하고 싶은 데이터는 query를 통해 전달
        메세지 바디를 사용해서 데이터를 전달, 권장은 x
         */

        /*
        Post
        요청 데이터 처리
        메세지 바디를 통해 서버로 요청 데이터 전달
        서버는 요청 데이터를 처리
        -> 메세지 바디를 통해 들어온 데이터를 처리하는 모든 기능을 수행함
         */
        /*
        Post에서 그런 요청 데이터를 어떻게 처리한다는 것일까
        --> 대상 리소스가 리소스의 고유한 의미 체계에 따라 요청에 포함된 표현 처리
        html 양식에 입력된 필드와 같은 데이터 블록을 데이터 처리 프로세스에 제공
        등등...

        새 리소스 생성
        요청 데이터 처리
        JSON로 조회 데이터를 넘겨야 하는데, Get메서드를 사용하기 어려운 경우
        put(리소스 대체)

         */

        /*
        안전/멱등/캐시가능
        Post는 멱등 아님
         */
    }

    @GetMapping("/")
    public String page(Model model) {
        return "index"; // 로그인 폼 템플릿 이름
    }


}
