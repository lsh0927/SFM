package com.example.sfm.service;

import com.example.sfm.domain.Band;
import com.example.sfm.repository.BandRepository;
import com.example.sfm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BaseMultiResolutionImage;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BandService {

    private BandRepository bandRepository;


    @Transactional
    public Long createBand(Band band){
        validateDuplicateBand(band);
        bandRepository.save(band);
        return band.getBandId();
    }

    private void validateDuplicateBand(Band band) {
        List<Band> bandNamelist = bandRepository.findByBandName(band.getBandName());
        if (!bandNamelist.isEmpty())
        {
            throw new RuntimeException("이미 존재하는 밴드명입니다");
        }

    }

    public String findBand(Long bandId){
        Band band= bandRepository.findById(bandId)
                .orElseThrow(()-> new RuntimeException("BAND_NOT_FOUND"));
        return band.getBandName();
    }

    @Transactional
    public void updateBand(Long bandId, String newBandName){
        Band band= bandRepository.findById(bandId).get();
        band.setBandName(newBandName);

    }
    @Transactional
    public void deleteBand(Band band){
        Band deleteband = bandRepository.findById(band.getBandId()).get();
        bandRepository.delete(deleteband);

    }
}
