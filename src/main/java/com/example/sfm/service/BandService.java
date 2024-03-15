package com.example.sfm.service;

import com.example.sfm.domain.Band;
import com.example.sfm.repository.BandRepository;
import com.example.sfm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BaseMultiResolutionImage;

@Service
@RequiredArgsConstructor
public class BandService {

    private BandRepository bandRepository;


    @Transactional
    public void createBand(Band band){
        if (validateDuplicateBand(band))
        {
            bandRepository.save(band);
        }
    }

    private boolean validateDuplicateBand(Band band) {
        if (bandRepository.existsByName(band.getBandName())){
            throw new RuntimeException("same name exists");
        }
        else {
            return  true;
        }

    }
    @Transactional
    public String findBand(Long bandId){
        Band band= bandRepository.findById(bandId)
                .orElseThrow(()-> new RuntimeException("BAND_NOT_FOUND"));
        return band.getBandName();
    }

    @Transactional
    public void updateBand(Long bandId, String newBandName){
        Band band= bandRepository.findById(bandId).orElseThrow(()-> new RuntimeException("BAND_NOT_FOUND"));

        band.setBandName(newBandName);
        bandRepository.save(band);

    }
    @Transactional
    public void deleteBand(Long bandId){
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));

        bandRepository.deleteById(band.getBandId());
    }


}
