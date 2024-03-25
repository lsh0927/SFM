package com.example.sfm.service;

import com.example.sfm.domain.Band;
import com.example.sfm.domain.JoinRequest;
import com.example.sfm.domain.Member;
import com.example.sfm.repository.BandRepository;
import com.example.sfm.repository.JoinRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BandService {

    private final BandRepository bandRepository;
    private final JoinRequestRepository joinRequestRepository;

    @Transactional
    public void join(Band band){
       // validateDuplicateBand(band);
        bandRepository.save(band);

    }

//    private void validateDuplicateBand(Band band) {
//        Band findBand= bandRepository.findByBandName(band.getBandName());
//        if (findBand!=null)
//        {
//            throw new RuntimeException("이미 존재하는 밴드 명입니다");
//        }
//
//    }




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



    public Band findBandByName(String bandName) {
        return bandRepository.findByBandName(bandName);
    }


    // 밴드와 함께 멤버를 가져오는 메서드
    public Band findBandWithMembers(Long bandId) {
        return bandRepository.findBandWithMembers(bandId);
    }

    public List<JoinRequest> getJoinRequestsForBand(Long Id) {
        return joinRequestRepository.findJoinRequestsById(Id);
    }
}
