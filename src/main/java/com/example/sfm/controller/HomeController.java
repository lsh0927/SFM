package com.example.sfm.controller;

import com.example.sfm.domain.Member;
import com.example.sfm.repository.BandRepository;
import com.example.sfm.repository.MemberRepository;
import com.example.sfm.service.BandService;
import com.example.sfm.service.MemberService;
import com.example.sfm.social.KakaoApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final BandService bandService;


    KakaoApi kakaoApi= new KakaoApi();


    @RequestMapping(value = "/login")
    public void login(@RequestParam("code") String code, HttpServletRequest request,
                        HttpServletResponse response) throws IOException {

        //1번. 인증코드 요철 전달
        String accessToken= kakaoApi.getAccessToken(code);
        //2번 인증코드로 토큰 전달
        HashMap<String,Object> userInfo=kakaoApi.getUserInfo(accessToken);

        System.out.println("login info: " +userInfo.toString());
        if (userInfo.get("email")!=null)
        {

            HttpSession session = request.getSession();
            session.setAttribute("email", userInfo.get("email")); // 키 값을 "email"로 변경
            session.setAttribute("nickname", userInfo.get("nickname"));

            // 로그아웃시에 엑세스토큰으로 요청을 보내야 함
            session.setAttribute("accessToken", accessToken); // 엑세스 토큰 저장

            // 사용자 정보 DB에 저장
            Member member = new Member();
            member.setName((String) userInfo.get("nickname"));
            member.setEmail((String) userInfo.get("email"));
            memberService.join(member);


            //리다이렉션
            response.sendRedirect("/login/dashboard");

        }
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session){
        ModelAndView mav= new ModelAndView();
        System.out.println("로그아웃 중");
        String s= (String)session.getAttribute("accessToken");
        System.out.println(s);
        //여기서 엑세스 토큰을 못받아옴...
        // 어케하지?
        kakaoApi.kakaoLogout((String)session.getAttribute("accessToken"));

        session.removeAttribute("accessToken");
        session.removeAttribute("userId");
        mav.setViewName("index");
        return mav;
    }
}
