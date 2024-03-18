package com.example.sfm.service;

import com.example.sfm.domain.Member;
import com.example.sfm.repository.BandRepository;
import com.example.sfm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getMemberId();
    }

    private void validateDuplicateMember(Member member) {
       List<Member> findMembers= memberRepository.findByName(member.getName());
       if (!findMembers.isEmpty()){
           throw new RuntimeException("이미 존재하는 회원입니다");

       }

    }

 //   @Transactional
    public Member findOne(Long memberId){
        return memberRepository.findById(memberId).get();
    }


    @Transactional
    public void updateMember(Long memberId, String newMemberName){
        Member member = memberRepository.findById(memberId).get();
        member.setName(newMemberName);
    }


    @Transactional
    public void deleteMember(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        memberRepository.deleteById(member.getMemberId());

    }



}
