package com.example.sfm.config.jwt;

import com.example.sfm.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class TokenProvider {
    private final JwtProperties jwtProperties;

    public String generateToken(Member member, Duration expiredAt){
        Date now= new Date();
        return makeToken(new Date(now.getTime()+expiredAt.toMillis()),member);
    }

    //Jwt 토큰 생성 메서드
    private String makeToken(Date expiry, Member member){
        Date now= new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                //헤더 타입: JWT, 내용 채워서 서명해서 빌드...
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(member.getEmail())
                .claim("id",member.getMemberId())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    //JWT 토큰 유효성 검증메서드
    public boolean validToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


    //토큰 기반으로 인증 정보를 가져오는 메서드

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject
                (), "", authorities), token, authorities);
    }

    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}

