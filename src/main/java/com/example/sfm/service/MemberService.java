package com.example.sfm.service;

import com.example.sfm.domain.Member;
import com.example.sfm.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getMemberId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers= memberRepository.findByName(member.getName());
        Member findEmail= memberRepository.findByEmail(member.getEmail());

        //합쳐서 비교하는 로직 수정 필요
       if (!findMembers.isEmpty()){
           throw new RuntimeException("이미 존재하는 회원입니다");
       }

    }

 //   @Transactional
    public Member findOne(Long memberId){
        return memberRepository.findById(memberId).get();
    }
    public List<Member> findAll(){
        return memberRepository.findAll();
    }


    @Transactional
    public void updateMemberName(Long memberId, Member member){
        Member findMember = memberRepository.findById(memberId).get();
        member.setName(findMember.getName());
    }


    @Transactional
    public void deleteMember(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        memberRepository.deleteById(member.getMemberId());

    }


    public void updateEmail(String userEmail) {
    }


    public Member findMemberById(String userId) {
        return memberRepository.findByEmail(userId);
    }
}
