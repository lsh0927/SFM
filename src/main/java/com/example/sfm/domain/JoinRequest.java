package com.example.sfm.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JoinRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId; // 가입 요청 식별자

    private boolean accepted; // 가입 요청 수락 여부
    private boolean proceed;
    private String bandName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 가입 요청을 한 회원

    @OneToOne
    @JoinColumn(name = "band_id")
    private Band band;

}