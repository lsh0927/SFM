package com.example.sfm.controller;

import com.example.sfm.domain.JoinRequest;
import com.example.sfm.domain.Member;
import com.example.sfm.repository.JoinRequestRepository;
import com.example.sfm.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final JoinRequestRepository joinRequestRepository;
    private final MemberService memberService;

    public AdminController(JoinRequestRepository joinRequestRepository, MemberService memberService) {
        this.joinRequestRepository = joinRequestRepository;
        this.memberService = memberService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        // 모든 밴드의 가입 요청 목록을 가져와 모델에 추가
        List<JoinRequest> joinRequests = joinRequestRepository.findAll();
        model.addAttribute("joinRequests", joinRequests);

        // 모든 멤버 목록을 가져와 모델에 추가
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);

        return "admin/dashboard";
    }
}
