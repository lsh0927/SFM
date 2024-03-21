package com.example.sfm.controller;

import com.example.sfm.domain.Band;

import com.example.sfm.service.BandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class BandController {

    private final BandService bandService;


    @PostMapping("/band-join-request")
    public String bandJoinRequest() {
        // 밴드 가입 신청 처리 로직 작성
        System.out.println("밴드 가입 신청 로직 확인");
        return "redirect:/dashboard"; // 혹은 다른 적절한 리다이렉션 경로
    }

}
