package com.example.sfm.service;

import com.example.sfm.domain.Member;
import com.example.sfm.repository.BandRepository;
import com.example.sfm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void createMember(Member member){
        if (validateDuplicateMember(member))
        memberRepository.save(member);

    }

    private boolean validateDuplicateMember(Member member) {
        if (!memberRepository.existsByName(member.getName())){
            throw new RuntimeException("same name exists");
        }
        else {
            return true;
        }
    }

    @Transactional
    public String findMember(Long memberId){
        Member member= memberRepository.findById(memberId).orElseThrow(()-> new RuntimeException("MEMBER_NOT_FOUND"));
        return member.getName();
    }


    @Transactional
    public void updateMember(Long memberId, String newMemberName){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("USER_NOT_FOUND"));
        member.setName(newMemberName);
        memberRepository.save(member);
    }


    @Transactional
    public void deleteMember(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));

        memberRepository.deleteById(member.getMemberId());
    }



}
