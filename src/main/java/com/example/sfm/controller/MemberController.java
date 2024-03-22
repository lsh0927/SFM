package com.example.sfm.controller;
import com.example.sfm.domain.Member;
import com.example.sfm.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("login")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createFrom(){
        return "members/createMemberForm";
        //html파일을 반환
    }

    @PostMapping("/members/new")
    public String create(Member member){
        memberService.join(member);
        return "redirect:/home";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("members",members);
        return "members/memberList";
    }

    //@GetMapping으로 url이 왔을 때 보여줄 웹 페이지
    @GetMapping("/register-instruments")
    public String goInstrumentsJoinPage(){
        return "session/createSession";
    }
    @PostMapping("/register-instruments")
    public String registerInstruments(@RequestParam("instrument") String instrument, HttpSession session) {

        // 세션에서 회원의 식별자 가져옴
        String userEmail = (String) session.getAttribute("email");
        // 가져온 회원의 식별자를 사용하여 데이터베이스에서 해당 회원의 정보를 가져옴
        Member member = memberService.findMemberByEmail(userEmail);
        // 악기 정보를 회원의 정보에 업데이트한다
        memberService.updateMemberSession(member,instrument);

        return "dashboard"; // 혹은 다른 적절한 리다이렉션 경로
    }

}