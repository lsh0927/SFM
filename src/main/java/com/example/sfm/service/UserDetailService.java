package com.example.sfm.service;

import com.example.sfm.domain.Member;
import com.example.sfm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    //내 레포지토리 적용

    //사용자 이름으로 사용자의 정보를 가져오는 메서드
    @Override
    public Member loadUserByUsername(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException(email));
    }
}
