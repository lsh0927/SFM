package com.example.sfm.controller;
import com.example.sfm.domain.Member;
import com.example.sfm.repository.MemberRepository;
import com.example.sfm.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
        //Post형식의 members/new라는 url요청을 받을 시 실행
        // Member에 getter와 setter가 설정되어 있음
        // setName을 하는데, form태그로 전달받은 값을 MemberDto안의 getName 메서드를 통해 집어 넣고
        // 그 값이 다시 할당
        // redirect를 통해  첫화면으로 돌아감
    }




    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("members",members);
        return "members/memberList";

        //해당 url 요청시 controller는 스프링이 제공하는 Model 인터페이스 이용
        //Model model을 이용시 model을 따로 지정하지 않아도
        //addAttribute 사용 가능
        // 찾은 멤버들을 members라고 지정된 곳에 해당 list 전달
    }

    //@GetMapping으로 url이 왔을 때 보여줄 웹 페이지
    @GetMapping("/register-instruments")
    public String goInstrumentsJoinPage(){
        return "session/createSession";
    }
    @PostMapping("/register-instruments")
    public String registerInstruments(@RequestParam("instrument") String instrument, HttpSession session) {
        // 악기 등록 처리 로직 작성

        // 세션에서 회원의 식별자(예: 이메일)를 가져온다
        String userEmail = (String) session.getAttribute("email");

        // 가져온 회원의 식별자를 사용하여 데이터베이스에서 해당 회원의 정보를 가져온다
        Member member = memberService.findMemberByEmail(userEmail);
        member.setSession(instrument);

        System.out.println(member.getName());
        System.out.println(member.getEmail());
        System.out.println(member.getSession());

        // 악기 정보를 회원의 정보에 업데이트한다
     //   memberService.updateMember(member);

        System.out.println("선택된 악기: " + instrument);
        //memberService.updateSession(instrument,session);

        return "redirect:/homepage"; // 혹은 다른 적절한 리다이렉션 경로
    }

}