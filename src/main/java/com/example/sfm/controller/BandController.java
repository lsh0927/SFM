package com.example.sfm.controller;

import com.example.sfm.domain.Band;

import com.example.sfm.domain.Member;
import com.example.sfm.service.BandService;
import com.example.sfm.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("login")
public class BandController {

    private final BandService bandService;
    private final MemberService memberService;

    //@GetMapping으로 url이 왔을 때 보여줄 웹 페이지
    @GetMapping("/band-join-request")
    public String goJoinBandPage(){
        return "band/joinBand";
    }

    @PostMapping("/band-join-request")
    public String bandJoinRequest(@RequestParam("band") String band, HttpSession session) {

        // 세션에서 회원의 식별자를 가져온다
        String userEmail = (String) session.getAttribute("email");

        // 가져온 회원의 식별자를 사용하여 데이터베이스에서 해당 회원의 정보를 가져온다
        Member member = memberService.findMemberByEmail(userEmail);

        Band newBand= new Band();
        newBand.setBandName(band);

        // 악기 정보를 회원의 정보에 업데이트한다
        bandService.join(newBand);
        memberService.updateMemberBand(member,newBand);
        //이걸 한번에 할 수 있는 방법이 있었는데, 연관관계 편의 메서드?
        //일단 나중에 ㅋㅋ



        return "homepage";
    }
}
