package com.example.sfm.controller;

import com.example.sfm.service.BandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BandController {

    private final BandService bandService;

    @Transactional
    @PostMapping("api/v1/band/find")
    public ResponseEntity<String> bandFind(@RequestBody Long bandId){
        String bandName= bandService.findBand(bandId);
        System.out.println("bandName="+bandName);
        return ResponseEntity.status(HttpStatus.CREATED).body("bandName find successfully");
    }
}
