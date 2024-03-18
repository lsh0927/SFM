package com.example.sfm.controller;

import com.example.sfm.domain.Member;
import com.example.sfm.repository.MemberRepository;
import com.example.sfm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Transactional
    @PostMapping("api/v1/members/find")
    public ResponseEntity<String> memberFind(@RequestBody Long memberId) {
        Member findMember = memberService.findOne(memberId);
        System.out.println("memberName=" +findMember.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body("MemberId find successfully");
    }



}