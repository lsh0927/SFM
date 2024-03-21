package com.example.sfm.controller;

import com.example.sfm.domain.Member;
import com.example.sfm.repository.BandRepository;
import com.example.sfm.repository.MemberRepository;
import com.example.sfm.service.BandService;
import com.example.sfm.service.MemberService;
import com.example.sfm.social.KakaoApi;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final BandService bandService;

//    @Value("${kakaoUrl}")
//    private String kakaoUrl;

    KakaoApi kakaoApi= new KakaoApi();


    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam("code") String code,
                              HttpSession session){
        ModelAndView mav= new ModelAndView();

        //1번. 인증코드 요철 전달
        String accessToken= kakaoApi.getAccessToken(code);
        //2번 인증코드로 토큰 전달
        HashMap<String,Object> userInfo=kakaoApi.getUserInfo(accessToken);

        System.out.println("login info: " +userInfo.toString());
        if (userInfo.get("email")!=null)
        {
            session.setAttribute("userId",userInfo.get("email"));
            session.setAttribute("accessToken",accessToken);
        }
        mav.addObject("userId",userInfo.get("email"));
        mav.setViewName("index");


        //세션에서 값을 받아와서, 내 DB와 연동
        //어떻게?
        //세션이 갖고 있는 값= 닉네임, 프로필 사진, 이메일
        //이 값을 DB에 그대로 저장?
        //그냥 얘네가 멤버가 되어야 하는게 맞는건가 -> 무조건 카카오 이용자만 가입 가능한 문제 생기지만 진행시켜
        //얘네로 가입을 하자 그냥

        Member member= new Member();
        member.setName((String) userInfo.get("nickname"));
        member.setEmail((String) userInfo.get("email"));

        memberService.join(member);

        //회원 정보를 업데이트 하고 싶다?
        // 그럼 어떻게 접근할까
        // 세션, 밴드는 카카오 인증으로 받아온 정보가 아닌, 시스템 로직상 웹페이지 form에서 입력받고 Mysql에서 관리하니까
        // update메서드를 멤버 서비스에 추가해야함 -> 근데 업데이트 기능은 나중에 하자




        return mav;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session){
        ModelAndView mav= new ModelAndView();
        kakaoApi.kakaoLogout((String)session.getAttribute("accessToken"));
        session.removeAttribute("accessToken");
        session.removeAttribute("userId");
        mav.setViewName("index");
        return mav;
    }
}
