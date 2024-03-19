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

    @PostMapping("/newBand")
    public ResponseEntity<Object> createBand(@RequestBody Band band){
        bandService.join(band);
        return new ResponseEntity<>(band,HttpStatus.OK);
    }

    @GetMapping("/newBand")
    public String createBand(Model model){
         model.addAttribute("bandDto", "hello");
        return "bands/new";
    }

    @DeleteMapping("/band")
    public ResponseEntity<Object> deleteMember(@RequestParam Long bandId){
        bandService.deleteBand(bandId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
