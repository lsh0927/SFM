package com.example.sfm.service;

import com.example.sfm.domain.Band;
import com.example.sfm.domain.Member;
import com.example.sfm.repository.BandRepository;
import com.example.sfm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BaseMultiResolutionImage;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BandService {

    private BandRepository bandRepository;

    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @Transactional
    public void join(Band band){
        validateDuplicateBand(band);
        bandRepository.save(band);

    }

    private void validateDuplicateBand(Band band) {
        List<Band> bandNamelist = bandRepository.findByBandName(band.getBandName());
        if (!bandNamelist.isEmpty())
        {
            throw new RuntimeException("이미 존재하는 밴드 명입니다");
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

    public List<Band> findAll(){
        return bandRepository.findAll();
    }

    @Transactional
    public void deleteBand(Long bandId){
        Optional<Band> band = bandRepository.findById(bandId);
        bandRepository.deleteById(band.get().getBandId());
    }

    public boolean isUserBandMember(Member member) {
        return false;
    }

    public void joinBand(Member member) {
    }
}
