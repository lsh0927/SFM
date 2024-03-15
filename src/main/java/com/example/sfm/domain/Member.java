package com.example.sfm.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "members")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 10)
    private String name;

    @Column(length = 20)
    private String session;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;



    // 내가 하고 싶은게 뭐야
    // 음악인을 위한 종합 플랫폼 개발
    // workBench로 ERD 그리는 법 배워야함





}
