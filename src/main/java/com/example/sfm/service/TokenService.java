package com.example.sfm.service;

import com.example.sfm.config.jwt.TokenProvider;
import com.example.sfm.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final MemberService memberService;

    public String createNewAccessToken(String refreshToken){
        //토큰 유효성 검사에 실패하면
        //예외

        if (!tokenProvider.validToken(refreshToken)){
            throw new IllegalArgumentException("unexpected token");
        }

        Long userId= refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        Member member= memberService.findById(userId);

        return  tokenProvider.generateToken(member, Duration.ofHours(2));
    }
}
