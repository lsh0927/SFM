package com.example.sfm.controller;

import com.example.sfm.domain.Member;
import com.example.sfm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @Transactional
    @PostMapping("/members/save")
    public ResponseEntity<String> memberSave(@RequestBody Member member){
        Member savedMember = memberRepository.save(member);
        System.out.println("Saved Member: " + savedMember);
        return ResponseEntity.status(HttpStatus.CREATED).body("Member saved successfully");
    }

    @Transactional
    @PostMapping("/members/find")
    public ResponseEntity<String> memberFind(@RequestBody Long id){
        Optional<Member> memberId = memberRepository.findById(id);
        System.out.println("Saved Member.id: " + memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body("MemberId find successfully");
    }
}
