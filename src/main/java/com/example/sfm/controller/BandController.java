package com.example.sfm.controller;

import com.example.sfm.domain.Band;

import com.example.sfm.service.BandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/band")
@RestController
@RequiredArgsConstructor
public class BandController {

    private final BandService bandService;

    @Transactional
    @PostMapping("find")
    public ResponseEntity<String> bandFind(@RequestBody Long bandId){
        String bandName= bandService.findBand(bandId);
        System.out.println("bandName="+bandName);
        return ResponseEntity.status(HttpStatus.CREATED).body("bandName find successfully");
    }

    // 생각해보니 밴드는 그냥 밴드...?
    // 그럼 멤버에서 밴드를 창단한다면 ?
    // 그때 창단 멤버가 관리하도록 하면 되겠네
    // enum으로 멤버 클래스에 넣자
    // 밴드장, 밴드원, 등등...
    // 그럼 회원이 밴드장일 때만 밴드의 기능을 관리
    // 근데 이건 일정 기능 다 구현하고 나서 구현
    // 이미 시스템 설계만 해놓자
    //
    @PostMapping("/bands/new")
    public ResponseEntity<Object> createBand(@RequestBody Band band){
        bandService.join(band);
        return new ResponseEntity<>(band,HttpStatus.OK);
    }

    @GetMapping("/newBand")
    public String findBand(Model model){
         model.addAttribute("bandDto", bandService.findAll());
        return "bands/new";
    }

    @DeleteMapping("/band")
    public ResponseEntity<Object> deleteMember(@RequestParam Long bandId){
        bandService.deleteBand(bandId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
