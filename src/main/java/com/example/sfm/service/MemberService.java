package com.example.sfm.service;

import com.example.sfm.domain.Band;
import com.example.sfm.domain.JoinRequest;
import com.example.sfm.domain.Member;
import com.example.sfm.dto.AddUserRequest;
import com.example.sfm.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //시큐리티 적용 코드


    @Transactional
    public Long join(Member member){
    //    validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getMemberId();
    }

    //회원 가입 객체를 인수로 받는 회원 정보
    public Long save(AddUserRequest dto){
        return memberRepository.save(Member.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getMemberId();
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


//    public Member findMemberById(String userId) {
//        return memberRepository.findByEmail(userId);
//    }
//
//
//
//    public Member findMemberByEmail(String userEmail) {
//        return memberRepository.findByEmail(userEmail);
//    }

    @Transactional
    public void updateMemberSession(Member member, String instrument) {

        member.setSession(instrument);
        memberRepository.save(member);
    }
    @Transactional
    public void updateMemberBand(Member member, Band band) {
        member.setBand(band);
        memberRepository.save(member);

    }

    public void updateJoinRequest(Member member, JoinRequest joinRequest) {
        member.setJoinRequest(joinRequest);
        memberRepository.save(member);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return memberRepository.findByEmail(email)
//                .orElseThrow(()-> new IllegalArgumentException(email));
//    }

//    public void updateMember(Member member) {
//        memberRepository.
//    }
}
