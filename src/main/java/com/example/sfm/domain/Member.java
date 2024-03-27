package com.example.sfm.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Table(name = "users")
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 10)
    private String name;

    @Column(length = 20)
    private String session;

    @Column(length = 50)
    private String email;


    //새로 들어간 값
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    @Builder
    public Member(String email, String password, String auth){
        this.email=email;
        this.password=password;
    }

    //밴드에서의 역할: 밴드장, 밴드원
    @Enumerated(EnumType.STRING)
    private BandRole bandRole;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "request_id")
    private JoinRequest joinRequest;

    //권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    //패스워드 반환
    @Override
    public String getPassword() {
        return password;
    }

    //사용자의 id 반환(고유한 값)
    @Override
    public String getUsername() {
        return email;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return true;
    }
}
