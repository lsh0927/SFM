package com.example.sfm.controller;

import com.example.sfm.domain.Member;
import com.example.sfm.service.BandService;
import com.example.sfm.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class DashboardController {

    private final MemberService memberService;
    private final BandService bandService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("유저 이메일입니다=");
        String email = (String) session.getAttribute("email"); // 키 값을 "email"로 변경
        System.out.println(email);
        if (email != null) {
            // 세션에서 가져온 사용자 정보를 기반으로 DB에서 추가 정보를 가져온다.
            Member member = memberService.findMemberById(email);
            if (member != null) {
                // 사용자가 밴드 회원인지 확인하고, 모델에 추가한다.
                boolean isBandMember = bandService.isUserBandMember(member);
                model.addAttribute("isBandMember", isBandMember);
                model.addAttribute("nickname", member.getName()); // 사용자 이름도 모델에 추가
                model.addAttribute("JoinedBand",session.getAttribute("JoinedBand"));

            }
            // 대시보드 페이지로 이동한다.
            return "dashboard";
        }
        else {
            // 로그인되지 않은 경우에는 로그인 페이지로 리다이렉트한다.
            return "index";
        }
    }




}

